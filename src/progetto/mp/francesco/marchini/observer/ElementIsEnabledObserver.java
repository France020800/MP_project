package progetto.mp.francesco.marchini.observer;

import progetto.mp.francesco.marchini.composite.Menu;

public class ElementIsEnabledObserver implements Observer {
	
	private Menu element;

	public ElementIsEnabledObserver(Menu element) {
		this.element = element;
	}
	
	@Override
	public void update() {
		element.getFather()
			.elementsStream()
			.filter(e -> e.isEnabled() == false)
			.findAny()
			.ifPresentOrElse(e -> element.getFather().changeStateTo(false), 
								() -> element.getFather().changeStateTo(true));
	}
}