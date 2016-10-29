package com.billingapp.rest.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

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

@SuppressWarnings("unchecked")
@Repository
public class BillingAppDaoImpl implements BillingAppDao {

	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory.getLogger(BillingAppDaoImpl.class);
	public List<ProcedureDTO> searchProcedures(int procedureId, String procedureName) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ProcedureDTO.class);
		if (procedureId > 0) {
			criteria.add(Restrictions.like("procedureID", procedureId));
		} else {
			criteria.add(Restrictions.like("procedureName", "%" + procedureName + "%"));
		}
		
		List<ProcedureDTO> procedures = criteria.list();
		session.close();
		return procedures;
	}

	
	public List<PatientDTO> searchPatients(int patientId, String firstName,
			String lastName) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientDTO.class);

		if (patientId > 0) {
			criteria.add(Restrictions.like("patientID", patientId));
		} else {
			
			Criterion firstNameCriteria = Restrictions.like("firstName", "%" + firstName + "%");
			Criterion lastNameCriteria = Restrictions.like("lastName", "%" + lastName + "%");
					
			if (!"".equalsIgnoreCase(lastName)) {
				if(firstName.equalsIgnoreCase(lastName)){
					LogicalExpression orExp = Restrictions.or(firstNameCriteria, lastNameCriteria);
					criteria.add(orExp);
				} else {
					LogicalExpression orExp = Restrictions.and(firstNameCriteria, lastNameCriteria);
					criteria.add(orExp);
				}
			}
		}

		List<PatientDTO> patients = criteria.list();
		session.close();

		return patients;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public PatientProcedureDTO getPatientProcedure(Integer patientProcedureID) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientProcedureDTO.class);
		if (patientProcedureID > 0) {
			criteria.add(Restrictions.eq("patientProcedureId", patientProcedureID))
					.setFetchMode("procedureAreas", FetchMode.JOIN)
					.setFetchMode("areas", FetchMode.JOIN);
		} 
		PatientProcedureDTO patientProcedures = (PatientProcedureDTO) criteria.uniqueResult();
		session.close();
		return patientProcedures;
	}

	
	public List<PatientChargeDTO> searchChargesPatient(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientChargeDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patient.patientID", patientId));
		} 
		
		List<PatientChargeDTO> patientcharges = criteria.list();
		session.close();
		return patientcharges;
	}

	
	public List<PatientProductDTO> searchProductsForPatients(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientProductDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patient.patientID", patientId));
		} 
		
		List<PatientProductDTO> patientproducts = criteria.list();
		session.close();
		return patientproducts;
	}

	
	public List<PatientCreditDTO> searchCreditsForPatients(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientCreditDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patient.patientID", patientId));
		} 
		
		List<PatientCreditDTO> patientcredits = criteria.list();
		session.close();
		return patientcredits;
	}

	
	public List<ChargeTypeDTO> getAllServices() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ChargeTypeDTO.class)
				.add(Restrictions.eq("display", true))
				.addOrder(Order.asc("charge"));
		
		List<ChargeTypeDTO> chargeTypesDTOss = criteria.list();
		session.close();
		return chargeTypesDTOss;
	}
	
	
	public void saveServices(List<ChargeTypeDTO> chargeTypeDTOs) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			for (ChargeTypeDTO chargeTypeDTO : chargeTypeDTOs) {
				session.saveOrUpdate(chargeTypeDTO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the services:" + e.getMessage());
		} finally {
			session.close();
		}
	}
	
	
	public void saveProducts(List<ProductDTO> productDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			for (ProductDTO productDTO : productDTOs) {
				session.saveOrUpdate(productDTO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the products:" + e.getMessage());
		} finally {
			session.close();
		}
		
	}
	
	
	public void saveCreditTypes(List<CreditTypesDTO> creditTypesDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			for (CreditTypesDTO creditTypesDTO : creditTypesDTOs) {
				session.saveOrUpdate(creditTypesDTO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the credit types:" + e.getMessage());
		} finally {
			session.close();
		}
		
	}

	
	public List<ProductDTO> getAllProducts() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ProductDTO.class)
				.add(Restrictions.eq("display", true))
				.addOrder(Order.asc("product"));
		
		List<ProductDTO> productDTOs = criteria.list();
		session.close();
		return productDTOs;
	}

	
	public List<CreditApplicationDTO> getAllCreditApplications() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(CreditApplicationDTO.class);
		
		List<CreditApplicationDTO> creditApplicationDTOs = criteria.list();
		session.close();
		return creditApplicationDTOs;
	}

	
	public List<CreditTypesDTO> getAllCreditTypes() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(CreditTypesDTO.class)
				.add(Restrictions.eq("display", true))
				.addOrder(Order.asc("creditType"));
		
		List<CreditTypesDTO> creditTypesDTOs = criteria.list();
		session.close();
		return creditTypesDTOs;
	}

	
	public PatientDTO getPatientDetails(Integer patientId) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patientID", patientId))
					.setFetchMode("patientProcedures", FetchMode.JOIN);
		}

		PatientDTO patient = (PatientDTO) criteria.uniqueResult();
		session.close();
		
		return patient;
	}
	
	public PatientDTO getPatient(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patientID", patientId));
		} 
		List<PatientDTO> patients = criteria.list();
		
		session.close();
		
		return ((patients != null && patients.size() > 0)? patients.get(0) : null);
	}

	
	public List<FormDTO> getAllForms() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(FormDTO.class);
		
		List<FormDTO> formDTOs = criteria.list();
		session.close();
		return formDTOs;
	}

	
	public int saveOrUpdatePatient(PatientDTO patientDTO) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(patientDTO);
			session.getTransaction().commit();
			return patientDTO.getPatientID();
			
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the Patient:" + e.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	
	public boolean deletePatient(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			PatientDTO patientDTO = (PatientDTO) session.get(PatientDTO.class, patientId);
			if(patientDTO != null) {
				session.delete(patientDTO);
				session.getTransaction().commit();
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Exception while Deleting the  Patient");
			return Boolean.FALSE;
		} finally {
			session.close();
		}
		return Boolean.FALSE;
	}

	
	public int saveOrUpdatePatientProcedure(PatientProcedureDTO patientProcedureDTO) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(patientProcedureDTO);
			session.getTransaction().commit();
			return patientProcedureDTO.getPatientProcedureId();
			
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the Procedure:" + e.getMessage());
			return 0;
		} finally {
			session.close();
		}
	}

	
	public List<ChargeTypeDTO> searchCharge(int chargeTypeId, String chargeName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ChargeTypeDTO.class);
		if (chargeTypeId > 0) {
			criteria.add(Restrictions.like("chargeId", chargeTypeId));
		} else {
			criteria.add(Restrictions.like("charge", "%" + chargeName + "%"));
		}
		
		List<ChargeTypeDTO> chargesTypes = criteria.list();
		session.close();
		return chargesTypes;
	}
	
	
	public Map<Integer,ChargeTypeDTO> searchChargeType(List<Integer> chargeTypeIds) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Map<Integer, ChargeTypeDTO> chargeMap = new HashMap<Integer, ChargeTypeDTO>();
		Criteria criteria = session.createCriteria(ChargeTypeDTO.class);
		if (chargeTypeIds != null && chargeTypeIds.size() > 0) {
			Restrictions.in("chargeId", chargeTypeIds);
		}
		
		List<ChargeTypeDTO> chargesTypes = criteria.list();
		for(ChargeTypeDTO chargeTypeDTO :chargesTypes) {
			chargeMap.put(chargeTypeDTO.getChargeId(),chargeTypeDTO);
		}
		session.close();
		return chargeMap;
	}

	
	public void saveOrUpdatePatientChargeTypes(
			List<PatientChargeDTO> patientChargeDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Iterator<PatientChargeDTO> iterator = patientChargeDTOs.iterator();
			while(iterator.hasNext()) {
				session.saveOrUpdate(iterator.next());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the patient charges:" + e.getMessage());
		} finally {
			session.close();
		}
	}

	
	public List<PatientChargeDTO> getPatientCharges(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientChargeDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patient.patientID", patientId));
			criteria.addOrder(Order.desc("chargeDate"));
		} 
		List<PatientChargeDTO> patientCharges = criteria.list();
		session.close();
		return ((patientCharges != null ? patientCharges : null));
	}

	
	public List<ProductDTO> searchProduct(Integer productId , String productName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ProductDTO.class);
		if (productId > 0) {
			criteria.add(Restrictions.like("productID", productId));
		} else {
			criteria.add(Restrictions.like("product", "%" + productName + "%"));
		}
		
		List<ProductDTO> productTypes = criteria.list();
		session.close();
		return productTypes;
	}

	
	public void saveOrUpdatePatientProducts(
			List<PatientProductDTO> patientProductDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Iterator<PatientProductDTO> iterator = patientProductDTOs.iterator();
			while(iterator.hasNext()) {
				session.saveOrUpdate(iterator.next());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the Procedure:" + e.getMessage());
		} finally {
			session.close();
		}
		
	}

	
	public List<PatientProductDTO> getPatientProductss(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientProductDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patient.patientID", patientId));
			criteria.addOrder(Order.desc("date"));
		} 
		List<PatientProductDTO> patientProducts= criteria.list();
		session.close();
		return ((patientProducts != null ? patientProducts : null));
	}

	
	public List<PatientCreditDTO> getPatientCredits(Integer patientId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientCreditDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patient.patientID", patientId));
			criteria.addOrder(Order.desc("creditDate"));
		} 
		List<PatientCreditDTO> patientCreditss= criteria.list();
		session.close();
		return ((patientCreditss != null ? patientCreditss : null));
	}

	
	public List<CreditTypesDTO> searchCreditType(Integer creditTypeId,
			String creditType) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(CreditTypesDTO.class);
		if (creditTypeId > 0) {
			criteria.add(Restrictions.like("creditTypeId", creditTypeId));
		} else {
			criteria.add(Restrictions.like("creditType", "%" + creditType + "%"));
		}
		
		List<CreditTypesDTO> creditTypes = criteria.list();
		session.close();
		return creditTypes;
	}

	
	public List<CreditApplicationDTO> searchCreditApplication(
			Integer creditApplicationID, String creditApplication) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(CreditApplicationDTO.class);
		if (creditApplicationID > 0) {
			criteria.add(Restrictions.like("creditApplicationID", creditApplicationID));
		} else {
			criteria.add(Restrictions.like("creditApplication", "%" + creditApplication + "%"));
		}
		
		List<CreditApplicationDTO> creditApplicationTypes = criteria.list();
		session.close();
		return creditApplicationTypes;
	}

	
	public void saveOrUpdatePatientCredits(
			List<PatientCreditDTO> patientCreditDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Iterator<PatientCreditDTO> iterator = patientCreditDTOs.iterator();
			while(iterator.hasNext()) {
				session.saveOrUpdate(iterator.next());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the Procedure:" + e.getMessage());
		} finally {
			session.close();
		}
	}

	
	public boolean deletePatientProcedure(Integer patientProcedureID) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			PatientProcedureDTO dto = (PatientProcedureDTO) session.get(
					PatientProcedureDTO.class, patientProcedureID);
			if (dto != null) {
				session.delete(dto);
				session.getTransaction().commit();
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			logger.error("Exception while Deleting the Patient procedure");
			return Boolean.FALSE;
		} finally {
			session.close();
		}
		return Boolean.FALSE;
	}

	
	public List<PatientChargeDTO> servicesReport(
			ServicesReportCriteria reportCriteria) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = null;
		List<PatientChargeDTO> patientChargeDTOs = null;
		try {
			if( null != reportCriteria.getStartDateInDateFormat() &&  null != reportCriteria.getEndDateInDateFormat() ) {
			
				criteria = session.createCriteria(PatientChargeDTO.class).add(
						Restrictions.between("chargeDate",
								reportCriteria.getStartDateInDateFormat(),
								reportCriteria.getEndDateInDateFormat()));
				patientChargeDTOs = criteria.list();
				
			}			
		} catch (Exception e) {
			logger.error("Exception while fetching the service report");
			return null;
		} finally {
			session.close();
		}
		return patientChargeDTOs;
	}
	
	
	public List<PatientProductDTO> productsReport(
			ServicesReportCriteria reportCriteria) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = null;
		List<PatientProductDTO> patientProductDTOs = null;
		try {
			if( null != reportCriteria.getStartDateInDateFormat() &&  null != reportCriteria.getEndDateInDateFormat() ) {
			
				criteria = session.createCriteria(PatientProductDTO.class).add(
						Restrictions.between("date",
								reportCriteria.getStartDateInDateFormat(),
								reportCriteria.getEndDateInDateFormat()));
				patientProductDTOs = criteria.list();
				
			}			
		} catch (Exception e) {
			logger.error("Exception while fetching the products report");
			return null;
		} finally {
			session.close();
		}
		return patientProductDTOs;
	}
	
	
	public List<PatientCreditDTO> creditsReport(
			ServicesReportCriteria reportCriteria) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = null;
		List<PatientCreditDTO> patientCreditDTOs = null;
		try {
			if( null != reportCriteria.getStartDateInDateFormat() &&  null != reportCriteria.getEndDateInDateFormat() ) {
			
				criteria = session.createCriteria(PatientCreditDTO.class).add(
						Restrictions.between("creditDate",
								reportCriteria.getStartDateInDateFormat(),
								reportCriteria.getEndDateInDateFormat()));
				patientCreditDTOs = criteria.list();
				
			}			
		} catch (Exception e) {
			logger.error("Exception while fetching the Credits report");
			return null;
		} finally {
			session.close();
		}
		return patientCreditDTOs;
	}

	
	public List<PatientEstimatesDTO> getPatientEstimates(Integer patientId) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientEstimatesDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patientID", patientId));
		} 
		List<PatientEstimatesDTO> estimates = criteria.list();
		session.close();
		return ((estimates != null ? estimates : null));
		
	}

	
	public boolean saveOrUpdatePatientEstimates(
			List<PatientEstimatesDTO> patientEstimatesDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Iterator<PatientEstimatesDTO> iterator = patientEstimatesDTOs.iterator();
			while(iterator.hasNext()) {
				session.saveOrUpdate(iterator.next());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the Patient Estimates:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	
	public boolean deletePatientEstimates(List<Integer> patientEstimateIds) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Query q = session
					.createQuery("delete FROM patientestimates WHERE patientEstimatesID IN (:patientEstimatesIDs)");
			q.setParameterList("patientEstimatesIDs", patientEstimateIds);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while deleting Patient Estimates:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		
		return true;
	}
	

	
	public List<PatientImagesDTO> getPatientImages(Integer patientId, boolean profile) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(PatientImagesDTO.class);
		if (patientId > 0) {
			criteria.add(Restrictions.eq("patientID", patientId));
			criteria.add(Restrictions.eq("profile", profile));
			criteria.addOrder(Order.desc("date"));
		}
		List<PatientImagesDTO> images = criteria.list();
		session.close();
		return ((images != null ? images : null));
	}
	
	
	public boolean saveOrUpdatePatientImages(PatientImagesDTO patientImagesDTO) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(patientImagesDTO);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the Patient image:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	
	public boolean deletePatientImages(List<Integer> patientImagesIds) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Query q = session
					.createQuery("delete FROM patientimages WHERE patientImagesID IN (:patientImagesIDs)");
			q.setParameterList("patientImagesIDs", patientImagesIds);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while deleting Patient images:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		
		return true;
	}
	
	
	public List<PatientImagesDTO> getPatientImagesByImageId(
			List<Integer> patientImagesIds) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<PatientImagesDTO> imagesDTOs = null;
		try {
			Query q = session.createQuery("FROM patientimages WHERE patientImagesID IN (:patientImagesIDs)");
			q.setParameterList("patientImagesIDs", patientImagesIds);
			imagesDTOs = q.list();

		} catch (Exception e) {
			logger.error("Exception while deleting Patient images:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}

		return imagesDTOs;
	}

	
	public List<AreasDTO> getAreas() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(AreasDTO.class);
		List<AreasDTO> areasDTOs = criteria.list();
		session.close();
		return areasDTOs;
	}


	public List<PatientDTO> getAppointmentsOld(AppointmentsCriteria appointmentsCriteria) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<PatientDTO> patientDTOs = null;
		try {
			Query q = session.createQuery(" FROM patients WHERE appointment = current_date");
			patientDTOs = q.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while deleting Patient Estimates:" + e.getMessage());
			return null;
		} finally {
			session.close();
		}
		
		return patientDTOs;
	}
	
	
	public List<PatientDTO> getAppointments(AppointmentsCriteria appCriteria) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = null;
		List<PatientDTO> patientDTOs = null;
		try {
			if (null != appCriteria.getStartDateInDateFormat()
					&& null != appCriteria.getEndDateInDateFormat()) {
				criteria = session.createCriteria(PatientDTO.class).add(
						Restrictions.between("appointment",
								appCriteria.getStartDateInDateFormat(),
								appCriteria.getEndDateInDateFormat()));
				patientDTOs = criteria.list();
			}
		} catch (Exception e) {
			logger.error("Exception while fetching the patient appointments");
			return null;
		} finally {
			session.close();
		}
		return patientDTOs;
	}

	
	public boolean deletePatientCharges(List<Integer> chargeIds) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session
					.createQuery("delete FROM patientcharges WHERE patientChargeID IN (:chargeIds)");
			q.setParameterList("chargeIds", chargeIds);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while deleting Patient Services:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	
	public boolean deletePatientProducts(List<Integer> productIds) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session
					.createQuery("delete FROM patientproducts WHERE patientProductID IN (:productIds)");
			q.setParameterList("productIds", productIds);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while deleting Patient products:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	
	public boolean deletePatientPayments(List<Integer> creditTypeIds) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			Query q = session
					.createQuery("delete FROM patientcredits WHERE patientCreditId IN (:creditTypeIds)");
			q.setParameterList("creditTypeIds", creditTypeIds);
			q.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while deleting Patient Payments:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	public User findByName(String username) throws UsernameNotFoundException {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		if (null != username && !"".equalsIgnoreCase(username)) {
			criteria.add(Restrictions.eq("name", username))
			.setFetchMode("userRoles", FetchMode.JOIN);
		}
		User user = (User) criteria.uniqueResult();
		session.close();
		return user;
	}
	
	@Override
	public boolean saveOrUpdateUser(User user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the User:" + e.getMessage());
		} finally {
			session.close();
		}
		return true;
	}


	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Role.class);
		List<Role> roles = criteria.list();
		session.close();
		return roles;
	}


	@Override
	public List<TaxRatesDTO> getTaxRates() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(TaxRatesDTO.class);
		List<TaxRatesDTO> rates = criteria.list();
		session.close();
		return rates;
	}


	@Override
	public boolean saveTaxRates(List<TaxRatesDTO> ratesDTOs) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			for (TaxRatesDTO ratesDTO : ratesDTOs) {
				session.saveOrUpdate(ratesDTO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Exception while Saving or Updating the tax rates:" + e.getMessage());
			return false;
		} finally {
			session.close();
		}
		return true;
	}

}
