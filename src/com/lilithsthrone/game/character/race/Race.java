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
