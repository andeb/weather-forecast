package me.andeb.web.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.andeb.domain.City;
import me.andeb.repository.CityRepository;
import me.andeb.service.ForecastService;
import me.andeb.web.rest.dto.ForecastDTO;

/**
 * Recurso para controle da exibição da previsão do tempo.
 * 
 * @author Anderson de Borba
 */
@RestController
@RequestMapping("forecast")
public class ForecastResource {

	@Inject
	private CityRepository cityRepository;

	@Inject
	private ForecastService forecastService;

	/**
	 * GET /{name} -> retorna a previsão do tempo para os próximos cinco dias de acordo com uma cidade.
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ForecastDTO>> getCity(@PathVariable("name") String cityName) {
		Optional<City> city = cityRepository.findOneByName(cityName);
		if (!city.isPresent())
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		// List<ForecastDTO> list = new ArrayList<>();
		// list.add(newRandom());
		// list.add(newRandom());
		// list.add(newRandom());
		// list.add(newRandom());
		// list.add(newRandom());

		List<ForecastDTO> forecast = forecastService.getForecast(cityName);
		return new ResponseEntity<>(forecast, HttpStatus.OK);
	}

	// private ForecastDTO newRandom() {
	// ForecastDTO dto = new ForecastDTO();
	// dto.setTempCelsius("" + ((int) (Math.random() * 10000) / 100.0));
	// dto.setType("rain");
	// dto.setDate(LocalDateTime.now().plusMinutes((long) Math.random() * 10000).toString());
	// dto.setIcon("02d.png");
	// return dto;
	// }

}
