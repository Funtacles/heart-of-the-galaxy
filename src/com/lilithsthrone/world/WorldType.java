package com.lilithsthrone.world;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.6
 * @author Innoxia
 */
public enum WorldType {
	
	// Dominion:
	
	DOMINION("Dominion",
			Colour.BASE_PURPLE,
			1,
			"/com/lilithsthrone/res/map/dominion/dominion.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					
					new Value<>(new Color(0x808080), PlaceType.DOMINION_STREET),
					new Value<>(new Color(0x404040), PlaceType.DOMINION_BOULEVARD),
					
					new Value<>(new Color(0xc10000), PlaceType.DOMINION_BACK_ALLEYS),
					new Value<>(new Color(0x5b0000), PlaceType.DOMINION_DARK_ALLEYS),
					new Value<>(new Color(0x40b4ff), PlaceType.DOMINION_ALLEYS_CANAL_CROSSING),
					
					new Value<>(new Color(0x0080ff), PlaceType.DOMINION_CANAL),
					new Value<>(new Color(0x004080), PlaceType.DOMINION_CANAL_END),
					
					new Value<>(new Color(0x8000ff), PlaceType.DOMINION_CITY_HALL),

					new Value<>(new Color(0x8080ff), PlaceType.DOMINION_EXIT_TO_SEA),
					new Value<>(new Color(0xff4a00), PlaceType.DOMINION_EXIT_TO_FIELDS),
					new Value<>(new Color(0x008040), PlaceType.DOMINION_EXIT_TO_JUNGLE),
					new Value<>(new Color(0xffff80), PlaceType.DOMINION_EXIT_TO_DESERT),
					
					new Value<>(new Color(0x008080), PlaceType.DOMINION_STREET_HARPY_NESTS),

					new Value<>(new Color(0x004000), PlaceType.DOMINION_PLAZA),
					new Value<>(new Color(0xffff00), PlaceType.DOMINION_SHOPPING_ARCADE),
					new Value<>(new Color(0xff0000), PlaceType.DOMINION_SLAVER_ALLEY),
					new Value<>(new Color(0x4bff00), PlaceType.DOMINION_PARK)
					)),
	

	// Empty:
	EMPTY("City",
			Colour.BASE_BROWN,
			1,
			"/com/lilithsthrone/res/map/empty.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0xff0000), PlaceType.GENERIC_EMPTY_TILE),
					new Value<>(new Color(0xffff00), PlaceType.GENERIC_HOLDING_CELL),
					new Value<>(new Color(0x0080ff), PlaceType.GENERIC_MUSEUM))),
	
	SLAVER_ALLEY("Slaver Alley",
			Colour.BASE_RED,
			1,
			"/com/lilithsthrone/res/map/dominion/slaverAlley/slaverAlley.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SLAVER_ALLEY_PATH),
					new Value<>(new Color(0xff0000), PlaceType.SLAVER_ALLEY_ENTRANCE),
					new Value<>(new Color(0xff00ff), PlaceType.SLAVER_ALLEY_MARKET_STALL),
					new Value<>(new Color(0x0000ff), PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION),
					new Value<>(new Color(0xffff00), PlaceType.SLAVER_ALLEY_AUCTIONING_BLOCK),
					new Value<>(new Color(0x00ff00), PlaceType.SLAVER_ALLEY_PUBLIC_STOCKS))),
	
	SHOPPING_ARCADE("Shopping Arcade",
			Colour.BASE_YELLOW,
			1,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/shoppingArcade.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SHOPPING_ARCADE_PATH),
					new Value<>(new Color(0xff0000), PlaceType.SHOPPING_ARCADE_ENTRANCE),
					new Value<>(new Color(0x0080ff), PlaceType.SHOPPING_ARCADE_VICKYS_SHOP),
					new Value<>(new Color(0xff8000), PlaceType.SHOPPING_ARCADE_KATES_SHOP),
					new Value<>(new Color(0xff00ff), PlaceType.SHOPPING_ARCADE_GENERIC_SHOP),
					new Value<>(new Color(0x008000), PlaceType.SHOPPING_ARCADE_ASHLEYS_SHOP),
					new Value<>(new Color(0x00ff00), PlaceType.SHOPPING_ARCADE_SUPPLIER_DEPOT)
					)){
		@Override
		public boolean isRevealedOnStart() {
			return true;
		}
	},
	
	SUPPLIER_DEN("Supplier Depot",
			Colour.BASE_CRIMSON,
			1,
			"/com/lilithsthrone/res/map/dominion/shoppingArcade/supplierDen.png",
			Util.newHashMapOfValues(
					new Value<>(new Color(0xFFFFFF), PlaceType.GENERIC_IMPASSABLE),
					new Value<>(new Color(0x808080), PlaceType.SUPPLIER_DEPOT_CORRIDOR),
					new Value<>(new Color(0xff0000), PlaceType.SUPPLIER_DEPOT_ENTRANCE),
					new Value<>(new Color(0xff00ff), PlaceType.SUPPLIER_DEPOT_STORAGE_ROOM),
					new Value<>(new Color(0x00ff00), PlaceType.SUPPLIER_DEPOT_OFFICE))),
	
	// Other:

	JUNGLE(6,
			"jungle",
			Colour.BASE_GREEN_LIME,
			240,
			PlaceType.JUNGLE_PATH,
			PlaceType.JUNGLE_DENSE_JUNGLE,
			Util.newArrayListOfValues(
					PlaceType.JUNGLE_BROTHEL,
					PlaceType.JUNGLE_CLUB,
					PlaceType.JUNGLE_ENTRANCE),

			Util.newArrayListOfValues(PlaceType.JUNGLE_TENTACLE_QUEENS_LAIR));

	
	private final String name, fileLocation;
	private Colour colour;
	private int worldSize, timeToTransition;
	
	private int tileSetRowNumber, moveCost;
	private PlaceType standardPlace, cutOffZone;
	private List<PlaceType> places, dangerousPlaces;
	
	private boolean usesFile;
	private Map<Color, PlaceType> placesMap;
	

	WorldType(int worldSize,
			String name,
			Colour colour,
			int timeToTransition,
			PlaceType standardPlace,
			PlaceType cutOffZone,
			List<PlaceType> places,
			List<PlaceType> dangerousPlaces) {
		this.worldSize=worldSize;
		
		this.name = name;
		this.colour = colour;
		this.timeToTransition=timeToTransition;
		this.moveCost = 5;

		this.standardPlace = standardPlace;
		this.cutOffZone = cutOffZone;

		this.places = places;
		this.dangerousPlaces = dangerousPlaces;
		
		fileLocation = null;
		usesFile = false;
		
	}
	
	WorldType(String name, Colour colour, int timeToTransition, String fileLocation, Map<Color, PlaceType> placesMap) {
		this.name = name;
		this.colour = colour;
		this.timeToTransition=timeToTransition;
		moveCost = 5;

		standardPlace = null;
		cutOffZone = null;

		places = null;
		dangerousPlaces = null;
		
		this.fileLocation = fileLocation;
		usesFile = true;
		this.placesMap=placesMap;
	}
	
	public int getTileSetRowNumber() {
		return tileSetRowNumber;
	}

	public String getName() {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public int getTimeToTransition() {
		return timeToTransition;
	}

	public int getMoveCost() {
		return moveCost;
	}
	
	public boolean isRevealedOnStart() {
		return false;
	}

	public PlaceType getStandardPlace() {
		return standardPlace;
	}

	public PlaceType getCutOffZone() {
		return cutOffZone;
	}

	public List<PlaceType> getPlaces() {
		return places;
	}

	public List<PlaceType> getDangerousPlaces() {
		return dangerousPlaces;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public boolean isUsesFile() {
		return usesFile;
	}

	public Map<Color, PlaceType> getPlacesMap() {
		return placesMap;
	}

	public int getWorldSize() {
		return worldSize;
	}

}
