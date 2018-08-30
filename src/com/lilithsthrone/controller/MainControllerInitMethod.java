package com.lilithsthrone.controller;

import java.io.File;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.events.EventTarget;

import com.lilithsthrone.controller.eventListeners.InventorySelectedItemEventListener;
import com.lilithsthrone.controller.eventListeners.InventoryTooltipEventListener;
import com.lilithsthrone.controller.eventListeners.TooltipInformationEventListener;
import com.lilithsthrone.data.ImportExportManager;
import com.lilithsthrone.data.SaveManager;
import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.PropertyValue;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Breast;
import com.lilithsthrone.game.character.body.Covering;
import com.lilithsthrone.game.character.body.Testicle;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BodyCoveringType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyHair;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.BreastShape;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CoveringModifier;
import com.lilithsthrone.game.character.body.valueEnums.CoveringPattern;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.EyeShape;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HairStyle;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.LabiaSize;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenetrationModifier;
import com.lilithsthrone.game.character.body.valueEnums.PiercingType;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.TongueLength;
import com.lilithsthrone.game.character.body.valueEnums.TongueModifier;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.effects.PerkCategory;
import com.lilithsthrone.game.character.effects.PerkManager;
import com.lilithsthrone.game.character.effects.TreeEntry;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.gender.GenderPreference;
import com.lilithsthrone.game.character.markings.AbstractTattooType;
import com.lilithsthrone.game.character.markings.TattooCountType;
import com.lilithsthrone.game.character.markings.TattooCounter;
import com.lilithsthrone.game.character.markings.TattooCounterType;
import com.lilithsthrone.game.character.markings.TattooType;
import com.lilithsthrone.game.character.markings.TattooWriting;
import com.lilithsthrone.game.character.markings.TattooWritingStyle;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.persona.PersonalityTrait;
import com.lilithsthrone.game.character.persona.PersonalityWeight;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.game.combat.DamageType;
import com.lilithsthrone.game.dialogue.DebugDialogue;
import com.lilithsthrone.game.dialogue.DialogueNodeType;
import com.lilithsthrone.game.dialogue.places.dominion.shoppingArcade.SuccubisSecrets;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.story.CharacterCreation;
import com.lilithsthrone.game.dialogue.utils.BodyChanging;
import com.lilithsthrone.game.dialogue.utils.CharacterModificationUtils;
import com.lilithsthrone.game.dialogue.utils.CharactersPresentDialogue;
import com.lilithsthrone.game.dialogue.utils.GiftDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryDialogue;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.OptionsDialogue;
import com.lilithsthrone.game.dialogue.utils.PhoneDialogue;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.clothing.AbstractClothing;
import com.lilithsthrone.game.inventory.clothing.AbstractClothingType;
import com.lilithsthrone.game.inventory.clothing.ClothingType;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.inventory.weapon.AbstractWeapon;
import com.lilithsthrone.game.inventory.weapon.AbstractWeaponType;
import com.lilithsthrone.game.inventory.weapon.WeaponType;
import com.lilithsthrone.game.settings.KeyboardAction;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Artist;
import com.lilithsthrone.rendering.Artwork;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.rendering.RenderingEngine;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.ColourListPresets;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.places.PlaceType;

/**
 * This method was causing MainController to lag out Eclipse, so I moved it to a separate file.
 * 
 * @since 0.2.5
 * @version 0.2.6
 * @author Innoxia
 */
public class MainControllerInitMethod {

	public static void initMainControllerListeners() {
		MainController.document = (Document) MainController.webEngine.executeScript("document");
		MainController.EventListenerDataMap.put(MainController.document, new ArrayList<>());
		
		String id = "";
		
		if(MainController.flashMessageColour !=null && MainController.flashMessageText != null) {
			Main.game.flashMessage(MainController.flashMessageColour, MainController.flashMessageText);
			MainController.flashMessageColour = null;
			MainController.flashMessageText = null;
		}
		
		if (((EventTarget) MainController.document.getElementById("copy-content-button")) != null) {
			MainController.addEventListener(MainController.document, "copy-content-button", "click", MainController.copyDialogueButtonListener, false);
			MainController.addEventListener(MainController.document, "copy-content-button", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(MainController.document, "copy-content-button", "mouseleave", MainController.hideTooltipListener, false);
			MainController.addEventListener(MainController.document, "copy-content-button", "mouseenter", MainController.copyInfoListener, false);
		}
		
		if (((EventTarget) MainController.document.getElementById("export-character-button")) != null) {
			MainController.addEventListener(MainController.document, "export-character-button", "click", e -> {
				if(Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CHARACTER_APPEARANCE)) {
					Game.exportCharacter(Main.game.getPlayer());
				} else {
					Game.exportCharacter(CharactersPresentDialogue.characterViewed);
				}
				
				Main.game.flashMessage(Colour.GENERIC_EXCELLENT, "Character Exported!");
			}, false);
			MainController.addEventListener(MainController.document, "export-character-button", "mousemove", MainController.moveTooltipListener, false);
			MainController.addEventListener(MainController.document, "export-character-button", "mouseleave", MainController.hideTooltipListener, false);
			MainController.addEventListener(MainController.document, "export-character-button", "mouseenter", new TooltipInformationEventListener().setInformation(
					"Export Character",
					"Export the currently displayed character to the 'data/characters' folder. Exported characters can be imported at the auction block in Slaver Alley."), false);
		}
		
		if(Main.game.getCurrentDialogueNode().equals(CharactersPresentDialogue.MENU)
				|| Main.game.getCurrentDialogueNode().equals(PhoneDialogue.CONTACTS_CHARACTER)) {
			if(CharactersPresentDialogue.characterViewed instanceof NPC && !((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().isEmpty()) {
				
				try {
					Artwork artwork = ((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().get(((NPC)CharactersPresentDialogue.characterViewed).getArtworkIndex());
					
					id = "ARTWORK_INFO";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(!artwork.getArtist().getWebsites().isEmpty()) {
								Util.openLinkInDefaultBrowser(artwork.getArtist().getWebsites().get(0).getURL());
							}
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(
								"Artwork by <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getName()+"</b>",
								(artwork.getArtist().getWebsites().isEmpty()
										?"This artist has no associated websites!"
										:"Click this button to open <b style='color:"+artwork.getArtist().getColour().toWebHexString()+";'>"+artwork.getArtist().getWebsites().get(0).getName()+"</b>"
											+ " ("+artwork.getArtist().getWebsites().get(0).getURL()+") <b>externally</b> in your default browser!")),
								false);
					}
					
					id = "ARTWORK_PREVIOUS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(-1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_NEXT";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(artwork.getTotalArtworkCount()>1) {
								artwork.incrementIndex(1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_ARTIST_PREVIOUS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().size()>1) {
								((NPC)CharactersPresentDialogue.characterViewed).incrementArtworkIndex(-1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
					
					id = "ARTWORK_ARTIST_NEXT";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(((NPC)CharactersPresentDialogue.characterViewed).getArtworkList().size()>1) {
								((NPC)CharactersPresentDialogue.characterViewed).incrementArtworkIndex(1);
								CharactersPresentDialogue.resetContent(CharactersPresentDialogue.characterViewed);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
					}
				} catch(Exception ex) {
					System.err.println("MainController Artwork handling error.");
				}
				
			}
		}
		
		// -------------------- Debug menu -------------------- //
		
		if(Main.game.getCurrentDialogueNode().equals(DebugDialogue.SPAWN_MENU)) {
			id = "";
			
			for(AbstractClothingType clothingType : DebugDialogue.clothingTotal) {
				id = clothingType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addClothing(AbstractClothingType.generateClothing(clothingType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericClothing(clothingType, clothingType.getAvailablePrimaryColours().get(0));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractWeaponType weaponType : DebugDialogue.weaponsTotal) {
				id = weaponType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addWeapon(AbstractWeaponType.generateWeapon(weaponType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericWeapon(weaponType, weaponType.getAvailableDamageTypes().get(0));
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(AbstractItemType itemType : DebugDialogue.itemsTotal) {
				id = itemType.getId() + "_SPAWN";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.getActiveWorld().getCell(Main.game.getPlayer().getLocation()).getInventory().addItem(AbstractItemType.generateItem(itemType));
						MainController.updateUIRightPanel();
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setGenericItem(itemType);
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
			}
			
			for(InventorySlot slot : InventorySlot.values()) {
				id = slot + "_SPAWN_SELECT";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						DebugDialogue.activeSlot = slot;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
					
				}
			}
			id = "ITEM_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = null;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "BOOK_SPAWN_SELECT";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					DebugDialogue.activeSlot = null;
					DebugDialogue.itemTag = ItemTag.BOOK;
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
		
		// -------------------- Inventory listeners -------------------- //
		
		if(Main.game.isStarted()) {
			id = "";
			
			// Gifts:
			if(Main.game.getCurrentDialogueNode().equals(GiftDialogue.GIFT_DIALOGUE)) {
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeWeapon(entry.getKey());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer());
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeItem(entry.getKey());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
					id = "GIFT_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("Give Gift", ":3", GiftDialogue.getDialogueToProceedTo()){
								@Override
								public void effects() {
									Main.game.setResponseTab(GiftDialogue.getProceedDialogueTab());
									Main.game.getTextStartStringBuilder().append(GiftDialogue.getReceiver().getGiftReaction(entry.getKey(), true));
									Main.game.getPlayer().removeClothing(entry.getKey());
								}
							});
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			// Non-equipped inventory:
			for(int i=0 ; i<RenderingEngine.INVENTORY_PAGES; i++) {
				MainController.setInventoryPageLeft(i);
				MainController.setInventoryPageRight(i);
			}
			
			
			// Player:
			for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayer().getMapOfDuplicateWeapons().entrySet()) {
				id = "PLAYER_WEAPON_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractItem, Integer> entry : Main.game.getPlayer().getMapOfDuplicateItems().entrySet()) {
				id = "PLAYER_ITEM_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayer().getMapOfDuplicateClothing().entrySet()) {
				id = "PLAYER_CLOTHING_" + entry.getKey().hashCode();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), Main.game.getPlayer());
					MainController.addEventListener(MainController.document, id, "click", el, false);
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), Main.game.getPlayer(), null);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			// Partner:
			if(InventoryDialogue.getInventoryNPC()!=null) {
				String idModifier = "NPC_"+InventoryDialogue.getInventoryNPC().getId()+"_";
				for (Entry<AbstractWeapon, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateWeapons().entrySet()) {
					id = idModifier+"WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractClothing, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateClothing().entrySet()) {
					id = idModifier+"CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				for (Entry<AbstractItem, Integer> entry : InventoryDialogue.getInventoryNPC().getMapOfDuplicateItems().entrySet()) {
					id = idModifier+"ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), InventoryDialogue.getInventoryNPC());
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), InventoryDialogue.getInventoryNPC(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
			// Floor:
			} else {
				// Weapons on floor:
				for (Entry<AbstractWeapon, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateWeapons().entrySet()) {
					id = "FLOOR_WEAPON_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				// Clothing on floor:
				for (Entry<AbstractClothing, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateClothing().entrySet()) {
					id = "FLOOR_CLOTHING_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing(entry.getKey(), null, null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				
				// Items on floor:
				for (Entry<AbstractItem, Integer> entry : Main.game.getPlayerCell().getInventory().getMapOfDuplicateItems().entrySet()) {
					id = "FLOOR_ITEM_" + entry.getKey().hashCode();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory(entry.getKey(), null);
						MainController.addEventListener(MainController.document, id, "click", el, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem(entry.getKey(), null, null);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);	
					}
				}
			}
			
			if(InventoryDialogue.getNPCInventoryInteraction() == InventoryInteraction.TRADING) {
			
				if(InventoryDialogue.getInventoryNPC() != null) {
					// Buyback panel:
					for (int i = Main.game.getPlayer().getBuybackStack().size() - 1; i >= 0; i--) {
						if (((EventTarget) MainController.document.getElementById("WEAPON_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setWeaponInventory((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							((EventTarget) MainController.document.getElementById("WEAPON_" + i)).addEventListener("click",el, false);
							MainController.addEventListener(MainController.document, "WEAPON_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "WEAPON_" + i, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setWeapon((AbstractWeapon) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC());
							((EventTarget) MainController.document.getElementById("WEAPON_" + i)).addEventListener("mouseenter",el2, false);
						}
						
						if (((EventTarget) MainController.document.getElementById("CLOTHING_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setClothingInventory((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "click", el, false);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setClothing((AbstractClothing) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							MainController.addEventListener(MainController.document, "CLOTHING_" + i, "mouseenter", el2, false);
						}
						
						if (((EventTarget) MainController.document.getElementById("ITEM_" + i)) != null) {
							InventorySelectedItemEventListener el = new InventorySelectedItemEventListener().setItemInventory((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), i);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "click", el, false);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setItem((AbstractItem) Main.game.getPlayer().getBuybackStack().get(i).getAbstractItemSold(), InventoryDialogue.getInventoryNPC(), null);
							MainController.addEventListener(MainController.document, "ITEM_" + i, "mouseenter", el2, false);
						}
					}
				}
			}
			
			for(String occupantId : Main.game.getPlayer().getFriendlyOccupants()) {
				id = occupantId;
				NPC occupant = (NPC) Main.game.getNPCById(occupantId);
				if(occupant!=null) { // It shouldn't equal null...
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Inspect", "Will be added soon!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_JOB"; //TODO
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Job", "Will be added soon!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_PERMISSIONS"; //TODO
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Permissions", "Will be added soon!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_INVENTORY";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.mainController.openInventory(occupant, InventoryInteraction.FULL_MANAGEMENT);
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Manage Inventory",
								UtilText.parse(occupant, "Manage [npc.namePos] inventory."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_TRANSFER";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()) {
								@Override
								public void effects() {
									occupant.setHomeLocation(Main.game.getPlayer().getWorldLocation(), Main.game.getPlayer().getLocation());
									occupant.returnToHome();
								}
							});
						}, false);
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Here",
								UtilText.parse(occupant, "Move [npc.name] to your current location."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_TRANSFER_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Move Here",
								UtilText.parse(occupant, "You cannot move [npc.name] to this location, as there's no room for [npc.herHim] here."));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_COSMETICS";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
	
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send to Kate", "Will be added soon!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
					
					id = occupantId+"_COSMETICS_DISABLED";
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);

						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Send to Kate", "Will be added soon!");
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
			}
			
			// -------------------- Incest Renaming -------------------- //
			
			if(Main.game.getActiveNPC()!=null) {
				id = Main.game.getActiveNPC().getId()+"_PET_NAME";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
	
						boolean unsuitableName = false;
					 	if(Main.mainController.getWebEngine().executeScript("document.getElementById('offspringPetNameInput')")!=null) {
						 
							Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenFieldName').innerHTML=document.getElementById('offspringPetNameInput').value;");
							if(Main.mainController.getWebEngine().getDocument()!=null) {
								if (Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() < 2
										|| Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent().length() > 32)
									unsuitableName = true;
								else {
									unsuitableName = false;
								}
							}
							
							if (!unsuitableName) {
								Main.game.setContent(new Response("Rename", "", Main.game.getCurrentDialogueNode()){
									@Override
									public void effects() {
										Main.game.getActiveNPC().setPlayerPetName(Main.mainController.getWebEngine().getDocument().getElementById("hiddenFieldName").getTextContent());
									}
								});
							}
							
						}
							
					}, false);
				}
			}
			
			
			// -------------------- Character Creation -------------------- //
			
			if(!Main.game.isInNewWorld()) {
				if(Main.game.getCurrentDialogueNode().equals(CharacterCreation.CHOOSE_APPEARANCE)) {
					for(Month month : Month.values()) {
						id = "STARTING_MONTH_"+month;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setStartingDateMonth(month);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				
				
				// Sex experiences:
				for(int i : CharacterModificationUtils.soSilly) {
					
					// Given:
					
					id = "HANDJOBS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.URETHRA_PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_GIVEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					// Received:

					id = "HANDJOBS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.URETHRA_PENIS, SexAreaPenetration.FINGER), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "FINGERINGS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.FINGER), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "BLOWJOBS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "CUNNILINGUS_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "VAGINAL_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					
					id = "ANAL_TAKEN_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							CharacterModificationUtils.setSexExperience(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS), i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
			}
			
			if(!Main.game.isInNewWorld()
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_CORE)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_FACE)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_ASS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_BREASTS)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_VAGINA)
					|| Main.game.getCurrentDialogueNode().equals(BodyChanging.BODY_CHANGING_PENIS)) {
				
				
				// Gender:
				id = "CHOOSE_GENDER_MALE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						CharacterCreation.setGenderMale();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}

				id = "CHOOSE_GENDER_FEMALE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						CharacterCreation.setGenderFemale();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Femininity
				id = "CHOOSE_FEM_MASCULINE_STRONG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_MASCULINE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.MASCULINE.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_ANDROGYNOUS";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.ANDROGYNOUS.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CHOOSE_FEM_FEMININE_STRONG";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setFemininity(Femininity.FEMININE_STRONG.getMedianFemininity());
						if(!Main.game.isInNewWorld()) {
							CharacterCreation.getDressed();
						}
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				// Personality:
				for(PersonalityTrait trait : PersonalityTrait.values()) {
					id = "PERSONALITY_INFO_"+trait;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(trait.getName()), trait.getDescription()), false);
					}
					
					for(PersonalityWeight weight : PersonalityWeight.values()) {
						id = "PERSONALITY_"+trait+"_"+weight;
						if (((EventTarget) MainController.document.getElementById(id)) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								BodyChanging.getTarget().setPersonalityTrait(trait, weight);
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				
				// Orientation:
				for(SexualOrientation orientation : SexualOrientation.values()) {
					id = "SEXUAL_ORIENTATION_"+orientation;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setSexualOrientation(orientation);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
			
				// Height:
				id = "HEIGHT_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "HEIGHT_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementHeight(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Body size:
				for(BodySize bs : BodySize.values()) {
					id = "BODY_SIZE_"+bs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBodySize(bs.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Muscles:
				for(Muscle muscle : Muscle.values()) {
					id = "MUSCLE_"+muscle;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setMuscle(muscle.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// ------------ Character creation -------------- //
				
				// Lip Size:
				for(LipSize ls : LipSize.values()) {
					id = "LIP_SIZE_"+ls;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLipSize(ls.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Lip puffiness:
				id = "LIP_PUFFY_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().addFaceOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "LIP_PUFFY_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().removeFaceOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				for(HornLength hornLength : HornLength.values()) {
					id = "CHANGE_HORN_LENGTH_"+hornLength;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHornLength(hornLength.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TongueLength tongueLength : TongueLength.values()) {
					id = "CHANGE_TONGUE_LENGTH_"+tongueLength;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTongueLength(tongueLength.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(WingSize wingSize : WingSize.values()) {
					id = "CHANGE_WING_SIZE_"+wingSize;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setWingSize(wingSize.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Breast size:
				for(CupSize cs : CupSize.values()) {
					id = "BREAST_SIZE_"+cs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastSize(cs.getMeasurement());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Breast Shape:
				for(BreastShape bs : BreastShape.values()) {
					id = "BREAST_SHAPE_"+bs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastShape(bs);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple size:
				for(NippleSize ns : NippleSize.values()) {
					id = "NIPPLE_SIZE_"+ns;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleSize(ns.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Areolae size:
				for(AreolaeSize as : AreolaeSize.values()) {
					id = "AREOLAE_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAreolaeSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple puffiness:
				id = "NIPPLE_PUFFY_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().addNippleOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "NIPPLE_PUFFY_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().removeNippleOrificeModifier(OrificeModifier.PUFFY);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Lactation:
				for(int i : CharacterModificationUtils.getLactationQuantitiesAvailable()) {
					id = "LACTATION_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastMilkStorage(i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass size:
				for(AssSize as : CharacterModificationUtils.getAssSizesAvailable()) {
					id = "ASS_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Hip size:
				for(HipSize hs : CharacterModificationUtils.getHipSizesAvailable()) {
					id = "HIP_SIZE_"+hs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHipSize(hs.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Bleached anus:
				id = "ANUS_BLEACHED_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAssBleached(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "ANUS_BLEACHED_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setAssBleached(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				// Penis size:
				for(int ps : CharacterModificationUtils.getPenisSizesAvailable()) {
					id = "PENIS_SIZE_"+ps;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisSize(ps);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				// Penis girth:
				for(PenisGirth girth : PenisGirth.values()) {
					id = "PENIS_GIRTH_"+girth;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisGirth(girth.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				// Testicle size:
				for(TesticleSize ts : CharacterModificationUtils.getTesticleSizesAvailable()) {
					id = "TESTICLE_SIZE_"+ts;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTesticleSize(ts.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Cum production:
				for(CumProduction cp: CharacterModificationUtils.getCumProductionAvailable()) {
					id = "CUM_PRODUCTION_"+cp;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisCumStorage(cp.getMedianValue());
							if(!Main.game.isInNewWorld()) {
								BodyChanging.getTarget().fillCumToMaxStorage();
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina capacity:
				for(Capacity capacity: Capacity.values()) {
					id = "VAGINA_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina wetness:
				for(Wetness wetness: Wetness.values()) {
					id = "VAGINA_WETNESS_"+wetness;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina elastcity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "VAGINA_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina plasticity:
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "VAGINA_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Clit size:
				for(ClitorisSize cs: ClitorisSize.values()) {
					id = "CLITORIS_SIZE_"+cs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaClitorisSize(cs.getMedianValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Labia size:
				for(LabiaSize ls: LabiaSize.values()) {
					id = "LABIA_SIZE_"+ls;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaLabiaSize(ls.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				

				// ------------ Demonic transformations -------------- //
				
				for(ArmType armType: ArmType.values()) {
					id = "CHANGE_ARM_"+armType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setArmType(armType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(EyeType eyeType: EyeType.values()) {
					id = "CHANGE_EYE_"+eyeType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEyeType(eyeType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(EarType earType: EarType.values()) {
					id = "CHANGE_EAR_"+earType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setEarType(earType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HornType hornType: HornType.values()) {
					id = "CHANGE_HORN_"+hornType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHornType(hornType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HairType hairType: HairType.values()) {
					id = "CHANGE_HAIR_"+hairType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHairType(hairType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				for(LegType legType: LegType.values()) {
					id = "CHANGE_LEG_"+legType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLegType(legType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(FaceType faceType: FaceType.values()) {
					id = "CHANGE_FACE_"+faceType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setFaceType(faceType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(SkinType skinType: SkinType.values()) {
					id = "CHANGE_SKIN_"+skinType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setSkinType(skinType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TailType tailType: TailType.values()) {
					id = "CHANGE_TAIL_"+tailType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTailType(tailType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(WingType wingType: WingType.values()) {
					id = "CHANGE_WING_"+wingType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setWingType(wingType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(AssType assType: AssType.values()) {
					id = "CHANGE_ASS_"+assType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssType(assType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(BreastType breastType: BreastType.values()) {
					id = "CHANGE_BREAST_"+breastType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastType(breastType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				// Face:
				
				for(EyeShape eyeShape : EyeShape.values()) {
					id = "CHANGE_IRIS_SHAPE_"+eyeShape;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setIrisShape(eyeShape);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
					id = "CHANGE_PUPIL_SHAPE_"+eyeShape;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPupilShape(eyeShape);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(LipSize lipSize : LipSize.values()) {
					id = "CHANGE_LIP_SIZE_"+lipSize;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setLipSize(lipSize.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_MOUTH_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasFaceOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeFaceOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addFaceOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(TongueModifier tongueMod : TongueModifier.values()) {
					id = "CHANGE_TONGUE_MOD_"+tongueMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasTongueModifier(tongueMod)) {
								BodyChanging.getTarget().removeTongueModifier(tongueMod);
							} else {
								BodyChanging.getTarget().addTongueModifier(tongueMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass:
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_ANUS_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasAssOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeAssOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addAssOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Ass size:
				for(AssSize as : AssSize.values()) {
					id = "CHANGE_ASS_SIZE_"+as;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssSize(as.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(HipSize hs : HipSize.values()) {
					id = "CHANGE_HIP_SIZE_"+hs;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setHipSize(hs.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "ANUS_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Wetness wetness: Wetness.values()) {
					id = "ANUS_WETNESS_"+wetness;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssWetness(wetness.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "ANUS_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "ANUS_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setAssPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				
				// Breasts:
				
				for(int i=1; i <= Breast.MAXIMUM_BREAST_ROWS; i++) {
					MainController.setBreastCountListener(i);
				}
				
				for(int i=1; i <= Breast.MAXIMUM_NIPPLES_PER_BREAST; i++) {
					MainController.setNippleCountListener(i);
				}
				
				// Nipple capacity:
				for(Capacity capacity: Capacity.values()) {
					id = "NIPPLE_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple elastcity:
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "NIPPLE_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNippleElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Nipple plasticity:
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "NIPPLE_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setNipplePlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(int i : CharacterModificationUtils.demonLactationValues) {
					id = "LACTATION_"+i;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setBreastMilkStorage(i);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_NIPPLE_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasNippleOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeNippleOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addNippleOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				// Vagina:
				
				for(VaginaType vaginaType: VaginaType.values()) {
					id = "CHANGE_VAGINA_"+vaginaType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaType(vaginaType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_VAGINA_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasVaginaOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeVaginaOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addVaginaOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "VAGINA_URETHRA_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "VAGINA_URETHRA_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "VAGINA_URETHRA_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setVaginaUrethraPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_VAGINA_URETHRA_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasVaginaUrethraOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeVaginaUrethraOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addVaginaUrethraOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				
				
				
				
				// Penis:
				
				for(PenisType penisType: PenisType.values()) {
					id = "CHANGE_PENIS_"+penisType;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisType(penisType);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				id = "PENIS_SIZE_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "PENIS_SIZE_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "PENIS_SIZE_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "PENIS_SIZE_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisSize(-5);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				for(TesticleSize size : TesticleSize.values()) {
					id = "TESTICLE_SIZE_"+size;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setTesticleSize(size.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}

				for(int i=Testicle.MIN_TESTICLE_COUNT; i<=Testicle.MAX_TESTICLE_COUNT; i+=2) {
					MainController.setTesticleCountListener(i);
				}
				
				id = "TESTICLES_INTERNAL_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setInternalTesticles(true);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "TESTICLES_INTERNAL_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().setInternalTesticles(false);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				for(Capacity capacity: Capacity.values()) {
					id = "URETHRA_CAPACITY_"+capacity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setPenisCapacity(capacity.getMedianValue(), true);
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				id = "CUM_PRODUCTION_INCREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_INCREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(25);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_INCREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(500);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-1);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_LARGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-25);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = "CUM_PRODUCTION_DECREASE_HUGE";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						BodyChanging.getTarget().incrementPenisCumStorage(-500);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				
				
				for(OrificeElasticity elasticity: OrificeElasticity.values()) {
					id = "URETHRA_ELASTICITY_"+elasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraElasticity(elasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificePlasticity plasticity: OrificePlasticity.values()) {
					id = "URETHRA_PLASTICITY_"+plasticity;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							BodyChanging.getTarget().setUrethraPlasticity(plasticity.getValue());
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(PenetrationModifier orificeMod : PenetrationModifier.values()) {
					id = "CHANGE_PENIS_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasPenisModifier(orificeMod)) {
								BodyChanging.getTarget().removePenisModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addPenisModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
				for(OrificeModifier orificeMod : OrificeModifier.values()) {
					id = "CHANGE_URETHRA_MOD_"+orificeMod;
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(BodyChanging.getTarget().hasUrethraOrificeModifier(orificeMod)) {
								BodyChanging.getTarget().removeUrethraOrificeModifier(orificeMod);
							} else {
								BodyChanging.getTarget().addUrethraOrificeModifier(orificeMod);
							}
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
				
			}
			
			// -------------------- Cosmetics -------------------- //
			
			
			boolean noCost = !Main.game.isInNewWorld() || Main.game.getCurrentDialogueNode().getDialogueNodeType()==DialogueNodeType.PHONE;
			
			for(BodyCoveringType bct : BodyCoveringType.values()) {
				
				id = "APPLY_COVERING_"+bct;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getBodyCoveringTypeCost(bct) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(CharacterModificationUtils.getCoveringsToBeApplied().containsKey(bct)) {
										if(!noCost) {
											Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getBodyCoveringTypeCost(bct));
										}
										
										BodyChanging.getTarget().setSkinCovering(new Covering(CharacterModificationUtils.getCoveringsToBeApplied().get(bct)), false);
										
										if(noCost) {
											if(bct == BodyCoveringType.HUMAN) {
												BodyChanging.getTarget().getBody().updateCoverings(false, false, false, true);
											}
										}
									}
								}
							});
						}
					}, false);
				}
				
				
				id = bct+"_PRIMARY_GLOW_OFF";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(false);
							}
						});
					}, false);
				}
				
				id = bct+"_PRIMARY_GLOW_ON";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing(true);
								
							}
						});
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_OFF";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(false);
							}
						});
					}, false);
				}
				
				id = bct+"_SECONDARY_GLOW_ON";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
								CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(true);
							}
						});
					}, false);
				}
				
				for(CoveringPattern pattern : CoveringPattern.values()) {
					id = bct+"_PATTERN_"+pattern;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPattern(pattern);
								}
							});
						}, false);
					}
				}
				
				for(CoveringModifier modifier : CoveringModifier.values()) {
					id = bct+"_MODIFIER_"+modifier;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setModifier(modifier);
								}
							});
						}, false);
					}
				}

				for(Colour colour : bct.getAllPrimaryColours()) {
					id = bct+"_PRIMARY_"+colour;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryColour(colour);
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setPrimaryGlowing((colour != Colour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isPrimaryGlowing()));
								}
							});
						}, false);
					}
				}
				for(Colour colour : bct.getAllSecondaryColours()) {
					id = bct+"_SECONDARY_"+colour;
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									CharacterModificationUtils.getCoveringsToBeApplied().putIfAbsent(bct, new Covering(BodyChanging.getTarget().getCovering(bct)));
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryColour(colour);
									CharacterModificationUtils.getCoveringsToBeApplied().get(bct).setSecondaryGlowing(colour != Colour.COVERING_NONE && BodyChanging.getTarget().getCovering(bct).isSecondaryGlowing());
								}
							});
						}, false);
					}
				}
			}
			
			for(HairLength hairLength : HairLength.values()) {
				id = "HAIR_LENGTH_"+hairLength;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_LENGTH_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_LENGTH_COST);
									}
									BodyChanging.getTarget().setHairLength(hairLength.getMedianValue());
								}
							});
						}
					}, false);
				}
			}
			
			for(HairStyle hairStyle: HairStyle.values()) {
				id = "HAIR_STYLE_"+hairStyle;
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_HAIR_STYLE_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_HAIR_STYLE_COST);
									}
									BodyChanging.getTarget().setHairStyle(hairStyle);
								}
							});
						}
					}, false);
				}
			}
			
			for(PiercingType piercingType : PiercingType.values()) {
				id = piercingType+"_PIERCE_REMOVE";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									}
									switch(piercingType) {
										case EAR:
											BodyChanging.getTarget().setPiercedEar(false);
											break;
										case LIP:
											BodyChanging.getTarget().setPiercedLip(false);
											break;
										case NAVEL:
											BodyChanging.getTarget().setPiercedNavel(false);
											break;
										case NIPPLE:
											BodyChanging.getTarget().setPiercedNipples(false);
											break;
										case NOSE:
											BodyChanging.getTarget().setPiercedNose(false);
											break;
										case PENIS:
											BodyChanging.getTarget().setPiercedPenis(false);
											break;
										case TONGUE:
											BodyChanging.getTarget().setPiercedTongue(false);
											break;
										case VAGINA:
											BodyChanging.getTarget().setPiercedVagina(false);
											break;
									}
								}
							});
						}
					}, false);
				}
				
				id = piercingType+"_PIERCE";
				
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.getPiercingCost(piercingType) || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.getPiercingCost(piercingType));
									}
									switch(piercingType) {
										case EAR:
											BodyChanging.getTarget().setPiercedEar(true);
											break;
										case LIP:
											BodyChanging.getTarget().setPiercedLip(true);
											break;
										case NAVEL:
											BodyChanging.getTarget().setPiercedNavel(true);
											break;
										case NIPPLE:
											BodyChanging.getTarget().setPiercedNipples(true);
											break;
										case NOSE:
											BodyChanging.getTarget().setPiercedNose(true);
											break;
										case PENIS:
											BodyChanging.getTarget().setPiercedPenis(true);
											break;
										case TONGUE:
											BodyChanging.getTarget().setPiercedTongue(true);
											break;
										case VAGINA:
											BodyChanging.getTarget().setPiercedVagina(true);
											break;
									}
								}
							});
						}
					}, false);
				}
			}
			
			if (((EventTarget) MainController.document.getElementById("BLEACHING_OFF")) != null) {
				
				((EventTarget) MainController.document.getElementById("BLEACHING_OFF")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST || noCost) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								if(!noCost) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								}
								BodyChanging.getTarget().setAssBleached(false);
							}
						});
					}
				}, false);
			}
			
			if (((EventTarget) MainController.document.getElementById("BLEACHING_ON")) != null) {
				
				((EventTarget) MainController.document.getElementById("BLEACHING_ON")).addEventListener("click", e -> {
					if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_ANAL_BLEACHING_COST || noCost) {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								if(!noCost) {
									Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_ANAL_BLEACHING_COST);
								}
								BodyChanging.getTarget().setAssBleached(true);
							}
						});
					}
				}, false);
			}
			
			for(BodyHair bodyHair: BodyHair.values()) {
				
				id = "ASS_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setAssHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "UNDERARM_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setUnderarmHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "PUBIC_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setPubicHair(bodyHair);
								}
							});
						}
					}, false);
				}
				
				id = "FACIAL_HAIR_"+bodyHair;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						if(Main.game.getPlayer().getMoney() >= SuccubisSecrets.BASE_BODY_HAIR_COST || noCost) {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									if(!noCost) {
										Main.game.getPlayer().incrementMoney(-SuccubisSecrets.BASE_BODY_HAIR_COST);
									}
									BodyChanging.getTarget().setFacialHair(bodyHair);
								}
							});
						}
					}, false);
				}
			}
			
			if(Main.game.getCurrentDialogueNode()==SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS
					|| Main.game.getCurrentDialogueNode()==CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS) {
				for(InventorySlot invSlot : InventorySlot.getClothingSlots()) {
					id = "TATTOO_INFO_"+invSlot.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						
						if(BodyChanging.getTarget().getTattooInSlot(invSlot)!=null) {
							InventoryTooltipEventListener el = new InventoryTooltipEventListener().setTattoo(invSlot, BodyChanging.getTarget().getTattooInSlot(invSlot), BodyChanging.getTarget(), BodyChanging.getTarget());
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						} else {
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(invSlot.getTattooSlotName()), "");
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
					
					id = "TATTOO_ADD_REMOVE_"+invSlot.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(BodyChanging.getTarget().getTattooInSlot(invSlot)==null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								Main.game.setContent(new Response("", "",
										Main.game.isInNewWorld()
											?SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD
											:CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD){
									@Override
									public void effects() {
										SuccubisSecrets.invSlotTattooToRemove = null;
										CharacterModificationUtils.resetTattooVariables(invSlot);
									}
								});
							}, false);
						
						} else {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								if(Main.game.getPlayer().getMoney()>=100 || !Main.game.isInNewWorld()) {
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
										@Override
										public void effects() {
											if(SuccubisSecrets.invSlotTattooToRemove == invSlot || !Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)) {
												SuccubisSecrets.invSlotTattooToRemove = null;
												if(Main.game.isInNewWorld()) {
													Main.game.getTextStartStringBuilder().append(Main.game.getPlayer().incrementMoney(-100)); //TODO Kate description
												}
												BodyChanging.getTarget().removeTattoo(invSlot);
											} else {
												SuccubisSecrets.invSlotTattooToRemove = invSlot;
											}
										}
									});
								}
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation("Remove tattoo",
									Main.game.isInNewWorld()
									?(Main.game.getPlayer().getMoney()>=100
										?"It will cost "+UtilText.formatAsMoney(100, "span")+" to remove this tattoo!"
												+ (!Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?" (<i>You will need to click twice to remove it.</i>)":"")
										:"You don't have the required "+UtilText.formatAsMoney(100, "span")+" to remove this tattoo!")
									:"Remove this tattoo. "+(!Main.getProperties().hasValue(PropertyValue.tattooRemovalConfirmations)?" (<i>You will need to click twice to remove it.</i>)":""));
							MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
						}
					}
				}
			}

			if(Main.game.getCurrentDialogueNode()==SuccubisSecrets.SHOP_BEAUTY_SALON_TATTOOS_ADD
					|| Main.game.getCurrentDialogueNode()==CharacterCreation.CHOOSE_ADVANCED_APPEARANCE_TATTOOS_ADD) {
				id = "NEW_TATTOO_INFO";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					
					InventoryTooltipEventListener el = new InventoryTooltipEventListener().setTattoo(CharacterModificationUtils.tattooInventorySlot, CharacterModificationUtils.tattoo, BodyChanging.getTarget(), BodyChanging.getTarget());
					MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
				}
				
				for(AbstractTattooType type : TattooType.getAllTattooTypes()) {
					id = "TATTOO_TYPE_"+type.getId();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						if(type.getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot)) {
							if(!CharacterModificationUtils.tattoo.getType().equals(type)) {
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
									Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
										@Override
										public void effects() {
											Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
											CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
											CharacterModificationUtils.tattoo.setType(type);
											CharacterModificationUtils.resetTattooColours();
										}
									});
								}, false);
							}
						}
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(type.getName()), type.getDescription()
								+(type.getSlotAvailability().contains(CharacterModificationUtils.tattooInventorySlot)
										?""
										:"<br/>[style.italicsBad(This tattoo type can't be applied to '"+CharacterModificationUtils.tattooInventorySlot.getTattooSlotName()+"'!)]<br/>"
												+ "Available slots: "+Util.tattooInventorySlotsToStringList(type.getSlotAvailability())));
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}
				
				for(Colour c : CharacterModificationUtils.tattoo.getType().getAvailablePrimaryColours()) {
					id = "TATTOO_COLOUR_PRIMARY_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setPrimaryColour(c);
								}
							});
						}, false);
					}
				}
				
				for(Colour c : CharacterModificationUtils.tattoo.getType().getAvailableSecondaryColours()) {
					id = "TATTOO_COLOUR_SECONDARY_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setSecondaryColour(c);
								}
							});
						}, false);
					}
				}
				
				for(Colour c : CharacterModificationUtils.tattoo.getType().getAvailableTertiaryColours()) {
					id = "TATTOO_COLOUR_TERTIARY_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.setTertiaryColour(c);
								}
							});
						}, false);
					}
				}
				
				id = "TATTOO_GLOW";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
								CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								CharacterModificationUtils.tattoo.setGlowing(!CharacterModificationUtils.tattoo.isGlowing());
							}
						});
					}, false);
				}
				
				// Writing:

				for(TattooWritingStyle style : TattooWritingStyle.values()) {
					id = "TATTOO_WRITING_STYLE_"+style.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									if(!CharacterModificationUtils.tattoo.getWriting().getStyles().contains(style)) {
										CharacterModificationUtils.tattoo.getWriting().addStyle(style);
									} else {
										CharacterModificationUtils.tattoo.getWriting().removeStyle(style);
									}
								}
							});
						}, false);
					}
				}

				for(Colour c : TattooWriting.getAvailableColours()) {
					id = "TATTOO_WRITING_COLOUR_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getWriting().setColour(c);
								}
							});
						}, false);
					}
				}
				
				id = "TATTOO_WRITING_GLOW";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
								CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								CharacterModificationUtils.tattoo.getWriting().setGlow(!CharacterModificationUtils.tattoo.getWriting().isGlow());
							}
						});
					}, false);
				}
				
				// Counter:

				for(TattooCounterType counterType : TattooCounterType.values()) {
					id = "TATTOO_COUNTER_TYPE_"+counterType.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getCounter().setType(counterType);
								}
							});
						}, false);

						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						TooltipInformationEventListener el =  new TooltipInformationEventListener().setInformation(Util.capitaliseSentence(counterType.getName()), counterType.getDescription());
						MainController.addEventListener(MainController.document, id, "mouseenter", el, false);
					}
				}

				for(TattooCountType countType : TattooCountType.values()) {
					id = "TATTOO_COUNT_TYPE_"+countType.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getCounter().setCountType(countType);
								}
							});
						}, false);
					}
				}

				for(Colour c : TattooCounter.getAvailableColours()) {
					id = "TATTOO_COUNTER_COLOUR_"+c.toString();
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
								@Override
								public void effects() {
									Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
									CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
									CharacterModificationUtils.tattoo.getCounter().setColour(c);
								}
							});
						}, false);
					}
				}
				
				id = "TATTOO_COUNTER_GLOW";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()){
							@Override
							public void effects() {
								Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('tattoo_name').value;");
								CharacterModificationUtils.tattoo.getWriting().setText(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent());
								CharacterModificationUtils.tattoo.getCounter().setGlow(!CharacterModificationUtils.tattoo.getCounter().isGlow());
							}
						});
					}, false);
				}
				
			}
			
			
			
			// -------------------- Phone listeners -------------------- // TODO track listeners
			
			// Phone item viewer:
			if(Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_CLOTHING
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_CLOTHING_CHARACTER_CREATION
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_CLOTHING
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_CLOTHING_CHARACTER_CREATION) {
//				for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				AbstractClothingType clothing = InventoryDialogue.getClothing().getClothingType();
				for (Colour c : clothing.getAllAvailablePrimaryColours()) {
					id = "PRIMARY_"+clothing.hashCode() + "_" + c.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewPrimary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingPrimary(InventoryDialogue.getClothing(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				if(!clothing.getAllAvailableSecondaryColours().isEmpty()) {
					for (Colour c : clothing.getAllAvailableSecondaryColours()) {
						id = "SECONDARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewSecondary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingSecondary(InventoryDialogue.getClothing(), c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}
				if(!clothing.getAllAvailableTertiaryColours().isEmpty()) {
					for (Colour c : clothing.getAllAvailableTertiaryColours()) {
						id = "TERTIARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewTertiary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
							
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingTertiary(InventoryDialogue.getClothing(), c);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}
					}
				}
				for(Pattern pattern : Pattern.getAllPatterns().values()) {
					id = "ITEM_PATTERN_"+pattern.getName();
					
					if (((EventTarget) MainController.document.getElementById(id)) != null) {
						
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							if(InventoryDialogue.dyePreviewPattern != pattern.getName()){
								InventoryDialogue.dyePreviewPattern = pattern.getName();
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}
						}, false);
						
						// For some reason tooltips here cause massive lag.
						/*if(InventoryDialogue.dyePreviewPattern != pattern.getName())
						{
							MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
							MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
							InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeClothingPattern(InventoryDialogue.getClothing(), pattern);
							MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
						}*/
					}
				}
				if(Pattern.getPattern(InventoryDialogue.dyePreviewPattern)!=null && Pattern.getPattern(InventoryDialogue.dyePreviewPattern).isPrimaryRecolourAvailable()) {
					for (Colour c : ColourListPresets.ALL.getPresetColourList()) {
						id = "PATTERN_PRIMARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatternPrimary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				if(Pattern.getPattern(InventoryDialogue.dyePreviewPattern)!=null && Pattern.getPattern(InventoryDialogue.dyePreviewPattern).isSecondaryRecolourAvailable()) {
					for (Colour c : ColourListPresets.ALL.getPresetColourList()) {
						id = "PATTERN_SECONDARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatternSecondary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
				if(Pattern.getPattern(InventoryDialogue.dyePreviewPattern)!=null && Pattern.getPattern(InventoryDialogue.dyePreviewPattern).isTertiaryRecolourAvailable()) {
					for (Colour c : ColourListPresets.ALL.getPresetColourList()) {
						id = "PATTERN_TERTIARY_"+clothing.hashCode() + "_" + c.toString();
						if ((EventTarget) MainController.document.getElementById(id) != null) {
							((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
								InventoryDialogue.dyePreviewPatternTertiary = c;
								Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
							}, false);
						}
					}
				}
			}
			
			if(Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_WEAPON
					|| Main.game.getCurrentDialogueNode()==InventoryDialogue.DYE_EQUIPPED_WEAPON) {
				AbstractWeaponType weapon = InventoryDialogue.getWeapon().getWeaponType();
				for (Colour c : weapon.getAllAvailablePrimaryColours()) {
					id = "PRIMARY_"+weapon.hashCode() + "_" + c.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewPrimary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeWeaponPrimary(InventoryDialogue.getWeapon(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
				for (Colour c : weapon.getAllAvailableSecondaryColours()) {
					id = "SECONDARY_"+weapon.hashCode() + "_" + c.toString();
					if ((EventTarget) MainController.document.getElementById(id) != null) {
						((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
							InventoryDialogue.dyePreviewSecondary = c;
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
						
						MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setDyeWeaponSecondary(InventoryDialogue.getWeapon(), c);
						MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
					}
				}
			}
			
			for (AbstractClothingType clothing : ClothingType.getAllClothing()) {
				for (Colour colour : clothing.getAllAvailablePrimaryColours()) {
					if ((EventTarget) MainController.document.getElementById(clothing.hashCode() + "_" + colour.toString()) != null) {
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericClothing(clothing, colour);
						MainController.addEventListener(MainController.document, clothing.hashCode() + "_" + colour.toString(), "mouseenter", el2, false);
					}
				}
			}
			
			for (AbstractItemType item : ItemType.allItems) {
				id = ItemType.itemToIdMap.get(item);
				if ((EventTarget) MainController.document.getElementById(id) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericItem(item);
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			
			for (AbstractWeaponType weapon : WeaponType.allweapons) {
				for (DamageType dt : weapon.getAvailableDamageTypes()) {
					if ((EventTarget) MainController.document.getElementById(weapon.hashCode() + "_" + dt.toString()) != null) {
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mousemove", MainController.moveTooltipListener, false);
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mouseleave", MainController.hideTooltipListener, false);
						InventoryTooltipEventListener el2 = new InventoryTooltipEventListener().setGenericWeapon(weapon, dt);
						MainController.addEventListener(MainController.document, weapon.hashCode() + "_" + dt.toString(), "mouseenter", el2, false);
					}
				}
			}

			for(Perk perk : Perk.values()) {
				id = "TRAIT_"+perk;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(PerkManager.MANAGER.getPerkRow(perk), perk, Main.game.getPlayer()), false);
					
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
						Main.game.getPlayer().removeTrait(perk);
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			// Level up dialogue:
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.CHARACTER_LEVEL_UP) {
				for(int i = 0; i<PerkManager.ROWS; i++) {
					for(Entry<PerkCategory, List<TreeEntry<PerkCategory, Perk>>> entry : PerkManager.MANAGER.getPerkTree().get(i).entrySet()) {
						for(TreeEntry<PerkCategory, Perk> e : entry.getValue()) {
							id = i+"_"+e.getEntry();
	
							if (((EventTarget) MainController.document.getElementById(id)) != null) {
								MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
								MainController.addEventListener(MainController.document, id, "mouseenter", new TooltipInformationEventListener().setLevelUpPerk(i, e.getEntry(), Main.game.getPlayer()), false);
								((EventTarget) MainController.document.getElementById(id)).addEventListener("click", event -> {
									if(e.getEntry().isEquippableTrait() && PerkManager.MANAGER.isPerkOwned(e)) {
										if(!Main.game.getPlayer().hasTraitActivated(e.getEntry())) {
											Main.game.getPlayer().addTrait(e.getEntry());
										} else {
											Main.game.getPlayer().removeTrait(e.getEntry());
										}
										Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
										
									} else if(Main.game.getPlayer().getPerkPoints()>=1 && PerkManager.MANAGER.isPerkAvailable(e)) {
										if(Main.game.getPlayer().addPerk(e.getRow(), e.getEntry())) {
											Main.game.getPlayer().incrementPerkPoints(-1);
											if(e.getEntry().isEquippableTrait() && Main.game.getPlayer().getTraits().size()<GameCharacter.MAX_TRAITS) {
												Main.game.getPlayer().addTrait(e.getEntry());
											}
											Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
										}
									}
								}, false);
							}
						}
					}
				}
			}
			if (Main.game.getCurrentDialogueNode() == PhoneDialogue.MENU) {
				Cell[][] grid = Main.game.getWorlds().get(Main.game.getPlayer().getWorldLocation()).getGrid();

				for(int i=grid[0].length-1; i>=0; i--) {
					for(int j=0; j<grid.length; j++) {
						Cell c = grid[j][i];
						boolean discovered = c.isDiscovered() || Main.game.isDebugMode();
						if(!discovered) {
							continue;
						}
						if(c.getPlace().getPlaceType() == PlaceType.GENERIC_IMPASSABLE) {
							continue;
						}
						MainController.setMapLocationListeners(c, i, j);
					}
				}
			}
		}

		// Hotkey bindings:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.KEYBINDS) {
			for (KeyboardAction ka : KeyboardAction.values()) {
				if (((EventTarget) MainController.document.getElementById("primary_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("primary_" + ka)).addEventListener("click", e -> {
						MainController.actionToBind = ka;
						MainController.primaryBinding = true;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) MainController.document.getElementById("primaryClear_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("primaryClear_" + ka)).addEventListener("click", e -> {
						Main.getProperties().hotkeyMapPrimary.put(ka, null);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

				if (((EventTarget) MainController.document.getElementById("secondary_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("secondary_" + ka)).addEventListener("click", e -> {
						MainController.actionToBind = ka;
						MainController.primaryBinding = false;
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				if (((EventTarget) MainController.document.getElementById("secondaryClear_" + ka)) != null)
					((EventTarget) MainController.document.getElementById("secondaryClear_" + ka)).addEventListener("click", e -> {
						Main.getProperties().hotkeyMapSecondary.put(ka, null);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);

			}
		}
		
		// Gender preferences:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.GENDER_PREFERENCE) {
			for (Gender g : Gender.values()) {
				for(GenderPreference preference : GenderPreference.values()) {
					if (((EventTarget) MainController.document.getElementById(preference+"_"+g)) != null) {
						((EventTarget) MainController.document.getElementById(preference+"_"+g)).addEventListener("click", e -> {
							Main.getProperties().genderPreferencesMap.put(g, preference.getValue());
							Main.saveProperties();
							Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
						}, false);
					}
				}
			}
		}
		
		// Content preferences:

		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.CONTENT_PREFERENCE || Main.game.getCurrentDialogueNode() == CharacterCreation.CONTENT_PREFERENCES) {
			id = "ARTWORK_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.artwork, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "ARTWORK_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.artwork, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}

			id = "THUMBNAIL_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.thumbnail, true);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "THUMBNAIL_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().setValue(PropertyValue.thumbnail, false);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}

			for(Artist artist : Artwork.allArtists) {
				id = "ARTIST_"+artist.getFolderName();
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.getProperties().preferredArtist = artist.getFolderName();
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			
			Map<String, PropertyValue> settingsMap = Util.newHashMapOfValues(
					new Value<>("ARTWORK", PropertyValue.artwork),
					new Value<>("NON_CON", PropertyValue.nonConContent),
					new Value<>("VOLUNTARY_NTR", PropertyValue.voluntaryNTR),
					new Value<>("INVOLUNTARY_NTR", PropertyValue.involuntaryNTR),
					new Value<>("INCEST", PropertyValue.incestContent),
					new Value<>("LACTATION", PropertyValue.lactationContent),
					new Value<>("CUM_REGENERATION", PropertyValue.cumRegenerationContent),
					new Value<>("URETHRAL", PropertyValue.urethralContent),
					new Value<>("NIPPLE_PEN", PropertyValue.nipplePenContent),
					new Value<>("HAIR_FACIAL", PropertyValue.facialHairContent),
					new Value<>("ANAL", PropertyValue.analContent),
					new Value<>("FUTA_BALLS", PropertyValue.futanariTesticles),
					new Value<>("AGE", PropertyValue.ageContent),
					new Value<>("HAIR_PUBIC", PropertyValue.pubicHairContent),
					new Value<>("HAIR_BODY", PropertyValue.bodyHairContent),
					new Value<>("HAIR_ASS", PropertyValue.assHairContent),
					new Value<>("FEMININE_BEARD", PropertyValue.feminineBeardsContent),
					new Value<>("INFLATION_CONTENT", PropertyValue.inflationContent));
			
			for(Entry<String, PropertyValue> entry : settingsMap.entrySet()) {
				id = entry.getKey()+"_ON";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.getProperties().setValue(entry.getValue(), true);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
				id = entry.getKey()+"_OFF";
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						Main.getProperties().setValue(entry.getValue(), false);
						Main.saveProperties();
						Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
					}, false);
				}
			}
			

			id = "PREGNANCY_BREAST_GROWTH_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowth = Math.min(10, Main.getProperties().pregnancyBreastGrowth+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowth = Math.max(0, Main.getProperties().pregnancyBreastGrowth-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowthLimit = Math.min(100, Main.getProperties().pregnancyBreastGrowthLimit+1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_BREAST_GROWTH_LIMIT_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyBreastGrowthLimit = Math.max(0, Main.getProperties().pregnancyBreastGrowthLimit-1);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			
			id = "PREGNANCY_LACTATION_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationIncrease = Math.min(1000, Main.getProperties().pregnancyLactationIncrease+50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationIncrease = Math.max(0, Main.getProperties().pregnancyLactationIncrease-50);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			
			id = "PREGNANCY_LACTATION_LIMIT_ON";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.min(Lactation.SEVEN_MONSTROUS_AMOUNT_POURING.getMaximumValue(), Main.getProperties().pregnancyLactationLimit+250);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
			id = "PREGNANCY_LACTATION_LIMIT_OFF";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
					Main.getProperties().pregnancyLactationLimit = Math.max(0, Main.getProperties().pregnancyLactationLimit-250);
					Main.saveProperties();
					Main.game.setContent(new Response("", "", Main.game.getCurrentDialogueNode()));
				}, false);
			}
		}
		
		// Save/load:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.SAVE_LOAD) {
			for (File f : SaveManager.getSavedGames()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				id = "overwrite_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.overwriteConfirmationName.equals(f.getName())) {
							OptionsDialogue.overwriteConfirmationName = "";
							SaveManager.saveGame(fileIdentifier, true);
						} else {
							OptionsDialogue.overwriteConfirmationName = f.getName();
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Overwrite", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "load_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.loadConfirmationName.equals(f.getName())) {
							OptionsDialogue.loadConfirmationName = "";
							SaveManager.loadGame(fileIdentifier);
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = f.getName();
							OptionsDialogue.deleteConfirmationName = "";
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Load", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
				id = "delete_saved_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							SaveManager.deleteGame(fileIdentifier);
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.SAVE_LOAD));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			id = "new_saved";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {//TODO
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					SaveManager.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);

				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save", "");
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
			id = "new_saved_disabled";
			if (((EventTarget) MainController.document.getElementById(id)) != null) {
				MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
				MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
				TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Save (Disabled)",
						(!Main.game.isStarted()
								?"You need to have started a game before you can save!"
								:"You cannot save the game unless you are in a tile's default scene!"));
				MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
			}
		}
		
		// Import:
		if (Main.game.getCurrentDialogueNode() == OptionsDialogue.IMPORT_EXPORT) {
			for (File f : ImportExportManager.getCharactersForImport()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				id = "delete_saved_character_" + fileIdentifier;
				if (((EventTarget) MainController.document.getElementById(id)) != null) {
					((EventTarget) MainController.document.getElementById(id)).addEventListener("click", e -> {
						
						if(!Main.getProperties().hasValue(PropertyValue.overwriteWarning) || OptionsDialogue.deleteConfirmationName.equals(f.getName())) {
							OptionsDialogue.deleteConfirmationName = "";
							ImportExportManager.deleteExportedCharacter(fileIdentifier);
						} else {
							OptionsDialogue.overwriteConfirmationName = "";
							OptionsDialogue.loadConfirmationName = "";
							OptionsDialogue.deleteConfirmationName = f.getName();
							Main.game.setContent(new Response("Save/Load", "Open the save/load game window.", OptionsDialogue.IMPORT_EXPORT));
						}
						
					}, false);

					MainController.addEventListener(MainController.document, id, "mousemove", MainController.moveTooltipListener, false);
					MainController.addEventListener(MainController.document, id, "mouseleave", MainController.hideTooltipListener, false);
					TooltipInformationEventListener el2 = new TooltipInformationEventListener().setInformation("Delete", "");
					MainController.addEventListener(MainController.document, id, "mouseenter", el2, false);
				}
			}
			if (((EventTarget) MainController.document.getElementById("new_saved")) != null) {
				((EventTarget) MainController.document.getElementById("new_saved")).addEventListener("click", e -> {
					Main.mainController.getWebEngine().executeScript("document.getElementById('hiddenPField').innerHTML=document.getElementById('new_save_name').value;");
					SaveManager.saveGame(Main.mainController.getWebEngine().getDocument().getElementById("hiddenPField").getTextContent(), false);
					
				}, false);
			}
		}
		
		if (Main.game.getCurrentDialogueNode() == CharacterCreation.IMPORT_CHOOSE) {
			for (File f : ImportExportManager.getCharactersForImport()) {
				String fileIdentifier = f.getName().substring(0, f.getName().lastIndexOf('.'));
				
				if (((EventTarget) MainController.document.getElementById("character_import_" + fileIdentifier )) != null) {
					((EventTarget) MainController.document.getElementById("character_import_" + fileIdentifier )).addEventListener("click", e -> {
						ImportExportManager.importCharacter(f);
						
					}, false);
				}
			}
		}

		MainController.setResponseEventListeners();
	}
}
