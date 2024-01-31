package progetto.mp.francesco.marchini.strategy;

import progetto.mp.francesco.marchini.composite.Menu;

public class EnabledDisabledStrategy implements PredicateStrategy {
	
	private boolean isEnabled;
	
	public EnabledDisabledStrategy(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public boolean applyPredicate(Menu element) {
		return element.isEnabled() == isEnabled;
	}
}