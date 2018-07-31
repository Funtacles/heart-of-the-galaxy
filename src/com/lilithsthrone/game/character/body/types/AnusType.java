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
public enum AnusType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.ANUS, Race.HUMAN),
	
	COW_MORPH(BodyCoveringType.ANUS, Race.COW_MORPH),
	
	DOG_MORPH(BodyCoveringType.ANUS, Race.DOG_MORPH),
	
	FOX_MORPH(BodyCoveringType.ANUS, Race.FOX_MORPH),
	
	SQUIRREL_MORPH(BodyCoveringType.ANUS, Race.SQUIRREL_MORPH),
	
	RAT_MORPH(BodyCoveringType.ANUS, Race.RAT_MORPH),
	
	RABBIT_MORPH(BodyCoveringType.ANUS, Race.RABBIT_MORPH),
	
	WOLF_MORPH(BodyCoveringType.ANUS, Race.WOLF_MORPH),
	
	CAT_MORPH(BodyCoveringType.ANUS, Race.CAT_MORPH),
	
	HORSE_MORPH(BodyCoveringType.ANUS, Race.HORSE_MORPH, OrificeModifier.PUFFY);
	
	private BodyCoveringType skinType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private AnusType(BodyCoveringType skinType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialOrificeModifiers, defaultRacialOrificeModifiers);
	}
	
	@Override
	public boolean isDefaultPlural() {
		return false;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "";
	}
	
	@Override
	public String getName(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("asshole", "back door", "rear entrance");
		}
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		return getName(gc);
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(this){
			default:
				return UtilText.returnStringAtRandom("assholes", "back doors", "rear entrances");
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like", "equine");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like", "bovine");
			default:
				return UtilText.returnStringAtRandom("");
		}
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