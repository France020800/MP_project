package progetto.mp.francesco.marchini.visitorTests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;
import progetto.mp.francesco.marchini.strategy.EnabledDisabledStrategy;
import progetto.mp.francesco.marchini.strategy.NameStrategy;
import progetto.mp.francesco.marchini.visitor.ConcreteRootElementVisitor;
import progetto.mp.francesco.marchini.visitor.MenuVisitor;

public class RootElementVisitorTest {
	
	private MenuTab settings;
	private MenuTab connections;
	private MenuTab accounts;
	private Menu wifi;
	private Menu bluetooth;
	private MenuTab advancedOptions;
	private Menu hotspot;
	private Menu google;
	private MenuVisitor<Collection<Menu>> fullNameVisitor = 
			new ConcreteRootElementVisitor(new NameStrategy("WiFi"));
	private MenuVisitor<Collection<Menu>> nameVisitor = 
			new ConcreteRootElementVisitor(new NameStrategy("A"));
	private MenuVisitor<Collection<Menu>> enabledVisitor = 
			new ConcreteRootElementVisitor(new EnabledDisabledStrategy(true));
	private MenuVisitor<Collection<Menu>> disabledVisitor = 
			new ConcreteRootElementVisitor(new EnabledDisabledStrategy(false));
	
	@Before
	public void setup() {
		settings = new MenuTab("Settings");
		connections = new MenuTab("Connections");
		accounts = new MenuTab("Accounts");
		wifi = new MenuOption("WiFi");
		bluetooth = new MenuOption("Bluetooth");
		advancedOptions = new MenuTab("Advanced options");
		hotspot = new MenuOption("Hotspot");
		google = new MenuOption("Google");
		settings.add(connections);
		settings.add(accounts);
		connections.add(wifi);
		connections.add(bluetooth);
		connections.add(advancedOptions);
		advancedOptions.add(hotspot);
		accounts.add(google);
		connections.enabled();
	}

	@Test
	public void testElementsFindByNameInTab() {
		assertThat(settings
				.accept(fullNameVisitor))
		.containsExactly(wifi);
		
		assertThat(accounts
				.accept(fullNameVisitor))
		.containsExactly(wifi);
		
		assertThat(settings
				.accept(nameVisitor))
		.containsExactlyInAnyOrder(accounts, advancedOptions);
		
		assertThat(accounts
				.accept(nameVisitor))
		.containsExactlyInAnyOrder(accounts, advancedOptions);
	}

	@Test
	public void testElementsFindByNameInOption() {
		assertThat(hotspot
				.accept(fullNameVisitor))
		.containsExactly(wifi);
		
		assertThat(wifi
				.accept(nameVisitor))
		.containsExactlyInAnyOrder(accounts, advancedOptions);
		
	}
	
	@Test
	public void testFindElementsEnabledInTab() {
		assertThat(settings
				.accept(enabledVisitor))
		.containsExactlyInAnyOrder(connections, bluetooth, wifi, advancedOptions, hotspot);
		
		
		connections.disabled();
		
		assertThat(settings
				.accept(enabledVisitor))
		.isEmpty();
	}
	
	@Test
	public void testFindElementsDisabledInOption() {
		assertThat(hotspot
				.accept(enabledVisitor))
		.containsExactlyInAnyOrder(connections, bluetooth, wifi, advancedOptions, hotspot);
		
		connections.disabled();
		
		assertThat(hotspot
				.accept(enabledVisitor))
		.isEmpty();
	}

	@Test
	public void testFindElementsDisabledInTab() {
		assertThat(connections
				.accept(disabledVisitor))
		.containsExactlyInAnyOrder(accounts, google);
		
		settings.enabled();
		
		assertThat(connections
				.accept(disabledVisitor))
		.isEmpty();
	}
	
	@Test
	public void testFindElementsEnabledInOption() {
		assertThat(wifi
				.accept(disabledVisitor))
		.containsExactlyInAnyOrder(accounts, google);

		settings.enabled();
		
		assertThat(wifi
				.accept(disabledVisitor))
		.isEmpty();
	}
}
