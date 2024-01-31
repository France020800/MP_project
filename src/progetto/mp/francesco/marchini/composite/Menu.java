package progetto.mp.francesco.marchini.composite;

import progetto.mp.francesco.marchini.observer.AbstractSubject;
import progetto.mp.francesco.marchini.visitor.MenuVisitor;

public abstract class Menu extends AbstractSubject {

	private String name;
	private MenuTab father;
	private boolean isEnabled;
	
	public Menu(String name) {
		this.name = name;     
	}
	
	public String getName() {
		return name;
	}
	
	public MenuTab getFather() {
		return father;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + name;
	}
	
	public void changeStateTo(boolean isEnabled) {
		this.isEnabled = isEnabled;
		notifyObservers();
	}
	
	protected void setFather(MenuTab father) {
		this.father = father;
	}
	
	public abstract void enabled();
	
	public abstract void disabled();
	
	public abstract <T> T accept(MenuVisitor<T> visitor);
}
