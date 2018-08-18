package com.lilithsthrone.game.dialogue.npcDialogue;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.npc.NPCFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMDoggy;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

/**
 * @since 0.1.85
 * @version 0.2.10
 * @author Innoxia
 */
public class OccupantDialogue {
	
	private static NPC slave() {
		return Main.game.getActiveNPC();
	}
	
	public static final DialogueNodeOld SLAVE_START = new DialogueNodeOld("", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel() {
			return Main.game.getPlayer().getLocationPlace().getName();
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getActiveNPC().isVisiblyPregnant()){
				// Pregnant encounters:
				if(!Main.game.getActiveNPC().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "As you approach [npc.name], it's impossible not to notice the fact that [npc.sheIs] sporting a round belly."
								+ " [npc.She] absent-mindedly strokes [npc.her] swollen bump as [npc.she] looks up at you,");
					
					GameCharacter father = Main.game.getActiveNPC().getPregnantLitter().getFather();
					
					if(father!=null && father.isPlayer()) {
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "You walk over to your slave, and, running your [pc.hands] over [npc.her] pregnant belly, you smile reassuringly at the mother of your children."
									+ " [pc.speech(When the time's right, Lilaya will be able to help you give birth, ok?)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name] responds in the affirmative, and after stroking and caressing [npc.her] belly for a little while, you wonder what to do next..."
								+ "</p>");
						
					} else {
						UtilText.nodeContentSB.append("</p>"
								+ "<p>"
									+ "You walk over to your slave, and, running your [pc.hands] over [npc.her] pregnant belly, you smile reassuringly at [npc.herHim]."
									+ " [pc.speech(When the time's right, Lilaya will be able to help you give birth, ok?)]"
								+ "</p>"
								+ "<p>"
									+ "[npc.Name] responds in the affirmative, and after stroking and caressing [npc.her] belly for a little while, you wonder what to do next..."
								+ "</p>");
					}
				
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "As you approach [npc.name], you see that [npc.sheIs] still sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] pregnant bump as [npc.she] looks up at you,");
					UtilText.nodeContentSB.append("</p>"
							+ "<p>"
								+ "You walk over to your slave, and, running your [pc.hands] over [npc.her] pregnant belly, you smile reassuringly at [npc.herHim]."
								+ " [pc.speech(Remember that Lilaya will be able to help you to give birth. Make sure you visit her when the time's right, ok?)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] responds in the affirmative, and after stroking and caressing [npc.her] belly for a little while, you wonder what to do next..."
							+ "</p>");
				}
				
			} else {
				// Standard repeat encounter:
				UtilText.nodeContentSB.append(
						"<p>"
							+ "As you approach [npc.name], [npc.she] looks up at you,");
				UtilText.nodeContentSB.append("</p>"
						+ "<p>"
							+ "You walk over to your slave, wondering what to do next..."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(getFooterText());

			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index == 0) {
				return "Dialogue";
			} else if(index == 1) {
				return "Sex";
			} else if(index == 2) {
				return "Management";
			}
			
			return null;
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab == 0) {
				if (index == 1) {
					if(!slave().NPCFlagValues.contains(NPCFlagValue.flagSlaveBackground)) {
						return new Response("Background", "Ask [npc.name] about [npc.her] past life.", SLAVE_PROGRESSION) {
							@Override
							public void effects() {
								if(slave().isVisiblyPregnant()){
									slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								slave().NPCFlagValues.add(NPCFlagValue.flagSlaveBackground);
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 3));
							}
						};
					} else {
						return new Response("Background", "You've already talked with [npc.name] about [npc.her] past life today.", null);
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", SLAVE_START) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							if(slave().isVisiblyPregnant()){
								slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
					};
					
				} else {
					return null;
				}
			
			} else if(responseTab == 1) {
				List<NPC> charactersPresent = Main.game.getCharactersPresent();
				
				if (index == 1) { 
					if(Main.game.isNonConEnabled() && !Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Rape", "[npc.Name] is definitely not interested in having sex with you, but it's not like [npc.she] has a choice in the matter...", 
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX, "<p>"
									+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
									+ " [npc.She] desperately tries to push you away, [npc.moaning],"
									+ " [npc.speech(No! Stop!)]"
								+ "</p>") {
							@Override
							public void effects() {
								if(slave().isVisiblyPregnant()){
									slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								if(Main.game.getActiveNPC().hasFetish(Fetish.FETISH_NON_CON_SUB)) {
									Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
								} else {
									Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), -25));
								}
							}
						};
						
					} else {
						return new ResponseSex("Sex", "Have sex with [npc.name].", 
								true, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX, "<p>"
									+ "Grinning, you step forwards and pull [npc.name] into a passionate kiss."
									+ " [npc.She] desperately leans into you, [npc.moaning],"
									+ " [npc.speech(~Mmm!~ Yes!)]"
								+ "</p>") {
							@Override
							public void effects() {
								if(slave().isVisiblyPregnant()){
									slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
					}
					
				} else if (index == 2) {
					if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
						return new ResponseSex("Submissive sex", "Have submissive sex with [npc.name].", 
								Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, null, null, null,
								true, true,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								null,
								AFTER_SEX, "<p>"
									+ "Taking hold of [npc.namePos] [npc.arms], you take a step forwards, guiding [npc.her] [npc.hands] around your body as you press forwards into a passionate kiss."
									+ " [npc.She] eagerly pulls you into [npc.herHim], [npc.moaning],"
									+ " [npc.speech(Looking for some fun, hmm?)]"
								+ "</p>") {
							@Override
							public void effects() {
								if(slave().isVisiblyPregnant()){
									slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								Main.game.getTextEndStringBuilder().append(Main.game.getActiveNPC().incrementAffection(Main.game.getPlayer(), 5));
							}
						};
						
					} else {
						return new Response("Submissive sex", "[npc.Name] is not too keen on having sex with you, so you'd need to be the dom...", null);
					}
					
				} else if (index == 3) {
					if(charactersPresent.size()>=2) {
						if(!charactersPresent.get(0).isAttractedTo(Main.game.getPlayer()) || !charactersPresent.get(1).isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast", UtilText.parse(charactersPresent.get(0), charactersPresent.get(1), "Neither [npc1.name] nor [npc2.name] are attracted to you..."), null);
							
						} else if(!charactersPresent.get(0).isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast", UtilText.parse(charactersPresent.get(0), "[npc.Name] is not attracted to you..."), null);
							
						} else if(!charactersPresent.get(1).isAttractedTo(Main.game.getPlayer())) {
							return new Response("Spitroast", UtilText.parse(charactersPresent.get(1), "[npc.Name] is not attracted to you..."), null);
							
						} else {
							return new ResponseSex("Get Spitroasted",
									UtilText.parse(charactersPresent.get(0), charactersPresent.get(1), "Let [npc1.name] and [npc2.name] spitroast you."),
									null, null, null, null, null,
									true, true,
									new SMDoggy(
											Util.newHashMapOfValues(
													new Value<>(charactersPresent.get(1), SexPositionSlot.DOGGY_INFRONT),
													new Value<>(charactersPresent.get(0), SexPositionSlot.DOGGY_BEHIND)),
											Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_ON_ALL_FOURS))),
									null,
									AFTER_SEX, "<p>"
										+ ""//TODO
									+ "</p>") {
								@Override
								public void effects() {
									if(slave().isVisiblyPregnant()){
										slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
									}
								}
							};
						}
					} else {
						return new Response("Spitroast", "Another slave needs to be present for this...",null);
					}
				
				} else if (index == 4) {
					if(charactersPresent.size()>=2) {
						return new ResponseSex("Side-by-side",
								UtilText.parse(charactersPresent.get(0), charactersPresent.get(1), "Push [npc1.name] and [npc2.name] down onto all fours, side-by-side, and get ready to fuck them."),
								null, null, null, null, null,
								true, false,
								new SMDoggy(
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.DOGGY_BEHIND)),
										Util.newHashMapOfValues(
												new Value<>(charactersPresent.get(0), SexPositionSlot.DOGGY_ON_ALL_FOURS),
												new Value<>(charactersPresent.get(1), SexPositionSlot.DOGGY_ON_ALL_FOURS_SECOND))),
								null,
								AFTER_SEX, "<p>"
									+ ""//TODO
								+ "</p>") {
							@Override
							public void effects() {
								if(slave().isVisiblyPregnant()){
									slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
							}
						};
					} else {
						return new Response("Side-by-side", "Another slave needs to be present for this...",null);
					}
				
				} else if (index == 0) {
					return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", SLAVE_START) {
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							if(slave().isVisiblyPregnant()){
								slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
							Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
						}
					};
					
				} else  {
					return null;
				}
				
			} else if(responseTab == 2) {
				switch(index) {
					case 1:
						return new Response("Inspect",
								"Will be added soon", null);
					case 2:
						return new Response("Job",
								"Will be added soon", null);
					case 3:
						return new Response("Permissions",
								"Will be added soon", null);
					case 4:
						return new ResponseEffectsOnly("Inventory",
								"Manage [npc.namePos] inventory.") {
									@Override
									public void effects() {
										if(slave().isVisiblyPregnant()){
											slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
										}
										Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(slave());
										Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
									}
								};
					case 5:
						return new Response("Send to Kate", "Will be added soon!", null);
					case 0:
						return new Response("Leave", "Tell [npc.name] that you'll catch up with [npc.herHim] some other time.", SLAVE_START) {
							@Override
							public DialogueNodeOld getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
							@Override
							public void effects() {
								if(slave().isVisiblyPregnant()){
									slave().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
								}
								Main.game.getDialogueFlags().setSlaveryManagerSlaveSelected(null);
							}
						};
								
					default:
						return null;
				}
				
			} else {
				return null;
			}
		}
	};
	
	private static String getFooterText() {
		return "<p><i>"
				+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
						?"From the way [npc.she] keeps on glancing hungrily at your body, you can tell that [npc.sheIs] attracted to you..."
						:"[npc.She] doesn't show any interest in being attracted to you...")
					+ "</i></p>";
	}
	
	public static final DialogueNodeOld SLAVE_PROGRESSION = new DialogueNodeOld("", "", true, true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Talking with [npc.Name]";
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "<i>This isn't fully implemented yet!</i>"
					+ "</p>"
					+ "<p>"
						+ "Deciding that you'd like to talk to [npc.name] about something a little more serious, you ask [npc.herHim] about [npc.her] life before moving in with you,"
						+ " [pc.speech(I'd like to know a little more about your past, [npc.name]. What was your life like before coming here?)]"
					+ "</p>"
					+ "<p>");
			
			UtilText.nodeContentSB.append("</p>"
					+getFooterText());
			
			return UtilText.parse(slave(), UtilText.nodeContentSB.toString());
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SLAVE_START.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVE_START.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>");
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech([npc.pcName]! I'm still horny!)]"
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Decide what to do next.", SLAVE_START);
				
			} else {
				return null;
			}
		}
	};
	
}
