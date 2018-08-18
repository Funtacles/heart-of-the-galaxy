package com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade;

import com.lilithsthrone.game.character.npc.dominion.Nyan;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.82
 * @version 0.1.99
 * @author Innoxia
 */
public class ClothingEmporium {

	public static final DialogueNodeOld EXTERIOR = new DialogueNodeOld("Nyan's Clothing Emporium (Exterior)", "-", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXTERIOR");
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				if(Main.game.getDialogueFlags().values.contains(DialogueFlagValue.nyanIntroduced)) {
					return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING_REPEAT);
					
				} else {
					return new Response("Enter", "Step inside Nyan's Clothing Emporium.", SHOP_CLOTHING);
				}
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Arcade Entrance", "Fast travel to the entrance to the arcade."){
					@Override
					public void effects() {
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SHOPPING_ARCADE), PlaceType.SHOPPING_ARCADE_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Stockroom", "Nyan has shown you into the stockroom.", SHOP_CLOTHING_STOCK_ROOM) {
					@Override
					public void effects() {
						Main.game.getDialogueFlags().values.add(DialogueFlagValue.nyanIntroduced);
					}
				};
			}
			return null;
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_STOCK_ROOM = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_STOCK_ROOM");
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld SHOP_CLOTHING_REPEAT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getAuthor() {
			if(Main.game.getNyan().isPregnant() && !Main.game.getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return "Duner";
			} else {
				return "Innoxia";
			}
		}
		
		@Override
		public String getContent() {
			if(Main.game.getNyan().isVisiblyPregnant() && !Main.game.getNyan().isCharacterReactedToPregnancy(Main.game.getPlayer())) {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT_PREGNANT");
			} else {
				return UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_GREETING_REPEAT");
			}
		}

		@Override
		public String getResponseTabTitle(int index) {
			if(index==0) {
				return "Trade";
			} else if(index==1) {
				return "Talk";
			} else {
				return null;
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(responseTab==1) {
				if (index == 0) {
					return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
							if(Main.game.getNyan().isVisiblyPregnant()) {
								Main.game.getNyan().setCharacterReactedToPregnancy(Main.game.getPlayer(), true);
							}
						}
					};
					
				} else {
					return null;
				}
				
				
			} else if(responseTab==0) {
				if (index == 1) {
					return new ResponseTrade("Female Clothing", "Ask her what female clothing is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleClothing())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 2) {
					return new ResponseTrade("Female Lingerie", "Ask her what female lingerie is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleUnderwear())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 3) {
					return new ResponseTrade("Female Accessories", "Ask her what female accessories are available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonFemaleAccessories())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 6) {
					return new ResponseTrade("Male Clothing", "Ask her what male clothing is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleClothing())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 7) {
					return new ResponseTrade("Male Underwear", "Ask her what male underwear is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleLingerie())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 8) {
					return new ResponseTrade("Male Accessories", "Ask her what male accessories are is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonMaleAccessories())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 11) {
					return new ResponseTrade("Unisex Clothing", "Ask her what unisex clothing is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousClothing())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 12) {
					return new ResponseTrade("Unisex Underwear", "Ask her what unisex underwear is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousLingerie())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 13) {
					return new ResponseTrade("Unisex Accessories", "Ask her what unisex accessories are is available.", Main.game.getNyan()){
						@Override
						public void effects() {
							Main.game.getNyan().clearNonEquippedInventory();
	
							for (AbstractClothing c : ((Nyan) Main.game.getNyan()).getCommonAndrogynousAccessories())
								Main.game.getNyan().addClothing(c, false);
						}
					};
					
				} else if (index == 5) {
					return new Response("Specials", "Nyan doesn't have any special items of clothing on offer at the moment...", null);
				} else if (index == 0) {
					return new Response("Leave", "Tell Nyan that you've got to get going.", EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getTextStartStringBuilder().append(UtilText.parseFromXMLFile("places/dominion/shoppingArcade/clothingEmporium", "NYAN_EXIT"));
						}
					};
					
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ROMANCE_TALK = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed() {
			return 60;
		}
		
		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_TALK_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROMANCE_COMPLIMENT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_COMPLIMENT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROMANCE_FLIRT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_FLIRT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROMANCE_KISS = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_KISS_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROMANCE_MAKE_OUT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_BASE"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT"));
			UtilText.nodeContentSB.append(UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_MAKE_OUT_FINAL"));
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld END_SEX = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Sex.getNumberOfOrgasms(Main.game.getNyan())==0) {
				return UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_END_SEX_NO_ORGASM");
			} else {
				return UtilText.parseFromXMLFile("characters/dominion/nyan", "NYAN_END_SEX");
			}
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
	public static final DialogueNodeOld ROMANCE_GIFT = new DialogueNodeOld("Nyan's Clothing Emporium", "-", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "";
		}

		@Override
		public String getResponseTabTitle(int index) {
			return SHOP_CLOTHING_REPEAT.getResponseTabTitle(index);
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			return SHOP_CLOTHING_REPEAT.getResponse(responseTab, index);
		}
	};
	
}
