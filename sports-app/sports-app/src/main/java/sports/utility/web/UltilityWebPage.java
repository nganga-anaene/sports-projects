package sports.utility.web;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

public class UltilityWebPage {

	private String associationName;
	private String competitionName;
	private String teamUrl;
	private List<Document> docs;

	public UltilityWebPage() {
	}

	public UltilityWebPage(String associationName, String competitionName, String teamUrl) {
		this.associationName = associationName;
		this.competitionName = competitionName;
		this.teamUrl = teamUrl;
		docs = new ArrayList<>();
	}

	public String getAssociationName() {
		return associationName;
	}

	public String getCompetitionName() {
		return competitionName;
	}

	public String getTeamUrl() {
		return teamUrl;
	}

	public void addDoc(Document doc) {
		docs.add(doc);
	}

	public List<Document> getDocs() {
		return docs;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[associationName=%s, competitionName=%s, teamsUrl=%s, no of web docs=%d]",
				associationName, competitionName, teamUrl, docs.size());
	}
}
