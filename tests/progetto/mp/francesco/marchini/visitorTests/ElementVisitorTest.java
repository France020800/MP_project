package progetto.mp.francesco.marchini.visitorTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;
import progetto.mp.francesco.marchini.strategy.EnabledDisabledStrategy;
import progetto.mp.francesco.marchini.strategy.NameStrategy;
import progetto.mp.francesco.marchini.strategy.PredicateStrategy;
import progetto.mp.francesco.marchini.visitor.ElementVisitor;

public class ElementVisitorTest {
	
	private PredicateStrategy fullNameStrategy = new NameStrategy("Hotspot");
	private PredicateStrategy nameStrategy = new NameStrategy("W");
	private PredicateStrategy enabledStrategy = new EnabledDisabledStrategy(true);
	private PredicateStrategy disabledStrategy = new EnabledDisabledStrategy(false);
	private Menu wifi;
	
	@Before
	public void setup() {
		wifi = new MenuOption("WiFi");
	}
	
	@Test
	public void testElementsFindByNameInTab() {
		MenuTab connections = new MenuTab("Connections");
		MenuTab advancedSettings = new MenuTab("Advanced settings");
		Menu wirelessSharing= new MenuOption("Wireless sharing");
		Menu hotspot = new MenuOption("Hotspot");
		connections.add(advancedSettings);
		connections.add(wirelessSharing);
		connections.add(wifi);
		advancedSettings.add(hotspot);
		
		assertThat(advancedSettings
				.accept(new ElementVisitor(fullNameStrategy)))
		.containsExactly(hotspot);
		
		assertThat(connections
				.accept(new ElementVisitor(nameStrategy)))
		.containsExactlyInAnyOrder(wifi, wirelessSharing);
		
		assertThat(advancedSettings
				.accept(new ElementVisitor(nameStrategy)))
		.isEmpty();
	}
	
	@Test
	public void testElementFindByNameInOption() {		
		assertThat(wifi.accept(new ElementVisitor(fullNameStrategy))).isEmpty();
		
		assertThat(wifi
				.accept(new ElementVisitor(nameStrategy)))
		.containsExactly(wifi);
	}
	
	@Test
	public void testFindElementsEneabledInTab() {
		MenuTab connection = new MenuTab("Connection");
		MenuTab advancedOption = new MenuTab("Advanced options");
		connection.add(wifi);
		connection.add(advancedOption);
		assertThat(connection
				.accept(new ElementVisitor(enabledStrategy)))
		.isEmpty();
		
		wifi.enabled();
		assertThat(connection
				.accept(new ElementVisitor(enabledStrategy)))
		.containsExactly(wifi);
		
		advancedOption.enabled();
		assertThat(connection
				.accept(new ElementVisitor(enabledStrategy)))
		.containsExactlyInAnyOrder(wifi, advancedOption);
	}
	
	@Test
	public void testFindElementsDisabledInTab() {
		MenuTab connection = new MenuTab("Connection");
		MenuTab advancedOption = new MenuTab("Advanced options");
		connection.add(wifi);
		connection.add(advancedOption);
		assertThat(connection
				.accept(new ElementVisitor(disabledStrategy)))
		.containsExactlyInAnyOrder(wifi, advancedOption);
		
		wifi.enabled();
		assertThat(connection
				.accept(new ElementVisitor(disabledStrategy)))
		.containsExactly(advancedOption);
		
		advancedOption.enabled();
		assertThat(connection
				.accept(new ElementVisitor(disabledStrategy)))
		.isEmpty();
	}
	
	@Test
	public void testFindElementEnabledInOption() {
		assertThat(wifi
				.accept(new ElementVisitor(enabledStrategy)))
		.isEmpty();
		
		wifi.enabled();
		assertThat(wifi
				.accept(new ElementVisitor(enabledStrategy)))
		.containsExactly(wifi);
	}
	
	@Test
	public void testFindElementDisabledInOption() {
		assertThat(wifi
				.accept(new ElementVisitor(disabledStrategy)))
		.containsExactly(wifi);
		
		wifi.enabled();
		assertThat(wifi
				.accept(new ElementVisitor(disabledStrategy)))
		.isEmpty();
	}

}
