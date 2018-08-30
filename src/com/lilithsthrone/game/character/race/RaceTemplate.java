package com.lilithsthrone.game.character.race;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.lilithsthrone.game.character.attributes.Attribute;
import com.lilithsthrone.game.character.attributes.AttributeRange;
import com.lilithsthrone.game.character.body.types.ArmType;
import com.lilithsthrone.game.character.body.types.AssType;
import com.lilithsthrone.game.character.body.types.BreastType;
import com.lilithsthrone.game.character.body.types.EarType;
import com.lilithsthrone.game.character.body.types.EyeType;
import com.lilithsthrone.game.character.body.types.FaceType;
import com.lilithsthrone.game.character.body.types.HairType;
import com.lilithsthrone.game.character.body.types.HornType;
import com.lilithsthrone.game.character.body.types.LegType;
import com.lilithsthrone.game.character.body.types.PenisType;
import com.lilithsthrone.game.character.body.types.SkinType;
import com.lilithsthrone.game.character.body.types.TailType;
import com.lilithsthrone.game.character.body.types.TentacleType;
import com.lilithsthrone.game.character.body.types.VaginaType;
import com.lilithsthrone.game.character.body.types.WingType;
import com.lilithsthrone.game.character.body.valueEnums.AreolaeSize;
import com.lilithsthrone.game.character.body.valueEnums.AssSize;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.body.valueEnums.BodySize;
import com.lilithsthrone.game.character.body.valueEnums.Capacity;
import com.lilithsthrone.game.character.body.valueEnums.ClitorisSize;
import com.lilithsthrone.game.character.body.valueEnums.CumProduction;
import com.lilithsthrone.game.character.body.valueEnums.CupSize;
import com.lilithsthrone.game.character.body.valueEnums.GenitalArrangement;
import com.lilithsthrone.game.character.body.valueEnums.HairLength;
import com.lilithsthrone.game.character.body.valueEnums.HornLength;
import com.lilithsthrone.game.character.body.valueEnums.Lactation;
import com.lilithsthrone.game.character.body.valueEnums.LipSize;
import com.lilithsthrone.game.character.body.valueEnums.Muscle;
import com.lilithsthrone.game.character.body.valueEnums.NippleShape;
import com.lilithsthrone.game.character.body.valueEnums.NippleSize;
import com.lilithsthrone.game.character.body.valueEnums.OrificeElasticity;
import com.lilithsthrone.game.character.body.valueEnums.OrificePlasticity;
import com.lilithsthrone.game.character.body.valueEnums.PenisGirth;
import com.lilithsthrone.game.character.body.valueEnums.PenisSize;
import com.lilithsthrone.game.character.body.valueEnums.TesticleSize;
import com.lilithsthrone.game.character.body.valueEnums.Wetness;
import com.lilithsthrone.game.character.body.valueEnums.WingSize;
import com.lilithsthrone.game.character.gender.Gender;
import com.lilithsthrone.game.character.persona.SexualOrientation;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Defines a race template as extracted from an XML definition file.
 * @author Funtacles
 */
public final class RaceTemplate {

	public final String id;
	public final String name, plural;


	// Attributes modified by this Trait:
	private HashMap<Attribute, AttributeRange> attributeModifiers;

	public final ArmType armType;
	public final AssType assType;
	public final BreastType breastType;
	public final FaceType faceType;
	public final EyeType eyeType;
	public final EarType earType;
	public final HairType hairType;
	public final LegType legType;
	public final SkinType skinType;
	public final List<HornType> hornTypes;
	public final PenisType penisType;
	public final PenisType penisSecondType;
	public final TailType tailType;
	public final TentacleType tentacleType;
	private VaginaType vaginaType;
	private WingType wingType;
	
	private BodyMaterial bodyMaterial;
	private GenitalArrangement genitalArrangement;
	
	private NippleShape maleNippleShape, femaleNippleShape;
	
	private int armRows,
			anusCapacity, anusWetness, maleAssSize, femaleAssSize, anusElasticity, anusPlasticity,
			maleHairLength, femaleHairLength,
			maleHornLength, femaleHornLength,
			noBreastSize, breastSize, maleLactationRate, femaleLactationRate, femaleBreastCapacity, maleBreastCapacity,
			femaleBreastElasticity, maleBreastElasticity, femaleBreastPlasticity, maleBreastPlasticity, maleNippleCountPerBreast, femaleNippleCountPerBreast, maleAreolaeSize, femaleAreolaeSize, maleNippleSize, femaleNippleSize,
			clitSize,
			maleHeight, femaleHeight, maleFemininity, femaleFemininity, maleBodySize, femaleBodySize, maleMuscle, femaleMuscle,
			maleLipSize, femaleLipSize,
			penisSize, penisSecondSize,
			penisGirth, penisSecondGirth,
			testicleSize, cumProduction, vaginaCapacity, vaginaWetness, vaginaElasticity, vaginaPlasticity, breastCountMale,
			breastCountFemale, testicleQuantity,
			maleWingSize, femaleWingSize;

	private RaceTemplate(String id, String name, String plural,
			HashMap<Attribute, AttributeRange> attributeModifiers,
			ArmType armType, int armRows,
			AssType assType, AssSize maleAssSize, AssSize femaleAssSize, Wetness anusWetness, Capacity anusCapacity, OrificeElasticity anusElasticity, OrificePlasticity anusPlasticity,
			BreastType breastType,
			CupSize maleBreastSize, int breastCountMale, Lactation maleLactationRate, Capacity maleBreastCapacity, OrificeElasticity maleBreastElasticity, OrificePlasticity maleBreastPlasticity,
				NippleSize maleNippleSize, NippleShape maleNippleShape, AreolaeSize maleAreolaeSize, int maleNippleCountPerBreast,
			CupSize femaleBreastSize, int breastCountFemale, Lactation femaleLactationRate, Capacity femaleBreastCapacity, OrificeElasticity femaleBreastElasticity, OrificePlasticity femaleBreastPlasticity,
				NippleSize femaleNippleSize, NippleShape femaleNippleShape, AreolaeSize femaleAreolaeSize, int femaleNippleCountPerBreast,
			int maleHeight, int maleFemininity, int maleBodySize, int maleMuscle,
			int femaleHeight, int femaleFemininity, int femaleBodySize, int femaleMuscle,
			EarType earType,
			EyeType eyeType,
			FaceType faceType, LipSize maleLipSize, LipSize femaleLipSize,
			HairType hairType, HairLength maleHairLength, HairLength femaleHairLength,
			LegType legType,
			SkinType skinType, BodyMaterial bodyMaterial,
			HornLength maleHornLength, HornLength femaleHornLength, List<HornType> hornTypes,
			PenisType penisType, PenisSize penisSize, PenisGirth penisGirth,
			PenisType penisSecondType, PenisSize penisSecondSize, PenisGirth penisSecondGirth,
			TesticleSize testicleSize, int testicleQuantity, CumProduction cumProduction,
			TailType tailType,
			TentacleType tentacleType,
			VaginaType vaginaType, Wetness vaginaWetness, Capacity vaginaCapacity, ClitorisSize clitSize, OrificeElasticity vaginaElasticity, OrificePlasticity vaginaPlasticity,
			WingType wingType, WingSize maleWingSize, WingSize femaleWingSize,
			GenitalArrangement genitalArrangement) {
		
		this.id = id;
		this.name = name;
		this.plural = plural;
				
		this.attributeModifiers = attributeModifiers;
		
		this.armType = armType;
		this.armRows = armRows;
		
		this.assType = assType;
		this.breastType = breastType;
		this.faceType = faceType;
		this.eyeType = eyeType;
		this.earType = earType;
		this.hairType = hairType;
		this.legType = legType;
		this.skinType = skinType;
		this.bodyMaterial = bodyMaterial;
		this.hornTypes = hornTypes;
		
		this.penisType = penisType;
		this.penisSecondType = penisSecondType;
		this.tailType = tailType;
		this.tentacleType = tentacleType;
		this.vaginaType = vaginaType;
		this.wingType = wingType;

		this.anusCapacity = anusCapacity.getMedianValue();
		this.anusWetness = anusWetness.getValue();
		this.maleAssSize = maleAssSize.getValue();
		this.femaleAssSize = femaleAssSize.getValue();
		this.anusElasticity = anusElasticity.getValue();
		this.anusPlasticity = anusPlasticity.getValue();
		
		this.maleHairLength = maleHairLength.getMedianValue();
		this.femaleHairLength = femaleHairLength.getMedianValue();

		this.maleHornLength = maleHornLength.getMedianValue();
		this.femaleHornLength = femaleHornLength.getMedianValue();
		
		this.noBreastSize = maleBreastSize.getMeasurement();
		this.breastCountMale = breastCountMale;
		this.maleLactationRate = maleLactationRate.getMedianValue();
		this.maleBreastCapacity = maleBreastCapacity.getMedianValue();
		this.maleBreastElasticity = maleBreastElasticity.getValue();
		this.maleBreastPlasticity = maleBreastPlasticity.getValue();
		this.maleNippleSize = maleNippleSize.getValue();
		this.maleNippleShape = maleNippleShape;
		this.maleAreolaeSize = maleAreolaeSize.getValue();
		this.maleNippleCountPerBreast = maleNippleCountPerBreast;
		
		this.breastSize = femaleBreastSize.getMeasurement();
		this.breastCountFemale = breastCountFemale;
		this.femaleLactationRate = femaleLactationRate.getMedianValue();
		this.femaleBreastCapacity = femaleBreastCapacity.getMedianValue();
		this.femaleBreastElasticity = femaleBreastElasticity.getValue();
		this.femaleBreastPlasticity = femaleBreastPlasticity.getValue();
		this.femaleNippleSize = femaleNippleSize.getValue();
		this.femaleNippleShape = femaleNippleShape;
		this.femaleAreolaeSize = femaleAreolaeSize.getValue();
		this.femaleNippleCountPerBreast = femaleNippleCountPerBreast;
		
		this.clitSize = clitSize.getMedianValue();
		
		this.maleHeight = maleHeight;
		this.maleFemininity = maleFemininity;
		this.maleBodySize = maleBodySize;
		this.maleMuscle = maleMuscle;
		
		this.femaleHeight = femaleHeight;
		this.femaleFemininity = femaleFemininity;
		this.femaleBodySize = femaleBodySize;
		this.femaleMuscle = femaleMuscle;
		
		this.maleLipSize = maleLipSize.getValue();
		this.femaleLipSize = femaleLipSize.getValue();
		
		this.penisSize = penisSize.getMedianValue();
		this.penisSecondSize = penisSecondSize.getMedianValue();

		this.penisGirth = penisGirth.getValue();
		this.penisSecondGirth = penisSecondGirth.getValue();
		
		this.testicleSize = testicleSize.getValue();
		this.cumProduction = cumProduction.getMedianValue();
		this.vaginaCapacity = vaginaCapacity.getMedianValue();
		this.vaginaWetness = vaginaWetness.getValue();
		this.vaginaElasticity = vaginaElasticity.getValue();
		this.vaginaPlasticity = vaginaPlasticity.getValue();
		this.testicleQuantity = testicleQuantity;
		
		this.maleWingSize = maleWingSize.getValue();
		this.femaleWingSize = femaleWingSize.getValue();
		
		this.genitalArrangement = genitalArrangement;

	}
	
	public SexualOrientation getSexualOrientation(Gender gender) {
		double chance = Math.random();
		
		if(gender.isFeminine()) {
			if(chance<0.15f) {
				return SexualOrientation.GYNEPHILIC;
			} else if (chance<0.5f) {
				return SexualOrientation.ANDROPHILIC;
			} else {
				return SexualOrientation.AMBIPHILIC;
			}
			
		} else {
			if(chance<0.10f) {
				return SexualOrientation.ANDROPHILIC;
			} else if (chance<0.5f) {
				return SexualOrientation.GYNEPHILIC;
			} else {
				return SexualOrientation.AMBIPHILIC;
			}
		}
	}
	
	public HashMap<Attribute, AttributeRange> getAttributeModifiers() {
		return attributeModifiers;
	}

	public ArmType getArmType() {
		return armType;
	}

	public AssType getAssType() {
		return assType;
	}

	public BreastType getBreastType() {
		return breastType;
	}

	public FaceType getFaceType() {
		return faceType;
	}

	public EyeType getEyeType() {
		return eyeType;
	}

	public EarType getEarType() {
		return earType;
	}

	public HairType getHairType() {
		return hairType;
	}
	
	/**
	 * @return true if this RacialBody requires FaceType to not be human in order to apply hair settings.
	 */
	public boolean isHairTypeLinkedToFaceType() {
		return false;
	}

	public LegType getLegType() {
		return legType;
	}

	public SkinType getSkinType() {
		return skinType;
	}
	
	public BodyMaterial getBodyMaterial() {
		return bodyMaterial;
	}

	/**
	 * @param includeTypeNONE Set as true if you want the returned HornType to possibly include HornType.NONE. (Will include NONE anyway if the list is empty.)
	 * @return A random HornType from this race's possible hornTypes.
	 */
	public HornType getRandomHornType(boolean includeTypeNONE) {
		List<HornType> hornList = new ArrayList<>(hornTypes);
		
		if(includeTypeNONE || hornTypes.size()==1) {
			return hornTypes.get(Util.random.nextInt(hornTypes.size()));
		} else {
			hornList.remove(HornType.NONE);
			return hornList.get(Util.random.nextInt(hornList.size()));
		}
	}
	
	public List<HornType> getHornType() {
		return hornTypes;
	}

	public PenisType getPenisType() {
		return penisType;
	}
	
	public PenisType getPenisSecondType() {
		return penisSecondType;
	}

	public TailType getTailType() {
		return tailType;
	}

	public TentacleType getTentacleType() {
		return tentacleType;
	}
	
	public VaginaType getVaginaType() {
		return vaginaType;
	}

	public WingType getWingType() {
		return wingType;
	}


	public int getArmRows() {
		return armRows;
	}
	
	public int getAnusCapacity() {
		return anusCapacity;
	}

	public int getAnusWetness() {
		return anusWetness;
	}

	public int getAnusElasticity() {
		return anusElasticity;
	}
	
	public int getAnusPlasticity() {
		return anusPlasticity;
	}

	public int getMaleHeight() {
		return maleHeight;
	}

	public int getMaleFemininity() {
		return maleFemininity;
	}

	public int getMaleMuscle() {
		return maleMuscle;
	}
	
	public int getMaleBodySize() {
		return maleBodySize;
	}

	public int getFemaleHeight() {
		return femaleHeight;
	}

	public int getFemaleFemininity() {
		return femaleFemininity;
	}
	
	public int getFemaleBodySize() {
		return femaleBodySize;
	}

	public int getFemaleMuscle() {
		return femaleMuscle;
	}

	public int getMaleAssSize() {
		return maleAssSize;
	}

	public int getFemaleAssSize() {
		return femaleAssSize;
	}

	public int getMaleHairLength() {
		return maleHairLength;
	}

	public int getFemaleHairLength() {
		return femaleHairLength;
	}

	public int getMaleHornLength() {
		return maleHornLength;
	}

	public int getFemaleHornLength() {
		return femaleHornLength;
	}

	public int getNoBreastSize() {
		return noBreastSize;
	}

	public int getBreastSize() {
		return breastSize;
	}

	public int getMaleLactationRate() {
		return maleLactationRate;
	}

	public int getFemaleLactationRate() {
		return femaleLactationRate;
	}

	public int getFemaleBreastElasticity() {
		return femaleBreastElasticity;
	}

	public int getMaleBreastElasticity() {
		return maleBreastElasticity;
	}

	public int getFemaleBreastPlasticity() {
		return femaleBreastPlasticity;
	}

	public int getMaleBreastPlasticity() {
		return maleBreastPlasticity;
	}

	public int getFemaleBreastCapacity() {
		return femaleBreastCapacity;
	}

	public int getMaleBreastCapacity() {
		return maleBreastCapacity;
	}

	public int getMaleNippleSize() {
		return maleNippleSize;
	}

	public int getFemaleNippleSize() {
		return femaleNippleSize;
	}

	public NippleShape getMaleNippleShape() {
		return maleNippleShape;
	}

	public NippleShape getFemaleNippleShape() {
		return femaleNippleShape;
	}

	public int getMaleNippleCountPerBreast() {
		return maleNippleCountPerBreast;
	}

	public int getFemaleNippleCountPerBreast() {
		return femaleNippleCountPerBreast;
	}

	public int getMaleAreolaeSize() {
		return maleAreolaeSize;
	}

	public int getFemaleAreolaeSize() {
		return femaleAreolaeSize;
	}

	public int getClitSize() {
		return clitSize;
	}

	public int getPenisSize() {
		return penisSize;
	}
	
	public int getPenisSecondSize() {
		return penisSecondSize;
	}
	
	public int getPenisGirth() {
		return penisGirth;
	}
	
	public int getPenisSecondGirth() {
		return penisSecondGirth;
	}

	public int getTesticleSize() {
		return testicleSize;
	}

	public int getCumProduction() {
		return cumProduction;
	}

	public int getVaginaCapacity() {
		return vaginaCapacity;
	}

	public int getVaginaWetness() {
		return vaginaWetness;
	}

	public int getVaginaElasticity() {
		return vaginaElasticity;
	}
	
	public int getVaginaPlasticity() {
		return vaginaPlasticity;
	}

	public int getBreastCountMale() {
		return breastCountMale;
	}

	public int getBreastCountFemale() {
		return breastCountFemale;
	}

	public int getTesticleQuantity() {
		return testicleQuantity;
	}

	public int getMaleLipSize() {
		return maleLipSize;
	}

	public int getFemaleLipSize() {
		return femaleLipSize;
	}

	public int getMaleWingSize() {
		return maleWingSize;
	}

	public int getFemaleWingSize() {
		return femaleWingSize;
	}

	public GenitalArrangement getGenitalArrangement() {
        return genitalArrangement;
    }
	
	private static Map<String, RaceTemplate> templates = new HashMap<String, RaceTemplate>();



    public static void importTemplates() throws Exception {
		File folder = new File("content/race-templates");
		List<Exception> exceptions = new ArrayList<Exception>();
		
		if(folder.exists()) {
			FilenameFilter textFilter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".xml");
				}
			};

			DocumentBuilder dBuilder;
				dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			for(File file : folder.listFiles(textFilter)) {
				try {
					Document doc = dBuilder.parse(file);
					
					Element root = doc.getDocumentElement();

					String id = root.getAttribute("id");
					String name = root.getAttribute("name");
					String plural = root.getAttribute("plural-name");

					RaceTemplate template = new RaceTemplate(
						id, name, plural,
						Util.newHashMapOfValues(
							new Value<Attribute, AttributeRange>(Attribute.MAJOR_PHYSIQUE, new AttributeRange(5f, 15f)),
							new Value<Attribute, AttributeRange>(Attribute.MAJOR_ARCANE, new AttributeRange(0f, 0f))),
						ArmType.HUMAN, 1,
						AssType.HUMAN, AssSize.TWO_SMALL, AssSize.FOUR_LARGE, Wetness.ZERO_DRY, Capacity.ONE_EXTREMELY_TIGHT, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT,
						BreastType.HUMAN,
						CupSize.FLAT, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.ZERO_TINY, 1,
						CupSize.C, 1, Lactation.ZERO_NONE, Capacity.ZERO_IMPENETRABLE, OrificeElasticity.THREE_FLEXIBLE, OrificePlasticity.THREE_RESILIENT, NippleSize.ZERO_TINY, NippleShape.NORMAL, AreolaeSize.TWO_BIG, 1,
						180, 25, BodySize.TWO_AVERAGE.getMedianValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMedianValue(),
						170, 75, BodySize.TWO_AVERAGE.getMinimumValue(), Muscle.ONE_LIGHTLY_MUSCLED.getMinimumValue(),
						EarType.HUMAN,
						EyeType.HUMAN,
						FaceType.HUMAN, LipSize.ONE_AVERAGE, LipSize.TWO_FULL,
						HairType.HUMAN, HairLength.TWO_SHORT, HairLength.FOUR_MID_BACK,
						LegType.HUMAN,
						SkinType.HUMAN, BodyMaterial.FLESH,
						HornLength.ZERO_TINY, HornLength.ZERO_TINY, Util.newArrayListOfValues(HornType.NONE),
						PenisType.HUMAN, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
						PenisType.NONE, PenisSize.TWO_AVERAGE, PenisGirth.TWO_AVERAGE,
						TesticleSize.TWO_AVERAGE, 2, CumProduction.THREE_AVERAGE,
						TailType.NONE,
						TentacleType.NONE,
						VaginaType.HUMAN, Wetness.TWO_MOIST, Capacity.TWO_TIGHT, ClitorisSize.ZERO_AVERAGE, OrificeElasticity.FOUR_LIMBER, OrificePlasticity.THREE_RESILIENT,
						WingType.NONE, WingSize.ZERO_TINY, WingSize.ZERO_TINY,
						GenitalArrangement.NORMAL);

					templates.put(template.id, template);
					
				} catch(Exception ex) {
					exceptions.add(ex);
				}
			}
		}
    }
}