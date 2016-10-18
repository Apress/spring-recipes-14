package com.apress.springrecipes.bookshop;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class BookShopCashier implements Cashier {
    private BookShop bookShop;

    public void setBookShop(BookShop bookShop) {
        this.bookShop = bookShop;
    }

    @Transactional
    public void checkout(List<String> isbns, String username) {
        for (String isbn : isbns) {
            bookShop.purchase(isbn, username);
        }
    }
}
