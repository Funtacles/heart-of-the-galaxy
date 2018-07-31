package com.lilithsthrone.game.character.body;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.0
 * @version 0.2.7
 * @author Innoxia
 */
public class Arm implements BodyPartInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	protected ArmType type;
	protected int armRows;
	protected BodyHair underarmHair;

	public Arm(ArmType type, int armRows) {
		this.type = type;
		this.armRows = armRows;
		underarmHair = BodyHair.ZERO_NONE;
	}
	
	@Override
	public ArmType getType() {
		return type;
	}

	@Override
	public String getDeterminer(GameCharacter gc) {
		return type.getDeterminer(gc);
	}

	@Override
	public String getName(GameCharacter gc) {
		return type.getName(gc);
	}
	
	@Override
	public String getNameSingular(GameCharacter gc) {
		return type.getNameSingular(gc);
	}

	@Override
	public String getNamePlural(GameCharacter gc) {
		return type.getNamePlural(gc);
	}
	
	@Override
	public String getDescriptor(GameCharacter gc) {
		List<String> descriptorList = new ArrayList<>();
		
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(type.getDescriptor(gc));
		descriptorList.add(Util.randomItemFrom(gc.getBodyShape().getLimbDescriptors()));
		
		return UtilText.returnStringAtRandom(descriptorList.toArray(new String[]{}));
	}

	public String setType(GameCharacter owner, ArmType type) {
		if (type == getType()) {
			if (owner.isPlayer()) {
				return "<p style='text-align:center;'>[style.colourDisabled(You already have the [pc.arms] of [pc.a_armRace], so nothing happens...)]</p>";
			} else {
				return UtilText.parse(owner, "<p style='text-align:center;'>[style.colourDisabled([npc.Name] already has the [npc.arms] of [npc.a_armRace], so nothing happens...)]</p>");
			}
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "Your [pc.arms+] suddenly feel hot and itchy, and you gasp as you realise that they're starting to transform."
							+ " You let out a painful cry as your upper limbs twist and transform, leaving you powerless to do anything other than look on as they reshape themselves into a new form.");
			} else {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "[npc.Name] fidgets about as [npc.she] feels [npc.her] [npc.arms+] growing hot and itchy, and [npc.she] gasps as [npc.she] realises that they're starting to transform."
							+ " [npc.She] lets out a painful cry as [npc.her] upper limbs twist and transform, leaving [npc.her] powerless to do anything other than look on as they reshape themselves into a new form.");
			}
		}

		// Parse existing content before transformation:
		String s = UtilText.parse(owner, UtilText.transformationContentSB.toString());
		UtilText.transformationContentSB.setLength(0);
		UtilText.transformationContentSB.append(s);
		this.type = type;
		
		switch (type) {
			case HUMAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Thankfully, the transformation only lasts a matter of moments, leaving you with normal-looking human arms, complete with human hands.<br/>"
								+ "You now have [style.boldHuman(human arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Thankfully for [npc.herHim], the transformation only lasts a matter of moments, leaving [npc.herHim] with normal-looking human arms, complete with human hands.<br/>"
								+ "[npc.Name] now has [style.boldHuman(human arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case DOG_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as little blunt claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little leathery pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldDogMorph(dog-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as little blunt claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little leathery pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldDogMorph(dog-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case FOX_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down, you see your fur growing over the backs of your new hands as sharp claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.</br>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldFoxMorph(fox-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.</br>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldFoxMorph(fox-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case LYCAN:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down, you see your fur growing over the backs of your new hands as sharp claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in tough leathery pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldWolfMorph(wolf-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in tough leathery pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldWolfMorph(wolf-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case CAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as sharp, retractable claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little pink pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldCatMorph(cat-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp, retractable claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little pink pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldCatMorph(cat-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case HORSE_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your hair growing over the backs of your new hands as tough, hoof-like nails push out in place of regular, human-like ones."
								+ " Despite their appearance, you're relieved to discover that your hands have lost none of their dexterity."
								+ " As the transformation comes to an end, you see that at your upper-biceps, your new hair smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "You're left with anthropomorphic, [style.boldHorseMorph(horse-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] hair growing over the backs of [npc.her] new hands as tough, hoof-like nails push out in place of regular, human-like ones."
								+ " Despite their appearance, [npc.sheIs] relieved to discover that [npc.her] hands have lost none of their dexterity."
								+ " As the transformation comes to an end, [npc.she] sees that at [npc.her] upper-biceps, [npc.her] new hair smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "[npc.Name] is left with anthropomorphic, [style.boldHorseMorph(horse-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case COW_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your hair growing over the backs of your new hands as tough, hoof-like nails push out in place of regular, human-like ones."
								+ " Despite their appearance, you're relieved to discover that your hands have lost none of their dexterity."
								+ " As the transformation comes to an end, you see that at your upper-biceps, your new hair smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "You're left with anthropomorphic, [style.boldCowMorph(cow-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] hair growing over the backs of [npc.her] new hands as tough, hoof-like nails push out in place of regular, human-like ones."
								+ " Despite their appearance, [npc.sheIs] relieved to discover that [npc.her] hands have lost none of their dexterity."
								+ " As the transformation comes to an end, [npc.she] sees that at [npc.her] upper-biceps, [npc.her] new hair smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "[npc.Name] is left with anthropomorphic, [style.boldCowMorph(cow-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case SQUIRREL_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as sharp little claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little pink pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldSquirrelMorph(squirrel-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp little claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little pink pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldSquirrelMorph(squirrel-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case RAT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new hands as sharp little claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in little pink pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldRatMorph(rat-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] hands as sharp little claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in little pink pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldRatMorph(rat-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
			case RABBIT_MORPH:
				if (owner.isPlayer()) {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [pc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " you see your fur growing over the backs of your new paw-like hands as little blunt claws push out to replace your fingernails."
								+ " Your palms rapidly transform to be covered in soft little pads, and at your upper-biceps, your new fur smoothly transitions into the [pc.skin] that's covering the rest of your body.<br/>"
								+ "As the transformation comes to an end, you're left with anthropomorphic, [style.boldRabbitMorph(rabbit-like arms and hands)], which are covered in [pc.armFullDescription]."
							+ "</p>");
				} else {
					UtilText.transformationContentSB.append(
								" Within a matter of moments, a layer of [npc.armFullDescription] has quickly grown over them, and, looking down,"
										+ " [npc.she] sees [npc.her] fur growing over the backs of [npc.her] new paw-like hands as little blunt claws push out to replace [npc.her] fingernails."
								+ " [npc.Her] palms rapidly transform to be covered in soft little pads, and at [npc.her] upper-biceps, [npc.her] new fur smoothly transitions into the [npc.skin] that's covering the rest of [npc.her] body.<br/>"
								+ "As the transformation comes to an end, [npc.name] is left with anthropomorphic, [style.boldRabbitMorph(rabbit-like arms and hands)], which are covered in [npc.armFullDescription]."
							+ "</p>");
				}
				break;
		}
		return UtilText.parse(owner, UtilText.transformationContentSB.toString())
				+ "<br/><br/>"
				+ owner.postTransformationCalculation()
				+ "</p>";
	}

	public int getArmRows() {
		return armRows;
	}

	public String setArmRows(GameCharacter owner, int armRows) {
		int currentArmRows = getArmRows();
		armRows = Math.max(1, Math.min(armRows, 3));
		if (armRows == currentArmRows) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
		}
		
		UtilText.transformationContentSB.setLength(0);
		
		if (armRows < currentArmRows) {
			boolean losesTwoPairs = (armRows + 2) == currentArmRows;
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel a strange pressure building up around the base of "
							+ (losesTwoPairs
								? "your two extra pairs"
								: (armRows == 2 
									? "the lowest of your extra pairs" 
									: "your extra pair"))
							+ " of [pc.arms], and before you can react, they rapidly shrink away into the [pc.skin] of your torso.<br/>" 
							+ "You now have [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], covered in [pc.armFullDescriptionColour]."
						+ "</p>");
			} else {
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] glances worriedly down at "
							+ (losesTwoPairs
								? "[npc.her] two extra pairs"
								: (armRows == 2 
									? "the lowest of [npc.her] extra pairs" 
									: "[npc.her] extra pair"))
							+ " of [npc.arms], and before [npc.she] can react, they rapidly shrink away into the [npc.skin] of [npc.her] torso.<br/>" 
							+ "[npc.She] now has [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], covered in [npc.armFullDescriptionColour]."
						+ "</p>"));
			}
			
		} else {
			boolean gainsTwoPairs = (armRows - 2) == currentArmRows;
			if (owner.isPlayer()) {
				UtilText.transformationContentSB.append(
						"<p>"
							+ "You feel a strange pressure building up down the sides of your torso, and before you have time to react, "
								+ (gainsTwoPairs
									? "two extra pairs"
									: "an extra pair")
							+ " of [pc.arms] rapidly grow out of the [pc.skin] of your lower torso.<br/>"
							+ "You now have [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [pc.arms])], covered in [pc.armFullDescriptionColour]."
						+ "</p>");
			} else {
				UtilText.transformationContentSB.append(UtilText.parse(owner,
						"<p>"
							+ "[npc.Name] glances worriedly down at [npc.her] torso, and before [npc.she] can react, "
								+ (gainsTwoPairs
									? "two extra pairs"
									: "an extra pair")
							+ " of [npc.arms] rapidly grow out of the [npc.skin] of [npc.her] lower torso.<br/>"
							+ "[npc.She] now has [style.boldTfLesser(" + Util.intToString(armRows) + " pair"+ (armRows > 1 ? "s" : "") + " of [npc.arms])], covered in [npc.armFullDescriptionColour]."
						+ "</p>"));
			}
		}
		
		this.armRows = armRows;
		
		return UtilText.transformationContentSB.toString();
	}

	public BodyHair getUnderarmHair() {
		return underarmHair;
	}

	public Covering getUnderarmHairType(GameCharacter owner) {
		return owner.getCovering(owner.getBodyHairCoveringType(owner.getArmType().getRace()));
	}

	public String setUnderarmHair(GameCharacter owner, BodyHair underarmHair) {
		if(owner==null) {
			this.underarmHair = underarmHair;
			return "";
		}
		
		if(getUnderarmHair() == underarmHair) {
			return "<p style='text-align:center;'>[style.colourDisabled(Nothing happens...)]</p>";
			
		} else {
			UtilText.transformationContentSB.setLength(0);
			
			switch(underarmHair) {
				case ZERO_NONE:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>There is no longer any trace of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>There is no longer any trace of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case ONE_STUBBLE:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have stubbly patches of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has stubbly patches of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case TWO_MANICURED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a well-manicured patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a well-manicured patch of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case THREE_TRIMMED:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have trimmed patches of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has trimmed patches of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case FOUR_NATURAL:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have a natural amount of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has a natural amount of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case FIVE_UNKEMPT:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have an unkempt mass of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has an unkempt mass of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case SIX_BUSHY:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have thick, bushy masses of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has thick, bushy masses of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
				case SEVEN_WILD:
					if(owner.isPlayer()) {
						UtilText.transformationContentSB.append("<p>You now have wild, bushy masses of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in your armpits.</p>");
					} else {
						UtilText.transformationContentSB.append(UtilText.parse(owner, "<p>[npc.Name] now has wild, bushy masses of "+getUnderarmHairType(owner).getFullDescription(owner, true)+" in [npc.her] armpits.</p>"));
					}
					break;
			}
		}
		
		this.underarmHair = underarmHair;

		return UtilText.transformationContentSB.toString();
	}
}
