package progetto.mp.francesco.marchini.strategyTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.strategy.EnabledDisabledStrategy;
import progetto.mp.francesco.marchini.strategy.PredicateStrategy;

public class EnabledDisabledStrategyTest {

	private PredicateStrategy disabledStrategy = new EnabledDisabledStrategy(false);
	private PredicateStrategy enabledStrategy = new EnabledDisabledStrategy(true);
	private Menu wifi = new MenuOption("WiFi");
	
	@Test
	public void testDisabledApplyPredicate() {
		assertThat(disabledStrategy.applyPredicate(wifi)).isTrue();
		
		wifi.enabled();
		
		assertThat(disabledStrategy.applyPredicate(wifi)).isFalse();
	}
	
	@Test
	public void testEnabledApplyPredicate() {
		assertThat(enabledStrategy.applyPredicate(wifi)).isFalse();
		
		wifi.enabled();
		
		assertThat(enabledStrategy.applyPredicate(wifi)).isTrue();
	}
}
