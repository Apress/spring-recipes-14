package com.apress.springrecipes.nosql;

import redis.clients.jedis.Jedis;

/**
 * Created by marten on 29-09-14.
 */
public class Main {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        jedis.rpush("authors", "Marten Deinum", "Josh Long", "Daniel Rubio", "Gary Mak");
        System.out.println("Authors: " + jedis.lrange("authors",0,-1));

        jedis.hset("sr_3", "authors", "Gary Mak, Danial Rubio, Josh Long, Marten Deinum");
        jedis.hset("sr_3", "published", "2014");

        System.out.println("Spring Recipes 3rd: " + jedis.hgetAll("sr_3"));

    }
}
