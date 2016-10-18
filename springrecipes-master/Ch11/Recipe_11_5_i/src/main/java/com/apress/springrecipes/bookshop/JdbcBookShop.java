package com.apress.springrecipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class JdbcBookShop extends JdbcDaoSupport implements BookShop {

    public void purchase(final String isbn, final String username) {

        int price = getJdbcTemplate().queryForObject(
                "SELECT PRICE FROM BOOK WHERE ISBN = ?", new Object[]{isbn}, Integer.class);

        getJdbcTemplate().update(
                "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[]{isbn});

        getJdbcTemplate().update(
                "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[]{price, username});
    }
}
