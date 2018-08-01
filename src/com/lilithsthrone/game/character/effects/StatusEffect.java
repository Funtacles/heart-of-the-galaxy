package com.lilithsthrone.game.character.effects;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AlcoholLevel;
import com.lilithsthrone.game.character.attributes.ArousalLevel;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.IntelligenceLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.attributes.PhysiqueLevel;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.FluidType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.ClothingSet;
import com.lilithsthrone.game.sex.LubricationType;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.slavery.SlaveJob;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public enum StatusEffect {

	// Attribute-related status effects:
	// Strength:
	PHYSIQUE_PERK_0(
			100,
			"sissy",
			"attStrength0",
			Colour.PHYSIQUE_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ZERO_WEAK.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are incredibly weak. You struggle to do much damage with your wimpy little [pc.arms], and your fragile body is particularly vulnerable to physical damage.";
			else
				return UtilText.parse(target, "[npc.Name] is incredibly weak. [npc.She] struggles to do much damage with [npc.her] wimpy little [npc.arms], and [npc.her] fragile body is particularly vulnerable to physical damage.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ZERO_WEAK;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	PHYSIQUE_PERK_1(
			100,
			"average",
			"attStrength1",
			Colour.PHYSIQUE_STAGE_ONE,
			true,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an average level of physical fitness for your body size.";
			else
				return UtilText.parse(target, "[npc.Name] has an average level of physical fitness for [npc.her] body size.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.ONE_AVERAGE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_2(
			100,
			"strong",
			"attStrength2",
			Colour.PHYSIQUE_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 10f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.TWO_STRONG.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are stronger and fitter than your body size would suggest, and are able to inflict more physical damage as a result.";
			else
				return UtilText.parse(target, "[npc.Name] is stronger and fitter than [npc.her] body size would suggest, and is able to inflict more physical damage as a result.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.TWO_STRONG;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_3(
			100,
			"powerful",
			"attStrength3",
			Colour.PHYSIQUE_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 10f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.THREE_POWERFUL.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are considerably stronger and fitter than your body size would suggest, and are able to inflict a significant amount of physical damage as a result.";
			else
				return UtilText.parse(target, "[npc.Name] is stronger and fitter than [npc.her] body size would suggest, and is able to inflict a significant amount of physical damage as a result.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.THREE_POWERFUL;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_4(
			100,
			"mighty",
			"attStrength4",
			Colour.PHYSIQUE_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 30f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 25f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FOUR_MIGHTY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You have an exceptional level of fitness, and there are few who could ever hope to rival your raw physical power.";
			else
				return UtilText.parse(target, "[npc.Name] has an exceptional level of fitness, and there are few who could ever hope to rival [npc.her] raw physical power.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FOUR_MIGHTY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	PHYSIQUE_PERK_5(
			100,
			"Herculean",
			"attStrength5",
			Colour.PHYSIQUE_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 20f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 50f),
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 50f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(PhysiqueLevel.FIVE_HERCULEAN.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "Your body is the stuff of legend; mere mortals look upon you in fear and awe!";
			else
				return UtilText.parse(owner, "[npc.NamePos] body is the stuff of legend.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return PhysiqueLevel.getPhysiqueLevelFromValue(target.getAttributeValue(Attribute.MAJOR_PHYSIQUE)) == PhysiqueLevel.FIVE_HERCULEAN;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},

	// Intelligence:
	INTELLIGENCE_PERK_0_OLD_WORLD(
			80,
			"No Arcane Power",
			"attIntelligence0",
			Colour.INTELLIGENCE_STAGE_ZERO,
			false,
			null,
			null) {
		
		@Override
		public String getDescription(GameCharacter owner) {
			return "Due to the fact that the arcane doesn't exist in this world, your ability to cast spells is non-existent.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && !Main.game.isInNewWorld();
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_0(
			80,
			"arcane impotence",
			"attIntelligence0",
			Colour.INTELLIGENCE_STAGE_ZERO,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, -75f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, -75f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + "'>Surrender in combat at maximum lust</b>")) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ZERO_AIRHEAD.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Despite your natural affinity with the arcane, you've somehow managed to end up losing most of your power...";
			} else {
				return UtilText.parse(owner, "[npc.Name] is unable to harness the arcane in any significant manner. This is a typical level of arcane affinity in all the common races of this world.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ZERO_AIRHEAD && Main.game.isInNewWorld();
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_1(
			100,
			"arcane potential",
			"attIntelligence1",
			Colour.INTELLIGENCE_STAGE_ONE,
			true,
			null,
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + "'>Surrender in combat at maximum lust</b>")) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.ONE_AVERAGE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are a less adept at harnessing the arcane than you were when first entering this world, but are nevertheless still far more adept than the vast majority of the population.";
			} else {
				return UtilText.parse(target, "[npc.Name] has a small amount of ability with the arcane; equal to that of a common race who's undergone extensive training.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.ONE_AVERAGE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_2(
			100,
			"arcane proficiency",
			"attIntelligence2",
			Colour.INTELLIGENCE_STAGE_TWO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 5f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 5f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.TWO_SMART.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Your natural arcane ability is little weaker than it was when first entering this world.";
			} else {
				return UtilText.parse(target, "[npc.Name] is proficient at harnessing the arcane, and [npc.her] spells are not only easier to cast, but also do more damage.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.TWO_SMART;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_3(
			100,
			"arcane prowess",
			"attIntelligence3",
			Colour.INTELLIGENCE_STAGE_THREE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 5f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 10f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.THREE_BRAINY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are highly proficient with the arcane. Your spells are easier to cast and do more damage, and you also have a small amount of elemental damage affinity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is highly proficient with the arcane. [npc.Her] spells are easier to cast and do more damage, and [npc.she] also has a small amount of elemental damage affinity.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.THREE_BRAINY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_4(
			100,
			"arcane mastery",
			"attIntelligence4",
			Colour.INTELLIGENCE_STAGE_FOUR,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 15f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 10f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 25f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FOUR_GENIUS.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "You are extremely proficient with the arcane. Your spells are easier to cast and do more damage, and you also have a considerable amount of elemental damage affinity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is extremely proficient with the arcane."
						+ " [npc.Her] spells are easier to cast and do more damage, and [npc.she] also has a considerable amount of elemental damage affinity.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FOUR_GENIUS;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	INTELLIGENCE_PERK_5(
			100,
			"arcane brilliance",
			"attIntelligence5",
			Colour.INTELLIGENCE_STAGE_FIVE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 20f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 20f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FIRE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ICE, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 15f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 50f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(IntelligenceLevel.FIVE_POLYMATH.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Your ability to harness the arcane is rivalled only by Lilith herself. Casting spells comes as naturally to you as does breathing.";
			} else {
				return UtilText.parse(owner, "[npc.NamePos] arcane ability is rivalled only by Lilith herself. Casting spells comes as naturally to [npc.herHim] as does breathing.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return IntelligenceLevel.getIntelligenceLevelFromValue(target.getAttributeValue(Attribute.MAJOR_ARCANE)) == IntelligenceLevel.FIVE_POLYMATH;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},

	// Corruption:
	CORRUPTION_PERK_0(100,
			"Pure",
			"attCorruption0",
			Colour.CORRUPTION_STAGE_ZERO,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 25f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ZERO_PURE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are completely uncorrupted, and aside from performing the most conservative of sexual acts with the person you love, you're not really interested in sex at all.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is completely uncorrupted, and aside from performing the most conservative of sexual acts with the person [npc.she] loves, [npc.sheIs] not really interested in sex at all.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ZERO_PURE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_1(
			100,
			"Vanilla",
			"attCorruption1",
			Colour.CORRUPTION_STAGE_ONE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.ONE_VANILLA.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You're open to the idea of having casual sex, but are still unwilling to perform any extreme sexual acts.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is open to the idea of having casual sex, but is unwilling to perform any extreme sexual acts.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.ONE_VANILLA;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_2(
			100,
			"dirty",
			"attCorruption2",
			Colour.CORRUPTION_STAGE_TWO,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 20f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.TWO_HORNY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "Sexual acts that once may have made you feel uncomfortable are now the focus of your fantasies, and you can't wait to try them out on a willing partner...";
			} else {
				return UtilText.parse(owner, "[npc.Name] has a dirty look in [npc.her] eyes, and you often notice [npc.her] gaze lingering hungrily over your body.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.TWO_HORNY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_3(
			100,
			"Lewd",
			"attCorruption3",
			Colour.CORRUPTION_STAGE_THREE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 30f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 25f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 25f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.THREE_DIRTY.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.getVaginaType() != VaginaType.NONE) {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body, and you feel as though it's going to be far easier to get pregnant from now on...";
				} else if (owner.getPenisType() != PenisType.NONE) {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to impregnate your sexual partners from now on...";
				} else {
					return "Given power by the fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body, but because you don't have any sexual organs, there's not much that's happened...";
				}
				
			} else {
				if (owner.getVaginaType() != VaginaType.NONE) {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body, making it far easier for [npc.herHim] to get pregnant.");
				} else if (owner.getPenisType() != PenisType.NONE) {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body, making it far easier for [npc.herHim] to impregnate others.");
				} else {
					return UtilText.parse(owner,
							"Given power by the fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " but because [npc.she] doesn't have any sexual organs, there's not much that's happened.");
				}
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.THREE_DIRTY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	CORRUPTION_PERK_4(
			100,
			"Lustful",
			"attCorruption4",
			Colour.CORRUPTION_STAGE_FOUR,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 40f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FOUR_LUSTFUL.getName());
		}

		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				if (owner.getVaginaType() != VaginaType.NONE) {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to get pregnant from now on...";
				} else if (owner.getPenisType() != PenisType.NONE) {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " and you feel as though it's going to be far easier to impregnate your sexual partners from now on...";
				} else {
					return "Given a huge amount of power by the lewd fantasies that constantly run through your mind, the arcane is starting to have a physical effect on your body,"
							+ " but because you don't have any sexual organs, there's not much that's happened...";
				}
				
			} else {
				if (owner.getVaginaType() != VaginaType.NONE) {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " making it far easier for [npc.herHim] to get pregnant.");
				} else if (owner.getPenisType() != PenisType.NONE) {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " making it far easier for [npc.herHim] to impregnate others.");
				} else {
					return UtilText.parse(owner,
							"Given a huge amount of power by the lewd fantasies that constantly run through [npc.her] mind, the arcane is starting to have a physical effect on [npc.namePos] body,"
							+ " but because [npc.she] doesn't have any sexual organs, there's not much that's happened.");
				}
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FOUR_LUSTFUL;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	CORRUPTION_PERK_5(
			100,
			"Corrupt",
			"attCorruption5",
			Colour.CORRUPTION_STAGE_FIVE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 50f),
					new Value<Attribute, Float>(Attribute.FERTILITY, 75f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 75f)),
			Util.newArrayListOfValues("<b style='color: "+ Colour.ATTRIBUTE_CORRUPTION.toWebHexString()+ "'>Obeys Lilin</b>")) {

		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(CorruptionLevel.FIVE_CORRUPT.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer()) {
				return "You are completely and utterly corrupted. The lewd thoughts and fantasies that continuously run through your mind have unlocked the full power of the arcane, making your body hyper-fertile and virile.";
			} else {
				return UtilText.parse(owner, "[npc.Name] is completely and utterly corrupted."
						+ " The lewd thoughts and fantasies that continuously run through [npc.her] mind have unlocked the full power of the arcane, making [npc.her] body hyper-fertile and virile.");
			}

		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return CorruptionLevel.getCorruptionLevelFromValue(target.getAttributeValue(Attribute.MAJOR_CORRUPTION)) == CorruptionLevel.FIVE_CORRUPT;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	// Arousal:
	AROUSAL_PERK_0(
			100,
			"none",
			"attArousal0",
			Colour.AROUSAL_STAGE_ZERO,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ZERO_NONE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You aren't aroused at all.";
			else
				return UtilText.parse(target, "[npc.Name] isn't aroused at all.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ZERO_NONE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_1(
			100,
			"turned on",
			"attArousal1",
			Colour.AROUSAL_STAGE_ONE,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.ONE_TURNED_ON.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're starting to get pretty turned on.";
			else
				return UtilText.parse(target, "[npc.Name] is starting to get turned on.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.ONE_TURNED_ON;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_2(
			100,
			"excited",
			"attArousal2",
			Colour.AROUSAL_STAGE_TWO,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.TWO_EXCITED.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're getting quite excited, and your thoughts are now focused on your sexual desires.";
			else
				return UtilText.parse(target, "[npc.Name] is getting quite excited.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.TWO_EXCITED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_3(
			100,
			"heated",
			"attArousal3",
			Colour.AROUSAL_STAGE_THREE,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.THREE_HEATED.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Things are starting to get pretty heated. You can focus on nothing but the thought of sex.";
			else
				return UtilText.parse(target, "[npc.Name] can no longer focus on anything but sex.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.THREE_HEATED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_4(
			100,
			"passionate",
			"attArousal4",
			Colour.AROUSAL_STAGE_FOUR,
			false,
			null,
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Mutual orgasm</b>")) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FOUR_PASSIONATE.getName());
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "The only thing you want right now is to reach your climax.";
			else
				return UtilText.parse(target, "[npc.Name] is only concerned with reaching [npc.her] climax.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FOUR_PASSIONATE;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	AROUSAL_PERK_5(
			100,
			"imminent orgasm",
			"attArousal5",
			Colour.AROUSAL_STAGE_FIVE,
			false,
			null,
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Mutual orgasm</b>")) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(ArousalLevel.FIVE_ORGASM_IMMINENT.getName());
		}
		
		@Override
		public String getDescription(GameCharacter owner) {
			if (owner.isPlayer())
				return "You feel your climax building. You know that it's only going to be a matter of seconds before you orgasm!";
			else
				return UtilText.parse(owner, "[npc.Name] is about to reach [npc.her] climax!");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ArousalLevel.getArousalLevelFromValue(target.getAttributeValue(Attribute.AROUSAL)) == ArousalLevel.FIVE_ORGASM_IMMINENT;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	
	// Lust:
	LUST_PERK_0(
			100,
			"none",
			"attLust0",
			Colour.LUST_STAGE_ZERO,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ZERO_COLD.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.ZERO_COLD.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ZERO_COLD.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ZERO_COLD;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	LUST_PERK_1(
			100,
			"turned on",
			"attLust1",
			Colour.LUST_STAGE_ONE,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.ONE_HORNY.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.ONE_HORNY.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.ONE_HORNY.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.ONE_HORNY;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	LUST_PERK_2(
			100,
			"excited",
			"attLust2",
			Colour.LUST_STAGE_TWO,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.TWO_AMOROUS.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.TWO_AMOROUS.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.TWO_AMOROUS.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.TWO_AMOROUS;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	LUST_PERK_3(
			100,
			"heated",
			"attLust3",
			Colour.LUST_STAGE_THREE,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.THREE_LUSTFUL.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.THREE_LUSTFUL.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.THREE_LUSTFUL.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.THREE_LUSTFUL;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	LUST_PERK_4(
			100,
			"passionate",
			"attLust4",
			Colour.LUST_STAGE_FOUR,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FOUR_IMPASSIONED.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.FOUR_IMPASSIONED.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FOUR_IMPASSIONED.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FOUR_IMPASSIONED;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	LUST_PERK_5(
			100,
			"passionate",
			"attLust5",
			Colour.LUST_STAGE_FOUR,
			false,
			null,
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return Util.capitaliseSentence(LustLevel.FIVE_BURNING.getName());
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>(modifiersList);
			modList.addAll(LustLevel.FIVE_BURNING.getStatusEffectModifierDescription(Sex.isConsensual(), target));
			return modList;
		}
		
		@Override
		public String getDescription(GameCharacter target) {
			return LustLevel.FIVE_BURNING.getStatusEffectDescription(Sex.isConsensual(), target);
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return LustLevel.getLustLevelFromValue(target.getAttributeValue(Attribute.LUST)) == LustLevel.FIVE_BURNING;
		}
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	
	
	
	// STANDARD EFFECTS:
	
	WEATHER_PROLOGUE(100,
			"Strange Atmosphere",
			"weatherNightStormIncoming",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			return "There's a strange atmosphere surrounding the museum this evening, and you inexplicably find yourself feeling incredibly aroused...";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInNewWorld();
		}
	},
	
	WEATHER_CLEAR(100,
			"Clear skies",
			"weatherDayClear",
			Colour.CLOTHING_WHITE,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(Main.game.isDayTime()) {
				return "The sun shines down on you, and you let out a contented sigh as you look up at the clear blue sky."
						+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
			} else {
				return "The moon shines down through a clear night's sky, and you let out a contented sigh as you look up at the stars."
						+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLEAR && Main.game.isInNewWorld();
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayClear();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightClear();
		}
		
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
		}
	},
	
	WEATHER_CLOUD(100,
			"Cloudy skies",
			"weatherDayCloudy",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			return "The weather seems to change at a moment's notice, and is currently overcast, with a chance of rain."
					+ " Although there's no sign of a storm at the moment, you can still feel the effects of the arcane manifesting in the form of an increased libido.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.CLOUD && Main.game.isInNewWorld();
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayCloud();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightCloud();
		}
		
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return Util.newArrayListOfValues(
						"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
		}
	},
	
	WEATHER_RAIN(100,
			"Rain",
			"weatherDayRain",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			return "The heavy rain clouds overhead have finally burst, unleashing a sudden, and torrential, downpour."
					+ " Although there's no sign of an arcane storm at the moment, you can still feel its effects manifesting in the form of an increased libido.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.RAIN && Main.game.isInNewWorld();
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayRain();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightRain();
		}
		
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
		}
	},
	
	WEATHER_STORM_GATHERING(100,
			"Gathering storm",
			"weatherDayStormIncoming",
			Colour.CLOTHING_WHITE,
			false,
			null,
			Util.newArrayListOfValues("<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			return "A roiling mass of thick black storm clouds hang heavy in the skies above you."
					+ " Flashes of pink and purple energy can be seen just beneath their surface, and you realise that an arcane storm is going to break out at any moment.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.getCurrentWeather()==Weather.MAGIC_STORM_GATHERING && Main.game.isInNewWorld();
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormIncoming();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormIncoming();
		}
		
		@Override
		public List<String> getExtraEffects(GameCharacter target) {
			return Util.newArrayListOfValues(
					"<b style='color: " + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Enhanced libido</b>");
		}
	},
	
	WEATHER_STORM(100,
			"Arcane storm",
			"weatherDayStorm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
					"[style.boldExcellent(Double)] all <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat")) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				return "<p>"
						+ "A bright-pink flash suddenly illuminates the entire city of Dominion, causing those few residents still prowling the streets to look skywards."
						+ " High up above them, the threatening storm clouds have finally broken, and a roiling mass of arcane energy finally crackles into life."
						+ "</p>"
						+ "<p>"
						+ "Within moments, a ghostly series of lewd moans and ecstatic screams start echoing throughout the city, and as the arcane thunder penetrates into the minds of those without a strong aura,"
						+ " they find themselves unable to think of anything but sex..."
						+ "</p>";
			} else {
				return "";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Huge streaks of pink and purple lightning arc through the sky as an arcane storm rages high above you."
						+ " Although resistant to most of its arousing power, you're not completely unaffected, and you find yourself feeling hornier than usual.";
			} else {
				return UtilText.parse(target, "[npc.Name] seems to be just as resistant to the ongoing arcane storm as you are!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if((target.isPlayer() || !target.getRace().isVulnerableToArcaneStorm()) && !target.getLocationPlace().isStormImmune()) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.isInNewWorld();
				
			} else {
				return false;
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime()) {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			} else {
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
			}
		}
	},
	
	WEATHER_STORM_VULNERABLE(100,
			"Arcane storm",
			"weatherDayStorm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -75f)),
			Util.newArrayListOfValues(
					"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Enhanced libido</b>",
					"<b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Overwhelming Lust</b>",
					"[style.boldExcellent(Double)] <b style='color: "+ Colour.GENERIC_ARCANE.toWebHexString()+ ";'>Essence gains</b> from sex & combat")) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] is being heavily affected by the ongoing arcane storm.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.getRace().isVulnerableToArcaneStorm() && !target.isPlayer() && !target.getLocationPlace().isStormImmune()) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.isInNewWorld();
			} else {
				return false;
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStorm();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStorm();
		}
	},
	
	WEATHER_STORM_PROTECTED(100,
			"Arcane storm (protected)",
			"weatherDayStorm",
			Colour.GENERIC_GOOD,
			true,
			null,
			Util.newArrayListOfValues("<b style='color: "
					+ Colour.GENERIC_ARCANE.toWebHexString()
					+ ";'>Enhanced libido</b>")) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer() && Main.game.getDialogueFlags().values.contains(DialogueFlagValue.stormTextUpdateRequired)) {
				Main.game.getDialogueFlags().values.remove(DialogueFlagValue.stormTextUpdateRequired);
				return "<p>"
						+ "A bright-pink flash suddenly illuminates the entire city of Dominion, causing those few residents still prowling the streets to look skywards."
						+ " High up above them, the threatening storm clouds have finally broken, and a roiling mass of arcane energy finally crackles into life."
						+ "</p>"
						+ "<p>"
						+ "Within moments, a ghostly series of lewd moans and ecstatic screams start echoing throughout the city, and as the arcane thunder penetrates into the minds of those without a strong aura,"
						+ " they find themselves unable to think of anything but sex..."
						+ "</p>";
			} else {
				return "";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Huge streaks of pink and purple lightning arc through the sky as an arcane storm rages high above you."
						+ " Although you can still feel its effects taking the form of an increased libido, you're currently protected from most of the storm's wrath.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is currently protected from the arcane storm.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.getLocationPlace().isStormImmune()) {
				return Main.game.getCurrentWeather()==Weather.MAGIC_STORM && Main.game.isInNewWorld();
			} else {
				return false;
			}
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			if(Main.game.isDayTime())
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherDayStormProtected();
			else
				return SVGImages.SVG_IMAGE_PROVIDER.getWeatherNightStormProtected();
		}
	},
	
	// RACES:
	// HUMAN:
	PURE_HUMAN_PROLOGUE(
			1000,
			"human",
			"raceHuman",
			Colour.CLOTHING_WHITE,
			true,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "You're a human, just like every other person in this world.";
			else
				return "[npc.Name] is a human, just like every other person in this world.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& target.getRaceStage() == RaceStage.HUMAN
					&& !Main.game.isInNewWorld();
		}
	},
	
	PURE_HUMAN(
			1000,
			"human",
			null,
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 20f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Humans have a much higher resistance to the arousing effects of the arcane than any other race.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HUMAN
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.HUMAN
					&& Main.game.isInNewWorld();
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},

	// CANINE:
	DOG_MORPH(1000,
			"dog-morph",
			null,
			Colour.RACE_DOG_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You always have lots of energy, and get excited about new things very easily.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " always has lots of energy, and [npc.she] gets excited about new things very easily.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.DOG_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	WOLF_MORPH(1000,
			"wolf-morph",
			null,
			Colour.RACE_WOLF_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your wolf-like body is very strong, but you often get strong urges to try and dominate people you meet.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s wolf-like body is very strong, but [npc.she] often gets strong urges to try and dominate people [npc.she] meets.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.WOLF_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	FOX_MORPH(1000,
			"fox-morph",
			null,
			Colour.RACE_FOX_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 5f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 20f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're more nimble than your appearance would suggest, and your senses reveal opportune moments to attack.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is more nimble than [npc.his] appearance would suggest, and [npc.his] senses reveal opportune moments to attack.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER
					&& target.getTailType() != TailType.FOX_MORPH_MAGIC;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	FOX_ASCENDANT_1(1000,
			"1-tailed youko",
			"raceFoxTail1",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 6f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 25f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you one magical tail, gained from a secret trial.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] one magical tail, gained from a secret trial.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 1
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_2(1000,
			"2-tailed youko",
			"raceFoxTail2",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 7f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 30f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 20f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you two magical tails, gained from a few trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] two magical tails, gained from a few trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 2
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_3(1000,
			"3-tailed youko",
			"raceFoxTail3",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 8f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 35f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 30f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you three magical tails, gained from a few trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] three magical tails, gained from a few trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 3
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_4(1000,
			"4-tailed youko",
			"raceFoxTail4",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 9f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 40f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 40f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you four magical tails, gained over many trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] four magical tails, gained over many trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 4
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_5(1000,
			"5-tailed youko",
			"raceFoxTail5",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 10f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 45f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 50f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you five magical tails, gained over many trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] five magical tails, gained over many trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 5
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_6(1000,
			"6-tailed youko",
			"raceFoxTail6",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 11f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 50f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 60f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you six magical tails, gained over many trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] six magical tails, gained over many trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 6
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},

	FOX_ASCENDANT_7(1000,
			"7-tailed youko",
			"raceFoxTail7",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 12f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 55f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 70f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you seven magical tails, gained over many trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] seven magical tails, gained over many trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 7
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_8(1000,
			"8-tailed youko",
			"raceFoxTail8",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 13f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 60f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 80f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your service to a particular Lilin having afforded you eight magical tails, gained over many trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] service to a particular Lilin having afforded [npc.him] eight magical tails, gained over many trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() == 8
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},
	
	FOX_ASCENDANT_FINAL(1000,
			"ascendant youko",
			"raceFoxTail9",
			Colour.RACE_FOX_MORPH,
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, 25f),
					new Value<Attribute, Float>(Attribute.CRITICAL_DAMAGE, 100f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 100f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You are a fox-morph, your vast number of tails a signal of your unending dedication to a particular Lilin, gained over many trials.";
			else
				return UtilText.parse(target, target.getName("The")
						+ " is a fox-morph, [npc.his] vast number of tails a signal of [npc.his] unending dedication to a particular Lilin, gained over many trials.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.FOX_MORPH
					&& !target.isRaceConcealed()
					&& target.getTailCount() >= 9
					&& target.getTailType() == TailType.FOX_MORPH_MAGIC;
		}
	},

	// FELINE:
	CAT_MORPH(1000,
			"cat-morph",
			null,
			Colour.RACE_CAT_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body is incredibly agile, and you possess lightning reflexes.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s body is incredibly agile, and [npc.she] possesses lightning reflexes.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.CAT_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},

	// RODENT:
	SQUIRREL_MORPH(1000,
			"Squirrel-morph",
			null,
			Colour.RACE_SQUIRREL_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body is incredibly agile, and you possess lightning reflexes.";
			else
				return UtilText.parse(target, "[npc.NamePos] body is incredibly agile, and [npc.she] possesses lightning reflexes.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.SQUIRREL_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	RAT_MORPH(1000,
			"Rat-morph",
			null,
			Colour.RACE_RAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_POISON, 15f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Your body is adapted for both resisting and dealing poison damage.";
			} else {
				return UtilText.parse(target, "[npc.NamePos] body is adapted for both resisting and dealing poison damage.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.RAT_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	RABBIT_MORPH(1000,
			"Rabbit-morph",
			null,
			Colour.RACE_RABBIT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "Your body is adapted for producing as many offspring as possible.";
			} else {
				return UtilText.parse(target, "[npc.NamePos] body is adapted for producing as many offspring as possible.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.RABBIT_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	// EQUINE:
	HORSE_MORPH(1000,
			"horse-morph",
			null,
			Colour.RACE_HORSE_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f), new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body possesses a great strength, but your mind is considerably slower than it once was.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s body possesses a great strength, but [npc.her] mind isn't exactly the quickest.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.HORSE_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},
	
	// BOVINE:
	COW_MORPH(1000,
			"cow-morph",
			null,
			Colour.RACE_COW_MORPH,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f), new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your body possesses a great strength, but your mind is considerably slower than it once was.";
			else
				return UtilText.parse(target, target.getName("The")
						+ "'s body possesses a great strength, but [npc.her] mind isn't exactly the quickest.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getRace() == Race.COW_MORPH
					&& !target.isRaceConcealed()
					&& target.getRaceStage() == RaceStage.GREATER;
		}

		@Override
		public String getSVGString(GameCharacter owner) {
			return owner.getSubspecies().getSVGString(owner);
		}
	},

	// SEXUAL ORIENTATIONS:
	
	ORIENTATION_ANDROPHILIC(
			90,
			"androphilic",
			"orientation_androphilic",
			Colour.MASCULINE,
			true,
			null,
			Util.newArrayListOfValues(
					"<b>-50%</b> <b style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>Lust damage</b> both to and from <b style='color:"+ Colour.FEMININE.toWebHexString()+ ";'>feminine opponents</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are sexually attracted to males and masculinity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is sexually attracted to males and masculinity.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.ANDROPHILIC;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	ORIENTATION_GYNEPHILIC(
			90,
			"gynephilic",
			"orientation_gynephilic",
			Colour.FEMININE,
			true,
			null,
			Util.newArrayListOfValues(
					"<b>-50%</b> <b style='color:"+ Colour.DAMAGE_TYPE_LUST.toWebHexString()+ ";'>Lust damage</b> both to and from <b style='color:"+ Colour.MASCULINE.toWebHexString()+ ";'>masculine opponents</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are sexually attracted to females and femininity.";
			} else {
				return UtilText.parse(target, "[npc.Name] is sexually attracted to females and femininity.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.GYNEPHILIC;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	ORIENTATION_AMBIPHILIC(
			90,
			"ambiphilic",
			"orientation_ambiphilic",
			Colour.ANDROGYNOUS,
			true,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are sexually attracted to both masculine and feminine people.";
			} else {
				return UtilText.parse(target, "[npc.Name] is sexually attracted to both masculine and feminine people.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getSexualOrientation()==SexualOrientation.AMBIPHILIC;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	

	// CLOTHING:

	CLOTHING_FEMININITY(
			85,
			"clothing too feminine",
			"clothingFemininity",
			Colour.CLOTHING_PINK_LIGHT,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Some of your clothes are too feminine for your masculine figure."
					+ " You find yourself incredibly embarrassed by wearing such clothing, causing you to struggle to think clearly.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
				return false;
			}
			
			for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getFemininityMinimum() > target.getFemininityValue()) {
					return true;
				}
			}
			
			return false;
		}
	},
	CLOTHING_MASCULINITY(
			85,
			"clothing too masculine",
			"clothingMasculinity",
			Colour.CLOTHING_BLUE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Some of your clothes are too masculine for your feminine figure."
					+ " You find yourself incredibly embarrassed by wearing such clothing, causing you to struggle to think clearly.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(target.hasFetish(Fetish.FETISH_CROSS_DRESSER)) {
				return false;
			}
			
			for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if (c.getClothingType().getFemininityMaximum() < target.getFemininityValue()) {
					return true;
				}
			}
			
			return false;
		}
	},
	
	CLOTHING_CUM(
			80,
			"dirty clothing",
			"clothingCummedIn",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Some of your clothes have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly embarrassed to be walking around in such filthy clothing.";
			else
				return "Some of "+target.getName("the")+"'s clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly embarrassed to be walking around in such filthy clothing.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(!isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(c.getClothingType().getItemTags(), Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.PLUGS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	},
	
	CLOTHING_CUM_MASOCHIST(
			80,
			"dirty clothing",
			"clothingCummedInMasochist",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some of your clothes have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly turned on to be walking around in such filthy clothing.";
			} else {
				return UtilText.parse(target, "Some of [npc.namePos] clothes have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly turned on to be walking around in such filthy clothing.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
					if (c.isDirty()
							&& Collections.disjoint(c.getClothingType().getItemTags(), Util.newArrayListOfValues(ItemTag.PLUGS_ANUS, ItemTag.PLUGS_VAGINA, ItemTag.PLUGS_NIPPLES))) {
						return true;
					}
				}
			}
			return false;
		}
	},
	
	BODY_CUM(
			80,
			"dirty body",
			"dirtyBody",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			List<InventorySlot> slotsToClean = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			for(AbstractClothing clothing : target.getClothingCurrentlyEquipped()) {
				if(target.getDirtySlots().contains(clothing.getClothingType().getSlot())) {
					slotsToClean.add(clothing.getClothingType().getSlot());
					if(!clothing.isDirty()) {
						clothing.setDirty(true);
						if(sb.length()>0) {
							sb.append("<br/>");
						}
						sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getClothingType().getSlot().getName()
								+", <b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" in the process</b>.");
					}
					
				} else {
					for(InventorySlot blockedSlot : clothing.getClothingType().getIncompatibleSlots()) {
						if(target.getDirtySlots().contains(blockedSlot)) {
							slotsToClean.add(blockedSlot);
							if(!clothing.isDirty()) {
								clothing.setDirty(true);
								if(sb.length()>0) {
									sb.append("<br/>");
								}
								sb.append("You use your <b>"+clothing.getDisplayName(true)+"</b> to clean your "+clothing.getClothingType().getSlot().getName()
										+", <b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying "+(clothing.getClothingType().isPlural()?"them":"it")+" in the process</b>.");
							}
						}
					}
				}
			}
			for(InventorySlot slotToClean : slotsToClean) {
				target.removeDirtySlot(slotToClean);
			}
			
			if(target.isPlayer()) {
				return sb.toString();
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some parts of your body have been covered in cum, milk or other sexual fluids."
						+ " You find yourself incredibly embarrassed to be walking around in such a filthy state.";
			} else {
				return UtilText.parse(target, "Some parts of [npc.namePos] body have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly embarrassed to be walking around in such a filthy state.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (!isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	},
	
	BODY_CUM_MASOCHIST(
			80,
			"dirty body",
			"dirtyBodyMasochist",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return StatusEffect.BODY_CUM.applyEffect(target, minutesPassed);
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Some parts of your body have been covered in cum, milk or other sexual fluids."
						+ " You find yourself feeling incredibly turned on by walking around in such a filthy state.";
			} else {
				return UtilText.parse(target, "Some parts of [npc.namePos] body have been covered in cum, milk or other sexual fluids."
						+ " [npc.sheIs] feeling incredibly turned on by walking around in such a filthy state.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return (isCumEffectPositive(target)) && !target.getDirtySlots().isEmpty();
		}
	},
	
	CLOTHING_JINXED(
			80,
			"jinxed clothing",
			"arcaneDrain",
			Colour.ATTRIBUTE_CORRUPTION,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer()) {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.jinxedClothingDiscovered)) {
					Main.game.getDialogueFlags().values.add(DialogueFlagValue.jinxedClothingDiscovered);
					AbstractClothing clothing = null;
					for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
						if(c.isSealed()) {
							clothing = c;
							break;
						}
					}
					if(!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)) {
						return "<p>"
									+ "As you finish fitting the "+clothing.getName()+" in place, you start to feel a strange warmth radiating from "+(clothing.getClothingType().isPlural()?"their":"its")+" surface."
									+ " Feeling a little uneasy about the manner of arcane enchantment that "+(clothing.getClothingType().isPlural()?"they":"it")+" must contain, you immediately try to take "
										+(clothing.getClothingType().isPlural()?"them":"it")+" off."
								+ "</p>"
								+ "<p>"
									+ "Taking hold of the "+clothing.getName()+", nothing seems to be wrong at first, but as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off, you find out that you've made a big mistake."
									+ " A jolt of arcane energy suddenly flashes up through your body, and as the invasive force shoots its way into your mind, you find yourself unwittingly releasing your grip."
								+ "</p>"
								+ "<p>"
									+ "Gritting your teeth, you try once again to remove the offending article of clothing, only to find yourself instantly letting go as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off."
									+ " No matter how much you struggle, all you're able to do is move the "+clothing.getName()
										+" around a little, and whenever it looks to be in danger of being removed, it moves back into its proper position with a mind of its own."
								+ "</p>"
								+ "<p>"
									+ "Eventually giving up, you decide to go and ask Lilaya what's going on with "+(clothing.getClothingType().isPlural()?"these":"this")
										+" <b style='color:"+Colour.RARITY_JINXED.toWebHexString()+";'>jinxed</b> "+clothing.getName()+"."
									+ " Maybe she'll know a way to break the seal?"
								+ "</p>"
								+(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY)?Main.game.getPlayer().startQuest(QuestLine.SIDE_ENCHANTMENT_DISCOVERY):"");
					
					} else {
						return "<p>"
									+ "As you finish fitting the "+clothing.getName()+" in place, you start to feel a strange warmth radiating from "+(clothing.getClothingType().isPlural()?"their":"its")+" surface."
									+ " Feeling a little uneasy about the manner of arcane enchantment that "+(clothing.getClothingType().isPlural()?"they":"it")+" must contain, you immediately try to take "
										+(clothing.getClothingType().isPlural()?"them":"it")+" off."
								+ "</p>"
								+ "<p>"
									+ "Taking hold of the "+clothing.getName()+", nothing seems to be wrong at first, but as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off, you find out that you've made a big mistake."
									+ " A jolt of arcane energy suddenly flashes up through your body, and as the invasive force shoots its way into your mind, you find yourself unwittingly releasing your grip."
								+ "</p>"
								+ "<p>"
									+ "Gritting your teeth, you try once again to remove the offending article of clothing, only to find yourself instantly letting go as you try to pull "+(clothing.getClothingType().isPlural()?"them":"it")+" off."
									+ " No matter how much you struggle, all you're able to do is move the "+clothing.getName()
										+" around a little, and whenever it looks to be in danger of being removed, it moves back into its proper position with a mind of its own."
								+ "</p>"
								+ "<p>"
									+ "Lilaya's warning about jinxed clothing suddenly shoots to the forefront of your mind, and you let out a groan as you realise that "+(clothing.getClothingType().isPlural()?"these ":"this ")+clothing.getName()
										+" are <b style='color:"+Colour.RARITY_JINXED.toWebHexString()+";'>jinxed</b>."
									+ " Remembering what Lilaya said, you should be able to remove the jinx if you focus some of your absorbed essences into it..."
								+ "</p>";
					}
					
				} else {
					return "";
				}
			} else {
				return "";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "The enchantment on your jinxed clothing seems to be vampyric in nature. You can feel it draining a little of your arcane aura as it uses your strength to power itself.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			for(AbstractClothing c : target.getClothingCurrentlyEquipped()) {
				if(c.isSealed()) {
					return true;
				}
			}
			return false;
		}
	},
	
	// OTHER:

	WELL_RESTED(
			80,
			"well rested",
			"wellRested",
			Colour.ATTRIBUTE_HEALTH,
			Colour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 20f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 20f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "After having a good rest, you feel full of energy.";
				} else {
					return UtilText.parse(target, "After having a good rest, [npc.name] feels full of energy.");
				}
			} else {
				return "";
			}
		}

		
	},
	
	WELL_RESTED_BOOSTED(
			80,
			"very well rested",
			"wellRestedBoosted",
			Colour.ATTRIBUTE_HEALTH,
			Colour.ATTRIBUTE_MANA,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 50f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 50f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "Thanks to your ability of knowing how to get the most out of a good rest, you now feel extremely full of energy.";
				} else {
					return UtilText.parse(target, "After having a good rest, [npc.name] feels full of energy.");
				}
			} else {
				return "";
			}
		}

		
	},
	
	OVERWORKED(
			80,
			"overworked",
			"overworked",
			Colour.BASE_MAGENTA,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -15f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, -50f)),
			Util.newArrayListOfValues("[style.boldBad(-0.1)] <b style='color: " + Colour.AFFECTION.toWebHexString() + ";'>Affection per hour while at work</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "As a result of working over eight hours a day, you often find yourself feeling tired and lethargic.";
				} else {
					return UtilText.parse(target, "As a result of working over eight hours a day, [npc.Name] often finds [npc.herself] feeling tired and lethargic.");
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isSlave() && target.getSlaveJob()!=SlaveJob.IDLE && target.getTotalHoursWorked()>8;
		}
	},
	
	PSYCHOACTIVE(
			80,
			"Psychoactive Trip",
			"psychoactive",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f)),
			Util.newArrayListOfValues("Open to <b style='color: " + Colour.PSYCHOACTIVE.toWebHexString() + ";'>Hypnotic Suggestion</b>")) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if(target.isPlayer() && Math.random()<=Util.getModifiedDropoffValue(minutesPassed*0.0075f, 0.5f)) {
				
				if(target.getPsychoactiveFluidsIngested().isEmpty()) {
					return "<p>"
								+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
							+ "</p>"
							+ "<p><i>"
							+ UtilText.returnStringAtRandom(
									"Suddenly, you find yourself back in your aunt Lily's museum."
										+ " One of the museum's staff is holding you by the [pc.hand] and pulling you into an empty room."
										+ " Once inside, they drop to their knees, grinning up at you as they lean forwards and plant a wet kiss on your exposed genitals...",
									"Suddenly, you find yourself face-to-face with your aunt Lily; the two of you standing in her old apartment."
										+ " With a giggle, she suddenly reaches down and grabs you by the [pc.hand], before pulling you into her bedroom."
										+ " Once inside, she slips off her dressing gown and steps forwards, pressing her lips against yours...",
									"Suddenly, you find yourself standing before a stunningly-beautiful demon."
										+ " She steps forwards, pressing her naked breasts against your body as her tail snakes up to rub against your groin."
										+ " Letting out a lewd moan, she presses her lips against yours...")
							+"</i></p>"
							+ "<p>"
								+ "With a gasp, you suddenly snap out of the hallucination."
								+ " Blushing, you feel a needy heat spreading throughout your groin..."
							+ "</p>"
							+target.incrementLust(25);
				} else {
					List<FluidType> list = new ArrayList<>(target.getPsychoactiveFluidsIngested());
					FluidType fluid = list.get(Util.random.nextInt(list.size()));
					String npcName = UtilText.generateSingularDeterminer(fluid.getRace().getName())+" "+fluid.getRace().getName();
					switch(fluid.getBaseType()) {
						case CUM:
							return "<p>"
										+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Suddenly, you find yourself back in your aunt Lily's museum."
												+ " You're kneeling before one of the museum's staff, "+npcName+", as you eagerly suck their cock."
												+ " After just a few moments, their shaft starts to twitch, and you let out a delighted moan as a thick stream of cum pours out into your mouth...",
											"Suddenly, you find yourself beside your aunt Lily; the two of you kneeling next to each other in the bedroom of her old apartment."
												+ " She's grinning in delight as she watches you suck the cock of the "+fluid.getRace().getName()+" before you."
												+ " As the thick shaft in your mouth starts to twitch, Lily reaches forwards and holds your head in place, making sure that the hot stream of cum spurts down your throat...",
											"Suddenly, you find yourself kneeling before a completely naked "+fluid.getRace().getName()+"."
												+ " They step forwards, reaching down to pull your head forwards as they force their hard cock into your mouth."
												+ " With a desperate moan, they instantly start cumming, filling your mouth with hot cum...")
									+"</i></p>"
									+ "<p>"
										+ "With a gasp, you suddenly snap out of the hallucination."
										+ " Blushing, you feel a needy heat spreading throughout your groin..."
									+ "</p>"
									+target.incrementLust(25);
						case GIRLCUM:
							return "<p>"
										+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Suddenly, you find yourself back in your aunt Lily's museum."
												+ " You're kneeling before one of the museum's staff, "+npcName+", as you eagerly lick and kiss their dripping-wet pussy."
												+ " After just a few moments, their cunt starts to spasm and twitch, and you let out a delighted moan as you lick up every drop of their delicious girlcum...",
											"Suddenly, you find yourself beside your aunt Lily; the two of you kneeling next to each other in the bedroom of her old apartment."
												+ " She's grinning in delight as she watches you lick and kiss the pussy of the "+fluid.getRace().getName()+" before you."
												+ " As their cunt starts to spasm and twitch, Lily reaches forwards and pushes your head forwards, making sure that you lick up every drop of their delicious girlcum...",
											"Suddenly, you find yourself kneeling before a completely naked "+fluid.getRace().getName()+"."
												+ " They step forwards, reaching down to pull your head forwards as they force their dripping-wet cunt against your [pc.lips+]."
												+ " With a desperate moan, they instantly start cumming, roughly forcing your face into their groin as they make you lick up every drop of their delicious girlcum...")
									+"</i></p>"
									+ "<p>"
										+ "With a gasp, you suddenly snap out of the hallucination."
										+ " Blushing, you feel a needy heat spreading throughout your groin..."
									+ "</p>"
									+target.incrementLust(25);
						case MILK:
							return "<p>"
										+ "You find yourself losing track of where you are as you struggle to overcome the psychoactive effects..."
									+ "</p>"
									+ "<p><i>"
									+ UtilText.returnStringAtRandom(
											"Suddenly, you find yourself back in your aunt Lily's museum."
												+ " You're sitting in the lap of one of the museum's staff, "+npcName+", as you eagerly kiss and suckle their engorged teats."
												+ " After just a few moments, a steady stream of milk starts to flow into your mouth, and you let out a delighted moan as you gulp down the delicious liquid...",
											"Suddenly, you find yourself in the lap of your aunt Lily; the two of you sitting on her bed in her old apartment."
												+ " She's grinning down at you in delight as you kiss and suckle her engorged teats."
												+ " After just a few moments, a steady stream of milk starts to flow into your mouth, and you let out a delighted moan as you gulp down the delicious liquid...",
											"Suddenly, you find yourself sitting in the lap of a completely naked "+fluid.getRace().getName()+"."
												+ " They reach down to pull your head into their huge breasts as they force one of their engorged teats against your [pc.lips+]."
												+ " With a desperate moan, you start kissing and suckling their puffy nipples, and after just a few moments, a steady stream of milk starts to flow into your mouth...")
									+"</i></p>"
									+ "<p>"
										+ "With a gasp, you suddenly snap out of the hallucination."
										+ " Blushing, you feel a needy heat spreading throughout your groin..."
									+ "</p>"
									+target.incrementLust(25);
					}
				}
				return "";
			} else {
				return "";
			}
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You're feeling very peculiar at the moment... Strange visions keep flashing before your [pc.eyes], and you aren't quite sure of your surroundings...";
			} else {
				return UtilText.parse(target, "[npc.name] is feeling very peculiar at the moment... Strange visions keep flashing before [npc.her] [npc.eyes], and [npc.she] doesn't seem to be quite sure of [npc.her] surroundings...");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.removePsychoactiveEffects();
			return "";
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}

		
	},
	
	DRUNK_1(
			80,
			"Intoxicated I - Tipsy",
			"drunk1",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementAlcoholLevel(-(minutesPassed*(1f/(60f*6)))); // alcohol level will completely go after 6 hours
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return ("After recently drinking an alcoholic liquid, you're feeling a little tipsy...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%");
			} else {
				return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.name] is feeling a little tipsy...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%"));
			}
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.ONE_TIPSY;
		}
	},
	
	DRUNK_2(
			80,
			"Intoxicated II - Merry",
			"drunk2",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 20f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -10f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementAlcoholLevel(-(minutesPassed*(1f/(60*6)))); // alcohol level will completely go after 6 hours
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return ("After recently drinking an alcoholic liquid, you're feeling quite merry...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%");
			} else {
				return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.name] is feeling quite merry...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%"));
			}
		}

		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.TWO_MERRY;
		}
	},
	
	DRUNK_3(
			80,
			"Intoxicated III - Drunk",
			"drunk3",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -20f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementAlcoholLevel(-(minutesPassed*(1f/(60*6)))); // alcohol level will completely go after 6 hours
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return ("After recently drinking an alcoholic liquid, you're feeling quite drunk...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%");
			} else {
				return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.name] is feeling quite drunk...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%"));
			}
		}

		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.THREE_DRUNK;
		}
	},
	
	DRUNK_4(
			80,
			"Intoxicated IV - Hammered",
			"drunk4",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -20f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementAlcoholLevel(-(minutesPassed*(1f/(60*6)))); // alcohol level will completely go after 6 hours
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return ("After recently drinking an alcoholic liquid, you're feeling pretty hammered...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%");
			} else {
				return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.name] is feeling pretty hammered...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%"));
			}
		}

		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FOUR_HAMMERED;
		}
	},
	
	DRUNK_5(
			80,
			"Intoxicated V - Wasted",
			"drunk5",
			Colour.BASE_YELLOW,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f),
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -15f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -25f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementAlcoholLevel(-(minutesPassed*(1f/(60*6)))); // alcohol level will completely go after 6 hours
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return ("After recently drinking an alcoholic liquid, you're feeling completely wasted...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%");
			} else {
				return (UtilText.parse(target, "After recently drinking an alcoholic liquid, [npc.name] is feeling completely wasted...<br/>"
						+ "Intoxication: "+target.getIntoxicationPercentage()+"%"));
			}
		}

		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getAlcoholLevel()==AlcoholLevel.FIVE_WASTED;
		}
	},

	PREGNANT_0(
			80,
			"risk of pregnancy",
			"pregnancy0",
			Colour.GENERIC_ARCANE,
			true,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After recently having unprotected sex, there's a risk that you'll get pregnant!"
					+ " Due to the fact that the arcane accelerates people's pregnancies, you'll know if you're pregnant within a matter of hours.";
			} else {
				return UtilText.parse(target,
						"After recently having unprotected sex, there's a risk that "+target.getName("the")+" will get pregnant!"
							+ " Due to the fact that the arcane accelerates people's pregnancies, [npc.she]'ll know if [npc.sheIs] pregnant within a matter of hours.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			
			StringBuilder sb = new StringBuilder();
			
			String inflationText = "";
			
			if (target.isPregnant()) {
				// Remove cum inflation:
				if(target.hasStatusEffect(StatusEffect.CUM_INFLATION_1)
						|| target.hasStatusEffect(StatusEffect.CUM_INFLATION_2)
						|| target.hasStatusEffect(StatusEffect.CUM_INFLATION_3)) {
					inflationText = "<p>"
								+ "[style.italicsSex(The swelling of your pregnant bump forces your body to expel most of the cum that's inflating your belly.)]"
							+ "</p>";
				}
				
				target.addStatusEffect(PREGNANT_1, 60 * (72 + Util.random.nextInt(13)));
				
				if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
					if(target.hasFetish(Fetish.FETISH_PREGNANCY)) {
						sb.append("<p>"
								+ "For the last few hours, your belly has been gradually swelling."
								+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
								+ " You're pregnant."
								+ " You experimentally start stroking your abdomen, making soft little gasps as the realisation of what's happening starts to sink in."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(I-I'm pregnant?"
									+ "<br/>"
									+ "..."
									+ "<br/>"
									+ "Oh my God! Yes! <b>I'm pregnant!</b>)]"
							+ "</p>"
							+ "<p>"
								+ "The overwhelming happiness you feel at not only discovering that you're pregnant, but also that you're showing physical signs after only a few hours, washes over you like a crashing wave of pure ecstasy."
								+ " You feel tears of joy welling up in your eyes as you lovingly cradle your swollen belly."
							+ "</p>"
							+ "<p>"
								+ "[pc.thought(This is the best feeling ever!"
										+ "<br/>"
										+ "If only aunt Lily were here, I bet she'd be so proud!"
										+ "<br/>"
										+ "Wait! Of course! <b>Lilaya!</b> She'll want to see this too!)]"
							+ "</p>"
							+ "<p>"
								+"After a little while of stroking and rubbing your wonderfully-swollen abdomen, you start to calm down a little."
								+ " You decide that you should probably go and see Lilaya, so that she can help you figure out all the details of giving birth."
							+ "</p>"
							+ "<p style='text-align:center;'>"
								+ "<b style='color:"+ Colour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
							+ "</p>");
						
					} else {
						sb.append("<p>"
									+ "For the last few hours, your belly has been gradually swelling."
									+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
									+ " You're pregnant."
									+ " You experimentally start stroking your abdomen, making soft little gasps as the realisation of what's happening starts to sink in."
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(I-I'm pregnant?"
										+ "<br/>"
										+ "..."
										+ "<br/>"
										+ "Oh my God! <b>I'm pregnant!</b>)]"
								+ "</p>"
								+ "<p>"
									+ "The sudden shock of not only discovering that you're pregnant, but also that you're showing physical signs after only a few hours, hits you like a sledgehammer."
									+ " Despite your best efforts, you feel yourself starting to hyperventilate as you walk around in little circles, alternating between cradling your head and stomach."
								+ "</p>"
								+ "<p>"
									+ "[pc.thought(What do I do? What do I do? What do I do?"
											+ "<br/>"
											+ "If only aunt Lily were here!"
											+ "<br/>"
											+ "Wait! Of course! <b>Lilaya!</b> She'll know what to do!)]"
								+ "</p>"
								+ "<p>"
									+ "You start to calm down a little as the initial shock starts to wear off."
									+ " If anyone knows what to do, it'll be Lilaya."
								+ "</p>"
								+ "<p style='text-align:center;'>"
									+ "<b style='color:"+ Colour.GENERIC_SEX.toWebHexString() + ";'>You're pregnant!</b>"
								+ "</p>");
					}
					
				} else {
					sb.append("<p>"
							+ "For the last couple of hours, your belly has been gradually swelling."
							+ " The progress was so slow that you didn't even realise anything was happening, but as you glance down at your stomach, there's no mistaking it."
							+ " You're pregnant again."
							+ " You start stroking your abdomen, making soft little gasps as the familiar feeling of being knocked up returns to you."
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
									?"[pc.thought(Haha! Yes! I got pregnant again! This is the best feeling ever...)]"
									:"[pc.thought(Mmm... Looks like I got pregnant again...)]")
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"Knowing what you're in for, you let out a delighted laugh before carrying on your way."
								:"Knowing what you're in for, you let out a contented sigh and start carrying on your way.")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString()+ ";'>You're pregnant!</b>"
						+ "</p>");
				}
				
			} else {
				target.endPregnancy(false);
				sb.append("<p>"
							+ "Enough time has passed now for you to be sure that you're in the clear."
							+ " There's no sign of any bump in your belly,"
								+" and you realise that despite having unprotected sex, you managed to avoid getting pregnant."
						+ "</p>"
						+ "<p>"
							+ (target.hasFetish(Fetish.FETISH_PREGNANCY)
								?"[pc.thought(Damn it...)]"
								:"[pc.thought(Well, that's a relief...)]")
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You aren't pregnant!</b>"
						+ "</p>");	
			}
			
			if(target.isPlayer()) {
				return sb.toString() + inflationText;
			} else {
				return "";
			}
		}
		
		@Override
		public String applyPostRemovalStatusEffect(GameCharacter target) {
			if(!target.isPregnant()) {
				target.performImpregnationCheck();
			}
			return "";
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	PREGNANT_1(
			80,
			"pregnant",
			"pregnancy1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "From one of your recent sexual encounters, you've been impregnated!"
						+ " Due to the fact that the arcane accelerates people's pregnancies, you'll move onto the next stage in a matter of days.";
			} else {
				return UtilText.parse(target,
							"From one of [npc.namePos] recent sexual encounters, [npc.sheIs] been impregnated!");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.addStatusEffect(PREGNANT_2, 60 * (72 + Util.random.nextInt(13)));
			
			boolean breastGrowth = false;
			if(Main.getProperties().pregnancyBreastGrowth>0 && target.getBreastRawSizeValue()<Main.getProperties().pregnancyBreastGrowthLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyBreastGrowth - Main.getProperties().pregnancyBreastGrowthVariance + Util.random.nextInt(Main.getProperties().pregnancyBreastGrowthVariance*2 + 1));
				
				if(target.getBreastRawSizeValue() + valueIncrease > Main.getProperties().pregnancyBreastGrowthLimit) {
					breastGrowth = true;
					target.setBreastSize(Main.getProperties().pregnancyBreastGrowthLimit);
				} else {
					breastGrowth = true;
					target.incrementBreastSize(valueIncrease);
				}
			}

			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the realisation that your belly has swollen massively."
							+ " You can't resist rubbing your hands over the bump in your abdomen, and you wonder just how big it's going to get."
							+ " As this is your first time getting pregnant, you're not quite sure what to expect, but you're reassured as you remember that Lilaya's always there to help."
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>"
						+(breastGrowth
								? "<p><i>"
										+"Your breasts have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
									+ "</i></p>"
								:"");
			} else {
				return "<p>"
							+ "Even though the change has been gradual, you're suddenly hit by the familiar realisation that your belly has swollen massively."
							+ " You can't resist rubbing your hands over the bump in your abdomen, smiling fondly at the comforting feeling."
							+ " Having been through all this before, you know that you've still got a way to go before you're ready to give birth."
						+ "</p>"
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now heavily pregnant!</b>"
						+ "</p>"
						+(breastGrowth
								? "<p><i>"
										+"Your breasts have swollen and grown larger as your body prepares to start lactating."
										+ " You now have [style.boldSex([pc.breastSize]"  + (target.getBreastRawSizeValue()>CupSize.AA.getMeasurement()?", "+target.getBreastSize().getCupSizeName()+"-cup":"") + " breasts)]!"
									+ "</i></p>"
								:"");
			}
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	PREGNANT_2(
			80,
			"heavily pregnant",
			"pregnancy2",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Your stomach has swollen considerably, making it clearly obvious to anyone who glances your way that you're expecting to give birth soon."
						+ " Due to the fact that the arcane accelerates people's pregnancies, you'll move onto the final stage in a matter of days.";
			} else {
				return UtilText.parse(target,
							"[npc.NamePos] stomach has swollen considerably, making it clearly obvious to anyone who glances [npc.her] way that [npc.sheIs] expecting to give birth soon.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {

			target.setTimeProgressedToFinalPregnancyStage(Main.game.getMinutesPassed());

			boolean lactationIncrease = false;
			if(Main.getProperties().pregnancyLactationIncrease>0 && target.getBreastRawMilkStorageValue()<Main.getProperties().pregnancyLactationLimit) {
				int valueIncrease = Math.max(1, Main.getProperties().pregnancyLactationIncrease - Main.getProperties().pregnancyLactationIncreaseVariance + Util.random.nextInt(Main.getProperties().pregnancyLactationIncreaseVariance*2 + 1));
				
				if(target.getBreastRawMilkStorageValue() + valueIncrease > Main.getProperties().pregnancyLactationLimit) {
					lactationIncrease = true;
					target.setBreastMilkStorage(Main.getProperties().pregnancyLactationLimit);
				} else {
					lactationIncrease = true;
					target.incrementBreastMilkStorage(valueIncrease);
				}
			}
			
			if (!Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_FIRST_TIME_PREGNANCY)) {
				return "<p>"
							+ "By now, your stomach has completely ballooned out in front of you, and you're having to arch your back and support yourself with one hand as you walk around."
							+ " Some time in the last couple of hours, you felt a strange rumble in your pregnant bump, and after panicking for a little while, you realised that it was your offspring kicking about in your womb."
							+ " You keep feeling another kick every now and then, and you know that you're ready to give birth."
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerThought("I really should go and see Lilaya...")
						+ "</p>"
						+(lactationIncrease
								? "<p><i>"
										+"Your breasts have gotten noticeably heavier, and as you softly stroke the round bump in your belly, you feel droplets of [pc.milk] beading up on your engorged teats."
										+ " You are now able to produce [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+target.getBreastRawMilkStorageValue()+"ml))]!"
									+ "</i></p>"
								:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You're now ready to give birth!</b>" 
						+ "</p>";
			} else {
				return "<p>"
							+ "By now, your stomach has completely ballooned out in front of you, and you're having to arch your back and support yourself with one hand as you walk around."
							+ " Some time in the last couple of hours, you felt a familiar rumble in your pregnant bump, and from experience, you instantly recognised that it was your offspring kicking about in your womb."
							+ " You keep feeling another kick every now and then, and you know that you're ready to give birth."
						+ "</p>"
						+ "<p>"
							+ UtilText.parsePlayerThought("I really should go and see Lilaya... Or maybe I'll stay like this for a little while!")
						+ "</p>"
						+(lactationIncrease
								? "<p><i>"
										+"Your breasts have gotten noticeably heavier, and as you softly stroke the round bump in your belly, you feel droplets of [pc.milk] beading up on your engorged teats."
										+ " You are now able to produce [style.boldSex(" + target.getBreastMilkStorage().getDescriptor() + " [pc.milk] ("+target.getBreastRawMilkStorageValue()+"ml))]!"
									+ "<i></p>"
								:"")
						+ "<p style='text-align:center;'>"
							+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString()+ ";'>You're now ready to give birth!</b>"
						+ "</p>";
			}
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	PREGNANT_3(
			80,
			"ready for birthing",
			"pregnancy3",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Your belly has inflated to a colossal size, and you find yourself having to support your back as you walk."
						+ " It might be a good idea to visit Lilaya so that she can help you to give birth.";
			} else {
				return UtilText.parse(target,
							"[npc.NamePos] belly has inflated to a colossal size, and [npc.sheIs] finding that [npc.she] has to support [npc.her] back with one hand as [npc.she] walks.");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			if(target instanceof NPC) {
				((NPC)target).setReactedToPregnancy(false);
			} else {
				if(target.isPlayer()) {
					for(NPC npc : Main.game.getAllNPCs()) {
						npc.setReactedToPlayerPregnancy(false);
					}
				}
			}
			
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPregnant()
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_0)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_1)
					 && !target.hasStatusEffect(StatusEffect.PREGNANT_2);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	VIXENS_VIRILITY(
			80,
			"Vixen's Virility",
			"vixensVirility",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FERTILITY, 50f),
					new Value<Attribute, Float>(Attribute.VIRILITY, 50f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "After consuming a Vixen's Virility pill, your body's fertility and virility have been temporarily boosted.";
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	PROMISCUITY_PILL(
			80,
			"Promiscuity Pill",
			"promiscuityPill",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.FERTILITY, -100f),
					new Value<Attribute, Float>(Attribute.VIRILITY, -100f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "After consuming a Promiscuity Pill, your body's fertility and virility has been temporarily reduced."
					+ " This is a <b>preventative</b> measure, and will not alter the outcome of any unprotected sex you had before taking the pill!";
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	CUM_PRODUCTION(
			80,
			"Cum Production",
			"cumProduction",
			Colour.GENERIC_SEX,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementPenisStoredCum(minutesPassed * target.getPenisCumProductionRegeneration().getPercentageRegen() * target.getPenisRawCumStorageValue());
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = Util.getRoundedFloat(target.getPenisCumProductionRegeneration().getPercentageRegen() * target.getPenisRawCumStorageValue(), 4);
			
			return UtilText.parse(target, "[npc.NamePos] balls are currently producing more [npc.cum], at a rate of "+cumRegenRate+"ml/minute."
					+ " They have stored "+Util.getRoundedFloat(target.getPenisRawStoredCumValue(), 2)+"ml, out of a maximum of "+Util.getRoundedFloat(target.getPenisRawCumStorageValue(), 2)+"ml.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()!=target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.getPlayerKnowsAreas().contains(CoverableArea.PENIS));
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	CUM_FULL(
			80,
			"Full Balls",
			"cumFull",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			float cumRegenRate = Util.getRoundedFloat(target.getPenisCumProductionRegeneration().getPercentageRegen() * target.getPenisRawCumStorageValue(), 4);
			
			return UtilText.parse(target, "[npc.NamePos] balls are completely filled with [npc.cum] ("+target.getPenisRawCumStorageValue()+"ml),"
					+ " and [npc.she] can't wait until the next time [npc.sheIs] able to empty them."
					+ " [npc.She] will ejaculate "+target.getPenisRawOrgasmCumQuantity()+"ml upon orgasm, and will then regenerate [npc.cum] at a rate of "+cumRegenRate+"ml/minute.");
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.getProperties().hasValue(PropertyValue.cumRegenerationContent)
					&& target.getPenisRawCumStorageValue()>0
					&& target.getPenisRawStoredCumValue()==target.getPenisRawCumStorageValue()
					&& target.hasPenisIgnoreDildo()
					&& (target.isPlayer() || target.getPlayerKnowsAreas().contains(CoverableArea.PENIS));
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	MILK_PRODUCTION(
			80,
			"Milk Production",
			"milkProduction",
			Colour.GENERIC_SEX,
			true,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			target.incrementBreastStoredMilk(minutesPassed * target.getBreastLactationRegeneration().getPercentageRegen() * target.getBreastRawMilkStorageValue());
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = Util.getRoundedFloat(target.getBreastLactationRegeneration().getPercentageRegen() * target.getBreastRawMilkStorageValue(), 4);
			
			if(target.isPlayer()) {
				return "Your breasts are currently producing more [pc.milk], at a rate of "+milkRegenRate+"ml/minute."
						+ " They have stored "+Util.getRoundedFloat(target.getBreastRawStoredMilkValue(), 2)+"ml, out of a maximum of "+target.getBreastRawMilkStorageValue()+"ml.";
			} else {
				return UtilText.parse(target,
						"[npc.NamePos] breasts are currently producing more [npc.milk], at a rate of "+milkRegenRate+"ml/minute."
						+ " They have stored "+Util.getRoundedFloat(target.getBreastRawStoredMilkValue(), 2)+"ml, out of a maximum of "+target.getBreastRawMilkStorageValue()+"ml.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0 && target.getBreastRawStoredMilkValue()!=target.getBreastRawMilkStorageValue();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	MILK_FULL(
			80,
			"Full Breasts",
			"milkFull",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -5f)),
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			float milkRegenRate = Util.getRoundedFloat(target.getBreastLactationRegeneration().getPercentageRegen() * target.getBreastRawMilkStorageValue(), 4);
			
			if(target.isPlayer()) {
				return "Your [pc.breasts] are completely filled with [pc.milk] ("+target.getBreastRawMilkStorageValue()+"ml), and your engorged [pc.nipples] are just begging for some attention."
						+ " Once milked, they will produce more [pc.milk] at a rate of "+milkRegenRate+"ml/minute.";
			} else {
				return UtilText.parse(target,
						"[npc.NamePos] [npc.breasts] are completely filled with [npc.milk] ("+target.getBreastRawMilkStorageValue()+"ml), and [npc.her] engorged [npc.nipples] are just begging for some attention..."
								+ " Once milked, they will produce more [npc.milk] at a rate of "+milkRegenRate+"ml/minute.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getBreastRawMilkStorageValue()>0
					&& target.getBreastRawStoredMilkValue()==target.getBreastRawMilkStorageValue();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	RECOVERING_ORIFICE(
			80,
			"Recovering Vagina",
			"recoveringOrifice",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f), new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, -5f)),
			null) {

		@Override
		public String getName(GameCharacter target) {
			int i=0;
			String s="";
			
			// Vagina:
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				s = "Recovering Vagina";
				i++;
				
			// Ass:
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				s = "Recovering Anus";
				i++;
				
			// Nipples:
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				s = "Recovering Nipples";
				i++;
				
			// Urethra:
			}
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				s = "Recovering Urethra";
				i++;
			}
			
			if(i>1)
				return "Recovering Orifices";
			else
				return s;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			
			// Vagina:
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				switch(target.getVaginaElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementVaginaStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementVaginaStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementVaginaStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementVaginaStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementVaginaStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementVaginaStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementVaginaStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementVaginaStretchedCapacity(-100);
						break;
					default:
						break;
				}
			
				if(target.getVaginaStretchedCapacity()<target.getVaginaRawCapacityValue()) {
					target.setVaginaStretchedCapacity(target.getVaginaRawCapacityValue());
				}
			}
			
			// Ass:
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				switch(target.getAssElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementAssStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementAssStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementAssStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementAssStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementAssStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementAssStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementAssStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementAssStretchedCapacity(-100);
						break;
					default:
						break;
				}
				
				if(target.getAssStretchedCapacity()<target.getAssRawCapacityValue())
					target.setAssStretchedCapacity(target.getAssRawCapacityValue());
			}
			
			// Nipples:
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				switch(target.getNippleElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementNippleStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementNippleStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementNippleStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementNippleStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementNippleStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementNippleStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementNippleStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementNippleStretchedCapacity(-100);
						break;
					default:
						break;
				}
				
				if(target.getNippleStretchedCapacity()<target.getNippleRawCapacityValue())
					target.setNippleStretchedCapacity(target.getNippleRawCapacityValue());
			}
			
			// Urethra:
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				switch(target.getUrethraElasticity()){
					//Takes 6 hours to recover each inch of capacity:
					case ZERO_UNYIELDING:
						target.incrementPenisStretchedCapacity(-(1/6f) * (minutesPassed/60f));
						break;
					//Takes 4 hours to recover each inch of capacity:
					case ONE_RIGID:
						target.incrementPenisStretchedCapacity(-(1/4f) * (minutesPassed/60f));
						break;
					//Takes 2 hours to recover each inch of capacity:
					case TWO_FIRM:
						target.incrementPenisStretchedCapacity(-(1/2f) * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case THREE_FLEXIBLE:
						target.incrementPenisStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 1 hour to recover each inch of capacity:
					case FOUR_LIMBER:
						target.incrementPenisStretchedCapacity(-1 * (minutesPassed/60f));
						break;
					//Takes 30 minutes to recover each inch of capacity:
					case FIVE_STRETCHY:
						target.incrementPenisStretchedCapacity(-2 * (minutesPassed/60f));
						break;
					//Takes 15 minutes to recover each inch of capacity:
					case SIX_SUPPLE:
						target.incrementPenisStretchedCapacity(-4 * (minutesPassed/60f));
						break;
					//Should have been instant after sex, this is just a backup:
					case SEVEN_ELASTIC:
						target.incrementPenisStretchedCapacity(-100);
						break;
					default:
						break;
				}
				
				if(target.getPenisStretchedCapacity()<target.getPenisRawCapacityValue())
					target.setPenisStretchedCapacity(target.getPenisRawCapacityValue());
			}
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			
			descriptionSB = new StringBuilder("The following orifices are recovering:");
			
			// Vagina:
			if (target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity()){
				descriptionSB.append("<br/><b>Vagina:</b> From "+Capacity.getCapacityFromValue(target.getVaginaStretchedCapacity()).getDescriptor()+" to "+target.getVaginaCapacity().getDescriptor()+".");
				
			// Ass:
			}
			if (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity()){
				descriptionSB.append("<br/><b>Anus:</b> From "+Capacity.getCapacityFromValue(target.getAssStretchedCapacity()).getDescriptor()+" to "+target.getAssCapacity().getDescriptor()+".");
				
			// Nipples:
			}
			if (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity()){
				descriptionSB.append("<br/><b>Nipples:</b> From "+Capacity.getCapacityFromValue(target.getNippleStretchedCapacity()).getDescriptor()+" to "+target.getNippleCapacity().getDescriptor()+".");
				
			// Urethra:
			}
			if (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()){
				descriptionSB.append("<br/><b>Urethra:</b> From "+Capacity.getCapacityFromValue(target.getPenisStretchedCapacity()).getDescriptor()+" to "+target.getPenisCapacity().getDescriptor()+".");
			}
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex() &&
					((target.hasVagina() && target.getVaginaRawCapacityValue()!=target.getVaginaStretchedCapacity())
					|| (target.getAssRawCapacityValue()!=target.getAssStretchedCapacity())
					|| (target.getNippleRawCapacityValue()!=target.getNippleStretchedCapacity())
					|| (target.getPenisRawCapacityValue()!=target.getPenisStretchedCapacity()));
		}
	},
	
	
	
	CREAMPIE_VAGINA(
			80,
			"Pussy Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerMinute(target)*minutesPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
					sb.append("<p>"
								+ "The cum from your creampied pussy quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
				target.addDirtySlot(InventorySlot.VAGINA);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.VAGINA, cumLost);
			
			return sb.toString();
		}

		@Override
		public String getDescription(GameCharacter target) {
			int cumLost = SexAreaOrifice.VAGINA.getCharactersCumLossPerMinute(target);
			
			if(target.isOrificePlugged(SexAreaOrifice.VAGINA)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used pussy.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.VAGINA)+"ml)]<br/>"
							+ "[style.boldTerrible(Plugged Vagina:)] No cum is leaking out (although some is still being absorbed)!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.asshole] has recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.VAGINA)+"ml)]<br/>"
							+ "[style.boldTerrible(Plugged Vagina:)] No cum is leaking out (although some is still being absorbed)!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel slimy cum drooling out of your recently-used pussy.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.VAGINA)+"ml)]<br/>"
							+ "(-"+cumLost+"ml/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.pussy] has recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.VAGINA)+"ml)]<br/>"
							+ "(-"+cumLost+"ml/minute)");
				}
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.VAGINA)>0;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.VAGINA);
		}
	},
	
	CREAMPIE_VAGINA_URETHRA(
			80,
			"Vaginal Urethra Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerMinute(target) * minutesPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.VAGINA)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).setDirty(true);
					sb.append("<p>"
								+ "Cum leaks out of your pussy's creampied urethra, quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.VAGINA).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.VAGINA)) {
				target.addDirtySlot(InventorySlot.VAGINA);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_VAGINA, cumLost);
			
			return sb.toString();
		}

		@Override
		public String getDescription(GameCharacter target) {
			int cumLost = SexAreaOrifice.URETHRA_VAGINA.getCharactersCumLossPerMinute(target);
			
			if(target.isPlayer()) {
				return "As you walk, you can feel slimy cum drooling out of your pussy's recently-used urethra.<br/>"
						+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA)+"ml)]<br/>"
						+ "(-"+cumLost+"ml/minute)";
			} else {
				return UtilText.parse(target, 
						"[npc.NamePos] pussy's urethra has recently been filled with cum.<br/>"
						+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA)+"ml)]<br/>"
						+ "(-"+cumLost+"ml/minute)");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_VAGINA)>0;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_VAGINA);
		}
	},
	
	CREAMPIE_PENIS_URETHRA(
			80,
			"Penis Urethra Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerMinute(target) * minutesPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.PENIS)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.PENIS).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.PENIS).setDirty(true);
					sb.append("<p>"
								+ "Cum leaks out of your cock's creampied urethra, quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirtying</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.PENIS).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.PENIS)) {
				target.addDirtySlot(InventorySlot.PENIS);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.URETHRA_PENIS, cumLost);
			
			return sb.toString();
		}

		@Override
		public String getDescription(GameCharacter target) {
			int cumLost = SexAreaOrifice.URETHRA_PENIS.getCharactersCumLossPerMinute(target);
			
			if(target.isPlayer()) {
				return "As you walk, you can feel slimy cum drooling out of your cock's recently-used urethra.<br/>"
						+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS)+"ml)]<br/>"
						+ "(-"+cumLost+"ml/minute)";
			} else {
				return UtilText.parse(target, 
						"[npc.NamePos] cock's urethra has recently been filled with cum.<br/>"
						+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS)+"ml)]<br/>"
						+ "(-"+cumLost+"ml/minute)");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.URETHRA_PENIS)>0;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.URETHRA_PENIS);
		}
	},
	
	CREAMPIE_ANUS(
			80,
			"Anal Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int cumLost = SexAreaOrifice.ANUS.getCharactersCumLossPerMinute(target) * minutesPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.ANUS)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.ANUS).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.ANUS).setDirty(true);
					sb.append("<p>"
								+ "The cum from your creampied asshole quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.ANUS).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.ANUS)) {
				target.addDirtySlot(InventorySlot.ANUS);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.ANUS, cumLost);
			
			return sb.toString();
		}

		@Override
		public String getDescription(GameCharacter target) {
			int cumLost = SexAreaOrifice.ANUS.getCharactersCumLossPerMinute(target);

			if(target.isOrificePlugged(SexAreaOrifice.ANUS)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used asshole.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.ANUS)+"ml)]<br/>"
							+ "[style.boldTerrible(Plugged Anus:)] No cum is leaking out (although some is still being absorbed)!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.asshole] has recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.ANUS)+"ml)]<br/>"
							+ "[style.boldTerrible(Plugged Anus:)] No cum is leaking out (although some is still being absorbed)!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used asshole.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.ANUS)+"ml)]<br/>"
							+ "(-"+cumLost+"ml/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.asshole] has recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.ANUS)+"ml)]<br/>"
							+ "(-"+cumLost+"ml/minute)");
				}
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.ANUS)>0;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.ANUS);
		}
	},
	
	CREAMPIE_NIPPLES(
			80,
			"Nipple Creampie",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			Util.newArrayListOfValues(
					"<b style='color: " + Colour.ATTRIBUTE_CORRUPTION.toWebHexString() + "'>Dirties clothing</b>")) {
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			attributeModifiersList.addAll(this.getExtraEffects(target));
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int cumLost = SexAreaOrifice.NIPPLE.getCharactersCumLossPerMinute(target) * minutesPassed;
			
			StringBuilder sb = new StringBuilder();
			
			if(target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES)!=null){
				if(!target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).isDirty()) {
					target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).setDirty(true);
					sb.append("<p>"
								+ "The cum from your creampied nipples quickly </b><b style='color:"+Colour.CUM.toWebHexString()+";'>dirties</b> your "+target.getLowestZLayerCoverableArea(CoverableArea.NIPPLES).getName()+"!"
							+ "</p>");
				}
			}
			
			if(!target.getDirtySlots().contains(InventorySlot.NIPPLE)) {
				target.addDirtySlot(InventorySlot.NIPPLE);
			}
			
			target.drainTotalFluidsStored(SexAreaOrifice.NIPPLE, cumLost);
			
			return sb.toString();
		}

		@Override
		public String getDescription(GameCharacter target) {
			int cumLost = SexAreaOrifice.NIPPLE.getCharactersCumLossPerMinute(target);
			
			if(target.isOrificePlugged(SexAreaOrifice.NIPPLE)) {
				if(target.isPlayer()) {
					return "As you walk, you can feel the cum trapped within your recently-used nipples.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)+"ml)]<br/>"
							+ "[style.boldTerrible(Plugged Nipples:)] No cum is leaking out (although some is still being absorbed)!";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.nipples] have recently been filled with cum, before being plugged.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)+"ml)]<br/>"
							+ "[style.boldTerrible(Plugged Nipples:)] No cum is leaking out (although some is still being absorbed)!");
				}
				
			} else {
				if(target.isPlayer()) {
					return "As you walk, you can feel cum drooling out of your recently-used nipples.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)+"ml)]<br/>"
							+ "(-"+cumLost+"ml/minute)";
				} else {
					return UtilText.parse(target, 
							"[npc.NamePos] [npc.nipples] have recently been filled with cum.<br/>"
							+ "Current creampie: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)+"ml)]<br/>"
							+ "(-"+cumLost+"ml/minute)");
				}
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.NIPPLE)>0;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.NIPPLE);
		}
	},
	
	CREAMPIE_MOUTH(
			80,
			"Cummy Meal",
			"creampie",
			Colour.CUM,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f)),
			null) {
		
		@Override
		public String getName(GameCharacter target) {
			return target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"Cummy Meal"
					:"Yummy Meal";
		}
		
		@Override
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			if(isCumEffectPositive(target)) {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 1f));
				
			} else {
				return Util.newHashMapOfValues(
						new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -1f));
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> attributeModifiersList = attributeModifiersToStringList(getAttributeModifiers(target));
			
			if(this.getExtraEffects(target)!=null) {
				attributeModifiersList.addAll(this.getExtraEffects(target));
			}
			
			return attributeModifiersList;
		}
		
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			int cumLost = SexAreaOrifice.MOUTH.getCharactersCumLossPerMinute(target) * minutesPassed;
			
			target.drainTotalFluidsStored(SexAreaOrifice.MOUTH, cumLost);
			
			return "";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, 
					target.isOnlyCumInArea(SexAreaOrifice.MOUTH)
					?"[npc.NamePos] recently swallowed a load of cum.<br/>"
						+ "Current cum in stomach: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.MOUTH)+"ml)]<br/>"
						+ "(-2ml/minute)"
					:"[npc.NamePos] recently swallowed some sexual fluids.<br/>"
						+ "Current fluids in stomach: [style.colourSex("+target.getTotalFluidInArea(SexAreaOrifice.MOUTH)+"ml)]<br/>"
						+ "(-2ml/minute)");
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getTotalFluidInArea(SexAreaOrifice.MOUTH)>0;
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getCreampieSVGString(owner, SexAreaOrifice.MOUTH);
		}
	},
	
	
	CUM_INFLATION_1(
			80,
			"swollen belly",
			"cumInflation1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a considerable amount of cum, your belly is now a little swollen."
						+ " Your extra weight makes it a little more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a considerable amount of cum, [npc.namePos] belly is now a little swollen.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	CUM_INFLATION_2(
			80,
			"inflated belly",
			"cumInflation2",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a huge amount of cum, your belly is now noticeably inflated."
						+ " The considerable amount of extra weight in your stomach makes it more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a huge amount of cum, [npc.namePos] belly is now noticeably inflated."
							+ " The considerable amount of extra weight in [npc.her] stomach is hindering [npc.her] ability to move.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	CUM_INFLATION_3(
			80,
			"over-inflated belly",
			"cumInflation3",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a colossal amount of cum, your belly is now massively over-inflated."
						+ " The huge amount of extra weight in your stomach is making it extremely difficult for you to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a colossal amount of cum, [npc.namePos] belly is now massively over-inflated."
									+ " The huge amount of extra weight in [npc.her] stomach is making it extremely difficult for [npc.herHim] to move around.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.ANUS) + target.getTotalFluidInArea(SexAreaOrifice.MOUTH) + target.getTotalFluidInArea(SexAreaOrifice.VAGINA);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	
	BREAST_CUM_INFLATION_1(
			80,
			"swollen breasts",
			"cumInflationBreasts1",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -2f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a considerable amount of cum, your [pc.breasts] are now a little swollen."
						+ " Your extra weight makes it a little more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a considerable amount of cum, [npc.namePos] [npc.breasts] are now a little swollen.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMinimumValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	BREAST_CUM_INFLATION_2(
			80,
			"inflated breasts",
			"cumInflationBreasts2",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a huge amount of cum, your [pc.breasts] are now noticeably inflated."
						+ " The considerable amount of extra weight in your top-half is making it more difficult to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a huge amount of cum, [npc.namePos] [npc.breasts] are now noticeably inflated."
							+ " The considerable amount of extra weight in [npc.her] top-half is hindering [npc.her] ability to move.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMedianValue()
					&& cumAmount < CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	BREAST_CUM_INFLATION_3(
			80,
			"over-inflated breasts",
			"cumInflationBreasts3",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "After being filled with a colossal amount of cum, your [pc.breasts] are now massively over-inflated."
						+ " The huge amount of extra weight in your top-half is making it extremely difficult for you to move around.";
			} else {
				return UtilText.parse(target,
							"After being filled with a colossal amount of cum, [npc.namePos] [npc.breasts] are now massively over-inflated."
									+ " The huge amount of extra weight in [npc.her] top-half is making it extremely difficult for [npc.herHim] to move around.");
			}
		}

		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			int cumAmount = target.getTotalFluidInArea(SexAreaOrifice.NIPPLE);
			return cumAmount >= CumProduction.SEVEN_MONSTROUS.getMaximumValue()
					&& Main.getProperties().hasValue(PropertyValue.inflationContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	
	FRUSTRATED_NO_ORGASM(
			80,
			"Frustrated",
			"frustrated",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, -10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You've recently had a sexual encounter in which you didn't manage to cum."
						+ " As a result, you're feeling extremely horny and frustrated...";
			} else {
				return UtilText.parse(target, "[npc.Name] recently had a sexual encounter in which [npc.she] didn't manage to cum."
						+ " As a result, [npc.sheIs] feeling extremely horny and frustrated...");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	PENT_UP_SLAVE(
			80,
			"Pent-up",
			"frustrated",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return UtilText.parse(target, "[npc.Name] hasn't had any sexual relief for over a day now, and is feeling extremely pent-up...");
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer() && target.isSlave() && (target.getOwner()!=null && target.getOwner().isPlayer()) && ((NPC)target).getLastTimeOrgasmed()+60*24<Main.game.getMinutesPassed();
		}
	},
	
	RECOVERING_AURA(
			80,
			"Strengthened aura",
			"recoveringAura",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Due to a recent orgasm, your arcane aura has been temporarily strengthened."
						+ " While in this state, you will no longer receive an arcane essence if you orgasm!";
			} else {
				return UtilText.parse(target, "Due to a recent orgasm, [npc.namePos] arcane aura has been temporarily strengthened."
						+ " While [npc.she] remains in this state, you will not receive an arcane essence if [npc.she] orgasms in your presence!");
			}
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	EXPOSED(
			80,
			"exposed",
			"clothingExposed",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -4f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't cover [npc.her] private parts, and [npc.she] feels highly embarrassed to be walking around in such an exposed fashion.");
			}
			return "Your clothing doesn't cover your private parts, and you feel highly embarrassed to be walking around in such an exposed fashion.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)); 
		}
	},
	
	EXPOSED_BREASTS(
			80,
			"exposed breasts",
			"clothingExposed",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -2f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't cover [npc.her] breasts, and [npc.she] feels highly embarrassed to be walking around in such an exposed fashion.");
			}
			return "Your clothing doesn't cover your breasts, and you feel highly embarrassed to be walking around in such an exposed fashion.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !(target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)); 
		}
	},
	
	EXPOSED_PLUS_BREASTS(
			80,
			"exposed",
			"clothingExposed",
			Colour.GENERIC_BAD,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, -6f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.NamePos] breasts and private parts are naked for the world to see, and [npc.she] feels highly embarrassed to be walking around in such an exposed fashion.");
			}
			return "Your breasts and private parts are naked for the world to see, and you feel highly embarrassed to be walking around in such an exposed fashion.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& !target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)); 
		}
	},
	
	FETISH_EXHIBITIONIST(
			80,
			"exhibitionist",
			"clothingExposed",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 8f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't cover [npc.her] private parts, and [npc.she] feels incredibly sexy every time [npc.she] catches someone staring at [npc.her] exposed groin.");
			}
			return "Your clothing doesn't cover your private parts, and you feel incredibly sexy every time you catch someone staring at your exposed groin.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& (target.hasFetish(Fetish.FETISH_EXHIBITIONIST)// Exhibitionist
							&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
									|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
									|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
						&& !((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)));
		}
	},
	FETISH_EXHIBITIONIST_BREASTS(
			80,
			"exhibitionist",
			"clothingExposed",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 4f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.NamePos] clothing doesn't cover [npc.her] breasts, and [npc.she] feels incredibly sexy every time [npc.she] catches someone staring at [npc.her] exposed chest.");
			}
			return "Your clothing doesn't cover your breasts, and you feel incredibly sexy every time you catch someone staring at your exposed chest.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& (target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& !(target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)));
		}
	},
	FETISH_EXHIBITIONIST_PLUS_BREASTS(
			80,
			"exhibitionist",
			"clothingExposed",
			Colour.GENERIC_SEX,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 12f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null && !target.isPlayer()) {
				return UtilText.parse(target, "[npc.NamePos] breasts and private parts are naked for the world to see, and [npc.she] feels incredibly sexy as [npc.she] walks around with all of [npc.her] goods on display.");
			}
			return "Your breasts and private parts are naked for the world to see, and you feel incredibly sexy as you walk around with all of your goods on display.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !Main.game.isInSex()
					&& (target.hasFetish(Fetish.FETISH_EXHIBITIONIST)
					&& (target.isCoverableAreaExposed(CoverableArea.ANUS)
							|| (target.isCoverableAreaExposed(CoverableArea.PENIS) && target.getPenisType() != PenisType.NONE)
							|| (target.isCoverableAreaExposed(CoverableArea.VAGINA) && target.getVaginaType() != VaginaType.NONE))
					&& ((target.hasBreasts() || target.isFeminine()) && target.isCoverableAreaExposed(CoverableArea.NIPPLES)));
		}
	},

	FETISH_PURE_VIRGIN(
			80,
			"pure virgin",
			"virginPure",
			Colour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 25f), new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You represent the perfect image of a pure, righteous being. Your noble bearing and virtuous personality mark you as a paragon of all that is angelic and good in this world.";
			} else {
				return UtilText.parse(target,
						"[npc.Name] represents the perfect image of a pure, righteous being. [npc.Her] noble bearing and virtuous personality mark [npc.herHim] as a paragon of all that is angelic and good in this world.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN) && !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	FETISH_PURE_VIRGIN_LUSTY_MAIDEN(
			80,
			"lusty maiden",
			"virginPureLustyMaiden",
			Colour.GENERIC_EXCELLENT,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You're more than willing to use your ass, mouth, breasts, and even the promise of your pussy in order to please your partners, but you'll never let anyone actually penetrate your feminine sex and take your precious virginity!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is more than willing to use [npc.her] ass, mouth, breasts, and even the promise of [npc.her] pussy in order to please [npc.her] partners,"
								+ " but [npc.she]'ll never let anyone actually penetrate [npc.her] feminine sex and take [npc.her] precious virginity!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	FETISH_BROKEN_VIRGIN(
			80,
			"broken virgin",
			"virginBroken",
			Colour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f),
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 50f), new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Losing your virginity has hit you hard. All you can think of is big, thick cocks breaking you in like a worthless slut, before defiling your womb with their foul cum..."
						+ " Without your virginity, all you can see yourself as is a cheap sex toy.";
			} else {
				return UtilText.parse(target,
						"Losing [npc.her] virginity has hit [npc.name] hard. All [npc.she] can think of is big, thick cocks breaking [npc.herHim] in like a worthless slut, before defiling [npc.her] womb with their foul cum..."
								+ " Without [npc.her] virginity, all [npc.she] can see [npc.herself] as is a cheap sex toy.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_PURE_VIRGIN) && !target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && !target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	FETISH_BROKEN_VIRGIN_LUSTY_MAIDEN(
			80,
			"broken maiden",
			"virginBrokenLustyMaiden",
			Colour.GENERIC_TERRIBLE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, -50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -50f),
					new Value<Attribute, Float>(Attribute.MAJOR_CORRUPTION, 50f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Losing your virginity has hit you hard. All of your efforts to deflect attention away from your pussy and to your other assets was wasted, and now you can only see yourself as a cheap sex toy, to be used by anyone.";
			} else {
				return UtilText.parse(target,
						"Losing [npc.her] virginity has hit [npc.name] hard. All [npc.she] can think of is big, thick cocks breaking [npc.herHim] in like a worthless slut, before defiling [npc.her] womb with their foul cum..."
								+ " Without [npc.her] virginity, all [npc.she] can see [npc.herself] as is a cheap sex toy.");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.hasFetish(Fetish.FETISH_LUSTY_MAIDEN) && target.hasVagina() && !target.isVaginaVirgin();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},

	// CLOTHING SETS:
	// (attribute bonuses are handled in the ClothingSet enum, leave all
	// attribute modifiers as null)
	SET_MAID(
			70,
			"Hard-working Maid",
			"set_maid",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Maid's outfit, you are filled with the energy you need in order to be a sexy hard-working maid.";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Maid's outfit, [npc.name] is filled with the energy [npc.she] needs in order to be a sexy hard-working maid.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.MAID.isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_MAID, true);
		}
	},
	
	SET_MAID_BOOSTED(
			70,
			"Professional Maid",
			"set_maidBoosted",
			Colour.CLOTHING_BLACK,
			Colour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Maid's outfit, you are reminded of your true profession; that of an exceptionally talented maid!";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Maid's outfit, [npc.name] is reminded of [npc.her] true profession; that of an exceptionally talented maid!");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.MAID.isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_MAID, true);
		}
	},
	
	SET_BUTLER(
			70,
			"Butler",
			"set_butler",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Butler's outfit, you are filled with the energy you need in order to carry out your duties as a butler.";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.name] is filled with the energy [npc.she] needs in order to carry out [npc.her] duties as a butler.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.BUTLER.isCharacterWearingCompleteSet(target) && !target.hasTrait(Perk.JOB_BUTLER, true);
		}
	},
	
	SET_BUTLER_BOOSTED(
			70,
			"Professional Butler",
			"set_butlerBoosted",
			Colour.CLOTHING_WHITE,
			Colour.BASE_GOLD,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 25f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Butler's outfit, you are reminded of your true profession; that of an exceptionally talented butler!";
					
				} else {
					return UtilText.parse(target, "By wearing the entire Butler's outfit, [npc.name] is reminded of [npc.her] true profession; that of an exceptionally talented butler!");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.BUTLER.isCharacterWearingCompleteSet(target) && target.hasTrait(Perk.JOB_BUTLER, true);
		}
	},
	
	SET_WITCH(
			70,
			"Arcane Witch",
			"set_witch",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SPELLS, 10f),
					new Value<Attribute, Float>(Attribute.SPELL_COST_MODIFIER, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the complete set of witch's clothes, you feel your arcane power growing stronger.";
					
				} else {
					return UtilText.parse(target, "By wearing the complete set of witch's clothes, [npc.namePos] arcane power has grown stronger.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.WITCH.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_SCIENTIST(
			70,
			"Scientist",
			"set_scientist",
			Colour.CLOTHING_BLACK,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By donning both a lab coat and safety goggles, you're confident that no chemical spill will harm you!";
					
				} else {
					return UtilText.parse(target, "By wearing both a lab coat and safety goggles, [npc.name] is well-protected against any chemical spill.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.SCIENTIST.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_MILK_MAID(
			70,
			"Milk Maid",
			"set_milk_maid",
			Colour.BASE_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f), new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the entire Milk Maid's outfit, you're filled with the energy you need in order to perform all of your milking duties!";
				} else {
					return UtilText.parse(target, "By wearing the entire Milk Maid's outfit, [npc.name] is filled with the energy [npc.she] needs in order to perform all of [npc.her] milking duties.");
				}
				
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.MILK_MAID.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_ENFORCER(
			70,
			"enforcer's uniform",
			"set_enforcer",
			Colour.CLOTHING_WHITE,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 10f), new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 15f)), null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing an Enforcer's uniform, you gain the energy and strength you need to fight crime.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing an Enforcer's uniform, granting [npc.herHim] the energy and strength [npc.she] needs to fight crime.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.ENFORCER.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_RAINBOW(
			70,
			"double rainbow",
			"set_rainbow",
			Colour.CLOTHING_MULTICOLOURED,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "Double rainbow... What does it mean?!";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.RAINBOW.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_BDSM(
			70,
			"BDSM",
			"set_bdsm",
			Colour.CLOTHING_WHITE,
			false,
			Util.newHashMapOfValues( new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, -15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You have been tied up in bondage gear, and are struggling to move.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] has been tied up in bondage gear, and is struggling to move.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.BDSM.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_CATTLE(
			70,
			"Cattle",
			"set_cattle",
			Colour.BASE_TAN,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You are wearing a set of accessories normally found on a cow or bull.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing a set of accessories normally found on a cow or bull.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.CATTLE.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_SNOWFLAKE(
			70,
			"Snowflake",
			"set_snowflake",
			Colour.BASE_BLUE_LIGHT,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By donning snowflake jewels, you're confident that no ice will harm you!";
					
				} else {
					return UtilText.parse(target, "By wearing snowflake jewels, [npc.name] is well-protected against any ice attacks.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.SNOWFLAKE.isCharacterWearingCompleteSet(target);
		}
	},
		
	SET_SUN(
			70,
			"Sun",
			"set_sun",
			Colour.BASE_ORANGE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 5f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By donning jewels that sparkle like the sun, you're confident that no fire will harm you!";
					
				} else {
					return UtilText.parse(target, "By wearing the sun, [npc.name] is well-protected against any fire attacks.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.SUN.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_GEISHA(
			70,
			"Geisha",
			"set_geisha",
			Colour.BASE_ROSE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "You are wearing a kitsune's ceremonial outfit, which closely resembles traditional Japanese clothing.";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing a kitsune's ceremonial outfit, which closely resembles traditional Japanese clothing.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.GEISHA.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_RONIN(
			70,
			"Ronin",
			"set_ronin",
			Colour.BASE_ROSE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 15f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<i>You understand there is something outside yourself that has to be served. And when that need is gone, when belief has died, what are you? [pc.A_man] without a master.</i>";
					
				} else {
					return UtilText.parse(target,
							"<i>[npc.Name] understands there is something outside [npc.herself] that has to be served. And when that need is gone, when belief has died, what is [npc.she]? [npc.A_man] without a master.</i>");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.RONIN.isCharacterWearingCompleteSet(target);
		}
	},
	
	SET_JOLNIR(
			70,
			"J&oacute;lnir",
			"set_jolnir",
			Colour.BASE_BLACK,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 25f),
					new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f)),
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "By wearing the outfit of the 'Yule figure', both your wisdom and prowess in battle are greatly increased!";
					
				} else {
					return UtilText.parse(target, "[npc.Name] is wearing the outfit of the 'Yule figure', thereby greatly increasing [npc.her] wisdom and prowess in battle.");
					
				}
			} else {
				return "";
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return ClothingSet.JOLNIR.isCharacterWearingCompleteSet(target);
		}
	},
	
	CLOTHING_EFFECT(
			70,
			"clothing effects",
			"combatHidden",
			Colour.TRANSFORMATION_GENERIC,
			false,
			null,
			null) {

		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			return "-";
		}

		@Override
		public String getDescription(GameCharacter target) {
			return "-";
		}

		
		
		@Override
		public boolean renderInEffectsPanel() {
			return false;
		}
	},
	
	POTION_EFFECTS(
			80,
			"potion effects",
			"potionEffects",
			Colour.GENERIC_ARCANE,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "After drinking a potion, you are now experiencing some effects...";
			else
				return UtilText.parse(target, "After drinking a potion, [npc.name] is now experiencing some effects...");
		}
		
//		potionAttributes
		public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
			return target.getPotionAttributes();
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			
			if (getAttributeModifiers(target) != null) {
				for (Entry<Attribute, Float> e : getAttributeModifiers(target).entrySet())
					modifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
			}
					
			return modifiersList;
		}
		
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			target.clearPotionAttributes();
			return "";
		}

		
	},
	
	HAPPINESS(
			70,
			"happiness",
			"happinessFox",
			Colour.CLOTHING_SILVER,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.HEALTH_MAXIMUM, 5f),
					new Value<Attribute, Float>(Attribute.MANA_MAXIMUM, 5f)),
			Util.newArrayListOfValues(
					"[style.italicsGood(Happiness!)]")) {

		@Override
		public String getDescription(GameCharacter target) {
			return "The silver-furred fox, <i>Happiness</i>, is following you around wherever you go."
					+ " Whenever you feel tired or down, the cute little animal brushes up against your [pc.legs] and sits still to have its ears scratched, which instantly fills you with happiness.";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isPlayer() && Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness);
		}
	},
	
	// Combat bonuses:
	
	COMBAT_BONUS_CAT_MORPH(
			80,
			"cat-morph intuition",
			"combatBonusCatMorph",
			Colour.RACE_CAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_CAT_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_CAT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how cat-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how cat-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_COW_MORPH(
			80,
			"cow-morph intuition",
			"combatBonusCowMorph",
			Colour.RACE_COW_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_COW_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_COW_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how cow-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how cow-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_DOG_MORPH(
			80,
			"dog-morph intuition",
			"combatBonusDogMorph",
			Colour.RACE_DOG_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_DOG_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_DOG_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how dog-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how dog-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_HORSE_MORPH(
			80,
			"horse-morph intuition",
			"combatBonusHorseMorph",
			Colour.RACE_HORSE_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HORSE_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_HORSE_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how horse-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how horse-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_HUMAN(
			80,
			"human intuition",
			"combatBonusHuman",
			Colour.RACE_HUMAN,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_HUMAN, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_HUMAN, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how humans will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how humans will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_SQUIRREL_MORPH(
			80,
			"squirrel-morph intuition",
			"combatBonusSquirrelMorph",
			Colour.RACE_SQUIRREL_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_SQUIRREL_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_SQUIRREL_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how squirrel-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how squirrel-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_RAT_MORPH(
			80,
			"rat-morph intuition",
			"combatBonusRatMorph",
			Colour.RACE_RAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RAT_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_RAT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how rat-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how rat-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_RABBIT_MORPH(
			80,
			"rabbit-morph intuition",
			"combatBonusRabbitMorph",
			Colour.RACE_RAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 5f),
					new Value<Attribute, Float>(Attribute.DAMAGE_RABBIT_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_RABBIT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how rabbit-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how rabbit-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_BAT_MORPH(
			80,
			"bat-morph intuition",
			"combatBonusBatMorph",
			Colour.RACE_BAT_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_ARCANE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_BAT_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_BAT_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how bat-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how bat-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_ALLIGATOR_MORPH(
			80,
			"alligator-morph intuition",
			"combatBonusAlligatorMorph",
			Colour.RACE_ALLIGATOR_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_ALLIGATOR_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ALLIGATOR_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how alligator-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how alligator-morphs will behave.");
			}
		}
		
	},
	
	COMBAT_BONUS_WOLF_MORPH(
			80,
			"wolf-morph intuition",
			"combatBonusWolfMorph",
			Colour.RACE_WOLF_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_WOLF_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_WOLF_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how wolf-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how wolf-morphs will behave.");
			}
		}
	},
	
	COMBAT_BONUS_FOX_MORPH(
			80,
			"fox-morph intuition",
			"combatBonusFoxMorph",
			Colour.RACE_FOX_MORPH,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.MAJOR_PHYSIQUE, 2f),
					new Value<Attribute, Float>(Attribute.DAMAGE_FOX_MORPH, 25f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FOX_MORPH, 25f)),
			null) {		@Override
		public String getDescription(GameCharacter target) {
			if(target == null) {
				return "";
			}
			if (target.isPlayer()) {
				return "After absorbing a specially-enchanted arcane essence, you find that you're able to accurately predict how fox-morphs will behave.";
			} else {
				return UtilText.parse(target, "After absorbing a specially-enchanted arcane essence, [npc.name] is able to accurately predict how fox-morphs will behave.");
			}
		}
	},
	

	// COMBAT EFFECTS:
	
	COMBAT_HIDDEN(
			70,
			"hidden",
			"combatHidden",
			Colour.GENERIC_BAD,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			return "You don't know what perks, status effects, spells, or special attacks your opponent has available. You require the "+Perk.OBSERVANT.getName(target)+" perk to reveal such information.";
		}

		
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	DESPERATE_FOR_SEX(
			70,
			"desperate for sex",
			"desperateForSex",
			Colour.ATTRIBUTE_LUST,
			false,
			null,
			Util.newArrayListOfValues(
					"Incoming <b style='color:"+Colour.ATTRIBUTE_LUST.toWebHexString()+";'>Lust damage</b> dealt as"
							+ " <b style='color:"+Colour.ATTRIBUTE_HEALTH.toWebHexString()+";'>2*Energy damage</b>"
							+ " and <b style='color:"+Colour.ATTRIBUTE_MANA.toWebHexString()+";'>1*Aura damage</b>",
					"<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + "'>Incoming damage ignores all resistances</b>")) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "You are absolutely desperate for sex, but thanks to the strength of your arcane aura, you are able to resist giving up right here on the spot!";
			} else {
				return UtilText.parse(target,
						"[npc.Name] is absolutely desperate for sex, but thanks to the strength of [npc.her] arcane aura, [npc.sheIs] able to resist giving up right here on the spot!");
			}
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.getLust()>=100 && !target.isVulnerableToLustLoss();
		}
	},

	// From spells (still in combat):
	
	ARCANE_WEAKNESS(//TODO
			10,
			"arcane weakness",
			"negativeCombatEffect",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, -10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your head is spinning and you're struggling to stay upright.";
			else
				return UtilText.parse(target,
						target.getName("The") + "'s head is spinning and [npc.sheIs] struggling to stay upright..");
		}

		
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	DAZED(
			10,
			"dazed",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -25f), new Value<Attribute, Float>(Attribute.CRITICAL_CHANCE, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "Your head is spinning and you're struggling to stay upright. You're finding it incredibly difficult to land a hit on your opponent or dodge one of their attacks.";
			else
				return UtilText.parse(target,
						target.getName("The") + "'s head is spinning and [npc.sheIs] struggling to stay upright. [npc.sheIs] finding it incredibly difficult to land a hit on you or dodge one of your attacks.");
		}

		
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	CRIPPLE(
			10,
			"crippled",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_PHYSICAL, -15f)),
			null) {
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You've been temporarily crippled, and you're struggling to do as much damage with your attacks as you're usually able to.";
			else
				return UtilText.parse(target,
						target.getName("The") + "'s been temporarily crippled, and [npc.sheIs] struggling to do as much damage with [npc.her] attacks as [npc.sheIs] usually able to.");
		}

		
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	VULNERABLE(
			10,
			"vulnerable",
			"negativeCombatEffect",
			Colour.DAMAGE_TYPE_PHYSICAL,
			false,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -15f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer())
				return "You're feeling particularly vulnerable, and aren't able to defend yourself to the best of your ability.";
			else
				return UtilText.parse(target, "[npc.Name] is feeling particularly vulnerable, and [npc.she] isn't able to defend [npc.herself] to the best of [npc.her] ability.");
		}

		
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	WITCH_SEAL(
		10,
		"Witch's Seal",
		"combat_witch_seal",
		Colour.GENERIC_ARCANE,
		false,
		null,
		null) {
		@Override
		public String applyEffect(GameCharacter target, int minutesPassed) {
			if (target.isPlayer()) {
				return "The <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Witch's Seal</b> is preventing you from making a move!";
				
			} else {
				return UtilText.parse(target,
						"The <b style='color:" + Colour.GENERIC_ARCANE.toWebHexString() + ";'>Witch's Seal</b> is preventing [npc.name] from making a move!");
			}
		}
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A powerful arcane seal is holding you firmly in place, preventing you from taking any action!";
				
			} else {
				return UtilText.parse(target, "A powerful arcane seal is holding [npc.name] firmly in place, preventing [npc.herHim] from taking any action!");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
		@Override
		public boolean isStun() {
			return true;
		}
	},
	
	WITCH_CHARM(
			10,
			"Bewitching Charm",
			"combat_witch_charm",
			Colour.GENERIC_SEX,
			true,
			Util.newHashMapOfValues(new Value<Attribute, Float>(Attribute.DAMAGE_LUST, 25f)),
			null) {
			@Override
			public String applyEffect(GameCharacter target, int minutesPassed) {
				if (target.isPlayer()) {
					return "The <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Bewitching Charm</b> is making you appear irresistibly attractive!";
					
				} else {
					return UtilText.parse(target,
							"The <b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>Bewitching Charm</b> is making [npc.name] appear irresistibly attractive!");
				}
			}
			@Override
			public String getDescription(GameCharacter target) {
				if (target.isPlayer()) {
					if(target.isFeminine()) {
						return "An arcane enchantment is bewitching anyone who looks upon you, causing them to view you as the most beautiful person they've ever seen.";
					} else {
						return "An arcane enchantment is bewitching anyone who looks upon you, causing them to view you as the most handsome person they've ever seen.";
					}
					
				} else {
					if(target.isFeminine()) {
						return UtilText.parse(target, "An arcane enchantment is bewitching you into viewing [npc.name] as the most beautiful person you've ever seen.");
					} else {
						return UtilText.parse(target, "An arcane enchantment is bewitching you into viewing [npc.name] as the most handsome person you've ever seen.");
					}
				}
			}
			@Override
			public boolean isConditionsMet(GameCharacter target) {
				return false;
			}
			@Override
			public boolean isCombatEffect() {
				return true;
			}
		},
	
	TELEPATHIC_COMMUNICATION_POWER_OF_SUGGESTION_TARGETED(
			10,
			"Power of Suggestion",
			"telepathic_communication_power_of_suggestion_targeted",
			Colour.DAMAGE_TYPE_LUST,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -25f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "The telepathic moans and suggestions that have been projected into your mind are causing you to lower your guard!";
			} else {
				return UtilText.parse(target,
						"The telepathic moans and suggestions that have been projected into [npc.namePos] mind are causing [npc.herHim] to lower [npc.her] guard!");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ARCANE_DUALITY_POSITIVE(
			10,
			"Arcane Duality (Defence)",
			"cleanse_positive",
			Colour.GENERIC_ARCANE,
			true,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, 10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, 10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A protective barrier of arcane energy has surrounded you, shielding you from all types of incoming damage.";
			} else {
				return UtilText.parse(target,
						"A protective barrier of arcane energy has surrounded [npc.name], shielding [npc.herHim] from all types of incoming damage.");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	ARCANE_DUALITY_NEGATIVE(
			10,
			"Arcane Duality (Weakness)",
			"cleanse_negative",
			Colour.GENERIC_ARCANE,
			false,
			Util.newHashMapOfValues(
					new Value<Attribute, Float>(Attribute.RESISTANCE_PHYSICAL, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_LUST, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_FIRE, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_ICE, -10f),
					new Value<Attribute, Float>(Attribute.RESISTANCE_POISON, -10f)),
			null) {
		
		@Override
		public String getDescription(GameCharacter target) {
			if (target.isPlayer()) {
				return "A weakening barrier of arcane energy has surrounded you, making you more vulnerable to all types of incoming damage.";
			} else {
				return UtilText.parse(target,
						"A weakening barrier of arcane energy has surrounded [npc.name], making [npc.herHim] more vulnerable to all types of incoming damage.");
			}
		}
		
		@Override
		public boolean isCombatEffect() {
			return true;
		}
	},
	
	// SEX EFFECTS:
	
	CONDOM_WORN(
			80,
			"Wearing a condom",
			"condom",
			Colour.CLOTHING_PINK_LIGHT,
			false,
			null,
			null) {
		
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return -0.5f;
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			modifiersList.clear();
			
			modifiersList.add("-0.5% <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>arousal/turn</b>");
					
			return modifiersList;
		}

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer())
				return "Because you're wearing a condom, sex doesn't feel quite as good...";
			else
				return "Because [npc.sheIs] wearing a condom, sex doesn't feel quite as good for [npc.name]...";
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return target.isWearingCondom();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	DESIRES(
			80,
			"Desires",
			"desires",
			Colour.GENERIC_ARCANE,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "";
				
			} else if(Main.game.isInSex()) {
				GameCharacter targetedCharacter = Sex.getTargetedPartner(target);
				SexType preference = Sex.isInForeplay()?((NPC)target).getForeplayPreference(targetedCharacter):((NPC)target).getMainSexPreference(targetedCharacter);
				return UtilText.parse(target, targetedCharacter, "Due to the underlying power of your arcane aura, you can sense [npc.namePos] non-neutral preferences towards sexual actions."
						+ "<br/>"
						+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"
						+ (preference!=null
								?"[npc.Name] wants to use [npc.her] "+preference.getPerformingSexArea().getName(target)+" and [npc2.namePos] "+preference.getTargetedSexArea().getName(Sex.getTargetedPartner(target))+"."
								:"")
						+ "</i>");
				
			} else {
				return UtilText.parse(target, "Due to the underlying power of your arcane aura, you can sense [npc.namePos] non-neutral preferences towards sexual actions."
						+ "<br/>"
						+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>"
								+ "You can detect what areas [npc.name] wants to use when in sex..."
						+ "</i>");
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();
			List<Fetish> orderedFetishList = new ArrayList<>();
			
			for(Fetish f : Fetish.values()) {
				FetishDesire desire = target.getFetishDesire(f);
				if(desire!=FetishDesire.TWO_NEUTRAL) {
					orderedFetishList.add(f);
				}
			}
			orderedFetishList.sort((e1, e2) -> target.getFetishDesire(e2).compareTo(target.getFetishDesire(e1)));

			for(Fetish f : orderedFetishList) {
				FetishDesire desire = target.getFetishDesire(f);
				modList.add("<b style='color:"+desire.getColour().toWebHexString()+";'>"+Util.capitaliseSentence(desire.getNameAsVerb())+"</b>: "+Util.capitaliseSentence(f.getShortDescriptor()));
			}
			
			return modList;
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return !target.isPlayer();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	ORGASM_COUNTER(
			80,
			"Orgasms",
			"sexEffects/orgasms",
			Colour.GENERIC_ARCANE,
			false,
			null,
			null) {

		@Override
		public String getDescription(GameCharacter target) {
			if(target.isPlayer()) {
				return "Anyone with a strong arcane aura, such as yours, doesn't suffer from any sort of refractory period after orgasming...";
			} else {
				return UtilText.parse(target, "Anyone in the presence of a strong arcane aura, such as yours, doesn't suffer from any sort of refractory period after orgasming...");
			}
		}
		
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			List<String> modList = new ArrayList<>();

			Colour orgasmColour = Colour.GENERIC_ARCANE;
			if(Sex.getNumberOfOrgasms(target)<RenderingEngine.orgasmColours.length) {
				orgasmColour = RenderingEngine.orgasmColours[Sex.getNumberOfOrgasms(target)];
			}
			
			modList.add("<b style='color:"+orgasmColour.toWebHexString()+";'>"+Sex.getNumberOfOrgasms(target)+"</b> Orgasms");
			
			return modList;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			SVGImageSB = new StringBuilder();

			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGString+"</div>");
			
			int orgasms = Sex.getNumberOfOrgasms(owner);
			
			SVGImageSB.append("<div style='width:40%;height:40%;position:absolute; top:0; right:4px;'>");
				if(orgasms == 0) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterZero());
				} else if(orgasms == 1) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterOne());
				} else if(orgasms == 2) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterTwo());
				} else if(orgasms == 3) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterThree());
				} else if(orgasms == 4) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFour());
				} else if(orgasms == 5) {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFive());
				} else {
					SVGImageSB.append(SVGImages.SVG_IMAGE_PROVIDER.getCounterFivePlus());
				}
			SVGImageSB.append("</div>");
			
			return SVGImageSB.toString();
		}
		
		@Override
		public String extraRemovalEffects(GameCharacter target) {
			return "";
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
	},
	
	PENIS_STATUS(//Just used for handjobs
			95,
			"Penis status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {
		
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getPenetrationArousalPerTurn(target, SexAreaPenetration.PENIS);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
//			Map<GameCharacter, Set<SexAreaInterface>> contactingAreas = Sex.getContactingSexAreas(self, SexAreaPenetration.PENIS);
//			if(contactingAreas!=null) {
//				if(contactingAreas.get(target)!=null && !contactingAreas.get(target).isEmpty()) {
//					for(SexAreaInterface area : contactingAreas.get(target)) {
//						if(!area.isOrifice()) {
//							return ((SexAreaPenetration)area).getBaseArousalWhenPenetrating();
//						}
//					}
//				}
//			}
			return 0;
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getPenetrationModifiersAsStringList(target, SexAreaPenetration.PENIS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaPenetration type = SexAreaPenetration.PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a handjob</b>!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>frotting</b> with [npc2.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a tail-job</b>!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a tentacle-job</b>!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a blowjob</b>!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-frotting</b> with [npc2.name]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] giving [npc2.name] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>a foot-job</b>!"));
								break;
						}
						
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No ongoing action.</b>");
			}

			appendPenetrationAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.penis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex()
					&& Sex.getOngoingActionsMap(target)!=null
					&& !Sex.getContactingSexAreas(target, SexAreaPenetration.PENIS).isEmpty()
					&& !Collections.disjoint(
							Sex.getContactingSexAreas(target, SexAreaPenetration.PENIS).get(Sex.getCharacterContactingSexArea(target, SexAreaPenetration.PENIS).get(0)),
							Util.newArrayListOfValues(SexAreaPenetration.values()));
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaPenetration.PENIS, SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis());
		}
	},
	
	ANUS_STATUS(
			96,
			"Anus status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {
		
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ANUS);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ANUS);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ANUS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ANUS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.asshole]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.asshole]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.asshole]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.asshole]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on [npc2.name]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.asshole]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>foot-fucking</b> [npc2.namePos] [npc2.asshole]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on [npc2.name]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.asshole]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.asshole]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ANUS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus());
		}
	},

	ASS_STATUS(
			96,
			"Ass status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {
		
		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.ASS);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.ASS);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.ASS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.ASS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.ass]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>hotdogging</b> [npc2.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-hotdogging</b> [npc2.name]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-hotdogging</b> [npc2.name]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc2.namePos] [npc2.ass]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-hotdogging</b> [npc2.name]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.ass] with [npc.her] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc2.namePos] [npc2.ass]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>hotdogging</b> [npc2.name]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.ass]"), descriptionSB);
			
			descriptionSB.append("</p>");

			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.ASS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAss());
		}
	},
	
	MOUTH_STATUS(
			99,
			"Mouth status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.MOUTH);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.MOUTH);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.MOUTH);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.MOUTH;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.fingers]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>blowjob</b>!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.tail]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] tentacle!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.clit]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc.namePos] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>anilingus</b> on [npc.name]!"));
								break;
							case ASS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>licking</b> [npc.namePos] [npc.ass]!"));
								break;
							case BREAST:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.namePos] [npc.breasts]!"));
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.name]!"));
								break;
							case NIPPLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>sucking</b> [npc.namePos] [npc.nipples]!"));
								break;
							case THIGHS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc.namePos] [npc.legs]!"));
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] giving [npc.name] a <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>blowjob</b>!"));
								break;
							case URETHRA_VAGINA:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc.name]!"));
								break;
							case VAGINA:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc.name]!"));
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] mouth"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.MOUTH, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth());
		}
	},
	
	BREAST_STATUS(
			98,
			"Breast status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public String getName(GameCharacter owner) {
			if(owner.hasBreasts()) {
				return "Breast status";
			} else {
				return "Chest status";
			}
		}

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.BREAST);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.BREAST);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.BREAST);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.BREAST;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.breasts]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.breasts]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.namePos] [npc.clit]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.breasts] with [npc.her] [npc.feet]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.breasts]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target,
										"[npc2.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>"+(entry.getKey().hasBreasts()?"paizuri":"naizuri")+"</b> on [npc.name]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.breasts]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.BREAST, owner.hasBreasts()?SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts():SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat());
		}
	},
	
	NIPPLE_STATUS(
			97,
			"Nipple status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.NIPPLE);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.NIPPLE);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.NIPPLE);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.NIPPLE;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.nipples]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.nipples]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.nipples]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && target.isBreastFuckableNipplePenetration() && Main.getProperties().hasValue(PropertyValue.nipplePenContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.NIPPLE, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple());
		}
	},
	
	URETHRA_PENIS_STATUS(
			97,
			"Penis Urethra status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_PENIS);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_PENIS);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_PENIS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_PENIS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.urethraPenis]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraPenis]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.urethraPenis]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && target.isUrethraFuckable() && Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_PENIS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis());
		}
	},
	
	URETHRA_VAGINA_STATUS(
			97,
			"Vaginal Urethra status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.URETHRA_VAGINA);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.URETHRA_VAGINA);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.URETHRA_VAGINA);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.URETHRA_VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.urethraVagina]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.urethraVagina]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.urethraVagina]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex() && target.isVaginaUrethraFuckable() && Main.getProperties().hasValue(PropertyValue.urethralContent);
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.URETHRA_VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina());
		}
	},
	
	VAGINA_STATUS(
			95,
			"Pussy status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.VAGINA);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.VAGINA);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.VAGINA);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.VAGINA;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fingering</b> [npc2.namePos] [npc2.pussy]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.pussy]!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] [npc2.pussy]!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] [npc2.pussy]!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc2.name]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] [npc2.pussy]!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.toes]</b> into [npc2.namePos] [npc2.pussy]!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] performing <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>cunnilingus</b> on [npc2.name]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] [npc2.pussy]!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}
			
			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] [npc.pussy]"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			if(Main.game.isInSex()) {
				return target.hasVagina();
				
			} else {
				return false;
			}
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.VAGINA, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina());
		}
	},
	
	THIGH_STATUS(
			95,
			"Thigh status",
			null,
			Colour.GENERIC_SEX,
			false,
			null,
			null) {

		@Override
		public float getArousalPerTurnSelf(GameCharacter target) {
			return getOrificeArousalPerTurnSelf(target, SexAreaOrifice.THIGHS);
		}

		@Override
		public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
			return getOrificeArousalPerTurnPartner(self, target, SexAreaOrifice.THIGHS);
		}
				
		@Override
		public List<String> getModifiersAsStringList(GameCharacter target) {
			return getOrificeModifiersAsStringList(target, SexAreaOrifice.THIGHS);
		}

		@Override
		public String getDescription(GameCharacter target) {
			descriptionSB = new StringBuilder();
			SexAreaOrifice type = SexAreaOrifice.THIGHS;

			descriptionSB.append("<p style='text-align:center; padding:0;margin:0;'>");
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(target, type).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						switch((SexAreaPenetration)sArea) {
							case FINGER:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>groping</b> [npc2.namePos] [npc2.legs]!"));
								break;
							case PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] thighs!"));
								break;
							case TAIL:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tail-fucking</b> [npc2.namePos] thighs!"));
								break;
							case TENTACLE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>tentacle-fucking</b> [npc2.namePos] thighs!"));
								break;
							case TONGUE:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.legs]!"));
								break;
							case CLIT:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>clit-fucking</b> [npc2.namePos] thighs!"));
								break;
							case TOES:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>pushing [npc.her] [npc.feet]</b> between [npc2.namePos] thighs!"));
								break;
						}
						
					} else if(sArea.isOrifice()) {
						switch((SexAreaOrifice)sArea) {
							case ANUS:
								break;
							case ASS:
								break;
							case BREAST:
								break;
							case MOUTH:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>kissing</b> [npc2.namePos] [npc2.legs]!"));
								break;
							case NIPPLE:
								break;
							case THIGHS:
								break;
							case URETHRA_PENIS:
								descriptionSB.append(UtilText.parse(entry.getKey(), target, "[npc.NameIsFull] <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>fucking</b> [npc2.namePos] thighs!"));
								break;
							case URETHRA_VAGINA:
								break;
							case VAGINA:
								break;
						}
					}
				}
			}
			if(Sex.getContactingSexAreas(target, type).isEmpty()) {
				descriptionSB.append("<b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No penetration.</b>");
			}

			appendOrificeAdditionGenericDescriptions(target, type, UtilText.parse(target, "[npc.NamePos] thighs"), descriptionSB);
			
			descriptionSB.append("</p>");
			
			return descriptionSB.toString();
		}

		@Override
		public boolean isConditionsMet(GameCharacter target) {
			return Main.game.isInSex();
		}
		
		@Override
		public boolean isSexEffect() {
			return true;
		}
		
		@Override
		public String getSVGString(GameCharacter owner) {
			return getOrificeSVGString(owner, SexAreaOrifice.THIGHS, SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs());
		}
	};

	private int renderingPriority;
	private String name;
	private Colour colourShade;
	private boolean beneficial;
	private Map<Attribute, Float> attributeModifiers;

	protected String SVGString;

	protected List<String> extraEffects;

	protected List<String> modifiersList;
	
	private static StringBuilder descriptionSB, SVGImageSB;

	private StatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority, name, pathName, colourShade, colourShade, colourShade, beneficial, attributeModifiers, extraEffects);
	}
	
	private StatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			Colour colourShadeSecondary,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		this(renderingPriority, name, pathName, colourShade, colourShadeSecondary, colourShade, beneficial, attributeModifiers, extraEffects);
	}
	
	private StatusEffect(int renderingPriority,
			String name,
			String pathName,
			Colour colourShade,
			Colour colourShadeSecondary,
			Colour colourShadeTertiary,
			boolean beneficial,
			Map<Attribute, Float> attributeModifiers,
			List<String> extraEffects) {
		
		this.renderingPriority = renderingPriority;
		this.name = name;
		this.beneficial = beneficial;
		this.attributeModifiers = attributeModifiers;
		this.colourShade = colourShade;

		if(extraEffects == null) {
			this.extraEffects = new ArrayList<>();
		} else {
			this.extraEffects = extraEffects;
		}
		
		if(pathName!=null && !pathName.isEmpty()) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/statusEffects/" + pathName + ".svg");
				if(is==null) {
					System.err.println("Error! StatusEffect icon file does not exist (Trying to read from '"+pathName+"')!");
				}
				SVGString = Util.colourReplacement(this.toString(), colourShade, colourShadeSecondary, colourShadeTertiary, Util.inputStreamToString(is));
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			SVGString = "";
		}

		modifiersList = attributeModifiersToStringList(attributeModifiers);
	}
	
	protected List<String> attributeModifiersToStringList(Map<Attribute, Float> attributeMap) {
		List<String> attributeModifiersList = new ArrayList<>();
		
		if (attributeMap != null) {
			for (Entry<Attribute, Float> e : attributeMap.entrySet()) {
				attributeModifiersList.add("<b>" + (e.getValue() > 0 ? "+" : "") + e.getValue() + "</b>" + " <b style='color: " + e.getKey().getColour().toWebHexString() + ";'>" + Util.capitaliseSentence(e.getKey().getAbbreviatedName()) + "</b>");
			}
		}
		
		return attributeModifiersList;
	}
	
	public String applyEffect(GameCharacter target, int minutesPassed) {
		return "";
	}

	/**
	 * @param target
	 * @return True if this status effect should be applied to the target.
	 *  False if conditions are not met <b>or</b> this status effect is only for timed purposes (i.e. the only time it should be applied is with a time condition.).
	 */
	public boolean isConditionsMet(GameCharacter target) {
		return false;
	}
	
	public boolean renderInEffectsPanel() {
		return true;
	}
	
	public boolean isCombatEffect() {
		return false;
	}
	
	public boolean isSexEffect() {
		return false;
	}
	
	public float getArousalPerTurnSelf(GameCharacter self) {
		return 0;
	}
	
	public float getArousalPerTurnPartner(GameCharacter self, GameCharacter target) {
		return 0;
	}

	public final String applyRemoveStatusEffect(GameCharacter target) {
		reverseStatusEffectAttributeModifiers(target);
		return extraRemovalEffects(target);
	}
	
	protected String extraRemovalEffects(GameCharacter target){
		return "";
	}
	
	private void reverseStatusEffectAttributeModifiers(GameCharacter target) {
		if (getAttributeModifiers(target) != null)
			for (Entry<Attribute, Float> e : getAttributeModifiers(target).entrySet())
				target.incrementBonusAttribute(e.getKey(), -e.getValue());
	}
	
	public String applyPostRemovalStatusEffect(GameCharacter target) {
		return "";
	}

	public int getRenderingPriority() {
		return renderingPriority;
	}

	public String getName(GameCharacter target) {
		return name;
	}

	public abstract String getDescription(GameCharacter target);

	public List<String> getModifiersAsStringList(GameCharacter target) {
		ArrayList<String> fullModList = new ArrayList<>(modifiersList);
		fullModList.addAll(getExtraEffects(target));
		return fullModList;
	}
	
	public boolean isBeneficial() {
		return beneficial;
	}
	
	public boolean isStun() {
		return false;
	}

	public Map<Attribute, Float> getAttributeModifiers(GameCharacter target) {
		return attributeModifiers;
	}

	public Colour getColour() {
		return colourShade;
	}

	public List<String> getExtraEffects(GameCharacter target) {
		return extraEffects;
	}

	public String getSVGString(GameCharacter owner) {
		return SVGString;
	}
	
	// Helper methods for sex effects:
	
	public static float getPenetrationArousalPerTurn(GameCharacter target, SexAreaPenetration penetration) {
		float arousal = 0;
		
		if(!Sex.getContactingSexAreas(target, penetration).isEmpty()) {
			arousal+=penetration.getBaseArousalWhenPenetrating();
			
			if(!Sex.hasLubricationTypeFromAnyone(target, penetration)) {
				arousal += penetration.getArousalChangePenetratingDry();
			}
		}
		
		return arousal;
	}
	
	public List<String> getPenetrationModifiersAsStringList(GameCharacter target, SexAreaPenetration penetration) {
		modifiersList.clear();
		
		String targetName = target.isPlayer()?"your":UtilText.parse(target, "[npc.namePos]");

		if(!Sex.getContactingSexAreas(target, penetration).isEmpty()
				&& !Collections.disjoint(Sex.getContactingSexAreas(target, penetration).get(Sex.getCharacterContactingSexArea(target, penetration).get(0)), Util.newArrayListOfValues(SexAreaPenetration.values()))) {
			
			modifiersList.add("+"+penetration.getBaseArousalWhenPenetrating()
				+" <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+targetName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
			
			if(!Sex.hasLubricationTypeFromAnyone(target, penetration)) {
				modifiersList.add(penetration.getArousalChangePenetratingDry()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+targetName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Dry</b>)");
			}
			
			for(GameCharacter penetrator : Sex.getContactingSexAreas(target, penetration).keySet()) {
				String penetratorName = penetrator.isPlayer()?"your":UtilText.parse(penetrator, "[npc.namePos]");
				
				if(!Sex.getContactingSexAreas(target, penetration).isEmpty()) {
					modifiersList.add(
							"+0 <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+penetratorName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
				}
			}
			
		} else {
			modifiersList.add("[style.colourDisabled(No bonuses)]");
		}
		return modifiersList;
	}
	
	public static float getOrificeArousalPerTurnSelf(GameCharacter target, SexAreaOrifice orifice) {
		float arousal = 0;
		
		if(!Sex.getContactingSexAreas(target, orifice).isEmpty()) {
			arousal+=orifice.getBaseArousalWhenPenetrated();
			
			if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedStretching();
			}
			if(Sex.getAreasTooLoose(target).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratedTooLoose();
			}
			if(!Sex.hasLubricationTypeFromAnyone(target, orifice)) {
				arousal += orifice.getArousalChangePenetratedDry();
			}
		}
		
		return arousal;
	}
	
	public float getOrificeArousalPerTurnPartner(GameCharacter self, GameCharacter target, SexAreaOrifice orifice) {
		float arousal = 0;
		
		if(Sex.getContactingSexAreas(self, orifice).containsKey(target)) {
			for(SexAreaInterface sArea : Sex.getContactingSexAreas(self, orifice).get(target)) {
				if(sArea.isPenetration()) {
					arousal+=((SexAreaPenetration)sArea).getBaseArousalWhenPenetrating();
				} else if(sArea.isOrifice()) {
					arousal+=((SexAreaOrifice)sArea).getBaseArousalWhenPenetrated();
				}
			}
			
			if(Sex.getAreasCurrentlyStretching(self).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratingStretching();
			}
			if(Sex.getAreasTooLoose(self).contains(orifice)) {
				arousal += orifice.getArousalChangePenetratingTooLoose();
			}
			if(!Sex.hasLubricationTypeFromAnyone(self, orifice)) {
				arousal += orifice.getArousalChangePenetratingDry();
			}
		}
		
		return arousal;
	}
	
	public List<String> getOrificeModifiersAsStringList(GameCharacter target, SexAreaOrifice orifice) {
		modifiersList.clear();
		
		String targetName = target.isPlayer()?"your":UtilText.parse(target, "[npc.namePos]");

		if(!Sex.getContactingSexAreas(target, orifice).isEmpty()) {
			modifiersList.add("+"+orifice.getBaseArousalWhenPenetrated()
				+" <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+targetName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
			
			if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedStretching()>0?"+":"")+orifice.getArousalChangePenetratedStretching()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+targetName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Stretching</b>)");
			}
			if(Sex.getAreasTooLoose(target).contains(orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedTooLoose()>0?"+":"")+orifice.getArousalChangePenetratedTooLoose()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+targetName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Too loose</b>)");
			}
			if(!Sex.hasLubricationTypeFromAnyone(target, orifice)) {
				modifiersList.add((orifice.getArousalChangePenetratedDry()>0?"+":"")+orifice.getArousalChangePenetratedDry()
						+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+targetName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Dry</b>)");
			}
			
			for(GameCharacter penetrator : Sex.getContactingSexAreas(target, orifice).keySet()) {
				String penetratorName = penetrator.isPlayer()?"your":UtilText.parse(penetrator, "[npc.namePos]");
				
				if(!Sex.getContactingSexAreas(target, orifice).isEmpty()) {
					modifiersList.add("+"+getOrificeArousalPerTurnPartner(target, penetrator, orifice)
							+" <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+penetratorName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Sex</b>)");
					
					if(Sex.getAreasCurrentlyStretching(target).contains(orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingStretching()>0?"+":"")+orifice.getArousalChangePenetratingStretching()
								+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+penetratorName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>Tight</b>)");
					}
					if(Sex.getAreasTooLoose(target).contains(orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingTooLoose()>0?"+":"")+orifice.getArousalChangePenetratingTooLoose()
								+ "<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+penetratorName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Too loose</b>)");
					}
					if(!Sex.hasLubricationTypeFromAnyone(target, orifice)) {
						modifiersList.add((orifice.getArousalChangePenetratingDry()>0?"+":"")+orifice.getArousalChangePenetratingDry()
								+ " <b style='color: " + Colour.GENERIC_SEX.toWebHexString() + "'>"+penetratorName+" arousal/turn</b> (<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + "'>Dry</b>)");
					}
				}
			}
			
		} else {
			modifiersList.add("[style.colourDisabled(No bonuses)]");
		}
		return modifiersList;
	}
	
	public void appendPenetrationAdditionGenericDescriptions(GameCharacter owner, SexAreaPenetration penetration, String penetrationName, StringBuilder stringBuilderToAppendTo) {
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, penetration)) {
			stringBuilderToAppendTo.append("<br/>"+penetrationName+" "+(penetration.isPlural()?"are":"is")+" <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>dry</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/>"+penetrationName+" "+(penetration.isPlural()?"have":"has")+" been <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>lubricated</b> by:<br/>");
			int i=0;
			List<String> lubricants = new ArrayList<>();
			for(GameCharacter lubricantProvidor : Sex.getAllParticipants()) {
				for(LubricationType lt : Sex.getWetAreas(owner).get(penetration).get(lubricantProvidor)) {
					if(i==0) {
						lubricants.add(lubricantProvidor==null
								?Util.capitaliseSentence(lt.getName(lubricantProvidor))
								:UtilText.parse(lubricantProvidor, "[npc.NamePos] "+lt.getName(lubricantProvidor)));
					} else {
						lubricants.add(lubricantProvidor==null
								?lt.getName(lubricantProvidor)
								:UtilText.parse(lubricantProvidor, "[npc.namePos] "+lt.getName(lubricantProvidor)));
					}
					i++;
				}
			}
			stringBuilderToAppendTo.append(Util.stringsToStringList(lubricants, false)+".");
		}
		stringBuilderToAppendTo.append("</p>");
	}
	
	public void appendOrificeAdditionGenericDescriptions(GameCharacter owner, SexAreaOrifice orificeType, String orificeName, StringBuilder stringBuilderToAppendTo) {
		
		if(Sex.getAreasCurrentlyStretching(owner).contains(orificeType)) {
			if(Sex.getFirstContactingSexAreaPenetration(owner, orificeType)==null) {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" has been <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			} else {
				stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" being <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			}
			
		} else if(Sex.getAreasTooLoose(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>too loose</b>!");
			
		} else if(Sex.getAreasStretched(owner).contains(orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" has been <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>stretched</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/><b style='color:"+Colour.TEXT_GREY.toWebHexString()+";'>No stretch effect.</b>");
		}
		
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, orificeType)) {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"are":"is")+" <b style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>dry</b>!");
			
		} else {
			stringBuilderToAppendTo.append("<br/>"+orificeName+" "+(orificeType.isPlural()?"have":"has")+" been <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>lubricated</b> by:<br/>");
			int i=0;
			List<String> lubricants = new ArrayList<>();
			for(GameCharacter lubricantProvidor : Sex.getAllParticipants()) {
				for(LubricationType lt : Sex.getWetAreas(owner).get(orificeType).get(lubricantProvidor)) {
					if(i==0) {
						lubricants.add(lubricantProvidor==null
								?Util.capitaliseSentence(lt.getName(lubricantProvidor))
								:UtilText.parse(lubricantProvidor, "[npc.NamePos] "+lt.getName(lubricantProvidor)));
					} else {
						lubricants.add(lubricantProvidor==null
								?lt.getName(lubricantProvidor)
								:UtilText.parse(lubricantProvidor, "[npc.namePos] "+lt.getName(lubricantProvidor)));
					}
					i++;
				}
			}
			stringBuilderToAppendTo.append(Util.stringsToStringList(lubricants, false)+".");
		}
		stringBuilderToAppendTo.append("</p>");
	}
	
	public String getPenetrationSVGString(GameCharacter owner, SexAreaInterface penetration, String baseSVG) {
		SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");
		
		if(!Sex.getContactingSexAreas(owner, penetration).isEmpty()) {
			SexAreaPenetration firstPenetration = null;
			outerloop:
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(owner, penetration).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						firstPenetration = (SexAreaPenetration) sArea;
						break outerloop;
					}
				}
			}
			if(firstPenetration!=null) {
				switch(firstPenetration){
					case FINGER:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
						break;
					case PENIS:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
						break;
					case TAIL:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
						break;
					case TONGUE:
						SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
						break;
					default:
						break;
				}
			}
		}
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, penetration)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
		} else {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
		}
		
		return SVGImageSB.toString();
	}
	
	public String getOrificeSVGString(GameCharacter owner, SexAreaInterface orifice, String baseSVG) {
		SVGImageSB = new StringBuilder();
		
		SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+baseSVG+"</div>");
		
		if(!Sex.getContactingSexAreas(owner, orifice).isEmpty()) {
			SexAreaPenetration firstPenetration = SexAreaPenetration.FINGER;
			outerloop:
			for(Entry<GameCharacter, Set<SexAreaInterface>> entry : Sex.getContactingSexAreas(owner, orifice).entrySet()) {
				for(SexAreaInterface sArea : entry.getValue()) {
					if(sArea.isPenetration()) {
						firstPenetration = (SexAreaPenetration) sArea;
						break outerloop;
					}
				}
			}
			switch(firstPenetration){
				case FINGER:
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeFinger()+"</div>");
					break;
				case PENIS:
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypePenis()+"</div>");
					break;
				case TAIL:
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTail()+"</div>");
					break;
				case TONGUE:
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;right:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getPenetrationTypeTongue()+"</div>");
					break;
				default:
					break;
			}
		}
		
		if(Sex.getAreasCurrentlyStretching(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationStretching()+"</div>");
		}
		
		if(Sex.getAreasTooLoose(owner).contains(orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationTooLoose()+"</div>");
		}
		
		if(!Sex.hasLubricationTypeFromAnyone(owner, orifice)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationDry()+"</div>");
		} else {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCombinationWet()+"</div>");
		}
		
		return SVGImageSB.toString();
	}
	
	public String getCreampieSVGString(GameCharacter owner, SexAreaOrifice orifice) {
		SVGImageSB = new StringBuilder();
		
		boolean justCum = owner.isOnlyCumInArea(orifice);
		
		if(isCumEffectPositive(owner)) {
			SVGImageSB.append("<div style='width:100%;height:100%;position:absolute;left:0;bottom:0;'>"
									+ (justCum
											?SVGImages.SVG_IMAGE_PROVIDER.getCreampieMasochist()
											:SVGImages.SVG_IMAGE_PROVIDER.getFluidIngestedMasochist())
								+"</div>");
		} else {
			SVGImageSB.append((justCum
					?SVGImages.SVG_IMAGE_PROVIDER.getCreampie()
					:SVGImages.SVG_IMAGE_PROVIDER.getFluidIngested()));
		}
		
		switch(orifice) {
			case ANUS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
				break;
			case ASS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaAnus()+"</div>");
				break;
			case BREAST:
				if(owner.hasBreasts()) {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreasts()+"</div>");
				} else {
					SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaBreastsFlat()+"</div>");
				}
				break;
			case MOUTH:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaMouth()+"</div>");
				break;
			case NIPPLE:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaNipple()+"</div>");
				break;
			case THIGHS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaThighs()+"</div>");
				break;
			case URETHRA_PENIS:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraPenis()+"</div>");
				break;
			case URETHRA_VAGINA:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaUrethraVagina()+"</div>");
				break;
			case VAGINA:
				SVGImageSB.append("<div style='width:50%;height:50%;position:absolute;left:0;top:0;'>"+SVGImages.SVG_IMAGE_PROVIDER.getCoverableAreaVagina()+"</div>");
				break;
		}
		
		return SVGImageSB.toString();
	}
	
	private static boolean isCumEffectPositive(GameCharacter target) {
		return target.hasFetish(Fetish.FETISH_MASOCHIST) || target.hasFetish(Fetish.FETISH_CUM_ADDICT);
	}
}