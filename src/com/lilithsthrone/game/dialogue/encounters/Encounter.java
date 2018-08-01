package com.lilithsthrone.game.dialogue.encounters;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;
import java.util.Map.Entry;

import com.lilithsthrone.game.Weather;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.0
 * @version 0.2.5
 * @author Innoxia
 */
public enum Encounter {
	
	DOMINION_STREET(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_STORM_ATTACK, 15f),
			new Value<EncounterType, Float>(EncounterType.SPECIAL_DOMINION_CULTIST, 5f),
			new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_FIND_HAPPINESS, 10f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STORM_ATTACK && Main.game.getCurrentWeather() == Weather.MAGIC_STORM) {
				try {
					Main.game.addNPC(Main.game.getActiveNPC(), false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return Main.game.getActiveNPC().getEncounterDialogue();
				
			} else if(node == EncounterType.DOMINION_STREET_FIND_HAPPINESS
					&& Main.game.getPlayer().getName().equalsIgnoreCase("Kinariu")
					&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.foundHappiness)) {
				Main.game.getDialogueFlags().setFlag(DialogueFlagValue.foundHappiness, true);
				return DominionEncounterDialogue.DOMINION_STREET_FIND_HAPPINESS;
				
			} else {
				return null;
			}
		}
	},
	
	DOMINION_BOULEVARD(Util.newHashMapOfValues(
			new Value<EncounterType, Float>(EncounterType.DOMINION_STREET_RENTAL_MOMMY, 10f))) {
		@Override
		protected DialogueNodeOld initialiseEncounter(EncounterType node) {
			if(node == EncounterType.DOMINION_STREET_RENTAL_MOMMY) {
				LocalDateTime time = Main.game.getDateNow();
				
				if(time.getMonth().equals(Month.MAY) /*&& time.getDayOfWeek().equals(DayOfWeek.SUNDAY)*/ && time.getDayOfMonth()>7 && time.getDayOfMonth()<=14
						&& !Main.game.getDialogueFlags().hasFlag(DialogueFlagValue.mommyFound)
						&& Main.game.getCurrentWeather()!=Weather.MAGIC_STORM) {
					
					try {
						Main.game.addNPC(Main.game.getActiveNPC(), false);
					} catch (Exception e) {
						e.printStackTrace();
					}
		
					return Main.game.getActiveNPC().getEncounterDialogue();
					
				}
			}
			
			return null;
		}
	};
	
	private static AbstractItem randomItem;
	private static AbstractClothing randomClothing;
	private static AbstractWeapon randomWeapon;

	private Map<EncounterType, Float> dialogues;

	private Encounter(Map<EncounterType, Float> dialogues) {
		this.dialogues = dialogues;
	}

	protected abstract DialogueNodeOld initialiseEncounter(EncounterType node);

	/**
	 * Returns a random encounter from the list, or null if no encounter was selected.
	 * 
	 * @param forceEncounter Forces an encounter to be selected. (Will still return null if the encounter list is empty.)
	 * @return null if no encounter.
	 */
	public DialogueNodeOld getRandomEncounter(boolean forceEncounter) {
		return getBaseRandomEncounter(forceEncounter);
	}
	
	public Map<EncounterType, Float> getDialogues() {
		return dialogues;
	}

	protected DialogueNodeOld getBaseRandomEncounter(boolean forceEncounter) {
		float r = (float) (Math.random() * 100);
		float total = 0;
		
		if(forceEncounter) {
			r = 0;
			for (Entry<EncounterType, Float> e : getDialogues().entrySet()) {
				r += e.getValue();
			}
			r *= Math.random();
		}
		
		for (Entry<EncounterType, Float> e : getDialogues().entrySet()) {
			total += e.getValue();
			if (r <= total) {
				return initialiseEncounter(e.getKey());
			}
		}

		return null;
	}

	public static AbstractItem getRandomItem() {
		return randomItem;
	}

	public static AbstractWeapon getRandomWeapon() {
		return randomWeapon;
	}

	public static AbstractClothing getRandomClothing() {
		return randomClothing;
	}

}
