package com.lilithsthrone.game.character.npc.dominion;

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.Name;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.npcDialogue.SlaveDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogue;
import com.lilithsthrone.game.dialogue.npcDialogue.dominion.AlleywayAttackerDialogueCompanions;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.66
 * @version 0.2.6
 * @author Innoxia
 */
public class DominionAlleywayAttacker extends NPC {

	public DominionAlleywayAttacker() {
		this(Gender.F_V_B_FEMALE, false);
	}
	
	public DominionAlleywayAttacker(Gender gender) {
		this(gender, false);
	}
	
	public DominionAlleywayAttacker(boolean isImported) {
		this(Gender.F_V_B_FEMALE, isImported);
	}
	
	public DominionAlleywayAttacker(Gender gender, boolean isImported) {
		super(null, "",
				3, gender, RacialBody.DOG_MORPH, RaceStage.GREATER,
				new CharacterInventory(10), WorldType.DOMINION, PlaceType.DOMINION_BACK_ALLEYS, false);

		if(!isImported) {
	
			this.setWorldLocation(Main.game.getPlayer().getWorldLocation());
			this.setLocation(new Vector2i(Main.game.getPlayer().getLocation().getX(), Main.game.getPlayer().getLocation().getY()));
			
			boolean canalSpecies = false;
			PlaceType pt = Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType();
			if(pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
					|| pt == PlaceType.DOMINION_CANAL
					|| pt == PlaceType.DOMINION_CANAL_END) {
				canalSpecies = true;
			}
			
			// Set random level from 1 to 3:
			setLevel(Util.random.nextInt(3) + 1);
			
			// RACE & NAME:
			
			Map<Subspecies, Integer> availableRaces = new HashMap<>();
			for(Subspecies s : Subspecies.values()) {
				switch(s) {
					// No spawn chance:
					case ANGEL:
					case DEMON:
					case HUMAN:
					case IMP:
					case IMP_ALPHA:
					case FOX_ASCENDANT:
					case FOX_ASCENDANT_FENNEC:
						break;
						
					// Canals spawn only:
					case ALLIGATOR_MORPH:
						addToSubspeciesMap(canalSpecies?20:0, gender, s, availableRaces);
						break;
						
					// Regular spawns:
					case CAT_MORPH:
						addToSubspeciesMap(canalSpecies?5:20, gender, s, availableRaces);
						break;
					case CAT_MORPH_LYNX:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD_SNOW:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LEOPARD:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_LION:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_TIGER:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CHEETAH:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case CAT_MORPH_CARACAL:
						addToSubspeciesMap(canalSpecies?2:5, gender, s, availableRaces);
						break;
					case COW_MORPH:
						addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						break;
					case DOG_MORPH:
						addToSubspeciesMap(canalSpecies?3:12, gender, s, availableRaces);
						break;
					case DOG_MORPH_DOBERMANN:
						addToSubspeciesMap(canalSpecies?1:4, gender, s, availableRaces);
						break;
					case DOG_MORPH_BORDER_COLLIE:
						addToSubspeciesMap(canalSpecies?1:4, gender, s, availableRaces);
						break;
					case FOX_MORPH:
						addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						break;
					case FOX_MORPH_FENNEC:
						addToSubspeciesMap(5, gender, s, availableRaces);
						break;
					case HORSE_MORPH:
						addToSubspeciesMap(canalSpecies?4:16, gender, s, availableRaces);
						break;
					case HORSE_MORPH_ZEBRA:
						addToSubspeciesMap(canalSpecies?1:4, gender, s, availableRaces);
						break;
					case SQUIRREL_MORPH:
						addToSubspeciesMap(canalSpecies?1:10, gender, s, availableRaces);
						break;
					case WOLF_MORPH:
						addToSubspeciesMap(canalSpecies?5:20, gender, s, availableRaces);
						break;
					case RABBIT_MORPH:
						addToSubspeciesMap(canalSpecies?1:3, gender, s, availableRaces);
						break;
					case RABBIT_MORPH_LOP:
						addToSubspeciesMap(canalSpecies?1:3, gender, s, availableRaces);
						break;
				}
			}
			
			this.setBodyFromSubspeciesPreference(gender, availableRaces);
			
			setSexualOrientation(RacialBody.valueOfRace(getRace()).getSexualOrientation(gender));
	
			setName(Name.getRandomTriplet(this.getRace()));
			this.setPlayerKnowsName(false);
			setDescription(UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who, for reasons of [npc.her] own, prowls the back alleys in search of victims to prey upon."));
			
			// PERSONALITY & BACKGROUND:
			
			CharacterUtils.setHistoryAndPersonality(this, true);
			
			// ADDING FETISHES:
			
			CharacterUtils.addFetishes(this);
			
			// BODY RANDOMISATION:
			
			CharacterUtils.randomiseBody(this);
			
			// INVENTORY:
			
			resetInventory();
			inventory.setMoney(10 + Util.random.nextInt(getLevel()*10) + 1);
			CharacterUtils.generateItemsInInventory(this);
	
			CharacterUtils.equipClothing(this, true, false);
			CharacterUtils.applyMakeup(this, true);
			
			// Set starting attributes based on the character's race
			for (Attribute a : RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().keySet()) {
				attributes.put(a, RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getMinimum() + RacialBody.valueOfRace(this.getRace()).getAttributeModifiers().get(a).getRandomVariance());
			}
			
			setMana(getAttributeValue(Attribute.MANA_MAXIMUM));
			setHealth(getAttributeValue(Attribute.HEALTH_MAXIMUM));
		}

		this.setEnslavementDialogue(SlaveDialogue.DEFAULT_ENSLAVEMENT_DIALOGUE);
	}
	
	@Override
	public void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings) {
		loadNPCVariablesFromXML(this, null, parentElement, doc, settings);
	}
	
	@Override
	public boolean isUnique() {
		return false;
	}
	
	@Override
	public String getDescription() {
		if(this.isSlave()) {
			return (UtilText.parse(this,
					"[npc.NamePos] days of prowling the back alleys of Dominion and mugging innocent travellers are now over. Having run afoul of the law, [npc.sheIs] now a slave, and is no more than [npc.her] owner's property."));
		} else {
			return (UtilText.parse(this,
					"[npc.Name] is a resident of Dominion, who prowls the back alleys in search of innocent travellers to mug and rape."));
		}
	}
	
	@Override
	public void endSex() {
		if(!isSlave()) {
			setPendingClothingDressing(true);
		}
	}

	@Override
	public boolean isClothingStealable() {
		return true;
	}
	
	@Override
	public boolean isAbleToBeImpregnated() {
		return true;
	}
	
	@Override
	public DialogueNodeOld getEncounterDialogue() {
		PlaceType pt = Main.game.getActiveWorld().getCell(location).getPlace().getPlaceType();
		
		if(pt == PlaceType.DOMINION_BACK_ALLEYS
				|| pt == PlaceType.DOMINION_CANAL
				|| pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
				|| pt == PlaceType.DOMINION_CANAL_END) {
			
			if(Main.game.getPlayer().getCompanions().isEmpty()) {
				return AlleywayAttackerDialogue.ALLEY_ATTACK;
			} else {
				return AlleywayAttackerDialogueCompanions.ALLEY_ATTACK;
			}
			
		} else {
			return AlleywayAttackerDialogue.STORM_ATTACK;
		}
	}

	// Combat:

	@Override
	public Response endCombat(boolean applyEffects, boolean victory) {
		if(Main.game.getPlayer().getCompanions().isEmpty()) {
			if (victory) {
				return new Response("", "", AlleywayAttackerDialogue.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayAttackerDialogue.AFTER_COMBAT_DEFEAT);
			}
		} else {
			if (victory) {
				return new Response("", "", AlleywayAttackerDialogueCompanions.AFTER_COMBAT_VICTORY);
			} else {
				return new Response ("", "", AlleywayAttackerDialogueCompanions.AFTER_COMBAT_DEFEAT);
			}
		}
	}
	
	@Override
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		return getItemUseEffectsAllowingUse(item, user, target);
	}
	
}
