package com.apress.springrecipes.nosql;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by marten on 15-10-14.
 */
public interface PlanetRepository extends GraphRepository<Planet> {}
