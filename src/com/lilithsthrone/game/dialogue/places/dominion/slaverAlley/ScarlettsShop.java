package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.main.Main;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class ScarlettsShop {
	
	public static final DialogueNodeOld SCARLETTS_SHOP_EXTERIOR = new DialogueNodeOld("Scarlett's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Tucked away in one corner of Slaver Alley, you see a shop that's quite unlike all the others."
					+ " Although the words 'Scarlett's Shop; open for business' are painted in fancy gold lettering above the door, the large glass windows don't show any sign of there being any slaves for sale."
					+ " While the areas in front of all of the other shops in Slaver Alley are filled with advertisement boards and platforms upon which to display their goods,"
						+ " the area in front of 'Scarlett's Shop' is noticeably barren; yet further proof that this particular store must have fallen upon hard times."
				+ "</p>"
				+ (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA
					?"<p>"
						+ "You haven't gone to report to Scarlett's matriarch, Alexa, yet, and you don't really want to have to talk to Scarlett until you've done as she's asked."
						+ " Scarlett said that you can find Alexa up in the Harpy Nests, so you'll need to go there report to her before stepping foot inside this shop again."
					+ "</p>"
					:"");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_E_REPORT_TO_ALEXA) {
					return new Response("Enter", "You should go and find Alexa before entering Scarlett's Shop again.", null);
					
				} else {
					return new Response("Enter", "Enter the shop.", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SCARLETTS_SHOP = new DialogueNodeOld("Scarlett's shop", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
				return "<p>"
						+ "Pushing open the door, you find that the shop's interior is just as bare as you feared."
						+ " There isn't a single slave for sale in sight, and the only sign of life is a rather defeated-looking harpy slouched down at the shop's front desk."
					+ "</p>"
					+ "<p>"
						+ "The harpy, who you assume to be Scarlett, looks up at you as you enter the shop, and with an annoyed huff, she shouts out, "
						+ "[scarlett.speech(If you aren't here to donate any slaves, then you'd better turn around and fuck right off! I'm not in the mood to deal with any fucking morons like you today.)]"
					+ "</p>"
					+ "<p>"
						+ "Apparently this extremely rude harpy is the person that you need to deal with, and, letting out an annoyed sigh, you wonder if you should ask about Arthur now, or come back at another time."
					+ "</p>";
				
			} else {
				return "<p>"
							+ "Pushing open the door, you find that the shop's interior is just as bare as you feared."
							+ " There isn't a single slave for sale in sight, and the only sign of life is a rather defeated-looking harpy slouched down at the shop's front desk."
						+ "</p>"
						+ "<p>"
							+ "The harpy, who you assume to be Scarlett, looks up at you as you enter the shop, and with an annoyed huff, she shouts out, "
							+ "[scarlett.speech(If you aren't here to donate any slaves, then you'd better turn around and fuck right off! I'm not in the mood to deal with any fucking morons like you today.)]"
						+ "</p>"
						+ "<p>"
							+ "You're quite taken aback at Scarlett's rude words, and, deciding that it's probably best not to get involved with someone like that, you turn around and head for the exit."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getQuest(QuestLine.MAIN) == Quest.MAIN_1_D_SLAVERY) {
					return new Response("Ask for Arthur", "Ask Scarlett if she's got a slave named Arthur for sale.", SCARLETT_IS_A_BITCH);

				}else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNodeOld SCARLETT_IS_A_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Letting out a defeated sigh as you wonder why everything has to be so difficult, you walk towards the front desk where Scarlett is sitting."
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Didn't I tell you to fuck off already?)]"
						+ " the rude harpy calls out, sitting up in her chair as you approach."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Look, I just need to know if you've got a slave for sale going by the name of 'Arthur', ok?)]"
					+ "</p>"
					+ "<p>"
						+ "As you mention the name 'Arthur', Scarlett stands up from behind her desk, and her cheeks flush red as she starts to shout and curse,"
						+ " [scarlett.speech(If I hear that fucking name one more time, I swear I'm going to kill someone!"
						+ " Are you working with them?! Huh?! Those fucking cunts who fucked me over?! You've got five fucking seconds to start explaining!)]"
					+ "</p>"
					+ "<p>"
						+ "Despite her foul language and aggressive posturing, you find Scarlett's little outburst to be more embarrassing rather than anything else."
						+ " She clearly lacks the physical strength required in order to follow through on any of her threats, so it's more out of a desire to calm her down rather than due to feeling intimidated that you start to answer her,"
						+ " [pc.speech(Calm down, I'm not working with 'them', whoever they might be. I'm just a friend of Arthur's who's been trying to track him down."
						+ " I found out from a helpful enforcer that he was meant to have been given to you."
						+ " If you're still in possession of him, I'd like to see if I can buy his freedom, and if not, then could you [style.italics(please)] just tell me who you sold him to?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Huh,)]"
						+ " Scarlett huffs as she sits back down, "
						+ "[scarlett.speech(so I guess you really are as stupid as you look. Can't you see that I've got no slaves for sale? I'm fucking finished. And it's all thanks to that business with Arthur...)]"
					+ "</p>"
					+ "<p>"
						+ "Rolling your eyes at the annoying harpy's reaction, you don't get any time to respond before she continues,"
						+ " [scarlett.speech(There might be a way for both of us to profit here though."
						+ " Yeah, I know what happened to Arthur, [style.italics(and)] where he's gone, but I'm also not going to tell a fucking idiot like you anything about it."
						+ " Not without helping me out first, at least.)]"
					+ "</p>"
					+ "<p>"
						+ "Wondering just how many different people you're going to have to deal with before finally finding Arthur, you sigh,"
						+ " [pc.speech(And what is it I'd need to do?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Even a clueless moron like you can see that this business is a complete failure,)]"
						+ " Scarlett grumbles as she leans back in her chair, "
						+ "[scarlett.speech(and my matriarch is [style.italics(not)] going to be happy when she finds out."
						+ " If you want to find out what happened to your stupid little friend, you're going to go up to the Harpy Nests, find a matriarch called 'Alexa',"
							+ " tell her that my business is bust, and take whatever punishment she'll decide upon on my behalf.)]"
					+ "</p>"
					+ "<p>"
						+ "You realise that if you ever want to find out what happened to Arthur, you're going to have to agree to Scarlett's demands, even if you don't actually plan on taking any punishment on her behalf."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to help Scarlett.", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.MAIN, Quest.MAIN_1_E_REPORT_TO_ALEXA));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SCARLETT_IS_A_SUPER_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding to just agree with the insufferable harpy for now, you respond,"
						+ " [pc.speech(Fine, I'll do it, but you'd better keep your end of the bargain here. When I get back, you're going to tell me exactly what's happened to Arthur, ok?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Just fuck off and do it already!"
						+ " Fuck, you're a real asshole to try and talk to, you know?!)]"
						+ " Scarlett shouts, looking just as infuriated as you feel right now. "
						+ "[scarlett.speech(Don't bother coming back here until you've taken Alexa's punishment, ok?"
						+ " And don't let her go easy on you, I'm gonna enjoy hearing what she did to you when you get back!)]"
					+ "</p>"
					+ "<p>"
						+ "Not wanting to waste any more time talking to her, you turn your back on Scarlett and walk out of the shop."
						+ " As the door swings shut behind you, you find yourself wondering if you've ever met anyone as annoying as that harpy is."
						+ " You seriously hope that this matriarch, 'Alexa', is nothing like Scarlett, and as you set off in the direction of the Harpy Nests, you wonder how many more hurdles you'll be presented with before finally finding Arthur..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}
		}
	};
}
