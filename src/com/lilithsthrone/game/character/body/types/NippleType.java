package com.lilithsthrone.game.character.body.types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.83
 * @version 0.2.2
 * @author Innoxia
 */
public enum NippleType implements BodyPartTypeInterface {
	
	HUMAN(BodyCoveringType.NIPPLES, Race.HUMAN),

	DOG_MORPH(BodyCoveringType.NIPPLES, Race.DOG_MORPH),
	
	WOLF_MORPH(BodyCoveringType.NIPPLES, Race.WOLF_MORPH),
	
	FOX_MORPH(BodyCoveringType.NIPPLES, Race.FOX_MORPH),
	
	CAT_MORPH(BodyCoveringType.NIPPLES, Race.CAT_MORPH),
	
	COW_MORPH(BodyCoveringType.NIPPLES, Race.COW_MORPH),
	
	SQUIRREL_MORPH(BodyCoveringType.NIPPLES, Race.SQUIRREL_MORPH),
	
	RAT_MORPH(BodyCoveringType.NIPPLES, Race.RAT_MORPH),
	
	RABBIT_MORPH(BodyCoveringType.NIPPLES, Race.RABBIT_MORPH),
	
	HORSE_MORPH(BodyCoveringType.NIPPLES, Race.HORSE_MORPH),
	
	REINDEER_MORPH(BodyCoveringType.NIPPLES, Race.REINDEER_MORPH);

	
	private BodyCoveringType skinType;
	private Race race;
	private List<OrificeModifier> defaultRacialOrificeModifiers;

	private NippleType(BodyCoveringType skinType, Race race, OrificeModifier... defaultRacialOrificeModifiers) {
		this.skinType = skinType;
		this.race = race;
		this.defaultRacialOrificeModifiers = new ArrayList<>();
		Collections.addAll(this.defaultRacialOrificeModifiers, defaultRacialOrificeModifiers);
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		if(gc.getBreastRows()==1) {
			return "a pair of";
		} else if(gc.getBreastRows()==2) {
			return "two pairs of";
		} else {
			return "three pairs of";
		}
	}

	@Override
	public boolean isDefaultPlural() {
		return true;
	}

	@Override
	public String getNameSingular(GameCharacter gc) {
		if(gc.hasBreasts()) {
			String name[] = new String[] {"nipple", "teat"};
			return name[Util.random.nextInt(name.length)];
			
		} else {
			return "nipple";
		}
	}
	
	@Override
	public String getNamePlural(GameCharacter gc) {
		if(gc.hasBreasts()) {
			String name[] = new String[] {"nipples", "teats"};
			return name[Util.random.nextInt(name.length)];
			
		} else {
			return "nipples";
		}
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		String descriptor[];
		
		switch(this){
			case HUMAN:
				descriptor = new String[] { "" };
				break;
			default:
				descriptor = new String[] { "" };
				break;
		}
		
		return descriptor[Util.random.nextInt(descriptor.length)];
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