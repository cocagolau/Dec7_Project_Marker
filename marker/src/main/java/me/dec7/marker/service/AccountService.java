package me.dec7.marker.service;

import me.dec7.marker.common.exception.UsernameAlreadyInUseException;
import me.dec7.marker.entity.Account;

public interface AccountService {

	void create(Account account) throws UsernameAlreadyInUseException;

}
