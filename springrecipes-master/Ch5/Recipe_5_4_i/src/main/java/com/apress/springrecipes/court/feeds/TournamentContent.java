package com.apress.springrecipes.court.feeds;

import java.util.Date;


public class TournamentContent {
    private static int idCounter = 0;
    private String author;
    private Date publicationDate;
    private String name;
    private String link;
    private int id;

    public static TournamentContent generateContent(String author, Date date, String name, String link) {
        TournamentContent content = new TournamentContent();
        content.author = author;
        content.publicationDate = date;
        content.name = name;
        content.link = link;
        content.id = idCounter++;

        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public int getId() {
        return id;
    }
}
