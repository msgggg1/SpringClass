package org.doit.ik.di6;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PasswordChangeService {
	private UserRepository userRepository;
	
	public PasswordChangeService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void changePassword(String userId, String oldPw, String newPw) {
		User user = userRepository.findUserById(userId);
		if (user == null) {
			throw new UsernameNotFoundException("회원 정보가 없습니다.");			
		}
		user.changePassword(oldPw, newPw);
	}
}
