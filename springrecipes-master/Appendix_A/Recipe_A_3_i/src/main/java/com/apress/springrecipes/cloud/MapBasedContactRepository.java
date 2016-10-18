package com.apress.springrecipes.cloud;

import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by marten on 06-10-14.
 */
@Repository
public class MapBasedContactRepository implements ContactRepository {

    private Map<Long, Contact> contacts = new HashMap<>();

    @Override
    public List<Contact> findAll() {
        return new ArrayList(contacts.values());
    }

    @Override
    public void save(Contact c) {
        if (c.getId() <= 0) {
            long generated = UUID.randomUUID().getMostSignificantBits();
            c.setId(generated);
        }
        contacts.put(c.getId(), c);
    }
}
