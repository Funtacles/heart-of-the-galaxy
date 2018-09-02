package com.lilithsthrone.game.dialogue;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.lilithsthrone.game.Game;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.XMLSaving;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public class DialogueFlags implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	public Set<DialogueFlagValue> values;
	
	public int scarlettPrice;
	public int eponaStamps;
	public long kalahariBreakStartTime;
	
	// Amount of dialogue choices you can make before offspring interaction ends:
	public int offspringDialogueTokens = 2;
	
	private String slaveTrader;
	private String slaveryManagerSlaveSelected;
	
	public DialogueFlags() {
		values = new HashSet<>();
		
		slaveryManagerSlaveSelected = null;
		slaveTrader = null;
		
		kalahariBreakStartTime = -1;
		
		eponaStamps = 0;
		
		scarlettPrice = 15000;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("dialogueFlags");
		parentElement.appendChild(element);
		
		CharacterUtils.createXMLElementWithValue(doc, element, "scarlettPrice", String.valueOf(scarlettPrice));
		CharacterUtils.createXMLElementWithValue(doc, element, "eponaStamps", String.valueOf(eponaStamps));
		CharacterUtils.createXMLElementWithValue(doc, element, "kalahariBreakStartTime", String.valueOf(kalahariBreakStartTime));
		
		CharacterUtils.createXMLElementWithValue(doc, element, "offspringDialogueTokens", String.valueOf(offspringDialogueTokens));
		CharacterUtils.createXMLElementWithValue(doc, element, "slaveTrader", slaveTrader);
		CharacterUtils.createXMLElementWithValue(doc, element, "slaveryManagerSlaveSelected", slaveryManagerSlaveSelected);
		
		Element valuesElement = doc.createElement("dialogueValues");
		element.appendChild(valuesElement);
		for(DialogueFlagValue value : values) {
			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "dialogueValue", value.toString());
		}
		
		return element;
	}
	
	public static DialogueFlags loadFromXML(Element parentElement, Document doc) {
		DialogueFlags newFlags = new DialogueFlags();
		
		newFlags.scarlettPrice = Integer.valueOf(((Element)parentElement.getElementsByTagName("scarlettPrice").item(0)).getAttribute("value"));
		
		newFlags.offspringDialogueTokens = Integer.valueOf(((Element)parentElement.getElementsByTagName("offspringDialogueTokens").item(0)).getAttribute("value"));
		newFlags.slaveTrader = ((Element)parentElement.getElementsByTagName("slaveTrader").item(0)).getAttribute("value");
		newFlags.slaveryManagerSlaveSelected = ((Element)parentElement.getElementsByTagName("slaveryManagerSlaveSelected").item(0)).getAttribute("value");
		
		try {
			newFlags.eponaStamps = Integer.valueOf(((Element)parentElement.getElementsByTagName("eponaStamps").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		try {
			newFlags.kalahariBreakStartTime = Long.valueOf(((Element)parentElement.getElementsByTagName("kalahariBreakStartTime").item(0)).getAttribute("value"));
		} catch(Exception ex) {
		}
		
		for(int i=0; i<((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").getLength(); i++){
			Element e = (Element) ((Element) parentElement.getElementsByTagName("dialogueValues").item(0)).getElementsByTagName("dialogueValue").item(i);
			
			try {
				newFlags.values.add(DialogueFlagValue.valueOf(e.getAttribute("value")));
			} catch(Exception ex) {
			}
		}
		
		if(Main.isVersionOlderThan(Game.loadingVersion, "0.2.6.1")) {
			newFlags.values.remove(DialogueFlagValue.axelIntroduced);
			newFlags.values.remove(DialogueFlagValue.roxyIntroduced);
			newFlags.values.remove(DialogueFlagValue.eponaIntroduced);
		}
		
		return newFlags;
	}
	
	public boolean hasFlag(DialogueFlagValue flag) {
		return values.contains(flag);
	}
	
	public void setFlag(DialogueFlagValue flag, boolean flagMarker) {
		if(flagMarker) {
			values.add(flag);
		} else {
			values.remove(flag);
		}
	}
	
	public NPC getSlaveTrader() {
		if(slaveTrader==null) {
			return null;
		}
		return (NPC) Main.game.getNPCById(slaveTrader);
	}

	public void setSlaveTrader(GameCharacter slaveTrader) {
		if(slaveTrader==null) {
			this.slaveTrader = null;
		} else {
			this.slaveTrader = slaveTrader.getId();
		}
	}
	
	public String getSlaveTraderId() {
		return slaveTrader;
	}

	public void setSlaveTraderId(String slaveTrader) {
		this.slaveTrader = slaveTrader;
	}

	public NPC getSlaveryManagerSlaveSelected() {
		if(slaveryManagerSlaveSelected==null
				|| slaveryManagerSlaveSelected.isEmpty()) {
			return null;
		}
		return (NPC) Main.game.getNPCById(slaveryManagerSlaveSelected);
	}

	public void setSlaveryManagerSlaveSelected(GameCharacter slaveryManagerSlaveSelected) {
		if(slaveryManagerSlaveSelected==null) {
			this.slaveryManagerSlaveSelected = null;
		} else {
			this.slaveryManagerSlaveSelected = slaveryManagerSlaveSelected.getId();
		}
	}
	
	public String getSlaveryManagerSlaveSelectedId() {
		return slaveryManagerSlaveSelected;
	}

	public void setSlaveryManagerSlaveSelectedId(String slaveryManagerSlaveSelected) {
		this.slaveryManagerSlaveSelected = slaveryManagerSlaveSelected;
	}
}
