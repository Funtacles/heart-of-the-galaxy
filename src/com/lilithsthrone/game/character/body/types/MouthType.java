package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.2
 * @author Innoxia
 */
public enum MouthType implements BodyPartTypeInterface {
	
	HUMAN(BodyCoveringType.MOUTH, Race.HUMAN),

	DOG_MORPH(BodyCoveringType.MOUTH, Race.DOG_MORPH),
	
	WOLF_MORPH(BodyCoveringType.MOUTH, Race.WOLF_MORPH),
	
	FOX_MORPH(BodyCoveringType.MOUTH, Race.FOX_MORPH),
	
	CAT_MORPH(BodyCoveringType.MOUTH, Race.CAT_MORPH),
	
	COW_MORPH(BodyCoveringType.MOUTH, Race.COW_MORPH),
	
	SQUIRREL_MORPH(BodyCoveringType.MOUTH, Race.SQUIRREL_MORPH),
	
	RAT_MORPH(BodyCoveringType.MOUTH, Race.RAT_MORPH),
	
	RABBIT_MORPH(BodyCoveringType.MOUTH, Race.RABBIT_MORPH),
	
	HORSE_MORPH(BodyCoveringType.MOUTH, Race.HORSE_MORPH);

	
	private BodyCoveringType skinType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private MouthType(BodyCoveringType skinType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialOrificeModifiers, defaultRacialOrificeModifiers);
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}

	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom(
				"mouth");
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom(
				"mouths");
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		return "";
	}
	
	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return race;
	}

	public List<OrificeModifier> getDefaultRacialOrificeModifiers() {
		return defaultRacialOrificeModifiers;
	}
}