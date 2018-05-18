package net.luvina.insurance.daoimplservice;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.luvina.insurance.beanentity.TblUser;

/**
 * UserRepository
 * 
 * @author nguyenthinhphu DAO xu ly bang tbl_user trong DB
 */
@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {

	/**
	 * findBy UserName va Password
	 * 
	 * @param username
	 * @param password
	 * @return List TblUser
	 */
	public List<TblUser> findByUsernameAndPassword(String username, String password);

	/**
	 * ListUser Info
	 * 
	 * @return List TblUser
	 */
	@Query(value = "SELECT * FROM tbl_user u " + "INNER JOIN tbl_insurance i "
			+ "ON u.insurance_internal_id = i.insurance_internal_id " + "ORDER BY u.user_full_name", nativeQuery = true)
	public List<TblUser> listUserInfos();

	/**
	 * List User By id
	 * 
	 * @param idUser
	 * @return list tbl_user
	 */
	@Query(value = "SELECT * FROM tbl_user u " + "INNER JOIN tbl_insurance i "
			+ "ON u.insurance_internal_id = i.insurance_internal_id " + "INNER JOIN tbl_company t "
			+ "ON u.company_internal_id = t.company_internal_id " + "WHERE u.user_internal_id = ?1", nativeQuery = true)
	public List<TblUser> listUserInfos(int idUser);

	/**
	 * insert vao bang tbl_user
	 * 
	 * @param companyInternalId
	 * @param insuranceInternalId
	 * @param userFullname
	 * @param userSexDivision
	 * @param birthdate
	 */
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO tbl_user ( company_internal_id,insurance_internal_id, user_full_name, user_sex_division, birthdate) "
			+ "VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
	public void insertUser(int companyInternalId, int insuranceInternalId, String userFullname, String userSexDivision,
			Date birthdate);

	/**
	 * Delete User By Id
	 * 
	 * @param idUser
	 */
	@Modifying
	@Transactional
	@Query(value = "delete from tbl_user where user_internal_id = ?1", nativeQuery = true)
	public void deleteUser(int idUser);

	/**
	 * update TblUser
	 * 
	 * @param userId
	 * @param companyInternalId
	 * @param insuranceInternalId
	 * @param userFullname
	 * @param userSexDivision
	 * @param birthdate
	 */
	@Modifying
	@Transactional
	@Query(value = "UPDATE tbl_user "
			+ "SET company_internal_id = ?2, insurance_internal_id = ?3, user_full_name = ?4, user_sex_division = ?5, birthdate = ?6 "
			+ "WHERE user_internal_id = ?1", nativeQuery = true)
	public void updateTblUser(int userId, int companyInternalId, int insuranceInternalId, String userFullname,
			String userSexDivision, Date birthdate);

	/**
	 * Tìm kiếm User By Company name
	 * 
	 * @param companyName
	 * @param page
	 * @return List User
	 */
	public List<TblUser> findByTblCompanyCompanyName(String companyName, Pageable page);

	/**
	 * Tìm kiếm User By User Id
	 * 
	 * @param userId
	 * @return TblUser
	 */
	public TblUser findByUserInternalId(int userId);

	/**
	 * Test Thử MH Test Tìm kiếm User By Company Id
	 * 
	 * @param id
	 * @return
	 */
	public List<TblUser> findByCompanyInternalId(int id);

	/**
	 * Find Theo đk tìm kiếm Test Thử MH Test Angular Js
	 * 
	 * @param userFullname
	 * @param placeOfRe
	 * @param insuranceNum
	 * @return
	 */
//	public List<TblUser> findByUserFullnameContainsAndTblInsurancePlaceOfRegisterContainsAndTblInsuranceInsuranceNumberContains(
//			String userFullname, String placeOfRe, String insuranceNum);

	@Query(value = "Select u.user_internal_id, u.company_internal_id, u.insurance_internal_id,  u.username, u.password, u.user_full_name, u.user_sex_division,u.birthdate"
			+ " from tbl_user u left outer join tbl_insurance i on u.insurance_internal_id = i.insurance_internal_id "
			+ " left outer join tbl_company c on u.company_internal_id = c.company_internal_id" + " where "
			+ " u.user_full_name like %:userFullname%" + " and i.insurance_number like %:insuranceNumber%"
			+ " and i.place_of_register like %:placeOfRegister%" + " and c.company_name = :companyName", nativeQuery = true)
	public List<TblUser> searchDataUsers(@Param("userFullname") String userFullname,
			@Param("insuranceNumber") String insuranceNumber, @Param("placeOfRegister") String placeOfRegister,
			@Param("companyName") String companyName);
}
