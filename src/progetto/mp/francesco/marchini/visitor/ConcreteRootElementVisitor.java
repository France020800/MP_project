package progetto.mp.francesco.marchini.visitor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;
import progetto.mp.francesco.marchini.strategy.PredicateStrategy;

public class ConcreteRootElementVisitor extends RootElementVisitor {
	
	private Collection<Menu> elements = new ArrayList<>();
	private VisitorForRecursiveVisitor visitor = new VisitorForRecursiveVisitor();

	public ConcreteRootElementVisitor(PredicateStrategy strategy) {
		super(strategy);
		visitor.setVisitor(this);
	}

	@Override
	protected MenuTab getRootFather(MenuTab rootFather) {
		elements.clear();
		while(rootFather.getFather() != null)
			rootFather = rootFather.getFather();
		return rootFather;
	}

	@Override
	protected void recursiveCollection(MenuTab rootFather) {
		elements
		.addAll(rootFather
				.elementsStream()
				.collect(Collectors.toList()));
		recursionCheck(rootFather);
	}

	@Override
	protected Collection<Menu> filter(PredicateStrategy strategy) {
		return elements.parallelStream()
				.filter(e -> strategy.applyPredicate(e))
				.collect(Collectors.toList());
	}
	
	private void recursionCheck(MenuTab rootFather) {
		rootFather
		.elementsStream()
		.forEach(e -> e.accept(visitor));
	}

	private class VisitorForRecursiveVisitor implements MenuVisitor<Boolean> {

		private RootElementVisitor visitor;
		
		@Override
		public Boolean visitTab(MenuTab t) {
			visitor.recursiveCollection(t);
			return true;
		}

		@Override
		public Boolean visitOption(MenuOption o) {
			return true;
		}
		
		private void setVisitor(RootElementVisitor visitor) {
			this.visitor = visitor;
		}
	}
}
