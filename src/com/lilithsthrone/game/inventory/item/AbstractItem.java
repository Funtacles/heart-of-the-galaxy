package com.lilithsthrone.game.inventory.item;

import java.io.Serializable;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.1.97
 * @author Innoxia
 */
public abstract class AbstractItem extends AbstractCoreItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;
	
	protected AbstractItemType itemType;
	protected List<ItemEffect> itemEffects;

	public AbstractItem(AbstractItemType itemType) {
		super(itemType.getName(false), itemType.getNamePlural(false), itemType.getSVGString(), itemType.getColourPrimary(), null, itemType.getItemTags());

		this.itemType = itemType;
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)) {
			return (o instanceof AbstractItem)
					&& ((AbstractItem)o).getItemType().equals(itemType);
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + itemType.hashCode();
		result = 31 * result + itemEffects.hashCode();
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("item");
		parentElement.appendChild(element);

		CharacterUtils.addAttribute(doc, element, "id", this.getItemType().getId());
		CharacterUtils.addAttribute(doc, element, "name", this.getName());
		CharacterUtils.addAttribute(doc, element, "colour", this.getColour().toString());
		
		Element innerElement = doc.createElement("itemEffects");
		element.appendChild(innerElement);
		
		return element;
	}
	
	public static AbstractItem loadFromXML(Element parentElement, Document doc) {
		try {
			AbstractItem item = AbstractItemType.generateItem(ItemType.idToItemMap.get(parentElement.getAttribute("id")));
			
			if(!parentElement.getAttribute("name").isEmpty()) {
				item.setName(parentElement.getAttribute("name"));
			}
			
			return item;
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractItem was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}
	}

	public AbstractItemType getItemType() {
		return itemType;
	}

	public String applyEffect(GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	
	// Getters & setters:
	
	public String getName(boolean withDeterminer) {
		return (withDeterminer
				? (!itemType.getDeterminer().equalsIgnoreCase("a") && !itemType.getDeterminer().equalsIgnoreCase("an")
					? itemType.getDeterminer() + " "
					: (Util.isVowel(name.charAt(0)) ? "an " : "a "))
				: " ")
				+ " " + name;
	}
	
	public String getDisplayName() {
		return Util.capitaliseSentence((itemType.getDeterminer()==""?"":itemType.getDeterminer()+" ") + name);
	}

	@Override
	public String getDescription() {
		return itemType.getDescription();
	}
	
	@Override
	public int getValue() {
		return itemType.getValue();
	}
	
	public String getExtraDescription(GameCharacter user, GameCharacter target) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>"
					+ "<b>Effects:</b><br/>");
		
		sb.append("</p>"
				+ "<p>"
					+ (this.getItemType().isPlural()?"They have":"It has")+" a value of " + UtilText.formatAsMoney(getValue()) + "."
				+ "</p>");
		
		return sb.toString();
	}

	public String getPathName() {
		return itemType.getPathName();
	}

	public boolean isConsumedOnUse() {
		return itemType.isConsumedOnUse();
	}
	
	public String getUseDescription(GameCharacter user, GameCharacter target) {
		return itemType.getUseDescription(user, target);
	}

	public boolean isAbleToBeUsedFromInventory() {
		return itemType.isAbleToBeUsedFromInventory();
	}
	
	public String getUnableToBeUsedFromInventoryDescription() {
		return itemType.getUnableToBeUsedFromInventoryDescription();
	}
	
	public boolean isAbleToBeUsed(GameCharacter target) {
		return itemType.isAbleToBeUsed(target);
	}
	
	public String getUnableToBeUsedDescription(GameCharacter target) {
		return itemType.getUnableToBeUsedDescription(target);
	}

	public boolean isAbleToBeUsedInCombat(){
		return itemType.isAbleToBeUsedInCombat();
	}

	public boolean isAbleToBeUsedInSex(){
		return itemType.isAbleToBeUsedInSex();
	}
	
	public boolean isGift() {
		return itemType.isGift();
	}
	
}
