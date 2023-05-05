package com.vti.backend.servicelayer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdate;

public interface IAccountService {
	public Page<Account> getAllAccount(Pageable pageable, String search);

	public Account getAccountById(int idInput);

	public Account getAccountByUserName(String userNameAcc);

	public void createAccount(AccountFormForCreating form);

	public void updateAccountById(int id, AccountFormForUpdate form);

	public void deleteAccountById(int id);
}
