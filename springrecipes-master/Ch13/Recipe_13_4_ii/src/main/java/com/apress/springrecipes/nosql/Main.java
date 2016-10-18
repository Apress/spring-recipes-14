package com.apress.springrecipes.nosql;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.*;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.util.Map;

import static com.apress.springrecipes.nosql.Main.RelationshipTypes.*;

/**
 * Created by marten on 03-10-14.
 */
public class Main {

    enum RelationshipTypes implements RelationshipType {FRIENDS_WITH, MASTER_OF, SIBLING, LOCATION};

    public static void main(String[] args) {
        final String DB_PATH = System.getProperty("user.home") + "/friends";
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);

        Transaction tx1 = db.beginTx();

        Label character = DynamicLabel.label("character");
        Label planet = DynamicLabel.label("planet");


        // Planets
        Node dagobah = db.createNode(planet);
        dagobah.setProperty("name", "Dagobah");

        Node tatooine = db.createNode(planet);
        tatooine.setProperty("name", "Tatooine");

        Node alderaan = db.createNode(planet);
        alderaan.setProperty("name", "Alderaan");

        // Characters
        Node yoda = db.createNode(character);
        yoda.setProperty("name", "Yoda");

        Node luke = db.createNode(character);
        luke.setProperty("name", "Luke Skywalker");

        Node leia = db.createNode(character);
        leia.setProperty("name", "Leia Organa");

        Node han = db.createNode(character);
        han.setProperty("name", "Han Solo");

        // Relations
        yoda.createRelationshipTo(luke, MASTER_OF);
        yoda.createRelationshipTo(dagobah, LOCATION);
        luke.createRelationshipTo(leia, SIBLING);
        luke.createRelationshipTo(tatooine, LOCATION);
        luke.createRelationshipTo(han, FRIENDS_WITH);
        leia.createRelationshipTo(han, FRIENDS_WITH);
        leia.createRelationshipTo(alderaan, LOCATION);

        tx1.success();

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
