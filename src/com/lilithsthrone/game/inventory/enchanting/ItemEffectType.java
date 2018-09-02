package com.lilithsthrone.game.inventory.enchanting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @since 0.1.7
 * @version 0.2.4
 * @author Innoxia
 */
public class ItemEffectType {
	
	public static AbstractItemEffectType DYE_BRUSH = new AbstractItemEffectType() {};

	// CLOTHING:
	
	public static AbstractItemEffectType CLOTHING = new AbstractItemEffectType() { };
	
	public static AbstractItemEffectType TATTOO = new AbstractItemEffectType() { };
	

	private static Map<AbstractItemEffectType, String> itemEffectTypeToIdMap = new HashMap<>();
	private static Map<String, AbstractItemEffectType> idToItemEffectTypeMap = new HashMap<>();
	private static List<AbstractItemEffectType> allEffectTypes = new ArrayList<>();
	
	public static void addAbstractItemEffectToIds(String id, AbstractItemEffectType itemEffectType) {
		allEffectTypes.add(itemEffectType);
		
		itemEffectTypeToIdMap.put(itemEffectType, id);
		idToItemEffectTypeMap.put(id, itemEffectType);
	}
	
	public static AbstractItemEffectType getItemEffectTypeFromId(String id) {
		return idToItemEffectTypeMap.get(id);
	}
	
	public static String getIdFromItemEffectType(AbstractItemEffectType itemEffectType) {
		return itemEffectTypeToIdMap.get(itemEffectType);
	}
	
	public static List<AbstractItemEffectType> getAllEffectTypes() {
		return allEffectTypes;
	}

	static {
		
		Field[] fields = ItemEffectType.class.getFields();
		
		for(Field f : fields){
			
			if (AbstractItemEffectType.class.isAssignableFrom(f.getType())) {
				
				AbstractItemEffectType iet;
				try {
					iet = ((AbstractItemEffectType) f.get(null));
					
					allEffectTypes.add(iet);
					
					// I feel like this is stupid :thinking:
					itemEffectTypeToIdMap.put(iet, f.getName());
					idToItemEffectTypeMap.put(f.getName(), iet);
					
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
			}
		}
  	    
	}
	
}
