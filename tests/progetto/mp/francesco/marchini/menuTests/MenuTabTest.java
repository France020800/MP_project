package progetto.mp.francesco.marchini.menuTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.francesco.marchini.composite.Menu;
import progetto.mp.francesco.marchini.composite.MenuOption;
import progetto.mp.francesco.marchini.composite.MenuTab;

public class MenuTabTest {
	
	MenuTab settings;
	MenuTab connections;
	Menu wifi;
	
	@Before
	public void setup() {
		settings = new MenuTab("Settings");
		connections = new MenuTab("Connections");
		wifi = new MenuOption("WiFi");
	}
	
	@Test
	public void testAdd() {
		settings.add(connections);
		connections.add(wifi);
		assertThat(settings.elementsStream()).containsExactly(connections);
		assertThat(connections.elementsStream()).containsExactly(wifi);
	}
	
	@Test
	public void testRemove() {
		settings.add(connections);
		settings.remove(connections);
		assertThat(settings.elementsStream()).isEmpty();
		connections.add(wifi);
		connections.remove(wifi);
		assertThat(connections.elementsStream()).isEmpty();
	}
	
	@Test
	public void testElementStream() {
		settings.add(connections);
		assertThat(settings.elementsStream()).containsExactly(connections);
		connections.add(wifi);
		assertThat(connections.elementsStream()).containsExactly(wifi);
	}

	@Test
	public void testEnabled() {
		settings.add(connections);
		connections.add(wifi);
		settings.enabled();
		assertThat(settings.isEnabled()).isTrue();
		assertThat(connections.isEnabled()).isTrue();
		assertThat(wifi.isEnabled()).isTrue();
	}
	
	@Test
	public void testDisabled() {
		settings.add(connections);
		connections.add(wifi);
		settings.enabled();
		settings.disabled();
		assertThat(settings.isEnabled()).isFalse();
		assertThat(connections.isEnabled()).isFalse();
		assertThat(wifi.isEnabled()).isFalse();
	}
	
	@Test
	public void testToString() {
		settings.add(connections);
		connections.add(wifi);
		Menu bluetooth = new MenuOption("BlueTooth");
		connections.add(bluetooth);
		MenuTab advanced = new MenuTab("Advanced options");
		connections.add(advanced);
		assertThat(settings.toString()).isEqualTo("MenuTab: Settings"
				+ "[MenuTab: Connections"
				+ "[MenuOption: WiFi, MenuOption: BlueTooth, MenuTab: Advanced options[]]]");
	}
	
	@Test
	public void testGetFather() {
		settings.add(connections);
		assertThat(connections.getFather()).isEqualTo(settings);
		assertThat(settings.getFather()).isNull();
	}

}
