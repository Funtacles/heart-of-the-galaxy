package com.lilithsthrone.game.character.race;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Height;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.world.WorldType;

/**
 * @since 0.1.91
 * @version 0.2.7
 * @author tukaima, Innoxia
 */
public enum Subspecies {

	// HUMAN:
	HUMAN("statusEffects/raceHuman",
			"human",
			"humans",
			"man",
			"woman",
			"men",
			"women",
			Race.HUMAN,
			Colour.RACE_HUMAN,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical human.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)),

	// DEMON:
	DEMON("statusEffects/raceDemon",
			"demon",
			"demons",
			"incubus",
			"succubus",
			"incubi",
			"succubi",
			Race.DEMON,
			Colour.RACE_DEMON,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical demon.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		
		@Override
		public void applySpeciesChanges(Body body) {
			if(Math.random()<0.25f) {
				if(body.getLeg().getType()==LegType.DEMON_COMMON) {
					body.getLeg().setType(null, LegType.DEMON_HOOFED);
				}
			}
		}
		
		@Override
		public Subspecies getOffspringSubspecies() {
			return IMP;
		}
		
		@Override
		public String getOffspringMaleName() {
			return "imps";
		}
		@Override
		public String getOffspringMaleNameSingular() {
			return "imp";
		}
		@Override
		public String getOffspringFemaleName() {
			return "imps";
		}
		@Override
		public String getOffspringFemaleNameSingular() {
			return "imp";
		}
		
	},
	
	IMP("statusEffects/raceImp",
			"imp",
			"imps",
			"imp",
			"imp",
			"imps",
			"imps",
			Race.IMP,
			Colour.RACE_IMP,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical imp.",
			Util.newArrayListOfValues()) {
		@Override
		public boolean isShortStature() {
			return true;
		}
	},
	
	IMP_ALPHA("statusEffects/raceImpAlpha",
			"alpha-imp",
			"alpha-imps",
			"alpha-imp",
			"alpha-imp",
			"alpha-imps",
			"alpha-imps",
			Race.IMP,
			Colour.RACE_IMP,
			SubspeciesPreference.ONE_LOW,
			"A more powerful form of imp, standing at over 3'6\" tall.",
			Util.newArrayListOfValues()) {
		@Override
		public Subspecies getOffspringSubspecies() {
			return IMP;
		}
		@Override
		public void applySpeciesChanges(Body body) {
			body.setHeight(Height.NEGATIVE_ONE_TINY.getMedianValue());
		}
		@Override
		public boolean isShortStature() {
			return true;
		}
	},
	
	// BOVINES:
	COW_MORPH("statusEffects/raceCowMorph",
			"cow-morph",
			"cow-morphs",
			"cow-boy",
			"cow-girl",
			"cow-boys",
			"cow-girls",
			Race.COW_MORPH,
			Colour.RACE_COW_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal cow-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)),
	
	// CANIDS:
	DOG_MORPH("statusEffects/raceDogMorph",
			"dog-morph",
			"dog-morphs",
			"dog-boy",
			"dog-girl",
			"dog-boys",
			"dog-girls",
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal dog-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
				@Override
				public void applySpeciesChanges(Body body) {
					if(body.getPenis().getType()==PenisType.CANINE) {
						body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
					}
				}
			},
	
	DOG_MORPH_BORDER_COLLIE("statusEffects/raceDogMorph",
			"border-collie-morph",
			"border-collie-morphs",
			"border-collie-boy",
			"border-collie-girl",
			"border-collie-boys",
			"border-collie-girls",
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A particularly energetic and intelligent dog-morph which resembles an anthropomorphised border collie."
					+ " To be identified as a border-collie-morph, a character must be a dog-morph that has either upright or folder ears, and fluffy, black fur with white markings.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
				@Override
				public void applySpeciesChanges(Body body) {
					if(body.getPenis().getType()==PenisType.CANINE) {
						body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
					}
					body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.FLUFFY, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false));
					if(body.getEar().getType()==EarType.DOG_MORPH) {
						if(Math.random()<0.5f) {
							body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
						} else {
							body.getEar().setType(null, EarType.DOG_MORPH_FOLDED);
						}
					}
				}
			},
	
	DOG_MORPH_DOBERMANN("statusEffects/raceDogMorphDobermann",
			"dobermann",
			"dobermanns",
			"dobermann",
			"dobermann",
			"dobermanns",
			"dobermanns",
			Race.DOG_MORPH,
			Colour.RACE_DOG_MORPH,
			SubspeciesPreference.TWO_AVERAGE,
			"A dog-morph which resembles an anthropomorphised dobermann. To be identified as a dobermann, a character must be a dog-morph that has short, black fur, with either brown, dark-brown, or tan markings.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getPenis().getType()==PenisType.CANINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
			Colour secondaryColour = Colour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				secondaryColour = Colour.COVERING_TAN;
			} else if(rand<0.6f) {
				secondaryColour = Colour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.CANINE_FUR, new Covering(BodyCoveringType.CANINE_FUR, CoveringPattern.MARKED, CoveringModifier.SHORT, Colour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_CANINE_FUR, new Covering(BodyCoveringType.HAIR_CANINE_FUR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, secondaryColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.DOG_MORPH) {
				body.getEar().setType(null, EarType.DOG_MORPH_POINTED);
			}
			if(body.getTail().getType()==TailType.DOG_MORPH) {
				body.getTail().setType(null, TailType.DOG_MORPH_STUBBY);
			}
		}
	},
	
	WOLF_MORPH("statusEffects/raceWolfMorph",
			"wolf-morph",
			"wolf-morphs",
			"wolf-boy",
			"wolf-girl",
			"wolf-boys",
			"wolf-girls",
			Race.WOLF_MORPH,
			Colour.RACE_WOLF_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal wolf-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getPenis().getType()==PenisType.LUPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_MORPH("statusEffects/raceFoxMorph",
			"fox-morph",
			"fox-morphs",
			"fox-boy",
			"fox-girl",
			"fox-boys",
			"fox-girls",
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal fox-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Subspecies.applyFoxColoring(body);
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_MORPH_FENNEC("statusEffects/raceFoxMorph",
			"fennec-morph",
			"fennec-morphs",
			"fennec-boy",
			"fennec-girl",
			"fennec-boys",
			"fennec-girls",
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A bipedal fox-morph with tan or bleach blonde fur and distinctive large ears.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour fennecColour = Colour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = Colour.COVERING_DIRTY_BLONDE;
			}
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_OLIVE, false, Colour.SKIN_OLIVE, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				body.getEar().setType(null, EarType.FOX_MORPH_BIG);
			}
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_ASCENDANT("statusEffects/raceFoxMorph",
			"youko",
			"youkos",
			"youko-boy",
			"youko-girl",
			"youko-boys",
			"youko-girls",
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A fox-morph, empowered by the gifts of a Lilin.",
			Util.newArrayListOfValues(WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Subspecies.applyFoxColoring(body);
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	FOX_ASCENDANT_FENNEC("statusEffects/raceFoxMorph",
			"fennec-youko",
			"fennec-youko",
			"fennec-youko-boy",
			"fennec-youko-girl",
			"fennec-youko-boys",
			"fennec-youko-girls",
			Race.FOX_MORPH,
			Colour.RACE_FOX_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A fennec-morph, empowered by the gifts of a Lilin.",
			Util.newArrayListOfValues(WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour fennecColour = Colour.COVERING_BLEACH_BLONDE;
			double rand = Math.random();
			if(rand<0.5f) {
				fennecColour = Colour.COVERING_DIRTY_BLONDE;
			}
			body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, CoveringPattern.NONE, fennecColour, false, fennecColour, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_OLIVE, false, Colour.SKIN_OLIVE, false));
			body.updateCoverings(true, true, true, true);
			if(body.getEar().getType()==EarType.FOX_MORPH) {
				body.getEar().setType(null, EarType.FOX_MORPH_BIG);
			}
			if(body.getTail().getType()==TailType.FOX_MORPH) {
				body.getTail().setType(null, TailType.FOX_MORPH_MAGIC);
			}
			if(body.getPenis().getType()==PenisType.VULPINE) {
				body.getCoverings().put(BodyCoveringType.PENIS, new Covering(BodyCoveringType.PENIS, Colour.SKIN_RED));
			}
		}
	},
	
	// FELINES:
	CAT_MORPH("statusEffects/raceCatMorph",
			"cat-morph",
			"cat-morphs",
			"cat-boy",
			"cat-girl",
			"cat-boys",
			"cat-girls",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal cat-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			// TODO Auto-generated method stub
			
		}
	},
	
	CAT_MORPH_LYNX("statusEffects/raceCatMorphLynx",
			"lynx-morph",
			"lynx-morphs",
			"lynx",
			"lynx",
			"lynxes",
			"lynxes",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LYNX,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised lynx. To be identified as a Lynx-morph, a character must be a cat-morph that has fluffy fur, tufted ears, short tail and side-fluff hair type.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_BROWN;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = Colour.COVERING_TAN;
			} else if(rand<0.6f) {
				primaryColor = Colour.COVERING_BROWN_DARK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, Colour.COVERING_BLACK, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, Colour.COVERING_BLACK, false));
			body.updateCoverings(true, true, true, true);
			body.getEar().setType(null, EarType.CAT_MORPH_TUFTED);
			body.getTail().setType(null, TailType.CAT_MORPH_SHORT);
			body.getHair().setType(null, HairType.CAT_MORPH_SIDEFLUFF);
		}
	},
	
	CAT_MORPH_LEOPARD_SNOW("statusEffects/raceCatMorphLeopardSnow",
			"snow leopard-morph",
			"snow leopard-morphs",
			"snow leopard",
			"snow leopardess",
			"snow leopards",
			"snow leopardesses",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LEOPARD_SNOW,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised snow leopard. To be identified as a snow leopard-morph, a character must be a cat-morph that has fluffy spotted fur, normal tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_WHITE;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.3f) {
				primaryColor = Colour.COVERING_WHITE;
			} else if(rand<0.6f) {
				primaryColor = Colour.COVERING_GREY;
			} else if(rand<0.65f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.FLUFFY, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_LEOPARD("statusEffects/raceCatMorphLeopard",
			"leopard-morph",
			"leopard-morphs",
			"leopard",
			"leopardess",
			"leopard",
			"leopardesses",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LEOPARD,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised leopard. To be identified as a leopard-morph, a character must be a cat-morph that has short spotted fur, normal tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_ORANGE;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.05f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_LION("statusEffects/raceCatMorphLion",
			"lion-morph",
			"lion-morphs",
			"lion",
			"lioness",
			"lions",
			"lionesses",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_LION,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised lion. To be identified as a lion-morph, a character must be a cat-morph that has short fur, tufted tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_TAN;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.05f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			else if(rand<0.1f) {
				primaryColor = Colour.COVERING_WHITE;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH_TUFTED);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_TIGER("statusEffects/raceCatMorphTiger",
			"tiger-morph",
			"tiger-morphs",
			"tiger",
			"tigress",
			"tigers",
			"tigresses",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_TIGER,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised tuger. To be identified as a tiger-morph, a character must be a cat-morph that has striped fur, normal tail and panther face.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_ORANGE;
			Colour secondaryColor = Colour.COVERING_BLACK;
			double rand = Math.random();
			if(rand<0.05f) {
				primaryColor = Colour.COVERING_BLACK;
			}
			else if(rand<0.10f) {
				primaryColor = Colour.COVERING_WHITE;
			}
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.STRIPED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			if(body.getFace().getType()==FaceType.CAT_MORPH) {
				body.getFace().setType(null, FaceType.CAT_MORPH_PANTHER);
			}
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			body.setBodySize(BodySize.TWO_AVERAGE.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_CHEETAH("statusEffects/raceCatMorphCheetah",
			"cheetah-morph",
			"cheetah-morphs",
			"cheetah",
			"cheetah",
			"cheetahs",
			"cheetahs",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_CHEETAH,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised cheetah. To be identified as a cheetah-morph, a character must be a cat-morph that has short, spotted fur and not identified as other feline morphs.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			Colour primaryColor = Colour.COVERING_ORANGE;
			double rand = Math.random();
			if(rand<0.35f) {
				primaryColor = Colour.COVERING_TAN;
			}
			Colour secondaryColor = Colour.COVERING_BLACK;
			body.getCoverings().put(BodyCoveringType.FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.SPOTTED, CoveringModifier.SHORT, primaryColor, false, secondaryColor, false));
			body.getCoverings().put(BodyCoveringType.HAIR_FELINE_FUR, new Covering(BodyCoveringType.FELINE_FUR, CoveringPattern.NONE, primaryColor, false, secondaryColor, false));
			body.updateCoverings(true, true, true, true);
			body.getTail().setType(null, TailType.CAT_MORPH);
			
			// Body size adjustment
			if(body.getBreast().getRawSizeValue()>CupSize.B.getMeasurement()) {
				rand = Math.random();
				if(rand<0.35f) {
					body.getBreast().setSize(null, CupSize.B.getMeasurement());
				} else if(rand<0.70f) {
					body.getBreast().setSize(null, CupSize.A.getMeasurement());
				} else {
					body.getBreast().setSize(null, CupSize.AA.getMeasurement());
				}
			}
			
			body.setBodySize(BodySize.ZERO_SKINNY.getMedianValue());
			body.setMuscle(Muscle.FOUR_RIPPED.getMedianValue());
		}
	},
	
	CAT_MORPH_CARACAL("statusEffects/raceCatMorphCaracal",
			"caracal-morph",
			"caracal-morphs",
			"caracal",
			"caracal",
			"caracals",
			"caracals",
			Race.CAT_MORPH,
			Colour.RACE_CAT_MORPH_CARACAL,
			SubspeciesPreference.TWO_AVERAGE,
			"A cat-morph which resembles an anthropomorphised caracal. To be identified as a caracal-morph, a character must be a cat-morph with tufted ears.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getEar().setType(null, EarType.CAT_MORPH_TUFTED);
		}
	},

	// EQUINES:
	HORSE_MORPH("statusEffects/raceHorseMorph",
			"horse-morph",
			"horse-morphs",
			"horse-boy",
			"horse-girl",
			"horse-boys",
			"horse-girls",
			Race.HORSE_MORPH,
			Colour.RACE_HORSE_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal horse-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)),

	HORSE_MORPH_ZEBRA("statusEffects/raceHorseMorphZebra",
			"zebra-morph",
			"zebra-morphs",
			"zebra-boy",
			"zebra-girl",
			"zebra-boys",
			"zebra-girls",
			Race.HORSE_MORPH,
			Colour.BASE_BLACK,
			SubspeciesPreference.ONE_LOW,
			"A bipedal horse-morph which has black-and-white striped fur. To be identified as a zebra-morph, a character must be a horse-morph that has black-and-white striped hair, with a zebra-morph's tail.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {
		@Override
		public void applySpeciesChanges(Body body) {
			body.getCoverings().put(BodyCoveringType.HORSE_HAIR, new Covering(BodyCoveringType.HORSE_HAIR, CoveringPattern.STRIPED, CoveringModifier.SHORT, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HAIR_HORSE_HAIR, new Covering(BodyCoveringType.HAIR_HORSE_HAIR, CoveringPattern.NONE, Colour.COVERING_BLACK, false, Colour.COVERING_WHITE, false));
			body.getCoverings().put(BodyCoveringType.HUMAN, new Covering(BodyCoveringType.HUMAN, CoveringPattern.NONE, Colour.SKIN_EBONY, false, Colour.SKIN_EBONY, false));
			body.updateCoverings(true, true, true, true);
			
			if(body.getTail().getType()==TailType.HORSE_MORPH) {
				body.getTail().setType(null, TailType.HORSE_MORPH_ZEBRA);
			}
		}
	},

	// REPTILE:
	ALLIGATOR_MORPH("statusEffects/raceGatorMorph",
			"alligator-morph",
			"alligator-morphs",
			"alligator-boy",
			"alligator-girl",
			"alligator-boys",
			"alligator-girls",
			Race.ALLIGATOR_MORPH,
			Colour.RACE_ALLIGATOR_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal alligator-morph.",
			Util.newArrayListOfValues(WorldType.DOMINION)),

	// RODENTS:
	SQUIRREL_MORPH("statusEffects/raceSquirrelMorph",
			"squirrel-morph",
			"squirrel-morphs",
			"squirrel-boy",
			"squirrel-girl",
			"squirrel-boys",
			"squirrel-girls",
			Race.SQUIRREL_MORPH,
			Colour.RACE_SQUIRREL_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal squirrel-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)),
	
	//MOUSE_MORPH(Race.MOUSE_MORPH.getName(), Race.MOUSE_MORPH, RacialBody.MOUSE_MORPH, SubspeciesPreference.FIVE_ABUNDANT,
	//		"A typical bipedal "+Race.MOUSE_MORPH.getName()),
	
	RABBIT_MORPH("statusEffects/raceRabbitMorph",
			"rabbit-morph",
			"rabbit-morphs",
			"rabbit-boy",
			"rabbit-girl",
			"rabbit-boys",
			"rabbit-girls",
			Race.RABBIT_MORPH,
			Colour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A typical bipedal rabbit-morph.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)), //TODO move to fields

	RABBIT_MORPH_LOP("statusEffects/raceRabbitLopMorph",
			"lop-rabbit-morph",
			"lop-rabbit-morphs",
			"lop-rabbit-boy",
			"lop-rabbit-girl",
			"lop-rabbit-boys",
			"lop-rabbit-girls",
			Race.RABBIT_MORPH,
			Colour.RACE_RABBIT_MORPH,
			SubspeciesPreference.FOUR_ABUNDANT,
			"A bipedal rabbit-morph, with floppy ears instead of the usual upright ones.",
			Util.newArrayListOfValues(
					WorldType.DOMINION)) {  //TODO move to fields
		@Override
		public void applySpeciesChanges(Body body) {
			if(body.getEar().getType()==EarType.RABBIT_MORPH) {
				body.getEar().setType(null, EarType.RABBIT_MORPH_FLOPPY);
			}
		}
	}
	;

	
	private String name, namePlural, singularMaleName, singularFemaleName, pluralMaleName, pluralFemaleName;
	private Race race;
	private Colour colour;
	private SubspeciesPreference subspeciesPreferenceDefault;
	private String description;
	protected String SVGString;
	private String SVGStringDesaturated;
	private List<WorldType> worldLocations;
	
	private static Map<WorldType, List<Subspecies>> worldSpecies;
	private static List<Subspecies> dominionStormImmuneSpecies;
	static {
		worldSpecies = new HashMap<>();
		dominionStormImmuneSpecies = new ArrayList<>();
		
		for(Subspecies species : Subspecies.values()) {
			for(WorldType type : species.getWorldLocations()) {
				worldSpecies.putIfAbsent(type, new ArrayList<>());
				worldSpecies.get(type).add(species);

				if(type == WorldType.DOMINION && !species.getRace().isVulnerableToArcaneStorm()) {
					dominionStormImmuneSpecies.add(species);
				}
			}
		}
	}

	private Subspecies(
			String iconPathName,
			String name,
			String namePlural,
			String singularMaleName,
			String singularFemaleName,
			String pluralMaleName,
			String pluralFemaleName,
			Race race,
			Colour colour,
			SubspeciesPreference subspeciesPreferenceDefault,
			String description,
			List<WorldType> worldLocations) {
		
		this.name = name;
		this.namePlural = namePlural;

		this.singularMaleName = singularMaleName;
		this.singularFemaleName = singularFemaleName;
		
		this.pluralMaleName = pluralMaleName;
		this.pluralFemaleName = pluralFemaleName;
		
		this.race = race;
		this.colour = colour;
		this.subspeciesPreferenceDefault = subspeciesPreferenceDefault;
		this.description = description;
		
		this.worldLocations = worldLocations;
		
		if(iconPathName!=null) {
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + iconPathName + ".svg");
				if(is==null) {
					System.err.println("Error! Subspecies icon file does not exist (Trying to read from '"+iconPathName+"')! (Code 1)");
				}
				SVGString = Util.inputStreamToString(is);
	
				SVGString = SVGString.replaceAll("#ff2a2a", colour.getShades()[0]);
				SVGString = SVGString.replaceAll("#ff5555", colour.getShades()[1]);
				SVGString = SVGString.replaceAll("#ff8080", colour.getShades()[2]);
				SVGString = SVGString.replaceAll("#ffaaaa", colour.getShades()[3]);
				SVGString = SVGString.replaceAll("#ffd5d5", colour.getShades()[4]);
	
				is.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/" + iconPathName + ".svg");
				if(is==null) {
					System.err.println("Error! Subspecies icon file does not exist (Trying to read from '"+iconPathName+"')! (Code 2)");
				}
				SVGStringDesaturated = Util.inputStreamToString(is);
	
				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff2a2a", Colour.BASE_GREY.getShades()[0]);
				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff5555", Colour.BASE_GREY.getShades()[1]);
				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ff8080", Colour.BASE_GREY.getShades()[2]);
				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ffaaaa", Colour.BASE_GREY.getShades()[3]);
				SVGStringDesaturated = SVGStringDesaturated.replaceAll("#ffd5d5", Colour.BASE_GREY.getShades()[4]);
	
				is.close();
	
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} else {
			SVGString = "";
		}
	}

	public void applySpeciesChanges(Body body) {	
	}

	/**
	 * Changes that should be applied to any offspring of this species.
	 */
	public void applyOffspringSpeciesChanges(Body body) {
		applySpeciesChanges(body);
	}

	public static Subspecies getMainSubspeciesOfRace(Race race) {
		switch(race) {
			case NONE:
				break;
			case ALLIGATOR_MORPH:
				return Subspecies.ALLIGATOR_MORPH;
			case CAT_MORPH:
				return Subspecies.CAT_MORPH;
			case COW_MORPH:
				return Subspecies.COW_MORPH;
			case DEMON:
				return Subspecies.DEMON;
			case DOG_MORPH:
				return Subspecies.DOG_MORPH;
			case FOX_MORPH:
				return Subspecies.FOX_MORPH;
			case HORSE_MORPH:
				return Subspecies.HORSE_MORPH;
			case HUMAN:
				return Subspecies.HUMAN;
			case IMP:
				return Subspecies.IMP;
			case RABBIT_MORPH:
				return Subspecies.RABBIT_MORPH;
			case SQUIRREL_MORPH:
				return Subspecies.SQUIRREL_MORPH;
			case WOLF_MORPH:
				return Subspecies.WOLF_MORPH;
		}
		return Subspecies.HUMAN;
	}
	
	public static Subspecies getSubspeciesFromBody(Body body, Race race) {
		Subspecies subspecies = null;
		switch(race) {
			case NONE:
				break;
			case ALLIGATOR_MORPH:
				subspecies = Subspecies.ALLIGATOR_MORPH;
				break;
			case CAT_MORPH:
				subspecies = Subspecies.CAT_MORPH;
				FaceType faceType = body.getFace().getType();
				
				if(body.getHair().getType() == HairType.CAT_MORPH_SIDEFLUFF
					&& body.getEar().getType()==EarType.CAT_MORPH_TUFTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.FLUFFY
					&& body.getTail().getType()==TailType.CAT_MORPH_SHORT) {
					subspecies = Subspecies.CAT_MORPH_LYNX;
						
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.SPOTTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.FLUFFY
					&& body.getTail().getType()==TailType.CAT_MORPH) {
					subspecies = Subspecies.CAT_MORPH_LEOPARD_SNOW;
					
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.SPOTTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.SHORT
					&& body.getTail().getType()==TailType.CAT_MORPH) {
					subspecies = Subspecies.CAT_MORPH_LEOPARD;
					
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.SHORT
					&& body.getTail().getType()==TailType.CAT_MORPH_TUFTED) {
					subspecies = Subspecies.CAT_MORPH_LION;
					
				} else if((faceType == FaceType.CAT_MORPH_PANTHER || faceType == FaceType.HUMAN)
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.STRIPED
					&& body.getTail().getType()==TailType.CAT_MORPH) {
					subspecies = Subspecies.CAT_MORPH_TIGER;
					
				} else if(body.getCoverings().get(BodyCoveringType.FELINE_FUR).getPattern() == CoveringPattern.SPOTTED
					&& body.getCoverings().get(BodyCoveringType.FELINE_FUR).getModifier() == CoveringModifier.SHORT) {
					subspecies = Subspecies.CAT_MORPH_CHEETAH;
					
				} else if(body.getEar().getType()==EarType.CAT_MORPH_TUFTED) {
					subspecies = Subspecies.CAT_MORPH_CARACAL;
				}
				break;
				
			case COW_MORPH:
				subspecies = Subspecies.COW_MORPH;
				break;
			case DEMON:
				subspecies = Subspecies.DEMON;
				break;
			case IMP:
				subspecies = Subspecies.IMP;
				if(body.getHeightValue()>Height.NEGATIVE_ONE_TINY.getMinimumValue()) {
					subspecies = Subspecies.IMP_ALPHA;
				}
				break;
			case DOG_MORPH:
				subspecies = Subspecies.DOG_MORPH;
			
				if(body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPrimaryColour()==Colour.COVERING_BLACK
					&& (body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_BROWN
							|| body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_BROWN_DARK
							|| body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_TAN)
					&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPattern() == CoveringPattern.MARKED
					&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getModifier() == CoveringModifier.SHORT
					) {
					subspecies = Subspecies.DOG_MORPH_DOBERMANN;
				}
			
				if(body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPrimaryColour()==Colour.COVERING_BLACK
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getSecondaryColour()==Colour.COVERING_WHITE
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getPattern() == CoveringPattern.MARKED
						&& body.getCoverings().get(BodyCoveringType.CANINE_FUR).getModifier() == CoveringModifier.FLUFFY
						&& (body.getEar().getType()==EarType.DOG_MORPH_FOLDED || body.getEar().getType()==EarType.DOG_MORPH_POINTED)
						) {
						subspecies = Subspecies.DOG_MORPH_BORDER_COLLIE;
				}
				break;
			case FOX_MORPH:
				subspecies = Subspecies.FOX_MORPH;
				Covering fox_fur = body.getCoverings().get(BodyCoveringType.FOX_FUR);
				List<Colour> fennecColours = Util.newArrayListOfValues(Colour.COVERING_BLEACH_BLONDE, Colour.COVERING_TAN);
				
				if (fennecColours.contains(fox_fur.getPrimaryColour())
						&& (fennecColours.contains(fox_fur.getSecondaryColour()) || fox_fur.getPattern()==CoveringPattern.NONE)
						&& (body.getEar().getType()==EarType.FOX_MORPH_BIG)) {
					subspecies = body.getTail().getType() == TailType.FOX_MORPH_MAGIC
							?Subspecies.FOX_ASCENDANT_FENNEC
							: Subspecies.FOX_MORPH_FENNEC;
					
				} else if (body.getTail().getType() == TailType.FOX_MORPH_MAGIC) {
					subspecies = Subspecies.FOX_ASCENDANT;
				}
				break;
			case HORSE_MORPH:
				subspecies = Subspecies.HORSE_MORPH;
				
				if(((body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getPrimaryColour()==Colour.COVERING_BLACK
						&& body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getSecondaryColour()==Colour.COVERING_WHITE)
						|| (body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getPrimaryColour()==Colour.COVERING_WHITE
								&& body.getCoverings().get(BodyCoveringType.HORSE_HAIR).getSecondaryColour()==Colour.COVERING_BLACK))
						&& body.getTail().getType()==TailType.HORSE_MORPH_ZEBRA) {
						subspecies = Subspecies.HORSE_MORPH_ZEBRA;
					}
				break;
			case HUMAN:
				subspecies = Subspecies.HUMAN;
				break;
			case SQUIRREL_MORPH:
				subspecies = Subspecies.SQUIRREL_MORPH;
				break;
			case WOLF_MORPH:
				subspecies = Subspecies.WOLF_MORPH;
				break;
			case RABBIT_MORPH:
				subspecies = Subspecies.RABBIT_MORPH;
				if(body.getEar().getType()==EarType.RABBIT_MORPH_FLOPPY) {
					subspecies = Subspecies.RABBIT_MORPH_LOP;
				}
				break;
		}
		
		return subspecies;
	}
	
	private static void applyFoxColoring(Body body) {
		Colour c1 = body.getCoverings().get(BodyCoveringType.FOX_FUR).getPrimaryColour();
		Colour c2 = Colour.COVERING_WHITE;
		CoveringPattern pat = CoveringPattern.MARKED;
		double rand = Math.random();
		
		switch (c1) {
			case COVERING_BROWN:
				if(rand<0.5f) {
					c2 = Colour.COVERING_BROWN;
					pat = CoveringPattern.NONE;
				} else {
					c2 = Colour.COVERING_TAN;
				}
				break;
			case COVERING_BROWN_DARK:
				if(rand<0.5f) {
					c2 = Colour.COVERING_BROWN_DARK;
					pat = CoveringPattern.NONE;
				} else {
					c2 = Colour.COVERING_BROWN;
				}
				break;
			case COVERING_BLONDE:
			case COVERING_GINGER:
			default:
				// Set primary color to GINGER if we have a color that otherwise wouldn't be in this switch statement.
				if(c1 != Colour.COVERING_BLONDE) {c1 = Colour.COVERING_GINGER;}
				if(rand<0.025f) {
					c2 = Colour.COVERING_BLACK;
				} else if(rand<0.05f) {
					c2 = Colour.COVERING_BROWN;
				} else if(rand<0.5f) {
					c2 = Colour.COVERING_GREY;
				}
				break;
			case COVERING_SILVER:
			case COVERING_GREY:
				if(rand<0.5f) {
					c2 = c1;
					pat = CoveringPattern.NONE;
				}
				break;
			case COVERING_BLACK:
				if(rand<0.5f) {
					c2 = Colour.COVERING_BLACK;
					pat = CoveringPattern.NONE;
				} else {
					c2 = Colour.COVERING_GREY;
				}
				break;
			case COVERING_TAN:
			case COVERING_WHITE:
				c2 = c1;
				pat = CoveringPattern.NONE;
				break;
		}
		body.getCoverings().put(BodyCoveringType.FOX_FUR, new Covering(BodyCoveringType.FOX_FUR, pat, c1, false, c2, false));
	}
	
	public Subspecies getOffspringSubspecies() {
		return this;
	}
	
	public boolean isOffspringAlwaysMothersRace() {
		return false;
	}
	
	public boolean isShortStature() {
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNamePlural() {
		return namePlural;
	}
	
	public String getSingularMaleName() {
		return singularMaleName;
	}

	public String getSingularFemaleName() {
		return singularFemaleName;
	}
	
	public String getPluralMaleName() {
		return pluralMaleName;
	}

	public String getPluralFemaleName() {
		return pluralFemaleName;
	}

	public String getOffspringMaleName() {
		return pluralMaleName;
	}
	public String getOffspringMaleNameSingular() {
		return singularMaleName;
	}
	
	public String getOffspringFemaleName() {
		return pluralFemaleName;
	}
	
	public String getOffspringFemaleNameSingular() {
		return singularFemaleName;
	}
	
	public Race getRace() {
		return race;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public SubspeciesPreference getSubspeciesPreferenceDefault() {
		return subspeciesPreferenceDefault;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getSVGString(GameCharacter character) {
		return SVGString;
	}
	
	public String getSVGStringDesaturated(GameCharacter character) {
		return SVGStringDesaturated;
	}

	public List<WorldType> getWorldLocations() {
		return worldLocations;
	}

	public static Map<WorldType, List<Subspecies>> getWorldSpecies() {
		return worldSpecies;
	}

	public static List<Subspecies> getDominionStormImmuneSpecies() {
		return dominionStormImmuneSpecies;
	}
}