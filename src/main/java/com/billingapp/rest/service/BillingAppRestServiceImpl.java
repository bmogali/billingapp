package com.billingapp.rest.service;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import com.billingapp.rest.command.BillingAppCommand;
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

@GZIP
public class BillingAppRestServiceImpl implements BillingAppRestService {
	
	private BillingAppCommand command;
	
	public BillingAppCommand getCommand() {
		return command;
	}

	public void setCommand(BillingAppCommand command) {
		this.command = command;
	}

	// Patient operations - Start
	
	@POST
	@Path("/searchPatients/")
	public List<Patient> searchPatients(
			@FormParam("patientSearchCriteria") String patientSearchCriteria) {

		List<Patient> patients = command.searchPatients(patientSearchCriteria);

		return patients;
	}
	
	@POST
	@Path("/getPatientDetail/")
	public Patient getPatientDetail(@FormParam("patientId") Integer patientId) {

		Patient patient = command.getPatientDetail(patientId);
		
		return patient;
	}
	
	@POST
	@Path("/saveOrUpdatePatient/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Patient saveOrUpdatePatient(Patient patient) {
		int patientId = command.saveOrUpdate(patient);
		if (patientId > 0) {
			return command.getPatientDetail(patientId);
		}
		
		return null;
	}
	
	@POST
	@Path("/deletePatient/")
	public Boolean deletePatient(@FormParam("patientId") Integer patientId) {
		boolean success = command.deletePatient(patientId);
		if (success) {
			return success;
		} else
			return Boolean.FALSE;
	}
	
	
	@POST
	@Path("/getAppointments/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PatientDetails> getAppointments(AppointmentsCriteria appointmentsCriteria) {
		return command.getAppointments(appointmentsCriteria);
	}
	
	// Patient operations - End
	
	// Patient Procedure operations - Start
	
	@POST
	@Path("/getPatientProcedure/")
	public PatientProcedure getPatientProcedure(@FormParam("patientProcedureId") Integer patientProcedureID) {
		
		PatientProcedure patientProcedure = command.getPatientProcedure(patientProcedureID);
		
		return patientProcedure;
	}
	
	@POST
	@Path("/savePatientProcedure/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PatientProcedure savePatientProcedure(PatientProcedure patientProcedure) {

		int patientProcedureId = command.savePatientProcedure(patientProcedure);
		
		if(patientProcedureId > 0) {
			return command.getPatientProcedure(patientProcedureId);
		}
		
		return null;
	}
	
	@POST
	@Path("/searchProcedures/")
	public List<Procedure> searchProcedures(
			@FormParam("procedureSearchCriteria") String procedureSearchCriteria) {

		List<Procedure> procedures = command.searchProcedures(procedureSearchCriteria);

		return procedures;
	}
	
	@POST
	@Path("/deletePatientProcedure/")
	public Boolean deletePatientProcedure(@FormParam("patientProcedureId") Integer patientProcedureID) {
		boolean success = command.deletePatientProcedure(patientProcedureID);
		if (success) {
			return success;
		} else
			return Boolean.FALSE;
	}
	
	@POST
	@Path("/getAreas/")
	public List<Areas> getAreas() {
		return command.getAreas();
	}	
	
	// Patient Procedure operations - End
	
	// Save patient billing operations - start
	
	@POST
	@Path("/savePatientCharges/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PatientChargeDetails> savePatientServiceCharges(List<PatientChargeDetails> patientChargeDetails) {

		 int patientId = command.savePatientCharges(patientChargeDetails);
		if(patientId > 0) {
			return command.getPatientCharges(patientId);
		}
		return null;
	}
	
	@POST
	@Path("/savePatientProducts/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PatientProductDetails> savePatientProductCharges(List<PatientProductDetails> patientProducts) {
		 Integer patientId = command.savePatientProduct(patientProducts);
			if(patientId > 0) {
				return command.getPatientProducts(patientId);
			}
			return null;
	}

	@POST
	@Path("/savePatientPayments/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PatientPaymentDetails> savePatientPayments(List<PatientPaymentDetails> patientPayments) {
		 Integer patientId = command.savePatientPayment(patientPayments);
			if(patientId > 0) {
				return command.getPatientPayments(patientId);
			}
			return null;
	}
	
	@POST
	@Path("/deletePatientCharges/")
	public boolean deletePatientCharges(List<Integer> chargeIds) {
		boolean result = command.deletePatientCharges(chargeIds);
		return result;
	}

	@POST
	@Path("/deletePatientProducts/")
	public boolean deletePatientProducts(List<Integer> productIds) {
		boolean result = command.deletePatientProducts(productIds);
		return result;
	}

	@POST
	@Path("/deletePatientPayments/")
	public boolean deletePatientPayments(List<Integer> creditTypeIds) {
		boolean result = command.deletePatientPayments(creditTypeIds);
		return result;
	}
	
	// Patient billing operations - End
	
	// Billing operations - Start
	
	@POST
	@Path("/getAllServices/")
	public List<ChargeType> getAllServices() {

		List<ChargeType> chargeTypeDetails = command.getAllServices();

		return chargeTypeDetails;
	}

	@POST
	@Path("/getAllProducts/")
	public List<Product> getAllProducts() {
		List<Product> listOfAllProducts = command.getAllProducts();

		return listOfAllProducts;
	}

	@POST
	@Path("/getAllCreditTypes/")
	public List<CreditType> getAllCreditTypes() {
		List<CreditType> listOfAllCreditTypes = command.getAllCreditTypes();

		return listOfAllCreditTypes;
	}
	
	@POST
	@Path("/saveServices/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean saveServices(List<ChargeType> chargeTypes) {

		return command.saveServices(chargeTypes);
	}
	
	@POST
	@Path("/saveProducts/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean saveProducts(List<Product> products) {

		return command.saveProducts(products);
	}
	
	@POST
	@Path("/saveCreditTypes/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean saveCreditTypes(List<CreditType> creditTypes) {

		return command.saveCreditTypes(creditTypes);
	}
	
	// Billing operations - End
	
	@POST
	@Path("/getPatientEstimates/")
	public List<PatientEstimates> getPatientEstimates(@FormParam("patientId") Integer patientId) {
		
		List<PatientEstimates> estimates = command.getPatientEstimates(patientId);
		
		return estimates;
	}
	
	@POST
	@Path("/savePatientEstimates/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PatientEstimates> savePatientEstimates(
			List<PatientEstimates> patientEstimates) {

		int patientId = command.savePatientEstimates(patientEstimates);

		if (patientId > 0) {
			return command.getPatientEstimates(patientId);
		}
		return null;
	}	
	

	@POST
	@Path("/deletePatientEstimates/")
	public boolean deletePatientEstimates(List<Integer> patientEstimateIds) {

		boolean result = command.deletePatientEstimates(patientEstimateIds);

		return result;

	}
	
	@POST
	@Path("/servicesReport/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Reports servicesReport(ServicesReportCriteria reportCriteria) {
		
		return command.servicesReport(reportCriteria);
		
	}
	
	@POST
	@Path("/getPatientImages/")
	public List<PatientImage> getPatientImages(@FormParam("patientId") Integer patientId) {
		
		List<PatientImage> patientImages = command.getPatientImages(patientId, false);
		
		return patientImages;
	}
	
	@POST
	@Path("/uploadPatientImage/")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	public PatientImage uploadPatientImage(Attachment attachment,
			@Multipart("patientId") int patientId,
			@Multipart("imageDescription") String imageDescription, @Multipart("fileName") String fileName, @Multipart("profile") boolean profile) {
 
		InputStream in = attachment.getObject(InputStream.class);
		
		PatientImageParams params = new PatientImageParams();
		params.setInputStream(in);
		params.setImageDescription(imageDescription);
		params.setFileName(fileName);
		params.setPatientId(patientId);
		params.setProfile(profile);
		
		PatientImage patientImage = command.uploadPatientImage(params);
		
		return patientImage;
		
    }
	
	@POST
	@Path("/deletePatientImages/")
	public boolean deletePatientImages(List<Integer> patientImagesIds) {
		
		boolean result = command.deletePatientImages(patientImagesIds);
		
		return result;
	}
	
	@POST
	@Path("/getAllForms/")
	public List<Form> getAllForms() {
		List<Form> listOfAllForms = command.getAllForms();

		return listOfAllForms;
	}

	@POST
	@Path("/getAllCreditApplications/")
	public List<CreditApplication> getAllCreditApplications() {
		List<CreditApplication> listOfAllCreditApplications = command.getAllCreditApplications();

		return listOfAllCreditApplications;
	}

	// Login operations - Start
	
	@POST
	@Path("/user/registerUser/")
	public boolean registerUser(User user) {
		
		boolean result = command.registerUser(user);
		
		return result;
	}

	@POST
	@Path("/user/authenticate/")
	public boolean authenticate() {
		
		return true;
	}
	
	@POST
	@Path("/user/logout/")
	public boolean logout() {
		
		return true;
	}

	@POST
	@Path("/user/getRoles/")
	public List<Role> getRoles() {
		return command.getRoles();
	}

	// Login operations - End
	
	@POST
	@Path("/getTaxRates/")
	public List<TaxRates> getTaxRates() {
		return command.getTaxRates();
	}

	@POST
	@Path("/saveTaxRates/")
	public boolean saveTaxRates(List<TaxRates> rates) {
		return command.saveTaxRates(rates);
	}

	
}
