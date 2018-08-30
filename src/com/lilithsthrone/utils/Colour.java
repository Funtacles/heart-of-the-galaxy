package com.lilithsthrone.utils;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * @since 0.1.0
 * @version 0.2.6
 * @author Innoxia
 */
public enum Colour {
	
	// This class and BaseColour are beyond saving x_x
	
	BASE_WHITE(false, BaseColour.WHITE, "white", Util.newArrayListOfValues("white")),
	BASE_GREY(false, BaseColour.GREY, "grey", Util.newArrayListOfValues("grey")),
	BASE_GREY_DARK(false, BaseColour.GREY_DARK, "dark grey", Util.newArrayListOfValues("darkGrey")),
	
	BASE_ROSE(false, BaseColour.ROSE, "rose", Util.newArrayListOfValues("rose")),
	BASE_LILAC(false, BaseColour.LILAC, "lilac", Util.newArrayListOfValues("lilac")),
	BASE_LILAC_LIGHT(false, BaseColour.LILAC_LIGHT, "light lilac", Util.newArrayListOfValues("lightLilac")),
	BASE_PURPLE_DARK(false, BaseColour.PURPLE_DARK, "dark purple", Util.newArrayListOfValues("darkPurple")),
	BASE_PURPLE(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("purple")),
	BASE_PURPLE_LIGHT(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("lightPurple")),
	
	BASE_PINK_DEEP(false, BaseColour.PINK_DEEP, "deep pink", Util.newArrayListOfValues("deepPink", "darkPink")),
	BASE_VIOLET(false, BaseColour.VIOLET, "violet", Util.newArrayListOfValues("violet")),
	BASE_PINK(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("pink")),
	BASE_PINK_LIGHT(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("lightPink")),
		
	BASE_MAGENTA(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("magenta")),
	BASE_CRIMSON(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("crimson")),
	BASE_RED(false, BaseColour.RED, "red", Util.newArrayListOfValues("red")),
	BASE_RED_LIGHT(false, BaseColour.RED_LIGHT, "light red", Util.newArrayListOfValues("lightRed")),
	
	BASE_TAN(false, BaseColour.TAN, "tan", Util.newArrayListOfValues("tan")),
	BASE_BROWN(false, BaseColour.BROWN, "brown", Util.newArrayListOfValues("brown")),
	BASE_BROWN_DARK(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("darkBrown")),
	BASE_ORANGE(false, BaseColour.ORANGE, "orange", Util.newArrayListOfValues("orange")),
	BASE_GINGER(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("ginger")),
	
	BASE_GOLD(false, BaseColour.GOLD, "gold", Util.newArrayListOfValues("gold")),
	BASE_YELLOW(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("yellow")),
	BASE_YELLOW_LIGHT(false, BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("lightYellow")),
	
	BASE_GREEN_LIME(false, BaseColour.GREEN_LIME, "lime green", Util.newArrayListOfValues("limeGreen")),
	BASE_GREEN_LIGHT(false, BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("lightGreen")),
	BASE_GREEN(false, BaseColour.GREEN, "green", Util.newArrayListOfValues("green")),
	BASE_GREEN_DARK(false, BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues("darkGreen")),
	
	BASE_AQUA(false, BaseColour.AQUA, "aqua", Util.newArrayListOfValues("aqua")),
	BASE_TEAL(false, BaseColour.TEAL, "teal", Util.newArrayListOfValues("teal")),
	BASE_PERIWINKLE(false, BaseColour.PERIWINKLE, "periwinkle", Util.newArrayListOfValues("periwinkle")),
	BASE_BLUE_LIGHT(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue")),
	BASE_BLUE(false, BaseColour.BLUE, "blue", Util.newArrayListOfValues("blue")),
	BASE_BLUE_STEEL(false, BaseColour.BLUE_STEEL, "steely blue", Util.newArrayListOfValues("steelyBlue")),
	
	BASE_BLACK(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("black")),
	BASE_PITCH_BLACK(false, BaseColour.PITCH_BLACK, "black", Util.newArrayListOfValues("black")),
	
	
	
	// Game colours:
	BACKGROUND(false, Util.newColour(0x222222), "grey"),
	BACKGROUND_ALT(false, Util.newColour(0x292929), "grey"),
	
	MAP_BACKGROUND_UNEXPLORED(false, Util.newColour(0x111), "black"),
	MAP_BACKGROUND_PINK(false, Util.newColour(0xb2a4bb),  "pink"),
	MAP_BACKGROUND(false, Util.newColour(0xbbbbbb),  "grey"),
	MAP_BACKGROUND_DARK(false, Util.newColour(0x888888), "dark grey"),
	MAP_BACKGROUND_BLUE(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("lightBlue")),
	
	GENERIC_SEX(false, BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues("sex")),
	GENERIC_COMBAT(false, BaseColour.CRIMSON, "crimson"),
	GENERIC_ARCANE(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("arcane")),
	GENERIC_TERRIBLE(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("terrible")),
	GENERIC_MINOR_BAD(false, BaseColour.RED_LIGHT, "red", Util.newArrayListOfValues("minorBad", "badMinor")),
	GENERIC_MINOR_GOOD(false, BaseColour.GREEN_LIGHT, "light green", Util.newArrayListOfValues("minorGood", "goodMinor")),
	GENERIC_BAD(false, BaseColour.RED, "red", Util.newArrayListOfValues("bad")),
	GENERIC_GOOD(false, BaseColour.GREEN, "green", Util.newArrayListOfValues("good")),
	GENERIC_EXCELLENT(false, BaseColour.GOLD, "gold", Util.newArrayListOfValues("excellent")),
	GENERIC_ATTRIBUTE(false, BaseColour.MAGENTA, "magenta"),
	GENERIC_EXPERIENCE(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("experience", "xp")),
	COOLDOWN(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("cooldown")),

	SCAR(false, BaseColour.TAN, "tan"),
	TATTOO(false, BaseColour.GREY, "grey"),
	
	PERK(false, BaseColour.AQUA, "aqua"),
	TRAIT(false, BaseColour.GREEN_LIGHT, "green"),
	FETISH(false, BaseColour.PINK_LIGHT, "light pink", Util.newArrayListOfValues("fetish")),
	STATUS_EFFECT(false, BaseColour.YELLOW, "yellow"),
	SPECIAL_ATTACK(false, BaseColour.CRIMSON, "crimson"),
	STATUS_EFFECT_TIME_OVERFLOW(false, BaseColour.BLUE, "aqua"),
	STATUS_EFFECT_TIME_HIGH(false, BaseColour.GREEN_LIGHT, "green"),
	STATUS_EFFECT_TIME_MEDIUM(false, BaseColour.ORANGE, "orange"),
	STATUS_EFFECT_TIME_LOW(false, BaseColour.RED, "red"),

	RACE_UNKNOWN(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("unknown")),
	RACE_HUMAN(false, BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("human")),
	RACE_DEMON(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("demon")),
	RACE_IMP(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("imp")),
	RACE_DOG_MORPH(false, BaseColour.BROWN, "brown", Util.newArrayListOfValues("dogMorph", "dog")),
	RACE_CAT_MORPH(false, BaseColour.VIOLET, "violet", Util.newArrayListOfValues("catMorph", "cat")),
	RACE_CAT_MORPH_CARACAL(false, BaseColour.VIOLET, "violet", Util.newArrayListOfValues("ocelotMorph", "ocelot")),
	RACE_CAT_MORPH_LION(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("lionMorph", "lion")),
	RACE_CAT_MORPH_LEOPARD(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("leopardMorph", "leopard")),
	RACE_CAT_MORPH_TIGER(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("tigerMorph", "tiger")),
	RACE_CAT_MORPH_CHEETAH(false, BaseColour.YELLOW, "yellow", Util.newArrayListOfValues("cheetahMorph", "cheetah")),
	RACE_CAT_MORPH_LYNX(false, BaseColour.SILVER, "silver", Util.newArrayListOfValues("lynxMorph", "lynx")),
	RACE_CAT_MORPH_LEOPARD_SNOW(false, BaseColour.SILVER, "silver", Util.newArrayListOfValues("leopardSnowMorph", "snowLeopard", "snep", "leopardSnow", "snowLeopardMorph")),
	RACE_COW_MORPH(false, BaseColour.TAN, "tan", Util.newArrayListOfValues("cowMorph", "cow")),
	RACE_HORSE_MORPH(false, BaseColour.ORANGE, "orange", Util.newArrayListOfValues("horseMorph", "horse")),
	RACE_WOLF_MORPH(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("wolfMorph", "wolf")),
	RACE_FOX_MORPH(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("foxMorph", "fox")),
	RACE_SQUIRREL_MORPH(false, BaseColour.GINGER, "ginger", Util.newArrayListOfValues("squirrelMorph", "squirrel")),
	RACE_RABBIT_MORPH(false, BaseColour.BROWN_DARK, "dark brown", Util.newArrayListOfValues("rabbitMorph", "rabbit")),
	RACE_BAT_MORPH(false, BaseColour.BLACK, "black", Util.newArrayListOfValues("batMorph", "bat")),
	RACE_ALLIGATOR_MORPH(false, BaseColour.GREEN_DARK, "dark green", Util.newArrayListOfValues("alligatorMorph", "alligator", "gatorMorph", "gator")),
	
	QUEST_MAIN(false, BaseColour.PINK, "pink"),
	QUEST_SIDE(false, BaseColour.BLUE, "blue"),
	QUEST_RELATIONSHIP(false, BaseColour.PINK_LIGHT, "pink"),

	MAP_MARKER(false, Util.newColour(0x6163DB), "blue"),

	ATTRIBUTE_HEALTH(false, BaseColour.CRIMSON, "crimson", Util.newArrayListOfValues("health", "hp", "energy")),
	ATTRIBUTE_MANA(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("willpower", "wp", "mana", "aura")),
//	ATTRIBUTE_STAMINA(BaseColour.LILAC, "lilac", Util.newArrayListOfValues("stamina", "sp", "energy")),

	ATTRIBUTE_PHYSIQUE(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("physique", "phys", "strength", "str")),
	ATTRIBUTE_ARCANE(false, BaseColour.PURPLE, "purple", Util.newArrayListOfValues("intelligence", "int")),
//	ATTRIBUTE_FITNESS(BaseColour.LILAC, "light purple", Util.newArrayListOfValues("fitness", "fit")),

	ATTRIBUTE_AROUSAL(false, BaseColour.PINK_DEEP, "pink", Util.newArrayListOfValues("arousal", "ars")),
	ATTRIBUTE_LUST(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("lust", "lst", "seduction")),

	//TODO
	PHYSIQUE_STAGE_ZERO(false, BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_ONE(false, BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_TWO(false, BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_THREE(false, BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_FOUR(false, BaseColour.MAGENTA, "magenta"),
	PHYSIQUE_STAGE_FIVE(false, BaseColour.GOLD, "gold"),
	
	//TODO
	INTELLIGENCE_STAGE_ZERO(false, BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_ONE(false, BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_TWO(false, BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_THREE(false, BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_FOUR(false, BaseColour.PURPLE, "purple"),
	INTELLIGENCE_STAGE_FIVE(false, BaseColour.GOLD, "gold"),
	
	//TODO
	FITNESS_STAGE_ZERO(false, BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_ONE(false, BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_TWO(false, BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_THREE(false, BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_FOUR(false, BaseColour.LILAC, "light purple"),
	FITNESS_STAGE_FIVE(false, BaseColour.GOLD, "gold"),
	
	AROUSAL_STAGE_ZERO(false, Util.newColour(0xfee6ff), "pink"),
	AROUSAL_STAGE_ONE(false, Util.newColour(0xfcb3ff), "pink"),
	AROUSAL_STAGE_TWO(false, Util.newColour(0xfb80ff), "pink"),
	AROUSAL_STAGE_THREE(false, Util.newColour(0xf94dff), "pink"),
	AROUSAL_STAGE_FOUR(false, Util.newColour(0xf824ff), "pink"),
	AROUSAL_STAGE_FIVE(false, Util.newColour(0xf700ff), "pink"),
	
	LUST_STAGE_ZERO(false, Util.newColour(0x80CAFF), "blue"),
	LUST_STAGE_ONE(false, Util.newColour(0xB699FF), "purple"),
	LUST_STAGE_TWO(false, Util.newColour(0xFF99D1), "pink"),
	LUST_STAGE_THREE(false, Util.newColour(0xFF61AB), "pink"),
	LUST_STAGE_FOUR(false, Util.newColour(0xFF3377), "dark pink"),
	LUST_STAGE_FIVE(false, Util.newColour(0xFF1A66), "dark pink"),

	DESIRE_STAGE_ZERO(false, Util.newColour(0xB699FF), "purple"),
	DESIRE_STAGE_ONE(false, Util.newColour(0xFF99D1), "pink"),
	DESIRE_STAGE_TWO(false, Util.newColour(0xFF61AB), "pink"),
	DESIRE_STAGE_THREE(false, Util.newColour(0xFF3377), "dark pink"),
	DESIRE_STAGE_FOUR(false, Util.newColour(0xffdf80), "gold"),


	COMPANION(false, BaseColour.GREEN_LIGHT,  "light green", Util.newArrayListOfValues("companion", "companions")),
	
	AFFECTION(false, BaseColour.PINK_LIGHT,  "light pink", Util.newArrayListOfValues("affection")),
	
	AFFECTION_NEGATIVE_FIVE(false, Util.newColour(0xff0066), "magenta"),
	AFFECTION_NEGATIVE_FOUR(false, Util.newColour(0xff2a7f), "magenta"),
	AFFECTION_NEGATIVE_THREE(false, Util.newColour(0xff5599), "pink"),
	AFFECTION_NEGATIVE_TWO(false, Util.newColour(0xff80b2), "pink"),
	AFFECTION_NEGATIVE_ONE(false, Util.newColour(0xffaacc), "pink"),
	AFFECTION_NEUTRAL(false, Util.newColour(0xe3dedb), "grey"),
	AFFECTION_POSITIVE_ONE(false, Util.newColour(0xffeeaa), "yellow"),
	AFFECTION_POSITIVE_TWO(false, Util.newColour(0xffe680), "yellow"),
	AFFECTION_POSITIVE_THREE(false, Util.newColour(0xffdd55), "yellow"),
	AFFECTION_POSITIVE_FOUR(false, Util.newColour(0xffd42a), "gold"),
	AFFECTION_POSITIVE_FIVE(false, Util.newColour(0xffcc00), "gold"),

	MASCULINE_PLUS(false, Util.newColour(0x4D9DFF), "dark blue", Util.newArrayListOfValues("masculineStrong", "masStr", "masculinePlus")),
	MASCULINE(false, Util.newColour(0x8ABEFF), "blue", Util.newArrayListOfValues("masculine", "mas")),
	ANDROGYNOUS(false, Util.newColour(0xB39EFF), "purple", Util.newArrayListOfValues("androgynous", "andro")),
	FEMININE(false, Util.newColour(0xFFBDFF), "pink", Util.newArrayListOfValues("feminine", "fem")),
	FEMININE_PLUS(false, Util.newColour(0xFF85FF), "pink", Util.newArrayListOfValues("feminineStrong", "femStr", "femininePlus")),
	
	BODY_SIZE_ZERO(false, Util.newColour(0xFFEBD6), "tan", Util.newArrayListOfValues("bodySizeZero")),
	BODY_SIZE_ONE(false, Util.newColour(0xFFE0BD), "tan", Util.newArrayListOfValues("bodySizeOne")),
	BODY_SIZE_TWO(false, Util.newColour(0xFFC88A), "tan", Util.newArrayListOfValues("bodySizeTwo")),
	BODY_SIZE_THREE(false, Util.newColour(0xFFAB57), "tan", Util.newArrayListOfValues("bodySizeThree")),
	BODY_SIZE_FOUR(false, Util.newColour(0xFF9124), "tan", Util.newArrayListOfValues("bodySizeFour")),

	MUSCLE_ZERO(false, Util.newColour(0xDBFFF6), "teal", Util.newArrayListOfValues("muscleZero")),
	MUSCLE_ONE(false, Util.newColour(0xBDFFED), "teal", Util.newArrayListOfValues("muscleOne")),
	MUSCLE_TWO(false, Util.newColour(0x8AFFE0), "teal", Util.newArrayListOfValues("muscleTwo")),
	MUSCLE_THREE(false, Util.newColour(0x57FFD2), "teal", Util.newArrayListOfValues("muscleThree")),
	MUSCLE_FOUR(false, Util.newColour(0x24FFC5), "teal", Util.newArrayListOfValues("muscleFour")),

	AGE_TEENS(false, Util.newColour(0xE1F0C1), "green", Util.newArrayListOfValues("ageTeens")),
	AGE_TWENTIES(false, Util.newColour(0xCCE698), "green", Util.newArrayListOfValues("ageTwenties")),
	AGE_THIRTIES(false, Util.newColour(0xB8DC6F), "green", Util.newArrayListOfValues("ageThirties")),
	AGE_FORTIES(false, Util.newColour(0xA4D246), "green", Util.newArrayListOfValues("ageForties")),
	
	ALCOHOL(false, BaseColour.YELLOW_LIGHT, "light yellow", Util.newArrayListOfValues("alcohol")),

	ALCOHOL_LEVEL_ZERO(false, Util.newColour(0xF2E8C0), "light yellow"),
	ALCOHOL_LEVEL_ONE(false,  Util.newColour(0xEDDFAB), "light yellow"),
	ALCOHOL_LEVEL_TWO(false,  Util.newColour(0xE8D696), "yellow"),
	ALCOHOL_LEVEL_THREE(false,  Util.newColour(0xE3CE82), "yellow"),
	ALCOHOL_LEVEL_FOUR(false,  Util.newColour(0xDEC66E), "yellow"),
	ALCOHOL_LEVEL_FIVE(false, Util.newColour(0xD9BD59), "gold"),
	
	PSYCHOACTIVE(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("psychoactive")),

	TRANSFORMATION_SHRINK(false, BaseColour.RED, "red", Util.newArrayListOfValues("tfShrink", "shrink", "tfShrunk", "shrunk", "tfShrinking", "shrinking")),
	TRANSFORMATION_GROW(false, BaseColour.GREEN, "green", Util.newArrayListOfValues("tfGrow", "grow", "tfGrown", "grown", "tfGrowth", "growth")),

	GENERIC_SIZE_ONE(false, Util.newColour(0xAFE9B3), "green"),
	GENERIC_SIZE_TWO(false, Util.newColour(0xA0E4A3), "green"),
	GENERIC_SIZE_THREE(false, Util.newColour(0x8FE096), "green"),
	GENERIC_SIZE_FOUR(false, Util.newColour(0x77DA7F), "green"),
	GENERIC_SIZE_FIVE(false, Util.newColour(0x67D570), "green"),
	GENERIC_SIZE_SIX(false, Util.newColour(0x57D161), "green"),
	GENERIC_SIZE_SEVEN(false, Util.newColour(0x47CD52), "green"),
	GENERIC_SIZE_EIGHT(false, Util.newColour(0x37C843), "green"),
	
	WETNESS(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("wetness", "wet", "tfWetness", "tfWet")),
	PLASTICITY(false, BaseColour.LILAC, "lilac", Util.newArrayListOfValues("plasticity", "tfPlasticity")),
	ELASTICITY(false, BaseColour.PURPLE_LIGHT, "light purple", Util.newArrayListOfValues("elasticity", "tfElasticity")),
	TRANSFORMATION_GENERIC(false, BaseColour.GREEN_LIME, "lime", Util.newArrayListOfValues("tfGeneric", "tfBase", "genericTF")),
	TRANSFORMATION_SEXUAL(false, BaseColour.PINK_LIGHT, "pink", Util.newArrayListOfValues("tfSex", "tfSexual", "sexualTF")),
	TRANSFORMATION_HUMAN(false, BaseColour.BLUE_STEEL, "pale blue", Util.newArrayListOfValues("tfHuman")),
	TRANSFORMATION_PARTIAL(false, Util.newColour(0xff80bf), "purple", Util.newArrayListOfValues("tfPartial")),
	TRANSFORMATION_PARTIAL_FULL(false, Util.newColour(0xff1a8c), "purple", Util.newArrayListOfValues("tfMinor")),
	TRANSFORMATION_LESSER(false, Util.newColour(0xe600ac), "purple", Util.newArrayListOfValues("tfLesser")),
	TRANSFORMATION_GREATER(false, Util.newColour(0xd411d4), "purple-pink", Util.newArrayListOfValues("tfGreater")),

	// Speech colours:
	MASCULINE_PLUS_NPC(false, BaseColour.BLUE, "blue"),
	MASCULINE_NPC(false, BaseColour.BLUE_LIGHT, "blue"),
	ANDROGYNOUS_NPC(false, BaseColour.LILAC_LIGHT, "purple"),
	FEMININE_NPC(false, BaseColour.ROSE, "pink"),
	FEMININE_PLUS_NPC(false, BaseColour.PINK, "pink"),

	// Combat colours:
	DAMAGE_TYPE_PHYSICAL(false, Util.newColour(0xFF428E), "red", Util.newArrayListOfValues("dmgPhysical", "resPhysical", "physical")),
	DAMAGE_TYPE_MANA(false, BaseColour.PURPLE_LIGHT, "purple", Util.newArrayListOfValues("dmgMana", "resMana")),
	DAMAGE_TYPE_LUST(false, BaseColour.MAGENTA, "magenta", Util.newArrayListOfValues("dmgLust", "resLust")),
	DAMAGE_TYPE_SPELL(false, Util.newColour(0xFF6BDA), "pink", Util.newArrayListOfValues("dmgSpell", "resSpell", "spell")),
	DAMAGE_TYPE_FIRE(false, Util.newColour(0xff9955), "orange", Util.newArrayListOfValues("dmgFire", "resFire", "fire")),
	DAMAGE_TYPE_COLD(false, Util.newColour(0x85C6FF), "blue", Util.newArrayListOfValues("dmgCold", "resCold", "cold", "ice")),
	DAMAGE_TYPE_POISON(false, Util.newColour(0x85FF8B), "green", Util.newArrayListOfValues("dmgPoison", "resPoison", "poison")),
	DAMAGE_TYPE_PURE(false, Util.newColour(0xFFCC00), "gold", Util.newArrayListOfValues("dmgPure", "resPure", "pure")),

	SPELL_SCHOOL_FIRE(false, BaseColour.ORANGE, "orange", Util.newArrayListOfValues("spellFire", "schoolFire")),
	SPELL_SCHOOL_WATER(false, BaseColour.AQUA, "aqua", Util.newArrayListOfValues("water", "spellWater", "schoolWater")),
	SPELL_SCHOOL_EARTH(false, BaseColour.BROWN, "brown", Util.newArrayListOfValues("earth", "spellEarth", "schoolEarth")),
	SPELL_SCHOOL_AIR(false, BaseColour.BLUE_LIGHT, "light blue", Util.newArrayListOfValues("air", "spellAir", "schoolAir")),
	SPELL_SCHOOL_ARCANE(false, BaseColour.PINK, "pink", Util.newArrayListOfValues("spellArcane", "schoolArcane")),
	
	// Rarity colours:
	RARITY_UNKNOWN(false, BaseColour.BLACK, "grey"),
	RARITY_JINXED(false, BaseColour.RED, "red", Util.newArrayListOfValues("jinx", "jinxed")),
	RARITY_COMMON(false, Util.newColour(0xf2f2f2), "white", Util.newArrayListOfValues("common")),
	RARITY_UNCOMMON(false, Util.newColour(0x1de547), "green", Util.newArrayListOfValues("uncommon")),
	RARITY_RARE(false, Util.newColour(0x47C2FF), "blue", Util.newArrayListOfValues("rare")),
	RARITY_EPIC(false, Util.newColour(0xFF4DFC), "purple", Util.newArrayListOfValues("epic")),
	RARITY_LEGENDARY(false, Util.newColour(0xffcc00), "gold", Util.newArrayListOfValues("legendary")),

	// Inventory colours:
	CURRENCY_GOLD(true, BaseColour.GOLD, "gold"),
	CURRENCY_SILVER(true, BaseColour.SILVER, "gold"),
	CURRENCY_COPPER(true, BaseColour.COPPER, "gold"),

	MILK(false, BaseColour.YELLOW_LIGHT,  "light yellow", Util.newArrayListOfValues("milk", "lactation")),
	CUM(false, BaseColour.BLUE_LIGHT,  "light blue", Util.newArrayListOfValues("cum", "cummed", "dirty")),
	GIRLCUM(false, BaseColour.PINK_LIGHT,  "light pink", Util.newArrayListOfValues("girlcum", "gcum")),
	
	SEALED(false, BaseColour.PINK_DEEP, "pink"),
	DISPLACED(false, BaseColour.CRIMSON, "crimson"),

	// Text colours:
	TEXT(false, Util.newColour(0xDDDDDD), "grey", Util.newArrayListOfValues("text")),
	TEXT_HALF_GREY(false, Util.newColour(0xBBBBBB), "grey", Util.newArrayListOfValues("halfDisabled")),
	TEXT_GREY(false, Util.newColour(0x777777), "grey", Util.newArrayListOfValues("disabled")),
	TEXT_GREY_DARK(false, Util.newColour(0x444444), "grey", Util.newArrayListOfValues("disabledDark")),

	// Standard colours used for clothing:
	CLOTHING_WHITE(false, Util.newColour(0xdddddd), "white"),
	CLOTHING_GREY(false, Util.newColour(0x777777), "grey"),
	CLOTHING_BLACK(false, Util.newColour(0x333333), "black"),
	
	CLOTHING_RED_DARK(false, Util.newColour(0x900020),  "burgundy"),
	CLOTHING_RED_BRIGHT(false, Util.newColour(0xFA2424), "bright red"),
	CLOTHING_RED(false, Util.newColour(0xD84646), "red"),
	CLOTHING_BROWN(false, Util.newColour(0xC87137), "brown"),
	CLOTHING_BROWN_DARK(false, Util.newColour(0x63391C), "dark brown"),
	CLOTHING_ORANGE(false, Util.newColour(0xE79F6F), "orange"),
	CLOTHING_ORANGE_BRIGHT(false, Util.newColour(0xFF7900), "bright orange"),
	CLOTHING_ORANGE_DARK(false, Util.newColour(0xE56D00), "dark orange"),
	CLOTHING_TAN(false, BaseColour.TAN, "tan"),
	CLOTHING_YELLOW(false, Util.newColour(0xE2C360), "yellow"),
	CLOTHING_GREEN_LIME(false, Util.newColour(0xD0E37D), "lime green"),
	CLOTHING_GREEN(false, Util.newColour(0x74AA74), "green"),
	CLOTHING_GREEN_DARK(false, Util.newColour(0x3B6F3D), "dark green"),
	CLOTHING_TURQUOISE(false, Util.newColour(0x6EC4B3), "turquoise"),
	CLOTHING_BLUE_LIGHT(false, Util.newColour(0x72CFE3), "light blue"),
	CLOTHING_BLUE(false, Util.newColour(0x3971C6), "blue"),
	CLOTHING_BLUE_DARK(false, Util.newColour(0x003C89), "dark blue"),
	CLOTHING_PURPLE_DARK(false, Util.newColour(0x674A95), "dark purple"),
	CLOTHING_PURPLE(false, Util.newColour(0xA382D3), "purple"),
	CLOTHING_PURPLE_LIGHT(false, Util.newColour(0xC58ED7), "violet"),
	CLOTHING_PERIWINKLE(false, BaseColour.PERIWINKLE, "periwinkle"),
	CLOTHING_PINK_LIGHT(false, Util.newColour(0xF4B3F4), "light pink"),
	CLOTHING_PINK(false, Util.newColour(0xD75086), "pink"),
	
	CLOTHING_BLACK_STEEL(true, Util.newColour(0x333333), "black"),
	CLOTHING_STEEL(true, Util.newColour(0x969696), "steel"),
	CLOTHING_COPPER(true, Util.newColour(0xD46F2B), "copper", Util.newArrayListOfValues("copper")),
	CLOTHING_SILVER(true, Util.newColour(0xC4C4C4), "silver", Util.newArrayListOfValues("silver")),
	CLOTHING_GOLD(true, Util.newColour(0xEBC633), "gold"),
	CLOTHING_ROSE_GOLD(true, BaseColour.ROSE_GOLD, "rose gold"),
	CLOTHING_PLATINUM(true, BaseColour.PLATINUM, "platinum"),
	
	// For special use with rainbow clothing:
	CLOTHING_MULTICOLOURED(false, Util.newColour(0xff3030), "multicoloured"),

	// Body parts:

	// Skin (Human and Demon):
	SKIN_PORCELAIN(false, Util.newColour(0xDBCDB9), "porcelain"),
	SKIN_PALE(false, Util.newColour(0xFBF4E9), "pale"),
	SKIN_LIGHT(false, BaseColour.YELLOW_LIGHT, "light"),
	SKIN_ROSY(false, Util.newColour(0xDDAA93), "rosy"),
	SKIN_TANNED(false, Util.newColour(0xC39D6B), "tanned"),
	SKIN_OLIVE(false, BaseColour.TAN, "olive"),
	SKIN_CHOCOLATE(false, Util.newColour(0x59372D), "chocolate"),
	SKIN_DARK(false, BaseColour.BROWN_DARK, "dark"),
	SKIN_EBONY(false, BaseColour.BLACK, "ebony"),
	
	SKIN_RED(false, BaseColour.CRIMSON, "scarlet"),
	SKIN_RED_DARK(false, BaseColour.RED_DARK, "dark red"),
	SKIN_BROWN(false, BaseColour.BROWN, "brown"),
	SKIN_YELLOW(false, BaseColour.YELLOW, "yellow"),
	SKIN_AMBER(false, BaseColour.AMBER, "amber"),
	SKIN_PINK(false, BaseColour.PINK, "pink"),
	SKIN_PINK_LIGHT(false, BaseColour.PINK_LIGHT, "light pink"),
	SKIN_GREEN(false, BaseColour.GREEN, "green"),
	SKIN_GREEN_DARK(false, BaseColour.GREEN_DARK, "dark green"),
	SKIN_BLUE_LIGHT(false, BaseColour.BLUE_LIGHT, "light blue"),
	SKIN_BLUE(false, BaseColour.BLUE, "blue"),
	SKIN_BLUE_DARK(false, BaseColour.BLUE_DARK, "dark blue"),
	SKIN_PERIWINKLE(false, BaseColour.PERIWINKLE, "periwinkle"),
	SKIN_LILAC(false, BaseColour.LILAC, "lilac"),
	SKIN_PURPLE(false, BaseColour.PURPLE, "purple"),
	SKIN_PURPLE_DARK(false, BaseColour.PURPLE_DARK, "dark purple"),
	SKIN_IVORY(false, BaseColour.WHITE, "ivory"),
	SKIN_GREY(false, BaseColour.GREY, "grey"),

	// Horns:
	HORN_WHITE(false, BaseColour.WHITE, "ivory"),
	HORN_GREY(false, BaseColour.GREY, "grey"),
	HORN_DARK_GREY(false, BaseColour.GREY_DARK, "dark-grey"),
	HORN_BLACK(false, BaseColour.BLACK, "black"),
	
	HORN_RED(false, BaseColour.RED, "red"),
	HORN_SCARLET(false, BaseColour.CRIMSON, "scarlet"),
	HORN_BROWN(false, BaseColour.BROWN, "brown"),
	HORN_DARK_BROWN(false, BaseColour.BROWN_DARK, "dark brown"),
	HORN_AMBER(false, BaseColour.AMBER, "amber"),
	HORN_PINK(false, BaseColour.PINK_LIGHT, "light pink"),
	HORN_GREEN(false, BaseColour.GREEN, "green"),
	HORN_BLUE(false, BaseColour.BLUE_LIGHT, "light blue"),
	HORN_LILAC(false, BaseColour.LILAC, "lilac"),
	HORN_PURPLE(false, BaseColour.PURPLE, "purple"),

	
	// Antlers:
	ANTLER_WHITE(false, BaseColour.WHITE, "ivory"),
	ANTLER_TAN(false, BaseColour.TAN, "tan"),
	ANTLER_BROWN(false, BaseColour.BROWN, "brown"),
	ANTLER_DARK_BROWN(false, BaseColour.BROWN_DARK, "dark brown"),
	ANTLER_GREY(false, BaseColour.GREY, "grey"),
	ANTLER_DARK_GREY(false, BaseColour.GREY_DARK, "dark-grey"),
	ANTLER_BLACK(false, BaseColour.BLACK, "black"),
	
	ANTLER_RED(false, BaseColour.RED, "red"),
	ANTLER_SCARLET(false, BaseColour.CRIMSON, "scarlet"),
	ANTLER_AMBER(false, BaseColour.AMBER, "amber"),
	ANTLER_PINK(false, BaseColour.PINK_LIGHT, "light pink"),
	ANTLER_GREEN(false, BaseColour.GREEN, "green"),
	ANTLER_BLUE(false, BaseColour.BLUE_LIGHT, "light blue"),
	ANTLER_LILAC(false, BaseColour.LILAC, "lilac"),
	ANTLER_PURPLE(false, BaseColour.PURPLE, "purple"),

	// Orifices:
	ORIFICE_INTERIOR(false, BaseColour.ROSE, "fleshy-pink"),

	// Misc:
	TONGUE(false, BaseColour.ROSE, "pink"),

	// Generic colours:
	COVERING_SILVER(true, BaseColour.GREY, "metallic silver"),
	COVERING_GOLD(true, BaseColour.GOLD, "metallic gold"),
	COVERING_PLATINUM(true, BaseColour.PLATINUM, "metallic platinum"),
	COVERING_ROSE_GOLD(true, BaseColour.ROSE_GOLD, "metallic rose gold"),
	COVERING_COPPER(true, BaseColour.COPPER, "metallic copper"),
	COVERING_STEEL(true, Util.newColour(0x969696), "metallic steel"),
	COVERING_TAN(false, BaseColour.TAN, "tan"),
	COVERING_BROWN(false, BaseColour.BROWN, "brown"),
	COVERING_BROWN_DARK(false, BaseColour.BROWN_DARK, "dark brown"),
	COVERING_BLACK(false, BaseColour.BLACK, "black"),
	COVERING_GREY(false, BaseColour.GREY, "grey"),
	COVERING_DIRTY_BLONDE(false, BaseColour.TAN, "dirty-blonde"),
	COVERING_BLONDE(false, BaseColour.YELLOW, "blonde"),
	COVERING_BLEACH_BLONDE(false, BaseColour.YELLOW_LIGHT, "bleach-blonde"),
	COVERING_YELLOW(false, BaseColour.YELLOW, "yellow"),
	COVERING_GINGER(false, BaseColour.GINGER, "ginger"),
	COVERING_ORANGE(false, BaseColour.ORANGE, "orange"),
	COVERING_AMBER(false, BaseColour.AMBER, "amber"),
	COVERING_RED(false, BaseColour.RED, "red"),
	COVERING_RED_DARK(false, BaseColour.RED_DARK, "dark red"),
	COVERING_AUBURN(false, BaseColour.AUBURN, "auburn"),
	COVERING_WHITE(false, BaseColour.WHITE, "white"),
	COVERING_BLUE_LIGHT(false, BaseColour.BLUE_LIGHT, "light blue"),
	COVERING_BLUE(false, BaseColour.BLUE, "blue"),
	COVERING_BLUE_DARK(false, BaseColour.BLUE_DARK, "dark blue"),
	COVERING_PERIWINKLE(false, BaseColour.PERIWINKLE, "periwinkle"),
	COVERING_LILAC(false, BaseColour.LILAC, "lilac"),
	COVERING_PURPLE(false, BaseColour.PURPLE, "purple"),
	COVERING_PURPLE_DARK(false, BaseColour.PURPLE_DARK, "dark purple"),
	COVERING_PINK_LIGHT(false, BaseColour.PINK_LIGHT, "light pink"),
	COVERING_PINK(false, BaseColour.PINK, "pink"),
	COVERING_PINK_DARK(false, BaseColour.PINK_DEEP, "dark pink"),
	COVERING_GREEN(false, BaseColour.GREEN, "green"),
	COVERING_GREEN_DARK(false, BaseColour.GREEN_DARK, "dark green"),
	
	// Specials:
	COVERING_CLEAR(false, BaseColour.WHITE, "clear"), // For nail-polish
	COVERING_RAINBOW(false, BaseColour.BLUE,
			"<span style='color:#E64C4C;'>r</span>"
			+ "<span style='color:#E6854C;'>a</span>"
			+ "<span style='color:#E6C74C;'>i</span>"
			+ "<span style='color:#6EE64C;'>n</span>"
			+ "<span style='color:#4CB2E6;'>b</span>"
			+ "<span style='color:#AD4CE6;'>o</span>"
			+ "<span style='color:#E64CA8;'>w</span>") {
		public boolean isRainbow() {
			return true;
		}
	},
	COVERING_NONE(false, BaseColour.GREY, "none"),

	// Eye colours:
	EYE_WHITE(false, BaseColour.WHITE, "white"),
	
	EYE_BROWN(false, BaseColour.BROWN, "brown"),
	EYE_BLUE(false, BaseColour.BLUE_LIGHT, "blue"),
	EYE_HAZEL(false, BaseColour.TAN, "hazel"),
	EYE_AQUA(false, BaseColour.AQUA, "aqua"),
	EYE_GREEN(false, BaseColour.GREEN, "green"),
	EYE_GREY(false, BaseColour.GREY, "grey"),

	EYE_PERIWINKLE(false, BaseColour.PERIWINKLE, "periwinkle"),
	EYE_LILAC(false, BaseColour.LILAC, "lilac"),
	EYE_PURPLE(false, BaseColour.PURPLE, "purple"),
	EYE_VIOLET(false, BaseColour.VIOLET, "violet"),
	EYE_CRIMSON(false, BaseColour.CRIMSON, "crimson"),
	EYE_GOLD(false, BaseColour.GOLD, "golden"),
	EYE_SILVER(false, BaseColour.SILVER, "silver"),
	
	EYE_YELLOW(false, BaseColour.YELLOW, "yellow"),
	EYE_AMBER(false, BaseColour.AMBER, "amber"),
	EYE_RED(false, BaseColour.RED, "red"),
	EYE_PINK(false, BaseColour.PINK, "pink"),
	EYE_ORANGE(false, BaseColour.ORANGE, "orange"),
	EYE_BLACK(false, BaseColour.BLACK, "black");
	
	

	// Skin/fur/body part groups:
	
	public static List<Colour> humanSkinColours = Util.newArrayListOfValues(
			Colour.SKIN_PORCELAIN,
			Colour.SKIN_PALE,
			Colour.SKIN_LIGHT,
			Colour.SKIN_ROSY,
			Colour.SKIN_TANNED,
			Colour.SKIN_OLIVE,
			Colour.SKIN_CHOCOLATE,
			Colour.SKIN_DARK,
			Colour.SKIN_EBONY);

	public static List<Colour> ratSkinColours = Util.newArrayListOfValues(
			Colour.SKIN_PINK_LIGHT);
	
	public static List<Colour> demonSkinColours = Util.newArrayListOfValues(
			Colour.SKIN_PORCELAIN,
			Colour.SKIN_PALE,
			Colour.SKIN_LIGHT,
			Colour.SKIN_ROSY,
			Colour.SKIN_TANNED,
			Colour.SKIN_OLIVE,
			Colour.SKIN_CHOCOLATE,
			Colour.SKIN_DARK,
			Colour.SKIN_EBONY,
			Colour.SKIN_IVORY,
			Colour.SKIN_GREY,
			Colour.SKIN_RED,
			Colour.SKIN_RED_DARK,
			Colour.SKIN_BROWN,
			Colour.SKIN_AMBER,
			Colour.SKIN_YELLOW,
			Colour.SKIN_GREEN,
			Colour.SKIN_GREEN_DARK,
			Colour.SKIN_BLUE_LIGHT,
			Colour.SKIN_BLUE,
			Colour.SKIN_BLUE_DARK,
			Colour.SKIN_PERIWINKLE,
			Colour.SKIN_LILAC,
			Colour.SKIN_PURPLE,
			Colour.SKIN_PURPLE_DARK,
			Colour.SKIN_PINK_LIGHT,
			Colour.SKIN_PINK);

	public static List<Colour> allSkinColours = Util.newArrayListOfValues(
			Colour.SKIN_PORCELAIN,
			Colour.SKIN_PALE,
			Colour.SKIN_LIGHT,
			Colour.SKIN_ROSY,
			Colour.SKIN_TANNED,
			Colour.SKIN_OLIVE,
			Colour.SKIN_CHOCOLATE,
			Colour.SKIN_DARK,
			Colour.SKIN_EBONY,
			Colour.SKIN_IVORY,
			Colour.SKIN_GREY,
			Colour.SKIN_RED,
			Colour.SKIN_RED_DARK,
			Colour.SKIN_BROWN,
			Colour.SKIN_AMBER,
			Colour.SKIN_YELLOW,
			Colour.SKIN_GREEN,
			Colour.SKIN_GREEN_DARK,
			Colour.SKIN_BLUE_LIGHT,
			Colour.SKIN_BLUE,
			Colour.SKIN_BLUE_DARK,
			Colour.SKIN_PERIWINKLE,
			Colour.SKIN_LILAC,
			Colour.SKIN_PURPLE,
			Colour.SKIN_PURPLE_DARK,
			Colour.SKIN_PINK_LIGHT,
			Colour.SKIN_PINK,
			Colour.COVERING_RAINBOW);


	public static List<Colour> naturalFeatherColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_GREY,
			Colour.COVERING_BLACK,
			Colour.COVERING_RED,
			Colour.COVERING_RED_DARK,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_BROWN,
			Colour.COVERING_TAN,
			Colour.COVERING_ORANGE,
			Colour.COVERING_GINGER,
			Colour.COVERING_BLEACH_BLONDE,
			Colour.COVERING_YELLOW,
			Colour.COVERING_AMBER,
			Colour.COVERING_GREEN,
			Colour.COVERING_GREEN_DARK,
			Colour.COVERING_BLUE_LIGHT,
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_DARK,
			Colour.COVERING_PERIWINKLE,
			Colour.COVERING_LILAC,
			Colour.COVERING_PURPLE,
			Colour.COVERING_PURPLE_DARK,
			Colour.COVERING_PINK,
			Colour.COVERING_PINK_LIGHT
			);
			
			
	public static List<Colour> dyeFeatherColours = Util.newArrayListOfValues(
			Colour.COVERING_PLATINUM,
			Colour.COVERING_GOLD,
			Colour.COVERING_SILVER,
			Colour.COVERING_COPPER,
			Colour.COVERING_STEEL,
			Colour.COVERING_ROSE_GOLD,
			Colour.COVERING_RAINBOW
			);
			
	public static List<Colour> naturalFurColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_SILVER,
			Colour.COVERING_BLONDE,
			Colour.COVERING_GINGER,
			Colour.COVERING_BROWN,
			Colour.COVERING_TAN,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_GREY,
			Colour.COVERING_BLACK);

	public static List<Colour> allCoveringColours = Util.newArrayListOfValues(
			Colour.COVERING_PLATINUM,
			Colour.COVERING_GOLD,
			Colour.COVERING_SILVER,
			Colour.COVERING_COPPER,
			Colour.COVERING_STEEL,
			Colour.COVERING_ROSE_GOLD,
			
			Colour.COVERING_WHITE,
			Colour.COVERING_GREY,
			Colour.COVERING_BLACK,
			Colour.COVERING_RED,
			Colour.COVERING_RED_DARK,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_BROWN,
			Colour.COVERING_TAN,
			Colour.COVERING_ORANGE,
			Colour.COVERING_GINGER,
			Colour.COVERING_BLEACH_BLONDE,
			Colour.COVERING_BLONDE,
			Colour.COVERING_YELLOW,
			Colour.COVERING_AMBER,
			Colour.COVERING_GREEN,
			Colour.COVERING_GREEN_DARK,
			Colour.COVERING_BLUE_LIGHT,
			Colour.COVERING_BLUE,
			Colour.COVERING_BLUE_DARK,
			Colour.COVERING_PERIWINKLE,
			Colour.COVERING_LILAC,
			Colour.COVERING_PURPLE,
			Colour.COVERING_PURPLE_DARK,
			Colour.COVERING_PINK,
			Colour.COVERING_PINK_LIGHT,
			Colour.COVERING_RAINBOW
			);

	public static List<Colour> naturalScaleColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_BROWN,
			Colour.COVERING_TAN,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_BLACK);

	public static List<Colour> hornColours = Util.newArrayListOfValues(
			Colour.HORN_WHITE,
			Colour.HORN_GREY,
			Colour.HORN_DARK_GREY,
			Colour.HORN_BLACK);
	
	public static List<Colour> dyeHornColours = Util.newArrayListOfValues(
			Colour.HORN_RED,
			Colour.HORN_SCARLET,
			Colour.HORN_BROWN,
			Colour.HORN_DARK_BROWN,
			Colour.HORN_AMBER,
			Colour.HORN_PINK,
			Colour.HORN_GREEN,
			Colour.HORN_BLUE,
			Colour.HORN_LILAC,
			Colour.HORN_PURPLE,
			Colour.COVERING_RAINBOW);

	
	// Antlers:
	public static List<Colour> antlerColours = Util.newArrayListOfValues(
			Colour.ANTLER_WHITE,
			Colour.ANTLER_TAN,
			Colour.ANTLER_BROWN,
			Colour.ANTLER_DARK_BROWN,
			Colour.ANTLER_GREY,
			Colour.ANTLER_DARK_GREY,
			Colour.ANTLER_BLACK);
	

	public static List<Colour> dyeAntlerColours = Util.newArrayListOfValues(
			Colour.ANTLER_RED,
			Colour.ANTLER_SCARLET,
			Colour.ANTLER_AMBER,
			Colour.ANTLER_PINK,
			Colour.ANTLER_GREEN,
			Colour.ANTLER_BLUE,
			Colour.ANTLER_LILAC,
			Colour.ANTLER_PURPLE,
			Colour.COVERING_RAINBOW);
	
	// Hair:
	
	public static List<Colour> naturalHairColours = Util.newArrayListOfValues(
			Colour.COVERING_WHITE,
			Colour.COVERING_BLONDE,
			Colour.COVERING_DIRTY_BLONDE,
			Colour.COVERING_GINGER,
			Colour.COVERING_BROWN,
			Colour.COVERING_BROWN_DARK,
			Colour.COVERING_AUBURN,
			Colour.COVERING_GREY,
			Colour.COVERING_BLACK);
	
	// Eyes:
	
	public static List<Colour> naturalIrisColours = Util.newArrayListOfValues(
			Colour.EYE_BROWN,
			Colour.EYE_AMBER,
			Colour.EYE_HAZEL,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY);
	
	public static List<Colour> dyeIrisColours = Util.newArrayListOfValues(
			Colour.EYE_SILVER,
			Colour.EYE_YELLOW,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_PERIWINKLE,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.EYE_BLACK,
			Colour.COVERING_RAINBOW);

	public static List<Colour> naturalDemonIrisColours = Util.newArrayListOfValues(
			Colour.EYE_BROWN,
			Colour.EYE_AMBER,
			Colour.EYE_HAZEL,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_YELLOW,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_PERIWINKLE,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.EYE_BLACK);
	
	public static List<Colour> dyeDemonIrisColours = Util.newArrayListOfValues(
			Colour.EYE_SILVER,
			Colour.EYE_GOLD,
			Colour.COVERING_RAINBOW);
	
	
	public static List<Colour> naturalPredatorIrisColours = Util.newArrayListOfValues(
			Colour.EYE_BROWN,
			Colour.EYE_AMBER,
			Colour.EYE_YELLOW,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY);
	
	public static List<Colour> dyePredatorIrisColours = Util.newArrayListOfValues(
			Colour.EYE_SILVER,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.EYE_BLACK,
			Colour.COVERING_RAINBOW);
	
	
	public static List<Colour> naturalPupilColours = Util.newArrayListOfValues(
			Colour.EYE_BLACK);
	
	public static List<Colour> dyePupilColours = Util.newArrayListOfValues(
			Colour.EYE_WHITE,
			Colour.EYE_SILVER,
			Colour.EYE_BROWN,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY,
			Colour.EYE_YELLOW,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_AMBER,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_PERIWINKLE,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.COVERING_RAINBOW);
	
	public static List<Colour> naturalScleraColours = Util.newArrayListOfValues(
			Colour.EYE_WHITE);
	
	public static List<Colour> dyeScleraColours = Util.newArrayListOfValues(
			Colour.EYE_BLACK,
			Colour.EYE_SILVER,
			Colour.EYE_BROWN,
			Colour.EYE_BLUE,
			Colour.EYE_AQUA,
			Colour.EYE_GREEN,
			Colour.EYE_GREY,
			Colour.EYE_YELLOW,
			Colour.EYE_GOLD,
			Colour.EYE_RED,
			Colour.EYE_CRIMSON,
			Colour.EYE_ORANGE,
			Colour.EYE_AMBER,
			Colour.EYE_PINK,
			Colour.EYE_VIOLET,
			Colour.EYE_PERIWINKLE,
			Colour.EYE_LILAC,
			Colour.EYE_PURPLE,
			Colour.COVERING_RAINBOW);
	
	
	
	private Color colour;
	private boolean metallic;
	private String name;
	private List<String> formattingNames;
	
	private Colour(boolean metallic, Color colour, String name) {
		this.metallic = metallic;
		this.colour = colour;
		this.name = name;
	}
	
	private Colour(boolean metallic, BaseColour colour, String name) {
		this.metallic = metallic;
		this.colour = colour.getColour();
		this.name = name;
	}
	
	// Constructors with formatting names:
	private Colour(boolean metallic, Color colour, String name, List<String> formattingNames) {
		this.metallic = metallic;
		this.colour = colour;
		this.name = name;
		this.formattingNames=formattingNames;
	}
	
	private Colour(boolean metallic, BaseColour colour, String name, List<String> formattingNames) {
		this.metallic = metallic;
		this.colour = colour.getColour();
		this.name = name;
		this.formattingNames=formattingNames;
	}

	/**
	 * Returns a String in the format RRGGBB
	 * 
	 * @return
	 */
	public String toWebHexString() {
		return "#"+getColor().toString().substring(2, 8);
	}

	public Color getColor() {
		return colour;
	}

	public boolean isMetallic() {
		return metallic;
	}

	public boolean isRainbow() {
		return false;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return An array of length 5, with [0] being darkest, [4] being lightest.
	 */
	public String[] getShades() {
		return getShades(5);
	}
	
	public String[] getShades(int shadesCount) {
		String[] shadesString = new String[shadesCount];
		float luminosity = -0.5f;
		float increment = (Math.abs(luminosity)*2)/(shadesCount-1);
		int red = Integer.parseInt(colour.toString().substring(2, 4), 16);
		int gre = Integer.parseInt(colour.toString().substring(4, 6), 16);
		int blu = Integer.parseInt(colour.toString().substring(6, 8), 16);
		int r, g, b;

		for (int i = 0; i < shadesCount; i++) {
			r = red + (int)(red * (i * increment + luminosity));
			r = Math.max(Math.min(r, 255), 0);

			g = gre + (int)(gre * (i * increment + luminosity));
			g = Math.max(Math.min(g, 255), 0);

			b = blu + (int)(blu * (i * increment + luminosity));
			b = Math.max(Math.min(b, 255), 0);

			shadesString[i] = String.format("#%02X%02X%02X", r, g, b);
		}

		return shadesString;
	}

	public List<String> getFormattingNames() {
		return formattingNames;
	}

}
