package me.dec7.marker.service.user;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component(value="passwordGenerator")
public class RandomPasswordGenerator implements PasswordGenerator {
	
	private static final int DEFAULT_RANDOM_PASSWORD_LENGTH = 12;

	@Override
	public String generate() {
		return RandomStringUtils.randomAlphanumeric(DEFAULT_RANDOM_PASSWORD_LENGTH);
	}
}
