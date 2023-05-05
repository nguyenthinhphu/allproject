package com.vti.backend.servicelayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vti.backend.datalayer.IAccountRepository;
import com.vti.backend.datalayer.IDepartmentRepository;
import com.vti.backend.datalayer.IPositionRepository;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdate;
import com.vti.specification.AccountSpecification;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountRepository accountRepository;

	@Autowired
	private IDepartmentRepository departmentRepository;

	@Autowired
	private IPositionRepository positionRepository;

	@SuppressWarnings("deprecation")
	@Override
	public Page<Account> getAllAccount(Pageable pageable, String search) {
		Specification<Account> whereAccount = null;

		if (!search.isEmpty()) {
			AccountSpecification fullNameSpecification = new AccountSpecification("fullName", "LIKE", search);
			AccountSpecification emailSpecification = new AccountSpecification("email", "LIKE", search);
			AccountSpecification departmentSpecification = new AccountSpecification("department", "LIKE", search);

			whereAccount = Specification.where(fullNameSpecification).or(emailSpecification)
					.or(departmentSpecification);
		}

		return accountRepository.findAll(whereAccount, pageable);
	}

	@Override
	public Account getAccountById(int idInput) {
		return accountRepository.findById(idInput).get();
	}

	@Override
	public Account getAccountByUserName(String userNameAcc) {
		return accountRepository.findByUserName(userNameAcc);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void createAccount(AccountFormForCreating form) {
		Account account = new Account();
		account.setEmail(form.getEmail());
		account.setUserName(form.getUserName());
		account.setFullName(form.getFullName());

		Department department = departmentRepository.getById(form.getDepartmentId());
		account.setDepartment(department);

		Position position = positionRepository.getById(form.getPositionId());
		account.setPosition(position);
		accountRepository.save(account);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateAccountById(int id, AccountFormForUpdate form) {
		Account account = accountRepository.getById(id);
		account.setFullName(form.getFullName());

		Department department = departmentRepository.getById(form.getDepartmentId());
		account.setDepartment(department);

		Position position = positionRepository.getById(form.getPositionId());
		account.setPosition(position);

		accountRepository.save(account);
	}

	@Override
	public void deleteAccountById(int id) {
		accountRepository.deleteById(id);

	}

}
