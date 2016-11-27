package com.billingapp.rest.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "patientprocedures")
public class PatientProcedureDTO {

	@Id
	@GeneratedValue
	@Column(name = "PatientProcedureID")
	private int patientProcedureId;

	@ManyToOne
	@JoinColumn(name = "PatientID")
	private PatientDTO patient;
	
	@ManyToOne
	@JoinColumn(name = "ProcedureID")
	private ProcedureDTO procedureDTO;
	
	@Column(name="TreatmentSequenceID")
	private Integer treamnentSequence;
	
	@Column(name="ProcedureDate")
	private Date procedureDate;
	
	@OneToMany(mappedBy="patientProcedure")
	@Cascade(CascadeType.DELETE)
	private Set<ProcedureAreasDTO> procedureAreas;
	
	@Column(name="PriorComplication")	private boolean PriorComplication;
	@Column(name="ComplicationDescription")	private String ComplicationDescription;
	@Column(name="BLTCreamUsedYes")	private boolean BLTCreamUsedYes;
	@Column(name="BLTCreamUsedNo")	private boolean BLTCreamUsedNo;
	@Column(name="Cheeks")	private boolean Cheeks;
	@Column(name="Nose")	private boolean Nose;
	@Column(name="Forehead")	private boolean Forehead;
	@Column(name="Neck")	private boolean Neck;
	@Column(name="Calf")	private boolean Calf;
	@Column(name="MidFaceRight")	private boolean MidFaceRight;
	@Column(name="MidFaceLeft")	private boolean MidFaceLeft;
	@Column(name="Face")	private boolean Face;
	@Column(name="Bikiniline")	private boolean Bikiniline;
	@Column(name="Underarms")	private boolean Underarms;
	@Column(name="Ears")	private boolean Ears;
	@Column(name="UpperLip")	private boolean UpperLip;
	@Column(name="Bikini")	private boolean Bikini;
	@Column(name="Back")	private boolean Back;
	@Column(name="Chin")	private boolean Chin;
	@Column(name="Leg")	private boolean Leg;
	@Column(name="Thigh")	private boolean Thigh;
	@Column(name="Chest")	private boolean Chest;
	@Column(name="OtherArea")	private boolean OtherArea;
	@Column(name="OtherAreaDescription")	private String OtherAreaDescription;
	@Column(name="Energy14")	private boolean Energy14;
	@Column(name="Energy15")	private boolean Energy15;
	@Column(name="Energy16")	private boolean Energy16;
	@Column(name="Energy17")	private boolean Energy17;
	@Column(name="Energy30")	private boolean Energy30;
	@Column(name="Energy45")	private boolean Energy45;
	@Column(name="Energy60")	private boolean Energy60;
	@Column(name="Energy75")	private boolean Energy75;
	@Column(name="Energy90")	private boolean Energy90;
	@Column(name="Energy105")	private boolean Energy105;
	@Column(name="Energy100")	private boolean Energy100;
	@Column(name="Energy125")	private boolean Energy125;
	@Column(name="Energy150")	private boolean Energy150;
	@Column(name="Energy175")	private boolean Energy175;
	@Column(name="Energy200")	private boolean Energy200;
	@Column(name="PPS1")	private boolean PPS1;
	@Column(name="PPS15")	private boolean PPS15;
	@Column(name="PPS2")	private boolean PPS2;
	@Column(name="PPS25")	private boolean PPS25;
	@Column(name="PPS3")	private boolean PPS3;
	@Column(name="PPSMax")	private boolean PPSMax;
	@Column(name="SpotSize25")	private boolean SpotSize25;
	@Column(name="SpotSize5")	private boolean SpotSize5;
	@Column(name="SpotSize7")	private boolean SpotSize7;
	@Column(name="SpotSize10")	private boolean SpotSize10;
	@Column(name="Power3")	private boolean Power3;
	@Column(name="RR5")	private boolean RR5;
	@Column(name="RR6")	private boolean RR6;
	@Column(name="RR7")	private boolean RR7;
	@Column(name="NumberofPulses")	private Integer NumberofPulses;
	@Column(name="Energy25")	private boolean Energy25;
	@Column(name="Energy40")	private boolean Energy40;
	@Column(name="Dry")	private boolean Dry;
	@Column(name="Normal")	private boolean Normal;
	@Column(name="Oily")	private boolean Oily;
	@Column(name="CleansedWith")	private String CleansedWith;
	@Column(name="PrepPad")	private boolean PrepPad;
	@Column(name="Solution")	private boolean Solution;
	@Column(name="AirDry")	private boolean AirDry;
	@Column(name="FanDry")	private boolean FanDry;
	@Column(name="ClothDry")	private boolean ClothDry;
	@Column(name="Gold")	private boolean Gold;
	@Column(name="Silver")	private boolean Silver;
	@Column(name="Amber")	private boolean Amber;
	@Column(name="Blue")	private boolean Blue;
	@Column(name="Clear")	private boolean Clear;
	@Column(name="Vac25")	private boolean Vac25;
	@Column(name="Vac30")	private boolean Vac30;
	@Column(name="Vac35")	private boolean Vac35;
	@Column(name="Vac40")	private boolean Vac40;
	@Column(name="Vac45")	private boolean Vac45;
	@Column(name="Vac50")	private boolean Vac50;
	@Column(name="knob20")	private boolean knob20;
	@Column(name="knob30")	private boolean knob30;
	@Column(name="knob40")	private boolean knob40;
	@Column(name="knob50")	private boolean knob50;
	@Column(name="ForheadNormal")	private boolean ForheadNormal;
	@Column(name="ForheadSensitive")	private boolean ForheadSensitive;
	@Column(name="ForheadRedness")	private boolean ForheadRedness;
	@Column(name="ForheadPetechiae")	private boolean ForheadPetechiae;
	@Column(name="ForheadExcoriation")	private boolean ForheadExcoriation;
	@Column(name="ForheadNone")	private boolean ForheadNone;
	@Column(name="Proceduresdone")	private boolean Proceduresdone;
	@Column(name="SkinTypeI")	private boolean SkinTypeI;
	@Column(name="SkinTypeII")	private boolean SkinTypeII;
	@Column(name="SkinTypeIII")	private boolean SkinTypeIII;
	@Column(name="SkinTypeIV")	private boolean SkinTypeIV;
	@Column(name="SkinTypeV")	private boolean SkinTypeV;
	@Column(name="SkinTypeVI")	private boolean SkinTypeVI;
	@Column(name="DoctorClinician")	private String DoctorClinician;
	@Column(name="30msecPreCool")	private boolean thirtymsecPreCool;
	@Column(name="25msecPreCool")	private boolean twentyFivemsecPreCool;
	@Column(name="20msecPreCool")	private boolean twentymsecPreCool;
	@Column(name="PostCooling")	private boolean PostCooling;
	@Column(name="10mmHandPiece")	private boolean tenmmHandPiece;
	@Column(name="PostTreatement")	private String PostTreatement;
	@Column(name="Instructions")	private String Instructions;
	@Column(name="Agent")	private Integer Agent;
	@Column(name="Lot")	private String Lot;
	@Column(name="ExpDate")	private Date ExpDate;
	@Column(name="lowerlipyn")	private boolean lowerlipyn;
	@Column(name="lowerlipml")	private Integer lowerlipml;
	@Column(name="vermilionyn")	private boolean vermilionyn;
	@Column(name="vermilionml")	private Integer vermilionml;
	@Column(name="nasalfoldyn")	private boolean nasalfoldyn;
	@Column(name="nasalfoldml")	private Integer nasalfoldml;
	@Column(name="cheekyn")	private boolean cheekyn;
	@Column(name="cheekml")	private Integer cheekml;
	@Column(name="MelomentFoldyn")	private boolean MelomentFoldyn;
	@Column(name="MelomentFoldml")	private Integer MelomentFoldml;
	@Column(name="otherml")	private Integer otherml;
	@Column(name="upperlipml")	private Integer upperlipml;
	@Column(name="forheadml")	private Integer forheadml;
	@Column(name="glabellayn")	private boolean glabellayn;
	@Column(name="glabellaynml")	private Integer glabellaynml;
	@Column(name="crowsfeetyn")	private boolean crowsfeetyn;
	@Column(name="crowsfeetml")	private Integer crowsfeetml;
	@Column(name="platysmayn")	private boolean platysmayn;
	@Column(name="platysmaml")	private Integer platysmaml;
	@Column(name="sel")	private String sel;
	@Column(name="medhx")	private String medhx;
	@Column(name="eyebrowyn")	private boolean eyebrowyn;
	@Column(name="eyebrowml")	private Integer eyebrowml;
	@Column(name="ForheadUnitPerInj")	private Integer ForheadUnitPerInj;
	@Column(name="ForheadNumberofInj")	private Integer ForheadNumberofInj;
	@Column(name="GlabellaUnitPerInj")	private Integer GlabellaUnitPerInj;
	@Column(name="GlabellaNumberofInj")	private Integer GlabellaNumberofInj;
	@Column(name="CrowsFeetUnitPerInj")	private Integer CrowsFeetUnitPerInj;
	@Column(name="CrowsFeetNumberofInj")	private Integer CrowsFeetNumberofInj;
	@Column(name="EyebrowUnitPerInj")	private Integer EyebrowUnitPerInj;
	@Column(name="EyebrowNumberofInj")	private Integer EyebrowNumberofInj;
	@Column(name="UpperLipUnitPerInj")	private Integer UpperLipUnitPerInj;
	@Column(name="UpperLipNumberofInj")	private Integer UpperLipNumberofInj;
	@Column(name="PlatysmaUnitPerInj")	private Integer PlatysmaUnitPerInj;
	@Column(name="PlatysmaNumberofInj")	private Integer PlatysmaNumberofInj;
	@Column(name="OtherAreaUnitPerInj")	private Integer OtherAreaUnitPerInj;
	@Column(name="OtherAreaNumberofInj")	private Integer OtherAreaNumberofInj;
	@Column(name="Dilution001")	private boolean Dilution001;
	@Column(name="Dilutionother")	private boolean Dilutionother;
	@Column(name="DilutionAmount")	private String DilutionAmount;
	@Column(name="ProcedureConsequences")	private String ProcedureConsequences;
	@Column(name="ProcedureConsentName")	private String ProcedureConsentName;
	@Column(name="Assistant")	private String Assistant;
	@Column(name="preopdx")	private String preopdx;
	@Column(name="postopdx")	private String postopdx;
	@Column(name="procedureMisc")	private String procedureMisc;
	@Column(name="Anehesia")	private String Anehesia;
	@Column(name="History")	private String History;
	@Column(name="Steps")	private String Steps;
	@Column(name="Complications")	private String Complications;
	@Column(name="AtLeast18")	private boolean AtLeast18;
	@Column(name="ExfolientUsed")	private String ExfolientUsed;
	@Column(name="ExfolientPercentage")	private Integer ExfolientPercentage;
	@Column(name="ExfolientTime")	private String ExfolientTime;
	@Column(name="Enzyme")	private String Enzyme;
	@Column(name="EnzymeLength")	private String EnzymeLength;
	@Column(name="Steam")	private boolean Steam;
	@Column(name="Mask")	private String Mask;
	@Column(name="OtherProduct1")	private String OtherProduct1;
	@Column(name="OtherProduct2")	private String OtherProduct2;
	@Column(name="OtherProduct3")	private String OtherProduct3;
	@Column(name="Sunblock")	private String Sunblock;
	@Column(name="Comedones")	private String Comedones;
	@Column(name="Milia")	private String Milia;
	@Column(name="Papeles")	private String Papeles;
	@Column(name="Pustules")	private String Pustules;
	@Column(name="Extractions")	private String Extractions;
	@Column(name="DateofLastTreatment")	private Date DateofLastTreatment;
	@Column(name="UsingHomecare")	private boolean UsingHomecare;
	@Column(name="ProductsNeeded")	private String ProductsNeeded;
	@Column(name="BotoxYN")	private boolean BotoxYN;
	@Column(name="HyalorunicYN")	private boolean HyalorunicYN;
	@Column(name="MesoYN")	private boolean MesoYN;
	@Column(name="Energy400")	private boolean Energy400;
	@Column(name="Energy600")	private boolean Energy600;
	@Column(name="LVR")	private boolean LVR;
	@Column(name="Hands")	private boolean Hands;
	@Column(name="NewProcedure")	private boolean NewProcedure;
	@Column(name="Arms")	private boolean Arms;
	public int getPatientProcedureId() {
		return patientProcedureId;
	}
	public void setPatientProcedureId(int patientProcedureId) {
		this.patientProcedureId = patientProcedureId;
	}
	public PatientDTO getPatient() {
		return patient;
	}
	public void setPatient(PatientDTO patient) {
		this.patient = patient;
	}
	public ProcedureDTO getProcedureDTO() {
		return procedureDTO;
	}
	public void setProcedureDTO(ProcedureDTO procedureDTO) {
		this.procedureDTO = procedureDTO;
	}
	public Integer getTreamnentSequence() {
		return treamnentSequence;
	}
	public void setTreamnentSequence(Integer treamnentSequence) {
		this.treamnentSequence = treamnentSequence;
	}
	public Date getProcedureDate() {
		return procedureDate;
	}
	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}
	public boolean isPriorComplication() {
		return PriorComplication;
	}
	public void setPriorComplication(boolean priorComplication) {
		PriorComplication = priorComplication;
	}
	public String getComplicationDescription() {
		return ComplicationDescription;
	}
	public void setComplicationDescription(String complicationDescription) {
		ComplicationDescription = complicationDescription;
	}
	public boolean isBLTCreamUsedYes() {
		return BLTCreamUsedYes;
	}
	public void setBLTCreamUsedYes(boolean bLTCreamUsedYes) {
		BLTCreamUsedYes = bLTCreamUsedYes;
	}
	public boolean isBLTCreamUsedNo() {
		return BLTCreamUsedNo;
	}
	public void setBLTCreamUsedNo(boolean bLTCreamUsedNo) {
		BLTCreamUsedNo = bLTCreamUsedNo;
	}
	public boolean isCheeks() {
		return Cheeks;
	}
	public void setCheeks(boolean cheeks) {
		Cheeks = cheeks;
	}
	public boolean isNose() {
		return Nose;
	}
	public void setNose(boolean nose) {
		Nose = nose;
	}
	public boolean isForehead() {
		return Forehead;
	}
	public void setForehead(boolean forehead) {
		Forehead = forehead;
	}
	public boolean isNeck() {
		return Neck;
	}
	public void setNeck(boolean neck) {
		Neck = neck;
	}
	public boolean isCalf() {
		return Calf;
	}
	public void setCalf(boolean calf) {
		Calf = calf;
	}
	public boolean isMidFaceRight() {
		return MidFaceRight;
	}
	public void setMidFaceRight(boolean midFaceRight) {
		MidFaceRight = midFaceRight;
	}
	public boolean isMidFaceLeft() {
		return MidFaceLeft;
	}
	public void setMidFaceLeft(boolean midFaceLeft) {
		MidFaceLeft = midFaceLeft;
	}
	public boolean isFace() {
		return Face;
	}
	public void setFace(boolean face) {
		Face = face;
	}
	public boolean isUpperLip() {
		return UpperLip;
	}
	public void setUpperLip(boolean upperLip) {
		UpperLip = upperLip;
	}
	public boolean isBikini() {
		return Bikini;
	}
	public void setBikini(boolean bikini) {
		Bikini = bikini;
	}
	public boolean isBack() {
		return Back;
	}
	public void setBack(boolean back) {
		Back = back;
	}
	public boolean isChin() {
		return Chin;
	}
	public void setChin(boolean chin) {
		Chin = chin;
	}
	public boolean isLeg() {
		return Leg;
	}
	public void setLeg(boolean leg) {
		Leg = leg;
	}
	public boolean isThigh() {
		return Thigh;
	}
	public void setThigh(boolean thigh) {
		Thigh = thigh;
	}
	public boolean isChest() {
		return Chest;
	}
	public void setChest(boolean chest) {
		Chest = chest;
	}
	public boolean isOtherArea() {
		return OtherArea;
	}
	public void setOtherArea(boolean otherArea) {
		OtherArea = otherArea;
	}
	public String getOtherAreaDescription() {
		return OtherAreaDescription;
	}
	public void setOtherAreaDescription(String otherAreaDescription) {
		OtherAreaDescription = otherAreaDescription;
	}
	public boolean isEnergy14() {
		return Energy14;
	}
	public void setEnergy14(boolean energy14) {
		Energy14 = energy14;
	}
	public boolean isEnergy15() {
		return Energy15;
	}
	public void setEnergy15(boolean energy15) {
		Energy15 = energy15;
	}
	public boolean isEnergy16() {
		return Energy16;
	}
	public void setEnergy16(boolean energy16) {
		Energy16 = energy16;
	}
	public boolean isEnergy17() {
		return Energy17;
	}
	public void setEnergy17(boolean energy17) {
		Energy17 = energy17;
	}
	public boolean isEnergy30() {
		return Energy30;
	}
	public void setEnergy30(boolean energy30) {
		Energy30 = energy30;
	}
	public boolean isEnergy45() {
		return Energy45;
	}
	public void setEnergy45(boolean energy45) {
		Energy45 = energy45;
	}
	public boolean isEnergy60() {
		return Energy60;
	}
	public void setEnergy60(boolean energy60) {
		Energy60 = energy60;
	}
	public boolean isEnergy75() {
		return Energy75;
	}
	public void setEnergy75(boolean energy75) {
		Energy75 = energy75;
	}
	public boolean isEnergy90() {
		return Energy90;
	}
	public void setEnergy90(boolean energy90) {
		Energy90 = energy90;
	}
	public boolean isEnergy105() {
		return Energy105;
	}
	public void setEnergy105(boolean energy105) {
		Energy105 = energy105;
	}
	public boolean isEnergy100() {
		return Energy100;
	}
	public void setEnergy100(boolean energy100) {
		Energy100 = energy100;
	}
	public boolean isEnergy125() {
		return Energy125;
	}
	public void setEnergy125(boolean energy125) {
		Energy125 = energy125;
	}
	public boolean isEnergy150() {
		return Energy150;
	}
	public void setEnergy150(boolean energy150) {
		Energy150 = energy150;
	}
	public boolean isEnergy175() {
		return Energy175;
	}
	public void setEnergy175(boolean energy175) {
		Energy175 = energy175;
	}
	public boolean isEnergy200() {
		return Energy200;
	}
	public void setEnergy200(boolean energy200) {
		Energy200 = energy200;
	}
	public boolean isPPS1() {
		return PPS1;
	}
	public void setPPS1(boolean pPS1) {
		PPS1 = pPS1;
	}
	public boolean isPPS15() {
		return PPS15;
	}
	public void setPPS15(boolean pPS15) {
		PPS15 = pPS15;
	}
	public boolean isPPS2() {
		return PPS2;
	}
	public void setPPS2(boolean pPS2) {
		PPS2 = pPS2;
	}
	public boolean isPPS25() {
		return PPS25;
	}
	public void setPPS25(boolean pPS25) {
		PPS25 = pPS25;
	}
	public boolean isPPS3() {
		return PPS3;
	}
	public void setPPS3(boolean pPS3) {
		PPS3 = pPS3;
	}
	public boolean isPPSMax() {
		return PPSMax;
	}
	public void setPPSMax(boolean pPSMax) {
		PPSMax = pPSMax;
	}
	public boolean isSpotSize25() {
		return SpotSize25;
	}
	public void setSpotSize25(boolean spotSize25) {
		SpotSize25 = spotSize25;
	}
	public boolean isSpotSize5() {
		return SpotSize5;
	}
	public void setSpotSize5(boolean spotSize5) {
		SpotSize5 = spotSize5;
	}
	public boolean isSpotSize7() {
		return SpotSize7;
	}
	public void setSpotSize7(boolean spotSize7) {
		SpotSize7 = spotSize7;
	}
	public boolean isSpotSize10() {
		return SpotSize10;
	}
	public void setSpotSize10(boolean spotSize10) {
		SpotSize10 = spotSize10;
	}
	public boolean isPower3() {
		return Power3;
	}
	public void setPower3(boolean power3) {
		Power3 = power3;
	}
	public boolean isRR5() {
		return RR5;
	}
	public void setRR5(boolean rR5) {
		RR5 = rR5;
	}
	public boolean isRR6() {
		return RR6;
	}
	public void setRR6(boolean rR6) {
		RR6 = rR6;
	}
	public boolean isRR7() {
		return RR7;
	}
	public void setRR7(boolean rR7) {
		RR7 = rR7;
	}
	public Integer getNumberofPulses() {
		return NumberofPulses;
	}
	public void setNumberofPulses(Integer numberofPulses) {
		NumberofPulses = numberofPulses;
	}
	public boolean isEnergy25() {
		return Energy25;
	}
	public void setEnergy25(boolean energy25) {
		Energy25 = energy25;
	}
	public boolean isEnergy40() {
		return Energy40;
	}
	public void setEnergy40(boolean energy40) {
		Energy40 = energy40;
	}
	public boolean isDry() {
		return Dry;
	}
	public void setDry(boolean dry) {
		Dry = dry;
	}
	public boolean isNormal() {
		return Normal;
	}
	public void setNormal(boolean normal) {
		Normal = normal;
	}
	public boolean isOily() {
		return Oily;
	}
	public void setOily(boolean oily) {
		Oily = oily;
	}
	public String getCleansedWith() {
		return CleansedWith;
	}
	public void setCleansedWith(String cleansedWith) {
		CleansedWith = cleansedWith;
	}
	public boolean isPrepPad() {
		return PrepPad;
	}
	public void setPrepPad(boolean prepPad) {
		PrepPad = prepPad;
	}
	public boolean isSolution() {
		return Solution;
	}
	public void setSolution(boolean solution) {
		Solution = solution;
	}
	public boolean isAirDry() {
		return AirDry;
	}
	public void setAirDry(boolean airDry) {
		AirDry = airDry;
	}
	public boolean isFanDry() {
		return FanDry;
	}
	public void setFanDry(boolean fanDry) {
		FanDry = fanDry;
	}
	public boolean isClothDry() {
		return ClothDry;
	}
	public void setClothDry(boolean clothDry) {
		ClothDry = clothDry;
	}
	public boolean isGold() {
		return Gold;
	}
	public void setGold(boolean gold) {
		Gold = gold;
	}
	public boolean isSilver() {
		return Silver;
	}
	public void setSilver(boolean silver) {
		Silver = silver;
	}
	public boolean isAmber() {
		return Amber;
	}
	public void setAmber(boolean amber) {
		Amber = amber;
	}
	public boolean isBlue() {
		return Blue;
	}
	public void setBlue(boolean blue) {
		Blue = blue;
	}
	public boolean isClear() {
		return Clear;
	}
	public void setClear(boolean clear) {
		Clear = clear;
	}
	public boolean isVac25() {
		return Vac25;
	}
	public void setVac25(boolean vac25) {
		Vac25 = vac25;
	}
	public boolean isVac30() {
		return Vac30;
	}
	public void setVac30(boolean vac30) {
		Vac30 = vac30;
	}
	public boolean isVac35() {
		return Vac35;
	}
	public void setVac35(boolean vac35) {
		Vac35 = vac35;
	}
	public boolean isVac40() {
		return Vac40;
	}
	public void setVac40(boolean vac40) {
		Vac40 = vac40;
	}
	public boolean isVac45() {
		return Vac45;
	}
	public void setVac45(boolean vac45) {
		Vac45 = vac45;
	}
	public boolean isVac50() {
		return Vac50;
	}
	public void setVac50(boolean vac50) {
		Vac50 = vac50;
	}
	public boolean isKnob20() {
		return knob20;
	}
	public void setKnob20(boolean knob20) {
		this.knob20 = knob20;
	}
	public boolean isKnob30() {
		return knob30;
	}
	public void setKnob30(boolean knob30) {
		this.knob30 = knob30;
	}
	public boolean isKnob40() {
		return knob40;
	}
	public void setKnob40(boolean knob40) {
		this.knob40 = knob40;
	}
	public boolean isKnob50() {
		return knob50;
	}
	public void setKnob50(boolean knob50) {
		this.knob50 = knob50;
	}
	public boolean isForheadNormal() {
		return ForheadNormal;
	}
	public void setForheadNormal(boolean forheadNormal) {
		ForheadNormal = forheadNormal;
	}
	public boolean isForheadSensitive() {
		return ForheadSensitive;
	}
	public void setForheadSensitive(boolean forheadSensitive) {
		ForheadSensitive = forheadSensitive;
	}
	public boolean isForheadRedness() {
		return ForheadRedness;
	}
	public void setForheadRedness(boolean forheadRedness) {
		ForheadRedness = forheadRedness;
	}
	public boolean isForheadPetechiae() {
		return ForheadPetechiae;
	}
	public void setForheadPetechiae(boolean forheadPetechiae) {
		ForheadPetechiae = forheadPetechiae;
	}
	public boolean isForheadExcoriation() {
		return ForheadExcoriation;
	}
	public void setForheadExcoriation(boolean forheadExcoriation) {
		ForheadExcoriation = forheadExcoriation;
	}
	public boolean isForheadNone() {
		return ForheadNone;
	}
	public void setForheadNone(boolean forheadNone) {
		ForheadNone = forheadNone;
	}
	public boolean isProceduresdone() {
		return Proceduresdone;
	}
	public void setProceduresdone(boolean proceduresdone) {
		Proceduresdone = proceduresdone;
	}
	public boolean isSkinTypeI() {
		return SkinTypeI;
	}
	public void setSkinTypeI(boolean skinTypeI) {
		SkinTypeI = skinTypeI;
	}
	public boolean isSkinTypeII() {
		return SkinTypeII;
	}
	public void setSkinTypeII(boolean skinTypeII) {
		SkinTypeII = skinTypeII;
	}
	public boolean isSkinTypeIII() {
		return SkinTypeIII;
	}
	public void setSkinTypeIII(boolean skinTypeIII) {
		SkinTypeIII = skinTypeIII;
	}
	public boolean isSkinTypeIV() {
		return SkinTypeIV;
	}
	public void setSkinTypeIV(boolean skinTypeIV) {
		SkinTypeIV = skinTypeIV;
	}
	public boolean isSkinTypeV() {
		return SkinTypeV;
	}
	public void setSkinTypeV(boolean skinTypeV) {
		SkinTypeV = skinTypeV;
	}
	public boolean isSkinTypeVI() {
		return SkinTypeVI;
	}
	public void setSkinTypeVI(boolean skinTypeVI) {
		SkinTypeVI = skinTypeVI;
	}
	public String getDoctorClinician() {
		return DoctorClinician;
	}
	public void setDoctorClinician(String doctorClinician) {
		DoctorClinician = doctorClinician;
	}
	public boolean isThirtymsecPreCool() {
		return thirtymsecPreCool;
	}
	public void setThirtymsecPreCool(boolean thirtymsecPreCool) {
		this.thirtymsecPreCool = thirtymsecPreCool;
	}
	public boolean isTwentyFivemsecPreCool() {
		return twentyFivemsecPreCool;
	}
	public void setTwentyFivemsecPreCool(boolean twentyFivemsecPreCool) {
		this.twentyFivemsecPreCool = twentyFivemsecPreCool;
	}
	public boolean isTwentymsecPreCool() {
		return twentymsecPreCool;
	}
	public void setTwentymsecPreCool(boolean twentymsecPreCool) {
		this.twentymsecPreCool = twentymsecPreCool;
	}
	public boolean isPostCooling() {
		return PostCooling;
	}
	public void setPostCooling(boolean postCooling) {
		PostCooling = postCooling;
	}
	public boolean isTenmmHandPiece() {
		return tenmmHandPiece;
	}
	public void setTenmmHandPiece(boolean tenmmHandPiece) {
		this.tenmmHandPiece = tenmmHandPiece;
	}
	public String getPostTreatement() {
		return PostTreatement;
	}
	public void setPostTreatement(String postTreatement) {
		PostTreatement = postTreatement;
	}
	public String getInstructions() {
		return Instructions;
	}
	public void setInstructions(String instructions) {
		Instructions = instructions;
	}
	public Integer getAgent() {
		return Agent;
	}
	public void setAgent(Integer agent) {
		Agent = agent;
	}
	public String getLot() {
		return Lot;
	}
	public void setLot(String lot) {
		Lot = lot;
	}
	public Date getExpDate() {
		return ExpDate;
	}
	public void setExpDate(Date expDate) {
		ExpDate = expDate;
	}
	public boolean isLowerlipyn() {
		return lowerlipyn;
	}
	public void setLowerlipyn(boolean lowerlipyn) {
		this.lowerlipyn = lowerlipyn;
	}
	public Integer getLowerlipml() {
		return lowerlipml;
	}
	public void setLowerlipml(Integer lowerlipml) {
		this.lowerlipml = lowerlipml;
	}
	public boolean isVermilionyn() {
		return vermilionyn;
	}
	public void setVermilionyn(boolean vermilionyn) {
		this.vermilionyn = vermilionyn;
	}
	public Integer getVermilionml() {
		return vermilionml;
	}
	public void setVermilionml(Integer vermilionml) {
		this.vermilionml = vermilionml;
	}
	public boolean isNasalfoldyn() {
		return nasalfoldyn;
	}
	public void setNasalfoldyn(boolean nasalfoldyn) {
		this.nasalfoldyn = nasalfoldyn;
	}
	public Integer getNasalfoldml() {
		return nasalfoldml;
	}
	public void setNasalfoldml(Integer nasalfoldml) {
		this.nasalfoldml = nasalfoldml;
	}
	public boolean isCheekyn() {
		return cheekyn;
	}
	public void setCheekyn(boolean cheekyn) {
		this.cheekyn = cheekyn;
	}
	public Integer getCheekml() {
		return cheekml;
	}
	public void setCheekml(Integer cheekml) {
		this.cheekml = cheekml;
	}
	public boolean isMelomentFoldyn() {
		return MelomentFoldyn;
	}
	public void setMelomentFoldyn(boolean melomentFoldyn) {
		MelomentFoldyn = melomentFoldyn;
	}
	public Integer getMelomentFoldml() {
		return MelomentFoldml;
	}
	public void setMelomentFoldml(Integer melomentFoldml) {
		MelomentFoldml = melomentFoldml;
	}
	public Integer getOtherml() {
		return otherml;
	}
	public void setOtherml(Integer otherml) {
		this.otherml = otherml;
	}
	public Integer getUpperlipml() {
		return upperlipml;
	}
	public void setUpperlipml(Integer upperlipml) {
		this.upperlipml = upperlipml;
	}
	public Integer getForheadml() {
		return forheadml;
	}
	public void setForheadml(Integer forheadml) {
		this.forheadml = forheadml;
	}
	public boolean isGlabellayn() {
		return glabellayn;
	}
	public void setGlabellayn(boolean glabellayn) {
		this.glabellayn = glabellayn;
	}
	public Integer getGlabellaynml() {
		return glabellaynml;
	}
	public void setGlabellaynml(Integer glabellaynml) {
		this.glabellaynml = glabellaynml;
	}
	public boolean isCrowsfeetyn() {
		return crowsfeetyn;
	}
	public void setCrowsfeetyn(boolean crowsfeetyn) {
		this.crowsfeetyn = crowsfeetyn;
	}
	public Integer getCrowsfeetml() {
		return crowsfeetml;
	}
	public void setCrowsfeetml(Integer crowsfeetml) {
		this.crowsfeetml = crowsfeetml;
	}
	public boolean isPlatysmayn() {
		return platysmayn;
	}
	public void setPlatysmayn(boolean platysmayn) {
		this.platysmayn = platysmayn;
	}
	public Integer getPlatysmaml() {
		return platysmaml;
	}
	public void setPlatysmaml(Integer platysmaml) {
		this.platysmaml = platysmaml;
	}
	public String getSel() {
		return sel;
	}
	public void setSel(String sel) {
		this.sel = sel;
	}
	public String getMedhx() {
		return medhx;
	}
	public void setMedhx(String medhx) {
		this.medhx = medhx;
	}
	public boolean isEyebrowyn() {
		return eyebrowyn;
	}
	public void setEyebrowyn(boolean eyebrowyn) {
		this.eyebrowyn = eyebrowyn;
	}
	public Integer getEyebrowml() {
		return eyebrowml;
	}
	public void setEyebrowml(Integer eyebrowml) {
		this.eyebrowml = eyebrowml;
	}
	public Integer getForheadUnitPerInj() {
		return ForheadUnitPerInj;
	}
	public void setForheadUnitPerInj(Integer forheadUnitPerInj) {
		ForheadUnitPerInj = forheadUnitPerInj;
	}
	public Integer getForheadNumberofInj() {
		return ForheadNumberofInj;
	}
	public void setForheadNumberofInj(Integer forheadNumberofInj) {
		ForheadNumberofInj = forheadNumberofInj;
	}
	public Integer getGlabellaUnitPerInj() {
		return GlabellaUnitPerInj;
	}
	public void setGlabellaUnitPerInj(Integer glabellaUnitPerInj) {
		GlabellaUnitPerInj = glabellaUnitPerInj;
	}
	public Integer getGlabellaNumberofInj() {
		return GlabellaNumberofInj;
	}
	public void setGlabellaNumberofInj(Integer glabellaNumberofInj) {
		GlabellaNumberofInj = glabellaNumberofInj;
	}
	public Integer getCrowsFeetUnitPerInj() {
		return CrowsFeetUnitPerInj;
	}
	public void setCrowsFeetUnitPerInj(Integer crowsFeetUnitPerInj) {
		CrowsFeetUnitPerInj = crowsFeetUnitPerInj;
	}
	public Integer getCrowsFeetNumberofInj() {
		return CrowsFeetNumberofInj;
	}
	public void setCrowsFeetNumberofInj(Integer crowsFeetNumberofInj) {
		CrowsFeetNumberofInj = crowsFeetNumberofInj;
	}
	public Integer getEyebrowUnitPerInj() {
		return EyebrowUnitPerInj;
	}
	public void setEyebrowUnitPerInj(Integer eyebrowUnitPerInj) {
		EyebrowUnitPerInj = eyebrowUnitPerInj;
	}
	public Integer getEyebrowNumberofInj() {
		return EyebrowNumberofInj;
	}
	public void setEyebrowNumberofInj(Integer eyebrowNumberofInj) {
		EyebrowNumberofInj = eyebrowNumberofInj;
	}
	public Integer getUpperLipUnitPerInj() {
		return UpperLipUnitPerInj;
	}
	public void setUpperLipUnitPerInj(Integer upperLipUnitPerInj) {
		UpperLipUnitPerInj = upperLipUnitPerInj;
	}
	public Integer getUpperLipNumberofInj() {
		return UpperLipNumberofInj;
	}
	public void setUpperLipNumberofInj(Integer upperLipNumberofInj) {
		UpperLipNumberofInj = upperLipNumberofInj;
	}
	public Integer getPlatysmaUnitPerInj() {
		return PlatysmaUnitPerInj;
	}
	public void setPlatysmaUnitPerInj(Integer platysmaUnitPerInj) {
		PlatysmaUnitPerInj = platysmaUnitPerInj;
	}
	public Integer getPlatysmaNumberofInj() {
		return PlatysmaNumberofInj;
	}
	public void setPlatysmaNumberofInj(Integer platysmaNumberofInj) {
		PlatysmaNumberofInj = platysmaNumberofInj;
	}
	public Integer getOtherAreaUnitPerInj() {
		return OtherAreaUnitPerInj;
	}
	public void setOtherAreaUnitPerInj(Integer otherAreaUnitPerInj) {
		OtherAreaUnitPerInj = otherAreaUnitPerInj;
	}
	public Integer getOtherAreaNumberofInj() {
		return OtherAreaNumberofInj;
	}
	public void setOtherAreaNumberofInj(Integer otherAreaNumberofInj) {
		OtherAreaNumberofInj = otherAreaNumberofInj;
	}
	public boolean isDilution001() {
		return Dilution001;
	}
	public void setDilution001(boolean dilution001) {
		Dilution001 = dilution001;
	}
	public boolean isDilutionother() {
		return Dilutionother;
	}
	public void setDilutionother(boolean dilutionother) {
		Dilutionother = dilutionother;
	}
	public String getDilutionAmount() {
		return DilutionAmount;
	}
	public void setDilutionAmount(String dilutionAmount) {
		DilutionAmount = dilutionAmount;
	}
	public String getProcedureConsequences() {
		return ProcedureConsequences;
	}
	public void setProcedureConsequences(String procedureConsequences) {
		ProcedureConsequences = procedureConsequences;
	}
	public String getProcedureConsentName() {
		return ProcedureConsentName;
	}
	public void setProcedureConsentName(String procedureConsentName) {
		ProcedureConsentName = procedureConsentName;
	}
	public String getAssistant() {
		return Assistant;
	}
	public void setAssistant(String assistant) {
		Assistant = assistant;
	}
	public String getPreopdx() {
		return preopdx;
	}
	public void setPreopdx(String preopdx) {
		this.preopdx = preopdx;
	}
	public String getPostopdx() {
		return postopdx;
	}
	public void setPostopdx(String postopdx) {
		this.postopdx = postopdx;
	}
	public String getProcedureMisc() {
		return procedureMisc;
	}
	public void setProcedureMisc(String procedureMisc) {
		this.procedureMisc = procedureMisc;
	}
	public String getAnehesia() {
		return Anehesia;
	}
	public void setAnehesia(String anehesia) {
		Anehesia = anehesia;
	}
	public String getHistory() {
		return History;
	}
	public void setHistory(String history) {
		History = history;
	}
	public String getSteps() {
		return Steps;
	}
	public void setSteps(String steps) {
		Steps = steps;
	}
	public String getComplications() {
		return Complications;
	}
	public void setComplications(String complications) {
		Complications = complications;
	}
	public boolean isAtLeast18() {
		return AtLeast18;
	}
	public void setAtLeast18(boolean atLeast18) {
		AtLeast18 = atLeast18;
	}
	public String getExfolientUsed() {
		return ExfolientUsed;
	}
	public void setExfolientUsed(String exfolientUsed) {
		ExfolientUsed = exfolientUsed;
	}
	public Integer getExfolientPercentage() {
		return ExfolientPercentage;
	}
	public void setExfolientPercentage(Integer exfolientPercentage) {
		ExfolientPercentage = exfolientPercentage;
	}
	public String getExfolientTime() {
		return ExfolientTime;
	}
	public void setExfolientTime(String exfolientTime) {
		ExfolientTime = exfolientTime;
	}
	public String getEnzyme() {
		return Enzyme;
	}
	public void setEnzyme(String enzyme) {
		Enzyme = enzyme;
	}
	public String getEnzymeLength() {
		return EnzymeLength;
	}
	public void setEnzymeLength(String enzymeLength) {
		EnzymeLength = enzymeLength;
	}
	public boolean isSteam() {
		return Steam;
	}
	public void setSteam(boolean steam) {
		Steam = steam;
	}
	public String getMask() {
		return Mask;
	}
	public void setMask(String mask) {
		Mask = mask;
	}
	public String getOtherProduct1() {
		return OtherProduct1;
	}
	public void setOtherProduct1(String otherProduct1) {
		OtherProduct1 = otherProduct1;
	}
	public String getOtherProduct2() {
		return OtherProduct2;
	}
	public void setOtherProduct2(String otherProduct2) {
		OtherProduct2 = otherProduct2;
	}
	public String getOtherProduct3() {
		return OtherProduct3;
	}
	public void setOtherProduct3(String otherProduct3) {
		OtherProduct3 = otherProduct3;
	}
	public String getSunblock() {
		return Sunblock;
	}
	public void setSunblock(String sunblock) {
		Sunblock = sunblock;
	}
	public String getComedones() {
		return Comedones;
	}
	public void setComedones(String comedones) {
		Comedones = comedones;
	}
	public String getMilia() {
		return Milia;
	}
	public void setMilia(String milia) {
		Milia = milia;
	}
	public String getPapeles() {
		return Papeles;
	}
	public void setPapeles(String papeles) {
		Papeles = papeles;
	}
	public String getPustules() {
		return Pustules;
	}
	public void setPustules(String pustules) {
		Pustules = pustules;
	}
	public String getExtractions() {
		return Extractions;
	}
	public void setExtractions(String extractions) {
		Extractions = extractions;
	}
	public Date getDateofLastTreatment() {
		return DateofLastTreatment;
	}
	public void setDateofLastTreatment(Date dateofLastTreatment) {
		DateofLastTreatment = dateofLastTreatment;
	}
	public boolean isUsingHomecare() {
		return UsingHomecare;
	}
	public void setUsingHomecare(boolean usingHomecare) {
		UsingHomecare = usingHomecare;
	}
	public String getProductsNeeded() {
		return ProductsNeeded;
	}
	public void setProductsNeeded(String productsNeeded) {
		ProductsNeeded = productsNeeded;
	}
	public boolean isBotoxYN() {
		return BotoxYN;
	}
	public void setBotoxYN(boolean botoxYN) {
		BotoxYN = botoxYN;
	}
	public boolean isHyalorunicYN() {
		return HyalorunicYN;
	}
	public void setHyalorunicYN(boolean hyalorunicYN) {
		HyalorunicYN = hyalorunicYN;
	}
	public boolean isMesoYN() {
		return MesoYN;
	}
	public void setMesoYN(boolean mesoYN) {
		MesoYN = mesoYN;
	}
	public boolean isEnergy400() {
		return Energy400;
	}
	public void setEnergy400(boolean energy400) {
		Energy400 = energy400;
	}
	public boolean isEnergy600() {
		return Energy600;
	}
	public void setEnergy600(boolean energy600) {
		Energy600 = energy600;
	}
	public boolean isLVR() {
		return LVR;
	}
	public void setLVR(boolean lVR) {
		LVR = lVR;
	}
	public boolean isHands() {
		return Hands;
	}
	public void setHands(boolean hands) {
		Hands = hands;
	}
	public boolean isNewProcedure() {
		return NewProcedure;
	}
	public void setNewProcedure(boolean newProcedure) {
		NewProcedure = newProcedure;
	}
	public boolean isArms() {
		return Arms;
	}
	public void setArms(boolean arms) {
		Arms = arms;
	}
	public Set<ProcedureAreasDTO> getProcedureAreas() {
		return procedureAreas;
	}
	public void setProcedureAreas(Set<ProcedureAreasDTO> procedureAreas) {
		this.procedureAreas = procedureAreas;
	}
	public boolean isBikiniline() {
		return Bikiniline;
	}
	public void setBikiniline(boolean bikiniline) {
		Bikiniline = bikiniline;
	}
	public boolean isUnderarms() {
		return Underarms;
	}
	public void setUnderarms(boolean underarms) {
		Underarms = underarms;
	}
	public boolean isEars() {
		return Ears;
	}
	public void setEars(boolean ears) {
		Ears = ears;
	}
	
}
