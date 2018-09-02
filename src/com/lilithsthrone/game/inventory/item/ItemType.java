package com.lilithsthrone.game.inventory.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.Rarity;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.game.inventory.enchanting.ItemEffectType;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.84
 * @version 0.2.4
 * @author Innoxia
 */
public class ItemType {

	/*
	 * Common: Restore resources Rare: Apply status effect Epic: Transformation
	 * Legendary: Uniques
	 */

	private static String getGenericUseDescription(GameCharacter user, GameCharacter target, String playerUseSelf, String playerUsePartner, String partnerUseSelf, String partnerUsePlayer) {
		if (user!=null && user.isPlayer()) {
			if(target!=null) {
				if(target.isPlayer()) {
					return "<p>"+playerUseSelf+"</p>";
					
				} else {
					return UtilText.parse(target, "<p>"+playerUsePartner+"</p>");
				}
			} else {
				return "";
			}
			
		} else {
			if(target!=null) {
				if(target.isPlayer()) {
					return UtilText.parse(user, "<p>"+partnerUsePlayer+"</p>");
					
				} else {
					return UtilText.parse(user, "<p>"+partnerUseSelf+"</p>");
				}
			} else {
				return "";
			}
		}
	}
	
	public static AbstractItemType FETISH_REFINED = new AbstractItemType(750,
			"a vial of",
			false,
			"Fetish Endowment",
			"Fetish Endowments",
			"A vial of bubbling pink liquid, which was refined from a bottle of 'Mystery Kink'."
					+ " Its potent enchantment is far more refined than that of the liquid it was distilled from, and is able to add or remove specific fetishes.",
			"fetishDrinkRefined",
			Colour.FETISH,
			null,
			null,
			Rarity.LEGENDARY,
			null, null) {

		private static final long serialVersionUID = 1L;

		@Override
		public boolean isAbleToBeUsedInSex() {
			return true;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return true;
		}

		@Override
		public String getUseName() {
			return "drink";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You pull the stopper out from the top of the glass vial of 'Fetish Endowment', before bringing it to your lips and gulping down the sickly sweet liquid that's contained within.",
					"You pull the stopper out from the top of the glass vial of 'Fetish Endowment', before bringing it to [npc.namePos] lips and forcing [npc.herHim] to drink down the liquid within.",
					"[npc.Name] pulls out a glass vial of 'Fetish Endowment', and, after quickly pulling out the stopper, [npc.she] promptly downs the entire bottle.",
					"[npc.Name] pulls out a glass vial of 'Fetish Endowment', and, after quickly pulling out the stopper,"
							+ " [npc.she] brings it to your lips before tilting your head back and forcing you to quickly gulp down the sickly sweet liquid that's contained within.");
		}
	};
	
	// Non-TF:

	public static AbstractItemType DYE_BRUSH = new AbstractItemType(150,
			"a",
			false,
			"dye-brush",
			"dye-brushes",
			"A small, very ordinary-looking brush, of the sort used for fine detailing on canvas or models."
					+ " On closer inspection, you notice a very faint purple glow emanating from the brush's tip, revealing its true nature as an arcane-enchanted dye-brush.",
			"dyeBrush",
			Colour.CLOTHING_WHITE,
			null,
			null,
			Rarity.EPIC,
			Util.newArrayListOfValues(new ItemEffect(ItemEffectType.DYE_BRUSH)),
			Util.newArrayListOfValues(
					ItemTag.DOMINION_ALLEYWAY_SPAWN)) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getUseName() {
			return "use";
		}
		
		@Override
		public boolean isAbleToBeUsedInSex() {
			return false;
		}

		@Override
		public boolean isAbleToBeUsedInCombat() {
			return false;
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return "<p>"
						+ "As you take hold of the Dye-brush, you see the purple glow around the tip growing in strength."
						+ " The closer you move it to a piece of clothing, the brighter the glow becomes, until suddenly, images of different colours start flashing through your mind."
						+ " As you touch the bristles to the piece of clothing, the Dye-brush instantly evaporates!"
						+ " You see that the arcane enchantment has dyed your piece of clothing the colour you wanted."
					+ "</p>";
		}

		@Override
		public boolean isAbleToBeUsedFromInventory() {
			return false;
		}
	};

	public static AbstractItemType ARTHURS_PACKAGE = new AbstractItemType(0,
			"",
			false,
			"Arthur's Package",
			"Arthur's Packages",
			"A package that you collected from Arcane Arts. You need to deliver this to Arthur.",
			"arthursPackage",
			Colour.ANDROGYNOUS,
			null,
			null,
			Rarity.LEGENDARY,
			null, null) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getUseName() {
			return "inspect";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.",
					"The package is quite small, measuring roughly 20cm along each edge. It's constructed of brown cardboard, and sealed with packaging tape.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
		
		@Override
		public boolean canBeSold() {
			return false;
		}

	};
	
	public static AbstractItemType GIFT_ROSE = new AbstractItemType(
			100,
			null,
			false,
			"Rose",
			"Roses",
			"A bouquet filled with roses of many colours, it smells pleasant even from a distance."
				+ " [Ashley.speech(Just in case you're clueless to the point that you don't even know the favourite colour of your intended recipient, every natural colour is included here.)]",
			//				+ " If their favourite happens to be blue, tough luck; maybe you should try getting acquainted with another species of flower instead of going with what's safe.)] ",
			"giftRose",
			Colour.BASE_CRIMSON,
			Colour.BASE_GREEN_DARK,
			null,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "A single, red rose."
					+ " You imagine that if it were any other colour, it would smell just as sweet.";
		}
		
		@Override
		public String getUseName() {
			return "smell";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You take a smell of the delicate perfume given off by the red Rose.",
					"You take a smell of the delicate perfume given off by the red Rose.",
					"You take a smell of the delicate perfume given off by the red Rose.",
					"You take a smell of the delicate perfume given off by the red Rose.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType GIFT_ROSE_BOUQUET = new AbstractItemType(
			500,
			null,
			false,
			"Rose Bouquet",
			"Rose Bouquets",
			"A bouquet filled with roses of many colours, it smells pleasant even from a distance."
				+ " [Ashley.speech(Just in case you're clueless to the point that you don't even know the favourite colour of your intended recipient, every natural colour is included here.)]",
			//				+ " If their favourite happens to be blue, tough luck; maybe you should try getting acquainted with another species of flower instead of going with what's safe.)] ",
			"giftRoseBouquet",
			Colour.BASE_RED,
			Colour.BASE_ORANGE,
			Colour.BASE_YELLOW,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "A bouquet filled with roses of many colours, it smells pleasant even from a distance.";
		}
		
		@Override
		public String getUseName() {
			return "smell";
		}
		
		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You take a smell of the delicate perfume given off by the Rose bouquet.",
					"You take a smell of the delicate perfume given off by the Rose bouquet.",
					"You take a smell of the delicate perfume given off by the Rose bouquet.",
					"You take a smell of the delicate perfume given off by the Rose bouquet.");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static AbstractItemType GIFT_TEDDY_BEAR = new AbstractItemType(
			600,
			null,
			false,
			"Teddy Bear",
			"Teddy Bears",
			"A cute brown teddy bear, with the words 'Hug me!' sewed onto a little heart that it's holding."
				+ " [Ashley.speech(Warning, this is an inanimate object; it does not actually yearn for your affection and cannot protect you from monsters hiding under the bed!)]",
			"giftTeddyBear",
			Colour.BASE_TAN,
			null,
			null,
			Rarity.UNCOMMON,
			null,
			Util.newArrayListOfValues(ItemTag.GIFT)) {

		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "A cute brown teddy bear, with the words 'Hug me!' sewed onto a little heart that it's holding.";
		}
		
		@Override
		public String getUseName() {
			return "hug";
		}

		@Override
		public String getUseDescription(GameCharacter user, GameCharacter target) {
			return getGenericUseDescription(user, target,
					"You hug the teddy bear. It's soft and fluffy...",
					"You get [npc.name] to hug the teddy bear. [npc.She] remarks on how soft and fluffy it is...",
					"[npc.Name] hugs the teddy bear. [npc.She] remarks on how soft and fluffy...",
					"[npc.Name] gets you to hug the teddy bear. It's soft and fluffy...");
		}
		
		@Override
		public boolean isConsumedOnUse() {
			return false;
		}
	};
	
	public static int getMooMilkerMaxMilk() {
		return 1000;
	}
	
	public static List<AbstractItemType> dominionAlleywayItems = new ArrayList<>();
	public static List<AbstractItemType> batCavernItems = new ArrayList<>();
	public static List<AbstractItemType> essences = new ArrayList<>();
	public static List<AbstractItemType> allItems = new ArrayList<>();
	
	public static Map<AbstractItemType, String> itemToIdMap = new HashMap<>();

	public static Map<String, AbstractItemType> idToItemMap = new HashMap<>();
	
	static{
		
		Field[] fields = ItemType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemType item;
				try {
					item = ((AbstractItemType) f.get(null));
					
					// I feel like this is stupid :thinking:
					itemToIdMap.put(item, f.getName());
					idToItemMap.put(f.getName(), item);
					
					allItems.add(item);
					
					if(item.getItemTags().contains(ItemTag.DOMINION_ALLEYWAY_SPAWN)) {
						dominionAlleywayItems.add(item);
					}
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
