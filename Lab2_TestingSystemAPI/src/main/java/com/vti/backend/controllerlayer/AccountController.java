package com.vti.backend.controllerlayer;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.AccountDTO;
import com.vti.backend.servicelayer.IAccountService;
import com.vti.entity.Account;
import com.vti.form.AccountFormForCreating;
import com.vti.form.AccountFormForUpdate;

@RestController
@RequestMapping(value = "api/v1/accounts")
@CrossOrigin("*")
public class AccountController {

	@Autowired
	private IAccountService service;

	// Get All Account
	@GetMapping()
	public ResponseEntity<?> getAllAccount(Pageable pageable, @RequestParam(required = false) String search) {
		Page<Account> listAccounts = service.getAllAccount(pageable, search);

		Page<AccountDTO> listAccountDTOs = listAccounts.map(new Function<Account, AccountDTO>() {
			@Override
			public AccountDTO apply(Account account) {
				AccountDTO accountDTO = new AccountDTO();
				accountDTO.setId(account.getId());
				accountDTO.setEmail(account.getEmail());
				accountDTO.setUserName(account.getUserName());
				accountDTO.setFullName(account.getFullName());
				accountDTO.setDepartment(account.getDepartment().getName().toString());
				accountDTO.setPosition(account.getPosition().getName().toString());
				accountDTO.setCreateDate(account.getCreateDate());
				return accountDTO;
			}
		});

		return new ResponseEntity<>(listAccountDTOs, HttpStatus.OK);

	}

	// Get Account By Id
	@GetMapping("/{id}")
	public ResponseEntity<?> getAccountById(@PathVariable(name = "id") int idInput) {
		Account account = service.getAccountById(idInput);

		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setEmail(account.getEmail());
		accountDTO.setUserName(account.getUserName());
		accountDTO.setFullName(account.getFullName());

//		DepartmentDTO departmentDTO = new DepartmentDTO();
//		departmentDTO.setId(account.getDepartment().getId());
//		departmentDTO.setName(account.getDepartment().getName());
		accountDTO.setDepartment(account.getDepartment().getName().toString());

//		PositionDTO positionDTO = new PositionDTO();
//		positionDTO.setId(account.getPosition().getId());
////		positionDTO.setName(account.getPosition().getName());
		accountDTO.setPosition(account.getPosition().getName().toString());

		accountDTO.setCreateDate(account.getCreateDate());

		return new ResponseEntity<>(accountDTO, HttpStatus.OK);

	}

	// Get Account By Name
	@GetMapping("/username/{username}")
	public ResponseEntity<?> getAccountByName(@PathVariable(name = "username") String userNameAcc) {
		Account account = service.getAccountByUserName(userNameAcc);

		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setEmail(account.getEmail());
		accountDTO.setUserName(account.getUserName());
		accountDTO.setFullName(account.getFullName());

//		DepartmentDTO departmentDTO = new DepartmentDTO();
//		departmentDTO.setId(account.getDepartment().getId());
//		departmentDTO.setName(account.getDepartment().getName());
		accountDTO.setDepartment(account.getDepartment().getName().toString());

//		PositionDTO positionDTO = new PositionDTO();
//		positionDTO.setId(account.getPosition().getId());
//		positionDTO.setName(account.getPosition().getName());
		accountDTO.setPosition(account.getPosition().getName().toString());

		accountDTO.setCreateDate(account.getCreateDate());

		return new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}

	// Create Account
	@PostMapping()
	public ResponseEntity<?> createAccount(@RequestBody AccountFormForCreating form) {
		service.createAccount(form);
		return new ResponseEntity<String>("Create successfully!", HttpStatus.CREATED);
	}

	// Update Account
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateAccountById(@PathVariable(name = "id") int id,
			@RequestBody AccountFormForUpdate form) {
		service.updateAccountById(id, form);
		return new ResponseEntity<String>("Upddate successfully!", HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteAccountById(@PathVariable(name = "id") int id) {
		service.deleteAccountById(id);
		return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
	}

}
