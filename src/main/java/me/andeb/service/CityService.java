package me.andeb.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import me.andeb.domain.City;
import me.andeb.repository.CityRepository;

/**
 * Servi�o para manipula��o das cidades.
 * 
 * @author Anderson de Borba
 */
@Service
public class CityService {

	private final Logger log = LoggerFactory.getLogger(CityService.class);

	@Inject
	private CityRepository cityRepository;

	/**
	 * Adiciona uma nova cidade.
	 * 
	 * @param cityName
	 *            o nome da cidade
	 * @return a cidade criada
	 */
	public City addCity(String cityName) {
		City newCity = new City();
		newCity.setName(cityName);

		cityRepository.save(newCity);

		log.debug("Cadastrado cidade {} atrav�s do servi�o.", newCity);
		return newCity;
	}

	/**
	 * Retorna todas as cidades cadastradas.
	 * 
	 * @return todas as cidades cadastradas
	 */
	public List<City> getCities() {
		return cityRepository.findAll();
	}

	/**
	 * Retorna uma cidade pelo nome.
	 * 
	 * @param name
	 *            o nome da cidade que deve ser encontrada
	 * @return uma cidade pelo nome
	 */
	public Optional<City> getCity(String name) {
		return cityRepository.findOneByName(name);
	}

	/**
	 * Exclui uma cidade pelo seu identificado ou n�o faz nada caso n�o encontre cidade com tal identificador.
	 * 
	 * @param id
	 *            identificador da cidade
	 */
	public void deleteCity(String id) {
		cityRepository.delete(id);
		log.debug("Exclu�do cidade {} atrav�s do servi�o.", id);
	}

}
