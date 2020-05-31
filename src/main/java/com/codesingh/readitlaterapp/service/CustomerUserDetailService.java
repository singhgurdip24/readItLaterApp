package com.codesingh.readitlaterapp.service;

import com.codesingh.readitlaterapp.model.User;
import com.codesingh.readitlaterapp.repository.UserRepository;
import com.codesingh.readitlaterapp.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerUserDetailService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

    User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
      .orElseThrow(() ->
        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));

    return UserPrincipal.create(user);
  }

  @Transactional
  public UserDetails loadByUserById(Long userId) {

    User user = userRepository.findById(userId)
      .orElseThrow(()-> new UsernameNotFoundException("User not found with id : " + userId));

    return UserPrincipal.create(user);
  }
}
