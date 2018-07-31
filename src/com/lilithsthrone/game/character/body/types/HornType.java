package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.0
 * @version 0.1.95
 * @author Innoxia
 */
public enum HornType implements BodyPartTypeInterface {
	NONE("", null),

	BOVINE_CURVED("curved", BodyCoveringType.HORN),
	BOVINE_STRAIGHT("straight", BodyCoveringType.HORN),
	
	CURLED("curled", BodyCoveringType.HORN),
	SPIRAL("spiral", BodyCoveringType.HORN),
	CURVED("curved", BodyCoveringType.HORN),
	SWEPT_BACK("swept-back", BodyCoveringType.HORN),
	STRAIGHT("straight", BodyCoveringType.HORN);
	
	private BodyCoveringType skinType;
	
	private HornType(String descriptor, BodyCoveringType skinType) {
		this.skinType = skinType;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return "a pair of";
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return UtilText.returnStringAtRandom("horn");
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		return UtilText.returnStringAtRandom("horns");
	}

	@Override
	public String getDescriptor(GameCharacter gc) {
		switch(this){
			case NONE:
				return "";
			case SWEPT_BACK:
				return UtilText.returnStringAtRandom("swept-back");
			case STRAIGHT: case BOVINE_STRAIGHT:
				return UtilText.returnStringAtRandom("straight");
			case CURLED:
				return UtilText.returnStringAtRandom("curled");
			case CURVED: case BOVINE_CURVED:
				return UtilText.returnStringAtRandom("curved");
			case SPIRAL:
				return UtilText.returnStringAtRandom("spiral");
		}
		return "";
	}
	
	public String getTransformName() {
		switch(this){
			case NONE:
				return "none";
			case SWEPT_BACK:
				return UtilText.returnStringAtRandom("swept-back");
			case STRAIGHT: case BOVINE_STRAIGHT:
				return UtilText.returnStringAtRandom("straight");
			case CURLED:
				return UtilText.returnStringAtRandom("curled");
			case CURVED: case BOVINE_CURVED:
				return UtilText.returnStringAtRandom("curved");
			case SPIRAL:
				return UtilText.returnStringAtRandom("spiral");
		}
		return "";
	}

	@Override
	public BodyCoveringType getBodyCoveringType(Body body) {
		return skinType;
	}

	@Override
	public Race getRace() {
		return Race.HUMAN;
	}
	
	public static List<HornType> getHornTypesSuitableForTransformation(List<HornType> options) {
		if (!options.contains(HornType.NONE)) {
			return options;
		}
		
		List<HornType> duplicatedOptions = new ArrayList<>(options);
		duplicatedOptions.remove(HornType.NONE);
		return duplicatedOptions;
	}
}
