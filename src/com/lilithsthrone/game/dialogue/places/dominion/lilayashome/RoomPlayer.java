package com.lilithsthrone.game.dialogue.places.dominion.lilayashome;

import java.util.HashSet;
import java.util.Set;

import com.lilithsthrone.game.character.attributes.Attribute;

import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.75
 * @version 0.2.8
 * @author Innoxia
 */
public class RoomPlayer {
	
	private static int sleepTimer = 240;

	private static Response getResponseRoom(int index) {
		int minutesPassed = (int) (Main.game.getMinutesPassed() % (24 * 60));
		
		sleepTimer = (Main.game.isDayTime()
				? (int) ((60 * 21) - minutesPassed)
				: (int) ((60 * (minutesPassed<(60*7)?7:31)) - minutesPassed));

		if (index == 1) {
			return new Response("Rest", "Rest for four hours. As well as replenishing your energy and aura, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLust(0);
					Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + 240);
				}
			};

		} else if (index == 2) {
			return new Response("Rest until " + (Main.game.isDayTime() ? "Evening" : "Morning"),
					"Rest for " + (sleepTimer >= 60 ? sleepTimer / 60 + " hours " : " ")
					+ (sleepTimer % 60 != 0 ? sleepTimer % 60 + " minutes" : "")
					+ " until " + (Main.game.isDayTime() ? "evening (21:00)." : "morning (07:00).")
					+ " As well as replenishing your energy and aura, you will also get the 'Well Rested' status effect.", AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					Main.game.getPlayer().setLust(0);
					Main.game.getPlayer().addStatusEffect(StatusEffect.WELL_RESTED, (6 * 60) + sleepTimer);
				}
			};

		} else if (index == 3) {
			return new Response("Wash", "Use your room's en-suite to take a bath or shower. Rose will come and clean your clothes while you wash yourself.", AUNT_HOME_PLAYERS_ROOM_WASH){
				@Override
				public void effects() {
					Main.game.getPlayer().setHealth(Main.game.getPlayer().getAttributeValue(Attribute.HEALTH_MAXIMUM));
					Main.game.getPlayer().setMana(Main.game.getPlayer().getAttributeValue(Attribute.MANA_MAXIMUM));
					
					Set<SexAreaOrifice> dirtyOrifices = new HashSet<>();
					for(SexAreaOrifice ot: SexAreaOrifice.values()) {
						if(Main.game.getPlayer().getTotalFluidInArea(ot)>0) {
							dirtyOrifices.add(ot);
						}
					}
					
					Main.game.getPlayer().washAllOrifices();
					Main.game.getPlayer().calculateStatusEffects(0);
					Main.game.getPlayer().cleanAllDirtySlots();
					Main.game.getPlayer().cleanAllClothing();
					
					for(SexAreaOrifice orifice : SexAreaOrifice.values()) {
						if(dirtyOrifices.contains(orifice)) {
							switch(orifice) {
								case ANUS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.asshole] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.asshole]."));
									}
									break;
								case ASS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.ass] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.ass]."));
									}
									break;
								case BREAST:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.breasts] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.breasts]."));
									}
									break;
								case MOUTH:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "The shower does nothing to clean the cum out of your stomach!"));
									}
									break;
								case NIPPLE:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.nipples] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.nipples]."));
									}
									break;
								case THIGHS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum off of your [pc.thighs] as you can, but there's so much that's covering it, that you're unable to fully clean yourself!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum off of your [pc.thighs]."));
									}
									break;
								case URETHRA_PENIS:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your cock's urethra as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your cock's urethra."));
									}
									break;
								case URETHRA_VAGINA:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your vagina's urethra as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your vagina's urethra."));
									}
									break;
								case VAGINA:
									if(Main.game.getPlayer().getTotalFluidInArea(orifice)>0) {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(false, "You wash as much of the cum out of your [pc.pussy] as you can, but there's so much in there that you're unable to fully clean it all out!"));
									} else {
										Main.game.getTextEndStringBuilder().append(formatWashingArea(true, "You wash all of the cum out of your [pc.pussy]."));
									}
									break;
							}
						}
					}
					
					Main.game.getTextEndStringBuilder().append("<p>"
								+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>Your clothes have been cleaned, and you feel refreshed!</b>"
							+ "</p>");
				}
			};
		} else if (index == 6) {
			return new ResponseEffectsOnly("Entrance hall", "Fast travel down to the entrance hall."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_ENTRANCE_HALL, true);
				}
			};

		} else if (index == 7) {
			return new ResponseEffectsOnly("Lilaya's Lab", "Fast travel down to Lilaya's laboratory."){
				@Override
				public void effects() {
					Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.LILAYAS_HOUSE_GROUND_FLOOR), PlaceType.LILAYA_HOME_LAB, true);
				}
			};

		} else {
			return null;
		}
	}
	
	private static String formatWashingArea(boolean isFullyCleaned, String input) {
		return "<p style='color:"+(isFullyCleaned?Colour.GENERIC_GOOD.toWebHexString():Colour.CUM.toWebHexString())+";'><i>"
					+ input
				+ "</i></p>";
	}

	public static final DialogueNodeOld ROOM = new DialogueNodeOld("Your Room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Your room is well-furnished, containing a king-sized bed, two sets of drawers and a full-height wardrobe."
						+ " A row of windows provide a good view of the courtyard garden below, which is flanked on all sides by different wings of Lilaya's house."
					+ "</p>"
					+ "<p>"
						+ "Your private en-suite bathroom is accessible from here via a door on one side of the room."
						+ " The bathroom is considerably larger than any other that you've used before, and is extravagantly decorated in marble and gold."
					+ "</p>"
					+ "<p>"
						+ "Like everything else that normally would have run on electricity in your world, the lighting, shower, and radiators all appear to be powered by the arcane."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}
	};
	
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_SLEEP = new DialogueNodeOld("Your Room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 240;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
						+ " You feel extremely safe and comfortable here in Lilaya's home, and soon drift off to sleep, thinking about all the things that have happened to you recently..."
					+ "</p>"
					+ "<p>"
						+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
						+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto the bed."
						+ " You decide that you'd better get up, and as you do so, you let out a satisfied yawn and stretch your [pc.arms]."
						+ " You open the curtains and gather your things, ready to set out once more."
					+ "</p>"
					+ "<p>"
						+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_SLEEP_LONG = new DialogueNodeOld("Your Room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return sleepTimer;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "You set your phone's alarm before drawing the curtains, lying on your bed and closing your eyes."
						+ " You feel extremely safe and comfortable here in Lilaya's home, and soon drift off to sleep, thinking about all the things that have happened to you recently..."
					+ "</p>"
					+ "<p>"
						+ "<i>Beep-beep... beep-beep... bee-</i>"
					+ "</p>"
					+ "<p>"
						+ "Rolling over, you fumble for your phone, turning off the alarm before sinking back onto the bed."
						+ " You decide that you'd better get up, and as you do so, you let out a satisfied yawn and stretch your [pc.arms]."
						+ " You open the curtains and gather your things, ready to set out once more."
					+ "</p>"
					+ "<p>"
					+ "<b style='color:"+ Colour.GENERIC_GOOD.toWebHexString()+ ";'>You feel completely refreshed!</b>"
					+ "</p>";
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_WASH = new DialogueNodeOld("Your Room", "", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 20;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			UtilText.nodeContentSB.append("<p>"
						+ "You step into the en-suite, marvelling at its extravagant design. Leaving your dirty clothes on the other side of the door, you take a long, relaxing shower."
					+ "</p>");
			
			return UtilText.nodeContentSB.toString();
		}


		@Override
		public Response getResponse(int responseTab, int index) {
			return getResponseRoom(index);
		}

		@Override
		public boolean isInventoryDisabled() {
			return false;
		}
	};
	
	public static final DialogueNodeOld BACK_HOME_AFTER_SEX = new DialogueNodeOld("Your Room", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};

	public static final DialogueNodeOld AUNT_HOME_PLAYERS_ROOM_CLUBBER_TAKEN_HOME_SEND_HOME = new DialogueNodeOld("Your Room", "", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 2;
		}
		
		@Override
		public String getContent() {
			return "";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return ROOM.getResponse(responseTab, index);
		}
	};
}
