package progetto.mp.francesco.marchini.observerTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;

public class ElementIsEnabledObserverTest {
	
	private Menu wifi;
	private MenuTab settings;
	private MenuTab connections;
	
	@Before
	public void setup() {
		wifi = new MenuOption("WiFi");
		connections = new MenuTab("Connections");
		settings = new MenuTab("Settings");
		settings.add(connections);
		connections.add(wifi);
	}

	@Test
	public void testElementIsEnabledOption() {
		wifi.enabled();
		assertThat(connections.isEnabled()).isTrue();
		assertThat(settings.isEnabled()).isTrue();
	}
	
	@Test
	public void testElementIsDisabledOption() {
		wifi.enabled();
		wifi.disabled();
		assertThat(connections.isEnabled()).isFalse();
		assertThat(settings.isEnabled()).isFalse();
	}

	@Test
	public void testElementIsEnabledTab() {
		MenuTab advanced = new MenuTab("Advaced");
		connections.add(advanced);
		wifi.enabled();
		advanced.enabled();
		assertThat(connections.isEnabled()).isTrue();
		assertThat(settings.isEnabled()).isTrue();
	}
	
	@Test
	public void testElementIsDisabledTab() {
		MenuTab advanced = new MenuTab("Advanced");
		connections.add(advanced);
		wifi.enabled();
		wifi.disabled();
		advanced.enabled();
		advanced.disabled();
		assertThat(connections.isEnabled()).isFalse();
		assertThat(settings.isEnabled()).isFalse();
	}
}
