package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.main.Main;

/**
 * @since 0.1.0
 * @version 0.1.99
 * @author Innoxia
 */
public enum Quest {
	
	
	// Main quests:

	MAIN_PROLOGUE(QuestType.MAIN, 1, 5) {
		@Override
		public String getName() {
			return "Survive the evening";
		}

		@Override
		public String getDescription() {
			return "You promised your aunt Lily that you'd attend the opening of her museum's new exhibit. You need to survive the boredom of the evening ahead.";
		}

		@Override
		public String getCompletedDescription() {
			return "Your evening at the museum turned out to be far more eventful than you'd have liked." + " A mysterious demon named Lilith tricked you into being pulled through a magical portal and into a parallel universe."
					+ " After waking up in the middle of an unfamiliar street, you were saved from a dire situation by the half-demon '" + Main.game.getLilaya().getName() + "'."
					+ " She seems to be this universe's version of your aunt Lily, and, in return for agreeing to help her with her experiments, she's allowed you to stay at her home.";
		}
	},

	MAIN_1_A_LILAYAS_TESTS(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya's tests";
		}

		@Override
		public String getDescription() {
			return "You can find Lilaya in her lab at any time, where she'll be ready to continue running her tests on you. Maybe she can find a way to send you back home?";
		}

		@Override
		public String getCompletedDescription() {
			return Main.game.getLilaya().getName() + " ran some more tests on you, but she's unable to progress with her research without the help of her old colleague, Arthur.";
		}
	},

	MAIN_1_B_DEMON_HOME(QuestType.MAIN, 1, 10) {
		@Override
		public String getName() {
			return "The search for Arthur; Demon Home";
		}

		@Override
		public String getDescription() {
			return "Lilaya has informed you that her old colleague, Arthur, would know more about the type of magic used in the portal."
					+ " However, she seems to have an intense dislike of him, and you've ended up being tasked to go and get him to apologise to Lilaya before she'll allow him to" + " come and work with her."
					+ " Arthur lives in a district of the city called 'Demon Home', so you can find him there.";
		}

		@Override
		public String getCompletedDescription() {
			return "When you arrived at Arthur's home, you found that Dominion's Enforcers had arrested him on suspicion of plotting against Lilith." + " After his arrest, he was taken to the Enforcer's HQ.";
		}
	},

	MAIN_1_E_REPORT_TO_ALEXA(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Find Alexa";
		}

		@Override
		public String getDescription() {
			return "After finding Scarlett in Slaver Alley, you discovered that she's no longer in possession of Arthur."
					+ " Before she'll tell you anything about it, she wants you to go the Harpy Nests and report to her matriarch, Alexa, that her business is a complete failure";
		}

		@Override
		public String getCompletedDescription() {
			return "You reported Scarlett's problems to her matriarch, Alexa."
					+ " She didn't seem to have much sympathy for Scarlett, and quickly flew off to go and talk to her in person.";
		}
	},
	
	MAIN_1_F_SCARLETTS_FATE(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Scarlett's fate";
		}

		@Override
		public String getDescription() {
			return "You need to travel back to Scarlett's shop to find out what's become of her. Hopefully Alexa wasn't too hard on her, and she'll be willing to tell you what happened to Arthur now...";
		}

		@Override
		public String getCompletedDescription() {
			return "You travelled back to Scarlett's shop, only to discover that Alexa has enslaved her!";
		}
	},
	
	MAIN_1_G_SLAVERY(QuestType.MAIN, 3, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Slavery";
		}

		@Override
		public String getDescription() {
			return "Alexa is willing to sell Scarlett to you, which seems to be the only way you'll get the information you need."
					+ " You'll need to have a slaver license in order to buy Scarlett.";
		}

		@Override
		public String getCompletedDescription() {
			return "Alexa sold Scarlett to you, which allowed you to order Scarlett to tell you what happened to Arthur.";
		}
	},
	
	MAIN_1_H_THE_GREAT_ESCAPE(QuestType.MAIN, 10, 200) {
		@Override
		public String getName() {
			return "The search for Arthur; The Great Escape";
		}

		@Override
		public String getDescription() {
			return "It turns out that Arthur was sold to an extremely dangerous demon called Zaranix, who lives in Demon Home."
					+ " You'll need to travel to demon home and rescue Arthur!";
		}

		@Override
		public String getCompletedDescription() {
			return "After defeating Zaranix, you saved Arthur and brought him back to Lilaya's home.";
		}
	},
	
	MAIN_1_I_ARTHURS_TALE(QuestType.MAIN, 10, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; Conclusion";
		}

		@Override
		public String getDescription() {
			return "Now that you've rescued Arthur from the clutches of Zaranix, you should travel back to Lilaya's home and get the full story of what happened from him.";
		}

		@Override
		public String getCompletedDescription() {
			return "Arthur explained how he was dabbling in the forbidden art of teleportation spells."
					+ " Through one of his agents, Zaranix found out about this, and had no difficulty in getting Arthur enslaved for treason."
					+ " Now that you've rescued him, he's keen to repay the favour by finding out how to send you back home.";
		}
	},
	
	MAIN_1_J_ARTHURS_ROOM(QuestType.MAIN, 10, 30) {
		@Override
		public String getName() {
			return "The search for Arthur; A room of his own";
		}

		@Override
		public String getDescription() {
			return "Lilaya really doesn't want Arthur in her lab, and has tasked you to help Rose find a suitable room for him to stay in.<br/>"
					+ "<i>Go into one of the empty rooms in Lilaya's House, and through the room management window, upgrade it to 'Arthur's Room'.</i>";
		}

		@Override
		public String getCompletedDescription() {
			return "You located a suitable room for Arthur, and, with Rose's help, moved a significant amount of arcane instrumentation into his new lab-cum-bedroom.";
		}
	},
	
	// Side Quests:

	SIDE_UTIL_COMPLETE(QuestType.SIDE, 1, 0) {
		@Override
		public String getName() {
			return "Quest Complete!";
		}

		@Override
		public String getDescription() {
			return "Quest complete!";
		}

		@Override
		public String getCompletedDescription() {
			return "Quest complete!";
		}
	},
	
	SIDE_DISCOVER_ALL_ITEMS(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Completionist";
		}

		@Override
		public String getDescription() {
			return "There are a lot of magical items in this new world. You wonder if you can find them all...";
		}

		@Override
		public String getCompletedDescription() {
			return "You have discovered every item there is to find!";
		}
	},

	SIDE_DISCOVER_ALL_RACES(QuestType.SIDE, 1, 100) {
		@Override
		public String getName() {
			return "Completionist";
		}

		@Override
		public String getDescription() {
			return "There seem to be a lot of strange new races in this world. You wonder if you can discover them all...";
		}

		@Override
		public String getCompletedDescription() {
			return "You have discovered every race there is to find!.";
		}
	},
	
	
	// For the first time you get pregnant:
	
	SIDE_PREGNANCY_CONSULT_LILAYA(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Lilaya knows best";
		}

		@Override
		public String getDescription() {
			return "You're pregnant... Oh crap, <b>you're pregnant</b>! Surely Lilaya will know what to do!";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya managed to calm you down, and reassured you that pregnancy in this world isn't as big a deal as it was back home.";
		}
	},
	SIDE_PREGNANCY_LILAYA_THE_MIDWIFE(QuestType.SIDE, 1, 20) {
		@Override
		public String getName() {
			return "Lilaya the midwife";
		}

		@Override
		public String getDescription() {
			return "Lilaya said that she'd be able to help you give birth whenever you're ready. You're going to need to wait until your belly has finished growing, then you can go and see Lilaya to give birth.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya helped you give birth. She said that if ever you get pregnant again, she can always help out.";
		}
	},
	
	// Getting a Slaver License:
	
	SIDE_SLAVER_NEED_RECOMMENDATION(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Letter of recommendation";
		}

		@Override
		public String getDescription() {
			return "After asking how to obtain a Slaver License at the Slavery Administration building, you discovered that you'll need a letter of recommendation first. Lilaya should be able to help with that.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya gave you a letter of recommendation, and what's more, she also offered to let you house your slaves in her mansion.";
		}
	},
	SIDE_SLAVER_RECOMMENDATION_OBTAINED(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Present letter";
		}

		@Override
		public String getDescription() {
			return "Now that you've obtained a letter of recommendation from Lilaya, you should go back to the Slavery Administration building in Slaver Alley and present it to [finch.name].";
		}

		@Override
		public String getCompletedDescription() {
			return "You presented the letter of recommendation to [finch.name], and, after paying the 500 flame fee, you obtained a slaver license!";
		}
	},
	
	// Accommodation:
	
	SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION(QuestType.SIDE, 1, 5) {
		@Override
		public String getName() {
			return "Lilaya's B&B";
		}

		@Override
		public String getDescription() {
			return "After getting to know one of the residents of Dominion's alleyways, you'd like to offer them a real home. Lilaya's mansion is full of empty rooms, so you should ask her if you could use one to house your new friend.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya gave you permission to use the empty rooms to house your friends and family, on the condition that you pay for the expenses that are incurred.";
		}
	},
	
	// Other:
	
	SIDE_HYPNO_WATCH_VICKY(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Order at Arcane Arts";
		}

		@Override
		public String getDescription() {
			return "Arthur informed you that he was instructed by Zaranix to find an arcane method of changing a person's sexual orientation."
					+ " While he reassured you that he had no intention of ever using such an item himself, Arthur did express an interest in completing his research,"
						+ " and asked you to fetch a special order from the store 'Arcane Arts' in the Shopping Arcade.";
		}

		@Override
		public String getCompletedDescription() {
			return "You retrieved the package from Arcane Arts, and brought it back to Arthur.";
		}
	},
	
	SIDE_HYPNO_WATCH_TEST_SUBJECT(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Test subject";
		}

		@Override
		public String getDescription() {
			return "After Lilaya followed Arthur's instructions to enchant the watch, she asked if it would be possible to test it on you...";
//					+ " You could either offer yourself, or, if you own any slaves, offer one of those to Arthur instead.";
		}

		@Override
		public String getCompletedDescription() {
			return "The Hypno-Watch appeared to work, although Lilaya stopped the test before it had a permanent effect."
					+ " She warned that it will have a strong corruptive effect upon the mind of the whoever is targeted, and disenchanted it for good measure, before handing it over to you.";
		}
	}
	;

	private int level, experienceReward;
	private QuestType questType;

	private Quest(QuestType questType, int level, int experienceReward) {
		this.questType = questType;

		this.level = level;
		this.experienceReward = experienceReward;
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getCompletedDescription();

	public int getLevel() {
		return level;
	}

	public QuestType getQuestType() {
		return questType;
	}

	public int getExperienceReward() {
		return experienceReward;
	}

}
