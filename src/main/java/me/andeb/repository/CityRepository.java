package me.andeb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import me.andeb.domain.City;

/**
 * Spring Data MongoDB repositório para a cidade.
 */
public interface CityRepository extends MongoRepository<City, String> {

	Optional<City> findOneByName(String name);

	@Override
	void delete(City t);

}
