package com.billingapp.rest.dao;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.billingapp.rest.dto.AreasDTO;
import com.billingapp.rest.dto.ChargeTypeDTO;
import com.billingapp.rest.dto.CreditApplicationDTO;
import com.billingapp.rest.dto.CreditTypesDTO;
import com.billingapp.rest.dto.FormDTO;
import com.billingapp.rest.dto.PatientChargeDTO;
import com.billingapp.rest.dto.PatientCreditDTO;
import com.billingapp.rest.dto.PatientDTO;
import com.billingapp.rest.dto.PatientEstimatesDTO;
import com.billingapp.rest.dto.PatientImagesDTO;
import com.billingapp.rest.dto.PatientProcedureDTO;
import com.billingapp.rest.dto.PatientProductDTO;
import com.billingapp.rest.dto.ProcedureDTO;
import com.billingapp.rest.dto.ProductDTO;
import com.billingapp.rest.dto.Role;
import com.billingapp.rest.dto.TaxRatesDTO;
import com.billingapp.rest.dto.User;
import com.billingapp.rest.model.AppointmentsCriteria;
import com.billingapp.rest.model.ServicesReportCriteria;

public interface BillingAppDao {
	public List<ProcedureDTO> searchProcedures(int procedureId,
			String procedureName);

	public List<PatientDTO> searchPatients(int patientId, String firstName,
			String lastName);

	public List<PatientChargeDTO> searchChargesPatient(Integer patientId);

	public List<PatientProductDTO> searchProductsForPatients(Integer patientId);

	public List<PatientCreditDTO> searchCreditsForPatients(Integer patientId);

	public List<ChargeTypeDTO> searchCharge(int chargeTypeId,
			String chargeName);
	
	public List<ChargeTypeDTO> getAllServices();

	public List<ProductDTO> getAllProducts();

	public List<CreditApplicationDTO> getAllCreditApplications();

	public List<CreditTypesDTO> getAllCreditTypes();

	public PatientDTO getPatientDetails(Integer patientId);

	public PatientProcedureDTO getPatientProcedure(Integer patientProcedureID);
	
	public List<FormDTO> getAllForms();

	public int saveOrUpdatePatient(PatientDTO patientDTO);
	
	public int saveOrUpdatePatientProcedure(PatientProcedureDTO patientProcedureDTO);

	public boolean deletePatient(Integer patientId);
	
	public boolean deletePatientProcedure(Integer patientProcedureID);

	public PatientDTO getPatient(Integer patientId);

	Map<Integer, ChargeTypeDTO> searchChargeType(List<Integer> chargeTypeIds);

	public void saveOrUpdatePatientChargeTypes(List<PatientChargeDTO> patientChargeDTOs);

	List<PatientChargeDTO> getPatientCharges(Integer patientId);

	public List<ProductDTO> searchProduct(Integer productId, String productName);

	public void saveOrUpdatePatientProducts(List<PatientProductDTO> patientProductDTOs);

	public List<PatientProductDTO> getPatientProductss(Integer patientId);

	public List<PatientCreditDTO> getPatientCredits(Integer patientId);

	public List<CreditTypesDTO> searchCreditType(Integer creditTypeId,
			String string);

	public List<CreditApplicationDTO> searchCreditApplication(
			Integer creditApplicationId, String string);

	public void saveOrUpdatePatientCredits(
			List<PatientCreditDTO> patientCreditDTOs);

	public void saveServices(List<ChargeTypeDTO> chargeTypeDTOs);
	
	public void saveProducts(List<ProductDTO> productDTOs);
	
	public void saveCreditTypes(List<CreditTypesDTO> creditTypesDTOs);
	
	public List<PatientChargeDTO> servicesReport(ServicesReportCriteria reportCriteria);
	
	public List<PatientProductDTO> productsReport(ServicesReportCriteria reportCriteria);
	
	public List<PatientCreditDTO> creditsReport(ServicesReportCriteria reportCriteria);
	
	public List<PatientEstimatesDTO> getPatientEstimates(Integer patientId);
	
	public boolean saveOrUpdatePatientEstimates(List<PatientEstimatesDTO> patientEstimatesDTOs);
	
	public boolean deletePatientEstimates(List<Integer> patientEstimateIds);
	
	public List<PatientImagesDTO> getPatientImages(Integer patientId, boolean profile);
	
	public boolean saveOrUpdatePatientImages(PatientImagesDTO patientImagesDTOs);
	
	public boolean deletePatientImages(List<Integer> patientImagesIds);
	
	public List<PatientImagesDTO> getPatientImagesByImageId(List<Integer> patientImagesIds);
	
	public List<AreasDTO> getAreas();
	
	public List<PatientDTO> getAppointments(AppointmentsCriteria appointmentsCriteria);
	
	public boolean deletePatientCharges(List<Integer> chargeIds);
	
	public boolean deletePatientProducts(List<Integer> productIds);
	
	public boolean deletePatientPayments(List<Integer> creditTypeIds);
	
	public User findByName(String username) throws UsernameNotFoundException;
	
	public boolean saveOrUpdateUser(User user);
	
	public List<Role> getRoles();
	
	public List<TaxRatesDTO> getTaxRates();
	
	public boolean saveTaxRates(List<TaxRatesDTO> ratesDTOs);
	
}
