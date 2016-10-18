package com.apress.springrecipes.court.feeds;

import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class AtomFeedView extends AbstractAtomFeedView {
    protected void buildFeedMetadata(Map model, Feed feed, HttpServletRequest request) {
        feed.setId("tag:tennis.org");
        feed.setTitle("Grand Slam Tournaments");

        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");

        for (TournamentContent tournament : tournamentList) {
            Date date = tournament.getPublicationDate();

            if ((feed.getUpdated() == null) || (date.compareTo(feed.getUpdated()) > 0)) {
                feed.setUpdated(date);
            }
        }
    }

    protected List buildFeedEntries(Map model, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        List<TournamentContent> tournamentList = (List<TournamentContent>) model.get("feedContent");
        List<Entry> entries = new ArrayList<Entry>(tournamentList.size());

        for (TournamentContent tournament : tournamentList) {
            Entry entry = new Entry();
            String date = String.format("%1$tY-%1$tm-%1$td", tournament.getPublicationDate());
            entry.setId(String.format("tag:tennis.org,%s:%d", date, tournament.getId()));
            entry.setTitle(String.format("%s - Posted by %s", tournament.getName(), tournament.getAuthor()));
            entry.setUpdated(tournament.getPublicationDate());

            Content summary = new Content();
            summary.setValue(String.format("%s - %s", tournament.getName(), tournament.getLink()));
            entry.setSummary(summary);

            entries.add(entry);
        }

        return entries;
    }
}
