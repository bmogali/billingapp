package com.billingapp.rest.command;

import java.util.List;

import com.billingapp.rest.model.AppointmentsCriteria;
import com.billingapp.rest.model.Areas;
import com.billingapp.rest.model.ChargeType;
import com.billingapp.rest.model.CreditApplication;
import com.billingapp.rest.model.CreditType;
import com.billingapp.rest.model.Patient;
import com.billingapp.rest.model.PatientBillingDetails;
import com.billingapp.rest.model.PatientChargeDetails;
import com.billingapp.rest.model.PatientDetails;
import com.billingapp.rest.model.PatientEstimates;
import com.billingapp.rest.model.PatientImage;
import com.billingapp.rest.model.PatientImageParams;
import com.billingapp.rest.model.PatientPaymentDetails;
import com.billingapp.rest.model.PatientProcedure;
import com.billingapp.rest.model.PatientProductDetails;
import com.billingapp.rest.model.Procedure;
import com.billingapp.rest.model.Product;
import com.billingapp.rest.model.Reports;
import com.billingapp.rest.model.Role;
import com.billingapp.rest.model.ServicesReportCriteria;
import com.billingapp.rest.model.TaxRates;
import com.billingapp.rest.model.User;
import com.billingapp.rest.model.Form.Form;

public interface BillingAppCommand {
	
	public List<Procedure> searchProcedures(String procedureSearchCriteria);
	
	public List<Patient> searchPatients(String patientSearchCriteria);
	
	public Patient getPatientDetail(Integer patientId);
	
	public PatientBillingDetails getPatientBillingDetails(Integer patientId);
	
	public PatientProcedure getPatientProcedure(Integer patientProcedureID);

	public List<ChargeType> getAllServices();

	public List<Product> getAllProducts();

	public List<CreditType> getAllCreditTypes();

	public List<CreditApplication> getAllCreditApplications();

	public List<Form> getAllForms();

	public int saveOrUpdate(Patient patient);
	
	public int savePatientProcedure(PatientProcedure patientProcedure);
	
	public int savePatientCharges(List<PatientChargeDetails> patientChargeDetails);

	public boolean deletePatient(Integer patientId);

	public List<PatientChargeDetails> getPatientCharges(int patientId);

	public int savePatientProduct(List<PatientProductDetails> patientProducts);

	public List<PatientProductDetails> getPatientProducts(Integer patientId);

	public Integer savePatientPayment(List<PatientPaymentDetails> patientPayments);

	public List<PatientPaymentDetails> getPatientPayments(Integer patientId);
	
	public boolean saveServices(List<ChargeType> chargeTypes);
	
	public boolean saveProducts(List<Product> products);
	
	public boolean saveCreditTypes(List<CreditType> creditTypes);
	
	public Boolean deletePatientProcedure(Integer patientProcedureID);
	
	public Reports servicesReport(ServicesReportCriteria reportCriteria);
	
	public List<PatientEstimates> getPatientEstimates(Integer patientId);
	
	public int savePatientEstimates(List<PatientEstimates> estimates);
	
	public boolean deletePatientEstimates(List<Integer> patientEstimateIds);
	
	public PatientImage uploadPatientImage(PatientImageParams params);
	
	public List<PatientImage> getPatientImages(Integer patientId, boolean profile);
	
	public boolean deletePatientImages(List<Integer> patientImagesIds);
	
	public List<Areas> getAreas();
	
	public List<PatientDetails> getAppointments(AppointmentsCriteria appointmentsCriteria);
	
	public boolean deletePatientCharges(List<Integer> chargeIds);
	
	public boolean deletePatientProducts(List<Integer> productIds);
	
	public boolean deletePatientPayments(List<Integer> creditTypeIds);
	
	public boolean registerUser(User user);
	
	public List<Role> getRoles();
	
	public List<TaxRates> getTaxRates();
	
	public boolean saveTaxRates(List<TaxRates> rates);
	
}
