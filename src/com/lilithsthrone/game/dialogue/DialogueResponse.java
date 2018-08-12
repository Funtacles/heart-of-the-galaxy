package com.lilithsthrone.game.dialogue;

import com.lilithsthrone.game.character.effects.Perk;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.sexActions.SexActionType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;

/**
 * @since 0.1.69
 * @version 0.2.8
 * @author Innoxia
 */
public class DialogueResponse {
	
	protected String title, tooltipText;
	protected DialogueNode nextDialogue;
	
	private DialogueCondition condition;
	private int index;

	public DialogueResponse(String title,
			String tooltipText,
			DialogueNode nextDialogue,
			DialogueCondition condition,
			int index) {
		
		this.title = UtilText.parse(title);
		this.tooltipText = UtilText.parse(tooltipText);
		this.nextDialogue = nextDialogue;
		this.condition = condition;
		this.index = index;
	}

	public String getTitle() {
		return title;
	}

	public String getTooltipText() {
		return tooltipText;
	}

	public DialogueNode getNextDialogue() {
		return nextDialogue;
	}

	public int getIndex() {
		return this.index;
	}

	public boolean disabledOnNullDialogue(){
		return true;
	}
	
	public boolean isSexHighlight() {
		return false;
	}
	
	public boolean isSexPenetrationHighlight() {
		return false;
	}
	
	public boolean isSexPositioningHighlight() {
		return false;
	}
	
	public boolean isCombatHighlight() {
		return false;
	}
	
	public boolean isTradeHighlight() {
		return false;
	}
	
	public Colour getHighlightColour() {
		if(isSexHighlight()) {
			return Colour.GENERIC_SEX;
			
		} else if(isSexPenetrationHighlight()) {
			return Colour.GENERIC_SEX;
			
		} else if(isSexPositioningHighlight()) {
			return Colour.GENERIC_ARCANE;
			
		} else if(isCombatHighlight()) {
			return Colour.GENERIC_COMBAT;
			
		} else if(isTradeHighlight()) {
			return Colour.BASE_YELLOW_LIGHT;
			
		} else {
			return Colour.TEXT;
		}
	}
	
	public SexPace getSexPace() {
		return null;
	}
	
	public SexActionType getSexActionType() {
		return null;
	}
	
	public final void applyEffects() {
		effects();
	}
	
	public void effects() {
	}
	
	public boolean hasRequirements() {
		return this.condition.hasRequirements();
	}
	
	public boolean isAvailable(){
		return this.condition.isAvailable();
	}
	
	public boolean isAbleToBypass(){
		return this.condition.isAbleToBypass();
	}

	private StringBuilder SB;
	
	public String getTooltipBlockingList(){
		SB = new StringBuilder();
		
		for(Perk p : this.condition.getPerksRequired()){
			if(Main.game.getPlayer().hasTrait(p, true)) {
				SB.append("<br/>"
						+"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
						+ " (<span style='color:"+Colour.PERK.toWebHexString()+";'>Perk</span>): "
						+Util.capitaliseSentence(p.getName(Main.game.getPlayer())));
			} else {
				SB.append("<br/>"
						+"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
						+ " (<span style='color:"+Colour.PERK.toWebHexString()+";'>Perk</span>): "
						+Util.capitaliseSentence(p.getName(Main.game.getPlayer())));
			}
		}
		
		if(this.condition.isFemininityInRange()) {
			SB.append("<br/>"
					+"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
					+ " (Femininity): "
					+ "<span style='color:"+this.condition.getFemininityRequired().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(this.condition.getFemininityRequired().getName(false))+"</span>");
		} else {
			SB.append("<br/>"
					+"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
					+ " (Femininity): "
					+ "<span style='color:"+this.condition.getFemininityRequired().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(this.condition.getFemininityRequired().getName(false))+"</span>");
		}
		
		if(this.condition.isRequiredRace()) {
			SB.append("<br/>"
					+"<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Requirement</b>"
					+ " (Race): "
					+"<span style='color:"+this.condition.getRaceRequired().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(this.condition.getRaceRequired().getName())+"</span>");
		} else {
			SB.append("<br/>"
					+"<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Requirement</b>"
					+ " (Race): "
					+"<span style='color:"+this.condition.getRaceRequired().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(this.condition.getRaceRequired().getName())+"</span>");
		}

		return SB.toString();
	}
	
	public String getTooltipRequiredList(){
		SB = new StringBuilder();
		
		for(Fetish f : this.condition.getFetishesRequired()){
			if(Main.game.getPlayer().hasFetish(f)) {
				SB.append("<br/>"
						+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
						+ " (<span style='color:"+Colour.GENERIC_MINOR_GOOD.toWebHexString()+";'>owned</span>): "
						+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
				
			} else {
				SB.append("<br/>"
						+"<span style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>Associated Fetish</span>"
						+ " (<span style='color:"+Colour.GENERIC_MINOR_BAD.toWebHexString()+";'>not owned</span>): "
						+ Util.capitaliseSentence(f.getName(Main.game.getPlayer())));
			}
		}
		
		return SB.toString();
	}
	
	public int lineHeight(){
		int heightLeft = 0;
		
		if(this.condition.getPerksRequired()!=null)
			heightLeft+=this.condition.getPerksRequired().size();
		if(this.condition.getFemininityRequired()!=null)
			heightLeft++;
		if(this.condition.getRaceRequired()!=null)
			heightLeft++;
		if(this.condition.getFetishesRequired()!=null)
			heightLeft+=this.condition.getFetishesRequired().size();
		
		return heightLeft;
	}

}
