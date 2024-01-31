package progetto.mp.francesco.marchini.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import progetto.mp.francesco.marchini.observer.ElementIsEnabledObserver;
import progetto.mp.francesco.marchini.visitor.MenuVisitor;

public class MenuTab extends Menu {

	private Collection<Menu> elements = new ArrayList<>();

	public MenuTab(String name) {
		super(name);
	}
	
	public void add(Menu element) {
		elements.add(element);
		element.setFather(this);
		element.attach(new ElementIsEnabledObserver(element));
	}
	
	public void remove(Menu element) {
		elements.remove(element);
		element.setFather(null);
		element.detachAll();
	}
	
	public Stream<Menu> elementsStream() {
		return elements.stream();
	}
	
	@Override
	public String toString() {
		return super.toString() + elements;
	}
	
	@Override
	public void enabled() {
		changeStateTo(true);
		elements.forEach(e -> e.enabled());
	}

	@Override
	public void disabled() {
		changeStateTo(false);
		elements.forEach(e -> e.disabled());
	}

	@Override
	public <T> T accept(MenuVisitor<T> visitor) {
		return visitor.visitTab(this);
	}
}
