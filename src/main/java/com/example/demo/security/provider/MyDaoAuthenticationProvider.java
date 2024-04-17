package com.example.demo.security.provider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider{
    static class DaoUserDetailService implements UserDetailsService{

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            // TODO Auto-generated method stub
            return User.withUsername(username).password("$2a$10$TAew3Qi13N6ISMjsZ1pbyeF9sakocZbCBy1lEBB4McUG2yb6oQJOq").build(); // root
        }
    }
    public MyDaoAuthenticationProvider() {
        super(new BCryptPasswordEncoder());
        super.setUserDetailsService(new DaoUserDetailService());
    }
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("root"));
    }
}
