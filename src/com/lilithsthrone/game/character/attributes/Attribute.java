package com.lilithsthrone.game.character.attributes;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public enum Attribute {

	HEALTH_MAXIMUM(0,
			1,
			1000,
			"energy",
			"Energy",
			"healthIcon",
			Colour.ATTRIBUTE_HEALTH,
			"energy", "lethargy", null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How much physical energy you have. You will be defeated in combat if this reaches 0.<br/><br/>"
								+ "<b>Stamina = " + GameCharacter.HEALTH_CALCULATION + "</b>";
					else
						return UtilText.parse(owner,
								"How much physical energy [npc.name] has. [npc.She] will be defeated in combat if this reaches 0.");
				}
			},

	MANA_MAXIMUM(0,
			1,
			1000,
			"aura",
			"Aura",
			"manaIcon",
			Colour.ATTRIBUTE_MANA,
			"aura-boost", "aura-drain", null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "A measure of the amount of arcane energy in your aura.<br/><br/>"
								+ "<b>Aura = " + GameCharacter.MANA_CALCULATION + "</b>";
					else
						return UtilText.parse(owner,
								"How much arcane energy [npc.name] has in [npc.her] aura.");
				}
			},

	EXPERIENCE(0,
			0,
			1000000,
			"experience",
			"Experience",
			"experienceIcon",
			Colour.GENERIC_EXPERIENCE,
			"learning", "forgetfulness", null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How much progress you have made to the next level.";
					else
						return UtilText.parse(owner,
								"How much progress [npc.name] has made to the next level.");
				}
			},

	AROUSAL(0,
			0,
			100,
			"arousal",
			"Arousal",
			"arousalIcon",
			Colour.ATTRIBUTE_AROUSAL,
			"long-lasting", "prematurity", null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer())
						return "How aroused you currently are. You will orgasm when your arousal maxes out.";
					else
						return UtilText.parse(owner,
								"How aroused [npc.name] is. [npc.She] will orgasm when [npc.her] arousal maxes out.");
				}
			},
	
	LUST(0,
			0,
			100,
			"lust",
			"Lust",
			"arousalIcon",
			Colour.ATTRIBUTE_LUST,
			"passion", "indifference", null) {
				@Override
				public String getDescription(GameCharacter owner) {
					if(owner.isPlayer()) {
						return "How desperate for sexual contact you are. Your lust will move towards your resting lust value over time.<br/>";
					} else {
						return UtilText.parse(owner,
								"How desperate for sexual contact [npc.name] is.");
					}
				}
			},

	MAJOR_PHYSIQUE(0,
			0,
			100,
			"physique",
			"Physique",
			"strengthIcon",
			Colour.ATTRIBUTE_PHYSIQUE,
			"power", "weakness", Util.newArrayListOfValues("<b>+2</b> <b style='color: " + Colour.ATTRIBUTE_HEALTH.toWebHexString() + "'>Energy</b> per 1 physique")) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of how physically healthy you are, physique <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>passively increases</b> your"
									+ " <b style='color:" + Colour.ATTRIBUTE_HEALTH.toWebHexString() + ";'>maximum energy</b>.";
							else
								return UtilText.parse(owner,
										"A measure of [npc.namePos] physical health.");
						}
					},

	MAJOR_ARCANE(0,
			0,
			100,
			"arcane",
			"Arcane",
			"intelligenceIcon",
			Colour.ATTRIBUTE_ARCANE,
			"arcane-boost", "arcane-drain", Util.newArrayListOfValues("<b>+2</b> <b style='color: " + Colour.ATTRIBUTE_MANA.toWebHexString() + "'>Aura</b> per 1 arcane")) {
						@Override
						public String getDescription(GameCharacter owner) {
							if(owner.isPlayer())
								return "A measure of your affinity with the arcane. This <b style='color:" + Colour.GENERIC_GOOD.toWebHexString() + ";'>passively increases</b> your"
									+ " <b style='color:" + Colour.ATTRIBUTE_MANA.toWebHexString() + ";'>maximum Aura</b>.";
							else
								return UtilText.parse(owner,
										"A measure of [npc.namePos] affinity with the arcane.");
						}
					},

	// Miscellaneous attributes:

	FERTILITY(10, -100, 100, "fertility", "Fertility", "shieldIcon", Colour.GENERIC_SEX, "fertility", "infertility", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the likelihood of becoming pregnant.";
		}
	},
	
	VIRILITY(10, -100, 100, "virility", "Virility", "shieldIcon", Colour.GENERIC_SEX, "virility", "sterility", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the likelihood of impregnating someone.";
		}
	},
	
	// Combat attributes:

	CRITICAL_CHANCE(5, 0, 100, "critical hit chance", "Critical chance", "shieldIcon", Colour.ATTRIBUTE_HEALTH, "luck", "misfortune", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point gives 1% chance to perform a critical hit.";
		}
	},
	CRITICAL_DAMAGE(150, 100, 500, "critical hit damage", "Critical damage", "shieldIcon", Colour.ATTRIBUTE_HEALTH, "impact", "failure", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Each point gives 1% extra critical hit damage.";
		}
	},
	
	DODGE_CHANCE(0, 0, 100, "dodge chance", "Dodge chance", "shieldIcon", Colour.GENERIC_EXCELLENT, "evasion", "sluggishness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the chance of dodging attacks.";
		}
	},
	MISS_CHANCE(0, 0, 100, "miss chance", "Miss chance", "shieldIcon", Colour.GENERIC_TERRIBLE, "poor aim", "true striking", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases the chance of missing with attacks in combat.";
		}
	},

	// Resistances:

	RESISTANCE_PHYSICAL(0, -100, 100, "physical resistance", "Physical resist", "shieldIcon", Colour.DAMAGE_TYPE_PHYSICAL, "toughness", "softness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Reduces physical damage taken.";
		}
	},
	
	RESISTANCE_LUST(0, -100, 100, "lust resistance", "Lust resist", "shieldIcon", Colour.GENERIC_SEX, "chastity", "temptation", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Reduces lust damage taken.";
		}
	},
	
	RESISTANCE_FIRE(0, -100, 100, "fire resistance", "Fire resist", "shieldIcon", Colour.DAMAGE_TYPE_FIRE, "extinguishing", "flammability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Reduces fire damage taken.";
		}
	},
	
	RESISTANCE_ICE(0, -100, 100, "cold resistance", "Cold resist", "shieldIcon", Colour.DAMAGE_TYPE_COLD, "warmth", "frostbite", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Reduces cold damage taken.";
		}
	},
	
	RESISTANCE_POISON(0, -100, 100, "poison resistance", "Poison resist", "shieldIcon", Colour.DAMAGE_TYPE_POISON, "anti-venom", "sickness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Reduces poison damage taken.";
		}
	},

	// Damages:

	DAMAGE_UNARMED(0, -100, 100, "unarmed damage", "Unarmed damage", "swordIcon", Colour.ATTRIBUTE_HEALTH, "martial arts", "martial incompetence", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage dealt from unarmed attacks, including special attacks obtained from non-human bodyparts.";
		}
	},
	
	DAMAGE_MELEE_WEAPON(0, -100, 100, "melee weapon damage", "Melee Weapon damage", "swordIcon", Colour.ATTRIBUTE_HEALTH, "melee mastery", "melee incompetence", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage dealt from attacks by melee weapons.";
		}
	},
	
	DAMAGE_RANGED_WEAPON(0, -100, 100, "ranged weapon damage", "Ranged weapon damage", "swordIcon", Colour.ATTRIBUTE_HEALTH, "ranged mastery", "ranged incompetence", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage dealt from attacks by ranged weapons.";
		}
	},
	
	DAMAGE_PHYSICAL(0, -100, 100, "physical damage", "Physical damage", "swordIcon", Colour.DAMAGE_TYPE_PHYSICAL, "force", "softness", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases physical damage.";
		}
	},
	
	DAMAGE_LUST(0, -100, 100, "lust damage", "Lust damage", "swordIcon", Colour.GENERIC_SEX, "seduction", "repulsion", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases lust damage.";
		}
	},
	
	DAMAGE_FIRE(0, -100, 100, "fire damage", "Fire damage", "swordIcon", Colour.DAMAGE_TYPE_FIRE, "inferno", "dying embers", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases fire damage.";
		}
	},
	
	DAMAGE_ICE(0, -100, 100, "cold damage", "Cold damage", "swordIcon", Colour.DAMAGE_TYPE_COLD, "blizzard", "slush", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases cold damage.";
		}
	},
	
	DAMAGE_POISON(0, -100, 100, "poison damage", "Poison damage", "swordIcon", Colour.DAMAGE_TYPE_POISON, "venom", "dilution", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases poison damage.";
		}
	},
	
	// Racial:
	
	DAMAGE_ANGEL(0, -100, 100, "angelic damage", "Angelic damage", "swordIcon", Colour.RACE_ANGEL, "angelic-obliteration", "angelic-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs angels.";
		}
	},
	DAMAGE_CAT_MORPH(0, -100, 100, "cat-morph damage", "Cat-morph damage", "swordIcon", Colour.RACE_CAT_MORPH, "cat-morph-obliteration", "cat-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs cat-morphs.";
		}
	},
	DAMAGE_COW_MORPH(0, -100, 100, "cow-morph damage", "Cow-morph damage", "swordIcon", Colour.RACE_COW_MORPH, "cow-morph-obliteration", "cow-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs cow-morphs.";
		}
	},
	DAMAGE_DEMON(0, -100, 100, "demonic damage", "Demonic damage", "swordIcon", Colour.RACE_DEMON, "demonic-obliteration", "demonic-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs demons.";
		}
	},
	DAMAGE_DOG_MORPH(0, -100, 100, "dog-morph damage", "Dog-morph damage", "swordIcon", Colour.RACE_DOG_MORPH, "dog-morph-obliteration", "dog-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs dog-morphs.";
		}
	},
	DAMAGE_HORSE_MORPH(0, -100, 100, "horse-morph damage", "Horse-morph damage", "swordIcon", Colour.RACE_HORSE_MORPH, "horse-morph-obliteration", "horse-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs horse-morphs.";
		}
	},
	DAMAGE_IMP(0, -100, 100, "imp damage", "Imp damage", "swordIcon", Colour.RACE_IMP, "impish-obliteration", "impish-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs imps.";
		}
	},
	DAMAGE_HUMAN(0, -100, 100, "human damage", "Human damage", "swordIcon", Colour.RACE_HUMAN, "human-obliteration", "human-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs humans.";
		}
	},
	DAMAGE_SQUIRREL_MORPH(0, -100, 100, "squirrel-morph damage", "Squirrel-morph damage", "swordIcon", Colour.RACE_SQUIRREL_MORPH, "squirrel-morph-obliteration", "squirrel-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs squirrel-morphs.";
		}
	},
	DAMAGE_RABBIT_MORPH(0, -100, 100, "rabbit-morph damage", "Rabbit-morph damage", "swordIcon", Colour.RACE_RABBIT_MORPH, "rabbit-morph-obliteration", "rabbit-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs rabbit-morphs.";
		}
	},
	DAMAGE_ALLIGATOR_MORPH(0, -100, 100, "alligator-morph damage", "Alligator-morph damage", "swordIcon", Colour.RACE_ALLIGATOR_MORPH, "alligator-morph-obliteration", "alligator-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs alligator-morphs.";
		}
	},
	DAMAGE_WOLF_MORPH(0, -100, 100, "wolf-morph damage", "Wolf-morph damage", "swordIcon", Colour.RACE_WOLF_MORPH, "wolf-morph-obliteration", "wolf-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs wolf-morphs.";
		}
	},
	DAMAGE_FOX_MORPH(0, -100, 100, "fox-morph damage", "Fox-morph damage", "swordIcon", Colour.RACE_FOX_MORPH, "fox-morph-obliteration", "fox-morph-mercy", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases damage vs fox-morphs.";
		}
	},
	
	RESISTANCE_ANGEL(0, -100, 100, "angelic resistance", "Angelic resistance", "shieldIcon", Colour.RACE_ANGEL, "angelic-immunity", "angelic-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by angels.";
		}
	},
	RESISTANCE_CAT_MORPH(0, -100, 100, "cat-morph resistance", "Cat-morph resistance", "shieldIcon", Colour.RACE_CAT_MORPH, "cat-morph-immunity", "cat-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by cat-morphs.";
		}
	},
	RESISTANCE_COW_MORPH(0, -100, 100, "cow-morph resistance", "Cow-morph resistance", "shieldIcon", Colour.RACE_COW_MORPH, "cow-morph-immunity", "cow-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by cow-morphs.";
		}
	},
	RESISTANCE_DEMON(0, -100, 100, "demonic resistance", "Demonic resistance", "shieldIcon", Colour.RACE_DEMON, "demonic-immunity", "demonic-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by demons.";
		}
	},
	RESISTANCE_DOG_MORPH(0, -100, 100, "dog-morph resistance", "Dog-morph resistance", "shieldIcon", Colour.RACE_DOG_MORPH, "dog-morph-immunity", "dog-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by dog-morphs.";
		}
	},
	RESISTANCE_HORSE_MORPH(0, -100, 100, "horse-morph resistance", "Horse-morph resistance", "shieldIcon", Colour.RACE_HORSE_MORPH, "horse-morph-immunity", "horse-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by horse-morphs.";
		}
	},
	RESISTANCE_IMP(0, -100, 100, "imp resistance", "Imp resistance", "shieldIcon", Colour.RACE_IMP, "impish-immunity", "impish-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by imps.";
		}
	},
	RESISTANCE_HUMAN(0, -100, 100, "human resistance", "Human resistance", "shieldIcon", Colour.RACE_HUMAN, "human-immunity", "human-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by humans.";
		}
	},
	RESISTANCE_SQUIRREL_MORPH(0, -100, 100, "squirrel-morph resistance", "Squirrel-morph resistance", "shieldIcon", Colour.RACE_SQUIRREL_MORPH, "squirrel-morph-immunity", "squirrel-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by squirrel-morphs.";
		}
	},
	RESISTANCE_RABBIT_MORPH(0, -100, 100, "rabbit-morph resistance", "Rabbit-morph resistance", "shieldIcon", Colour.RACE_RABBIT_MORPH, "rabbit-morph-immunity", "rabbit-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by rabbit-morphs.";
		}
	},
	RESISTANCE_ALLIGATOR_MORPH(0, -100, 100, "alligator-morph resistance", "Alligator-morph resistance", "shieldIcon", Colour.RACE_ALLIGATOR_MORPH, "alligator-morph-immunity", "alligator-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by alligator-morphs.";
		}
	},
	RESISTANCE_WOLF_MORPH(0, -100, 100, "wolf-morph resistance", "Wolf-morph resistance", "shieldIcon", Colour.RACE_WOLF_MORPH, "wolf-morph-immunity", "wolf-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by wolf-morphs.";
		}
	},
	RESISTANCE_FOX_MORPH(0, -100, 100, "fox-morph resistance", "Fox-morph resistance", "shieldIcon", Colour.RACE_FOX_MORPH, "fox-morph-immunity", "fox-morph-vulnerability", null) {
		@Override
		public String getDescription(GameCharacter owner) {
			return "Increases resistance vs damage inflicted by fox-morphs.";
		}
	},
	;

	
	private int baseValue;
	private	int lowerLimit;
	private	int upperLimit;
	private String name;
	private String nameAbbreviation;
	private String positiveEnchantment;
	private String negativeEnchantment;
	private Colour colour;
	public static final List<Attribute> attributeBonusesForEnchanting = new ArrayList<>(), baseAttributesGood = new ArrayList<>();
	private List<String> extraEffects;
	private String SVGString;

	static {
		attributeBonusesForEnchanting.add(Attribute.CRITICAL_CHANCE);
		attributeBonusesForEnchanting.add(Attribute.CRITICAL_DAMAGE);


		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_PHYSICAL);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_FIRE);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_ICE);
		attributeBonusesForEnchanting.add(Attribute.RESISTANCE_POISON);

		attributeBonusesForEnchanting.add(Attribute.DAMAGE_PHYSICAL);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_FIRE);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_ICE);
		attributeBonusesForEnchanting.add(Attribute.DAMAGE_POISON);

		baseAttributesGood.add(Attribute.MAJOR_PHYSIQUE);
		baseAttributesGood.add(Attribute.MAJOR_ARCANE);
	}

	private Attribute(int baseValue,
			int lowerLimit,
			int upperLimit,
			String name,
			String nameAbbreviation,
			String pathName,
			Colour colour,
			String positiveEnchantment, String negativeEnchantment, List<String> extraEffects) {
		
		this.baseValue = baseValue;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.name = name;
		this.nameAbbreviation = nameAbbreviation;
		this.colour = colour;
		this.positiveEnchantment = positiveEnchantment;
		this.negativeEnchantment = negativeEnchantment;
		this.extraEffects = extraEffects;

		try {
			InputStream is = this.getClass().getResourceAsStream("/com/lilithsthrone/res/UIElements/" + pathName + ".svg");
			if(is==null) {
				System.err.println("Error! Attribute icon file does not exist (Trying to read from '"+pathName+"')!");
			}
			SVGString = Util.inputStreamToString(is);

			if (colour.getShades() != null) {
				SVGString = SVGString.replaceAll("#ff2a2a", colour.getShades()[0]);
				SVGString = SVGString.replaceAll("#ff5555", colour.getShades()[1]);
				SVGString = SVGString.replaceAll("#ff8080", colour.getShades()[2]);
				SVGString = SVGString.replaceAll("#ffaaaa", colour.getShades()[3]);
				SVGString = SVGString.replaceAll("#ffd5d5", colour.getShades()[4]);
			}

			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Attribute[] getCoreAttributes() {
		return new Attribute[] { MAJOR_PHYSIQUE, MAJOR_ARCANE };
	}

	public int getBaseValue() {
		return baseValue;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}
	
	public int getUpperLimit() {
		return upperLimit;
	}

	public String getName() {
		return name;
	}
	
	public String getColouredName(String tag) {
		return "<"+tag+" style='color:"+this.getColour().toWebHexString()+";'>"+name+"</"+tag+">";
	}

	public String getAbbreviatedName() {
		return nameAbbreviation;
	}

	public abstract String getDescription(GameCharacter owner);

	public Colour getColour() {
		return colour;
	}

	public String getAttributeChangeText(GameCharacter target, float value) {
		if(target==null) {
			return "";
		}
		
		if(target.isPlayer()) {
			if (value > 0) {
				return "<p style='text-align:center;'>"
							+ "You gain <b>"+value+"</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>";
	
			} else if (value < 0) {
				return "<p style='text-align:center;'>"
							+ "You lose <b>" + value + "</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>";
	
			} else {
				return "<p style='text-align:center;'>"
							+ "Your <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b> doesn't change..."
						+ "</p>";
			}
		} else {
			if (value > 0) {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] gains <b>" + value + "</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>");
	
			} else if (value < 0) {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.Name] loses <b>" + value + "</b> <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b>!"
						+ "</p>");
				
	
			} else {
				return UtilText.parse(target,
						"<p>"
							+ "[npc.NamePos] <b style='color:" + this.getColour().toWebHexString() + ";'>" + this.getName() + "</b> remains unchanged..."
						+ "</p>");
			}
		}
	}

	private StringBuilder descriptionSB = new StringBuilder();

	public String getEffectsAsStringList() {
		descriptionSB = new StringBuilder();

		if (extraEffects != null)
			for (String s : extraEffects)
				descriptionSB.append("<br/>" + s);

		return descriptionSB.toString();
	}

	public String getPositiveEnchantment() {
		return positiveEnchantment;
	}

	public String getNegativeEnchantment() {
		return negativeEnchantment;
	}

	public String getSVGString() {
		return SVGString;
	}

}
