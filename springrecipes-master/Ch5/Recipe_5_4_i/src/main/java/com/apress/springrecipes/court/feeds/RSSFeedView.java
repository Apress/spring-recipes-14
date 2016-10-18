package com.apress.springrecipes.court.feeds;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class RSSFeedView extends AbstractRssFeedView {
    protected void buildFeedMetadata(Map model, Channel feed, HttpServletRequest request) {
        feed.setTitle("World Soccer Tournaments");
        feed.setDescription("FIFA World Soccer Tournament Calendar");
        feed.setLink("tennis.org");

        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        for (TournamentContent tournament : tournamentList) {
            Date date = tournament.getPublicationDate();

            if ((feed.getLastBuildDate() == null) || (date.compareTo(feed.getLastBuildDate()) > 0)) {
                feed.setLastBuildDate(date);
            }
        }
    }

    protected List buildFeedItems(Map model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");
        List<Item> items = new ArrayList<Item>(tournamentList.size());

        for (TournamentContent tournament : tournamentList) {
            Item item = new Item();
            String date = String.format("%1$tY-%1$tm-%1$td", tournament.getPublicationDate());
            item.setAuthor(tournament.getAuthor());
            item.setTitle(String.format("%s - Posted by %s", tournament.getName(), tournament.getAuthor()));
            item.setPubDate(tournament.getPublicationDate());
            item.setLink(tournament.getLink());
            items.add(item);
        }

        return items;
    }
}
