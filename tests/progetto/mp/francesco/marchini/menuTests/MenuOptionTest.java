package progetto.mp.francesco.marchini.menuTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;

public class MenuOptionTest {
	
	private Menu wifi;
	
	@Before
	public void setup() {
		wifi = new MenuOption("WiFi");
	}

	@Test
	public void testEnabled() {
		wifi.enabled();
		assertThat(wifi.isEnabled()).isTrue();
	}
	
	@Test
	public void testDisabled() {
		wifi.enabled();
		wifi.disabled();
		assertThat(wifi.isEnabled()).isFalse();
	}
	
	@Test
	public void testToString() {
		assertThat(wifi.toString()).isEqualTo("MenuOption: WiFi");
	}
	
	@Test
	public void testGetFather() {
		MenuTab connections = new MenuTab("Connections");
		connections.add(wifi);
		assertThat(wifi.getFather()).isEqualTo(connections);
	}
}
