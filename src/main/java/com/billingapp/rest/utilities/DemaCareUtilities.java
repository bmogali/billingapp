package com.billingapp.rest.utilities;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.billingapp.rest.dto.AreasDTO;
import com.billingapp.rest.dto.ChargeTypeDTO;
import com.billingapp.rest.dto.CreditTypesDTO;
import com.billingapp.rest.dto.PatientChargeDTO;
import com.billingapp.rest.dto.PatientCreditDTO;
import com.billingapp.rest.dto.PatientDTO;
import com.billingapp.rest.dto.PatientEstimatesDTO;
import com.billingapp.rest.dto.PatientProcedureDTO;
import com.billingapp.rest.dto.PatientProductDTO;
import com.billingapp.rest.dto.ProcedureAreasDTO;
import com.billingapp.rest.dto.ProductDTO;
import com.billingapp.rest.model.Areas;
import com.billingapp.rest.model.ChargeType;
import com.billingapp.rest.model.CreditType;
import com.billingapp.rest.model.Patient;
import com.billingapp.rest.model.PatientBillingDetails;
import com.billingapp.rest.model.PatientChargeDetails;
import com.billingapp.rest.model.PatientDetails;
import com.billingapp.rest.model.PatientEstimates;
import com.billingapp.rest.model.PatientPaymentDetails;
import com.billingapp.rest.model.PatientProcedure;
import com.billingapp.rest.model.PatientProcedures;
import com.billingapp.rest.model.PatientProductDetails;
import com.billingapp.rest.model.ProcedureAreas;
import com.billingapp.rest.model.Product;

public class DemaCareUtilities {

	public static PatientDTO convertToPatientDTO(Patient patient) {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setPatientID((patient.getPatientId() == 0) ? null : patient.getPatientId());
		patientDTO.setFirstName(patient.getFirstName());
		patientDTO.setLastName(patient.getLastName());
		patientDTO.setDob(DateUtil.stringToDate(patient.getDob()));
		patientDTO.setEmail(patient.getEmail());
		patientDTO.setMailingAddress1(patient.getMailingAddress1());
		patientDTO.setMailingAddress2(patient.getMailingAddress2());
		patientDTO.setMailingCity(patient.getMailingCity());
		patientDTO.setMailingState(patient.getMailingState());
		patientDTO.setMailingZip(patient.getMailingZip());
		patientDTO.setMaritalStatus(patient.getMaritalStatus());
		patientDTO.setMedicalHistory(patient.getMedicalHistory());
		patientDTO.setMobileNumber(patient.getMobileNumber());
		patientDTO.setHomePhoneNumber(patient.getHomePhoneNumber());
		patientDTO.setWorkPhoneNumber(patient.getWorkPhoneNumber());
		patientDTO.setAllergies(patient.getAllergies());
		patientDTO.setSex(patient.getSex());
		patientDTO.setSocSec(patient.getSocSec());
		patientDTO.setDriversLicenseNum(patient.getDriversLicenseNum());
		patientDTO.setPharmacy(patient.getPharmacy());
		patientDTO.setSpousePartner(patient.getSpousePartner());
		patientDTO.setReferredBy(patient.getReferredBy());
		patientDTO.setPrimaryCare(patient.getPrimaryCare());
		patientDTO.setGuarantorName(patient.getGuarantorName());
		patientDTO.setGuarantorDOB(DateUtil.stringToDate(patient.getGuarantorDOB()));
		patientDTO.setGuarantorSocSec(patient.getGuarantorSocSec());
		patientDTO.setGuarantorAddress(patient.getGuarantorAddress());
		patientDTO.setGuarantorCity(patient.getGuarantorCity());
		patientDTO.setGuarantorState(patient.getGuarantorState());
		patientDTO.setGuarantorZip(patient.getGuarantorZip());
		patientDTO.setEmployer(patient.getEmployer());
		patientDTO.setWorkAddress(patient.getWorkAddress());
		patientDTO.setWorkPhone(patient.getWorkPhone());
		patientDTO.setWorkCity(patient.getWorkCity());
		patientDTO.setWorkState(patient.getWorkState());
		patientDTO.setWorkZip(patient.getWorkZip());
		patientDTO.setPrimaryInsurance(patient.getPrimaryInsurance());
		patientDTO.setPrimaryPlan(patient.getPrimaryPlan());
		patientDTO.setPrimarySusbscriberName(patient.getPrimarySusbscriberName());
		patientDTO.setPrimaryPolicyID(patient.getPrimaryPolicyId());
		patientDTO.setPrimaryGroupNumber(patient.getPrimaryGroupNumber());
		patientDTO.setPrimaryPatientRelationshiptoSubsc(patient.getPrimaryPatientRelationshiptoSubsc());
		patientDTO.setSecondaryPlan(patient.getSecondaryPlan());
		patientDTO.setSecondarySusbscriberName(patient.getSecondarySusbscriberName());
		patientDTO.setSecondaryGroupNumber(patient.getSecondaryGroupNumber());
		patientDTO.setSecondaryPolicyID(patient.getSecondaryPolicyId());
		patientDTO.setSecondaryInsurance(patient.getSecondaryInsurance());
		patientDTO.setSecondaryPatientRelationshiptoSubsc(patient.getSecondaryPatientRelationshiptoSubsc());
		patientDTO.setOtherInsurance(patient.getOtherInsurance());
		patientDTO.setImages(patient.getImages());
		patientDTO.setPictureLocation(patient.getPictureLocation());
		patientDTO.setAppointment(DateUtil.stringToDateTime(patient.getAppointment()));

		return patientDTO;
	}
	
	
	public static Patient convertToPatient(PatientDTO patientDTO) {
		Patient patient = new Patient();
		patient.setPatientId(patientDTO.getPatientID());
		patient.setFirstName(patientDTO.getFirstName());
		patient.setLastName(patientDTO.getLastName());
		patient.setDob(DateUtil.dateToString(patientDTO.getDob()));
		patient.setEmail(patientDTO.getEmail());
		patient.setMailingAddress1(patientDTO.getMailingAddress1());
		patient.setMailingAddress2(patientDTO.getMailingAddress2());
		patient.setMailingCity(patientDTO.getMailingCity());
		patient.setMailingState(patientDTO.getMailingState());
		patient.setMailingZip(patientDTO.getMailingZip());
		patient.setMaritalStatus(patientDTO.getMaritalStatus());
		patient.setMedicalHistory(patientDTO.getMedicalHistory());
		patient.setMobileNumber(patientDTO.getMobileNumber());
		patient.setHomePhoneNumber(patientDTO.getHomePhoneNumber());
		patient.setWorkPhoneNumber(patientDTO.getWorkPhoneNumber());
		patient.setAllergies(patientDTO.getAllergies());
		patient.setSex(patientDTO.getSex());
		patient.setSocSec(patientDTO.getSocSec());
		patient.setDriversLicenseNum(patientDTO.getDriversLicenseNum());
		patient.setPharmacy(patientDTO.getPharmacy());
		patient.setSpousePartner(patientDTO.getSpousePartner());
		patient.setReferredBy(patientDTO.getReferredBy());
		patient.setPrimaryCare(patientDTO.getPrimaryCare());
		patient.setGuarantorName(patientDTO.getGuarantorName());
		patient.setGuarantorDOB(DateUtil.dateToString(patientDTO.getGuarantorDOB()));
		patient.setGuarantorSocSec(patientDTO.getGuarantorSocSec());
		patient.setGuarantorAddress(patientDTO.getGuarantorAddress());
		patient.setGuarantorCity(patientDTO.getGuarantorCity());
		patient.setGuarantorState(patientDTO.getGuarantorState());
		patient.setGuarantorZip(patientDTO.getGuarantorZip());
		patient.setEmployer(patientDTO.getEmployer());
		patient.setWorkAddress(patientDTO.getWorkAddress());
		patient.setWorkPhone(patientDTO.getWorkPhone());
		patient.setWorkCity(patientDTO.getWorkCity());
		patient.setWorkState(patientDTO.getWorkState());
		patient.setWorkZip(patientDTO.getWorkZip());
		patient.setPrimaryInsurance(patientDTO.getPrimaryInsurance());
		patient.setPrimaryPlan(patientDTO.getPrimaryPlan());
		patient.setPrimarySusbscriberName(patientDTO.getPrimarySusbscriberName());
		patient.setPrimaryPolicyId(patientDTO.getPrimaryPolicyID());
		patient.setPrimaryGroupNumber(patientDTO.getPrimaryGroupNumber());
		patient.setPrimaryPatientRelationshiptoSubsc(patientDTO.getPrimaryPatientRelationshiptoSubsc());
		patient.setSecondaryPlan(patientDTO.getSecondaryPlan());
		patient.setSecondarySusbscriberName(patientDTO.getSecondarySusbscriberName());
		patient.setSecondaryGroupNumber(patientDTO.getSecondaryGroupNumber());
		patient.setSecondaryPolicyId(patientDTO.getSecondaryPolicyID());
		patient.setSecondaryInsurance(patientDTO.getSecondaryInsurance());
		patient.setSecondaryPatientRelationshiptoSubsc(patientDTO.getSecondaryPatientRelationshiptoSubsc());
		patient.setOtherInsurance(patientDTO.getOtherInsurance());
		patient.setImages(patientDTO.getImages());
		patient.setPictureLocation(patientDTO.getPictureLocation());
		patient.setAppointment(DateUtil.dateTimeToString(patientDTO.getAppointment()));

		return patient;
	}
	
	public static void setPatientProcedures(
			Set<PatientProcedureDTO> patientProcedures, Patient patient) {

		if (null != patientProcedures && patientProcedures.size() > 0) {

			List<PatientProcedures> procedures = new ArrayList<PatientProcedures>();

			for (PatientProcedureDTO procedureDTO : patientProcedures) {

				PatientProcedures procedure = new PatientProcedures();
				procedure.setPatientProcedureId(procedureDTO.getPatientProcedureId());
				if (null != procedureDTO.getProcedureDTO()) {
					procedure.setProcedureName(procedureDTO.getProcedureDTO().getProcedureName());
				}
				procedure.setProcedureDate(DateUtil.dateTimeToString(procedureDTO.getProcedureDate()));
				procedure.setSequence(procedureDTO.getTreamnentSequence());
				procedures.add(procedure);
			}
			patient.setPatientProcedures(procedures);
		}
	}
	
	public static List<PatientChargeDetails> convertToPatientCharge(
			List<PatientChargeDTO> patientChargeDTOs, Integer patientId) {

		List<PatientChargeDetails> patientChargeDetails = new ArrayList<PatientChargeDetails>();

		for (PatientChargeDTO dto : patientChargeDTOs) {
			PatientChargeDetails chargeDetail = new PatientChargeDetails();
			chargeDetail.setPatientChargeId(dto.getPatientChargeID());
			PatientDTO patientDTO = dto.getPatient();
			if (null != patientDTO) {
				chargeDetail.setPatientId(patientDTO.getPatientID());
				chargeDetail.setFirstName(patientDTO.getFirstName());
				chargeDetail.setLastName(patientDTO.getLastName());
			}
			chargeDetail.setChargeTypeId(dto.getChargeType() != null ? dto.getChargeType().getChargeId() : null);
			chargeDetail.setBaseCharge(dto.getBaseCharge() != null ? dto.getBaseCharge().toString() : null);
			chargeDetail.setChargeDate(DateUtil.dateTimeToString(dto.getChargeDate()));
			chargeDetail.setChargeName(dto.getChargeType() != null ? dto.getChargeType().getCharge() : null);
			chargeDetail.setComment(dto.getComment());
			chargeDetail.setDiscount(dto.getDiscount() != null ? dto.getDiscount().toString() : null);
			chargeDetail.setNumberOfSessions(dto.getNumberofSessions());
			chargeDetail.setTax(dto.getTax() != null ? dto.getTax().toString() : null);
			chargeDetail.setTotalCharge(dto.getTotalCharge() != null ? dto.getTotalCharge().toString() : null);
			patientChargeDetails.add(chargeDetail);
		}
		return patientChargeDetails;
	}

	public static PatientProcedure convertToPatientProcedure(PatientProcedureDTO procedureDTO) {
		
		PatientProcedure procedure = new PatientProcedure();
		
		procedure.setPatientProcedureId(procedureDTO.getPatientProcedureId());
		procedure.setPatientId(procedureDTO.getPatient().getPatientID());
		
		procedure.setProcedureAreas(convertToProcedureAreas(procedureDTO));
		
		procedure.setProcedureId(procedureDTO.getProcedureDTO().getProcedureID());
		procedure.setTreatmentSequenceId(procedureDTO.getTreamnentSequence());
		procedure.setProcedureDate(DateUtil.dateTimeToString(procedureDTO.getProcedureDate()));
		procedure.setPriorComplication(procedureDTO.isPriorComplication());
		procedure.setComplicationDescription(procedureDTO.getComplicationDescription());
		procedure.setBLTCreamUsedYes(procedureDTO.isBLTCreamUsedYes());
		procedure.setBLTCreamUsedNo(procedureDTO.isBLTCreamUsedNo());
		procedure.setCheeks(procedureDTO.isCheeks());
		procedure.setNose(procedureDTO.isNose());
		procedure.setForehead(procedureDTO.isForehead());
		procedure.setNeck(procedureDTO.isNeck());
		procedure.setCalf(procedureDTO.isCalf());
		procedure.setMidFaceRight(procedureDTO.isMidFaceRight());
		procedure.setMidFaceLeft(procedureDTO.isMidFaceLeft());
		procedure.setFace(procedureDTO.isFace());
		procedure.setUpperLip(procedureDTO.isUpperLip());
		procedure.setBikini(procedureDTO.isBikini());
		procedure.setBack(procedureDTO.isBack());
		procedure.setChin(procedureDTO.isChin());
		procedure.setLeg(procedureDTO.isLeg());
		procedure.setThigh(procedureDTO.isThigh());
		procedure.setChest(procedureDTO.isChest());
		procedure.setOtherArea(procedureDTO.isOtherArea());
		procedure.setOtherAreaDescription(procedureDTO.getOtherAreaDescription());
		procedure.setEnergy14(procedureDTO.isEnergy14());
		procedure.setEnergy15(procedureDTO.isEnergy15());
		procedure.setEnergy16(procedureDTO.isEnergy16());
		procedure.setEnergy17(procedureDTO.isEnergy17());
		procedure.setEnergy30(procedureDTO.isEnergy30());
		procedure.setEnergy45(procedureDTO.isEnergy45());
		procedure.setEnergy60(procedureDTO.isEnergy60());
		procedure.setEnergy75(procedureDTO.isEnergy75());
		procedure.setEnergy90(procedureDTO.isEnergy90());
		procedure.setEnergy105(procedureDTO.isEnergy105());
		procedure.setEnergy100(procedureDTO.isEnergy100());
		procedure.setEnergy125(procedureDTO.isEnergy125());
		procedure.setEnergy150(procedureDTO.isEnergy150());
		procedure.setEnergy175(procedureDTO.isEnergy175());
		procedure.setEnergy200(procedureDTO.isEnergy200());
		procedure.setPPS1(procedureDTO.isPPS1());
		procedure.setPPS15(procedureDTO.isPPS15());
		procedure.setPPS2(procedureDTO.isPPS2());
		procedure.setPPS25(procedureDTO.isPPS25());
		procedure.setPPS3(procedureDTO.isPPS3());
		procedure.setPPSMax(procedureDTO.isPPSMax());
		procedure.setSpotSize25(procedureDTO.isSpotSize25());
		procedure.setSpotSize5(procedureDTO.isSpotSize5());
		procedure.setSpotSize7(procedureDTO.isSpotSize7());
		procedure.setSpotSize10(procedureDTO.isSpotSize10());
		procedure.setPower3(procedureDTO.isPower3());
		procedure.setRR5(procedureDTO.isRR5());
		procedure.setRR6(procedureDTO.isRR6());
		procedure.setRR7(procedureDTO.isRR7());
		procedure.setNumberofPulses(procedureDTO.getNumberofPulses());
		procedure.setEnergy25(procedureDTO.isEnergy25());
		procedure.setEnergy40(procedureDTO.isEnergy40());
		procedure.setDry(procedureDTO.isDry());
		procedure.setNormal(procedureDTO.isNormal());
		procedure.setOily(procedureDTO.isOily());
		procedure.setCleansedWith(procedureDTO.getCleansedWith());
		procedure.setPrepPad(procedureDTO.isPrepPad());
		procedure.setSolution(procedureDTO.isSolution());
		procedure.setAirDry(procedureDTO.isAirDry());
		procedure.setFanDry(procedureDTO.isFanDry());
		procedure.setClothDry(procedureDTO.isClothDry());
		procedure.setGold(procedureDTO.isGold());
		procedure.setSilver(procedureDTO.isSilver());
		procedure.setAmber(procedureDTO.isAmber());
		procedure.setBlue(procedureDTO.isBlue());
		procedure.setClear(procedureDTO.isClear());
		procedure.setVac25(procedureDTO.isVac25());
		procedure.setVac30(procedureDTO.isVac30());
		procedure.setVac35(procedureDTO.isVac35());
		procedure.setVac40(procedureDTO.isVac40());
		procedure.setVac45(procedureDTO.isVac45());
		procedure.setVac50(procedureDTO.isVac50());
		procedure.setKnob20(procedureDTO.isKnob20());
		procedure.setKnob30(procedureDTO.isKnob30());
		procedure.setKnob40(procedureDTO.isKnob40());
		procedure.setKnob50(procedureDTO.isKnob50());
		procedure.setForheadNormal(procedureDTO.isForheadNormal());
		procedure.setForheadSensitive(procedureDTO.isForheadSensitive());
		procedure.setForheadRedness(procedureDTO.isForheadRedness());
		procedure.setForheadPetechiae(procedureDTO.isForheadPetechiae());
		procedure.setForheadExcoriation(procedureDTO.isForheadExcoriation());
		procedure.setForheadNone(procedureDTO.isForheadNone());
		procedure.setProceduresdone(procedureDTO.isProceduresdone());
		procedure.setSkinTypeI(procedureDTO.isSkinTypeI());
		procedure.setSkinTypeII(procedureDTO.isSkinTypeII());
		procedure.setSkinTypeIII(procedureDTO.isSkinTypeIII());
		procedure.setSkinTypeIV(procedureDTO.isSkinTypeIV());
		procedure.setSkinTypeV(procedureDTO.isSkinTypeV());
		procedure.setSkinTypeVI(procedureDTO.isSkinTypeVI());
		procedure.setDoctorClinician(procedureDTO.getDoctorClinician());
		procedure.setThirtymsecPreCool(procedureDTO.isThirtymsecPreCool());
		procedure.setTwentyFivemsecPreCool(procedureDTO.isTwentyFivemsecPreCool());
		procedure.setTwentymsecPreCool(procedureDTO.isTwentymsecPreCool());
		procedure.setPostCooling(procedureDTO.isPostCooling());
		procedure.setTenmmHandPiece(procedureDTO.isTenmmHandPiece());
		procedure.setPostTreatement(procedureDTO.getPostTreatement());
		procedure.setInstructions(procedureDTO.getInstructions());
		procedure.setAgent(procedureDTO.getAgent());
		procedure.setLot(procedureDTO.getLot());
		
		procedure.setExpDate(DateUtil.dateTimeToString(procedureDTO.getExpDate()));
		
		procedure.setLowerlipyn(procedureDTO.isLowerlipyn());
		procedure.setLowerlipml(procedureDTO.getLowerlipml());
		procedure.setVermilionyn(procedureDTO.isVermilionyn());
		procedure.setVermilionml(procedureDTO.getVermilionml());
		procedure.setNasalfoldyn(procedureDTO.isNasalfoldyn());
		procedure.setNasalfoldml(procedureDTO.getNasalfoldml());
		procedure.setCheekyn(procedureDTO.isCheekyn());
		procedure.setCheekml(procedureDTO.getCheekml());
		procedure.setMelomentFoldyn(procedureDTO.isMelomentFoldyn());
		procedure.setMelomentFoldml(procedureDTO.getMelomentFoldml());
		procedure.setOtherml(procedureDTO.getOtherml());
		procedure.setUpperlipml(procedureDTO.getUpperlipml());
		procedure.setForheadml(procedureDTO.getForheadml());
		procedure.setGlabellayn(procedureDTO.isGlabellayn());
		procedure.setGlabellaynml(procedureDTO.getGlabellaynml());
		procedure.setCrowsfeetyn(procedureDTO.isCrowsfeetyn());
		procedure.setCrowsfeetml(procedureDTO.getCrowsfeetml());
		procedure.setPlatysmayn(procedureDTO.isPlatysmayn());
		procedure.setPlatysmaml(procedureDTO.getPlatysmaml());
		procedure.setSel(procedureDTO.getSel());
		procedure.setMedhx(procedureDTO.getMedhx());
		procedure.setEyebrowyn(procedureDTO.isEyebrowyn());
		procedure.setEyebrowml(procedureDTO.getEyebrowml());
		procedure.setForheadUnitPerInj(procedureDTO.getForheadUnitPerInj());
		procedure.setForheadNumberofInj(procedureDTO.getForheadNumberofInj());
		procedure.setGlabellaUnitPerInj(procedureDTO.getGlabellaUnitPerInj());
		procedure.setGlabellaNumberofInj(procedureDTO.getGlabellaNumberofInj());
		procedure.setCrowsFeetUnitPerInj(procedureDTO.getCrowsFeetUnitPerInj());
		procedure.setCrowsFeetNumberofInj(procedureDTO.getCrowsFeetNumberofInj());
		procedure.setEyebrowUnitPerInj(procedureDTO.getEyebrowUnitPerInj());
		procedure.setEyebrowNumberofInj(procedureDTO.getEyebrowNumberofInj());
		procedure.setUpperLipUnitPerInj(procedureDTO.getUpperLipUnitPerInj());
		procedure.setUpperLipNumberofInj(procedureDTO.getUpperLipNumberofInj());
		procedure.setPlatysmaUnitPerInj(procedureDTO.getPlatysmaUnitPerInj());
		procedure.setPlatysmaNumberofInj(procedureDTO.getPlatysmaNumberofInj());
		procedure.setOtherAreaUnitPerInj(procedureDTO.getOtherAreaUnitPerInj());
		procedure.setOtherAreaNumberofInj(procedureDTO.getOtherAreaNumberofInj());
		procedure.setDilution001(procedureDTO.isDilution001());
		procedure.setDilutionother(procedureDTO.isDilutionother());
		procedure.setDilutionAmount(procedureDTO.getDilutionAmount());
		procedure.setProcedureConsequences(procedureDTO.getProcedureConsequences());
		procedure.setProcedureConsentName(procedureDTO.getProcedureConsentName());
		procedure.setAssistant(procedureDTO.getAssistant());
		procedure.setPreopdx(procedureDTO.getPreopdx());
		procedure.setPostopdx(procedureDTO.getPostopdx());
		procedure.setProcedure(procedureDTO.getProcedureMisc());
		procedure.setAnehesia(procedureDTO.getAnehesia());
		procedure.setHistory(procedureDTO.getHistory());
		procedure.setSteps(procedureDTO.getSteps());
		procedure.setComplications(procedureDTO.getComplications());
		procedure.setAtLeast18(procedureDTO.isAtLeast18());
		procedure.setExfolientUsed(procedureDTO.getExfolientUsed());
		if (null != procedureDTO.getExfolientPercentage()) {
			procedure.setExfolientPercentage(procedureDTO
					.getExfolientPercentage());
		}
		procedure.setExfolientTime(procedureDTO.getExfolientTime());
		procedure.setEnzyme(procedureDTO.getEnzyme());
		procedure.setEnzymeLength(procedureDTO.getEnzymeLength());
		procedure.setSteam(procedureDTO.isSteam());
		procedure.setMask(procedureDTO.getMask());
		procedure.setOtherProduct1(procedureDTO.getOtherProduct1());
		procedure.setOtherProduct2(procedureDTO.getOtherProduct2());
		procedure.setOtherProduct3(procedureDTO.getOtherProduct3());
		procedure.setSunblock(procedureDTO.getSunblock());
		procedure.setComedones(procedureDTO.getComedones());
		procedure.setMilia(procedureDTO.getMilia());
		procedure.setPapeles(procedureDTO.getPapeles());
		procedure.setPustules(procedureDTO.getPustules());
		procedure.setExtractions(procedureDTO.getExtractions());
		
		procedure.setDateofLastTreatment(DateUtil.dateTimeToString(procedureDTO.getDateofLastTreatment()));
		
		procedure.setUsingHomecare(procedureDTO.isUsingHomecare());
		procedure.setProductsNeeded(procedureDTO.getProductsNeeded());
		procedure.setBotoxYN(procedureDTO.isBotoxYN());
		procedure.setHyalorunicYN(procedureDTO.isHyalorunicYN());
		procedure.setMesoYN(procedureDTO.isMesoYN());
		procedure.setEnergy400(procedureDTO.isEnergy400());
		procedure.setEnergy600(procedureDTO.isEnergy600());
		procedure.setLVR(procedureDTO.isLVR());
		procedure.setHands(procedureDTO.isHands());
		procedure.setNewProcedure(procedureDTO.isNewProcedure());
		procedure.setArms(procedureDTO.isArms());
		
		
		return procedure;
		
	}


	private static List<ProcedureAreas> convertToProcedureAreas(
			PatientProcedureDTO procedureDTO) {
		Set<ProcedureAreasDTO> procedureAreasDTOs = procedureDTO.getProcedureAreas();
		List<ProcedureAreas> procedureAreas = new ArrayList<ProcedureAreas>();

		if (null != procedureAreasDTOs && procedureAreasDTOs.size() > 0) {

			for (ProcedureAreasDTO procAreasDTO : procedureAreasDTOs) {

				ProcedureAreas procArea = new ProcedureAreas();

				procArea.setProcedureAreaId(procAreasDTO.getProcedureAreaId());
				procArea.setBaselineTemp(procAreasDTO.getBaselineTemp());
				procArea.setFluence(procAreasDTO.getFluence());
				procArea.setPeakTemp(procAreasDTO.getPeakTemp());
				if (null != procAreasDTO.getPatientProcedure()) {
					procArea.setPatientProcedureId(procAreasDTO.getPatientProcedure().getPatientProcedureId());
				}
				if (null != procAreasDTO.getAreas()) {
					Areas areas = new Areas();
					areas.setAreaId(procAreasDTO.getAreas().getAreaId());
					areas.setArea(procAreasDTO.getAreas().getArea());
					procArea.setAreas(areas);
				}
				procedureAreas.add(procArea);
			}
		}
		return procedureAreas;
	}
	
	private static Set<ProcedureAreasDTO> convertToProcedureAreasDTO(PatientProcedure procedure, PatientProcedureDTO procedureDTO) {
		
		Set<ProcedureAreasDTO> procedureAreaDTOs = new HashSet<ProcedureAreasDTO>();
		List<ProcedureAreas> procedureAreas = procedure.getProcedureAreas();
		
		if (null != procedureAreas && procedureAreas.size() > 0) {

			for (ProcedureAreas procAreas : procedureAreas) {

				ProcedureAreasDTO procAreaDTO = new ProcedureAreasDTO();

				procAreaDTO.setProcedureAreaId(procAreas.getProcedureAreaId());
				procAreaDTO.setBaselineTemp(procAreas.getBaselineTemp());
				procAreaDTO.setFluence(procAreas.getFluence());
				procAreaDTO.setPeakTemp(procAreas.getPeakTemp());
				procAreaDTO.setPatientProcedure(procedureDTO);
				
				if (null != procAreas.getAreas()) {
					AreasDTO areasDTO = new AreasDTO();
					areasDTO.setAreaId(procAreas.getAreas().getAreaId());
					areasDTO.setArea(procAreas.getAreas().getArea());
					procAreaDTO.setAreas(areasDTO);
				}
				procedureAreaDTOs.add(procAreaDTO);
			}
		}
		return procedureAreaDTOs;
	}
	
		
	public static PatientProcedureDTO convertToPatientProcedureDTO(PatientProcedure procedure) {
		PatientProcedureDTO procedureDTO = new PatientProcedureDTO();
		
		procedureDTO.setPatientProcedureId(procedure.getPatientProcedureId());
		procedureDTO.setTreamnentSequence(procedure.getTreatmentSequenceId());
		
		procedureDTO.setProcedureDate(DateUtil.stringToDateTime(procedure.getProcedureDate()));
		procedureDTO.setPriorComplication(procedure.isPriorComplication());
		procedureDTO.setComplicationDescription(procedure.getComplicationDescription());
		procedureDTO.setBLTCreamUsedYes(procedure.isBLTCreamUsedYes());
		procedureDTO.setBLTCreamUsedNo(procedure.isBLTCreamUsedNo());
		procedureDTO.setCheeks(procedure.isCheeks());
		procedureDTO.setNose(procedure.isNose());
		procedureDTO.setForehead(procedure.isForehead());
		procedureDTO.setNeck(procedure.isNeck());
		procedureDTO.setCalf(procedure.isCalf());
		procedureDTO.setMidFaceRight(procedure.isMidFaceRight());
		procedureDTO.setMidFaceLeft(procedure.isMidFaceLeft());
		procedureDTO.setFace(procedure.isFace());
		procedureDTO.setUpperLip(procedure.isUpperLip());
		procedureDTO.setBikini(procedure.isBikini());
		procedureDTO.setBack(procedure.isBack());
		procedureDTO.setChin(procedure.isChin());
		procedureDTO.setLeg(procedure.isLeg());
		procedureDTO.setThigh(procedure.isThigh());
		procedureDTO.setChest(procedure.isChest());
		procedureDTO.setOtherArea(procedure.isOtherArea());
		procedureDTO.setOtherAreaDescription(procedure.getOtherAreaDescription());
		procedureDTO.setEnergy14(procedure.isEnergy14());
		procedureDTO.setEnergy15(procedure.isEnergy15());
		procedureDTO.setEnergy16(procedure.isEnergy16());
		procedureDTO.setEnergy17(procedure.isEnergy17());
		procedureDTO.setEnergy30(procedure.isEnergy30());
		procedureDTO.setEnergy45(procedure.isEnergy45());
		procedureDTO.setEnergy60(procedure.isEnergy60());
		procedureDTO.setEnergy75(procedure.isEnergy75());
		procedureDTO.setEnergy90(procedure.isEnergy90());
		procedureDTO.setEnergy105(procedure.isEnergy105());
		procedureDTO.setEnergy100(procedure.isEnergy100());
		procedureDTO.setEnergy125(procedure.isEnergy125());
		procedureDTO.setEnergy150(procedure.isEnergy150());
		procedureDTO.setEnergy175(procedure.isEnergy175());
		procedureDTO.setEnergy200(procedure.isEnergy200());
		procedureDTO.setPPS1(procedure.isPPS1());
		procedureDTO.setPPS15(procedure.isPPS15());
		procedureDTO.setPPS2(procedure.isPPS2());
		procedureDTO.setPPS25(procedure.isPPS25());
		procedureDTO.setPPS3(procedure.isPPS3());
		procedureDTO.setPPSMax(procedure.isPPSMax());
		procedureDTO.setSpotSize25(procedure.isSpotSize25());
		procedureDTO.setSpotSize5(procedure.isSpotSize5());
		procedureDTO.setSpotSize7(procedure.isSpotSize7());
		procedureDTO.setSpotSize10(procedure.isSpotSize10());
		procedureDTO.setPower3(procedure.isPower3());
		procedureDTO.setRR5(procedure.isRR5());
		procedureDTO.setRR6(procedure.isRR6());
		procedureDTO.setRR7(procedure.isRR7());
		procedureDTO.setNumberofPulses(procedure.getNumberofPulses());
		procedureDTO.setEnergy25(procedure.isEnergy25());
		procedureDTO.setEnergy40(procedure.isEnergy40());
		procedureDTO.setDry(procedure.isDry());
		procedureDTO.setNormal(procedure.isNormal());
		procedureDTO.setOily(procedure.isOily());
		procedureDTO.setCleansedWith(procedure.getCleansedWith());
		procedureDTO.setPrepPad(procedure.isPrepPad());
		procedureDTO.setSolution(procedure.isSolution());
		procedureDTO.setAirDry(procedure.isAirDry());
		procedureDTO.setFanDry(procedure.isFanDry());
		procedureDTO.setClothDry(procedure.isClothDry());
		procedureDTO.setGold(procedure.isGold());
		procedureDTO.setSilver(procedure.isSilver());
		procedureDTO.setAmber(procedure.isAmber());
		procedureDTO.setBlue(procedure.isBlue());
		procedureDTO.setClear(procedure.isClear());
		procedureDTO.setVac25(procedure.isVac25());
		procedureDTO.setVac30(procedure.isVac30());
		procedureDTO.setVac35(procedure.isVac35());
		procedureDTO.setVac40(procedure.isVac40());
		procedureDTO.setVac45(procedure.isVac45());
		procedureDTO.setVac50(procedure.isVac50());
		procedureDTO.setKnob20(procedure.isKnob20());
		procedureDTO.setKnob30(procedure.isKnob30());
		procedureDTO.setKnob40(procedure.isKnob40());
		procedureDTO.setKnob50(procedure.isKnob50());
		procedureDTO.setForheadNormal(procedure.isForheadNormal());
		procedureDTO.setForheadSensitive(procedure.isForheadSensitive());
		procedureDTO.setForheadRedness(procedure.isForheadRedness());
		procedureDTO.setForheadPetechiae(procedure.isForheadPetechiae());
		procedureDTO.setForheadExcoriation(procedure.isForheadExcoriation());
		procedureDTO.setForheadNone(procedure.isForheadNone());
		procedureDTO.setProceduresdone(procedure.isProceduresdone());
		procedureDTO.setSkinTypeI(procedure.isSkinTypeI());
		procedureDTO.setSkinTypeII(procedure.isSkinTypeII());
		procedureDTO.setSkinTypeIII(procedure.isSkinTypeIII());
		procedureDTO.setSkinTypeIV(procedure.isSkinTypeIV());
		procedureDTO.setSkinTypeV(procedure.isSkinTypeV());
		procedureDTO.setSkinTypeVI(procedure.isSkinTypeVI());
		procedureDTO.setDoctorClinician(procedure.getDoctorClinician());
		procedureDTO.setThirtymsecPreCool(procedure.isThirtymsecPreCool());
		procedureDTO.setTwentyFivemsecPreCool(procedure.isTwentyFivemsecPreCool());
		procedureDTO.setTwentymsecPreCool(procedure.isTwentymsecPreCool());
		procedureDTO.setPostCooling(procedure.isPostCooling());
		procedureDTO.setTenmmHandPiece(procedure.isTenmmHandPiece());
		procedureDTO.setPostTreatement(procedure.getPostTreatement());
		procedureDTO.setInstructions(procedure.getInstructions());
		procedureDTO.setAgent(procedure.getAgent());
		procedureDTO.setLot(procedure.getLot());
		
		procedureDTO.setExpDate(DateUtil.stringToDateTime(procedure.getExpDate()));
		procedureDTO.setLowerlipyn(procedure.isLowerlipyn());
		procedureDTO.setLowerlipml(procedure.getLowerlipml());
		procedureDTO.setVermilionyn(procedure.isVermilionyn());
		procedureDTO.setVermilionml(procedure.getVermilionml());
		procedureDTO.setNasalfoldyn(procedure.isNasalfoldyn());
		procedureDTO.setNasalfoldml(procedure.getNasalfoldml());
		procedureDTO.setCheekyn(procedure.isCheekyn());
		procedureDTO.setCheekml(procedure.getCheekml());
		procedureDTO.setMelomentFoldyn(procedure.isMelomentFoldyn());
		procedureDTO.setMelomentFoldml(procedure.getMelomentFoldml());
		procedureDTO.setOtherml(procedure.getOtherml());
		procedureDTO.setUpperlipml(procedure.getUpperlipml());
		procedureDTO.setForheadml(procedure.getForheadml());
		procedureDTO.setGlabellayn(procedure.isGlabellayn());
		procedureDTO.setGlabellaynml(procedure.getGlabellaynml());
		procedureDTO.setCrowsfeetyn(procedure.isCrowsfeetyn());
		procedureDTO.setCrowsfeetml(procedure.getCrowsfeetml());
		procedureDTO.setPlatysmayn(procedure.isPlatysmayn());
		procedureDTO.setPlatysmaml(procedure.getPlatysmaml());
		procedureDTO.setSel(procedure.getSel());
		procedureDTO.setMedhx(procedure.getMedhx());
		procedureDTO.setEyebrowyn(procedure.isEyebrowyn());
		procedureDTO.setEyebrowml(procedure.getEyebrowml());
		procedureDTO.setForheadUnitPerInj(procedure.getForheadUnitPerInj());
		procedureDTO.setForheadNumberofInj(procedure.getForheadNumberofInj());
		procedureDTO.setGlabellaUnitPerInj(procedure.getGlabellaUnitPerInj());
		procedureDTO.setGlabellaNumberofInj(procedure.getGlabellaNumberofInj());
		procedureDTO.setCrowsFeetUnitPerInj(procedure.getCrowsFeetUnitPerInj());
		procedureDTO.setCrowsFeetNumberofInj(procedure.getCrowsFeetNumberofInj());
		procedureDTO.setEyebrowUnitPerInj(procedure.getEyebrowUnitPerInj());
		procedureDTO.setEyebrowNumberofInj(procedure.getEyebrowNumberofInj());
		procedureDTO.setUpperLipUnitPerInj(procedure.getUpperLipUnitPerInj());
		procedureDTO.setUpperLipNumberofInj(procedure.getUpperLipNumberofInj());
		procedureDTO.setPlatysmaUnitPerInj(procedure.getPlatysmaUnitPerInj());
		procedureDTO.setPlatysmaNumberofInj(procedure.getPlatysmaNumberofInj());
		procedureDTO.setOtherAreaUnitPerInj(procedure.getOtherAreaUnitPerInj());
		procedureDTO.setOtherAreaNumberofInj(procedure.getOtherAreaNumberofInj());
		procedureDTO.setDilution001(procedure.isDilution001());
		procedureDTO.setDilutionother(procedure.isDilutionother());
		procedureDTO.setDilutionAmount(procedure.getDilutionAmount());
		procedureDTO.setProcedureConsequences(procedure.getProcedureConsequences());
		procedureDTO.setProcedureConsentName(procedure.getProcedureConsentName());
		procedureDTO.setAssistant(procedure.getAssistant());
		procedureDTO.setPreopdx(procedure.getPreopdx());
		procedureDTO.setPostopdx(procedure.getPostopdx());
		procedureDTO.setProcedureMisc(procedure.getProcedure());
		procedureDTO.setAnehesia(procedure.getAnehesia());
		procedureDTO.setHistory(procedure.getHistory());
		procedureDTO.setSteps(procedure.getSteps());
		procedureDTO.setComplications(procedure.getComplications());
		procedureDTO.setAtLeast18(procedure.isAtLeast18());
		procedureDTO.setExfolientUsed(procedure.getExfolientUsed());
		
		procedureDTO.setExfolientPercentage(procedure.getExfolientPercentage());
		
		procedureDTO.setExfolientTime(procedure.getExfolientTime());
		procedureDTO.setEnzyme(procedure.getEnzyme());
		procedureDTO.setEnzymeLength(procedure.getEnzymeLength());
		procedureDTO.setSteam(procedure.isSteam());
		procedureDTO.setMask(procedure.getMask());
		procedureDTO.setOtherProduct1(procedure.getOtherProduct1());
		procedureDTO.setOtherProduct2(procedure.getOtherProduct2());
		procedureDTO.setOtherProduct3(procedure.getOtherProduct3());
		procedureDTO.setSunblock(procedure.getSunblock());
		procedureDTO.setComedones(procedure.getComedones());
		procedureDTO.setMilia(procedure.getMilia());
		procedureDTO.setPapeles(procedure.getPapeles());
		procedureDTO.setPustules(procedure.getPustules());
		procedureDTO.setExtractions(procedure.getExtractions());
		procedureDTO.setDateofLastTreatment(DateUtil.stringToDateTime(procedure.getDateofLastTreatment()));
		procedureDTO.setUsingHomecare(procedure.isUsingHomecare());
		procedureDTO.setProductsNeeded(procedure.getProductsNeeded());
		procedureDTO.setBotoxYN(procedure.isBotoxYN());
		procedureDTO.setHyalorunicYN(procedure.isHyalorunicYN());
		procedureDTO.setMesoYN(procedure.isMesoYN());
		procedureDTO.setEnergy400(procedure.isEnergy400());
		procedureDTO.setEnergy600(procedure.isEnergy600());
		procedureDTO.setLVR(procedure.isLVR());
		procedureDTO.setHands(procedure.isHands());
		procedureDTO.setNewProcedure(procedure.isNewProcedure());
		procedureDTO.setArms(procedure.isArms());
		
		procedureDTO.setProcedureAreas(convertToProcedureAreasDTO(procedure, procedureDTO));
		
		return procedureDTO;
		
	}



	public static void setPatientBillingDetails(
			List<PatientChargeDTO> patientChargeDTOss,
			List<PatientProductDTO> patientProductsDTos,
			List<PatientCreditDTO> patientCreditsDTos,
			PatientBillingDetails patientBillingDetails,
			Integer patientId) {
		
		
		List<PatientChargeDetails> patientChargeDetails = new ArrayList<PatientChargeDetails>();
		List<PatientProductDetails> patientProductDetails = new ArrayList<PatientProductDetails>();
		List<PatientPaymentDetails> patientPaymentDetails = new ArrayList<PatientPaymentDetails>();
		
		Double totalChargesForServices= 0.0;
		Double totalChargesForProducts = 0.0;
		Double totalAmountPaidForServices = 0.0;
		Double totalAmountPaidForProducts=0.0;
		Double totalAmount=0.0;
		Double totalAmountPaid = 0.0;
		Double totalAmountLeft= 0.0;
		
		for(PatientChargeDTO patientChargeDTO : patientChargeDTOss) {
			PatientChargeDetails patientChargeDetail = new PatientChargeDetails();
			patientChargeDetail.setPatientChargeId(patientChargeDTO.getPatientChargeID());
			patientChargeDetail.setPatientId(patientId);
			patientChargeDetail.setChargeTypeId(patientChargeDTO.getChargeType() != null ? patientChargeDTO.getChargeType().getChargeId() : null);
			patientChargeDetail.setBaseCharge(patientChargeDTO.getBaseCharge() != null ? patientChargeDTO.getBaseCharge().toString() : null);
			patientChargeDetail.setChargeDate(DateUtil.dateTimeToString(patientChargeDTO.getChargeDate()));
			patientChargeDetail.setChargeName(patientChargeDTO.getChargeType() != null ? patientChargeDTO.getChargeType().getCharge() : null);
			patientChargeDetail.setComment(patientChargeDTO.getComment());
			patientChargeDetail.setDiscount(patientChargeDTO.getDiscount() != null ? patientChargeDTO.getDiscount().toString() : null);
			patientChargeDetail.setNumberOfSessions(patientChargeDTO.getNumberofSessions());
			patientChargeDetail.setTax(patientChargeDTO.getTax() != null ? patientChargeDTO.getTax().toString() : null);
			patientChargeDetail.setTotalCharge(patientChargeDTO.getTotalCharge() != null ? patientChargeDTO.getTotalCharge().toString() : null);
			patientChargeDetails.add(patientChargeDetail); 
			if(patientChargeDTO.getTotalCharge() != null)
			totalChargesForServices += patientChargeDTO.getTotalCharge();
		}
		
		for(PatientProductDTO patientProdDto : patientProductsDTos) {
			PatientProductDetails patientProdDetails = new PatientProductDetails();
			patientProdDetails.setPatientProductId(patientProdDto.getPatientProductID());
			patientProdDetails.setPatientId(patientId);
			patientProdDetails.setProductId(patientProdDto.getProduct() != null ? patientProdDto.getProduct().getProductID(): null);
			patientProdDetails.setComment(patientProdDto.getComment());
			patientProdDetails.setDate(DateUtil.dateTimeToString(patientProdDto.getDate()));
			patientProdDetails.setDiscount(patientProdDto.getDiscount() != null ? patientProdDto.getDiscount().toString() : null);
			patientProdDetails.setProductBaseCharge(patientProdDto.getProductBaseCharge() != null ? 
					patientProdDto.getProductBaseCharge().toString(): null);
			patientProdDetails.setProductName(patientProdDto.getProduct() != null ? patientProdDto.getProduct().getProduct(): null);
			patientProdDetails.setProductTax(patientProdDto.getProdudctTax() != null ? patientProdDto.getProdudctTax().toString() : null);
			patientProdDetails.setProductTotalCharge(patientProdDto.getProductTotalCharge() != null ?
					patientProdDto.getProductTotalCharge().toString() : null);
			
			patientProdDetails.setQuantity(patientProdDto.getQuantity().toString());
			patientProductDetails.add(patientProdDetails);
			totalChargesForProducts += patientProdDto.getProductTotalCharge();
		}
		
		for(PatientCreditDTO patientCreditDTO : patientCreditsDTos) {
			PatientPaymentDetails patientPaymentDetail = new PatientPaymentDetails();
			patientPaymentDetail.setPatientPaymentId(patientCreditDTO.getPatientCreditId());
			patientPaymentDetail.setPatientId(patientId);
			patientPaymentDetail.setCreditTypeId(patientCreditDTO.getCreditType() != null ?
					patientCreditDTO.getCreditType().getCreditTypeId() : null);
			patientPaymentDetail.setCreditName(patientCreditDTO.getCreditType() != null? patientCreditDTO.getCreditType().getCreditType() : null);
			patientPaymentDetail.setCreditAmount(patientCreditDTO.getCreditAmount() != null ? patientCreditDTO.getCreditAmount().toString() : null);
			patientPaymentDetail.setCreditDate(DateUtil.dateTimeToString(patientCreditDTO.getCreditDate()));
			patientPaymentDetail.setComment(patientCreditDTO.getComment());
			patientPaymentDetail.setCreditApplicationId((patientCreditDTO.getCreditApplication() != null ?
					patientCreditDTO.getCreditApplication().getCreditApplicationID() : null));
			patientPaymentDetail.setCreditApplicationName(patientCreditDTO.getCreditApplication() != null
									? patientCreditDTO.getCreditApplication().getCreditApplication() : null);
			if(patientPaymentDetail.getCreditApplicationName() != null && 
					patientPaymentDetail.getCreditApplicationName().equalsIgnoreCase("Service")) {
				totalAmountPaidForServices += patientCreditDTO.getCreditAmount();
			} else if(patientPaymentDetail.getCreditApplicationName() != null && 
					patientPaymentDetail.getCreditApplicationName().equalsIgnoreCase("Product")) {
				totalAmountPaidForProducts += patientCreditDTO.getCreditAmount();
			}
			patientPaymentDetails.add(patientPaymentDetail);
			
		}
		totalAmount = totalChargesForServices + totalChargesForProducts;
		totalAmountPaid = totalAmountPaidForServices + totalAmountPaidForProducts;
		totalAmountLeft = totalAmount - totalAmountPaid;
		// SEtting the response Correctly.
		DecimalFormat decimalFormatter = new DecimalFormat("##.00");
		patientBillingDetails.setPatientChargeDetails(patientChargeDetails);
		patientBillingDetails.setPatientPaymentDetails(patientPaymentDetails);
		patientBillingDetails.setPatientProductDetails(patientProductDetails);
		patientBillingDetails.setTotalAmount(decimalFormatter.format(totalAmount));
		patientBillingDetails.setTotalAmountForServices(decimalFormatter.format(totalChargesForServices));
		patientBillingDetails.setTotalAmountForProducts(decimalFormatter.format(totalChargesForProducts));
		patientBillingDetails.setTotalAmountPaidForServices(decimalFormatter.format(totalAmountPaidForServices));
		patientBillingDetails.setTotalAmountPaidForProducts(decimalFormatter.format(totalAmountPaidForProducts));
		patientBillingDetails.setTotalAmountPaid(decimalFormatter.format(totalAmountPaid));
		patientBillingDetails.setTotalAmountLeft(decimalFormatter.format(totalAmountLeft));
		
}


	public static PatientChargeDTO convertToPatientChargeDTOs(
			PatientChargeDetails patientChargeDetails) {
		
		PatientChargeDTO patientChargeDTO = new PatientChargeDTO();
		patientChargeDTO.setPatientChargeID(patientChargeDetails.getPatientChargeId());
		
		if (StringUtils.isNotBlank(patientChargeDetails.getBaseCharge())) {
			patientChargeDTO.setBaseCharge(Double.valueOf(patientChargeDetails.getBaseCharge()));
		} else {
			patientChargeDTO.setBaseCharge(0.0);
		}
		
		patientChargeDTO.setChargeDate(DateUtil.stringToDateTime((patientChargeDetails.getChargeDate())));
		patientChargeDTO.setComment(patientChargeDetails.getComment());
		
		if (StringUtils.isNotBlank(patientChargeDetails.getDiscount())) {
			patientChargeDTO.setDiscount(Double.valueOf(patientChargeDetails.getDiscount()));
		} else {
			patientChargeDTO.setDiscount(0.0);
		}
		
		patientChargeDTO.setNumberofSessions(patientChargeDetails.getNumberOfSessions());
		
		if (StringUtils.isNotBlank(patientChargeDetails.getTax())) {
			patientChargeDTO.setTax(Double.valueOf(patientChargeDetails.getTax()));
		} else {
			patientChargeDTO.setTax(0.0);
		}
		
		if (StringUtils.isNotBlank(patientChargeDetails.getTotalCharge())) {
			patientChargeDTO.setTotalCharge(Double.valueOf(patientChargeDetails.getTotalCharge()));
		} else {
			patientChargeDTO.setTotalCharge(0.0);
		}
		
		return patientChargeDTO;
	}
	
	public static PatientProductDTO convertToPatientProductDTOs(
			PatientProductDetails patientProductDetails) {
		
		PatientProductDTO patientProductDTO = new PatientProductDTO();
		patientProductDTO.setPatientProductID(patientProductDetails.getPatientProductId());
		
		if (StringUtils.isNotBlank(patientProductDetails.getProductBaseCharge())) {
			patientProductDTO.setProductBaseCharge(Double
					.valueOf(patientProductDetails.getProductBaseCharge()));
		} else {
			patientProductDTO.setProductBaseCharge(0.0);
		}
			
		patientProductDTO.setDate(DateUtil.stringToDateTime(patientProductDetails.getDate()));
		
		if (patientProductDetails.getComment() != null) {
			patientProductDTO.setComment(patientProductDetails.getComment());
		}
		
		if (StringUtils.isNotBlank(patientProductDetails.getProductTotalCharge())) {
			patientProductDTO.setProductTotalCharge(Double
					.valueOf(patientProductDetails.getProductTotalCharge()));
		} else {
			patientProductDTO.setProductTotalCharge(0.0);
		}
		
		if (StringUtils.isNotBlank(patientProductDetails.getProductTax())) {
			patientProductDTO.setProdudctTax(Double
					.valueOf(patientProductDetails.getProductTax()));
		} else {
			patientProductDTO.setProdudctTax(0.0);
		}
		
		if (StringUtils.isNotBlank(patientProductDetails.getQuantity())) {
			patientProductDTO.setQuantity(Integer.valueOf(patientProductDetails
					.getQuantity()));
		} else {
			patientProductDTO.setQuantity(0);
		}
		
		return patientProductDTO;
	}


	public static List<PatientProductDetails> convertToPatientProduct(
			List<PatientProductDTO> patientProductsDTos, Integer patientId) {

		List<PatientProductDetails> patientProductDetails = null;
		for (PatientProductDTO dto : patientProductsDTos) {
			PatientProductDetails prodDetails = new PatientProductDetails();
			prodDetails.setPatientProductId(dto.getPatientProductID());
			PatientDTO patientDTO = dto.getPatient();
			if (null != patientDTO) {
				prodDetails.setPatientId(patientDTO.getPatientID());
				prodDetails.setFirstName(patientDTO.getFirstName());
				prodDetails.setLastName(patientDTO.getLastName());
			}
			prodDetails.setProductId(dto.getProduct() != null ? dto.getProduct().getProductID() : null);
			prodDetails.setComment(dto.getComment());
			prodDetails.setDate(DateUtil.dateTimeToString(dto.getDate()));
			prodDetails.setDiscount(dto.getDiscount() != null ? dto.getDiscount().toString() : null);
			prodDetails.setProductBaseCharge(dto.getProduct() != null ? dto
					.getProduct().getProductDefaultCharge() != null ? dto
					.getProduct().getProductDefaultCharge().toString() : null : null);

			prodDetails.setProductName(dto.getProduct() != null ? dto.getProduct().getProduct() : null);
			prodDetails.setProductTax(dto.getProdudctTax() != null ? dto.getProdudctTax().toString() : null);
			prodDetails.setProductTotalCharge(dto.getProductTotalCharge() != null ? dto.getProductTotalCharge().toString() : null);
			prodDetails.setQuantity(dto.getQuantity().toString());
			if (patientProductDetails != null) {
				patientProductDetails.add(prodDetails);
			} else {
				patientProductDetails = new ArrayList<PatientProductDetails>();
				patientProductDetails.add(prodDetails);
			}
		}
		return patientProductDetails;
	}

	public static List<PatientPaymentDetails> convertToPatientPayment(
			List<PatientCreditDTO> patientCreditsDTos, Integer patientId) {
		List<PatientPaymentDetails> patientPaymentDetails = null;
		for (PatientCreditDTO dto : patientCreditsDTos) {
			PatientPaymentDetails payment = new PatientPaymentDetails();
			payment.setPatientPaymentId(dto.getPatientCreditId());
			PatientDTO patientDTO = dto.getPatient();
			if (null != patientDTO) {
				payment.setPatientId(patientDTO.getPatientID());
				payment.setFirstName(patientDTO.getFirstName());
				payment.setLastName(patientDTO.getLastName());
			}
			payment.setCreditApplicationId(dto.getCreditApplication() != null ? dto.getCreditApplication().getCreditApplicationID() : null);
			payment.setCreditName(dto.getCreditType() != null ? dto.getCreditType().getCreditType() : null);
			payment.setCreditAmount(dto.getCreditAmount() != null ? dto.getCreditAmount().toString() : null);
			payment.setCreditDate(DateUtil.dateTimeToString(dto.getCreditDate()));
			payment.setComment(dto.getComment());
			payment.setCreditApplicationId(dto.getCreditApplication() != null ? dto.getCreditApplication().getCreditApplicationID() : null);
			payment.setCreditApplicationName(dto.getCreditApplication() != null ? dto.getCreditApplication().getCreditApplication() : null);
			if (patientPaymentDetails != null)
				patientPaymentDetails.add(payment);
			else {
				patientPaymentDetails = new ArrayList<PatientPaymentDetails>();
				patientPaymentDetails.add(payment);
			}
		}
		return patientPaymentDetails;
	}

	public static PatientCreditDTO convertToPatientPaymentDTOs(
			PatientPaymentDetails patientPaymentDetail) {
		
		PatientCreditDTO patientCreditDTO = new PatientCreditDTO();
		patientCreditDTO.setPatientCreditId(patientPaymentDetail.getPatientPaymentId());
		patientCreditDTO.setComment(patientPaymentDetail.getComment());
		patientCreditDTO.setCreditDate(DateUtil.stringToDateTime(patientPaymentDetail.getCreditDate()));
		patientCreditDTO.setCreditAmount(Double.valueOf(patientPaymentDetail.getCreditAmount()));
		
	return patientCreditDTO;
	}

	public static void convertToChargeTypeDTO(List<ChargeType> chargeTypes, List<ChargeTypeDTO> chargeTypeDTOs) {
		for (ChargeType chargeType : chargeTypes) {
			ChargeTypeDTO dto = new ChargeTypeDTO();
			if (null != chargeType.getChargeTypeId()) {
				dto.setChargeId(chargeType.getChargeTypeId());
			}
			dto.setCharge(chargeType.getChargeName());
			dto.setDefaultCharge(chargeType.getDefaultCharge());
			dto.setDisplay(chargeType.isDisplay());
			chargeTypeDTOs.add(dto);
		}
	}
	
	public static void convertToProductDTO(List<Product> products, List<ProductDTO> productDTOs) {
		for (Product product : products) {
			ProductDTO dto = new ProductDTO();
			if (null != product.getProductId()) {
				dto.setProductID(product.getProductId());
			}
			dto.setProduct(product.getName());
			dto.setProductDefaultCharge(product.getDefaultCharge());
			dto.setDisplay(product.isDisplay());
			productDTOs.add(dto);
		}
	}
	
	public static void convertToCreditTypesDTO(List<CreditType> creditTypes,
			List<CreditTypesDTO> creditTypeDTOs) {
		for (CreditType creditType : creditTypes) {
			CreditTypesDTO dto = new CreditTypesDTO();

			if (null != creditType.getCreditTypeId()) {
				dto.setCreditTypeId(creditType.getCreditTypeId());
			}
			dto.setCreditType(creditType.getCreditName());
			dto.setDisplay(creditType.isDisplay());
			creditTypeDTOs.add(dto);
		}
	}
	
	public static void convertToPatientEstimates(List<PatientEstimates> estimates,
			List<PatientEstimatesDTO> estimatesDTO) {
		if (null != estimatesDTO && estimatesDTO.size() > 0) {
			for (PatientEstimatesDTO dto : estimatesDTO) {
				PatientEstimates estimate = new PatientEstimates();
				estimate.setPatientEstimatesId(dto.getPatientEstimatesID());
				estimate.setPatientId(dto.getPatientID());
				estimate.setChargeTypeId(dto.getChargeTypeId());
				estimate.setDateOfService(DateUtil.dateToString(dto.getDateOfService()));
				estimate.setTypeOfService(dto.getTypeOfService());
				estimate.setDefaultCharge(dto.getDefaultCharge());
				estimate.setBaseCharge(dto.getBaseCharge());
				estimate.setDiscount(dto.getDiscount());
				estimate.setTax(dto.getTax());
				estimate.setTotalCharge(dto.getTotalCharge());
				estimate.setComments(dto.getComments());
				estimate.setNumberOfSessions(dto.getNumberOfSessions());
				estimates.add(estimate);
			}
		}
	}
	
	public static void convertToPatientEstimatesDTO(List<PatientEstimatesDTO> estimatesDTO,
			List<PatientEstimates> estimates) {
		if (null != estimates && estimates.size() > 0) {
			for (PatientEstimates estimate : estimates) {
				PatientEstimatesDTO estimateDTO = new PatientEstimatesDTO();
				estimateDTO.setPatientEstimatesID(estimate.getPatientEstimatesId());
				estimateDTO.setPatientID(estimate.getPatientId());
				estimateDTO.setChargeTypeId(estimate.getChargeTypeId());
				estimateDTO.setDateOfService(DateUtil.stringToDate(estimate.getDateOfService()));
				estimateDTO.setTypeOfService(estimate.getTypeOfService());
				estimateDTO.setDefaultCharge(estimate.getDefaultCharge());
				estimateDTO.setBaseCharge(estimate.getBaseCharge());
				estimateDTO.setDiscount(estimate.getDiscount());
				estimateDTO.setTax(estimate.getTax());
				estimateDTO.setTotalCharge(estimate.getTotalCharge());
				estimateDTO.setComments(estimate.getComments());
				estimateDTO.setNumberOfSessions(estimate.getNumberOfSessions());
				estimatesDTO.add(estimateDTO);
			}
		}
	}
	
	public static String getAbsolutePath(String uri){
		
		uri = uri.replaceFirst(uri.substring(uri.indexOf("WEB-INF")), "");
		uri = uri.replaceFirst("file:", "");
		
		return uri;
		
	}
	
	public static List<PatientDetails> convertToPatientDetails(
			List<PatientDTO> patientDTOs) {

		if (null != patientDTOs) {
			List<PatientDetails> patientDetails = new ArrayList<PatientDetails>();
			for (PatientDTO patientDTO : patientDTOs) {
				PatientDetails details = new PatientDetails();
				details.setPatientId(patientDTO.getPatientID());
				details.setFirstName(patientDTO.getFirstName());
				details.setLastName(patientDTO.getLastName());
				details.setAppointment(DateUtil.dateTimeToString(patientDTO.getAppointment()));
				patientDetails.add(details);
			}
			return patientDetails;
		}
		return null;
	}

}
