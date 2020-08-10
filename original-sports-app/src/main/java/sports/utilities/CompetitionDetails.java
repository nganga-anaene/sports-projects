/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

/**
 *
 * @author Lee
 */
public class CompetitionDetails {

    private String associationName;
    private String teamsUrl;
    private String competitonName;
    private String leaguesUrl;
    private String fixturesUrl;

    public CompetitionDetails() {
    }

    public String getTeamsUrl() {
        return teamsUrl;
    }

    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    public String getCompetitonName() {
        return competitonName;
    }

    public void setCompetitonName(String competitonName) {
        this.competitonName = competitonName;
    }

    public String getLeaguesUrl() {
        return leaguesUrl;
    }

    public void setCompetitionsUrl(String competitionsUrl) {
        this.leaguesUrl = competitionsUrl;
    }

    public String getFixturesUrl() {
        return fixturesUrl;
    }

    public void setFixturesUrl(String fixturesUrl) {
        this.fixturesUrl = fixturesUrl;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Competition Details - [");
        sb.append(", Association Name: ");
        sb.append(associationName);
        sb.append(", Teams url: ");
        sb.append(teamsUrl);
        sb.append(", Competition Url: ");
        sb.append(leaguesUrl);
        sb.append(", Fixture url: ");
        sb.append(fixturesUrl);
        sb.append(", Competition Name: ");
        sb.append(competitonName);
        sb.append("]");
        return sb.toString();
    }

}
