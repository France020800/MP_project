package progetto.mp.francesco.marchini.visitor;

import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;

public interface MenuVisitor<T> {

	T visitTab(MenuTab t);
	
	T visitOption(MenuOption o);
	
}
