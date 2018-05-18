package net.luvina.insurance.daoimplservice;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.luvina.insurance.beanentity.TblInsurance;
/**
 * TblInsurance
 * @author nguyenthinhphu
 * DAO xu ly lay du lieu tu database bang TblInsurance
 */
@Repository
public interface InsuranceRepository extends JpaRepository<TblInsurance, Long> {
	
	/**
	 * findByInsuranceNumber
	 * @param insuranceNumber
	 * @return ListTblInsurance
	 */
	public List<TblInsurance> findByInsuranceNumber(String insuranceNumber);
	
	/**
	 * get List Insurance
	 * @param insuranceNumber
	 * @param idInsuranceUpdate
	 * @return list Insurance
	 */
	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM tbl_insurance "
					+ "WHERE tbl_insurance.insurance_number = ?1 AND tbl_insurance.insurance_internal_id <> ?2", 
					nativeQuery = true)
	public List<TblInsurance> getListInsuranceNotId(String insuranceNumber, int idInsuranceUpdate);
	
	/**
	 * insert vao bang TblInsurance
	 * @param insuranceId
	 * @param insuranceNumber
	 * @param insuranceStartDate
	 * @param insuranceEndDate
	 * @param place
	 */
	@Modifying
	@Transactional
	@Query(value = "insert into tbl_insurance(insurance_internal_id, insurance_number, insurance_start_date, insurance_end_date, place_of_register) VALUES (?1,?2,?3,?4,?5)", nativeQuery = true)
	public void insertTblInsurance(int insuranceId, String insuranceNumber, Date insuranceStartDate, Date insuranceEndDate,  String place);
	
	/**
	 * Get Max Id trong TblInssurance
	 * @return
	 */
	@Query(value = "select max(tbl_insurance.insurance_internal_id) from tbl_insurance", nativeQuery = true)
	public int getMaxTblInsurance();

	/**
	 * delete Insurance bang insurance_id
	 * @param idInsuranceUpdate
	 */
	@Modifying
	@Transactional
	public void deleteByInsuranceInternalId(int idInsuranceUpdate);
	
	/**
	 * Update Insurance
	 * @param insuranceId
	 * @param insuranceNumber
	 * @param insuranceStartDate
	 * @param insuranceEndDate
	 * @param placeOfRegister
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE tbl_insurance "
					+ "SET insurance_number = ?2, insurance_start_date = ?3, insurance_end_date = ?4, place_of_register = ?5 "
					+ "WHERE insurance_internal_id = ?1", nativeQuery = true)
	public void updateTblInsurance(int insuranceId, String insuranceNumber, Date insuranceStartDate, Date insuranceEndDate, String placeOfRegister);
}
