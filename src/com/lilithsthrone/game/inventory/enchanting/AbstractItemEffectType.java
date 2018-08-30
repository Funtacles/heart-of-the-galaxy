package com.lilithsthrone.game.inventory.enchanting;

import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.dialogue.eventLog.EventLogEntryBookAddedToLibrary;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;

/**
 * @since 0.2.4
 * @version 0.2.4
 * @author Innoxia
 */
public abstract class AbstractItemEffectType {
	
	private Colour colour;
	
	public AbstractItemEffectType(Colour colour) {
		this.colour = colour;
	}
	
	public Colour getColour() {
		return colour;
	}
	
	public String getPotionDescriptor() {
		return "";
	}

	protected static String getBookEffect(Race race, AbstractItemType book) {
		Main.getProperties().addRaceDiscovered(race);
		if(Main.getProperties().addAdvancedRaceKnowledge(race) && book!=null) {
			Main.game.addEvent(new EventLogEntryBookAddedToLibrary(book), true);
		}
		
		if(Main.game.getPlayer().addRaceDiscoveredFromBook(race)) {
			return race.getBasicDescription()
					+race.getAdvancedDescription()
					+Main.game.getPlayer().incrementAttribute(race.getDamageMultiplier(), 5f)
					+Main.game.getPlayer().incrementAttribute(race.getResistanceMultiplier(), 5f);
			
		} else {
			return race.getBasicDescription()
					+race.getAdvancedDescription()
					+"<p style='text-align:center; color:"+Colour.TEXT_GREY.toWebHexString()+";'>"
						+ "Nothing further can be gained from re-reading this book..."
					+ "</p>";
		}
		
	}
	
}
