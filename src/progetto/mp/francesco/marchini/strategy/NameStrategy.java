package progetto.mp.francesco.marchini.strategy;

import progetto.mp.francesco.marchini.composite.Menu;

public class NameStrategy implements PredicateStrategy {
	
	private String name;
	
	public NameStrategy(String name) {
		this.name = name;
	}

	@Override
	public boolean applyPredicate(Menu element) {
		return element.getName().startsWith(name);
	}
}
