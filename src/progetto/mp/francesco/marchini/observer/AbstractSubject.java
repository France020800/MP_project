package progetto.mp.francesco.marchini.observer;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractSubject {
	
	private Collection<Observer> observers = new ArrayList<>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}

	public void detachAll() {
		observers.clear();
	}
	
	public void notifyObservers() {
		observers.forEach(Observer::update);
	}
}
