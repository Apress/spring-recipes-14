package com.apress.springrecipes.nosql;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.*;

/**
 * Created by marten on 10-10-14.
 */
@NodeEntity
public class Character {

    @GraphId
    private Long id;
    private String name;

    @RelatedTo(type="LOCATION")
    private Planet location;
    @RelatedTo(type="FRIENDS_WITH")
    private final Set<Character> friends = new HashSet<>();
    @RelatedTo(type="MASTER_OF")
    private Character apprentice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Planet getLocation() {
        return location;
    }

    public void setLocation(Planet location) {
        this.location = location;
    }

    public Character getApprentice() {
        return apprentice;
    }

    public void setApprentice(Character apprentice) {
        this.apprentice = apprentice;
    }

    public Set<Character> getFriends() {
        return Collections.unmodifiableSet(friends);
    }

    public void addFriend(Character friend) {
        friends.add(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Character character = (Character) o;

        if (id != null ? !id.equals(character.id) : character.id != null) return false;
        if (name != null ? !name.equals(character.name) : character.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", friends=" + friends.size() +
                ", apprentice=" + apprentice +
                '}';
    }
}
