package com.fsoft.fa.interviewprocessmanagement.service;

import com.fsoft.fa.interviewprocessmanagement.model.User;
import com.fsoft.fa.interviewprocessmanagement.model.UserDetail;
import com.fsoft.fa.interviewprocessmanagement.repository.RoleRepository;
import com.fsoft.fa.interviewprocessmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Tai khoan khong dung"));
        return optionalUser.map(UserDetail::new).get();
    }

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveMyUser(User user) {
        userRepository.save(user);
    }

    public List<User> showAllUsers() {
        return userRepository.findAll();
    }
    
    public User getById(int id) {
    	return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User with id " + id + " not found"));
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByRoleName(String rolename) {
        return userRepository.getRolesByName(rolename);
    }

	public User getByName(String name) {
		return userRepository.findByUsername(name).orElseThrow(() ->
        new NotFoundException("User with name " + name + " not found")
);
	}
}
