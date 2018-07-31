package com.lilithsthrone.game.character.quests;

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
					+ " After waking up in the middle of an unfamiliar street, you were saved from a dire situation by the half-demon 'Lilaya'."
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
			return "Lilaya ran some more tests on you, but she's unable to progress with her research without the help of her old colleague, Arthur.";
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

	MAIN_1_C_WOLFS_DEN(QuestType.MAIN, 3, 20) {
		@Override
		public String getName() {
			return "The search for Arthur; The Wolf's Den";
		}

		@Override
		public String getDescription() {
			return "Arthur has been arrested by Dominion's Enforcers, and has been taken to the Enforcer's HQ." + " It looks like you'll have to inquire further there and find out a way to save Arthur.";
		}

		@Override
		public String getCompletedDescription() {
			return "You were forced into a fight with the Enforcer's Chief, Brax." + " Thankfully, you were able to beat him, but you then found out that Arthur has been sold into slavery!";
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
	
	
	// For when you discover your first essence:
	
	SIDE_ENCHANTMENTS_LILAYA_HELP(QuestType.SIDE, 1, 10) {
		@Override
		public String getName() {
			return "Ask Lilaya for help";
		}

		@Override
		public String getDescription() {
			return "You recently felt a strange force entering your body, and although it doesn't seem to have had any obvious effect, you should probably have it checked out."
					+ " Lilaya is sure to know more, so perhaps you should go and talk to her about it.";
		}

		@Override
		public String getCompletedDescription() {
			return "Lilaya informed you that you're able to collect 'essences' from other people's arcane aura."
					+ " She seemed a little worried that you're able to do this, as apparently it's normally only Lilin who are able to gather essences in this fashion...";
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
	},
	
	
	RELATIONSHIP_NYAN_STOCK_ISSUES(QuestType.RELATIONSHIP, 1, 0) {
		@Override
		public String getName() {
			return "Offer Nyan your help";
		}

		@Override
		public String getDescription() {
			return "Nyan explained that she's unable to sell any enchanted clothing due to the fact that her suppliers don't offer any."
					+ " Apparently, these suppliers have used force to drive all of their rivals out of the area, so she has no alternative but to use them...<br/>"
					+ "Perhaps you could offer to convince these new suppliers to let the old ones back?";
		}

		@Override
		public String getCompletedDescription() {
			return "You offered Nyan your help in convincing the new suppliers to let the old ones back.";
		}
	},
	
	RELATIONSHIP_NYAN_STOCK_ISSUES_AGREED_TO_HELP(QuestType.RELATIONSHIP, 10, 0) {
		@Override
		public String getName() {
			return "Confront the suppliers";
		}

		@Override
		public String getDescription() {
			return "Nyan explained that the suppliers still work out of the supply depot here in the Shopping Arcane."
					+ " From all that Nyan's told you about these new suppliers, you should be prepared for a tough fight if you decide to go and talk to them...";
		}

		@Override
		public String getCompletedDescription() {
			return "You put an end to the dobermanns' monopoly, and although they'll continue to work as clothing suppliers, they'll stop intimidating the others.";
		}
	},
	
	RELATIONSHIP_NYAN_STOCK_ISSUES_SUPPLIERS_BEATEN(QuestType.RELATIONSHIP, 10, 100) {
		@Override
		public String getName() {
			return "Reward";
		}

		@Override
		public String getDescription() {
			return "You should return to Nyan and get the reward she promised you.";
		}

		@Override
		public String getCompletedDescription() {
			return "Nyan paid you the reward she promised, and also offered to give you a 25% discount in her store. She also let slip that she's single, in a clumsy attempt to hit on you...";
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
