package com.lilithsthrone.game.inventory.enchanting;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.8
 * @version 0.2.4
 * @author Innoxia
 */
public class ItemEffect implements Serializable, XMLSaving {
	private static final long serialVersionUID = 1L;
	
	private AbstractItemEffectType itemEffectType;
	private int limit;
	private ItemEffectTimer timer;
	
	public ItemEffect(AbstractItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
		this.limit = 0;
		this.timer = new ItemEffectTimer();
	}
	
	public ItemEffect(AbstractItemEffectType itemEffectType, int limit) {
		this.itemEffectType = itemEffectType;
		this.limit = limit;
		this.timer = new ItemEffectTimer();
	}
	
	@Override
	public boolean equals (Object o) {
		if(o instanceof ItemEffect){
			if((((ItemEffect)o).getItemEffectType()==null && itemEffectType==null
					||((ItemEffect)o).getItemEffectType()!=null && ((ItemEffect)o).getItemEffectType().equals(itemEffectType))
				&& ((ItemEffect)o).getLimit() == limit){
					return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		if(itemEffectType!=null) {
			result = 31 * result + itemEffectType.hashCode();
		}
		result = 31 * result + limit;
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element effect = doc.createElement("effect");
		parentElement.appendChild(effect);

		CharacterUtils.addAttribute(doc, effect, "itemEffectType", ItemEffectType.getIdFromItemEffectType(getItemEffectType()));
		CharacterUtils.addAttribute(doc, effect, "limit", String.valueOf(getLimit()));
		CharacterUtils.addAttribute(doc, effect, "timer", String.valueOf(getTimer().getTimePassed()));
		
		return effect;
	}
	
	public static ItemEffect loadFromXML(Element parentElement, Document doc) {
		String itemEffectType = parentElement.getAttribute("itemEffectType");
		switch(itemEffectType) {
			case "ATTRIBUTE_STRENGTH":
			case "ATTRIBUTE_FITNESS":
				itemEffectType = "ATTRIBUTE_PHYSIQUE";
				break;
			case "ATTRIBUTE_INTELLIGENCE":
				itemEffectType = "ATTRIBUTE_ARCANE";
				break;
		}
		switch(parentElement.getAttribute("primaryModifier")) {
			case "DAMAGE_ATTACK":
			case "RESISTANCE_ATTACK":
				return null;
		}
		
		ItemEffect ie;
		try { // Wrap this in a try, as the TFModifier.valueof might fail, due to removing Broodmother/Seeder fetish modifiers in 0.2.7.5.
			ie = new ItemEffect(
					ItemEffectType.getItemEffectTypeFromId(itemEffectType),
					Integer.valueOf(parentElement.getAttribute("limit")));
		} catch(Exception ex) {
			return null;
		}
		
		try {
			if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.5")) {
				int timer = Integer.valueOf(parentElement.getAttribute("timer"))/60;
				ie.getTimer().setTimePassed((timer*60) + (int)(Main.game.getMinutesPassed()%60));
			} else {
				ie.getTimer().setTimePassed(Integer.valueOf(parentElement.getAttribute("timer")));
			}
		} catch(Exception ex) {	
		}
		
		return ie;
	}
	
	public int getCost() {
		int cost = 1;
		if(getLimit() != -1) {
			cost+=1;
		}
		
		return cost;
	}
	
	public AbstractItemEffectType getItemEffectType() {
		return itemEffectType;
	}

	public void setItemEffectType(AbstractItemEffectType itemEffectType) {
		this.itemEffectType = itemEffectType;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ItemEffectTimer getTimer() {
		return timer;
	}

	public void setTimer(ItemEffectTimer timer) {
		this.timer = timer;
	}
	
}
