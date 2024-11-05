package com.medicine.donate.medicine.service.impl;

import com.medicine.donate.medicine.entity.UserEntity;
import com.medicine.donate.medicine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUuid(UUID.fromString(uuid))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUuid().toString(),
                user.getPassword(),
                new ArrayList<>() // You can add roles/authorities here
        );
    }
}