package me.andeb.web.rest;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.andeb.domain.City;
import me.andeb.repository.CityRepository;
import me.andeb.service.CityService;
import me.andeb.service.ForecastService;
import me.andeb.web.rest.dto.CityDTO;
import me.andeb.web.rest.errors.ErrorDTO;

/**
 * Recurso para controle da inserção, exlusão e listagem das cidades disponíveis.
 * 
 * @author Anderson de Borba
 */
@RestController
@RequestMapping("cities")
public class CityResource {

	private final Logger log = LoggerFactory.getLogger(CityResource.class);

	@Inject
	private CityRepository cityRepository;

	@Inject
	private CityService cityService;

	@Inject
	private ForecastService forecastService;

	/**
	 * POST / -> Registra uma nova cidade.
	 */
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerAccount(@Valid @RequestBody CityDTO cityDTO, HttpServletRequest request) {
		String name = cityDTO.getName();

		return cityRepository.findOneByName(name).map(user -> new ResponseEntity<>(new ErrorDTO("A cidade " + name + " já está cadastrada.", null), HttpStatus.BAD_REQUEST)).orElseGet(() -> {
			if (!forecastService.hasCity(name)) {
				return new ResponseEntity<>(new ErrorDTO("Não encontrou previsão do tempo para a cidade " + name + ".", null), HttpStatus.BAD_REQUEST);
			}

			cityService.addCity(name);
			log.debug("Cadastrado cidade através do resource {}", name);
			return new ResponseEntity<>(HttpStatus.CREATED);
		});
	}

	/**
	 * GET / -> retorna todas as cidades disponíveis.
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<City>> getCities() {
		return Optional.ofNullable(cityService.getCities()).map(user -> new ResponseEntity<List<City>>(user, HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 * GET /{name} -> retorna o recurso da cidade.
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> getCity(@PathVariable("name") String cityName) {
		return Optional.ofNullable(cityService.getCity(cityName)).map(city -> new ResponseEntity<City>(city.get(), HttpStatus.OK)).orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 * DELETE /{id} -> deleta uma cidade de acordo com o ID.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<City> deleteCity(@PathVariable("id") String id) {
		cityService.deleteCity(id);
		log.debug("Excluído cidade através do resource {}", id);
		return new ResponseEntity<City>(HttpStatus.OK);
	}

}
