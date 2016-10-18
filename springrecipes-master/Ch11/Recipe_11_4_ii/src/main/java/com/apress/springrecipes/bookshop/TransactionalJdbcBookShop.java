package com.apress.springrecipes.bookshop;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;


public class TransactionalJdbcBookShop extends JdbcDaoSupport implements BookShop {

    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void purchase(final String isbn, final String username) {

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {

            protected void doInTransactionWithoutResult(
                    TransactionStatus status) {

            int price = getJdbcTemplate().queryForObject(
                    "SELECT PRICE FROM BOOK WHERE ISBN = ?", new Object[]{isbn}, Integer.class);

            getJdbcTemplate().update(
                    "UPDATE BOOK_STOCK SET STOCK = STOCK - 1 WHERE ISBN = ?", new Object[]{isbn});

            getJdbcTemplate().update(
                    "UPDATE ACCOUNT SET BALANCE = BALANCE - ? WHERE USERNAME = ?", new Object[]{price, username});
            }
        });
    }


}
