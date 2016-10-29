package com.billingapp.rest.command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import com.billingapp.rest.dao.BillingAppDao;
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
import com.billingapp.rest.dto.TaxRatesDTO;
import com.billingapp.rest.dto.UserRoles;
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
import com.billingapp.rest.utilities.DateUtil;
import com.billingapp.rest.utilities.DemaCareUtilities;

public class BillingAppCommandImpl implements BillingAppCommand {

	private BillingAppDao dao;
	
	@Value("${conf.patientImageAbsolutePath}") 
	private String patientImageAbsolutePath;
	
	@Value("${conf.patientImagesRelativePath}") 
	private String patientImagesRelativePath;
	
	private PasswordEncoder passwordEncoder;
	
	private static final Logger logger = LoggerFactory.getLogger(BillingAppCommandImpl.class);
	
	public BillingAppDao getDao() {
		return dao;
	}

	public void setDao(BillingAppDao dao) {
		this.dao = dao;
	}
	
	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<Procedure> searchProcedures(String procedureSearchCriteria) {
		
		int procedureId = 0;
		String procedureName = "";
		
		if (procedureSearchCriteria.matches("[0-9]+")) {
			procedureId = Integer.parseInt(procedureSearchCriteria);
		} else {
			procedureName = procedureSearchCriteria;
		}
		
		List<ProcedureDTO> procedureDtos = dao.searchProcedures(procedureId, procedureName);
		List<Procedure> procedures = new ArrayList<Procedure>();
		for (ProcedureDTO procedureDto : procedureDtos) {
			Procedure procedure = new Procedure();
			procedure.setProcedureId(procedureDto.getProcedureID());
			procedure.setProcedureName(procedureDto.getProcedureName());
			procedure.setProcedureForm(procedureDto.getProcedureForm());
			procedure.setProcedureReport(procedureDto.getProcedureReport());
			procedures.add(procedure);
		}
		return procedures;
	}

	public List<Patient> searchPatients(String patientSearchCriteria) {

		int patientid = 0;
		String firstName = "";
		String lastName = "";

		if (patientSearchCriteria.matches("[0-9]+")) {
			patientid = Integer.parseInt(patientSearchCriteria);
		} else {
			String[] names = patientSearchCriteria.split(" ");
			firstName = names[0];
			lastName = (names.length > 1) ? names[1] : names[0];
		}

		List<PatientDTO> patientDTOs = dao.searchPatients(patientid,
				firstName, lastName);

		List<Patient> patients = new ArrayList<Patient>();

		for (PatientDTO patientDTO : patientDTOs) {
			Patient patient = new Patient();
			patient.setFirstName(patientDTO.getFirstName());
			patient.setLastName(patientDTO.getLastName());
			patient.setPatientId(patientDTO.getPatientID());
			patient.setMailingAddress1(patientDTO.getMailingAddress1());
			patient.setMailingCity(patientDTO.getMailingCity());
			patients.add(patient);
		}

		return patients;
	}

	public List<ChargeType> getAllServices() {
		
		List<ChargeTypeDTO> chargeTypeDTOss = dao.getAllServices();
		
		List<ChargeType> chargeTypeDetails = null;
		for( ChargeTypeDTO chargeTypeDTO : chargeTypeDTOss) {
			ChargeType chargeTypeDetail = new ChargeType();
			chargeTypeDetail.setChargeTypeId(chargeTypeDTO.getChargeId());
			chargeTypeDetail.setChargeName(chargeTypeDTO.getCharge());
			chargeTypeDetail.setDefaultCharge(chargeTypeDTO.getDefaultCharge());
			chargeTypeDetail.setDisplay(chargeTypeDTO.isDisplay());
			if(chargeTypeDetails != null) {
				chargeTypeDetails.add(chargeTypeDetail);
			} else {
				chargeTypeDetails = new ArrayList<ChargeType>();
				chargeTypeDetails.add(chargeTypeDetail);
			}
		}
		return chargeTypeDetails;
	}
	
	
	public List<Product> getAllProducts() {
		
		List<ProductDTO> productDTOs = dao.getAllProducts();
		
		List<Product> products = null;
		for( ProductDTO productDTO : productDTOs) {
			Product product = new Product();
			product.setProductId(productDTO.getProductID());
			product.setName(productDTO.getProduct());
			product.setDefaultCharge(productDTO.getProductDefaultCharge());
			product.setDisplay(productDTO.isDisplay());
			if(products != null) {
				products.add(product);
			} else {
				products = new ArrayList<Product>();
				products.add(product);
			}
		}
		return products;
	}
	
	
	public List<CreditType> getAllCreditTypes() {
		
		List<CreditTypesDTO> creditTypesDTO = dao.getAllCreditTypes();
		
		List<CreditType> creditTypes = null;
		for( CreditTypesDTO creditTypeDTO : creditTypesDTO) {
			CreditType creditType = new CreditType();
			creditType.setCreditTypeId(creditTypeDTO.getCreditTypeId());
			creditType.setCreditName(creditTypeDTO.getCreditType());
			creditType.setDisplay(creditTypeDTO.isDisplay());
			if(creditTypes != null) {
				creditTypes.add(creditType);
			} else {
				creditTypes = new ArrayList<CreditType>();
				creditTypes.add(creditType);
			}
		}
		return creditTypes;
	}
	
	
	public List<CreditApplication> getAllCreditApplications() {
		
		List<CreditApplicationDTO> creditApplicationDTOs= dao.getAllCreditApplications();
		
		List<CreditApplication> creditApplications = null;
		for( CreditApplicationDTO creditApplicationDTO : creditApplicationDTOs) {
			CreditApplication creditApplication = new CreditApplication();
			creditApplication.setCreditApplicationId(creditApplicationDTO.getCreditApplicationID());
			creditApplication.setCreditApplicationName(creditApplicationDTO.getCreditApplication());
			if(creditApplications != null) {
				creditApplications.add(creditApplication);
			} else {
				creditApplications = new ArrayList<CreditApplication>();
				creditApplications.add(creditApplication);
			}
		}
		return creditApplications;
	}

	
	public Patient getPatientDetail(Integer patientId) {
		Patient patient = null;
		PatientDTO patientDTO = dao.getPatientDetails(patientId);
		if(patientDTO != null) {
			patient = DemaCareUtilities.convertToPatient(patientDTO);
			DemaCareUtilities.setPatientProcedures(patientDTO.getPatientProcedures(), patient);
			patient.setPatientBillingDetails(getPatientBillingDetails(patientId));
		}
		return patient;
	}
	

	public PatientBillingDetails getPatientBillingDetails(Integer patientId) {

		List<PatientChargeDTO> patientChargeDTOss = dao.getPatientCharges(patientId);
		List<PatientProductDTO> patientProductsDTos = dao.getPatientProductss(patientId);
		List<PatientCreditDTO> patientCreditsDTos = dao.getPatientCredits(patientId);
		PatientBillingDetails patientBillingDetails = new PatientBillingDetails();
		
		DemaCareUtilities.setPatientBillingDetails(patientChargeDTOss, patientProductsDTos, patientCreditsDTos, patientBillingDetails, patientId);
		
		return patientBillingDetails;
	}
	
	
	public PatientProcedure getPatientProcedure(Integer patientProcedureID) {
	
		PatientProcedure patientProcedure = null;
		PatientProcedureDTO patientProcedureDTO = dao.getPatientProcedure(patientProcedureID);
		
		if (null != patientProcedureDTO) {
			patientProcedure = DemaCareUtilities.convertToPatientProcedure(patientProcedureDTO);
		}
		
		return patientProcedure;
	}

	
	public List<Form> getAllForms() {
		
		List<FormDTO> formDTOs = dao.getAllForms();
		
		List<Form> forms= null;
		for( FormDTO formDTO : formDTOs) {
			Form form = new Form();
			form.setFormId(formDTO.getFormID());
			form.setFormName(formDTO.getFormName());
			form.setFormReport(formDTO.getFormName());
			if(forms != null) {
				forms.add(form);
			} else {
				forms = new ArrayList<Form>();
				forms.add(form);
			}
		}
		return forms;
	}

	
	public int saveOrUpdate(Patient patient) {
		if(patient != null) {
			PatientDTO patientDTO = DemaCareUtilities.convertToPatientDTO(patient);
			return dao.saveOrUpdatePatient(patientDTO);
		}
		return 0;
	}

	
	public boolean deletePatient(Integer patientId) {
		if(patientId != null) {
			return dao.deletePatient(patientId);
		}
		return Boolean.FALSE;
	}

	
	public int savePatientProcedure(PatientProcedure patientProcedure) {
		
		if (null != patientProcedure) {
			
			PatientProcedureDTO patientProcedureDTO =  DemaCareUtilities.convertToPatientProcedureDTO(patientProcedure);
			
			PatientDTO patientDTO = dao.getPatient(patientProcedure.getPatientId());
			
			if (null != patientDTO) {
				patientProcedureDTO.setPatient(patientDTO);
			}
			
			List<ProcedureDTO> procedureDTOs = dao.searchProcedures(patientProcedure.getProcedureId(), "");
			
			ProcedureDTO procedureDTO = (procedureDTOs != null && procedureDTOs.size() > 0)? procedureDTOs.get(0) : null ;
			patientProcedureDTO.setProcedureDTO(procedureDTO);
			
			return dao.saveOrUpdatePatientProcedure(patientProcedureDTO);
		}
		return 0;
	}

	
	public int savePatientCharges(List<PatientChargeDetails> patientChargeDetails) {
		
		List<PatientChargeDTO> patientChargeDTOs = new ArrayList<PatientChargeDTO>();
		
		if (null != patientChargeDetails && patientChargeDetails.size() > 0) {
			
			PatientDTO patientDTO = dao.getPatient(patientChargeDetails.get(0).getPatientId());
		
			for (PatientChargeDetails patienChargeDetail : patientChargeDetails) {

				PatientChargeDTO patientChargeDTO = DemaCareUtilities
						.convertToPatientChargeDTOs(patienChargeDetail);

				if (null != patientDTO) {
					patientChargeDTO.setPatient(patientDTO);
				}
				List<ChargeTypeDTO> chargeDTOs = dao.searchCharge(patienChargeDetail.getChargeTypeId(), "");
				ChargeTypeDTO chargeTypeDTO = (chargeDTOs != null && chargeDTOs.size() > 0) ? chargeDTOs.get(0) : null;
				patientChargeDTO.setChargeType(chargeTypeDTO);
				
				patientChargeDTOs.add(patientChargeDTO);
			}
			try{
				dao.saveOrUpdatePatientChargeTypes(patientChargeDTOs);	
			}
			catch(Exception e) {
				logger.error("Exception while Saving or Updating the Procedure:" + e.getMessage());
				return 0;
			}
			
			return patientDTO.getPatientID();
		}
		return 0;
	}

	
	public List<PatientChargeDetails> getPatientCharges(int patientId) {
		List<PatientChargeDetails> patientChargeDetails = null;
		List<PatientChargeDTO> patientChargeDTOss = dao.getPatientCharges(patientId);
		
		if (null != patientChargeDTOss) {
			patientChargeDetails = DemaCareUtilities.convertToPatientCharge(patientChargeDTOss,patientId);
		}
		
		return patientChargeDetails;
	}

	
	public int savePatientProduct(List<PatientProductDetails> patientProducts) {
		
		List<PatientProductDTO> patientProductDTOs = new ArrayList<PatientProductDTO>();
		
		if (null != patientProducts && patientProducts.size() > 0) {
			
			PatientDTO patientDTO = dao.getPatient(patientProducts.get(0).getPatientId());
		
			for (PatientProductDetails patienProdctDetail : patientProducts) {

				PatientProductDTO patientProductsDTO = DemaCareUtilities
						.convertToPatientProductDTOs(patienProdctDetail);

				if (null != patientDTO) {
					patientProductsDTO.setPatient(patientDTO);
				}
				List<ProductDTO> productDTOs = dao.searchProduct(patienProdctDetail.getProductId(), "");
				ProductDTO productDTO = (productDTOs != null && productDTOs.size() > 0) ? productDTOs.get(0) : null;
				patientProductsDTO.setProduct(productDTO);
				
				patientProductDTOs.add(patientProductsDTO);
			}
			try{
				dao.saveOrUpdatePatientProducts(patientProductDTOs);	
			}
			catch(Exception e) {
				logger.error("Exception while Saving or Updating the Patient Products:" + e.getMessage());
				return 0;
			}
			
			return patientDTO.getPatientID();
		}
		return 0;
	}

	
	public List<PatientProductDetails> getPatientProducts(Integer patientId) {
		List<PatientProductDetails> patientProductDetails = null;
		List<PatientProductDTO> patientProductsDTos = dao.getPatientProductss(patientId);
		
		if (null != patientProductsDTos) {
			patientProductDetails = DemaCareUtilities.convertToPatientProduct(patientProductsDTos,patientId);
		}
		return patientProductDetails;
	}

	
	public Integer savePatientPayment(List<PatientPaymentDetails> patientPayments) {
		
		List<PatientCreditDTO> patientCreditDTOs = new ArrayList<PatientCreditDTO>();
		
		if (null != patientPayments && patientPayments.size() > 0) {
			
			PatientDTO patientDTO = dao.getPatient(patientPayments.get(0).getPatientId());
		
			for (PatientPaymentDetails patienPaymentDetail : patientPayments) {

				PatientCreditDTO patientCreditsDTO = DemaCareUtilities
						.convertToPatientPaymentDTOs(patienPaymentDetail);

				if (null != patientDTO) {
					patientCreditsDTO.setPatient(patientDTO);
				}
				List<CreditTypesDTO> creditTypesDTOs = dao.searchCreditType(patienPaymentDetail.getCreditTypeId(), "");
				CreditTypesDTO creditDTO = (creditTypesDTOs != null && creditTypesDTOs.size() > 0) ? creditTypesDTOs.get(0) : null;
				patientCreditsDTO.setCreditType(creditDTO);
				
				List<CreditApplicationDTO> creditApplicationDTOs = dao.searchCreditApplication(patienPaymentDetail.getCreditApplicationId(), "");
				CreditApplicationDTO creditApplication = (creditApplicationDTOs != null && creditApplicationDTOs.size() > 0) ? creditApplicationDTOs.get(0) : null;
				patientCreditsDTO.setCreditApplication(creditApplication);
				
				patientCreditDTOs.add(patientCreditsDTO);
			}
			try{
				dao.saveOrUpdatePatientCredits(patientCreditDTOs);	
			}
			catch(Exception e) {
				logger.error("Exception while Saving or Updating the Patient Products:" + e.getMessage());
				return 0;
			}
			
			return patientDTO.getPatientID();
		}
		return 0;
	}

	
	public List<PatientPaymentDetails> getPatientPayments(Integer patientId) {
		List<PatientPaymentDetails> patientPaymentDetails = null;
		List<PatientCreditDTO> patientCreditsDTos = dao.getPatientCredits(patientId);
		
		if (null != patientCreditsDTos) {
			patientPaymentDetails = DemaCareUtilities.convertToPatientPayment(patientCreditsDTos,patientId);
		}
		return patientPaymentDetails;
	}

	
	public boolean saveServices(List<ChargeType> chargeTypes) {

		List<ChargeTypeDTO> chargeTypeDTOs = new ArrayList<ChargeTypeDTO>();

		if (null != chargeTypes && chargeTypes.size() > 0) {
			DemaCareUtilities.convertToChargeTypeDTO(chargeTypes, chargeTypeDTOs);
			try {
				dao.saveServices(chargeTypeDTOs);
			} catch (Exception e) {
				logger.error("Exception while Saving or Updating the services:"+ e.getMessage());
				return false;
			}
		}
		return true;
	}

	
	public boolean saveProducts(List<Product> products) {

		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		if (null != products && products.size() > 0) {
			DemaCareUtilities.convertToProductDTO(products, productDTOs);
			try {
				dao.saveProducts(productDTOs);
			} catch (Exception e) {
				logger.error("Exception while Saving or Updating the products:"+ e.getMessage());
				return false;
			}
		}
		return true;
	}

	
	public boolean saveCreditTypes(List<CreditType> creditTypes) {
		
		List<CreditTypesDTO> creditTypeDTOs = new ArrayList<CreditTypesDTO>();
		if (null != creditTypes && creditTypes.size() > 0) {
			DemaCareUtilities.convertToCreditTypesDTO(creditTypes, creditTypeDTOs);
			try {
				dao.saveCreditTypes(creditTypeDTOs);
			} catch (Exception e) {
				logger.error("Exception while Saving or Updating the credit types:"+ e.getMessage());
				return false;
			}
		}
		return true;
	}

	
	public Boolean deletePatientProcedure(Integer patientProcedureID) {
		if(patientProcedureID != null) {
			return dao.deletePatientProcedure(patientProcedureID);
		}
		return Boolean.FALSE;
		
	}
	
	
	public Reports servicesReport(ServicesReportCriteria reportCriteria) {
		
		Reports reports = new Reports();

		//Services
		List<PatientChargeDetails> patientChargeDetails = null;
		List<PatientChargeDTO> patientChargeDTOss = dao.servicesReport(reportCriteria);
		
		if (null != patientChargeDTOss) {
			patientChargeDetails = DemaCareUtilities.convertToPatientCharge(patientChargeDTOss, 0);
		}
		reports.setPatientChargeDetiails(patientChargeDetails);
		
		// Products
		List<PatientProductDetails> patientProductDetails = null;
		List<PatientProductDTO> patientProductsDTos = dao.productsReport(reportCriteria);
		
		if (null != patientProductsDTos) {
			patientProductDetails = DemaCareUtilities.convertToPatientProduct(patientProductsDTos, 0);
		}
		reports.setPatientProductDetiails(patientProductDetails);
		
		// Payment
		List<PatientPaymentDetails> patientPaymentDetails = null;
		List<PatientCreditDTO> patientCreditsDTos = dao.creditsReport(reportCriteria);
		
		if (null != patientCreditsDTos) {
			patientPaymentDetails = DemaCareUtilities.convertToPatientPayment(patientCreditsDTos, 0);
		}
		reports.setPatientPaymentDetiails(patientPaymentDetails);
		
		return reports;
	}

	
	public List<PatientEstimates> getPatientEstimates(Integer patientId) {
		
		List<PatientEstimates> estimates = new ArrayList<PatientEstimates>();
		
		List<PatientEstimatesDTO> estimatesDTO = dao.getPatientEstimates(patientId);
		
		DemaCareUtilities.convertToPatientEstimates(estimates, estimatesDTO);
		
		return estimates;
	}

	
	public int savePatientEstimates(List<PatientEstimates> estimates) {
		
		List<PatientEstimatesDTO> estimatesDTO = new ArrayList<PatientEstimatesDTO>();
		
		DemaCareUtilities.convertToPatientEstimatesDTO(estimatesDTO, estimates);
		
		boolean result = dao.saveOrUpdatePatientEstimates(estimatesDTO);
		
		if (result && null != estimatesDTO && estimatesDTO.size() > 0) {
			return estimatesDTO.get(0).getPatientID();
		}
		return 0;
	}

	
	public boolean deletePatientEstimates(List<Integer> patientEstimateIds) {
		
		boolean result = dao.deletePatientEstimates(patientEstimateIds);
		
		return result;
	}

	
	public PatientImage uploadPatientImage(PatientImageParams params) {
		
		try {
			
			Date date = new Date();
			String imageName = String.valueOf(date.getTime()) + ".jpg";
			Path path = Paths.get(patientImageAbsolutePath + imageName);
			
			//Files.deleteIfExists(path);
			Files.copy(params.getInputStream(), path);
			
			PatientImagesDTO dto = new PatientImagesDTO();
			dto.setPatientID(params.getPatientId());
			dto.setDescription(params.getImageDescription());
			dto.setFileName(params.getFileName());
			dto.setProfile(params.isProfile());
			dto.setDate(date);
			dto.setImageName(imageName);
			
			boolean result = dao.saveOrUpdatePatientImages(dto);

			if (result) {
				PatientImage image = new PatientImage();
				image.setPatientID(dto.getPatientID());
				image.setDescription(dto.getDescription());
				image.setDate(DateUtil.dateTimeToString(dto.getDate()));
				image.setImageName(patientImagesRelativePath + dto.getImageName());
				image.setFileName(dto.getFileName());
				image.setProfile(dto.isProfile());
				image.setPatientImagesID(dto.getPatientImagesID());
				return image;
			}
			
		} catch(Exception e) {
			logger.error("Error while uploading:"+e.getMessage());
		}
		return null;
	}

	
	public List<PatientImage> getPatientImages(Integer patientId, boolean profile) {
		
		logger.info("get patient images");

		List<PatientImagesDTO> imagesDTOs = dao.getPatientImages(patientId, false);
		List<PatientImage> images = new ArrayList<PatientImage>();

		if (null != imagesDTOs) {
			for (PatientImagesDTO dto : imagesDTOs) {
				PatientImage image = new PatientImage();
				image.setPatientID(dto.getPatientID());
				image.setDescription(dto.getDescription());
				image.setDate(DateUtil.dateTimeToString(dto.getDate()));
				image.setImageName(patientImagesRelativePath + dto.getImageName());
				image.setFileName(dto.getFileName());
				image.setProfile(dto.isProfile());
				image.setPatientImagesID(dto.getPatientImagesID());
				images.add(image);
			}
		}

		return images;
	}
	
	
	public boolean deletePatientImages(List<Integer> patientImagesIds) {
		try {
			List<PatientImagesDTO> imagesDTOs = dao.getPatientImagesByImageId(patientImagesIds);
			if (null != imagesDTOs && imagesDTOs.size() > 0) {
				for (PatientImagesDTO dto : imagesDTOs) {
					Path path = Paths.get(patientImageAbsolutePath + dto.getImageName());
					Files.deleteIfExists(path);
				}
			}
			if (null != patientImagesIds && patientImagesIds.size() > 0) {
				boolean result = dao.deletePatientImages(patientImagesIds);
				return result;
			}
		} catch (Exception e) {
			logger.error("Exception while deleting patient images:"+ e.getMessage());
		}
		return false;
	}

	
	public List<Areas> getAreas() {

		List<Areas> areasList = new ArrayList<Areas>();
		List<AreasDTO> areasDTOs = dao.getAreas();
		if (null != areasDTOs) {
			for (AreasDTO areasDTO : areasDTOs) {
				Areas areas = new Areas();
				areas.setAreaId(areasDTO.getAreaId());
				areas.setArea(areasDTO.getArea());
				areasList.add(areas);
			}
		}
		return areasList;
	}

	
	public List<PatientDetails> getAppointments(AppointmentsCriteria appointmentsCriteria) {
		
		List<PatientDTO> patientDTOs = dao.getAppointments(appointmentsCriteria);
		
		return DemaCareUtilities.convertToPatientDetails(patientDTOs);
		
	}

	
	public boolean deletePatientCharges(List<Integer> chargeIds) {
		boolean result = dao.deletePatientCharges(chargeIds);
		return result;
	}

	
	public boolean deletePatientProducts(List<Integer> productIds) {
		boolean result = dao.deletePatientProducts(productIds);
		return result;
	}

	
	public boolean deletePatientPayments(List<Integer> creditTypeIds) {
		boolean result = dao.deletePatientPayments(creditTypeIds);
		return result;
	}

	@Override
	public boolean registerUser(User user) {
		boolean result = false;
		com.billingapp.rest.dto.User userDto = new com.billingapp.rest.dto.User();
		userDto.setName(user.getName());
		userDto.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		Set<UserRoles> userRolesDto = new HashSet<UserRoles>();
		Set<Role> roles = user.getRoles();
		
		if(!CollectionUtils.isEmpty(roles)){
			for (Role role : roles) {
				UserRoles userRole = new UserRoles();
				userRole.setUser(userDto);
				com.billingapp.rest.dto.Role roleDto = new com.billingapp.rest.dto.Role();
				roleDto.setRoleId(role.getRoleId());
				roleDto.setName(role.getName());
				userRole.setRole(roleDto);
				userRolesDto.add(userRole);
			}
		}
		userDto.setUserRoles(userRolesDto);
		result = dao.saveOrUpdateUser(userDto);
		
		return result;
	}

	@Override
	public List<Role> getRoles() {
		List<com.billingapp.rest.dto.Role> rolesDto = dao.getRoles();
		List<Role> roles = new ArrayList<Role>();
		if (!CollectionUtils.isEmpty(rolesDto)) {
			for (com.billingapp.rest.dto.Role roleDto : rolesDto) {
				roles.add(new Role(roleDto.getRoleId(), roleDto.getName()));
			}
		}
		return roles;
	}

	@Override
	public List<TaxRates> getTaxRates() {
		List<TaxRatesDTO> dtos = dao.getTaxRates();
		List<TaxRates> taxRates = new ArrayList<TaxRates>();
		if (!CollectionUtils.isEmpty(dtos)) {
			for (TaxRatesDTO dto : dtos) {
				TaxRates rates = new TaxRates();
				rates.setTaxRateId(dto.getTaxRateId());
				rates.setTaxItem(dto.getTaxItem());
				rates.setTaxRate(dto.getTaxRate());
				taxRates.add(rates);
			}
		}
		return taxRates;
	}

	@Override
	public boolean saveTaxRates(List<TaxRates> rates) {
		List<TaxRatesDTO> ratesDTOs = new ArrayList<TaxRatesDTO>();
		if (!CollectionUtils.isEmpty(rates)) {
			for (TaxRates taxRate : rates) {
				TaxRatesDTO dto = new TaxRatesDTO();
				dto.setTaxRateId(taxRate.getTaxRateId());
				dto.setTaxItem(taxRate.getTaxItem());
				dto.setTaxRate(taxRate.getTaxRate());
				ratesDTOs.add(dto);
			}
		}
		return dao.saveTaxRates(ratesDTOs);
	}

}
