package com.lilithsthrone.game.character.npc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterImportSetting;
import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.Body;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HipSize;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeModifier;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.character.fetishes.FetishDesire;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.NameTriplet;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.RaceStage;
import com.lilithsthrone.game.character.race.RacialBody;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.game.combat.Attack;
import com.lilithsthrone.game.combat.Combat;
import com.lilithsthrone.game.combat.SpecialAttack;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.AbstractCoreItem;
import com.lilithsthrone.game.inventory.CharacterInventory;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.AbstractItemType;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.settings.ForcedTFTendency;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexAreaInterface;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.game.sex.SexAreaPenetration;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexParticipantType;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.SexType;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.8
 * @author Innoxia
 */
public abstract class NPC extends GameCharacter implements XMLSaving {
	
	public static final int DEFAULT_TIME_START_VALUE = -1;
	
	protected long lastTimeEncountered = DEFAULT_TIME_START_VALUE;
	protected long lastTimeHadSex = DEFAULT_TIME_START_VALUE;
	protected long lastTimeOrgasmed = DEFAULT_TIME_START_VALUE;
	
	protected float buyModifier;
	protected float sellModifier;
	
	protected boolean addedToContacts;
	
	public Set<NPCFlagValue> NPCFlagValues;
	
	protected Set<SexPositionSlot> sexPositionPreferences;
	
	protected Body bodyPreference = null;
	
	protected Value<String, AbstractItem> heldTransformativePotion = null;
	
	protected NPC(NameTriplet nameTriplet, String description, int level, Gender startingGender, RacialBody startingRace,
			RaceStage stage, CharacterInventory inventory, WorldType worldLocation, PlaceType startingPlace, boolean addedToContacts) {
		super(nameTriplet, description, level, startingGender, startingRace, stage, inventory, worldLocation, startingPlace);
		
		this.addedToContacts = addedToContacts;
		
		sexPositionPreferences = new HashSet<>();
		
		buyModifier=0.75f;
		sellModifier=1.5f;
		
		NPCFlagValues = new HashSet<>();
		
		if(getLocation().equals(Main.game.getPlayer().getLocation()) && getWorldLocation()==Main.game.getPlayer().getWorldLocation()) {
			for(CoverableArea ca : CoverableArea.values()) {
				if(isCoverableAreaExposed(ca) && ca!=CoverableArea.MOUTH) {
					getPlayerKnowsAreas().add(ca);
				}
			}
		}
		
		loadImages();
	}
	
	@Override
	public Element saveAsXML(Element parentElement, Document doc) {
		Element properties = super.saveAsXML(parentElement, doc);
		
		Element npcSpecific = doc.createElement("npcSpecific");
		properties.appendChild(npcSpecific);

		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "lastTimeEncountered", String.valueOf(lastTimeEncountered));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "lastTimeHadSex", String.valueOf(lastTimeHadSex));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "lastTimeOrgasmed", String.valueOf(lastTimeOrgasmed));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "buyModifier", String.valueOf(buyModifier));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "sellModifier", String.valueOf(sellModifier));
		CharacterUtils.createXMLElementWithValue(doc, npcSpecific, "addedToContacts", String.valueOf(addedToContacts));

		Element valuesElement = doc.createElement("NPCValues");
		npcSpecific.appendChild(valuesElement);
		for(NPCFlagValue value : NPCFlagValues) {
			CharacterUtils.createXMLElementWithValue(doc, valuesElement, "NPCValue", value.toString());
		}
		
		Element preferredBody = doc.createElement("preferredBody");
		npcSpecific.appendChild(preferredBody);
		getPreferredBody().saveAsXML(preferredBody, doc);
		
		return properties;
	}
	
	public abstract void loadFromXML(Element parentElement, Document doc, CharacterImportSetting... settings);
	
	public static void loadNPCVariablesFromXML(NPC npc, StringBuilder log, Element parentElement, Document doc, CharacterImportSetting... settings) {
		
		GameCharacter.loadGameCharacterVariablesFromXML(npc, log, parentElement, doc, settings);
		
		Element npcSpecificElement = (Element) parentElement.getElementsByTagName("npcSpecific").item(0);
		
		if(npcSpecificElement!=null) {
			npc.setLastTimeEncountered(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeEncountered").item(0)).getAttribute("value")));
			npc.setLastTimeHadSex(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeHadSex").item(0)).getAttribute("value")), false);
			
			if(((Element)npcSpecificElement.getElementsByTagName("lastTimeOrgasmed").item(0))!=null) {
				npc.setLastTimeOrgasmed(Long.valueOf(((Element)npcSpecificElement.getElementsByTagName("lastTimeOrgasmed").item(0)).getAttribute("value")));
			} else {
				npc.setLastTimeOrgasmed(npc.getLastTimeHadSex());
			}
			
			npc.setBuyModifier(Float.valueOf(((Element)npcSpecificElement.getElementsByTagName("buyModifier").item(0)).getAttribute("value")));
			npc.setSellModifier(Float.valueOf(((Element)npcSpecificElement.getElementsByTagName("sellModifier").item(0)).getAttribute("value")));
			npc.addedToContacts = (Boolean.valueOf(((Element)npcSpecificElement.getElementsByTagName("addedToContacts").item(0)).getAttribute("value")));
		
	
			NodeList npcValues = ((Element) npcSpecificElement.getElementsByTagName("NPCValues").item(0)).getElementsByTagName("NPCValue");
			for(int i = 0; i < npcValues.getLength(); i++){
				Element e = (Element) npcValues.item(i);
				try {
					npc.NPCFlagValues.add(NPCFlagValue.valueOf(e.getAttribute("value")));
				} catch(Exception ex) {
				}
			}
			
			npc.bodyPreference = Body.loadFromXML(log, (Element) parentElement.getElementsByTagName("preferredBody").item(0), doc);
		}
	}
	
	public void resetSlaveFlags() {
		NPCFlagValues.remove(NPCFlagValue.flagSlaveBackground);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveSmallTalk);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveEncourage);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveHug);
		NPCFlagValues.remove(NPCFlagValue.flagSlavePettings);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveInspect);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveSpanking);
		NPCFlagValues.remove(NPCFlagValue.flagSlaveMolest);
	}
	
	/**
	 * Resets this character to their default state.
	 */
	public void dailyReset() {
	}
	
	/**
	 * Applies an hourly update to this NPC.
	 */
	public void hourlyUpdate() {
	}
	
	/**
	 * Applies an update to this NPC every time the game makes a turn.
	 */
	public void turnUpdate() {
	}
	
	public abstract DialogueNodeOld getEncounterDialogue();
	
	public void equipClothing(boolean replaceUnsuitableClothing, boolean onlyAddCoreClothing) {
		CharacterUtils.equipClothing(this, replaceUnsuitableClothing, onlyAddCoreClothing);
	}
	
	public boolean isClothingStealable() {
		return false;
	}
	
	public String getPresentInTileDescription() {
		StringBuilder tileSB = new StringBuilder();
		
		if(!this.isRaceConcealed()) {		
			tileSB.append(
					UtilText.parse(this,
							"<p style='text-align:center;'>"
							+ "<b style='color:"+Femininity.valueOf(this.getFemininityValue()).getColour().toWebHexString()+";'>[npc.A_femininity]</b>"
							+ " <b style='color:"+this.getRaceStage().getColour().toWebHexString()+";'>[npc.raceStage]</b>"
							+ " <b style='color:"+this.getRace().getColour().toWebHexString()+";'>[npc.race]</b> <b>is prowling this area!</b></p>"
						
							+ "<p style='text-align:center;'>"));
		} else {
			tileSB.append(
					UtilText.parse(this,
							"<p style='text-align:center;'>"
							+"<b>Someone or something is prowling this area!</b></p>"
				
							+ "<p style='text-align:center;'>"));
		}
				
		// Combat:
		if(this.getFoughtPlayerCount()>0) {
			tileSB.append(
					UtilText.parse(this,"You have <b style='color:"+Colour.GENERIC_COMBAT.toWebHexString()+";'>fought</b> [npc.herHim] <b>"));
					
					if(this.getFoughtPlayerCount()==1) {
						tileSB.append("once.");
					} else if(this.getFoughtPlayerCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getFoughtPlayerCount())+" times.");
					}
					
			tileSB.append("</b>"
							+ "<br/>"
							+ "You have <b style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>won</b> <b>");
					
					if(this.getLostCombatCount()==1) {
						tileSB.append("once.");
					} else if(this.getLostCombatCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getLostCombatCount())+" times.");
					}
							
			tileSB.append("</b>"
					+ "<br/>"
					+ "You have <b style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>lost</b> <b>");
					if(this.getWonCombatCount()==1) {
						tileSB.append("once.");
					} else if(this.getWonCombatCount()==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getWonCombatCount())+" times.");
					}
					tileSB.append("</b></p>");
		}
		
		// Sex:
		if(this.getSexPartners().containsKey(Main.game.getPlayer().getId())) {
			tileSB.append("<p style='text-align:center;'>");
					
			tileSB.append(
					UtilText.parse(this,
							"You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>submissive sex</b> with [npc.herHim]<b> "));
			
					if(this.getSexAsDomCount(Main.game.getPlayer())==1) {
						tileSB.append("once.");
					} else if(this.getSexAsDomCount(Main.game.getPlayer())==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getSexAsDomCount(Main.game.getPlayer()))+" times.");
					}
					
			tileSB.append(
					UtilText.parse(this,
							"</b>"
							+ "<br/>"
							+ "You have had <b style='color:"+Colour.GENERIC_SEX.toWebHexString()+";'>dominant sex</b> with  [npc.herHim]<b> "));
			
					if(this.getSexAsSubCount(Main.game.getPlayer())==1) {
						tileSB.append("once.");
					} else if(this.getSexAsSubCount(Main.game.getPlayer())==2) {
						tileSB.append("twice.");
					} else {
						tileSB.append(Util.intToString(this.getSexAsSubCount(Main.game.getPlayer()))+" times.");
					}
					tileSB.append("</b></p>");
		}
		
		return tileSB.toString();
	}
	
	// Trader:

	public String getTraderDescription() {
		return UtilText.parse(this,
				"<p>"
					+ "You have a look at what [npc.name] has for sale."
				+ "</p>");
	}

	public boolean isTrader() {
		return false;
	}

	public boolean willBuy(AbstractCoreItem item) {
		return false;
	}
	
	/**
	 * Handles any extra effects that need to be taken into account when selling an item to the player.
	 * @param item
	 */
	public void handleSellingEffects(AbstractCoreItem item, int count, int itemPrice) {
	}

	public float getBuyModifier() {
		return buyModifier;
	}

	public void setBuyModifier(float buyModifier) {
		this.buyModifier = buyModifier;
	}

	public float getSellModifier() {
		return Math.max(getBuyModifier(), sellModifier);
	}

	public void setSellModifier(float sellModifier) {
		this.sellModifier = sellModifier;
	}

	// Combat:
	
	public List<SpecialAttack> getSpecialAttacksAbleToUse() {
		List<SpecialAttack> specialAttacksAbleToUse = new ArrayList<>();
		
		for(SpecialAttack sa : this.getSpecialAttacks()) {
			if(Main.game.isInCombat()) {
				if(Combat.getCooldown(this, sa)==0) {
					specialAttacksAbleToUse.add(sa);
				}
			} else {
				specialAttacksAbleToUse.add(sa);
			}
		}
		
		return specialAttacksAbleToUse;
	}
	
	public Attack attackType() {
		boolean canCastASpecialAttack = !getSpecialAttacksAbleToUse().isEmpty();
		
		Map<Attack, Integer> attackWeightingMap = new HashMap<>();
		
		attackWeightingMap.put(Attack.MAIN, this.getRace().getPreferredAttacks().contains(Attack.MAIN)?75:50);
		attackWeightingMap.put(Attack.OFFHAND, this.getOffhandWeapon()==null?0:(this.getRace().getPreferredAttacks().contains(Attack.MAIN)?50:25));
		attackWeightingMap.put(Attack.SEDUCTION, 100);
		attackWeightingMap.put(Attack.SPECIAL_ATTACK, !canCastASpecialAttack?0:(this.getRace().getPreferredAttacks().contains(Attack.MAIN)?100:50));
		
		int total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
		}
		
		int index = Util.random.nextInt(total);
		total = 0;
		for(Entry<Attack, Integer> entry : attackWeightingMap.entrySet()) {
			total+=entry.getValue();
			if(index<total) {
				return entry.getKey();
			}
		}
		
		return Attack.MAIN;
	}
	
	public Response endCombat(boolean applyEffects, boolean playerVictory) {
		return null;
	};

	public int getEscapeChance() {
		return 30;
	}

	public boolean isSurrendersAtZeroMana() {
		return true;
	}

	// Post-combat:

	public int getExperienceFromVictory() {
		return getLevel() * 2;
	}

	public int getLootMoney() {
		return (int) ((getLevel() * 25) * (1 + Math.random() - 0.5f));
	}
	
	public List<AbstractCoreItem> getLootItems() {
		return Util.newArrayListOfValues(AbstractItemType.generateItem(ItemType.DYE_BRUSH));
	}
	
	
	// Relationships:
	
	public float getHourlyAffectionChange(int hour) {
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(this.getHomeLocationPlace().getHourlyAffectionChange()*100)/100f;
	}
	
	public float getDailyAffectionChange() {
		float totalAffectionChange = 0;
		
		for (int homeHour = 0; homeHour < 24-this.getTotalHoursWorked(); homeHour++) {
			totalAffectionChange += this.getHomeLocationPlace().getHourlyAffectionChange();
		}
		
		// To get rid of e.g. 2.3999999999999999999999:
		return Math.round(totalAffectionChange*100)/100f;
	}
	
	
	// Misc:

	/**
	 * By default, NPCs can't be impregnated.
	 * 
	 * @return
	 */
	@Override
	public boolean isAbleToBeImpregnated() {
		return false;
	}

	public boolean hasFlag(NPCFlagValue flag) {
		return NPCFlagValues.contains(flag);
	}
	
	public boolean addFlag(NPCFlagValue flag) {
		return NPCFlagValues.add(flag);
	}
	
	public boolean removeFlag(NPCFlagValue flag) {
		return NPCFlagValues.remove(flag);
	}
	
	public boolean setFlag(NPCFlagValue flag, boolean value) {
		if(value) {
			return addFlag(flag);
		} else {
			return removeFlag(flag);
		}
	}
	
	public boolean isKnowsPlayerGender() {
		return NPCFlagValues.contains(NPCFlagValue.knowsPlayerGender);
	}

	public void setKnowsPlayerGender(boolean knowsPlayerGender) {
		if(knowsPlayerGender) {
			NPCFlagValues.add(NPCFlagValue.knowsPlayerGender);
		} else {
			NPCFlagValues.remove(NPCFlagValue.knowsPlayerGender);
		}
	}
	
	public boolean isIntroducedToPlayer() {
		return NPCFlagValues.contains(NPCFlagValue.introducedToPlayer);
	}

	public void setIntroducedToPlayer(boolean introducedToPlayer) {
		if(introducedToPlayer) {
			NPCFlagValues.add(NPCFlagValue.introducedToPlayer);
		} else {
			NPCFlagValues.remove(NPCFlagValue.introducedToPlayer);
		}
	}
	
	public boolean isPendingClothingDressing() {
		return NPCFlagValues.contains(NPCFlagValue.pendingClothingDressing);
	}
	public void setPendingClothingDressing(boolean pendingClothingDressing) {
		if(pendingClothingDressing) {
			NPCFlagValues.add(NPCFlagValue.pendingClothingDressing);
		} else {
			NPCFlagValues.remove(NPCFlagValue.pendingClothingDressing);
		}
	}
	
	public boolean isPendingTransformationToGenderIdentity() {
		return NPCFlagValues.contains(NPCFlagValue.pendingTransformationToGenderIdentity);
	}
	public void setPendingTransformationToGenderIdentity(boolean pendingTransformationToGenderIdentity) {
		if(pendingTransformationToGenderIdentity) {
			NPCFlagValues.add(NPCFlagValue.pendingTransformationToGenderIdentity);
		} else {
			NPCFlagValues.remove(NPCFlagValue.pendingTransformationToGenderIdentity);
		}
	}
	
	public long getLastTimeEncountered() {
		return lastTimeEncountered;
	}

	public void setLastTimeEncountered(long minutesPassed) {
		this.lastTimeEncountered = minutesPassed;
	}

	public long getLastTimeHadSex() {
		return lastTimeHadSex;
	}
	
	public void setLastTimeHadSex(long lastTimeHadSex, boolean orgasmed) {
		this.lastTimeHadSex = lastTimeHadSex;
		if(orgasmed) {
			setLastTimeOrgasmed(lastTimeHadSex);
		}
	}
	
	public long getLastTimeOrgasmed() {
		return lastTimeOrgasmed;
	}
	
	public void setLastTimeOrgasmed(long lastTimeOrgasmed) {
		this.lastTimeOrgasmed = lastTimeOrgasmed;
	}

	public boolean isAddedToContacts() {
		return addedToContacts;
	}
	
	public String getPreferredBodyDescription(String tag) {
		return "<"+tag+" style='color:"+getPreferredBody().getGender().getColour().toWebHexString()+";'>"+getPreferredBody().getGender().getName()+"</"+tag+">"
				+ " <"+tag+" style='color:"+getPreferredBody().getRace().getColour().toWebHexString()+";'>"+getPreferredBody().getRace().getName()+"</"+tag+">";
	}
	
	public Body getPreferredBody() {
		if(bodyPreference == null) {
			regenerateBodyPreference();
		}
		
		return bodyPreference;
	}
	
	public void regenerateBodyPreference() {
		bodyPreference = generatePreferredBody();
	}
	
	private Body generatePreferredBody() {
		
		// Preferred gender:
		
		Gender preferredGender = Gender.N_P_V_B_HERMAPHRODITE;
		Map<Gender, Integer> desiredGenders = new HashMap<>();
		
		switch(this.getSexualOrientation()) {
			case AMBIPHILIC:
				if(this.isFeminine() && 
						// ambiphilic characters respect forcedTFTendency setting by not entering this case if the
						// player has requested a feminine tendency; admittedly, this specific logic does slightly skew 
						// towards pushing the player feminine in neutral scenarios, but only to a small degree, so more
						// complex but fair logic doesn't feel too required
						Main.getProperties().forcedTFTendency != ForcedTFTendency.FEMININE &&
						Main.getProperties().forcedTFTendency != ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.M_P_MALE, 14);
					// maybe it would be appropriate to raise these chances for impregnators?
					desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
					desiredGenders.put(Gender.M_V_CUNTBOY, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				} else {
					// basic chances of cis-female preference
					desiredGenders.put(Gender.F_V_B_FEMALE, 14);
					
					// increase chances of growing a penis if fetishes increase desirability 
					if(this.hasVagina() && (this.hasFetish(Fetish.FETISH_PREGNANCY))) {
						desiredGenders.put(Gender.F_P_V_B_FUTANARI, 4);
						desiredGenders.put(Gender.F_P_B_SHEMALE, 4);
						desiredGenders.put(Gender.F_P_TRAP, 4);
						
					} else {
						desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
						desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
						desiredGenders.put(Gender.F_P_TRAP, 2);
					};
					
					// heavy masculine forcedTFTendency option adds a bit of a chance for masculine preferenes here
					if (Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE_HEAVY) {
						desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 4);
						desiredGenders.put(Gender.M_V_CUNTBOY, 4);
						desiredGenders.put(Gender.F_P_TRAP, 4);
						desiredGenders.put(Gender.M_V_B_BUTCH, 4);
					}
				}
				break;
			case ANDROPHILIC:
				// Heavy feminine forcedTFTendency causes androphiles to lose the majority of masculine options
				if (Main.getProperties().forcedTFTendency != ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.M_P_MALE, 14);
				}
				
				// base chance options regardless of forcedTFTendency option
				desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
				desiredGenders.put(Gender.M_V_CUNTBOY, 2);
				
				// both feminine forcedTFTendency options add decent chances to get some feminine options despite tastes
				if(Main.getProperties().forcedTFTendency == ForcedTFTendency.FEMININE || 
				   Main.getProperties().forcedTFTendency == ForcedTFTendency.FEMININE_HEAVY) {
					desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
					desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
					desiredGenders.put(Gender.M_V_B_BUTCH, 2);
				}
				break;
			case GYNEPHILIC:
				// increase chances of growing a penis if fetishes increase desirability; also, this is a reasonable
				// base level of feminine options even if forcedTFTendency is heavy male
				if(this.hasVagina() && (this.hasFetish(Fetish.FETISH_PREGNANCY))) {
					desiredGenders.put(Gender.F_P_V_B_FUTANARI, 2);
					desiredGenders.put(Gender.F_P_B_SHEMALE, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				// much lower base chance of pure female preference for heavy masculine forcedTFTendency
				} else if (Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE_HEAVY) {
					desiredGenders.put(Gender.F_V_B_FEMALE, 4);
				}
				else {
					desiredGenders.put(Gender.F_V_B_FEMALE, 14);
				}
				
				// both masculine forcedTFTendency options add decent chances to get some masculine options despite tastes
				if(Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE || 
				   Main.getProperties().forcedTFTendency == ForcedTFTendency.MASCULINE_HEAVY) {
					desiredGenders.put(Gender.M_P_V_HERMAPHRODITE, 2);
					desiredGenders.put(Gender.M_V_CUNTBOY, 2);
					desiredGenders.put(Gender.M_V_B_BUTCH, 2);
					desiredGenders.put(Gender.F_P_TRAP, 2);
				}
				break;
		}
		
		int total = 0;
		for(Entry<Gender, Integer> entry : desiredGenders.entrySet()) {
			total+=entry.getValue();
		}
		int count = Util.random.nextInt(total)+1;
		total = 0;
		for(Entry<Gender, Integer> entry : desiredGenders.entrySet()) {
			if(total < count && total+entry.getValue()>= count) {
				preferredGender = entry.getKey();
				break;
			}
			total+=entry.getValue();
		}
		
		// Leaving this present but commented out so it can be easily re-enabled by anyone wanting to tweak or check
		// the results of gender selection and the forcedTFTendency setting
//		System.out.println("PREFERRED GENDER");
//		System.out.println(preferredGender);
//		System.out.println(desiredGenders);
		
		// Preferred race:
		
		Subspecies species = getSubspecies();
		RaceStage stage = getRaceStage();
		
		if((getRace()==Race.WOLF_MORPH || getRace()==Race.DOG_MORPH) && Math.random()>0.8f) {
			List<Subspecies> availableRaces = new ArrayList<>();
			availableRaces.add(Subspecies.CAT_MORPH);
			availableRaces.add(Subspecies.COW_MORPH);
			availableRaces.add(Subspecies.SQUIRREL_MORPH);
			species = availableRaces.get(Util.random.nextInt(availableRaces.size()));
		}
		
		// Chance for race to be random:
		if(Math.random()>0.85f) {
			List<Subspecies> availableRaces = new ArrayList<>();
			availableRaces.add(Subspecies.CAT_MORPH);
			availableRaces.add(Subspecies.DOG_MORPH);
			availableRaces.add(Subspecies.HORSE_MORPH);
			availableRaces.add(Subspecies.HUMAN);
			availableRaces.add(Subspecies.SQUIRREL_MORPH);
			availableRaces.add(Subspecies.COW_MORPH);
			availableRaces.add(Subspecies.WOLF_MORPH);
			species = availableRaces.get(Util.random.nextInt(availableRaces.size()));
		}
		
		// Preferred race stage:
		stage = RaceStage.GREATER;
		
		Body body = CharacterUtils.generateBody(preferredGender, species, stage);
		
		// Apply fetish modifiers:
		
		GameCharacter genericOwner = Main.game.getGenericAndrogynousNPC();
		
		//Ass:
		if(hasFetish(Fetish.FETISH_ANAL_GIVING)) {
			body.getAss().setAssSize(genericOwner, AssSize.FIVE_HUGE.getValue());
			body.getAss().setAssSize(genericOwner, HipSize.FIVE_VERY_WIDE.getValue());
		}
		
		// Body:
		if(preferredGender.isFeminine()) {
			// Want feminine bodies to be smaller than them:
			body.setHeight(getHeightValue() - Util.random.nextInt(25));
			
		} else {
			// Want masculine bodies to be taller than them:
			body.setHeight(getHeightValue() + Util.random.nextInt(25));
		}
		
		//Breasts:
		if(Main.getProperties().multiBreasts==0) {
			body.getBreast().setRows(genericOwner, 1);
			
		} else if(Main.getProperties().multiBreasts==1) {
			if(stage != RaceStage.GREATER) {
				body.getBreast().setRows(genericOwner, 1);
			}
		}

		if(hasFetish(Fetish.FETISH_BREASTS_OTHERS) && preferredGender.isFeminine()) {
			body.getBreast().setSize(genericOwner, CupSize.DD.getMeasurement() + (Util.random.nextInt(5)));
		}
		
		// Face:
		if(hasFetish(Fetish.FETISH_ORAL_RECEIVING)) {
			body.getFace().getMouth().getOrificeMouth().addOrificeModifier(genericOwner, OrificeModifier.PUFFY);
			body.getFace().getMouth().setLipSize(genericOwner, LipSize.FOUR_HUGE.getValue());
		}
		
		// Hair:
		if(preferredGender.isFeminine()) {
			body.getHair().setLength(genericOwner, HairLength.THREE_SHOULDER_LENGTH.getMedianValue() + Util.random.nextInt(HairLength.SEVEN_TO_FLOOR.getMinimumValue() - HairLength.THREE_SHOULDER_LENGTH.getMedianValue()));
			
		} else {
			body.getHair().setLength(genericOwner, HairLength.ONE_VERY_SHORT.getMedianValue() + Util.random.nextInt(HairLength.THREE_SHOULDER_LENGTH.getMinimumValue() - HairLength.ONE_VERY_SHORT.getMedianValue()));
		}
		
		// Penis:
		if(body.getPenis().getType()!=PenisType.NONE) {
			if(preferredGender==Gender.F_P_TRAP && Math.random()>=0.1f) { // Most traps have a small cock:
				body.getPenis().setPenisSize(genericOwner, PenisSize.ONE_TINY.getMedianValue() + Util.random.nextInt(4));
				body.getPenis().getTesticle().setTesticleSize(genericOwner, TesticleSize.ONE_TINY.getValue());
				body.getPenis().getTesticle().setCumStorage(genericOwner, CumProduction.ONE_TRICKLE.getMedianValue());
			} else {
				body.getPenis().setPenisSize(genericOwner,body.getPenis().getSize().getMinimumValue() + Util.random.nextInt(body.getPenis().getSize().getMaximumValue() - body.getPenis().getSize().getMinimumValue()) +1);
			}
		}
		
		// Vagina:
		if(body.getVagina().getType()!=VaginaType.NONE) {
			body.getVagina().getOrificeVagina().setWetness(genericOwner, Wetness.THREE_WET.getValue() + Util.random.nextInt(4));
		}
		
		return body;
	}
	
	
	// Sex:
	
	
	public void calculateGenericSexEffects(boolean isDom, NPC partner, SexType sexType) {
		this.setLastTimeHadSex(Main.game.getMinutesPassed(), true);
		partner.setLastTimeHadSex(Main.game.getMinutesPassed(), true);
		
		if(isDom) {
			this.setSexAsDomCount(partner, this.getSexAsSubCount(partner)+1);
			partner.setSexAsSubCount(partner, partner.getSexAsSubCount(partner)+1);
			
		} else {
			partner.setSexAsDomCount(partner, partner.getSexAsSubCount(partner)+1);
			this.setSexAsSubCount(partner, this.getSexAsSubCount(partner)+1);
		}
		
		SexAreaInterface performingArea = sexType.getPerformingSexArea();
		SexAreaInterface targetedArea = sexType.getTargetedSexArea();
		
		this.addSexPartner(partner, sexType);
		SexType partnerSexType = new SexType(SexParticipantType.NORMAL, targetedArea, performingArea);
		partner.addSexPartner(this, partnerSexType);

		if(targetedArea.isPenetration()) {
			if(((SexAreaPenetration)targetedArea).isTakesVirginity()) {
				this.setVirginityLoss(sexType, partner.getName("a") + " " + partner.getLostVirginityDescriptor());
				if(performingArea.isOrifice()) {
					switch(((SexAreaOrifice)performingArea)) {
						case ANUS:
							this.setAssVirgin(false);
							break;
						case ASS:
							break;
						case BREAST:
							break;
						case MOUTH:
							this.setFaceVirgin(false);
							break;
						case NIPPLE:
							this.setNippleVirgin(false);
							break;
						case THIGHS:
							break;
						case URETHRA_PENIS:
							this.setUrethraVirgin(false);
							break;
						case URETHRA_VAGINA:
							this.setVaginaUrethraVirgin(false);
							break;
						case VAGINA:
							this.setVaginaVirgin(false);
							break;
					}
				}
			}
			switch(((SexAreaPenetration)targetedArea)) {
				case FINGER:
					break;
				case PENIS:
					this.setVirginityLoss(partnerSexType, this.getName("a") + " " + this.getLostVirginityDescriptor());
					partner.setPenisVirgin(false);
					if(partner.getPenisRawCumStorageValue()>0 && performingArea.isOrifice()) {
						this.ingestFluid(partner, partner.getCumType(), (SexAreaOrifice)performingArea, partner.getPenisRawCumStorageValue(), partner.getCumModifiers());
					}
					break;
				case TAIL:
					break;
				case TENTACLE:
					break;
				case TONGUE:
					break;
				case CLIT:
					break;
				case FOOT:
					break;
			}
		}
		
	}
	
	public void endSex() {
	}
	
	
	
	public boolean getSexBehaviourDeniesRequests(SexType sexTypeRequest) {
		
		boolean keenToPerform = false;
		
		if(sexTypeRequest.getPerformingSexArea()!=null) {
			if(sexTypeRequest.getPerformingSexArea().isOrifice()) {
				switch((SexAreaOrifice)sexTypeRequest.getPerformingSexArea()) {
					case ANUS:
						if(this.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case ASS:
						if(this.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ANAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case BREAST:
						if(this.getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
							keenToPerform = true;
						}
						break;
					case MOUTH:
						if(this.getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case NIPPLE:
						if(this.getFetishDesire(Fetish.FETISH_BREASTS_SELF).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_BREASTS_SELF).isPositive()) {
							keenToPerform = true;
						}
						break;
					case THIGHS:
						if(this.getFetishDesire(Fetish.FETISH_STRUTTER).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_STRUTTER).isPositive()) {
							keenToPerform = true;
						}
						break;
					case URETHRA_PENIS:
						if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case URETHRA_VAGINA:
						if(this.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case VAGINA:
						if(this.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
				}
			} else {
				switch((SexAreaPenetration)sexTypeRequest.getPerformingSexArea()) {
					case CLIT:
						if(this.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_VAGINAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case FINGER:
						break;
					case PENIS:
						if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_PENIS_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						if(this.getFetishDesire(Fetish.FETISH_FOOT_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_FOOT_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case TONGUE:
						if(this.getFetishDesire(Fetish.FETISH_ORAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ORAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
				}
			}
			
		} else if(sexTypeRequest.getTargetedSexArea()!=null) {
			if(sexTypeRequest.getTargetedSexArea().isOrifice()) {
				switch((SexAreaOrifice)sexTypeRequest.getTargetedSexArea()) {
					case ANUS:
						if(this.getFetishDesire(Fetish.FETISH_ANAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case ASS:
						if(this.getFetishDesire(Fetish.FETISH_ANAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ANAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case BREAST:
						if(this.getFetishDesire(Fetish.FETISH_BREASTS_OTHERS).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_BREASTS_OTHERS).isPositive()) {
							keenToPerform = true;
						}
						break;
					case MOUTH:
						if(this.getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case NIPPLE:
						if(this.getFetishDesire(Fetish.FETISH_BREASTS_OTHERS).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_BREASTS_OTHERS).isPositive()) {
							keenToPerform = true;
						}
						break;
					case THIGHS:
						if(this.getFetishDesire(Fetish.FETISH_LEG_LOVER).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_LEG_LOVER).isPositive()) {
							keenToPerform = true;
						}
						break;
					case URETHRA_PENIS:
						if(this.getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case URETHRA_VAGINA:
						if(this.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case VAGINA:
						if(this.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
				}
			} else {
				switch((SexAreaPenetration)sexTypeRequest.getTargetedSexArea()) {
					case CLIT:
						if(this.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_VAGINAL_GIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case FINGER:
						break;
					case PENIS:
						if(this.getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_PENIS_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case TAIL:
						break;
					case TENTACLE:
						break;
					case FOOT:
						if(this.getFetishDesire(Fetish.FETISH_FOOT_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_FOOT_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
					case TONGUE:
						if(this.getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isNegative()) {
							return true;
						} else if(this.getFetishDesire(Fetish.FETISH_ORAL_RECEIVING).isPositive()) {
							keenToPerform = true;
						}
						break;
				}
			}
		}
		
		return !keenToPerform && hasFetish(Fetish.FETISH_SADIST);
	}
	
	protected Map<GameCharacter, SexType> foreplayPreference = new HashMap<>();
	protected Map<GameCharacter, SexType> mainSexPreference = new HashMap<>();
	
	public SexType getForeplayPreference(GameCharacter target) {
		return foreplayPreference.get(target);
	}

	public void setForeplayPreference(GameCharacter target, SexType foreplayPreference) {
		this.foreplayPreference.put(target, foreplayPreference);
	}

	public SexType getMainSexPreference(GameCharacter target) {
		return mainSexPreference.get(target);
	}

	public void setMainSexPreference(GameCharacter target, SexType mainSexPreference) {
		this.mainSexPreference.put(target, mainSexPreference);
	}

	private boolean isKeenToPerformFetishAction(GameCharacter target, Fetish fetish) {
		return this.getFetishDesire(fetish)==FetishDesire.THREE_LIKE || this.getFetishDesire(fetish)==FetishDesire.FOUR_LOVE;
	}
	
	private boolean isKeenToAvoidFetishAction(GameCharacter target, Fetish fetish) {
		return this.getFetishDesire(fetish)==FetishDesire.ZERO_HATE || this.getFetishDesire(fetish)==FetishDesire.ONE_DISLIKE;
	}
	
	public void generateSexChoices(GameCharacter target, SexType request) {
		List<SexType> foreplaySexTypes = new ArrayList<>();
		List<SexType> mainSexTypes = new ArrayList<>();
		
		// ************************ Populate possibilities from fetishes and likes. ************************ //
		
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_BREASTS_OTHERS)
				|| (request!=null && request.getTargetedSexArea()==SexAreaOrifice.BREAST)
				|| (request!=null && request.getTargetedSexArea()==SexAreaOrifice.NIPPLE)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.BREAST));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.NIPPLE));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.BREAST));
			
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.BREAST));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TAIL, SexAreaOrifice.BREAST));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TAIL, SexAreaOrifice.NIPPLE));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_BREASTS_SELF)
				|| (request!=null && request.getPerformingSexArea()==SexAreaOrifice.BREAST)
				|| (request!=null && request.getPerformingSexArea()==SexAreaOrifice.NIPPLE)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.FINGER));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.TONGUE));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.FINGER));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS));

			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.TAIL));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.TAIL));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ANAL_GIVING)
				|| (request!=null && request.getTargetedSexArea()==SexAreaOrifice.ANUS)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.FINGER, SexAreaOrifice.ANUS));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS));
			
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TAIL, SexAreaOrifice.ANUS));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ANAL_RECEIVING)
				|| (request!=null && request.getPerformingSexArea()==SexAreaOrifice.ANUS)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.FINGER));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE));

			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TAIL));
		}
		if((isKeenToPerformFetishAction(target, Fetish.FETISH_DEFLOWERING) && target.isVaginaVirgin())
				|| isKeenToPerformFetishAction(target, Fetish.FETISH_IMPREGNATION)
				|| (request!=null && request.getTargetedSexArea()==SexAreaOrifice.VAGINA)) {
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_PREGNANCY)) {
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
		}
		if(request!=null && request.getPerformingSexArea()==SexAreaOrifice.VAGINA) {
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS));
		}
		if(request!=null && request.getTargetedSexArea()==SexAreaOrifice.VAGINA) {
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ORAL_RECEIVING)
				|| (request!=null && request.getTargetedSexArea()==SexAreaOrifice.MOUTH)
				|| (request!=null && request.getTargetedSexArea()==SexAreaPenetration.TONGUE)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
		}
		if(isKeenToPerformFetishAction(target, Fetish.FETISH_ORAL_GIVING)
				|| (request!=null && request.getPerformingSexArea()==SexAreaOrifice.MOUTH)
				|| (request!=null && request.getPerformingSexArea()==SexAreaPenetration.TONGUE)) {
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA));
			mainSexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
		}
		
		// ************************ This section deals with the possibilities that no fetish-related SexTypes were chosen ************************ //
		
		// If no preferences from fetishes, add all common foreplay actions:
		if(foreplaySexTypes.isEmpty()) {
			// Player penetrates:
			List<SexAreaPenetration> penTypes = Util.newArrayListOfValues(
					SexAreaPenetration.FINGER,
					SexAreaPenetration.TONGUE);

			List<SexAreaOrifice> orificeTypes = Util.newArrayListOfValues(
					SexAreaOrifice.BREAST,
					SexAreaOrifice.NIPPLE,
					SexAreaOrifice.VAGINA);
			
			for(SexAreaPenetration pen : penTypes) {
				for(SexAreaOrifice orifice : orificeTypes) {
					foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, orifice, pen));
					foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, pen, orifice));
				}
			}
			
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS));
			foreplaySexTypes.add(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH));
			
		}
		// If no preferences from fetishes, add all common sex actions:
		if(mainSexTypes.isEmpty()) { 
			// Player penetrates:
			List<SexAreaPenetration> penTypes = Util.newArrayListOfValues(
					SexAreaPenetration.PENIS,
					SexAreaPenetration.PENIS,
					SexAreaPenetration.PENIS,
					SexAreaPenetration.TAIL);

			List<SexAreaOrifice> orificeTypes = Util.newArrayListOfValues(
					SexAreaOrifice.BREAST,
					SexAreaOrifice.VAGINA,
					SexAreaOrifice.VAGINA,
					SexAreaOrifice.VAGINA);
			
			if(!target.hasVagina() || !target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)) {
				orificeTypes.add(SexAreaOrifice.ANUS);
				orificeTypes.add(SexAreaOrifice.ANUS);
				orificeTypes.add(SexAreaOrifice.ANUS);
			}
			
			for(SexAreaPenetration pen : penTypes) {
				for(SexAreaOrifice orifice : orificeTypes) {
					if(!(pen==SexAreaPenetration.TAIL && orifice!=SexAreaOrifice.BREAST)) {
						mainSexTypes.add(new SexType(SexParticipantType.NORMAL, orifice, pen));
						mainSexTypes.add(new SexType(SexParticipantType.NORMAL, pen, orifice));
					}
				}
			}
			
		}

		// ************************ Remove SexTypes that are physically impossible to perform, or that are not wanted by the NPC. ************************ //
		
		// Penis:
		if(!target.hasPenis()
				|| !target.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_PENIS_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaPenetration.PENIS);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaPenetration.PENIS);
		}
		if(!this.hasPenis()
				|| !this.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_PENIS_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaPenetration.PENIS);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaPenetration.PENIS);
		}
		// Vagina:
		if(!target.hasVagina()
				|| !target.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_VAGINAL_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.VAGINA);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.VAGINA);
		}
		if(isKeenToAvoidFetishAction(target, Fetish.FETISH_PURE_VIRGIN)
				|| !this.hasVagina()
				|| !this.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_VAGINAL_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.VAGINA);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.VAGINA);
		}
		// Anus:
		if(!target.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_ANAL_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.ANUS);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.ANUS);
		}
		if(!this.isAbleToAccessCoverableArea(CoverableArea.ANUS, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_VAGINAL_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.ANUS);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.ANUS);
		}
		// Oral:
		if(!target.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_ORAL_RECEIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.MOUTH);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.MOUTH);
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaPenetration.TONGUE);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaPenetration.TONGUE);
		}
		if(!this.isAbleToAccessCoverableArea(CoverableArea.MOUTH, true)
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_ORAL_GIVING)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.MOUTH);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.MOUTH);
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaPenetration.TONGUE);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaPenetration.TONGUE);
		}
		// Breasts:
		if(!target.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
				|| (!target.hasBreasts() && !target.isBreastFuckableNipplePenetration())
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_BREASTS_OTHERS)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.NIPPLE);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.NIPPLE);
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.BREAST);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaOrifice.BREAST);
		}
		if(!this.isAbleToAccessCoverableArea(CoverableArea.NIPPLES, true)
				|| (!this.hasBreasts() && !this.isBreastFuckableNipplePenetration())
				|| isKeenToAvoidFetishAction(target, Fetish.FETISH_BREASTS_SELF)) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.NIPPLE);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.NIPPLE);
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.BREAST);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaOrifice.BREAST);
		}
		// Tail:
		if(!target.getTailType().isSuitableForPenetration() || (this.hasPenis())) {
			foreplaySexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaPenetration.TAIL);
			mainSexTypes.removeIf(sexType -> sexType.getTargetedSexArea()==SexAreaPenetration.TAIL);
		}
		if(!this.getTailType().isSuitableForPenetration() || this.hasPenis()) {
			foreplaySexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaPenetration.TAIL);
			mainSexTypes.removeIf(sexType -> sexType.getPerformingSexArea()==SexAreaPenetration.TAIL);
		}
		
		
		// ************************ Finally, set preferences from the resulting lists. ************************ //

		foreplayPreference.put(target, null);
		if(!foreplaySexTypes.isEmpty()) {
			if(request!=null) {
				List<SexType> requestedSexTypes = new ArrayList<>(foreplaySexTypes);
				requestedSexTypes.removeIf((type) -> type.getTargetedSexArea()!=request);
				if(!requestedSexTypes.isEmpty()) {
					foreplayPreference.put(target, requestedSexTypes.get(Util.random.nextInt(requestedSexTypes.size())));
				}
			}
			if(foreplayPreference.get(target)==null) {
				foreplayPreference.put(target, foreplaySexTypes.get(Util.random.nextInt(foreplaySexTypes.size())));
			}
//			System.out.println("Foreplay: "+foreplayPreference.get(target).getPerformingSexArea().toString()+" "+foreplayPreference.get(target).getTargetedSexArea().toString());
		}

		mainSexPreference.put(target, null);
		if(!mainSexTypes.isEmpty()) {
			if(request!=null) {
				List<SexType> requestedSexTypes = new ArrayList<>(mainSexTypes);
				requestedSexTypes.removeIf((type) -> type.getTargetedSexArea()!=request);
				if(!requestedSexTypes.isEmpty()) {
					mainSexPreference.put(target, requestedSexTypes.get(Util.random.nextInt(requestedSexTypes.size())));
				}
			}
			if(mainSexPreference.get(target)==null) {
				mainSexPreference.put(target, mainSexTypes.get(Util.random.nextInt(mainSexTypes.size())));
			}
//			System.out.println("Main: "+mainSexPreference.get(target).getPerformingSexArea().toString()+" "+mainSexPreference.get(target).getTargetedSexArea().toString());
		}
	}

	public Set<SexPositionSlot> getSexPositionPreferences(GameCharacter target) {
		SexType targetSexPreference = this.getForeplayPreference(target);
		
		if(!Sex.isInForeplay()) {
			targetSexPreference = this.getMainSexPreference(target);
		}
		
		sexPositionPreferences.clear();
		
		if(targetSexPreference!=null) {
			if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.MOUTH))) {
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_ORAL_SITTING);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.TONGUE))) {
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.FACE_SITTING_ON_FACE);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_ORAL_SITTING);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.TONGUE))) {
				sexPositionPreferences.add(SexPositionSlot.FACE_SITTING_ON_FACE);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_ORAL_SITTING);
				
			}  else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.BREAST))
					|| targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.NIPPLE))
					|| targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.TONGUE))
					|| targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.TONGUE))) {
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.MOUTH, SexAreaPenetration.PENIS))){
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_KNEELING);
				sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_KNEELING);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.VAGINA))){
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND_ORAL);
				sexPositionPreferences.add(SexPositionSlot.FACE_SITTING_ON_BACK);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_KNEELING);
				sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_KNEELING);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.TONGUE, SexAreaOrifice.ANUS))){
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND_ORAL);
				sexPositionPreferences.add(SexPositionSlot.FACE_SITTING_ON_BACK);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_KNEELING);
				sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_KNEELING);
				
			} else if(targetSexPreference.getTargetedSexArea()==SexAreaOrifice.ANUS){
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.BREAST))
					|| targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.NIPPLE))) {
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.BREAST, SexAreaPenetration.PENIS))
					|| targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.NIPPLE, SexAreaPenetration.PENIS))) {
				sexPositionPreferences.add(SexPositionSlot.KNEELING_PERFORMING_ORAL);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.ANUS))) {
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_BOTTOM);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaPenetration.PENIS, SexAreaOrifice.VAGINA))) {
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_BOTTOM);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.ANUS, SexAreaPenetration.PENIS))) {
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_ON_ALL_FOURS);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_TOP);
				sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_FUCKED);
				
			} else if(targetSexPreference.equals(new SexType(SexParticipantType.NORMAL, SexAreaOrifice.VAGINA, SexAreaPenetration.PENIS))) {
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_ON_ALL_FOURS);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_TOP);
				sexPositionPreferences.add(SexPositionSlot.GLORY_HOLE_FUCKED);
				
			}
		}
		
		if(sexPositionPreferences.isEmpty()){ // If no preferences found, add 'standard' positions:
			if(Sex.isInForeplay()) {
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_BOTTOM);
				
			} else {
				sexPositionPreferences.add(SexPositionSlot.BACK_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.DOGGY_BEHIND);
				sexPositionPreferences.add(SexPositionSlot.FACE_TO_WALL_FACING_TARGET);
				sexPositionPreferences.add(SexPositionSlot.KNEELING_RECEIVING_ORAL);
				sexPositionPreferences.add(SexPositionSlot.SIXTY_NINE_TOP);
				sexPositionPreferences.add(SexPositionSlot.COWGIRL_RIDING);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_ON_BACK);
				sexPositionPreferences.add(SexPositionSlot.MISSIONARY_KNEELING_BETWEEN_LEGS);
				sexPositionPreferences.add(SexPositionSlot.CHAIR_BOTTOM);
			}
		}
		
		return sexPositionPreferences;
		
	}
	
	public boolean isWillingToRape(GameCharacter character) {
		return Main.game.isNonConEnabled()
				&& this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)!=FetishDesire.ONE_DISLIKE
				&& this.getFetishDesire(Fetish.FETISH_NON_CON_DOM)!=FetishDesire.ZERO_HATE;
	}

	/**
	 * Override to force this character to use a certain SexPace in sex. Return null to use default Pace calculations.
	 */
	public SexPace getSexPaceSubPreference(GameCharacter character){
		return null;
	}

	// The methods of theoretical sex paces should be applicable to all those branches of thought in which the essential features are expressible with fetishes, arousal, and lust.
	public SexPace getTheoreticalSexPaceSubPreference(GameCharacter character) {
		if(!isAttractedTo(character) || this.hasFetish(Fetish.FETISH_NON_CON_SUB)) {
			if(Main.game.isNonConEnabled()) {
				return SexPace.SUB_RESISTING;
			} else {
				return SexPace.SUB_NORMAL;
			}
		}
		
		if(hasStatusEffect(StatusEffect.WEATHER_STORM_VULNERABLE)) { // If they're vulnerable to arcane storms, they will always be eager during a storm:
			return SexPace.SUB_EAGER;
		}
		
		if (hasFetish(Fetish.FETISH_SUBMISSIVE) // Subs like being sub I guess ^^
				|| (hasFetish(Fetish.FETISH_PREGNANCY) && character.hasPenis() && hasVagina()) // Want to get pregnant
				|| (hasFetish(Fetish.FETISH_IMPREGNATION) && character.hasVagina() && hasPenis()) // Want to impregnate player
				) {
			return SexPace.SUB_EAGER;
		}
		
		return SexPace.SUB_NORMAL;
	}
	
	/**
	 * Override to force this character to use a certain SexPace in sex. Return null to use default Pace calculations.
	 */
	public SexPace getSexPaceDomPreference(){
		return null;
	}
	
	// Most people don't have time to master the very lewd details of theoretical sex paces.
	public SexPace getTheoreticalSexPaceDomPreference() {
		if(hasStatusEffect(StatusEffect.FETISH_PURE_VIRGIN) || (hasFetish(Fetish.FETISH_SUBMISSIVE) && !hasFetish(Fetish.FETISH_DOMINANT))) {
			return SexPace.DOM_GENTLE;
		}
		
		if(hasFetish(Fetish.FETISH_SADIST) || hasFetish(Fetish.FETISH_DOMINANT)) {
			return SexPace.DOM_ROUGH;
		}
		
		return SexPace.DOM_NORMAL;
	}
	
	public List<Class<?>> getUniqueSexClasses() {
		return new ArrayList<>();
	}

	/**
	 * Returns a description of how this npc reacts to item usage.
	 * @param item
	 * @return
	 */
	public String getItemUseEffects(AbstractItem item, GameCharacter user, GameCharacter target){
		// Player is using an item:
		if(user.isPlayer()) {
			// Player uses item on themselves:
			if(target.isPlayer()) {
				return Main.game.getPlayer().useItem(item, target, false);
				
			// Player uses item on NPC:
			} else {
				return "<p>"
							+ UtilText.parse(this, "You try to give [npc.name] the "+item.getName()+", but [npc.she] refuses to take it. You put the "+item.getName()+" back in your inventory.")
						+ "</p>";
			}
			
		// NPC is using an item:
		} else {
			return this.useItem(item, target, false);
		}
	}
	
	protected static String getItemUseEffectsAllowingUse(AbstractItem item, GameCharacter user, GameCharacter target) {
		// Player is using an item:
		if(user.isPlayer()){
				return Main.game.getPlayer().useItem(item, target, false);
		// NPC is using an item:
		} else {
			return user.useItem(item, target, false);
		}
	}

}
