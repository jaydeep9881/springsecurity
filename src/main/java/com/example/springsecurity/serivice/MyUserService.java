package com.example.springsecurity.serivice;

import com.example.springsecurity.model.UserPrincipal;
import com.example.springsecurity.model.Users;
import com.example.springsecurity.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=repo.findByUsername(username);
        if(user == null){
            System.out.println("User 404 ");
            throw new UsernameNotFoundException("User 404 not found");
        }
        return new UserPrincipal(user);
    }
}
