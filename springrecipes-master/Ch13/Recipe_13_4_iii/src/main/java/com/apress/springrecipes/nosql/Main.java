package com.apress.springrecipes.nosql;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * Created by marten on 03-10-14.
 */
public class Main {

    public static void main(String[] args) {
        final String DB_PATH = System.getProperty("user.home") + "/starwars";
        final GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);

        StarwarsRepository repository = new Neo4jStarwarsRepository(db);

        Transaction tx = db.beginTx();

        // Planets
        Planet dagobah = new Planet();
        dagobah.setName("Dagobah");

        Planet alderaan = new Planet();
        alderaan.setName("Alderaan");

        Planet tatooine = new Planet();
        tatooine.setName("Tatooine");

        dagobah = repository.save(dagobah);
        repository.save(alderaan);
        repository.save(tatooine);

        // Characters
        Character han = new Character();
        han.setName("Han Solo");

        Character leia = new Character();
        leia.setName("Leia Organa");
        leia.setLocation(alderaan);
        leia.addFriend(han);

        Character luke = new Character();
        luke.setName("Luke Skywalker");
        luke.setLocation(tatooine);
        luke.addFriend(han);
        luke.addFriend(leia);

        Character yoda = new Character();
        yoda.setName("Yoda");
        yoda.setLocation(dagobah);
        yoda.setApprentice(luke);

        repository.save(han);
        repository.save(luke);
        repository.save(leia);
        repository.save(yoda);

        tx.success();

        ExecutionEngine engine = new ExecutionEngine(db);
        ExecutionResult result = engine.execute("MATCH (n) RETURN n.name as name");
        String rows = "";
        for ( Map<String, Object> row : result ) {
            for ( Map.Entry<String, Object> column : row.entrySet() ) {
                rows += column.getKey() + ": " + column.getValue() + "; ";
            }
            rows += "\n";
        }
        System.out.println(rows);

    }
}
