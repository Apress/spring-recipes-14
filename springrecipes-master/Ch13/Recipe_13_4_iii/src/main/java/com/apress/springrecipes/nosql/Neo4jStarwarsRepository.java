package com.apress.springrecipes.nosql;

import org.neo4j.graphdb.*;

import static com.apress.springrecipes.nosql.RelationshipTypes.*;

/**
 * Created by marten on 10-10-14.
 */
public class Neo4jStarwarsRepository implements StarwarsRepository {

    private final GraphDatabaseService db;

    public Neo4jStarwarsRepository(GraphDatabaseService db) {
        this.db = db;
    }


    @Override
    public Planet save(Planet planet) {
        if (planet.getId() != null) {
            return planet;
        }
        Transaction tx = db.beginTx();
        Label label =  DynamicLabel.label("planet");
        Node node = db.createNode(label);
        node.setProperty("name", planet.getName());
        tx.success();
        planet.setId(node.getId());
        return planet;
    }

    @Override
    public Character save(Character character) {
        if (character.getId() != null) {
            return character;
        }
        Transaction tx = db.beginTx();
        Label label =  DynamicLabel.label("character");
        Node node = db.createNode(label);
        node.setProperty("name", character.getName());

        if (character.getLocation() != null) {
            Planet planet = character.getLocation();
            planet = save(planet);
            node.createRelationshipTo(db.getNodeById(planet.getId()), LOCATION);
        }

        for (Character friend : character.getFriends()) {
            friend = save(friend);
            node.createRelationshipTo(db.getNodeById(friend.getId()), FRIENDS_WITH);
        }

        if (character.getApprentice() != null) {
            save(character.getApprentice());
            node.createRelationshipTo(db.getNodeById(character.getApprentice().getId()), MASTER_OF);
        }

        tx.success();
        character.setId(node.getId());
        return character;
    }

}
