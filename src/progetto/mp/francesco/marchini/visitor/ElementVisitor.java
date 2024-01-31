package progetto.mp.francesco.marchini.visitor;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;
import progetto.mp.francesco.marchini.strategy.PredicateStrategy;

public class ElementVisitor implements MenuVisitor<Collection<Menu>> {
	
	private PredicateStrategy strategy;

	public ElementVisitor(PredicateStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public Collection<Menu> visitTab(MenuTab t) {
		return commonVisit(t.elementsStream());
	}

	@Override
	public Collection<Menu> visitOption(MenuOption o) {
		return commonVisit(Stream.of(o));
	}
	
	private Collection<Menu> commonVisit(Stream<Menu> menuStream) {
		return menuStream
				.filter(e -> strategy.applyPredicate(e))
				.collect(Collectors.toList());
	}
}