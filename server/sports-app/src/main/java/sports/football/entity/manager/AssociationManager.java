package sports.football.entity.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sports.football.collector.AssociationCollector;
import sports.football.entity.Association;
import sports.football.entity.Confederation;
import sports.football.parser.AssociationParser;

@Component
public class AssociationManager {

	@Autowired
	private AssociationCollector associationCollector;
	@Autowired
	private AssociationParser associationParser;
	@Autowired
	private FootballEntityManager fem;
	
	public AssociationManager() {
	}
	
	public void readAssociations() {
		associationCollector.readConfederationUrls();
		associationParser.parseConfederationPages(associationCollector.getPages());
		associationParser.getAssociations().forEach(fem::saveAssociation);
		readUniqueAssociations();
	}
	
	private void readUniqueAssociations() {
		associationCollector.getUniqueAssociations().entrySet().forEach(entry -> {
			String abb = entry.getValue().toString();
			String name = entry.getKey();
			Confederation confederation = fem.getConfederationByAbbreviation(abb);
			fem.saveAssociation(new Association(abb, name, confederation));
		});
	}
}
