package com.apress.springrecipes.nosql;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

/**
 * Created by marten on 03-10-14.
 */
public class Main {

    public static void main(String[] args) {
        final String DB_PATH = System.getProperty("user.home") + "/friends";
        GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);

        Transaction tx1 = db.beginTx();

        Node hello = db.createNode();
        hello.setProperty("msg", "Hello");

        Node world = db.createNode();
        world.setProperty("msg", "World");
        tx1.success();


        Iterable<Node> nodes = db.getAllNodes();
        for (Node n : nodes) {
            System.out.println("Msg: " + n.getProperty("msg"));
        }

        // Remove all nodes
    }
}
