package com.apress.springrecipes.nosql;

/**
 * Created by marten on 10-10-14.
 */
public interface StarwarsRepository {

    Planet save(Planet planet);
    Character save(Character charachter);

}
