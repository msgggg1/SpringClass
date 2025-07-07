package org.doit.ik.di6;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Service
@AllArgsConstructor
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
