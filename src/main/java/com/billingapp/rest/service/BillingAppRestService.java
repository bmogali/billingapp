package com.billingapp.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import com.billingapp.rest.model.AppointmentsCriteria;
import com.billingapp.rest.model.Areas;
import com.billingapp.rest.model.ChargeType;
import com.billingapp.rest.model.CreditApplication;
import com.billingapp.rest.model.CreditType;
import com.billingapp.rest.model.Patient;
import com.billingapp.rest.model.PatientChargeDetails;
import com.billingapp.rest.model.PatientDetails;
import com.billingapp.rest.model.PatientEstimates;
import com.billingapp.rest.model.PatientImage;
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

@Produces("application/json")
public interface BillingAppRestService {
	
	@Consumes("application/json")
	public List<Procedure> searchProcedures(String proceduresSearchCriteria);

	public List<Patient> searchPatients(String patientSearchCriteria);
	
	public Patient getPatientDetail(Integer patientId);
	
	public PatientProcedure getPatientProcedure(Integer patientProcedureID);
	
	public List<ChargeType> getAllServices();

	public List<Product> getAllProducts();
		
	public List<Form> getAllForms();
	
	public List<CreditApplication> getAllCreditApplications();
	public List<CreditType> getAllCreditTypes();
	
	public Patient saveOrUpdatePatient(Patient patient);
	public PatientProcedure savePatientProcedure(PatientProcedure patientProcedure);
	public List<PatientChargeDetails> savePatientServiceCharges(List<PatientChargeDetails> patientChargeDetails);
	
	public List<PatientProductDetails> savePatientProductCharges(List<PatientProductDetails> patientProducts);
	
	public Boolean deletePatient(Integer patientId);
	
	public boolean saveServices(List<ChargeType> chargeTypes);
	
	public boolean saveProducts(List<Product> products);
	
	public boolean saveCreditTypes(List<CreditType> creditTypes);
	
	public Boolean deletePatientProcedure(Integer patientProcedureID);
	
	public Reports servicesReport(ServicesReportCriteria reportCriteria);
	
	public List<PatientEstimates> getPatientEstimates(Integer patientId);
	
	public boolean deletePatientEstimates(List<Integer> patientEstimateIds);
	
	public List<PatientImage> getPatientImages(Integer patientId);
	
	public PatientImage uploadPatientImage(Attachment attachment,
			int patientId, String imageDescription, String fileName, boolean profile);
	
	public boolean deletePatientImages(List<Integer> patientImagesIds);
	
	public List<Areas> getAreas();
	
	public List<PatientDetails> getAppointments(AppointmentsCriteria appointmentsCriteria);
	
	public boolean deletePatientCharges(List<Integer> chargeIds);
	
	public boolean deletePatientProducts(List<Integer> productIds);
	
	public boolean deletePatientPayments(List<Integer> creditTypeIds);
	
	public boolean registerUser(User user);
	
	public boolean authenticate();
	
	public boolean logout();
	
	public List<Role> getRoles();
	
	public List<TaxRates> getTaxRates();
	
	public boolean saveTaxRates(List<TaxRates> rates);
	
}
