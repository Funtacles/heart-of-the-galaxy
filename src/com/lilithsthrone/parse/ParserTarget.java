package com.lilithsthrone.parse;

import java.util.List;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69.9
 * @version 0.2.5
 * @author Innoxia
 */
public enum ParserTarget {
	
	STYLE(Util.newArrayListOfValues(
			"style",
			"game"),
			"Returns the same as 'pc', but should be used for style methods such as style.bold or style.italics or conditional methods such as game.isArcaneStorm.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					return Main.game.getPlayer();
				}
			},
	
	PC(Util.newArrayListOfValues(
			"pc",
			"player"),
			"The player character.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					return Main.game.getPlayer();
				}
			},
	
	NPC(Util.newArrayListOfValues(
			"npc",
			"npc1",
			"npc2",
			"npc3",
			"npc4",
			"npc5",
			"npc6"),
			"The currently 'active' NPC.<br/>"
			+"<b>The tag 'npc' can be extended with a number, starting at 1, to signify which npc in the scene it is referring to!</b> e.g. 'npc1' is the first npc, 'npc2' is the second, etc.<br/>"
			+ "If in <b>combat</b>, it returns your opponent.<br/>"
			+ "If in <b>sex</b>, it returns your partner.<br/>"
			+ "<b>Otherwise</b>, it returns the most important NPC in the scene.") {
				@Override
				public GameCharacter getCharacter(String tag) {
					if(!UtilText.getSpecialNPCList().isEmpty()) {
						if(tag.equalsIgnoreCase("npc")) {
							return UtilText.getSpecialNPCList().get(0);
						} else {
							return UtilText.getSpecialNPCList().get(Math.max(0, Integer.parseInt(tag.substring(3))-1));
						}
						
					} else if(Main.game.isInCombat()) {
						return Combat.getActiveNPC();
						
					} else if (Main.game.isInSex()) {
						return Sex.getActivePartner();
						
					} else if (Main.game.getCurrentDialogueNode()!=null) {
						if(Main.game.getCurrentDialogueNode()==CharactersPresentDialogue.MENU
								 || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS
								 || Main.game.getCurrentDialogueNode()==PhoneDialogue.CONTACTS_CHARACTER) {
							return CharactersPresentDialogue.characterViewed;
							
						} else if(Main.game.getActiveNPC()!=null) {
							return Main.game.getActiveNPC();
							
						} else if (!Main.game.getCharactersPresent().isEmpty()) {
							List<NPC> charactersPresent = Main.game.getCharactersPresent();
							if(tag.equalsIgnoreCase("npc")) {
								return charactersPresent.get(0);
							} else {
								return charactersPresent.get(Math.min(charactersPresent.size()-1, Math.max(0, Integer.parseInt(tag.substring(3))-1)));
							}
							
						} else {
							throw new NullPointerException();
						}
						
					} else {
						throw new NullPointerException();
					}
				}
			},
	
	PROLOGUE_FEMALE(Util.newArrayListOfValues("prologueFemale"), "") {
		public String getDescription() {
			return Main.game.getPrologueFemale().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getPrologueFemale();
		}
	},
	
	NPC_MALE(Util.newArrayListOfValues(
			"NPCmale",
			"maleNPC",
			"genericMale",
			"maleGeneric"), "") {
		public String getDescription() {
			return Main.game.getGenericMaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericMaleNPC();
		}
	},
	
	NPC_FEMALE(Util.newArrayListOfValues(
			"NPCfemale",
			"femaleNPC",
			"genericFemale",
			"femaleGeneric"), "") {
		public String getDescription() {
			return Main.game.getGenericFemaleNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericFemaleNPC();
		}
	},
	
	NPC_ANDROGYNOUS(Util.newArrayListOfValues(
			"NPCandrogynous",
			"androgynousNPC",
			"NPCambiguous",
			"ambiguousNPC"), "") {
		public String getDescription() {
			return Main.game.getGenericAndrogynousNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getGenericAndrogynousNPC();
		}
	},
	
	TEST_NPC(Util.newArrayListOfValues(
			"testNPC",
			"test"), "") {
		public String getDescription() {
			return Main.game.getTestNPC().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getTestNPC();
		}
	},
	
	LILAYA(Util.newArrayListOfValues(
			"lilaya",
			"aunt"), "") {
		public String getDescription() {
			return Main.game.getLilaya().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getLilaya();
		}
	},
	
	ROSE(Util.newArrayListOfValues("rose"), "") {
		public String getDescription() {
			return Main.game.getRose().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getRose();
		}
	},
	
	ARTHUR(Util.newArrayListOfValues("arthur"), "") {
		public String getDescription() {
			return Main.game.getArthur().getDescription();
		}

		@Override
		public GameCharacter getCharacter(String tag) {
			return Main.game.getArthur();
		}
	};
	
	
	
	private String description;
	private List<String> tags;
	private ParserTarget(List<String> tags, String description) {
		this.tags=tags;
		this.description=description;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public String getDescription() {
		return description;
	}
	
	public abstract GameCharacter getCharacter(String tag);
}
