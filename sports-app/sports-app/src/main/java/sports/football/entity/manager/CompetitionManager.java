package sports.football.entity.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.collector.CompetitionCollector;
import sports.football.entity.Association;
import sports.football.entity.Competition;
import sports.football.parser.CompetitionParser;

@Component
public class CompetitionManager {

	@Autowired
	private CompetitionCollector competitionCollector;
	@Autowired
	private CompetitionParser competitionParser;
	@Autowired
	private FootballEntityManager fem;

	public CompetitionManager() {
	}

	public void processCompetitions() {
		fem.deleteAllCompetitions();
		competitionCollector.readCompetitionUrls();
		competitionParser.parseCompetitions(competitionCollector.getWebpages());
		competitionParser.writeJsonFiles();
		saveCompetitions(competitionParser.getCompetitionDetails());
	}

	private void saveCompetitions(Map<String, List<String>> competitionDetails) {
		competitionDetails.entrySet().forEach(entry -> {
			entry.getValue().forEach(name -> {
				Association association = fem.getAssociationByName(entry.getKey());
				if (association != null) {
					Competition competition = new Competition(name, association);
					fem.saveCompetition(competition);
				}
			});
		});
	}
}
