package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.83
 * @version 0.2.2
 * @author Innoxia
 */
public enum HairType implements BodyPartTypeInterface {
	HUMAN(BodyCoveringType.HAIR_HUMAN, Race.HUMAN),

	ANGEL(BodyCoveringType.HAIR_ANGEL, Race.ANGEL),

	DEMON_COMMON(BodyCoveringType.HAIR_DEMON, Race.DEMON),
	
	IMP(BodyCoveringType.HAIR_IMP, Race.IMP),

	DOG_MORPH(BodyCoveringType.HAIR_CANINE_FUR, Race.DOG_MORPH),

	LYCAN(BodyCoveringType.HAIR_LYCAN_FUR, Race.WOLF_MORPH),
	
	FOX_MORPH(BodyCoveringType.HAIR_FOX_FUR, Race.FOX_MORPH),

	CAT_MORPH(BodyCoveringType.HAIR_FELINE_FUR, Race.CAT_MORPH),

	CAT_MORPH_SIDEFLUFF(BodyCoveringType.HAIR_FELINE_FUR, Race.CAT_MORPH),

	COW_MORPH(BodyCoveringType.HAIR_BOVINE_FUR, Race.COW_MORPH),

	ALLIGATOR_MORPH(BodyCoveringType.HAIR_SCALES_ALLIGATOR, Race.ALLIGATOR_MORPH),

	SQUIRREL_MORPH(BodyCoveringType.HAIR_SQUIRREL_FUR, Race.SQUIRREL_MORPH),

	RABBIT_MORPH(BodyCoveringType.HAIR_RABBIT_FUR, Race.RABBIT_MORPH),

	HORSE_MORPH(BodyCoveringType.HAIR_HORSE_HAIR, Race.HORSE_MORPH);

	
	private BodyCoveringType coveringType;
	private Race race;

	private HairType(BodyCoveringType coveringType, Race race) {
		this.coveringType = coveringType;
		this.race = race;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a head of";
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
				return "hair";
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		switch(coveringType) {
			case ALLIGATOR_SCALES:
				return "scales";
			default:
				return "hair";
		}
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case ANGEL:
				return UtilText.returnStringAtRandom("angelic");
			case CAT_MORPH:
				return UtilText.returnStringAtRandom("cat-like");
			case CAT_MORPH_SIDEFLUFF:
				return UtilText.returnStringAtRandom("cat-like");
			case COW_MORPH:
				return UtilText.returnStringAtRandom("cow-like");
			case DEMON_COMMON:
				return UtilText.returnStringAtRandom("demonic");
			case IMP:
				return UtilText.returnStringAtRandom("impish");
			case DOG_MORPH:
				return UtilText.returnStringAtRandom("dog-like");
			case SQUIRREL_MORPH:
				return UtilText.returnStringAtRandom("squirrel-like");
			case ALLIGATOR_MORPH:
				return UtilText.returnStringAtRandom("alligator-like");
			case HORSE_MORPH:
				return UtilText.returnStringAtRandom("horse-like");
			case HUMAN:
				return UtilText.returnStringAtRandom("");
			case LYCAN:
				return UtilText.returnStringAtRandom("wolf-like");
			case FOX_MORPH:
				return UtilText.returnStringAtRandom("fox-like");
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

	public String getTransformName() {
		switch(this){
			case ANGEL:
				return "angelic";
			case CAT_MORPH:
				return "feline";
			case CAT_MORPH_SIDEFLUFF:
				return UtilText.returnStringAtRandom("feline sidefluff");
			case DEMON_COMMON:
				return "demonic";
			case IMP:
				return "impish";
			case DOG_MORPH:
				return "canine";
			case COW_MORPH:
				return "bovine";
			case SQUIRREL_MORPH:
				return "furry";
			case ALLIGATOR_MORPH:
				return "alligator";
			case HORSE_MORPH:
				return "equine";
			case HUMAN:
				return "human";
			case LYCAN:
				return "lupine";
			case FOX_MORPH:
				return "vulpine";
			case RABBIT_MORPH:
				return "rabbit";
		}
		return "";
	}
	
	private static Map<Race, List<HairType>> typesMap = new HashMap<>();
	public static List<HairType> getHairTypes(Race r) {
		if(typesMap.containsKey(r)) {
			return typesMap.get(r);
		}
		
		List<HairType> types = new ArrayList<>();
		for(HairType type : HairType.values()) {
			if(type.getRace()==r) {
				types.add(type);
			}
		}
		typesMap.put(r, types);
		return types;
	}
}