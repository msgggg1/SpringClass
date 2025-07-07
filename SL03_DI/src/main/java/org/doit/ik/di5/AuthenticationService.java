package org.doit.ik.di5;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.Setter;

@Setter
public class AuthenticationService {
	private UserRepository userRepository;
	private AuthFailLogger failLogger;
	
	public AuthInfo authenticate(String id, String password) {
		User user = userRepository.findUserById(id);
		if (user == null) {
			throw new UserNotFoundException();			
		}
		
		if (!user.matchPassword(password)) {
			failLogger.insertBadPw(id, password);
		}
		
		return new AuthInfo(user.getId());
	}
}
