package com.apress.springrecipes.nosql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * Created by marten on 10-10-14.
 */
@Repository
@Transactional
public class Neo4jStarwarsRepository implements StarwarsRepository {

    private final Neo4jTemplate template;

    @Autowired
    public Neo4jStarwarsRepository(Neo4jTemplate template) {
        this.template = template;
    }


    @Override
    public Planet save(Planet planet) {
        template.save(planet);
        return planet;
    }

    @Override
    public Character save(Character character) {
        template.save(character);
        return character;
    }

    @Override
    public void printAll() {
        Result<Map<String, Object>> result = template.queryEngineFor().query("MATCH (n) RETURN n.name as name", null);
        String rows = "";
        for ( Map<String, Object> row : result ) {
            for ( Map.Entry<String, Object> column : row.entrySet() ) {
                rows += column.getKey() + ": " + column.getValue() + "; ";
            }
            rows += "\n";
        }
        System.out.println(rows);
    }

    @PreDestroy
    public void cleanUp() {
        // Clean up when shutdown
        template.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", null);
    }

}
