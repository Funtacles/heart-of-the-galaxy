package com.lilithsthrone.game.character.body.types;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.2
 * @author Innoxia
 */
public enum SkinType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HUMAN, Race.HUMAN),

	COW_MORPH(BodyCoveringType.BOVINE_FUR, Race.COW_MORPH),

	DOG_MORPH(BodyCoveringType.CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.LYCAN_FUR, Race.WOLF_MORPH),
	
	FOX_MORPH(BodyCoveringType.FOX_FUR, Race.FOX_MORPH),

	CAT_MORPH(BodyCoveringType.FELINE_FUR, Race.CAT_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	RAT_MORPH(BodyCoveringType.RAT_FUR, Race.RAT_MORPH),

	RABBIT_MORPH(BodyCoveringType.RABBIT_FUR, Race.RABBIT_MORPH),

	HORSE_MORPH(BodyCoveringType.HORSE_HAIR, Race.HORSE_MORPH);


	
	private BodyCoveringType coveringType;
	private Race race;

	private SkinType(BodyCoveringType coveringType, Race race) {
		this.coveringType = coveringType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a layer of";
	}

	@Override
	public boolean isDefaultPlural() {
		switch(coveringType) {
			case ALLIGATOR_SCALES:
				return true;
			default:
				return false;
		}
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		switch(coveringType) {
			case ALLIGATOR_SCALES:
				return "scale";
			default:
				return coveringType.getName(gc);
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(coveringType) {
			case ALLIGATOR_SCALES:
				return "scales";
			default:
				return coveringType.getNamePlural(gc);
		}
	}
	
	public String getTransformName() {
		switch(this){
			case CAT_MORPH:
				return "feline";
			case COW_MORPH:
				return "bovine";
			case DOG_MORPH:
				return "canine";
			case HORSE_MORPH:
				return "equine";
			case LYCAN:
				return "lupine";
			case FOX_MORPH:
				return "vulpine";
			case SQUIRREL_MORPH:
				return "fluffy";
			case RAT_MORPH:
				return "rat";
			case RABBIT_MORPH:
				return "rabbit";
			case HUMAN:
				return "human";
		}
		return "";
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("fox-like");
			case RAT_MORPH:
				return UtilText.returnStringAtRandom("rat-like");
			case RABBIT_MORPH:
				return UtilText.returnStringAtRandom("rabbit-like");
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return coveringType;
	}

	@Override
	public Race getRace() {
		return race;
	}
}