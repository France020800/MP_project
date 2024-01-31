package progetto.mp.francesco.marchini.visitor;

import java.util.Collection;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;
import progetto.mp.francesco.marchini.strategy.PredicateStrategy;

public abstract class RootElementVisitor implements MenuVisitor<Collection<Menu>> {
	
	private PredicateStrategy strategy;
	
	public RootElementVisitor(PredicateStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public Collection<Menu> visitTab(MenuTab t) {
		return commonVisit(t);
	}
	
	@Override
	public Collection<Menu> visitOption(MenuOption o) {
		return commonVisit(o.getFather());
	}
	
	private final Collection<Menu> commonVisit(MenuTab father) {
		recursiveCollection(getRootFather(father));
		return filter(strategy);
	}
	
	protected abstract MenuTab getRootFather(MenuTab rootFather);	
	protected abstract void recursiveCollection(MenuTab rootFather);
	protected abstract Collection<Menu> filter(PredicateStrategy strategy);
}