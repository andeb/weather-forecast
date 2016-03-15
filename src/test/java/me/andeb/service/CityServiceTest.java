package me.andeb.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import me.andeb.domain.City;
import me.andeb.repository.CityRepository;

/**
 * Teste unitário simples para o serviço de adição de cidades.
 * 
 * @author Anderson de Borba
 */
@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

	private static final String CITY_NAME = "blumenau";

	@Mock
	private CityRepository cityRepository;

	@InjectMocks
	private CityService service = new CityService();

	@Test
	public void testAddCity() {
		// Service call
		service.addCity(CITY_NAME);

		// Validations
		ArgumentCaptor<City> cityCaptor = ArgumentCaptor.forClass(City.class);

		Mockito.verify(cityRepository).save(cityCaptor.capture());

		assertEquals(CITY_NAME, cityCaptor.getValue().getName());
	}

}