package com.apress.springrecipes.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by marten on 06-10-14.
 */
@Service
public class JdbcContactRepository extends JdbcDaoSupport implements ContactRepository {

    @Autowired
    public JdbcContactRepository(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public List<Contact> findAll() {
        return getJdbcTemplate().query("select id, name, email from contact", new RowMapper<Contact>() {
            @Override
            public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
                Contact contact = new Contact();
                contact.setId(rs.getLong(1));
                contact.setName(rs.getString(2));
                contact.setEmail(rs.getString(3));
                return contact;
            }
        });
    }

    @Override
    public void save(Contact c) {
        getJdbcTemplate().update("insert into contact (name, email) values (?, ?)", c.getName(), c.getEmail());
    }


}
