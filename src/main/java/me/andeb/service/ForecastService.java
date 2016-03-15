package me.andeb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import me.andeb.domain.Forecast;
import me.andeb.domain.Forecast.ForecastDetail;
import me.andeb.web.rest.dto.ForecastDTO;

/**
 * Serviço para retornar informações sobre a previsão do tempo.
 * 
 * @author Anderson de Borba
 */
@Service
public class ForecastService {

	private static final String API_KEY = "eb8b1a9405e659b2ffc78f0a520b1a46";;

	private final Logger log = LoggerFactory.getLogger(ForecastService.class);

	private String URL = "http://api.openweathermap.org/data/2.5/forecast?q={cityName}&units=metric&mode=json&appid=" + API_KEY;

	/**
	 * Retorna a previsão do tempo de uma cidade para os próximos cinco dias.
	 * 
	 * @param cityName
	 *            o nome da cidade da previsão
	 * @return a previsão do tempo de uma cidade para os próximos cinco dias
	 */
	public List<ForecastDTO> getForecast(String cityName) {
		Forecast forObject = callWeatherMapAPi(cityName).get();

		List<ForecastDTO> dtos = new ArrayList<>();
		List<ForecastDetail> list = forObject.getList();
		for (int i = 0; i < list.size(); i++) {
			ForecastDetail fd = list.get(i);
			if (i++ % 8 == 0) { // 24 / 3 == 8, então pulamos sempre 8 marcações para pegar o horário do próximo dia.
				ForecastDTO forecastDTO = new ForecastDTO();

				forecastDTO.setDate(fd.getDt_txt());
				forecastDTO.setIcon(fd.getWeather().get(0).getIcon());
				forecastDTO.setTempCelsius(Double.toString(fd.getMain().getTemp()));
				forecastDTO.setType(fd.getWeather().get(0).getDescription());

				dtos.add(forecastDTO);
			}
		}
		return dtos;
	}

	/**
	 * Realiza a chamada para a API do OpenWeather.
	 * 
	 * @param cityName
	 *            nome da cidade para pesquisar
	 * @return o objeto de domínio da chamada
	 */
	private Optional<Forecast> callWeatherMapAPi(String cityName) {
		RestTemplate restTemplate = new RestTemplate();

		try {
			Map<String, String> vars = Collections.singletonMap("cityName", cityName);

			return Optional.of(restTemplate.getForObject(URL, Forecast.class, vars));
		} catch (RestClientException e) {
			log.error(e.getMessage(), e);
			return Optional.<Forecast> empty();
		}
	}

	/**
	 * Retorna <code>true</code> caso exista a cidade disponível para consulta na API.
	 * 
	 * @param cityName
	 *            o nome da cidade a ser testada
	 * @return <code>true</code> caso exista a cidade disponível para consulta na API
	 */
	public boolean hasCity(String cityName) {
		Optional<Forecast> optional = callWeatherMapAPi(cityName);

		return optional.map(forecast -> forecast.getCity() != null && cityName.equalsIgnoreCase(forecast.getCity().getName())).orElse(false);
	}

}
