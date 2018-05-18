package net.luvina.insurance.daoimplservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.luvina.insurance.beanentity.TblCompany;
/**
 * CompanyRepository
 * @author nguyenthinhphu
 * DAO xử lý dữ liệu lấy từ database
 *
 */
@Repository
public interface CompanyRepository extends JpaRepository<TblCompany, Long> {
	
	/**
	 * List All Company Sắp xếp theo tên
	 * @return list Tbl Company
	 */
	public List<TblCompany> findAllByOrderByCompanyNameAsc();
	
	/**
	 * getId By Company Name
	 * @param selectCompanyName
	 * @return list TblCompany
	 */
	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM tbl_company t WHERE t.company_name = ?1",
					nativeQuery = true)
	public List<TblCompany> getIdByCompanyName(String selectCompanyName);

	/**
	 * insert TblCompany
	 * @param companyId
	 * @param companyNameNew
	 * @param address
	 * @param email
	 * @param telephone
	 */
	@Modifying
	@Transactional
	@Query(value = "INSERT INTO tbl_company (company_internal_id,company_name, address, email, telephone) "
					+ "VALUES (?1, ?2, ?3, ?4,?5)", nativeQuery = true)
	public void insertCompany(int companyId,String companyNameNew, String address, String email, String telephone);
	
	/**
	 * Selex Max id trong bang tbl_company
	 * @return id
	 */
	@Transactional
	@Query(value = "SELECT MAX(company_internal_id) FROM tbl_company", nativeQuery = true)
	public int getMaxCompanyId();
	
	/**
	 * tim kiem boi company Name
	 * @param companyName
	 * @return
	 */
	public TblCompany findByCompanyName(String companyName);
	/*@Modifying
	@Transactional
	@Query(value = "SELECT t.company_internal_id, company_name FROM tbl_company t ",
					nativeQuery = true)
	List<Object[]> list();*/
	/**
	 * tim kiem company boi company internal Id
	 * @param id
	 * @return
	 */
	public TblCompany findByCompanyInternalId(int id);
}
