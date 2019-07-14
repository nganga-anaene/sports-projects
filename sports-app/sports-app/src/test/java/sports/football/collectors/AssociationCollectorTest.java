package sports.football.collectors;

import static org.junit.Assert.*;

import org.junit.Test;

import sports.football.collector.AssociationCollector;

public class AssociationCollectorTest {

	@Test
	public void test() {
		AssociationCollector collector = new AssociationCollector();
		collector.readConfederationUrls();
	}

}
