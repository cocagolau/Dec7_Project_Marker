package me.dec7.marker.service;

import me.dec7.marker.common.exception.UsernameAlreadyInUseException;
import me.dec7.marker.entity.Account;
import me.dec7.marker.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void create(Account account) throws UsernameAlreadyInUseException {
		
		try {
			accountRepository.save(account);
		} catch (DuplicateKeyException e) {
			throw new UsernameAlreadyInUseException(account.getUsername());
		}
		
	}



}
