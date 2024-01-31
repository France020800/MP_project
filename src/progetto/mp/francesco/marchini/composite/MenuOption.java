package progetto.mp.francesco.marchini.composite;

import progetto.mp.francesco.marchini.visitor.MenuVisitor;

public class MenuOption extends Menu {

	public MenuOption(String name) {
		super(name);
	}

	@Override
	public void enabled() {
		changeStateTo(true);
	}

	@Override
	public void disabled() {
		changeStateTo(false);
	}

	@Override
	public <T> T accept(MenuVisitor<T> visitor) {
		return visitor.visitOption(this);
	}
}
