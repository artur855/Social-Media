package com.arthurzera.social.media.services;

import com.arthurzera.social.media.models.Post;
import com.arthurzera.social.media.models.User;
import com.arthurzera.social.media.models.VerificationToken;
import com.arthurzera.social.media.repositories.UserEmailRepository;
import com.arthurzera.social.media.repositories.UserRepository;
import com.arthurzera.social.media.repositories.VerificationTokenRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VerificationTokenRepository tokenRepository;
    
    @Autowired 
    private UserEmailRepository userEmailRepository;
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
        return this.userRepository.findByEmail(userEmailRepository.findByEmail(email));
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


	@Override
	public void follow(User currentUser, User user) {
		currentUser.follow(user);
		userRepository.save(currentUser);
		userRepository.save(user);
	}


	@Override
	public void unfollow(User currentUser, User user) {
		currentUser.unfollow(user);
		userRepository.save(currentUser);
		userRepository.save(user);
	}


	@Override
	public List<Post> findTimeLine(User currentUser) {
		List<User> users = currentUser.getFollowing();
		users.add(currentUser);
		List<Post> timeLine = new ArrayList<>();
		users.stream().forEach(user -> user.getPosts().stream().forEach(post -> timeLine.add(post)));;
		return timeLine;
	}


	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}


	@Override
	public boolean existsByFullName(String fullName) {
		return userRepository.existsByFullName(fullName);
	}


	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(userEmailRepository.findByEmail(email)) && userEmailRepository.existsByEmail(email).getEmail().equals(email);
	}

}
