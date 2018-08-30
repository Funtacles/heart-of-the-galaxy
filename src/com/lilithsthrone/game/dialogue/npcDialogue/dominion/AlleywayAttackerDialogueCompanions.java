package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;
import java.util.List;

import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.AffectionLevel;

import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.2.10
 * @version 0.2.10
 * @author Innoxia
 */
public class AlleywayAttackerDialogueCompanions {
	
	private static boolean isCanal() {
		PlaceType pt = getMugger().getLocationPlace().getPlaceType();
		return (pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
				|| pt == PlaceType.DOMINION_CANAL
				|| pt == PlaceType.DOMINION_CANAL_END);
	}
	
	private static List<GameCharacter> getCompanions() {
		return Main.game.getPlayer().getCompanions();
	}
	
	private static GameCharacter getMainCompanion() {
		return getCompanions().get(0);
	}
	
	private static NPC getMugger() {
		return Main.game.getActiveNPC();
	}
	
	private static boolean isWantsToFight() {
		return getMugger().getAffection(Main.game.getPlayer())<AffectionLevel.POSITIVE_ONE_FRIENDLY.getMinimumValue();
	}
	
	private static boolean isAffectionHighEnoughToInviteHome() {
		return getMugger().getAffection(Main.game.getPlayer())>=AffectionLevel.POSITIVE_THREE_CARING.getMinimumValue();
	}
	
	private static List<GameCharacter> getAllCharacters() {
		List<GameCharacter> allCharacters = new ArrayList<>();
		allCharacters.add(getMugger());
		allCharacters.addAll(Main.game.getPlayer().getCompanions());
		return allCharacters;
	}
	
	private static void applyPregnancyReactions() {
		if(getMugger().isVisiblyPregnant()){
			getMugger().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
		}
		if(Main.game.getPlayer().isVisiblyPregnant()) {
			Main.game.getPlayer().setCharacterReactedToPregnancy(getMugger(), true);
		}
		if(getMainCompanion().isVisiblyPregnant()) {
			getMainCompanion().setCharacterReactedToPregnancy(getMugger(), true);
		}
	}
	
	public static final DialogueNodeOld ALLEY_ATTACK = new DialogueNodeOld("Assaulted!", "A figure jumps out from the shadows!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(getMugger().getLastTimeEncountered() != -1) {
				if(isWantsToFight()) {
					if(isCanal()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_CANAL_REPEAT_INTRO", getAllCharacters()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT_INTRO", getAllCharacters()));
					}
					
					if(getMugger().isVisiblyPregnant()
							|| (Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(getMugger().getId()))
							|| (getMainCompanion().isVisiblyPregnant() && getMainCompanion().isCharacterPossiblyFather(getMugger().getId()))) {
						if(getMugger().isVisiblyPregnant()) {
							if(!getMugger().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT_PREGNANCY_REVEAL", getAllCharacters()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT_STILL_PREGNANT", getAllCharacters()));
							}
						}
						
						if((Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(getMugger().getId()))
								|| (getMainCompanion().isVisiblyPregnant() && getMainCompanion().isCharacterPossiblyFather(getMugger().getId()))) {
							// Pregnant encounters:
							if(!Main.game.getPlayer().isCharacterReactedToPregnancy(getMugger())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT_PLAYER_PREGNANCY", getAllCharacters()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT_CONTINUED_PLAYER_PREGNANCY", getAllCharacters()));
							}
						}
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT", getAllCharacters()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_REPEAT_END", getAllCharacters()));
					
				} else {
					if(isCanal()) {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_CANAL_INTRO", getMugger()));
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_INTRO", getMugger()));
					}
					
					if(getMugger().isVisiblyPregnant()
						|| (Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(getMugger().getId()))){
						
						if(getMugger().isVisiblyPregnant()) {
							if(!getMugger().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_PREGNANCY_REVEAL", getMugger()));
							
							} else {
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_STILL_PREGNANT", getMugger()));
							}
						}
						
						if(Main.game.getPlayer().isVisiblyPregnant() && Main.game.getPlayer().isCharacterPossiblyFather(getMugger().getId())) {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_PLAYER_PREGNANCY", getMugger()));
						
						} else {
							UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_CONTINUED_PLAYER_PREGNANCY", getMugger()));
						}
						
						
					} else {
						UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL", getMugger()));
					}
					
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_END", getMugger()));
				}
				
				
			} else {
				if(isCanal()) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_CANAL_INTRO", getAllCharacters()));
					
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_INTRO", getAllCharacters()));
				}

				UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK", getAllCharacters()));
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(isWantsToFight()) {
				if (index == 1) {
					return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", getMugger()) {
						@Override
						public void effects() {
							applyPregnancyReactions();
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getPlayer().incrementMoney(-250);
								UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PAY_OFF", getAllCharacters()));
							}
						};
					}
					
				} else if (index == 3) {
					if(getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null,
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(getMainCompanion()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_SOLO_WITH_COMPANION", getMugger(), getMainCompanion())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
					}
					
				} else if (index == 4) {
					GameCharacter companion = getMainCompanion();
	
					if(!getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with you, so wouldn't want to have a threesome..."),
								null);
						
					} else if(!getMugger().isAttractedTo(companion)) {
						return new Response(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name], so wouldn't want to have a threesome..."),
								null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
								UtilText.parse(getMugger(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
								true, true,
								new SMDoggy(
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_BEHIND)),
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
												new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
								null,
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_WITH_COMPANION", getMugger(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
					}
					
				} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();
	
					if(getMugger().isAttractedTo(companion)) {
						return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_OFFER_COMPANION", getMugger(), companion)) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
									Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
								}
							}
						};
						
					} else {
						return new Response(UtilText.parse(companion, "Offer [npc.name]"),
								UtilText.parse(getMugger(), companion, "You can tell that [npc.name] isn't at all interested in having sex with [npc2.name]..."),
								null);
					}
					
				} else {
					return null;
				}
			
			} else {
				if (index == 1) {
					return new Response("Talk", "Talk to [npc.name] for a while in order to get to know [npc.herHim] a little better.", ALLEY_PEACEFUL_TALK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), 5));
							
							if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
							}
						}
					};
					
				} else if (index == 2) {
					if(Main.game.getPlayer().getMoney()<250) {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"You don't have enough money to offer [npc.name] any.", null);
					} else {
						return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")",
								"Offer [npc.name] some money to help [npc.herHim] buy food and clothing.", ALLEY_PEACEFUL_OFFER_MONEY) {
							@Override
							public void effects() {
								applyPregnancyReactions();
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementMoney(-250));
								Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), 10));

								if(isAffectionHighEnoughToInviteHome() && !Main.game.getPlayer().hasQuest(QuestLine.SIDE_ACCOMMODATION)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_ACCOMMODATION));
								}
							}
						};
					}
					
				} else if (index == 3) {
					if(getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (dom)", "Take the dominant role and have sex with [npc.name] .",
								Util.newArrayListOfValues(Fetish.FETISH_DOMINANT), null,
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX_PEACEFUL,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_SEX_AS_DOM", getMugger())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (dom)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} else if (index == 4) {
					if(getMugger().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Sex (sub)", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null,
								null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX_PEACEFUL,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_PEACEFUL_SEX_AS_SUB", getMugger())) {
							@Override
							public void effects() {
								applyPregnancyReactions();
							}
						};
						
					} else {
						return new Response("Sex (sub)", "You can tell that [npc.name] isn't interested in having sex with you...", null);
					}
					
				} else if (index==10) {
					return new Response("Attack", "Betray [npc.namePos] trust and attack [npc.herHim]!", ALLEY_PEACEFUL_ATTACK) {
						@Override
						public void effects() {
							applyPregnancyReactions();
							Main.game.getTextEndStringBuilder().append(getMugger().incrementAffection(Main.game.getPlayer(), -50));
							getMugger().addFlag(NPCFlagValue.genericNPCBetrayedByPlayer);
						}
						@Override
						public boolean isCombatHighlight() {
							return true;
						}
					};
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you're in a rush to be somewhere else, before continuing on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_TALK = new DialogueNodeOld("Talk", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_TALK", getMugger()));
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getMugger()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME", getMugger()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go and meet [npc.her] friend.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_OFFER_MONEY = new DialogueNodeOld("Offer money", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_OFFER_MONEY", getMugger()));
			
			if(isAffectionHighEnoughToInviteHome()) {
				if(Main.game.getPlayer().isQuestCompleted(QuestLine.SIDE_ACCOMMODATION)) {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME_REQUIRES_LILAYA_PERMISSION", getMugger()));
				} else {
					UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_CAN_INVITE_HOME", getMugger()));
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] go and buy food.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME = new DialogueNodeOld("New Room", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_OFFER_ROOM_BACK_HOME", getMugger());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Let [npc.name] get settled in.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEY_PEACEFUL_ATTACK = new DialogueNodeOld("Attack", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_PEACEFUL_ATTACK", getMugger());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Start fighting [npc.name]!", getMugger());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_PEACEFUL = new DialogueNodeOld("Continue", "Step away from [npc.name] and prepare to continue on your way.", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(getMugger())>0) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_SEX_PEACEFUL", getMugger());
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_SEX_PEACEFUL_NO_ORGASM", getMugger());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STORM_ATTACK = new DialogueNodeOld("Attacked!", "A figure jumps out of a nearby doorway!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}

		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "STORM_ATTACK", getAllCharacters());
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Defend yourself against the unwanted advances of [npc.name]!", getMugger());
				
			} else if (index == 2) {
				return new Response("Offer money",
						"Due to the ongoing arcane storm, [npc.name] isn't interested in your money, and only wants to have sex! You'll have to either fight [npc.herHim] or give [npc.herHim] what [npc.she] wants!",
						null);
				
			} else if (index == 3) {
				return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
						Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null,
						null, null, null,
						true, true,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
						Util.newArrayListOfValues(getMainCompanion()),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_SOLO_WITH_COMPANION", getMugger(), getMainCompanion()));
					
			} else if (index == 4) {
				GameCharacter companion = getMainCompanion();

				return new ResponseSex(UtilText.parse(companion, "Offer threesome"),
						UtilText.parse(getMugger(), companion, "Offer [npc.name] the opportunity to have sex with both you and [npc2.name] in order to avoid a violent confrontation."),
						true, true,
						new SMDoggy(
								Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_BEHIND)),
								Util.newHashMapOfValues(
										new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
										new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
						null,
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_OFFER_BODY_WITH_COMPANION", getMugger(), companion));
				
				
			} else if (index == 5 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
				GameCharacter companion = getMainCompanion();

				return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
						UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.namePos] body in order to avoid a violent confrontation."),
						true, false,
						new SMStanding(
								Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
								Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
						Util.newArrayListOfValues(Main.game.getPlayer()),
						AFTER_SEX_DEFEAT,
						UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "ALLEY_ATTACK_OFFER_COMPANION", getMugger(), companion)) {
					@Override
					public void effects() {
						if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
							Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
						}
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_ATTRACTION", getAllCharacters());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_NO_ATTRACTION", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new Response("Continue", "Decide against having sex with [npc.name] and continue on your way...", Main.game.getDefaultDialogueNoEncounter());
					
				} else if (index == 2) {
					return new ResponseSex("Sex",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_SEX", getAllCharacters()));
					
				} else if (index == 3) {
					return new ResponseSex("Gentle sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_SEX_GENTLE", getAllCharacters()));
					
				} else if (index == 4) {
					return new ResponseSex("Rough sex",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_SEX_ROUGH", getAllCharacters()));
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now... Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_SEX_SUBMIT", getAllCharacters()));
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 7) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Threesome"),
								UtilText.parse(getMugger(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND),
												new Value<>(companion, SexPositionSlot.DOGGY_INFRONT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_THREESOME", getMugger(), companion));
					}
					
				} else if (index == 8) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, getMugger(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger(), companion));
					}
					
				} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();

					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
							Main.game.banishNPC(getMugger());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_RAPE", getAllCharacters()));
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_RAPE_GENTLE", getAllCharacters()));
					
				} else if (index == 4) {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_VICTORY, UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_RAPE_ROUGH", getAllCharacters()));
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				}else if (index == 7) {
					GameCharacter companion = getMainCompanion();

					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Threesome"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Threesome"),
								UtilText.parse(getMugger(), companion, "Have dominant sex with [npc.name], and get [npc2.name] to join in with the fun."),
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(
												new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND),
												new Value<>(companion, SexPositionSlot.DOGGY_INFRONT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
								null,
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_THREESOME", getMugger(), companion));
					}
					
				} else if (index == 8) {
					GameCharacter companion = getMainCompanion();
					
					if(!companion.isAttractedTo(getMugger())) {
						return new Response(UtilText.parse(companion, "Give to [npc.name]"), UtilText.parse(companion, getMugger(), "[npc.Name] isn't attracted to [npc2.name], so wouldn't be willing to have sex with [npc2.herHim]!"), null);
						
					} else {
						return new ResponseSex(UtilText.parse(companion, "Give to [npc.name]"),
								UtilText.parse(companion, getMugger(), "Tell [npc.name] that [npc.she] can have some fun with [npc2.name] while you watch."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_VICTORY,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_GIVE_TO_COMPANION", getMugger(), companion));
					}
					
				} else if (index == 9 && Main.getProperties().hasValue(PropertyValue.voluntaryNTR)) {
					GameCharacter companion = getMainCompanion();

					return new ResponseSex(UtilText.parse(companion, "Offer [npc.name]"),
							UtilText.parse(getMugger(), companion, "Tell [npc.name] that [npc.she] can use [npc2.name]."),
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(Main.game.getPlayer()),
							AFTER_SEX_VICTORY,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_OFFER_COMPANION", getMugger(), companion)) {
						@Override
						public void effects() {
							if(!companion.isAttractedTo(getMugger()) && Main.game.isNonConEnabled()) {
								Main.game.getTextEndStringBuilder().append(companion.incrementAffection(Main.game.getPlayer(), -50));
							}
						}
					};
					
				}  else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							Main.game.getDefaultDialogueNoEncounter()){
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_VICTORY_BANISH_NPC", getAllCharacters()));
							Main.game.banishNPC(getMugger());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {

			if(getMugger().isWillingToRape(Main.game.getPlayer())
					&& ((getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(getMainCompanion()))
							|| (getMugger().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {

				GameCharacter companion = getMainCompanion();
				
				if (getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(companion)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getAllCharacters());
				
				} else if (getMugger().isAttractedTo(companion) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_WANTS_COMPANION", getAllCharacters());
					
				}
			}
			
			if(getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isWillingToRape(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_NO_TF_ATTRACTED", getAllCharacters());
				
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_NO_TF_NOT_ATTRACTED", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isWillingToRape(Main.game.getPlayer())
					&& ((getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(getMainCompanion()))
							|| (getMugger().isAttractedTo(getMainCompanion()) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)))) {
				
				GameCharacter companion = getMainCompanion();

				if (index == 1) {
					if (getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isAttractedTo(companion)) {
							return new ResponseSex(UtilText.parse(companion, "Threesome"),
									UtilText.parse(getMugger(), companion, "[npc.Name] uses this opportunity to have sex with both you and [npc2.name]..."),
									false, false,
									new SMDoggy(
											Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(
													new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS),
													new Value<>(companion, SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
									null,
									AFTER_SEX_DEFEAT,
									UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_THREESOME", getMugger(), companion));
						
					} else if (getMugger().isAttractedTo(companion) && Main.getProperties().hasValue(PropertyValue.involuntaryNTR)) {

						return new ResponseSex(UtilText.parse(companion, "[npc.Name] used"),
								UtilText.parse(getMugger(), companion, "[npc.Name] uses [npc2.namePos] body in order to get some sexual relief..."),
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(companion, SexPositionSlot.STANDING_SUBMISSIVE))),
								Util.newArrayListOfValues(Main.game.getPlayer()),
								AFTER_SEX_DEFEAT,
								UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_USES_COMPANION", getMugger(), companion));
						
					}
					
				} else {
					return null;
				}

			} else if(getMugger().isAttractedTo(Main.game.getPlayer()) && getMugger().isWillingToRape(Main.game.getPlayer())) {
				
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							Util.newArrayListOfValues(getMainCompanion()),
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
					
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				}
			}
			
			return null;
			
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_TRANSFORMATION_REFUSED_ATTRACTED", getAllCharacters());
			
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_TRANSFORMATION_REFUSED_NOT_ATTRACTED", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_TRANSFORMATION_ATTRACTED", getAllCharacters());
			
			} else {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_TRANSFORMATION_NOT_ATTRACTED", getAllCharacters());
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(getMugger().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX", getAllCharacters()));
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_EAGER", getAllCharacters()));
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(getMugger(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							null,
							AFTER_SEX_DEFEAT,
							UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_COMBAT_DEFEAT_SEX_RESIST", getAllCharacters()));
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", Main.game.getDefaultDialogueNoEncounter());
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!getMugger().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_SEX_VICTORY_RAPE", getAllCharacters());
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_SEX_VICTORY", getAllCharacters());
				} else {
					return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_SEX_VICTORY_NO_ORGASM", getAllCharacters());
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.namePos] clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(getMugger(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(getMugger());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.namePos] dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("encounters/dominion/alleywayAttackCompanions", "AFTER_SEX_DEFEAT", getAllCharacters());
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
