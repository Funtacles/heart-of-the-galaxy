package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public enum Perk {
	
	// NPC Histories:
	
	JOB_MISC(20,
			true,
			"Misc",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(),
			null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";
		}
	},
	
	JOB_PROSTITUTE(20,
			true,
			"The oldest profession",
			PerkCategory.JOB,
			"perks/jobs/prostitute",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 25),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, 25)),
			Util.newArrayListOfValues("[style.boldExcellent(Doubles)] all slave and self-prostitution income")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";
		}
	},
	
	JOB_MUGGER(20,
			true,
			"Outlaw",
			PerkCategory.JOB,
			"perks/jobs/mugger",
			Colour.BASE_CRIMSON,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 15)),
			Util.newArrayListOfValues("[style.boldExcellent(Triples)] all mugging income")) {
		@Override
		public String getDescription(GameCharacter owner) {
			return ".";
		}
	},
	
	// Physical:
	
	PHYSICAL_BASE(20,
			false,
			"natural fitness",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a natural amount of physical fitness.";
		}
	},
	
	PHYSIQUE_1(20,
			false,
			"physically fit I",
			PerkCategory.PHYSICAL,
			"perks/attStrength1",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You feel as though your physical fitness is improving!";
		}
	},
	
	PHYSIQUE_3(20,
			false,
			"physically fit III",
			PerkCategory.PHYSICAL,
			"perks/attStrength3",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical fitness is definitely improving!";
		}
	},

	PHYSIQUE_5(20,
			false,
			"physically fit V",
			PerkCategory.PHYSICAL,
			"perks/attStrength5",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical fitness is improving massively!";
		}
	},
	
	PHYSICAL_DAMAGE_5(20,
			false,
			"striker V",
			PerkCategory.PHYSICAL,
			"UIElements/swordIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical damage is improving massively!";
		}
	},
	
	PHYSICAL_RESISTANCE_5(20,
			false,
			"defender V",
			PerkCategory.PHYSICAL,
			"UIElements/shieldIcon",
			Colour.DAMAGE_TYPE_PHYSICAL,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your physical resistance is improving massively!";
		}
	},

	AURA_BOOST_10(20,
			false,
			"aura reserves X",
			PerkCategory.ARCANE,
			"UIElements/shieldIcon",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "The capacity of your aura is growing!";
		}
	},
	
	ENERGY_BOOST_10(20,
			false,
			"energy reserves X",
			PerkCategory.ARCANE,
			"UIElements/shieldIcon",
			Colour.ATTRIBUTE_HEALTH,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.HEALTH_MAXIMUM, 10)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your energy reserves are growing!";
		}
	},
	
	ELEMENTALIST_5(20,
			false,
			"elementalist V",
			PerkCategory.BOTH,
			"perks/elementalist5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5),
					new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You are learning how to harness arcane elements more effectively.";
		}
	},
	
	
	ARCANE_BASE(20,
			false,
			"natural arcane power",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 40)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a surprisingly large amount of natural arcane power.";
		}
	},
	
	ARCANE_1(20,
			false,
			"arcane affinity I",
			PerkCategory.ARCANE,
			"perks/attIntelligence1",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You feel as though your ability to harness the arcane is improving!";
		}
	},
	
	ARCANE_3(20,
			false,
			"arcane affinity III",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 3)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your ability to harness the arcane is definitely improving!";
		}
	},

	ARCANE_5(20,
			false,
			"arcane affinity V",
			PerkCategory.ARCANE,
			"perks/attIntelligence5",
			Colour.ATTRIBUTE_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 5)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your ability to harness the arcane is improving massively!";
		}
	},
	
	
	SEDUCTION_1(20,
			false,
			"seductive I",
			PerkCategory.ARCANE,
			"perks/attSeduction1",
			Colour.BASE_PINK_LIGHT,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 1)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You love flirting, and, from your experience, your partners love it too!";
			else
				return UtilText.parse(owner, "[npc.Name] is extremely flirty.");
		}
	},
	
	SEDUCTION_3(20,
			false,
			"seductive III",
			PerkCategory.ARCANE,
			"perks/attSeduction3",
			Colour.BASE_PINK,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 3)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're somewhat more than the typical flirt. You know just how to move your body in order to seduce even the most frigid of potential partners.";
			else
				return UtilText.parse(owner, "[npc.Name] moves in a highly seductive manner.");
		}
	},
	
	SEDUCTION_5(20,
			false,
			"seductive V",
			PerkCategory.ARCANE,
			"perks/attSeduction5",
			Colour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your every move drips with sexually suggestive body language. You're a walking sex bomb, and from the reactions of those around you, everyone can see it.";
			else
				return UtilText.parse(owner, "[npc.Name] is a walking sex bomb. [npc.Her] every movement drips with suggestive body language, and you can't help but feel extremely aroused just by looking at [npc.herHim].");
		}
	},
	
	SEDUCTION_5_B(20,
			false,
			"seductive V",
			PerkCategory.ARCANE,
			"perks/attSeduction5",
			Colour.BASE_PINK_DEEP,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_LUST, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your every move drips with sexually suggestive body language. You're a walking sex bomb, and from the reactions of those around you, everyone can see it.";
			else
				return UtilText.parse(owner, "[npc.Name] is a walking sex bomb. [npc.Her] every movement drips with suggestive body language, and you can't help but feel extremely aroused just by looking at [npc.herHim].");
		}
	},
	
	
	AURA_RESILIENCE(20,
			true,
			"resilient aura",
			PerkCategory.ARCANE,
			"perks/fitness_runner",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 25)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "You have a considerable pool of arcane energy stored in your aura.";
		}
	},
	
	AURA_RESILIENCE_2(20,
			true,
			"indomitable aura",
			PerkCategory.ARCANE,
			"perks/fitness_runner_2",
			Colour.ATTRIBUTE_MANA,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MANA_MAXIMUM, 50)),
			null) {

		@Override
		public String getDescription(GameCharacter owner) {
			return "Your aura reserves are seemingly endless.";
		}
	},
	
	
	BRAWLER(20,
			true,
			"brawler",
			PerkCategory.PHYSICAL,
			"perks/physical_brawler",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_PHYSICAL, 15),
					new Value<Attribute, Integer>(Attribute.RESISTANCE_PHYSICAL, 15)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're quite competent at fighting. You gain a bonus to your physical damage and resistance.";
			else
				return UtilText.parse(owner, "[npc.Name] is a competent fighter. [npc.She] gains a bonus to [npc.her] physical damage and resistance.");
		}
	},
	
	
	OBSERVANT(60,
			true,
			"observant",
			PerkCategory.PHYSICAL,
			"perks/misc_observant",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.CRITICAL_CHANCE, 5)),
			Util.newArrayListOfValues(
					"<span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Gender detection</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are very perceptive, and are capable of noticing the slightest of changes in your surroundings."
						+ " You are always able to determine a person's gender, even if you have no knowledge of what their groin looks like.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is very perceptive, and [npc.she] continuously scans [npc.her] surroundings for signs of danger.");
			}
		}
	},

	FIRE_ENHANCEMENT(20,
			false,
			"firebrand",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane fire. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane fire. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	FIRE_ENHANCEMENT_2(20,
			false,
			"incendiary",
			PerkCategory.ARCANE,
			"perks/arcane_fire_1",
			Colour.DAMAGE_TYPE_FIRE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_FIRE, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_FIRE, 10)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane fire. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane fire. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},
	COLD_ENHANCEMENT(20,
			false,
			"frosty",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane ice. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane ice. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	COLD_ENHANCEMENT_2(20,
			false,
			"ice cold",
			PerkCategory.ARCANE,
			"perks/arcane_ice_1",
			Colour.DAMAGE_TYPE_COLD,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_ICE, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_ICE, 10)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane ice. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane ice. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},
	POISON_ENHANCEMENT(20,
			false,
			"venomous",
			PerkCategory.ARCANE,
			"perks/attIntelligence3",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 5)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You have a heightened affinity with arcane poison. You know just how to manipulate it in order to maximise the damage caused.";
			else
				return UtilText.parse(owner, "[npc.Name] has a heightened affinity with arcane poison. [npc.She] knows just how to manipulate it in order to maximise the damage caused.");
		}
	},
	POISON_ENHANCEMENT_2(20,
			false,
			"toxic",
			PerkCategory.ARCANE,
			"perks/arcane_poison_1",
			Colour.DAMAGE_TYPE_POISON,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.DAMAGE_POISON, 10), new Value<Attribute, Integer>(Attribute.RESISTANCE_POISON, 10)), null) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are an expert at manipulating arcane poison. Not only are you able to maximise its damage, but you also have a heightened resistance to its effects.";
			else
				return UtilText.parse(owner, "[npc.Name] is an expert at manipulating arcane poison. Not only is [npc.she] able to maximise its damage, but [npc.she] also has a heightened resistance to its effects.");
		}
	},

	// Fitness:
	RUNNER(20,
			true,
			"runner",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 3)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Improved escape chance</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're a natural runner and possess a good level of stamina.";
			else
				return UtilText.parse(owner, "[npc.Name] is natural runner and possesses a good level of stamina.");
		}
	},
	RUNNER_2(20,
			true,
			"cardio champion",
			PerkCategory.PHYSICAL,
			"perks/fitness_runner_2",
			Colour.ATTRIBUTE_PHYSIQUE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_PHYSIQUE, 5)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.ATTRIBUTE_PHYSIQUE.toWebHexString()+ ";'>Improved escape chance</span>")) {
		@Override
		public String getName(GameCharacter character) {
			if (character.isFeminine())
				return "Cardio Queen";
			else
				return "Cardio King";
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're the " + (owner.isFeminine() ? "queen" : "king") + " of cardio, possessing a seemingly endless reserve of energy.";
			else
				return UtilText.parse(owner, "[npc.Name] is the " + (owner.isFeminine() ? "queen" : "king") + " of cardio, possessing a seemingly endless reserve of energy.");
		}
	},
	FEMALE_ATTRACTION(60,
			true,
			"ladykiller",
			PerkCategory.ARCANE,
			"perks/fitness_female_attraction",
			Colour.FEMININE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</span>"
					+ " vs <span style='color:" + Colour.FEMININE.toWebHexString()+ ";'>feminine opponents</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're very flirtatious, and although your charms work well on both sexes, you find that you get more opportunities to seduce women than you do men.";
			else
				return UtilText.parse(owner, "[npc.Name] is very popular with the ladies.");
		}

	},
	MALE_ATTRACTION(60,
			true,
			"minx",
			PerkCategory.ARCANE,
			"perks/fitness_male_attraction",
			Colour.MASCULINE,
			null, Util.newArrayListOfValues("+10% <span style='color:" + Attribute.DAMAGE_LUST.getColour().toWebHexString() + ";'>lust damage</span>"
					+ " vs <span style='color:" + Colour.MASCULINE.toWebHexString()+ ";'>masculine opponents</span>")) {
		@Override
		public String applyPerkGained(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String applyPerkLost(GameCharacter character) {
			return UtilText.parsePlayerThought("");
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You're quite a tease, and although your charms work well on both sexes, you find that you get more opportunities to seduce men than you do women.";
			else
				return UtilText.parse(owner, "[npc.Name] is very popular with men.");
		}

	},
	
	NYMPHOMANIAC(20,
			true,
			"nymphomaniac",
			PerkCategory.ARCANE,
			"perks/fitness_nymphomaniac",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(
					new Value<Attribute, Integer>(Attribute.RESISTANCE_LUST, -25)),
			Util.newArrayListOfValues("Doubles <span style='color:" + Colour.GENERIC_ARCANE.toWebHexString()+ ";'>arcane essence gain</span> from each orgasm")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You are completely and hopelessly addicted to sex.";
			else
				return UtilText.parse(owner, "[npc.Name] is completely and hopelessly addicted to sex.");
		}
	},
	
	
	CLOTHING_ENCHANTER(20,
			false,
			"arcane weaver",
			PerkCategory.ARCANE,
			"perks/arcaneWeaver",
			Colour.GENERIC_ARCANE,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.MAJOR_ARCANE, 1)),
			Util.newArrayListOfValues("<span style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Halves cost of all clothing enchantments</span>")) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You have a natural affinity for weaving arcane enchantments into items of clothing, allowing you to expend only half of the usual arcane essences when enchanting clothing.";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a natural affinity for weaving arcane enchantments into items of clothing, allowing [npc.herHim] to expend only half of the usual arcane essences when enchanting clothing.");
			}
		}
	},
	
	BARREN(20,
			true,
			"barren",
			PerkCategory.PHYSICAL,
			"perks/barren",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, -100)), null) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are very infertile, and are highly unlikely to ever get pregnant.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is highly unlikely to get pregnant.");
			}
		}
	},
	
	FIRING_BLANKS(20,
			true,
			"firing blanks",
			PerkCategory.PHYSICAL,
			"perks/firing_blanks",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, -100)), null) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your seed is incredibly weak, and you are highly unlikely to ever get anyone pregnant.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] seed is incredibly weak, and [npc.sheIs] highly unlikely to ever get anyone pregnant.");
			}
		}
	},
	

	
	FETISH_BROODMOTHER(20,
			true,
			"broodmother",
			PerkCategory.PHYSICAL,
			"fetishes/fetish_broodmother",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.FERTILITY, 10)),
			Util.newArrayListOfValues("2x <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Maximum offspring in mothered litters</span>")) {

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your body is built for one thing; pumping out as many children as possible."
							+ " Whether due to an effect of your arcane aura, or perhaps just because of your body's natural fertility, you seem to always give birth to huge numbers of children at once.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] body is built for one thing; pumping out as many children as possible."
						+ " [npc.She] seems to always give birth to huge numbers of children at once.");
			}
		}

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
	},
	
	FETISH_SEEDER(20,
			true,
			"seeder",
			PerkCategory.PHYSICAL,
			"fetishes/fetish_seeder",
			Colour.GENERIC_SEX,
			Util.newHashMapOfValues(new Value<Attribute, Integer>(Attribute.VIRILITY, 10)),
			Util.newArrayListOfValues("2x <span style='color:"+ Colour.GENERIC_SEX.toWebHexString()+ ";'>Maximum offspring in fathered litters</span>")) {

		@Override
		public boolean isAlwaysAvailable() {
			return true;
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your seed has the potent effect of causing anyone impregnated by it to give birth to huge numbers of children.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] seed has the potent effect of causing anyone impregnated by it to give birth to huge numbers of children.");
			}
		}
	},;

	private int renderingPriority;
	protected String name;
	private Colour colour;
	private boolean equippableTrait;

	// Attributes modified by this Virtue:
	private HashMap<Attribute, Integer> attributeModifiers;

	private PerkCategory perkCategory;

	private String SVGString;

	private List<String> extraEffects;

	private List<String> modifiersList;

	private Perk(int renderingPriority, boolean major, String name, PerkCategory perkCategory, String pathName, Colour colour, HashMap<Attribute, Integer> attributeModifiers, List<String> extraEffects) {

		this.renderingPriority = renderingPriority;
		this.name = name;
		this.colour = colour;
		
		this.equippableTrait = major;
		
		this.perkCategory = perkCategory;

		this.attributeModifiers = attributeModifiers;

		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Perk icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);

			SVGString = SVGString.replaceAll("#ff2a2a", colour.getShades()[0]);
			SVGString = SVGString.replaceAll("#ff5555", colour.getShades()[1]);
			SVGString = SVGString.replaceAll("#ff8080", colour.getShades()[2]);
			SVGString = SVGString.replaceAll("#ffaaaa", colour.getShades()[3]);
			SVGString = SVGString.replaceAll("#ffd5d5", colour.getShades()[4]);

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		modifiersList = new ArrayList<>();

		if (attributeModifiers != null)
			for (Entry<Attribute, Integer> e : attributeModifiers.entrySet())
				modifiersList.add("<b>"
						+ (e.getValue() > 0 ? "+" : "")
						+ e.getValue()
						+ "</b>"
						+ " <b style='color: "
						+ e.getKey().getColour().toWebHexString()
						+ ";'>"
						+ Util.capitaliseSentence(e.getKey().getAbbreviatedName())
						+ "</b>");

		if (extraEffects != null)
			modifiersList.addAll(extraEffects);
	}

	public boolean isAlwaysAvailable() {
		return false;
	}
	
	public String getName(GameCharacter owner) {
		return name;
	}

	public Colour getColour() {
		return colour;
	}

	public boolean isEquippableTrait() {
		return equippableTrait;
	}

	public abstract String getDescription(GameCharacter target);

	public List<String> getModifiersAsStringList() {
		return modifiersList;
	}

	public HashMap<Attribute, Integer> getAttributeModifiers() {
		return attributeModifiers;
	}

	public String applyPerkGained(GameCharacter character) {
		return "";
	};

	public String applyPerkLost(GameCharacter character) {
		return "";
	};

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public List<String> getExtraEffects() {
		return extraEffects;
	}

	public String getSVGString() {
		return SVGString;
	}

	public PerkCategory getPerkCategory() {
		return perkCategory;
	}
}
