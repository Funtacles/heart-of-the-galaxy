package com.lilithsthrone.game.slavery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.lilithsthrone.game.character.CharacterUtils;
import com.lilithsthrone.game.character.body.CoverableArea;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.eventLog.SlaveryEventLogEntry;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexAreaOrifice;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.XMLSaving;
import com.lilithsthrone.utils.Vector2i;
import com.lilithsthrone.world.Cell;
import com.lilithsthrone.world.WorldType;

/**
 * A class to handle all slave-related mechanics.
 * 
 * @since 0.1.87
 * @version 0.2.5
 * @author Innoxia
 */
public class SlaveryUtil implements XMLSaving {
	
	private Map<SlaveJob, List<NPC>> slavesAtJob;
	private List<NPC> slavesResting;
	private int generatedIncome;
	private int generatedUpkeep;
	
	private List<MilkingRoom> milkingRooms;
	
	// Slave income:
	private Map<NPC, Integer> slaveDailyIncome;
	
	public SlaveryUtil() {
		slavesAtJob = new HashMap<>();
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.put(job, new ArrayList<>());
		}
		
		slavesResting = new ArrayList<>();
		generatedIncome = 0;
		generatedUpkeep = 0;
		
		milkingRooms = new ArrayList<>();
		
		slaveDailyIncome = new HashMap<>();
	}

	public Element saveAsXML(Element parentElement, Document doc) {
		Element element = doc.createElement("slavery");
		parentElement.appendChild(element);
		
		CharacterUtils.addAttribute(doc, element, "generatedIncome", String.valueOf(Main.game.getSlaveryUtil().getGeneratedIncome()));
		CharacterUtils.addAttribute(doc, element, "generatedUpkeep", String.valueOf(Main.game.getSlaveryUtil().getGeneratedUpkeep()));
		
		for(MilkingRoom room : this.getMilkingRooms()) {
			room.saveAsXML(element, doc);
		}
		
		return element;
	}
	
	public static SlaveryUtil loadFromXML(Element parentElement, Document doc) {
		try {
			SlaveryUtil slaveryUtil = new SlaveryUtil();
			
			slaveryUtil.setGeneratedIncome(Integer.valueOf(parentElement.getAttribute("generatedIncome")));
			slaveryUtil.setGeneratedUpkeep(Integer.valueOf(parentElement.getAttribute("generatedUpkeep")));
			
			NodeList milkingRoomElements = parentElement.getElementsByTagName("milkingRoom");
			for(int i=0; i<milkingRoomElements.getLength(); i++){
				Element e = ((Element)milkingRoomElements.item(i));
				
				MilkingRoom room = MilkingRoom.loadFromXML(e, doc);
				
				if(slaveryUtil.getMilkingRoom(room.getWorldType(), room.getLocation())==null) {
					slaveryUtil.addMilkingRoom(room);
				}
			}
			
			return slaveryUtil;
			
		} catch(Exception ex) {
			System.err.println("Warning: SlaveryUtil failed to import!");
			return null;
		}
	}
	
	
	private void clearSlavesJobTracking() {
		for(SlaveJob job : SlaveJob.values()) {
			slavesAtJob.get(job).clear();
		}
		slavesResting.clear();
	}
	
	public void performHourlyUpdate(int day, int hour) {
		clearSlavesJobTracking();
		
		// First need to set correct jobs:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave = (NPC) Main.game.getNPCById(id);
			if(Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
			if(slave.getWorkHours()[hour]) {
				slave.getSlaveJob().sendToWorkLocation(slave);
				slavesAtJob.get(slave.getSlaveJob()).add(slave);
				
			} else {
				slave.setLocation(slave.getHomeWorldLocation(), slave.getHomeLocation(), false);
				slavesResting.add(slave);
			}
		}
		
		// Now can apply changes and generate events based on who else is present in the job:
		for(String id : Main.game.getPlayer().getSlavesOwned()) {
			NPC slave = (NPC) Main.game.getNPCById(id);
			
			if(slave==null || Main.game.getPlayer().hasCompanion(slave)) {
				continue;
			}
			
			slave.incrementAffection(slave.getOwner(), slave.getHourlyAffectionChange(hour));
			slave.incrementObedience(slave.getHourlyObedienceChange(hour), false);
			
			// If at work:
			if(slave.getWorkHours()[hour]) {
				// Get paid for hour's work:
				if(slave.getSlaveJob()!=SlaveJob.MILKING) {
					int income = slave.getSlaveJob().getFinalHourlyIncomeAfterModifiers(slave);
					generatedIncome += income;
					incrementSlaveDailyIncome(slave, income);
				}
				// Overworked effect:
				if(slave.hasStatusEffect(StatusEffect.OVERWORKED)) {
					slave.incrementAffection(slave.getOwner(), -0.1f);
				}
				
			} else {
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_MASTURBATE)) {
					slave.setLastTimeHadSex((day*24*60l) + hour*60l, true);
				}
			}
			
			// ***** EVENTS: ***** //
			
			// Washing body:
			if(slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_BODY)
					&& !slave.getWorkHours()[hour]
					&& !slave.getDirtySlots().isEmpty()) {
				SlaveryEventLogEntry entry = new SlaveryEventLogEntry(hour,
						slave,
						SlaveEvent.WASHED_BODY,
						null,
						true);
				
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_ANUS)) {
					entry.addTag(SlaveEventTag.WASHED_BODY_ANAL_CREAMPIE, slave, true);
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_VAGINA)) {
					entry.addTag(SlaveEventTag.WASHED_BODY_VAGINAL_CREAMPIE, slave, true);
				}
				if(slave.hasStatusEffect(StatusEffect.CREAMPIE_NIPPLES)) {
					entry.addTag(SlaveEventTag.WASHED_BODY_NIPPLE_CREAMPIE, slave, true);
				}
				
				Main.game.addSlaveryEvent(day, slave, entry);
			}
			
			// Washing clothes:
			if((slave.hasStatusEffect(StatusEffect.CLOTHING_CUM) || !slave.getDirtySlots().isEmpty())
					&& !slave.getWorkHours()[hour]
					&& slave.hasSlavePermissionSetting(SlavePermissionSetting.CLEANLINESS_WASH_CLOTHES)) {
				Main.game.addSlaveryEvent(day, slave, new SlaveryEventLogEntry(hour,
						slave,
						SlaveEvent.WASHED_CLOTHES,
						Util.newArrayListOfValues(SlaveEventTag.WASHED_CLOTHES),
						true));
			}
			
			// Events:
			boolean eventAdded = false;
			SlaveryEventLogEntry entry = null;
			// Interaction events:
			if(slavesAtJob.get(slave.getSlaveJob()).size()>1 || slave.getSlaveJob()==SlaveJob.IDLE) {
				if(Math.random()<0.25f) {
					entry = generateNPCInteractionEvent(day, hour, slave, slavesAtJob.get(slave.getSlaveJob()));
					if(entry!=null) {
						Main.game.addSlaveryEvent(day, slave, entry);
						eventAdded = true;
					}
				}
			}
			// Standard events:
			if(!eventAdded) {
				if(Math.random()<0.05f || slave.getSlaveJob()==SlaveJob.MILKING || (Math.random()<0.5f && (slave.getSlaveJob()==SlaveJob.PUBLIC_STOCKS))) {
					List<SlaveryEventLogEntry> entries = generateEvents(hour, slave);
					for(SlaveryEventLogEntry e : entries) {
						Main.game.addSlaveryEvent(day, slave, e);
						eventAdded = true;
					}
				}
			}
			
			if(hour%24==0) { // At the start of a new day:
				
				SlaveryEventLogEntry dailyEntry = new SlaveryEventLogEntry(hour,
						slave,
						SlaveEvent.DAILY_UPDATE,
						null,
						true);
				
				// Payments:
				if(slaveDailyIncome.containsKey(slave)) {
					dailyEntry.addExtraEffect("[style.boldGood(Earned)] "+UtilText.formatAsMoney(slaveDailyIncome.get(slave)));
				}
				
				// Muscle:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_FORBIDDEN) && slave.getMuscleValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS_LARGE, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_REST) && slave.getMuscleValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_LOSS, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_TRAINING) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.EXERCISE_BODY_BUILDING) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_MUSCLE_GAIN_LARGE, slave, true);
				}
				
				// Body size:
				if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET_EXTREME) && slave.getBodySizeValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS_LARGE, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_DIET) && slave.getMuscleValue()>0) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_LOSS, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_PLUS) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN, slave, true);
					
				} else if(slave.hasSlavePermissionSetting(SlavePermissionSetting.FOOD_LAVISH) && slave.getMuscleValue()<100) {
					dailyEntry.addTag(SlaveEventTag.DAILY_BODY_SIZE_GAIN_LARGE, slave, true);
				}
				
				if(dailyEntry.getTags()!=null || dailyEntry.getExtraEffects()!=null) {
					Main.game.addSlaveryEvent(day, slave, dailyEntry);
				}
				
				slave.resetSlaveFlags();
			}
		}
		
		if(hour%24==0) { // Reset daily income tracking:
			slaveDailyIncome.clear();
			// Rooms:
			for(Cell c : SlaveryManagementDialogue.getImportantCells()) {
				generatedUpkeep += c.getPlace().getUpkeep();
			}
		}
		
	}

	/**
	 * @param minute Time at which this event is happening.
	 * @param slave The slave to calculate an event for.
	 */
	private List<SlaveryEventLogEntry> generateEvents(int hour, NPC slave) {
		
		SlaveJob job = slave.getSlaveJob();

		List<SlaveryEventLogEntry> events = new ArrayList<>();
		
		if(slave.getWorkHours()[hour] && job != SlaveJob.IDLE) {
			switch (job) {
				case CLEANING:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_CLEANING, true));
					return events;
					
				case KITCHEN:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_COOKING, true));
					return events;
					
				case LIBRARY:
					events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_LIBRARIAN, true));
					return events;
					
				case MILKING:
					int income = 0;

					Cell c = MilkingRoom.getMilkingCell(slave, false);
					MilkingRoom room = this.getMilkingRoom(c.getType(), c.getLocation());
					
					if(slave.getBreastRawStoredMilkValue()>0 && !slave.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_MILK_DISABLE)) {
						float milked = MilkingRoom.getActualMilkPerHour(slave);
						if(milked < slave.getBreastRawStoredMilkValue() && milked < MilkingRoom.getMaximumMilkPerHour(slave)) {
							milked = Math.min(slave.getBreastRawStoredMilkValue(), MilkingRoom.getMaximumMilkPerHour(slave));
						}
						slave.incrementBreastStoredMilk(-milked);
						
						if(milked>0) {
							if(room.isAutoSellMilk()) {
								income = Math.max(1, (int) (milked * slave.getMilk().getValuePerMl()));
								generatedIncome += income;
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_MILK_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_MILK_SOLD),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold")),
										true));
								
							} else {
								room.incrementMilkStorage(slave.getMilk(), milked);
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_MILK_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_MILK_MILKED),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] added to storage."),
										true));
							}
						}
					}
					if(slave.hasPenisIgnoreDildo() && slave.getPenisRawStoredCumValue()>0 && !slave.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_CUM_DISABLE)) {
						int milked = MilkingRoom.getActualCumPerHour(slave);

						if(milked>0) {
							if(room.isAutoSellCum()) {
								income = Math.max(1, (int) (milked * slave.getCum().getValuePerMl()));
								generatedIncome += income;
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_CUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_CUM_SOLD),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold")),
										true));
							
							} else {
								room.incrementCumStorage(slave.getCum(), milked);
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_CUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_CUM_MILKED),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] added to storage."),
										true));
							}
						}
					}
					if(slave.hasVagina() && !slave.getSlaveJobSettings().contains(SlaveJobSetting.MILKING_GIRLCUM_DISABLE)) {
						int milked = MilkingRoom.getActualGirlcumPerHour(slave);
						
						if(milked>0) {
							if(room.isAutoSellGirlcum()) {
								income = Math.max(1, (int) (milked * slave.getGirlcum().getValuePerMl()));
								generatedIncome += income;
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_GIRLCUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_GIRLCUM_SOLD),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] milked: +"+UtilText.formatAsMoney(income, "bold")),
										true));
							
							} else {
								room.incrementGirlcumStorage(slave.getGirlcum(), milked);
								
								events.add(new SlaveryEventLogEntry(hour, slave,
										SlaveEvent.JOB_GIRLCUM_MILKED,
										Util.newArrayListOfValues(
												SlaveEventTag.JOB_GIRLCUM_MILKED),
										Util.newArrayListOfValues("[style.boldGood("+milked+"ml)] added to storage."),
										true));
							}
						}
					}
					return events;
				case IDLE:
					// Can not reach :3
					break;
			}
			
		} else { // Slave is resting:
			events.add(new SlaveryEventLogEntry(hour, slave, SlaveEvent.JOB_IDLE, true));
		}

		return events;
	}
	
	/**
	 * 
	 * @param hour Pass in hour of the day
	 * @param slave
	 * @param otherSlavesPresent
	 * @return
	 */
	public static SlaveryEventLogEntry generateNPCInteractionEvent(int day, int hour, NPC slave, List<NPC> otherSlavesPresent) {
		
		if(slave.getSlaveJob()==SlaveJob.PUBLIC_STOCKS) {
			return null;
		}
		
		Collections.shuffle(otherSlavesPresent);
		for(NPC npc : otherSlavesPresent) {
			if(!npc.equals(slave)) {
				if(slave.getLastTimeHadSex()+24*60<Main.game.getMinutesPassed()) { // They only want sex once a day, to stop the logs from being flooded
					if(slave.isAttractedTo(npc) && npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_RECEIVE_SLAVES) && slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_INITIATE_SLAVES)) {
						
						boolean canImpregnate =
								slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE)
								&& npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED)
								&& slave.hasPenis()
								&& slave.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
								&& npc.hasVagina()
								&& npc.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
						
						boolean canBeImpregnated = 
								!slave.isVaginaVirgin()
								&& slave.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATED)
								&& npc.hasSlavePermissionSetting(SlavePermissionSetting.SEX_IMPREGNATE)
								&& npc.hasPenis()
								&& npc.isAbleToAccessCoverableArea(CoverableArea.PENIS, true)
								&& slave.hasVagina()
								&& slave.isAbleToAccessCoverableArea(CoverableArea.VAGINA, true);
						
						boolean impregnationAttempt = false, gettingPregnantAttempt = false;
						
						// Apply sex effects:
						if(canImpregnate) {
							npc.ingestFluid(slave, slave.getCumType(), SexAreaOrifice.VAGINA, slave.getPenisRawOrgasmCumQuantity(), slave.getCum().getFluidModifiers());
							slave.applyOrgasmCumEffect();
							npc.setVaginaVirgin(false);
							impregnationAttempt = true;
						}
						if(canBeImpregnated) {
							slave.ingestFluid(npc, npc.getCumType(), SexAreaOrifice.VAGINA, npc.getPenisRawOrgasmCumQuantity(), npc.getCum().getFluidModifiers());
							npc.applyOrgasmCumEffect();
							slave.setVaginaVirgin(false);
							gettingPregnantAttempt = true;
						}
						slave.setLastTimeHadSex((day*24*60l) + hour*60l, true);
						
						
						switch(slave.getSlaveJob()) {
							case CLEANING:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"While dusting one of the first-floor corridors, [npc1.name] caught sight of [npc2.name],"
												+ " and couldn't resist pulling [npc2.herHim] into an empty room and giving [npc2.herHim] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
								
							case IDLE: //TODO
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"[npc1.name] gave [npc2.name] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
							case KITCHEN:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"While working in the kitchen, [npc1.name] saw [npc2.name] enter the pantry alone,"
														+ " and couldn't resist following [npc2.herHim] inside, before locking the door and giving [npc2.herHim] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
								
							case LIBRARY:
								return new SlaveryEventLogEntry(hour,
										slave,
										SlaveEvent.SLAVE_SEX,
										null,
										Util.newArrayListOfValues(UtilText.parse(slave, npc,
												"[npc1.Name] pulled [npc2.name] behind one of the shelves in the Library, before giving [npc2.herHim] a "+slave.getTheoreticalSexPaceDomPreference().getName()+" fucking."
												+ (impregnationAttempt?"<br/>[style.colourSex([npc2.Name] might have gotten pregnant!)]":"")
												+ (gettingPregnantAttempt?"<br/>[style.colourSex([npc1.Name] might have gotten pregnant!)]":""))),
										true);
							case PUBLIC_STOCKS:
								//TODO 
							case MILKING:
								//TODO 
								break;
						}
					}
				}
			}
		}
		
//		TODO
//		boolean silence = slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_SILENCE);
//		boolean crawling = slave.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_CRAWLING);
		
		return null;
	}
	
	
	public MilkingRoom getMilkingRoom(WorldType worldType, Vector2i location) {
		for(MilkingRoom room : getMilkingRooms()) {
			if(room.getWorldType() == worldType && room.getLocation().equals(location)) {
				return room;
			}
		}
		return null;
	}
	
	public List<MilkingRoom> getMilkingRooms() {
		return milkingRooms;
	}
	
	public boolean addMilkingRoom(MilkingRoom milkingRoom) {
		return milkingRooms.add(milkingRoom);
	}
	
	public boolean removeMilkingRoom(MilkingRoom milkingRoom) {
		return milkingRooms.remove(milkingRoom);
	}

	public void payOutBalance() {
		Main.game.getPlayer().incrementMoney(getGeneratedBalance());
		
		generatedIncome = 0;
		generatedUpkeep = 0;
	}
	
	public int getGeneratedBalance() {
		return generatedIncome - generatedUpkeep;
	}
	
	public int getGeneratedIncome() {
		return generatedIncome;
	}
	
	public void setGeneratedIncome(int generatedIncome) {
		this.generatedIncome = generatedIncome;
	}

	public void setGeneratedUpkeep(int generatedUpkeep) {
		this.generatedUpkeep = generatedUpkeep;
	}

	public int getGeneratedUpkeep() {
		return generatedUpkeep;
	}
	
	private void incrementSlaveDailyIncome(NPC slave, int increment) {
		slaveDailyIncome.putIfAbsent(slave, 0);
		slaveDailyIncome.put(slave, slaveDailyIncome.get(slave)+increment);
	}

	public Map<SlaveJob, List<NPC>> getSlavesAtJob() {
		return slavesAtJob;
	}

	public List<NPC> getSlavesResting() {
		return slavesResting;
	}
	
}
