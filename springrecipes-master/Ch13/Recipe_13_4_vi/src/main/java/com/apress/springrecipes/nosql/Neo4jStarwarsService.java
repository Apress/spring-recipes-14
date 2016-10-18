package com.apress.springrecipes.nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;

/**
 * Created by marten on 10-10-14.
 */
@Service
@Transactional
public class Neo4jStarwarsService implements StarwarsService {


    private final PlanetRepository planetRepository;
    private final CharacterRepository characterRepository;

    @Autowired
    public Neo4jStarwarsService(PlanetRepository planetRepository, CharacterRepository characterRepository) {
        this.planetRepository=planetRepository;
        this.characterRepository=characterRepository;
    }


    @Override
    public Planet save(Planet planet) {
        return planetRepository.save(planet);
    }

    @Override
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public void printAll() {
        Result<Planet> planets = planetRepository.findAll();
        Result<Character> characters = characterRepository.findAll();

        for (Planet p : planets) {
            System.out.println(p);
        }

        for (Character c : characters) {
            System.out.println(c);
        }
    }

    @PreDestroy
    public void cleanUp() {
        // Clean up when shutdown
        characterRepository.deleteAll();
        planetRepository.deleteAll();
    }

}
