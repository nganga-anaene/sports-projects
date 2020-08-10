/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.web;

/**
 *
 * @author Lee
 */
public class JsoupQueries {

    private JsoupQueries() {
    }

    public String selectAssociationTables() {
        return "#mw-content-text > div > table.wikitable.sortable.hlist.jquery-tablesorter";
    }

    public String selectCompetitionDetails() {
        return "#navi > div.sitenavi > div.navibox2 > div > ul > li > a";
    }

    public String selectTeamDetails() {
        return "#site > div.white > div.content > div > div.box > div > table > tbody > tr";
    }
    
    public String mainNavigationBottomActiveListItems() {
        return "#navi > div.subnavi > ul > li > a.active";
    }

    public String selectTeamAssociation() {
        return "td:nth-child(3) > img";
    }

    public String selectTeam() {
        return "td:nth-child(2) > a";
    }

    public String checkAssociationTable() {
        return "thead > tr > th:nth-child(1) > a";
    }

    public String getAssociationsConfederationName() {
        return "firstHeading";
    }

    public String selectRows() {
        return "tbody > tr";
    }

    public String associationString() {
        return "#topnavi > ul > li.active > a";
    }

    public String selectSeasonString() {
        return "#site > div.white > div.content > div > div.box2 > div > table > tbody > tr > td:nth-child(2) > form > select > option";
    }

    public String metaTags() {
        return "meta";
    }

    /*
    Attribute Strings
    */
    public String nameAttribute() {
        return "name";
    }

    public String contentAttribute() {
        return "content";
    }

    public String titleAttribute() {
        return "title";
    }

    public String selectedAttribute() {
        return "selected";
    }

    public String hrefAttribute() {
        return "href";
    }

    public String valueAttribute() {
        return "value";
    }

    /*
    Other Strings
    */
    public String teamsString() {
        return "teams";
    }

    public String fixturesString() {
        return "schedule";
    }

    public String leaguesString() {
        return "archive";
    }

    public String pageTopic() {
        return "page-topic";
    }

    public String worldFootballUrl() {
        return "https://www.worldfootball.net";
    }

    public static JsoupQueries getInstance() {
        return JsoupQueriesHolder.INSTANCE;
    }

    private static class JsoupQueriesHolder {

        private static final JsoupQueries INSTANCE = new JsoupQueries();
    }
}
