package progetto.mp.francesco.marchini.strategyTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;
import progetto.mp.francesco.marchini.strategy.NameStrategy;
import progetto.mp.francesco.marchini.strategy.PredicateStrategy;

public class NameStrategyTest {
	
	private PredicateStrategy nameStrategy = new NameStrategy("A");
	private Menu account = new MenuTab("Account");
	private Menu wifi = new MenuOption("WiFi");

	@Test
	public void testNameApplyPredicate() {
		assertThat(nameStrategy.applyPredicate(account)).isTrue();
		assertThat(nameStrategy.applyPredicate(wifi)).isFalse();
	}
}
