package com.api.parkingcontrol.configs.security;

import com.api.parkingcontrol.Models.UserModel;
import com.api.parkingcontrol.Repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: "+ username));
        return
                new User(userModel.getUsername(),
                        userModel.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        userModel.getAuthorities());
    }
}
