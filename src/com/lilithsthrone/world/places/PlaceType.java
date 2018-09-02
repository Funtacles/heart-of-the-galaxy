package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.JunglePlaces;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.LilithsTower;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Lab;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.Library;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayaHomeGeneric;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.LilayasRoom;
import com.lilithsthrone.game.dialogue.places.dominion.lilayashome.RoomPlayer;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.BaseColour;
import com.lilithsthrone.utils.Bearing;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.EntranceType;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public enum PlaceType {
	
	
	GENERIC_IMPASSABLE(null, null, null, Colour.MAP_BACKGROUND, null, null, false, false, true, ""),
	
	GENERIC_EMPTY_TILE("Empty", "dominion/slaverAlleyIcon",  BaseColour.CRIMSON, Colour.MAP_BACKGROUND, null, null, false, false, true, ""),

	GENERIC_HOLDING_CELL("Holding cell", "dominion/slaverAlleyIcon",  BaseColour.CRIMSON, Colour.MAP_BACKGROUND, null, null, false, false, true, ""),
	
	GENERIC_MUSEUM("Museum", "dominion/slaverAlleyIcon",  BaseColour.TAN, Colour.MAP_BACKGROUND, null, null, false, true, false, "in Lily's Museum"),
	
	DOMINION_PLAZA("Lilith's Plaza", "dominion/statue",  BaseColour.PINK_DEEP, Colour.MAP_BACKGROUND_PINK, CityPlaces.DOMINION_PLAZA, null, false, false, true, "in Dominion's central plaza") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			if(Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				return Subspecies.getDominionStormImmuneSpecies();
			} else {
				return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
			}
		}
	},
	
	DOMINION_STREET("Dominion Streets", null, null, Colour.MAP_BACKGROUND, CityPlaces.STREET, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	DOMINION_LILITHS_TOWER("Lilith's Tower", "dominion/lilithsTowerIcon", BaseColour.PURPLE, Colour.MAP_BACKGROUND_PINK, LilithsTower.OUTSIDE, null, false, false, true, "in the streets of Dominion") {

		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	DOMINION_CITY_HALL("City Hall", "dominion/townHallIcon",  BaseColour.LILAC, Colour.MAP_BACKGROUND, CityHall.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	DOMINION_AUNTS_HOME("Lilaya's Home", "dominion/homeIcon", BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, LilayaHomeGeneric.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	DOMINION_PARK("Park", "dominion/park", BaseColour.GREEN, Colour.MAP_BACKGROUND, CityPlaces.PARK, Encounter.DOMINION_STREET, false, false, true, "in one of Dominion's parks") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	// Alleyways:
	
	DOMINION_BACK_ALLEYS("Dominion Alleyways", "dominion/alleysIcon",  BaseColour.BLACK, Colour.MAP_BACKGROUND, CityPlaces.BACK_ALLEYS, Encounter.DOMINION_ALLEY, true, false, true, "in one of Dominion's backalleys"),

	DOMINION_DARK_ALLEYS("Dark Alleyways", "dominion/alleysDarkIcon",  BaseColour.PURPLE, Colour.MAP_BACKGROUND, CityPlaces.DARK_ALLEYS, Encounter.DOMINION_DARK_ALLEY, true, false, true, "in one of Dominion's dark alleyways"),
	
	DOMINION_ALLEYS_CANAL_CROSSING("Canal Crossing", "dominion/bridge",  BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, CityPlaces.BACK_ALLEYS_CANAL, Encounter.DOMINION_ALLEY, true, false, true, "in one of Dominion's backalleys"),
	
	// Canals:
	
	DOMINION_CANAL("Dominion Canal", "dominion/canalIcon",  BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, CityPlaces.CANAL, Encounter.DOMINION_CANAL, true, false, true, "beside one of Dominion's canals"),
	
	DOMINION_CANAL_END("Dominion Canal", "dominion/canalEndIcon",  BaseColour.BLUE, Colour.MAP_BACKGROUND, CityPlaces.CANAL_END, Encounter.DOMINION_CANAL, true, false, true, "beside one of Dominion's canals"),
	
	// Exits & entrances:
	
	DOMINION_EXIT_TO_JUNGLE("Jungle Entrance", "dominion/JungleExit",  BaseColour.GREEN_LIME, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_JUNGLE, null, false, false, true, "in the streets of Dominion") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.NORTH;
		}
	},
	
	DOMINION_EXIT_TO_FIELDS("Fields Entrance", "dominion/fieldsExit",  BaseColour.GREEN_LIGHT, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_FIELDS, null, false, false, true, "in the streets of Dominion") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.WEST;
		}
	},
	
	DOMINION_EXIT_TO_SEA("Endless Sea Entrance", "dominion/endlessSeaExit",  BaseColour.TEAL, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_SEA, null, false, false, true, "in the streets of Dominion") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.EAST;
		}
	},
	
	DOMINION_EXIT_TO_DESERT("Desert Entrance", "dominion/desertExit", BaseColour.YELLOW, Colour.MAP_BACKGROUND_PINK, CityPlaces.CITY_EXIT_DESERT, null, false, false, true, "in the streets of Dominion") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
		@Override
		public Bearing getBearing() {
			return Bearing.SOUTH;
		}
	},
	
	// Standard tiles:
	JUNGLE_PATH("Jungle Path", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.PATH, null, false, false, true, "in the jungle"),
	
	JUNGLE_DENSE_JUNGLE("Dense Jungle", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.DENSE_JUNGLE, null, true, false, true, "in the jungle"),

	// Safe places:
	JUNGLE_CLUB("Club", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.CLUB, null, false, false, true, "in the jungle"),
	
	JUNGLE_BROTHEL("Brothel", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.BROTHEL, null, false, false, true, "in the jungle"),

	// Dangerous places:
	JUNGLE_TENTACLE_QUEENS_LAIR("Tentacle Queen's Lair", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.TENTACLE_QUEENS_LAIR, null, false, false, true, "in the jungle"),

	// Exits & entrances:
	JUNGLE_ENTRANCE("Jungle Entrance", null, BaseColour.GREEN, Colour.MAP_BACKGROUND, JunglePlaces.JUNGLE_ENTRANCE, null, false, false, true, "in the jungle"){
//		@Override
//		public WorldType getParentWorldType() {
//			return WorldType.DOMINION;
//		}
//		@Override
//		public PlaceType getParentPlaceType() {
//			return PlaceType.DOMINION_EXIT_TO_JUNGLE;
//		}
//		@Override
//		public EntranceType getParentAlignment() {
//			return EntranceType.ALIGNED_FLIP_VERTICAL;
//		}
	},
	
	
	
	// Ground floor:
	
	LILAYA_HOME_CORRIDOR("Corridor", null, BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.CORRIDOR, Encounter.LILAYAS_HOME_CORRIDOR, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_WINDOW_GROUND_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " G-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_GROUND_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN_GROUND_FLOOR, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " GG-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_WINDOW_FIRST_FLOOR("Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_WINDOW, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " F-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ROOM_GARDEN_FIRST_FLOOR("Garden Room", "dominion/lilayasHome/room", BaseColour.GREY, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_GARDEN, null, false, true, false, "in Lilaya's Home") {
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.LILAYA_EMPTY_ROOM);
		}
		@Override
		public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
			return getAvailableLilayaRoomPlaceUpgrades(upgrades);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			return getLilayaRoomSVGString(upgrades);
		}
		@Override
		public boolean isAbleToBeUpgraded() {
			return true;
		}
		@Override
		public String getPlaceNameAppendFormat(int count) {
			return " FG-"+String.format("%02d", count);
		}
	},
	
	LILAYA_HOME_ARTHUR_ROOM("Arthur's Room", "dominion/lilayasHome/roomArthur", BaseColour.BLUE_STEEL, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_ARTHUR, null, false, true, false, "in Arthur's Room"),
	
	LILAYA_HOME_BIRTHING_ROOM("Room", "dominion/lilayasHome/roomBirthing", BaseColour.PINK, Colour.MAP_BACKGROUND, LilayaHomeGeneric.BIRTHING_ROOM, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_KITCHEN("Kitchen", "dominion/lilayasHome/kitchen", BaseColour.TAN, Colour.MAP_BACKGROUND, LilayaHomeGeneric.KITCHEN, null, false, true, false, "in Lilaya's kitchen"),
	
	LILAYA_HOME_LIBRARY("Library", "dominion/lilayasHome/library", BaseColour.TEAL, Colour.MAP_BACKGROUND, Library.LIBRARY, null, false, true, false, "in Lilaya's library"),
	
	LILAYA_HOME_STAIR_UP("Staircase", "dominion/lilayasHome/stairsUp", BaseColour.GREEN_LIGHT, Colour.MAP_BACKGROUND, LilayaHomeGeneric.STAIRCASE_UP, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ENTRANCE_HALL("Entrance Hall", "dominion/lilayasHome/entranceHall", BaseColour.RED, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ENTRANCE_HALL, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_LAB("Lilaya's Lab", "dominion/lilayasHome/lab", BaseColour.ORANGE, Colour.MAP_BACKGROUND, Lab.LAB, null, false, true, false, "in Lilaya's lab"),
	
	LILAYA_HOME_GARDEN("Garden", "dominion/lilayasHome/garden", BaseColour.GREEN, Colour.MAP_BACKGROUND, LilayaHomeGeneric.GARDEN, null, false, false, false, "in Lilaya's garden"),
	
	LILAYA_HOME_FOUNTAIN("Fountain", "dominion/lilayasHome/fountain", BaseColour.BLUE_LIGHT, Colour.MAP_BACKGROUND, LilayaHomeGeneric.FOUNTAIN, null, false, false, false, "in Lilaya's garden"),
	

	// First floor:

	LILAYA_HOME_ROOM_LILAYA("Lilaya's Room", "dominion/lilayasHome/roomLilaya", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, LilayasRoom.ROOM_LILAYA, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_ROSE("Rose's Room", "dominion/lilayasHome/roomRose", BaseColour.PINK, Colour.MAP_BACKGROUND, LilayaHomeGeneric.ROOM_ROSE, null, false, true, false, "in Lilaya's Home"),
	
	LILAYA_HOME_ROOM_PLAYER("Your Room", "dominion/lilayasHome/roomPlayer", BaseColour.AQUA, Colour.MAP_BACKGROUND, RoomPlayer.ROOM, null, false, true, false, "in your room"),
	
	LILAYA_HOME_STAIR_DOWN("Staircase", "dominion/lilayasHome/stairsDown", BaseColour.RED, Colour.MAP_BACKGROUND, LilayaHomeGeneric.STAIRCASE_DOWN, null, false, true, false, "in Lilaya's Home"),
	;

	
	private String name;
	protected String SVGString;
	private BaseColour colour;
	private Colour backgroundColour;
	protected DialogueNodeOld dialogue;
	private Encounter encounterType;
	private boolean dangerous, stormImmune, itemsDisappear;
	private String virgintyLossDescription;

	private static Map<String, String> SVGOverrides = new HashMap<>(); 
	
	private PlaceType(String name,
			String SVGPath,
			BaseColour colour,
			Colour backgroundColour,
			DialogueNodeOld dialogue,
			Encounter encounterType,
			boolean dangerous,
			boolean stormImmune,
			boolean itemsDisappear,
			String virgintyLossDescription) {
		
		this.name = name;
		this.colour = colour;
		this.backgroundColour = backgroundColour;
		this.dialogue = dialogue;
		this.encounterType = encounterType;
		this.dangerous = dangerous;
		this.stormImmune = stormImmune;
		this.itemsDisappear = itemsDisappear;
		this.virgintyLossDescription = virgintyLossDescription;
		
		if(SVGPath!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + SVGPath + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+SVGPath+"')! (Code 1)");
				}
				String s = Util.inputStreamToString(is);
				
				if(colour!=null) {
					s = s.replaceAll("#ff2a2a", this.colour.getShades()[0]);
					s = s.replaceAll("#ff5555", this.colour.getShades()[1]);
					s = s.replaceAll("#ff8080", this.colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", this.colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", this.colour.getShades()[4]);
				}
				SVGString = s;
	
				is.close();
	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			SVGString = null;
		}
	}

	public String getName() {
		return name;
	}

	public BaseColour getColour() {
		return colour;
	}

	public Colour getBackgroundColour() {
		return backgroundColour;
	}

	public Encounter getEncounterType() {
		return encounterType;
	}

	public DialogueNodeOld getDialogue(boolean withRandomEncounter) {
		return getDialogue(withRandomEncounter, false);
	}
	
	public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
		if (getEncounterType() != null && withRandomEncounter) {
			DialogueNodeOld dn = getEncounterType().getRandomEncounter(forceEncounter);
			if (dn != null) {
				return dn;
			}
		}

		return dialogue;
	}
	
	public List<Subspecies> getSpeciesPopulatingArea() {
		return null;
	}
	
	public boolean isPopulated() {
		return getSpeciesPopulatingArea()!=null && !getSpeciesPopulatingArea().isEmpty();
	}

	public boolean isDangerous() {
		return dangerous;
	}

	public boolean isStormImmune() {
		return stormImmune;
	}
	
	public boolean isItemsDisappear() {
		return itemsDisappear;
	}
	
	private static String getSVGOverride(String pathName, Colour colour) {
		if(!SVGOverrides.keySet().contains(pathName+colour)) {
			try {
				InputStream is = colour.getClass().getResourceAsStream("/com/lilithsthrone/res/map/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! PlaceType icon file does not exist (Trying to read from '"+pathName+"')! (Code 2)");
				}
				String s = Util.inputStreamToString(is);
				
				if(colour!=null) {
					s = s.replaceAll("#ff2a2a", colour.getShades()[0]);
					s = s.replaceAll("#ff5555", colour.getShades()[1]);
					s = s.replaceAll("#ff8080", colour.getShades()[2]);
					s = s.replaceAll("#ffaaaa", colour.getShades()[3]);
					s = s.replaceAll("#ffd5d5", colour.getShades()[4]);
				}
				SVGOverrides.put(pathName+colour, s);
	
				is.close();
	
			} catch (Exception e1) {
				System.err.println("Eeeeeek! PlaceType.getSVGOverride()");
				e1.printStackTrace();
				return "";
			}
		}
		
		return SVGOverrides.get(pathName+colour);
	}
	
	public String getSVGString(Set<PlaceUpgrade> upgrades) {
		return SVGString;
	}
	
	// For determining where this place should be placed:
	
	public Bearing getBearing() {
		return null;
	}
	
	public WorldType getParentWorldType() {
		return null;
	}
	
	public PlaceType getParentPlaceType() {
		return null;
	}
	
	public EntranceType getParentAlignment() {
		return null;
	}
	
	public String getPlaceNameAppendFormat(int count) {
		return "";
	}
	
	public boolean isAbleToBeUpgraded() {
		return false;
	}
	
	public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
		return new ArrayList<>();
	}
	
	public ArrayList<PlaceUpgrade> getAvailablePlaceUpgrades(Set<PlaceUpgrade> upgrades) {
		return new ArrayList<>();
	}

	public static ArrayList<PlaceUpgrade> getAvailableLilayaRoomPlaceUpgrades(Set<PlaceUpgrade> upgrades) {
		if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			return PlaceUpgrade.getGuestRoomUpgrades();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesSingle();
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
			return PlaceUpgrade.getSlaveQuartersUpgradesDouble();
		}
			
		return PlaceUpgrade.getCoreRoomUpgrades();
	}
	
	public String getLilayaRoomSVGString(Set<PlaceUpgrade> upgrades) {
		if(upgrades.contains(PlaceUpgrade.LILAYA_GUEST_ROOM)) {
			return PlaceType.getSVGOverride("dominion/lilayasHome/roomGuest", Colour.BASE_GREEN_LIGHT);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM)) {
			return PlaceType.getSVGOverride("dominion/lilayasHome/roomSlave", Colour.BASE_CRIMSON);
			
		} else if(upgrades.contains(PlaceUpgrade.LILAYA_SLAVE_ROOM_DOUBLE)) {
			return PlaceType.getSVGOverride("dominion/lilayasHome/roomSlaveDouble", Colour.BASE_MAGENTA);
			
		} else {
			return SVGString;
		}
	}

	public String getVirgintyLossDescription() {
		return virgintyLossDescription;
	}
}
