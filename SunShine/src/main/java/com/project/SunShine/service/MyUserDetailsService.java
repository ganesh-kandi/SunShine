package com.project.SunShine.service;

import com.project.SunShine.dao.UsersRepo;
import com.project.SunShine.model.UserPrincipal;
import com.project.SunShine.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user == null){
            System.out.println("No user found");
            throw  new UsernameNotFoundException("No user found");
        }

        return new UserPrincipal(user);
    }
}
