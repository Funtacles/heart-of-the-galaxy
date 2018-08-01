package com.lilithsthrone.world.places;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.encounters.Encounter;
import com.lilithsthrone.game.dialogue.places.JunglePlaces;
import com.lilithsthrone.game.dialogue.places.dominion.CityHall;
import com.lilithsthrone.game.dialogue.places.dominion.CityPlaces;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.DreamLover;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.ShoppingArcadeDialogue;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SupplierDepot;
import com.lilithsthrone.game.dialogue.places.dominion.slaverAlley.SlaverAlleyDialogue;
import com.lilithsthrone.game.inventory.CharacterInventory;
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
	
	DOMINION_BOULEVARD("Dominion Boulevard", null, null, Colour.MAP_BACKGROUND_PINK, CityPlaces.BOULEVARD, Encounter.DOMINION_BOULEVARD, false, false, true, "in the streets of Dominion") {

		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	DOMINION_SHOPPING_ARCADE("Shopping Arcade", "dominion/shoppingArcadeIcon", BaseColour.GOLD, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.OUTSIDE, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return DOMINION_PLAZA.getSpeciesPopulatingArea();
		}
	},
	
	DOMINION_STREET_HARPY_NESTS("Dominion Streets", null, null, Colour.MAP_BACKGROUND_DARK, CityPlaces.STREET_SHADED, Encounter.DOMINION_STREET, false, false, true, "in the streets of Dominion") {
		@Override
		public boolean isDangerous() {
			return Main.game.getCurrentWeather() == Weather.MAGIC_STORM;
		}
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
	
	DOMINION_SLAVER_ALLEY("Slaver Alley", "dominion/slaverAlleyIcon",  BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.OUTSIDE, null, false, false, true, "in the alleyways near Slaver's Alley") {
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

	// Shopping arcade:
	
	SHOPPING_ARCADE_PATH("Arcade", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.ARCADE, null, false, true, true, "in the Shopping Arcade") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},

	SHOPPING_ARCADE_GENERIC_SHOP("Shop", "dominion/shoppingArcade/genericShop", BaseColour.BLACK, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.GENERIC_SHOP, null, false, true, true, "in the Shopping Arcade") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},
	
	SHOPPING_ARCADE_KATES_SHOP("Succubi's Secrets", "dominion/shoppingArcade/kateShop", BaseColour.PINK, Colour.MAP_BACKGROUND, SuccubisSecrets.EXTERIOR, null, false, true, true, "in her beauty salon"),

	SHOPPING_ARCADE_ASHLEYS_SHOP("Dream Lover", "dominion/shoppingArcade/ashleyShop", BaseColour.LILAC_LIGHT, Colour.MAP_BACKGROUND, DreamLover.EXTERIOR, null, false, true, true, "in their store"),
	
	SHOPPING_ARCADE_SUPPLIER_DEPOT("Supplier Depot", "dominion/shoppingArcade/supplierDepot", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SupplierDepot.EXTERIOR, null, false, true, true, "in the supplier depot") {
		@Override
		public BaseColour getColour() {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return BaseColour.GREEN;
			} else {
				return BaseColour.CRIMSON;
			}
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return PlaceType.getSVGOverride("dominion/shoppingArcade/supplierDepot", Colour.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	},
	
	// Exits & entrances:
	SHOPPING_ARCADE_ENTRANCE("Exit", "dominion/shoppingArcade/exit", BaseColour.RED, Colour.MAP_BACKGROUND, ShoppingArcadeDialogue.ENTRY, null, false, true, true, "in the Shopping Arcade"),
	
	
	// Supplier Depot:
	
	SUPPLIER_DEPOT_CORRIDOR("Corridor", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_CORRIDOR, null, false, true, false, "in the supplier depot"),
	
	SUPPLIER_DEPOT_ENTRANCE("Reception Area", "dominion/shoppingArcade/exit", BaseColour.GREY, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_RECEPTION, null, false, true, false, "in the supplier depot") {
		@Override
		public boolean isPopulated() {
			return Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP);
		}
		@Override
		public String getSVGString(Set<PlaceUpgrade> upgrades) {
			if(Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP)) {
				return PlaceType.getSVGOverride("dominion/shoppingArcade/exit", Colour.BASE_GREEN);
			} else {
				return SVGString;
			}
		}
	},
	
	SUPPLIER_DEPOT_STORAGE_ROOM("Storage Room", "dominion/shoppingArcade/supplierStorageRoom", BaseColour.ORANGE, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_STORAGE_ROOM, null, false, true, false, "in the supplier depot"),
	
	SUPPLIER_DEPOT_OFFICE("Office", "dominion/shoppingArcade/supplierOffice", BaseColour.CRIMSON, Colour.MAP_BACKGROUND, SupplierDepot.SUPPLIER_DEPOT_OFFICE, null, true, true, false, "in the supplier depot") {
		@Override
		public boolean isDangerous() {
			return !Main.game.getPlayer().isQuestCompleted(QuestLine.RELATIONSHIP_NYAN_HELP);
		}
		@Override
		public DialogueNodeOld getDialogue(boolean withRandomEncounter, boolean forceEncounter) {
			if(Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.suppliersEncountered)) {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE_REPEAT;
				
			} else {
				return SupplierDepot.SUPPLIER_DEPOT_OFFICE;
			}
		}
	},
	
	
	// Slaver Alley:
	
	SLAVER_ALLEY_PATH("Alleyway", null, BaseColour.BLACK, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.ALLEYWAY, null, false, true, true, "in Slaver's Alley") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},

	SLAVER_ALLEY_MARKET_STALL("Slaver's Shop", "dominion/slaverAlley/marketStall", BaseColour.BLACK, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.MARKET_STALL, null, false, true, true, "in Slaver's Alley") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},
	
	SLAVER_ALLEY_AUCTIONING_BLOCK("Auctioning Block", "dominion/slaverAlley/auctionBlock", BaseColour.GOLD, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.AUCTION_BLOCK, null, false, true, true, "in Slaver's Alley") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},

	SLAVER_ALLEY_PUBLIC_STOCKS("Public Stocks", "dominion/slaverAlley/stocks", BaseColour.TAN, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.PUBLIC_STOCKS, null, false, true, true, "in the stocks at Slaver's Alley") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},

	SLAVER_ALLEY_SLAVERY_ADMINISTRATION("Slavery Administration", "dominion/slaverAlley/slaveryAdministration", BaseColour.PURPLE, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.SLAVERY_ADMINISTRATION_EXTERIOR, null, false, true, true, "in Slaver's Alley"){
		@Override
		public ArrayList<PlaceUpgrade> getStartingPlaceUpgrades() {
			return Util.newArrayListOfValues(PlaceUpgrade.SLAVERY_ADMINISTRATION_CELLS);
		}
	},
	
	SLAVER_ALLEY_ENTRANCE("Gateway", "dominion/slaverAlley/exit", BaseColour.RED, Colour.MAP_BACKGROUND, SlaverAlleyDialogue.GATEWAY, null, false, true, true, "in Slaver's Alley") {
		@Override
		public List<Subspecies> getSpeciesPopulatingArea() {
			return Subspecies.getWorldSpecies().get(WorldType.DOMINION);
		}
	},
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
	
	public void applyInventoryInit(CharacterInventory inventory) {
		
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

	public String getVirgintyLossDescription() {
		return virgintyLossDescription;
	}
}
