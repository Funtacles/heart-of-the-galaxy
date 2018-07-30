package com.lilithsthrone.game.sex.sexActions.baseActionsMisc;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.attributes.LustLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.ArousalIncrease;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexPositionType;
import com.lilithsthrone.game.sex.sexActions.SexAction;
import com.lilithsthrone.game.sex.sexActions.SexActionPriority;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.79
 * @version 0.2.8
 * @author Innoxia
 */
public class GenericActions {
	
	public static final SexAction PLAYER_HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Calming Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] doesn't like having sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getActivePartner().getPsychoactiveFluidsIngested().isEmpty()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "Wanting to take advantage of the fact that [npc2.name] is under the strong effect of a psychoactive substance, you lean towards [npc2.herHim] and [npc.moan],"
					+ " [npc.speech(You aren't really interested in having sex with me, are you?)]"
				+ "</p>"
				+ "<p>"
					+ "[npc2.NamePos] [npc2.eyes] glaze over a little as [npc2.she] answers,"
					+ " [npc2.speech(Yes... I... I don't know why I'm having sex with you...)]"
				+ "</p>"
				+ "<p>"
					+ "Pushing a little further, you continue,"
					+ " [npc.speech(You'd rather I wasn't fucking you right now, isn't that right?)]"
				+ "</p>"
				+ "<p>"
					+ (Sex.isDom(Sex.getActivePartner())
							?"As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] starts to sound a lot calmer, and sighs,"
								+ " [npc2.speech(This isn't really all that fun...)]"
							:"As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] starts to sound a lot more distressed, and cries out,"
								+ " [npc2.speech(Wait, w-why is this happening?! Please, stop it! Get away from me!)]")
				+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getActivePartner().incrementLust(-50);
		}
	};
	
	public static final SexAction PLAYER_HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Lustful Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] loves to have sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getActivePartner().getPsychoactiveFluidsIngested().isEmpty()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
				return "Wanting to take advantage of the fact that [npc2.name] is under the strong effect of a psychoactive substance, you lean towards [npc2.herHim] and [npc.moan],"
						+ " [npc.speech(You love having sex with me, don't you?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc2.NamePos] [npc2.eyes] glaze over a little as [npc2.she] answers,"
						+ " [npc2.speech(Yes... I... I love having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further, you continue,"
						+ " [npc.speech(You love begging for me to fuck you, isn't that right?)]"
					+ "</p>"
					+ "<p>"
						+ "As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] starts to sound a lot more eager, and [npc2.moansVerb],"
						+ " [npc2.speech(Yes [npc.name]! I love it! Please, fuck me! I <i>need</i> you to fuck me!)]"
					+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getActivePartner().incrementLust(50);
		}
	};
	
	
	
	public static final SexAction PLAYER_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Main.game.getPlayer().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isDom(Main.game.getPlayer())
					&& !Sex.isConsensual()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [npc2.name].";
		}
		
		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc2.name] as you desperately try to struggle out of [npc2.her] grip, but your efforts prove to be in vain as [npc2.she] easily keeps you pinned back against the wall.",
						"Struggling against [npc2.name], you let out [npc.a_sob+] as you weakly try to wriggle out of [npc2.her] grasp, but [npc2.her] grip is too strong for you, and [npc2.she] easily keeps you pushed you back against the wall.",
						"Begging for [npc2.herHim] to leave you alone, you desperately struggle against [npc2.name], [npc.sobbing] in distress as [npc2.she] pushes you back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"You let out [npc.a_sob+] as you try to crawl away from [npc2.name], but your efforts prove to be in vain as [npc2.she] grabs your [npc.hips] and pulls your [npc.ass] back into [npc2.her] groin.",
						"Trying to crawl away from [npc2.name] on all fours, you let out [npc.a_sob+] as you feel [npc2.herHim] grasp your [npc.hips], before pulling you back into position.",
						"Begging for [npc2.herHim] to leave you alone, you desperately try to crawl away from [npc2.name], [npc.sobbing] in distress as [npc2.she] takes hold of your [npc.hips] and pulls you back into [npc2.herHim].");
				
			} else if(Sex.getPosition()==SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc2.name] as you desperately try to struggle out of [npc2.her] grip, but your efforts prove to be in vain as [npc2.she] easily keeps you pinned up against the wall.",
						"Struggling against [npc2.name], you let out [npc.a_sob+] as you weakly try to wriggle out of [npc2.her] grasp, but [npc2.her] grip is too strong for you, and [npc2.she] easily keeps you pushed up against the wall.",
						"Begging for [npc2.herHim] to leave you alone, you desperately struggle against [npc2.name], [npc.sobbing] in distress as [npc2.she] pushes you up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc2.namePos] groin away from your [npc.face], but your efforts prove to be in vain as [npc2.she] grabs hold of your head and pulls you back into [npc2.her] crotch.",
						"Struggling against [npc2.name], you let out [npc.a_sob+] as you weakly try to pull your [npc.face] away from [npc2.her] groin, but [npc2.her] grasp on your head is too strong, and you're quickly forced back into position.",
						"Begging for [npc2.herHim] to leave you alone, you desperately struggle against [npc2.name], [npc.sobbing] in distress as [npc2.she] pulls your [npc.face] back into [npc2.her] groin.");
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Main.game.getPlayer())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				return UtilText.returnStringAtRandom(
						"You try to push [npc2.name] off of you as you desperately try to wriggle out from under [npc2.herHim], but your efforts prove to be in vain as [npc2.she] easily pins you to the floor.",
						"Struggling against [npc2.name], you let out [npc.a_sob+] as you weakly try to wriggle out from under [npc2.herHim], but [npc2.she] presses [npc2.her] body down onto yours, preventing you from getting away.",
						"Begging for [npc2.herHim] to leave you alone, you desperately struggle against [npc2.name], [npc.sobbing] in distress as [npc2.she] uses [npc2.her] body to pin you to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"You slap, hit, and kick [npc2.name] as you desperately try to struggle out of [npc2.her] grip, but your efforts prove to be in vain as [npc2.she] easily continues restraining you.",
						"Struggling against [npc2.name], you let out [npc.a_sob+] as you weakly try to wriggle out of [npc2.her] grasp.",
						"Begging for [npc2.herHim] to leave you alone, you desperately struggle against [npc2.name], [npc.sobbing] in distress as you realise that [npc2.her] grip is too strong.");
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.isPlayer()) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			}
		}
	};
	
	public static final SexAction DENIAL_FETISH_DENY = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.TWO_LOW,
			ArousalIncrease.NEGATIVE,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Deny";
		}

		@Override
		public String getActionDescription() {
			return "Force [npc2.name] to stay perfectly still, holding them in position until they've lost a good portion of their arousal.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "Taking control of the situation, you hold [npc2.name] quite still, only releasing [npc2.herHim] once [npc2.sheHas] lost a good portion of [npc2.her] arousal.";
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterPerformingAction())) {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_DENIAL_SELF);
			}
		}
	};
	
	public static final SexAction PLAYER_STOP_ALL_PENETRATIONS = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop penetrations";
		}

		@Override
		public String getActionDescription() {
			return "Stop all ongoing penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isAnyOngoingActionHappening()
					&& !Sex.isMasturbation()
					&& (Sex.isDom(Main.game.getPlayer())?true:Sex.isSubHasEqualControl())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			for(SexAreaOrifice ot : SexAreaOrifice.values()) {
				switch(ot) {
					case ANUS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.asshole+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.asshole+].");
						}
						break;
					case ASS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop using [npc2.her] [npc2.ass+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.ass+].");
						}
						break;
					case BREAST:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you stop playing with [npc2.her] [npc2.breasts+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.breasts+].");
						}
						break;
					case MOUTH:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] mouth.");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you pull out of your mouth.");
						}
						break;
					case NIPPLE:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.nipple+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.nipple+].");
						}
						break;
					case URETHRA_PENIS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.penisUrethra+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.penisUrethra+].");
						}
						break;
					case URETHRA_VAGINA:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.vaginaUrethra+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.vaginaUrethra+].");
						}
						break;
					case VAGINA:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out of [npc2.her] [npc2.pussy+].");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop stimulating your [npc.pussy+].");
						}
						break;
					case THIGHS:
						if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc2.Name] lets out [npc2.a_moan+] as you pull out from between [npc2.her] thighs.");
						}
						if (Sex.getCharacterContactingSexArea(Main.game.getPlayer(), ot).contains(Main.game.getPlayer())) {
							UtilText.nodeContentSB.append("<br/>[npc.A_moan+] drifts out from between your [npc.lips+] as you stop playing with your thighs.");
						}
						break;
				}
			}
			
			UtilText.nodeContentSB.replace(0, 5, "");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			for(GameCharacter character : Sex.getAllParticipants()) {
				Sex.stopAllOngoingActions(Main.game.getPlayer(), character);
			}
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid self actions";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& !Sex.isSubHasEqualControl())
					&& Sex.isPartnerAllowedToUseSelfActions()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(Sex.getActivePartner())) {
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc2.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] pouts at you as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop using [npc2.her] mouth.");
				}
			}
			
			if(UtilText.nodeContentSB.length()!=0)
				UtilText.nodeContentSB.append("<br/><br/>");
			UtilText.nodeContentSB.append("[npc.speech(I don't want to see you trying to get yourself off, understood?)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will no longer use any self-penetrative actions.</i>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
			
			Sex.setPartnerAllowedToUseSelfActions(false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit self actions";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to perform all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return (Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& !Sex.isSubHasEqualControl())
					&& !Sex.isPartnerAllowedToUseSelfActions()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(I want to see you trying to get yourself off!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to use any self-penetrative actions.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setPartnerAllowedToUseSelfActions(true);
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from managing any of your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& !Sex.isSubHasEqualControl()
					&& Sex.isCanRemoveOthersClothing(Sex.getActivePartner())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(Don't you <i>dare</i> try and touch any of my clothes!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will not attempt to remove or displace any of your clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveOthersClothing(Sex.getActivePartner(), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING_REMOVAL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to take off and displace your clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& !Sex.isSubHasEqualControl()
					&& !Sex.isCanRemoveOthersClothing(Sex.getActivePartner())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(How about you help me take off some of these clothes?)] you [npc.moan].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to manage your clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveOthersClothing(Sex.getActivePartner(), true);
		}
	};
	
	public static final SexAction PLAYER_FORBID_PARTNER_SELF_CLOTHING = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Forbid self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Forbid [npc2.name] from managing any of [npc2.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& !Sex.isSubHasEqualControl()
					&& Sex.isCanRemoveSelfClothing(Sex.getActivePartner())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(Don't you <i>dare</i> try and touch your clothes!)] you growl at [npc2.name].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] will not attempt to remove or displace any of [npc2.her] clothes.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveSelfClothing(Sex.getActivePartner(), false);
		}
	};
	
	public static final SexAction PLAYER_PERMIT_PARTNER_CLOTHING_SELF_REMOVAL = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Permit self clothing";
		}

		@Override
		public String getActionDescription() {
			return "Permit [npc2.name] to take off and displace [npc2.her] clothing.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Main.game.getPlayer())
					&& !Sex.isMasturbation()
					&& !Sex.isSubHasEqualControl()
					&& !Sex.isCanRemoveSelfClothing(Sex.getActivePartner())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "[npc.speech(How about you start taking off some of your clothes?)] you [npc.moan].<br/><br/>"
					+ "<i style='color:"+Colour.GENERIC_ARCANE.toWebHexString()+";'>[npc2.Name] is now able to manage [npc2.her] clothing.</i>";
		}

		@Override
		public void applyEffects() {
			Sex.setCanRemoveSelfClothing(Sex.getActivePartner(), true);
		}
	};
	
	
	public static final SexAction PLAYER_STOP_PARTNER_SELF = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop partner";
		}

		@Override
		public String getActionDescription() {
			return "Stop [npc2.name] from performing all self-penetrative actions.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.isMasturbation()
					&& Sex.isCharacterSelfOngoingActionHappening(Sex.getActivePartner())
					&& (Sex.isDom(Main.game.getPlayer())?true:Sex.isSubHasEqualControl())
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.VAGINA).contains(Sex.getActivePartner())) {
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.ANUS).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As you put an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc2.she] lets out a pathetic whine.");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.NIPPLE).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] pouts at you as you force [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getActivePartner(), SexAreaOrifice.MOUTH).contains(Sex.getActivePartner())) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc2.Name] lets out a disappointed [npc.moan] as you force [npc2.herHim] to stop using [npc2.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	// Partner:
	
	public static final SexAction PARTNER_HYPNOTIC_SUGGESTION_LUST_DECREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Calming Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] doesn't like having sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					&& Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_DOM)
					&& Sex.getCharacterTargetedForSexAction(this).getLust()>LustLevel.ONE_HORNY.getMaximumValue()
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return "<p>"
					+ "Wanting to take advantage of the fact that [npc2.nameIs] under the strong effect of a psychoactive substance, [npc.name] leans towards [npc2.herHim] and [npc.moansVerb],"
						+ " [npc.speech(You aren't really interested in having sex with me, are you?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc2.Name] can't help but agree with what [npc.sheIs] saying, and [npc2.name] haltingly [npc2.verb(answer)],"
						+ " [npc2.speech(Yes... I... I don't know why I'm having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further, and driven on by [npc.her] fetish for having non-consensual sex, [npc.name] continues,"
						+ " [npc.speech(You'd rather I wasn't fucking you right now, isn't that right?)]"
					+ "</p>"
					+ "<p>"
						+ "As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] [npc2.verb(start)] to feel a lot more distressed, and [npc2.verb(cry)] out,"
							+ " [npc2.speech(Wait, w-why is this happening?! Please, stop it! Get away from me!)]"
					+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterTargetedForSexAction(this).incrementLust(-50);
		}
	};
	
	public static final SexAction PARTNER_HYPNOTIC_SUGGESTION_LUST_INCREASE = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.TWO_LOW,
			CorruptionLevel.THREE_DIRTY,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "[style.colourPsychoactive(Lustful Suggestion)]";
		}

		@Override
		public String getActionDescription() {
			return "[npc2.Name] is under the effect of a psychoactive substance. Use this to your advantage and hypnotically suggest that [npc2.she] loves to have sex with you.";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isDom(Sex.getActivePartner())
					&& !Sex.getCharacterTargetedForSexAction(this).getPsychoactiveFluidsIngested().isEmpty()
					&& !Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_DOM)
					&& Sex.getCharacterTargetedForSexAction(this).getLust()<LustLevel.FOUR_IMPASSIONED.getMinimumValue()
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
				return "Wanting to take advantage of the fact that [npc2.nameIs] under the strong effect of a psychoactive substance, [npc.name] leans towards [npc2.herHim] and [npc.moansVerb],"
						+ " [npc.speech(You love having sex with me, don't you?)]"
					+ "</p>"
					+ "<p>"
						+ "[npc2.Name] can't help but agree with what [npc.sheIs] saying, and haltingly [npc2.verb(answer)],"
						+ " [npc2.speech(Yes... I... I love having sex with you...)]"
					+ "</p>"
					+ "<p>"
						+ "Pushing a little further, [npc.name] continues,"
						+ " [npc.speech(You love begging for me to fuck you, isn't that right?)]"
					+ "</p>"
					+ "<p>"
						+ "As the hypnotic suggestion sinks into [npc2.namePos] head, [npc2.she] can't help but feel a lot more eager, and [npc2.moanVerb],"
						+ " [npc2.speech(Yes! I love it! Please, fuck me! I <i>need</i> you to fuck me!)]"
					+ "</p>";
		}

		@Override
		public void applyEffects() {
			Sex.getCharacterTargetedForSexAction(this).incrementLust(50);
		}
	};
	
	
	public static final SexAction PARTNER_RESIST = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ZERO_NONE,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL,
			SexPace.SUB_RESISTING) {
		@Override
		public ArousalIncrease getArousalGainSelf() {
			if(Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
				return ArousalIncrease.THREE_NORMAL;
			}
			return ArousalIncrease.ZERO_NONE;
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public String getActionTitle() {
			return "Resist";
		}

		@Override
		public String getActionDescription() {
			return "Resist having sex with [npc2.name].";
		}

		@Override
		public String getDescription() {
			
			if(Sex.getPosition()==SexPositionType.BACK_TO_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.BACK_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks [npc2.name] as [npc.she] desperately tries to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] easily [npc2.verb(keep)] [npc.herHim] pinned back against the wall.",
						
						"Struggling against [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of [npc2.her] grasp,"
								+ " but [npc2.her] grip is too strong for [npc.herHim], and [npc2.name] easily [npc2.verb(keep)] [npc.herHim] pushed back against the wall.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately struggles against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(push)] [npc.herHim] back against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.DOGGY_STYLE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.DOGGY_ON_ALL_FOURS) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] lets out [npc.a_sob+] as [npc.she] tries to crawl away from [npc2.name],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] [npc.her] [npc.hips] and pull [npc.her] [npc.ass] back into [npc2.her] groin.",
						
						"Trying to crawl away from [npc2.name] on all fours, [npc.name] lets out [npc.a_sob+] as [npc2.she] [npc2.verb(grasp)] [npc.her] [npc.hips], before pulling [npc.herHim] back into position.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately tries to crawl away from [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(take)] hold of [npc.her] [npc.hips] and pull [npc.herHim] back into [npc2.herHim].");
				
			} else if(Sex.getPosition()==SexPositionType.FACING_WALL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.FACE_TO_WALL_AGAINST_WALL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks [npc2.name] as [npc.she] desperately tries to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] easily [npc2.verb(keep)] [npc.herHim] pinned up against the wall.",
						
						"Struggling against [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of [npc2.her] grasp,"
								+ " but [npc2.her] grip is too strong for [npc.herHim], and [npc2.she] easily [npc2.verb(keep)] [npc.herHim] pushed up against the wall.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately struggles against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(push)] [npc.herHim] up against the wall.");
				
			} else if(Sex.getPosition()==SexPositionType.KNEELING_ORAL && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.KNEELING_PERFORMING_ORAL) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push [npc2.namePos] groin away from [npc.her] [npc.face],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] [npc2.verb(grab)] hold of [npc.her] head and [npc2.verb(pull)] [npc.herHim] back into [npc2.her] crotch.",
						
						"Struggling against [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to pull [npc.her] [npc.face] away from [npc2.her] groin,"
								+ " but [npc2.namePos] grasp on [npc.her] head is too strong, and [npc2.she] quickly [npc2.verb(force)] [npc.herHim] back into position.",
								
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately struggles against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(pull)] [npc.her] [npc.face] back into [npc2.her] groin.");
				
			} else if(Sex.getPosition()==SexPositionType.SIXTY_NINE && Sex.getSexPositionSlot(Sex.getActivePartner())==SexPositionSlot.SIXTY_NINE_BOTTOM) {
				return UtilText.returnStringAtRandom(
						"[npc.Name] tries to push [npc2.name] off of [npc.herHim] as [npc.she] desperately tries to wriggle out from under [npc2.herHim],"
								+ " but [npc.her] efforts prove to be in vain as [npc2.name] easily [npc2.verb(pin)] [npc.herHim] to the floor.",
						
						"Struggling against [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out from under [npc2.herHim],"
								+ " but [npc2.name] [npc2.verb(press)] [npc2.her] body down onto [npc.hers], preventing [npc.herHim] from getting away.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately struggles against [npc2.herHim],"
								+ " [npc.sobbing] in distress as [npc2.she] [npc2.verb(use)] [npc2.her] body to pin [npc.herHim] to the floor.");
				
			} else {
				return UtilText.returnStringAtRandom(
						"[npc.Name] slaps, hits, and kicks [npc2.name] as [npc.she] desperately tries to struggle out of [npc2.her] grip,"
								+ " but [npc.her] efforts prove to be in vain as [npc2.she] easily [npc2.verb(continue)] restraining [npc.herHim].",
								
						"Struggling against [npc2.name], [npc.name] lets out [npc.a_sob+] as [npc.she] weakly tries to wriggle out of [npc2.her] grasp.",
						
						"Begging for [npc2.name] to leave [npc.herHim] alone, [npc.name] desperately struggles against [npc2.herHim], [npc.sobbing] in distress as [npc2.she] easily [npc2.verb(hold)] [npc.herHim] in place.");
			}
		}
		
		@Override
		public List<Fetish> getFetishes(GameCharacter character) {
			if(character.equals(Sex.getCharacterTargetedForSexAction(this))) {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM);
			} else {
				return Util.newArrayListOfValues(Fetish.FETISH_NON_CON_SUB);
			}
		}
	};
	
	public static final SexAction PARTNER_STOP_PLAYER_SELF = new SexAction(
			SexActionType.ONGOING,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "Stop player";
		}

		@Override
		public String getActionDescription() {
			return "";
		}
		
		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.isCharacterSelfOngoingActionHappening(Sex.getCharacterTargetedForSexAction(this))
					&& !Sex.isAnyNonSelfOngoingActionHappening()
					&& (Sex.isDom(Sex.getCharacterPerformingAction()) || Sex.isSubHasEqualControl())
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			UtilText.nodeContentSB.setLength(0);
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.VAGINA).contains(Sex.getCharacterTargetedForSexAction(this))) {
					UtilText.nodeContentSB.append("[npc.Name] lets out an angry growl as [npc.she] forces [npc2.name] to stop stimulating [npc2.her] [npc2.pussy+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.ANUS).contains(Sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("As [npc.name] puts an end to [npc2.namePos] self-stimulation of [npc2.her] [npc2.asshole], [npc.she] growls menacingly at [npc2.herHim].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.NIPPLE).contains(Sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] frowns at [npc2.name] as [npc.she] forces [npc2.herHim] to stop stimulating [npc2.her] [npc2.nipples+].");
				}
			}
			
			if (Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH)!=null) {
				if(Sex.getCharacterContactingSexArea(Sex.getCharacterTargetedForSexAction(this), SexAreaOrifice.MOUTH).contains(Sex.getCharacterTargetedForSexAction(this))) {
					if(UtilText.nodeContentSB.length()!=0)
						UtilText.nodeContentSB.append("<br/>");
					UtilText.nodeContentSB.append("[npc.Name] makes a disapproving clicking noise with [npc.her] [npc.tongue] as [npc.she] forces [npc2.name] to stop using [npc2.her] mouth.");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public void applyEffects() {
			Sex.stopAllOngoingActions(Sex.getCharacterTargetedForSexAction(this), Sex.getCharacterTargetedForSexAction(this));
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX_NOT_HAVING_FUN = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.NORMAL) {
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			if(Sex.getCharacterPerformingAction().isPlayer()) {
				return false;
			}
			
			if(!Sex.isDom(Sex.getActivePartner())) {
				return Sex.getSexPace(Sex.getActivePartner()) == SexPace.SUB_RESISTING
						&& !Sex.getActivePartner().hasFetish(Fetish.FETISH_NON_CON_SUB)
						&& Sex.isSubHasEqualControl();
			} else {
				return false;// doms will never end it
			}
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With an annoyed sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches,"
					+ " [npc.speechNoEffects(Eugh... I'm not really feeling this right now, ok?)]";
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PARTNER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {
		
		@Override
		public String getActionTitle() {
			return "";
		}

		@Override
		public String getActionDescription() {
			return "";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isPartnerWantingToStopSex()
					&& !Sex.getCharacterPerformingAction().isPlayer();
		}
		
		@Override
		public SexActionPriority getPriority() {
			return SexActionPriority.HIGH;
		}
		
		@Override
		public String getDescription() {
			return "With a satisfied sigh, [npc.name] disentangles [npc.herself] from [npc2.namePos] clutches, before stating that [npc.sheHas] had enough for now.";
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
	
	public static final SexAction PLAYER_STOP_SEX = new SexAction(
			SexActionType.SPECIAL,
			ArousalIncrease.ONE_MINIMUM,
			ArousalIncrease.ONE_MINIMUM,
			CorruptionLevel.ZERO_PURE,
			null,
			SexParticipantType.SELF) {

		@Override
		public String getActionTitle() {
			return Sex.isMasturbation()
					?"Stop masturbating"
					:"Stop sex";
		}

		@Override
		public String getActionDescription() {
			return Sex.isMasturbation()
					?"Put an end to your masturbation session."
					:"Stop having sex with [npc2.name].";
		}

		@Override
		public boolean isBaseRequirementsMet() {
			return Sex.getSexManager().isPlayerAbleToStopSex()
					&& Sex.getCharacterPerformingAction().isPlayer();
		}

		@Override
		public String getDescription() {
			return Sex.isMasturbation()
					?"Deciding that you've had enough, you put an end to your masturbation session."
					:"Deciding that you've had enough, you step back from [npc2.name].";
		}
		
		@Override
		public SexParticipantType getParticipantType() {
			return Sex.isMasturbation()?SexParticipantType.SELF:SexParticipantType.NORMAL;
		}
		
		@Override
		public boolean endsSex() {
			return true;
		}
	};
}
