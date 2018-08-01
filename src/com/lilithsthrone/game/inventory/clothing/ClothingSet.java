package com.lilithsthrone.game.inventory.clothing;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.9
 * @author Innoxia
 */
public enum ClothingSet {

	// Hat, gloves, shirt, skirt/shorts, thigh-high boots
	ENFORCER("Commanding Enforcer",
			StatusEffect.SET_ENFORCER,
			2,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.LEG),
			null),

	MAID("Hard-working Maid",
			StatusEffect.SET_MAID,
			5,
			Util.newArrayListOfValues(
					InventorySlot.HEAD,
					InventorySlot.TORSO_UNDER,
					InventorySlot.SOCK,
					InventorySlot.FOOT,
					InventorySlot.HAND),
			null),

	BUTLER("Butler",
			StatusEffect.SET_BUTLER,
			5,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.TORSO_UNDER,
					InventorySlot.LEG,
					InventorySlot.FOOT,
					InventorySlot.HAND),
			null),

	WITCH("Witch",
			StatusEffect.SET_WITCH,
			3,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT,
					InventorySlot.HEAD),
			null),

	SCIENTIST("Brilliant Scientist",
			StatusEffect.SET_SCIENTIST,
			2,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.EYES),
			null),

	MILK_MAID("Milk Maid",
			StatusEffect.SET_MILK_MAID,
			2,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_UNDER,
					InventorySlot.HEAD),
			null),

	BDSM("Locked in Bondage",
			StatusEffect.SET_BDSM,
			4,
			null,
			null),

	CATTLE("Cattle",
			StatusEffect.SET_CATTLE,
			3,
			Util.newArrayListOfValues(
					InventorySlot.PIERCING_EAR,
					InventorySlot.PIERCING_NOSE,
					InventorySlot.NECK),
			null),

	GEISHA("Geisha",
			StatusEffect.SET_GEISHA,
			3,
			Util.newArrayListOfValues(
					InventorySlot.HAIR,
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT),
			null),

	RONIN("Ronin",
			StatusEffect.SET_RONIN,
			3,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT),
			null),

	JOLNIR("J&oacute;lnir",
			StatusEffect.SET_JOLNIR,
			3,
			Util.newArrayListOfValues(
					InventorySlot.TORSO_OVER,
					InventorySlot.TORSO_UNDER,
					InventorySlot.FOOT,
					InventorySlot.HEAD),
			null),

	SUN("Solar Power",
			StatusEffect.SET_SUN,
			3,
			null,
			null),

	SNOWFLAKE("Blizzard",
			StatusEffect.SET_SNOWFLAKE,
			3,
			null,
			null),

	RAINBOW("Rainbow",
			StatusEffect.SET_RAINBOW,
			2,
			null,
			null);

	private String name;
	private int numberRequiredForCompleteSet;
	private List<InventorySlot> blockedSlotsCountingTowardsFullSet;
	private List<SpecialAttack> specialAttacks;
	private StatusEffect associatedStatusEffect;

	private ClothingSet(String name, StatusEffect associatedStatusEffect, int numberRequiredForCompleteSet, List<InventorySlot> blockedSlotsCountingTowardsFullSet, List<SpecialAttack> specialAttacks) {
		this.name = name;
		this.numberRequiredForCompleteSet = numberRequiredForCompleteSet;
		
		if(blockedSlotsCountingTowardsFullSet==null) {
			this.blockedSlotsCountingTowardsFullSet = new ArrayList<>();
		} else {
			this.blockedSlotsCountingTowardsFullSet = blockedSlotsCountingTowardsFullSet;
		}
		
		this.specialAttacks = specialAttacks;
		this.associatedStatusEffect = associatedStatusEffect;
	}
	
	public boolean isCharacterWearingCompleteSet(GameCharacter target) {
		int setCount = 0;
		
		for(InventorySlot slot : this.getBlockedSlotsCountingTowardsFullSet()) {
			if(slot.slotBlockedByRace(target) != null) {
				setCount++;
			}
		}
		
		boolean atLeastOneClothingFound = false;
		for (AbstractClothing c : target.getClothingCurrentlyEquipped()) {
			if (c.getClothingType().getClothingSet() == this) {
				setCount++;
				atLeastOneClothingFound = true;
			}
		}
		
		return atLeastOneClothingFound && setCount >= this.getNumberRequiredForCompleteSet();
	}

	public String getName() {
		return name;
	}

	public int getNumberRequiredForCompleteSet() {
		return numberRequiredForCompleteSet;
	}

	public List<SpecialAttack> getSpecialAttacks() {
		return specialAttacks;
	}

	public StatusEffect getAssociatedStatusEffect() {
		return associatedStatusEffect;
	}

	public List<InventorySlot> getBlockedSlotsCountingTowardsFullSet() {
		return blockedSlotsCountingTowardsFullSet;
	}
}
