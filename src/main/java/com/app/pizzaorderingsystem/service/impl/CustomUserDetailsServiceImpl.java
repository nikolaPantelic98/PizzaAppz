package com.app.pizzaorderingsystem.service.impl;

import com.app.pizzaorderingsystem.entity.Role;
import com.app.pizzaorderingsystem.entity.User;
import com.app.pizzaorderingsystem.repository.RoleRepository;
import com.app.pizzaorderingsystem.repository.UserRepository;
import com.app.pizzaorderingsystem.security.CustomUserDetails;
import com.app.pizzaorderingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public CustomUserDetailsServiceImpl() {
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    @Override
    public User registerNewUser(User user) {
        User newUser = new User(user.getFirstName(), user.getLastName(),
                user.getEmailAddress(), passwordEncoder.encode(user.getPassword()), Arrays.asList(roleRepository.findByName("Customer")));
        return userRepository.save(newUser);
    }

    @Override
    public void saveUserWithRole(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(emailAddress);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new CustomUserDetails(user);
    }
}
