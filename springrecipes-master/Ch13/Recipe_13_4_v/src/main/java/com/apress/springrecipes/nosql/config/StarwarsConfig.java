package com.apress.springrecipes.nosql.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

/**
 * Created by marten on 10-10-14.
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = {"com.apress.springrecipes.nosql"})
@ComponentScan(basePackages = {"com.apress.springrecipes.nosql"})
public class StarwarsConfig extends Neo4jConfiguration {

    @PostConstruct
    public void init() {
        setBasePackage("com.apress.springrecipes.nosql");
    }


    @Bean(destroyMethod = "shutdown")
    public GraphDatabaseService graphDatabaseService() {
        final String DB_PATH = System.getProperty("user.home") + "/starwars";
        return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
    }


}
