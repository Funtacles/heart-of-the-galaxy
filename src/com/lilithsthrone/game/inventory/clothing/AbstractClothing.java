package com.lilithsthrone.game.inventory.clothing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.InventorySlot;
import com.lilithsthrone.game.inventory.ItemTag;
import com.lilithsthrone.game.inventory.enchanting.ItemEffect;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.Pattern;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;

import java.util.Set;

/**
 * @since 0.1.0
 * @version 0.2.5
 * @author Innoxia
 */
public abstract class AbstractClothing extends AbstractCoreItem implements Serializable, XMLSaving {

	private static final long serialVersionUID = 1L;

	private AbstractClothingType clothingType;
	protected List<ItemEffect> effects;
	
	private Colour secondaryColour;
	private Colour tertiaryColour;
	private boolean cummedIn;
	private List<DisplacementType> displacedList;
	
	private String pattern; // name of the pattern. 
	private Colour patternColour;
	private Colour patternSecondaryColour;
	private Colour patternTertiaryColour;
	
	public AbstractClothing(AbstractClothingType clothingType, Colour colour, Colour secondaryColour, Colour tertiaryColour, boolean allowRandomEnchantment) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColours().contains(colour) ? colour : clothingType.getAllAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColours().size())),
				null);

		this.itemTags = new HashSet<>(clothingType.getItemTags());
		
		this.clothingType = clothingType;
		if(clothingType.getEffects()==null) {
			this.effects = new ArrayList<>();
		} else {
			this.effects = new ArrayList<>(clothingType.getEffects());
		}
		
		cummedIn = false;
		
		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;
		
		patternColour = Colour.CLOTHING_BLACK;
		patternSecondaryColour = Colour.CLOTHING_BLACK;
		patternTertiaryColour = Colour.CLOTHING_BLACK;

		displacedList = new ArrayList<>();
	}
	
	public AbstractClothing(AbstractClothingType clothingType, Colour colour, Colour secondaryColour, Colour tertiaryColour, List<ItemEffect> effects) {
		super(clothingType.getName(),
				clothingType.getNamePlural(),
				clothingType.getPathName(),
				clothingType.getAllAvailablePrimaryColours().contains(colour) ? colour : clothingType.getAllAvailablePrimaryColours().get(Util.random.nextInt(clothingType.getAllAvailablePrimaryColours().size())),
				null);

		this.itemTags = new HashSet<>(clothingType.getItemTags());
		
		this.clothingType = clothingType;

		cummedIn = false;

		this.secondaryColour = secondaryColour;
		this.tertiaryColour = tertiaryColour;
		
		patternColour = Colour.CLOTHING_BLACK;
		patternSecondaryColour = Colour.CLOTHING_BLACK;
		patternTertiaryColour = Colour.CLOTHING_BLACK;
		
		displacedList = new ArrayList<>();

		this.effects = effects;
	}
	
	@Override
	public boolean equals (Object o) {
		if(super.equals(o)){
			if(o instanceof AbstractClothing){
				if(((AbstractClothing)o).getClothingType().equals(getClothingType())
						&& ((AbstractClothing)o).getSecondaryColour()==secondaryColour
						&& ((AbstractClothing)o).getTertiaryColour()==tertiaryColour
						&& ((AbstractClothing)o).getPattern().equals(getPattern())
						&& ((AbstractClothing)o).isDirty()==cummedIn
						){
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + getClothingType().hashCode();
		if(getSecondaryColour()!=null) {
			result = 31 * result + getSecondaryColour().hashCode();
		}
		if(getTertiaryColour()!=null) {
			result = 31 * result + getTertiaryColour().hashCode();
		}
		result = 31 * result + getPattern().hashCode();
		result = 31 * result + (cummedIn ? 1 : 0);
		return result;
	}
	
	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("clothing");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "id", this.getClothingType().getId());
		CharacterUtils.addAttribute(doc, element, "name", name);
		CharacterUtils.addAttribute(doc, element, "colour", this.getColour().toString());
		CharacterUtils.addAttribute(doc, element, "colourSecondary", this.getSecondaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "colourTertiary", this.getTertiaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "patternColour", this.getPatternColour().toString());
		CharacterUtils.addAttribute(doc, element, "patternColourSecondary", this.getPatternSecondaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "patternColourTertiary", this.getPatternTertiaryColour().toString());
		CharacterUtils.addAttribute(doc, element, "pattern", this.getPattern());
		CharacterUtils.addAttribute(doc, element, "isDirty", String.valueOf(this.isDirty()));
		
		Element innerElement = doc.createElement("effects");
		element.appendChild(innerElement);
		
		innerElement = doc.createElement("displacedList");
		element.appendChild(innerElement);
		for(DisplacementType dt : this.getDisplacedList()) {
			Element displacementType = doc.createElement("displacementType");
			innerElement.appendChild(displacementType);
			CharacterUtils.addAttribute(doc, displacementType, "value", dt.toString());
		}
		
		return element;
	}
	
	public static AbstractClothing loadFromXML(Element parentElement, Document doc) {
		AbstractClothing clothing = null;
		
		try {
			clothing = AbstractClothingType.generateClothing(ClothingType.getClothingTypeFromId(parentElement.getAttribute("id")), false);
		} catch(Exception ex) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}
		
		if(clothing==null) {
			System.err.println("Warning: An instance of AbstractClothing was unable to be imported. ("+parentElement.getAttribute("id")+")");
			return null;
		}
		

		if(!parentElement.getAttribute("name").isEmpty()) {
			clothing.setName(parentElement.getAttribute("name"));
		}
		
		// Try to load colour:
		try {
			clothing.setColour(Colour.valueOf(parentElement.getAttribute("colour")));
			if(!parentElement.getAttribute("colourSecondary").isEmpty()) {
				Colour secColour = Colour.valueOf(parentElement.getAttribute("colourSecondary"));
				if(clothing.clothingType.getAllAvailableSecondaryColours().contains(secColour)) {
					clothing.setSecondaryColour(secColour);
				}
			}
			if(!parentElement.getAttribute("colourTertiary").isEmpty()) {
				Colour terColour = Colour.valueOf(parentElement.getAttribute("colourTertiary"));
				if(clothing.clothingType.getAllAvailableTertiaryColours().contains(terColour)) {
					clothing.setTertiaryColour(terColour);
				}
			}
			
			if(!parentElement.getAttribute("pattern").isEmpty()) {
				String pat = parentElement.getAttribute("pattern");
				clothing.setPattern(pat);
			}
			
			if(!parentElement.getAttribute("patternColour").isEmpty()) {
				Colour colour = Colour.valueOf(parentElement.getAttribute("patternColour"));
				clothing.setPatternColour(colour);
			}
			if(!parentElement.getAttribute("patternColourSecondary").isEmpty()) {
				Colour secColour = Colour.valueOf(parentElement.getAttribute("patternColourSecondary"));
				clothing.setPatternSecondaryColour(secColour);
			}
			if(!parentElement.getAttribute("patternColourTertiary").isEmpty()) {
				Colour terColour = Colour.valueOf(parentElement.getAttribute("patternColourTertiary"));
				clothing.setPatternTertiaryColour(terColour);
			}
		} catch(Exception ex) {
		}

		// Try to load core features:
		try {
			clothing.setDirty(Boolean.valueOf(parentElement.getAttribute("isDirty")));
		} catch(Exception ex) {
		}
		
		// Try to load displacements:
		try {
			clothing.displacedList = new ArrayList<>();
			Element displacementElement = (Element)parentElement.getElementsByTagName("displacedList").item(0);
			NodeList displacementTypeElements = displacementElement.getElementsByTagName("displacementType");
			for(int i = 0; i < displacementTypeElements.getLength(); i++){
				Element e = ((Element)displacementTypeElements.item(i));
				
				DisplacementType dt = DisplacementType.valueOf(e.getAttribute("value"));
				boolean displacementTypeFound = false;
				for (BlockedParts bp : clothing.getClothingType().getBlockedPartsList()) {
					if (bp.displacementType == dt)
						displacementTypeFound = true;
				}
				if(displacementTypeFound)
					clothing.displacedList.add(dt);
				else
					System.err.println("Warning: Invalid displacement");
			}
		} catch(Exception ex) {
		}
		
		return clothing;
	}
	
	public Colour getSecondaryColour() {
		return secondaryColour;
	}

	public void setSecondaryColour(Colour secondaryColour) {
		this.secondaryColour = secondaryColour;
	}

	public Colour getTertiaryColour() {
		return tertiaryColour;
	}

	public void setTertiaryColour(Colour tertiaryColour) {
		this.tertiaryColour = tertiaryColour;
	}
	
	/**
	 * Returns the name of a pattern that the clothing has.
	 * @return
	 */
	public String getPattern() {
		if(pattern == null) {
			return "none";
		}
		return pattern;
	}
	
	/**
	 * Changes pattern to specified one. Will not render that pattern if it doesn't exist or the item doesn't support it anyway.
	 * @param pattern
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Colour getPatternColour() {
		return patternColour;
	}

	public Colour getPatternSecondaryColour() {
		return patternSecondaryColour;
	}

	public Colour getPatternTertiaryColour() {
		return patternTertiaryColour;
	}

	public void setPatternColour(Colour patternColour) {
		this.patternColour = patternColour;
	}

	public void setPatternSecondaryColour(Colour patternSecondaryColour) {
		this.patternSecondaryColour = patternSecondaryColour;
	}

	public void setPatternTertiaryColour(Colour patternTertiaryColour) {
		this.patternTertiaryColour = patternTertiaryColour;
	}

	private static StringBuilder descriptionSB = new StringBuilder();

	public String getTypeDescription() {
		return this.getClothingType().getDescription();
	}
	
	@Override
	public String getDescription() {
		descriptionSB.setLength(0);
		
		descriptionSB.append(
				"<p>"
					+ getTypeDescription()
				+ "</p>");
		
		// Physical resistance
		descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They" : "It") + " provide" + (getClothingType().isPlural() ? "" : "s") + " <b>" + getClothingType().getPhysicalResistance() + "</b> <b style='color: "
				+ Attribute.RESISTANCE_PHYSICAL.getColour().toWebHexString() + ";'> " + Attribute.RESISTANCE_PHYSICAL.getName() + "</b>.</p>");

		descriptionSB.append("<p>" + (getClothingType().isPlural() ? "They have" : "It has") + " a value of " + UtilText.formatAsMoney(getValue()) + ".");
		
		descriptionSB.append("</p>");

		return descriptionSB.toString();
	}

	public AbstractClothingType getClothingType() {
		return clothingType;
	}

	public boolean isCanBeEquipped(GameCharacter clothingOwner) {
		return this.getClothingType().isCanBeEquipped(clothingOwner);
	}

	public String getCannotBeEquippedText(GameCharacter clothingOwner) {
		return this.getClothingType().getCannotBeEquippedText(clothingOwner);
	}
	
	@Override
	public int getValue() {
		float runningTotal = this.getClothingType().getBaseValue();

		if (colourShade == Colour.CLOTHING_PLATINUM) {
			runningTotal *= 2f;
			
		} else if (colourShade == Colour.CLOTHING_GOLD) {
			runningTotal *= 1.75f;
			
		} else if (colourShade == Colour.CLOTHING_ROSE_GOLD) {
			runningTotal *= 1.5f;
			
		} else if (colourShade == Colour.CLOTHING_SILVER) {
			runningTotal *= 1.25f;
		}
		
		float attributeBonuses = 0;//getModifiedDropoffValue
		if (attributeModifiers != null) {
			for (Integer i : attributeModifiers.values()) {
				attributeBonuses += i * 15;
			}
		}
		
		attributeBonuses = Util.getModifiedDropoffValue(attributeBonuses, 500);
		
		runningTotal += Math.max(0, attributeBonuses);
		
		if (runningTotal < 1) {
			runningTotal = 1;
		}
		
		return (int) runningTotal;
	}
	
	@Override
	public String getName() {
		return this.getClothingType().getName();
	}
	
	/**
	 * @param withDeterminer
	 *            True if you want the determiner to prefix the name
	 * @return A string in the format "blue shirt" or "a blue shirt"
	 */
	public String getName(boolean withDeterminer) {
		return (withDeterminer ? (getClothingType().isPlural() ? getClothingType().getDeterminer() + " " : (Util.isVowel(getColour().getName().charAt(0)) ? "an " : "a ")) : "") + getColour().getName() + " " + getName();
	}
	
	/**
	 * @return A string in the format "Blue cap of frostbite" or
	 *         "Gold circlet of anti-magic"
	 */
	public String getDisplayName() {
		if(!this.getName().equalsIgnoreCase(this.getClothingType().getName())) { // If this item has a custom name, just display that
			return getName();
		}
		
		return Util.capitaliseSentence(getColour().getName()) + " "
				+ (!this.getPattern().equalsIgnoreCase("none")?Pattern.getPattern(this.getPattern()).getNiceName():"")
				+ getName();
	}

	@Override
	public String getSVGString() {
		return getClothingType().getSVGImage(colourShade, secondaryColour, tertiaryColour, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
	}
	
	public String getSVGEquippedString(GameCharacter character) {
		return getClothingType().getSVGEquippedImage(character, colourShade, secondaryColour, tertiaryColour, pattern, patternColour, patternSecondaryColour, patternTertiaryColour);
	}

	/**
	 * @return A description of this clothing being equipped.
	 */
	public String onEquipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().equipText(clothingOwner, clothingEquipper, rough, this, false);
	}

	/**
	 * Applies any extra effects this clothing causes when being unequipped.
	 * 
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipApplyEffects(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, rough, this, true);
	}

	/**
	 * @return A description of this clothing being unequipped.
	 */
	public String onUnequipText(GameCharacter clothingOwner, GameCharacter clothingEquipper, boolean rough) {
		return getClothingType().unequipText(clothingOwner, clothingEquipper, rough, this, false);
	}

	private static List<String> incompatibleClothing = new ArrayList<>();

	/**
	 * Returns a formatted description of if this clothing is sealed, cummedIn, too feminine/masculine and what slots it is blocking.
	 */
	public String clothingExtraInformation(GameCharacter equippedToCharacter) {
		StringBuilder extraInformationSB = new StringBuilder();
		
		if (equippedToCharacter == null) { // The clothing is not currently equipped by anyone:

			incompatibleClothing.clear();
			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots())
					if (Main.game.getPlayer().getClothingInSlot(invSlot) != null)
						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
			}
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots())
					if (getClothingType().getSlot() == invSlot)
						incompatibleClothing.add(c.getClothingType().getName());
			
			if(!getClothingType().getIncompatibleSlots().isEmpty()) {
				extraInformationSB.append("Equipping "+(getClothingType().isPlural()?"them":"it")+" will [style.boldBad(block)] your "+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots())+".<br/>");
			}
			
			if(Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot())!=null && Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot()).getClothingType().isDiscardedOnUnequip()) {
				extraInformationSB.append("[style.boldBad(Equipping this will cause the "+Main.game.getPlayer().getClothingInSlot(this.getClothingType().getSlot()).getName()+" you're already wearing to be discarded!)]<br/>");
			}
			
			if(cummedIn) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Colour.CUM.toWebHexString() + ";'>covered in sexual fluids</b>!<br/>");
			}
			
			if(this.getClothingType().isMufflesSpeech()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They [style.boldBad(muffle" : "It [style.boldBad(muffles") + " the wearer's speech)].<br/>");
			}

			if(this.getClothingType().isHindersLegMovement()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They [style.boldTerrible(block" : "It [style.boldTerrible(blocks") + " the wearer's escape in combat)] (if they are unable to fly).<br/>");
			}
			
			if(this.getClothingType().isHindersArmMovement()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They [style.boldTerrible(block" : "It [style.boldTerrible(blocks") + " flight from arm-wings)].<br/>");
			}
			
			if(getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.<br/>");
			}

			if(getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for you.<br/>");
			}
			
			if(!incompatibleClothing.isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color:" + Colour.GENERIC_BAD.toWebHexString() + ";'>incompatible</b> with your "
						+ Util.stringsToStringList(incompatibleClothing, false) + ".<br/>");
			}
			
			if(extraInformationSB.length()==0) {
				return "";
			}
			return "<p>"+extraInformationSB.toString().substring(0, extraInformationSB.length()-5)+"</p>";

		} else if (equippedToCharacter.isPlayer()) { // Character is player:
			
			if(!getClothingType().getIncompatibleSlots().isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.GENERIC_BAD.toWebHexString()
					+ ";'>blocking</b> your " + Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + "!<br/>");
			}
			
			if(this.getClothingType().isDiscardedOnUnequip()) {
				extraInformationSB.append("[style.boldBad(Removing your "+this.getName()+" will cause "+(getClothingType().isPlural() ? "them" : "it")+" to be discarded!)]<br/>");
			}
			
			if(cummedIn) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Colour.CUM.toWebHexString() + ";'>covered in sexual fluids</b>!<br/>");
			}
			
			if(this.getClothingType().isMufflesSpeech()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldBad(muffling your speech)].<br/>");
			}

			if(this.getClothingType().isHindersLegMovement() && !equippedToCharacter.isAbleToFly()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldTerrible(blocking your escape in combat)].<br/>");
			}

			if(this.getClothingType().isHindersArmMovement() && equippedToCharacter.isAbleToFlyFromArms()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are " : "It is") + " [style.boldTerrible(blocking flight from your arm-wings)].<br/>");
			}
			
			if(getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for you.<br/>");
			}

			if(getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for you.<br/>");
			}
			
			if(!displacedList.isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>"
						+ Util.displacementTypesToStringList(displacedList) + "</b>!<br/>");
			}

			if(extraInformationSB.length()==0) {
				return "";
			}
			return "<p>"+extraInformationSB.toString().substring(0, extraInformationSB.length()-5)+"</p>";

		} else { // Character is an NPC:


			if(!getClothingType().getIncompatibleSlots().isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldBad(blocking)] [npc.her] "
						+ Util.inventorySlotsToStringList(getClothingType().getIncompatibleSlots()) + "!<br/>");
			}
			
			if(this.getClothingType().isDiscardedOnUnequip()) {
				extraInformationSB.append("[style.boldBad(Removing [npc.namePos] "+this.getName()+" will cause "+(getClothingType().isPlural() ? "them" : "it")+" to be discarded!)]<br/>");
			}

			if(cummedIn) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have" : "It has") + " been <b style='color: " + Colour.CUM.toWebHexString() + ";'>covered in sexual fluids</b>!<br/>");
			}

			if(this.getClothingType().isMufflesSpeech()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldBad(muffling [npc.her] speech)].<br/>");
			}

			if(this.getClothingType().isHindersLegMovement() && !equippedToCharacter.isAbleToFly()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " [style.boldTerrible(blocking [npc.her] escape in combat)].<br/>");
			}

			if(this.getClothingType().isHindersArmMovement() && equippedToCharacter.isAbleToFlyFromArms()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are " : "It is") + " [style.boldTerrible(blocking flight from [npc.her] arm-wings)].<br/>");
			}
			
			if(getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>too masculine</b> for [npc.herHim].<br/>");
			}

			if(getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They are" : "It is") + " <b style='color: " + Colour.FEMININE.toWebHexString() + ";'>too feminine</b> for [npc.herHim].<br/>");
			}
			
			if(!displacedList.isEmpty()) {
				extraInformationSB.append((getClothingType().isPlural() ? "They have been" : "It has been") 
						+ " <b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>"+ Util.displacementTypesToStringList(displacedList) + "</b>!<br/>");
			}

			if(extraInformationSB.length()==0) {
				return "";
			}
			return "<p>"+UtilText.parse(equippedToCharacter,extraInformationSB.toString().substring(0, extraInformationSB.length()-5))+"</p>";
		}
		
	}
	
	public String getDisplacementBlockingDescriptions(GameCharacter equippedToCharacter){
		descriptionSB = new StringBuilder("<p><b>Displacement types:</b>");
		for(BlockedParts bp : getClothingType().getBlockedPartsList()){
			descriptionSB.append("<br/><b>"+Util.capitaliseSentence(bp.displacementType.getDescription())+":</b> ");
			if(equippedToCharacter.isAbleToBeDisplaced(this, bp.displacementType, false, false, equippedToCharacter))
				descriptionSB.append("<b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Available</b>");
			else
				descriptionSB.append("<b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>Blocked</b> by "+equippedToCharacter.getBlockingClothing().getName()+"");
		}
		descriptionSB.append("</p>");
		
		return descriptionSB.toString();
	}

	public List<String> getExtraDescriptions(GameCharacter equippedToCharacter) {
		List<String> descriptionsList = new ArrayList<>();
		
		if(this.getClothingType().isHindersLegMovement()) {
			descriptionsList.add("<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Blocks non-flight escape in combat</b>");
		}
		
		if(this.getClothingType().isHindersArmMovement()) {
			descriptionsList.add("<b style='color: " + Colour.GENERIC_TERRIBLE.toWebHexString() + ";'>Blocks arm-wing flight</b>");
		}
		
		if(this.getClothingType().isMufflesSpeech()) {
			descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Muffles speech</b>");
		}
		
		if(this.getItemTags().contains(ItemTag.PLUGS_ANUS)) {
			descriptionsList.add("<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + ";'>Plugs Anus</b>");
		}
		if(this.getItemTags().contains(ItemTag.PLUGS_VAGINA)) {
			descriptionsList.add("<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + ";'>Plugs Vagina</b>");
		}
		if(this.getItemTags().contains(ItemTag.PLUGS_NIPPLES)) {
			descriptionsList.add("<b style='color: " + Colour.GENERIC_SEX.toWebHexString() + ";'>Plugs Nipples</b>");
		}
		
		if (equippedToCharacter == null) { // The clothing is not currently
											// equipped by anyone:

			incompatibleClothing.clear();
			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				for (InventorySlot invSlot : getClothingType().getIncompatibleSlots())
					if (Main.game.getPlayer().getClothingInSlot(invSlot) != null)
						incompatibleClothing.add(Main.game.getPlayer().getClothingInSlot(invSlot).getClothingType().getName());
			}
			for (AbstractClothing c : Main.game.getPlayer().getClothingCurrentlyEquipped())
				for (InventorySlot invSlot : c.getClothingType().getIncompatibleSlots())
					if (getClothingType().getSlot() == invSlot)
						incompatibleClothing.add(c.getClothingType().getName());

			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Colour.GENERIC_BAD.toWebHexString()+";'>Equipping
				// blocks</b>");
				for (InventorySlot slot : getClothingType().getIncompatibleSlots())
					descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Blocks " + Util.capitaliseSentence(slot.getName()) + "</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Colour.CUM.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < Main.game.getPlayer().getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > Main.game.getPlayer().getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Colour.FEMININE.toWebHexString() + ";'>Too feminine</b>");
			}

			if (!incompatibleClothing.isEmpty()) {
				descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Incompatible with:</b>");
				descriptionsList.addAll(incompatibleClothing);
			}

		} else { // Being worn:

			if (!getClothingType().getIncompatibleSlots().isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Colour.GENERIC_BAD.toWebHexString()+";'>Blocking</b>");
				for (InventorySlot slot : getClothingType().getIncompatibleSlots())
					descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>Blocking " + Util.capitaliseSentence(slot.getName()) + "</b>");
			}

			if (cummedIn) {
				descriptionsList.add("<b style='color: " + Colour.CUM.toWebHexString() + ";'>Dirty</b>");
			}

			if (getClothingType().getFemininityMaximum() < equippedToCharacter.getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Colour.MASCULINE.toWebHexString() + ";'>Too masculine</b>");
			}

			if (getClothingType().getFemininityMinimum() > equippedToCharacter.getFemininityValue()) {
				descriptionsList.add("<b style='color: " + Colour.FEMININE.toWebHexString() + ";'>Too feminine</b>");
			}

			if (!displacedList.isEmpty()) {
				// descriptionsList.add("-<b style='color:
				// "+Colour.GENERIC_BAD.toWebHexString()+";'>Displaced</b>");
				for (DisplacementType dt : displacedList)
					descriptionsList.add("<b style='color: " + Colour.GENERIC_BAD.toWebHexString() + ";'>" + Util.capitaliseSentence(dt.getDescriptionPast()) + "</b>");
			}

		}

		return descriptionsList;
	}

	/**
	 * @return A list of blocked body parts. e.g. "Penis, Anus and Vagina" or
	 *         "Nipples"
	 */
	public String getClothingBlockingDescription(DisplacementType dt, GameCharacter owner, String preFix, String postFix) {
		Set<CoverableArea> coveredAreas = new HashSet<>();// EnumSet.noneOf(CoverableArea.class);

		if (dt == null) {
			for (BlockedParts bp : this.getClothingType().getBlockedPartsList())
				if (!this.getDisplacedList().contains(bp.displacementType))
					coveredAreas.addAll(bp.blockedBodyParts);
		} else {

			for (BlockedParts bp : this.getClothingType().getBlockedPartsList())
				if (bp.displacementType == dt)
					coveredAreas.addAll(bp.blockedBodyParts);
		}
		
		if(owner!=null) {
			if (owner.getVaginaType() == VaginaType.NONE)
				coveredAreas.remove(CoverableArea.VAGINA);
			if (owner.getPenisType() == PenisType.NONE)
				coveredAreas.remove(CoverableArea.PENIS);
		}
		
		if (!coveredAreas.isEmpty())
			return preFix + Util.setToStringListCoverableArea(coveredAreas) + postFix;
		else
			return "";
	}

	public boolean isDirty() {
		return cummedIn;
	}

	public void setDirty(boolean cummedIn) {
		this.cummedIn = cummedIn;
		if(Main.game.getPlayer()!=null) {
			if(Main.game.getPlayer().getClothingCurrentlyEquipped().contains(this)) {
				Main.game.getPlayer().updateInventoryListeners();
			}
		}
	}

	public List<DisplacementType> getDisplacedList() {
		return displacedList;
	}
	
	public void clearDisplacementList() {
		displacedList.clear();
	}

	@Override
	public Map<Attribute, Integer> getAttributeModifiers() {
		attributeModifiers.clear();
		
		return attributeModifiers;
	}
	
}
