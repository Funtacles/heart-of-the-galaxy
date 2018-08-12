package com.lilithsthrone.game.character.race;

import java.util.List;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.5
 * @author Innoxia
 */
public enum Race {

	NONE("none",
			"none",
			"none",
			"none",
			"none",
			"none",
			"",
			"",
			Colour.RACE_HUMAN,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(Attack.MAIN),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HUMAN,
			Attribute.RESISTANCE_HUMAN,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			false),
	
	// HUMAN:
	HUMAN("human",
			"humans",
			
			"man",
			"woman",
			
			"men",
			"women",
			
			UtilText.parseFromXMLFile("characters/raceInfo", "HUMAN_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "HUMAN_ADVANCED"),
			
			Colour.RACE_HUMAN,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(Attack.MAIN),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HUMAN,
			Attribute.RESISTANCE_HUMAN,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			false),

	// ANGEL:
	ANGEL("angel",
			"angels",
			"angel",
			"angel",
			"angel",
			"angel",

			UtilText.parseFromXMLFile("characters/raceInfo", "ANGEL_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ANGEL_ADVANCED"),

			Colour.CLOTHING_WHITE,
			Disposition.CIVILIZED,
			StatusEffect.PURE_HUMAN,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPELL),
			false,
			0.25f,
			1,
			1,
			Attribute.DAMAGE_ANGEL,
			Attribute.RESISTANCE_ANGEL,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			false),

	// DEMON:
	DEMON("demon",
			"demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",

			UtilText.parseFromXMLFile("characters/raceInfo", "DEMON_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "DEMON_ADVANCED"),

			Colour.RACE_DEMON,
			Disposition.CIVILIZED,
			StatusEffect.DEMON,
			Util.newArrayListOfValues(
					Attack.SPECIAL_ATTACK,
					Attack.SEDUCTION,
					Attack.SPELL),
			false,
			0.75f,
			2,
			3,
			Attribute.DAMAGE_DEMON,
			Attribute.RESISTANCE_DEMON,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),

	IMP("imp",
			"imps",
			"imp",
			"imp",
			"imps",
			"imps",

			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "IMP_ADVANCED"),

			Colour.RACE_DEMON,
			Disposition.UNPREDICTABLE,
			StatusEffect.IMP,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SEDUCTION),
			true,
			0.75f,
			2,
			3,
			Attribute.DAMAGE_IMP,
			Attribute.RESISTANCE_IMP,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),

	// BOVINES:
	COW_MORPH("cow-morph",
			"cow-morphs",
			"cow-boy",
			"cow-girl",
			"cow-boys",
			"cow-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "COW_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "COW_MORPH_ADVANCED"),

			Colour.RACE_COW_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.COW_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_COW_MORPH,
			Attribute.RESISTANCE_COW_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	// CANINES:
	DOG_MORPH("dog-morph",
			"dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "DOG_MORPH_ADVANCED"),

			Colour.RACE_DOG_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.DOG_MORPH,
			Util.newArrayListOfValues(Attack.MAIN),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_DOG_MORPH,
			Attribute.RESISTANCE_DOG_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	WOLF_MORPH("wolf-morph",
			"wolf-morphs",
			"wolf-boy",
			"wolf-girl",
			"wolf-boys",
			"wolf-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "WOLF_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "WOLF_MORPH_ADVANCED"),
			
			Colour.RACE_WOLF_MORPH,
			Disposition.SAVAGE,
			StatusEffect.WOLF_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_WOLF_MORPH,
			Attribute.RESISTANCE_WOLF_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),
	
	FOX_MORPH("fox-morph",
			"fox-morphs",
			"fox-boy",
			"fox-girl",
			"fox-boys",
			"fox-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "FOX_MORPH_ADVANCED"),
			
			Colour.RACE_FOX_MORPH,
			Disposition.UNPREDICTABLE,
			StatusEffect.FOX_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SEDUCTION,
					Attack.SPELL),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_FOX_MORPH,
			Attribute.RESISTANCE_FOX_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	// FELINES:
	CAT_MORPH("cat-morph",
			"cat-morphs",
			"cat-boy",
			"cat-girl",
			"cat-boys",
			"cat-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "CAT_MORPH_ADVANCED"),

			Colour.RACE_CAT_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.CAT_MORPH,
			Util.newArrayListOfValues(
					Attack.SEDUCTION,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_CAT_MORPH,
			Attribute.RESISTANCE_CAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	// EQUINE:
	HORSE_MORPH("horse-morph",
			"horse-morphs",
			"horse-boy",
			"horse-girl",
			"horse-boys",
			"horse-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "HORSE_MORPH_ADVANCED"),

			Colour.RACE_HORSE_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.HORSE_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_HORSE_MORPH,
			Attribute.RESISTANCE_HORSE_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	
	 REINDEER_MORPH("reindeer-morph",
			"reindeer-morphs",
			"reindeer-boy",
			"reindeer-girl",
			"reindeer-boys",
			"reindeer-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "REINDEER_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "REINDEER_MORPH_ADVANCED"),
		 
	  Colour.RACE_REINDEER_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.REINDEER_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_REINDEER_MORPH,
			Attribute.RESISTANCE_REINDEER_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),
			

	SQUIRREL_MORPH("squirrel-morph",
			"squirrel-morphs",
			"squirrel-boy",
			"squirrel-girl",
			"squirrel-boys",
			"squirrel-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "SQUIRREL_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "SQUIRREL_MORPH_ADVANCED"),

			Colour.RACE_SQUIRREL_MORPH,
			Disposition.CIVILIZED,
			StatusEffect.SQUIRREL_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_SQUIRREL_MORPH,
			Attribute.RESISTANCE_SQUIRREL_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	RAT_MORPH("rat-morph",
			"rat-morphs",
			"rat-boy",
			"rat-girl",
			"rat-boys",
			"rat-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "RAT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "RAT_MORPH_ADVANCED"),

			Colour.RACE_RAT_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.RAT_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			4,
			Attribute.DAMAGE_RAT_MORPH,
			Attribute.RESISTANCE_RAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	RABBIT_MORPH("rabbit-morph",
			"rabbit-morphs",
			"rabbit-boy",
			"rabbit-girl",
			"rabbit-boys",
			"rabbit-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "RABBIT_MORPH_ADVANCED"),

			Colour.RACE_RABBIT_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.RABBIT_MORPH,
			Util.newArrayListOfValues(
					Attack.SEDUCTION,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			2,
			8,
			Attribute.DAMAGE_RABBIT_MORPH,
			Attribute.RESISTANCE_RABBIT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),
	
	BAT_MORPH("bat-morph",
			"bat-morphs",
			"bat-boy",
			"bat-girl",
			"bat-boys",
			"bat-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "BAT_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "BAT_MORPH_ADVANCED"),

			Colour.RACE_BAT_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.BAT_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			2,
			Attribute.DAMAGE_BAT_MORPH,
			Attribute.RESISTANCE_BAT_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),
	
	ALLIGATOR_MORPH("alligator-morph",
			"alligator-morphs",
			"alligator-boy",
			"alligator-girl",
			"alligator-boys",
			"alligator-girls",

			UtilText.parseFromXMLFile("characters/raceInfo", "ALLIGATOR_MORPH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ALLIGATOR_MORPH_ADVANCED"),

			Colour.RACE_ALLIGATOR_MORPH,
			Disposition.NEUTRAL,
			StatusEffect.ALLIGATOR_MORPH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPECIAL_ATTACK),
			true,
			0.5f,
			1,
			4,
			Attribute.DAMAGE_ALLIGATOR_MORPH,
			Attribute.RESISTANCE_ALLIGATOR_MORPH,
			FurryPreference.NORMAL,
			FurryPreference.NORMAL,
			true),

	// ELEMENTALS:
	ELEMENTAL_EARTH("earth elemental",
			"earth elementals",
			"earth elemental",
			"earth elemental",
			"earth elementals",
			"earth elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_EARTH_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_EARTH_ADVANCED"),

			Colour.SPELL_SCHOOL_EARTH,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_EARTH,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPELL),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_EARTH,
			Attribute.RESISTANCE_ELEMENTAL_EARTH,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),
	
	ELEMENTAL_WATER("water elemental",
			"water elementals",
			"water elemental",
			"water elemental",
			"water elementals",
			"water elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_WATER_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_WATER_ADVANCED"),

			Colour.SPELL_SCHOOL_WATER,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_WATER,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPELL),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_WATER,
			Attribute.RESISTANCE_ELEMENTAL_WATER,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),
	
	ELEMENTAL_AIR("air elemental",
			"air elementals",
			"air elemental",
			"air elemental",
			"air elementals",
			"air elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_AIR_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_AIR_ADVANCED"),

			Colour.SPELL_SCHOOL_AIR,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_AIR,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SPELL),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_AIR,
			Attribute.RESISTANCE_ELEMENTAL_AIR,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),
	
	ELEMENTAL_FIRE("fire elemental",
			"fire elementals",
			"fire elemental",
			"fire elemental",
			"fire elementals",
			"fire elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_FIRE_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_FIRE_ADVANCED"),

			Colour.SPELL_SCHOOL_FIRE,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_FIRE,
			Util.newArrayListOfValues(
					Attack.MAIN,
					Attack.SEDUCTION,
					Attack.SPELL),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_FIRE,
			Attribute.RESISTANCE_ELEMENTAL_FIRE,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),
	
	ELEMENTAL_ARCANE("arcane elemental",
			"arcane elementals",
			"arcane elemental",
			"arcane elemental",
			"arcane elementals",
			"arcane elementals",

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_ARCANE_BASIC"),

			UtilText.parseFromXMLFile("characters/raceInfo", "ELEMENTAL_ARCANE_ADVANCED"),

			Colour.SPELL_SCHOOL_ARCANE,
			Disposition.NEUTRAL,
			StatusEffect.ELEMENTAL_ARCANE,
			Util.newArrayListOfValues(
					Attack.SEDUCTION,
					Attack.SPELL),
			false,
			0.5f,
			1,
			1,
			Attribute.DAMAGE_ELEMENTAL_ARCANE,
			Attribute.RESISTANCE_ELEMENTAL_ARCANE,
			FurryPreference.MAXIMUM,
			FurryPreference.MAXIMUM,
			false),
	
	;

	private String name, basicDescription, advancedDescription;
	private Colour colour;
	private Disposition disposition;
	private StatusEffect statusEffect;
	private List<Attack> preferredAttacks;
	private boolean vulnerableToLilithsLustStorm;
	private int numberOfOffspringLow, numberOfOffspringHigh;
	private float chanceForMaleOffspring;
	private Attribute damageMultiplier, resistanceMultiplier;
	private FurryPreference defaultFemininePreference, defaultMasculinePreference;
	private boolean affectedByFurryPreference;
	
	private Race(String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,

			String basicDescription,
			String advancedDescription,

			Colour colour,
			Disposition disposition,
			StatusEffect statusEffect,
			List<Attack> preferredAttacks,
			boolean vulnerableToLilithsLustStorm,
			
			float chanceForMaleOffspring,
			int numberOfOffspringLow,
			int numberOfOffspringHigh,
			
			Attribute damageMultiplier,
			Attribute resistanceMultiplier,
			
			FurryPreference defaultFemininePreference,
			FurryPreference defaultMasculinePreference,
			
			boolean affectedByFurryPreference) {
		
		this.name = name;

		this.basicDescription = basicDescription;
		this.advancedDescription = advancedDescription;

		this.colour = colour;
		this.disposition = disposition;
		this.statusEffect = statusEffect;

		this.preferredAttacks = preferredAttacks;

		this.vulnerableToLilithsLustStorm = vulnerableToLilithsLustStorm;

		this.chanceForMaleOffspring=chanceForMaleOffspring;
		
		this.numberOfOffspringLow = numberOfOffspringLow;
		this.numberOfOffspringHigh = numberOfOffspringHigh;
		
		this.damageMultiplier = damageMultiplier;
		this.resistanceMultiplier = resistanceMultiplier;
		
		this.defaultFemininePreference = defaultFemininePreference;
		this.defaultMasculinePreference = defaultMasculinePreference;
		
		this.affectedByFurryPreference = affectedByFurryPreference;
	}

	public String getName() {
		return name;
	}

	public String getBasicDescription() {
		return basicDescription;
	}

	public String getAdvancedDescription() {
		return advancedDescription;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public StatusEffect getStatusEffect() {
		return statusEffect;
	}

	public List<Attack> getPreferredAttacks() {
		return preferredAttacks;
	}

	public boolean isVulnerableToArcaneStorm() {
		return vulnerableToLilithsLustStorm;
	}

	public int getNumberOfOffspringLow() {
		return numberOfOffspringLow;
	}

	public int getNumberOfOffspringHigh() {
		return numberOfOffspringHigh;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public boolean isAffectedByFurryPreference() {
		return affectedByFurryPreference;
	}
	
	public float getChanceForMaleOffspring() {
		return chanceForMaleOffspring;
	}

	public Attribute getDamageMultiplier() {
		return damageMultiplier;
	}

	public Attribute getResistanceMultiplier() {
		return resistanceMultiplier;
	}

	public FurryPreference getDefaultFemininePreference() {
		return defaultFemininePreference;
	}

	public FurryPreference getDefaultMasculinePreference() {
		return defaultMasculinePreference;
	}

}
