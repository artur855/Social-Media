
package com.arthurzera.website.services;



import com.arthurzera.website.models.User;
import com.arthurzera.website.models.VerificationToken;
import com.arthurzera.website.repositories.UserRepository;
import com.arthurzera.website.repositories.VerificationTokenRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VerificationTokenRepository tokenRepository;
    
    @Override
    public boolean authenticate(String username, String password) {
        try {      
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = userRepository.findByUsername(username);
        	return passwordEncoder.matches(password, user.getPasswordHash());
        } catch (NullPointerException ex) {
            return false;
        }
    }
 
    
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.delete(id);
    }

    @Override 
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    

	@Override
	public void createVerificationToken(User user, String token) {
		VerificationToken userToken = new VerificationToken(token, user);
		tokenRepository.save(userToken);
	}


	@Override
	public VerificationToken getVerificationToken(String token) {
		return tokenRepository.findByToken(token);
	}

}
