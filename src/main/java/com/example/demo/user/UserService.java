package com.example.demo.user;

import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    User user=repository.findByUsername(username);
                    return new MyUserDetails(user);
                }catch (Exception e){
                    return null;
                }
            }
        };
    }

    public UserDetails loadByUsername(String username){
        try{
            User user = repository.findByUsername(username);
            return new MyUserDetails(user);
        }catch (Exception e){
            return null;
        }
    }


    public String login(LoginDTO loginDTO)throws Exception {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getUsername(),
                            loginDTO.getPassword()
                    )
            );
        }catch (Exception e){
            throw new Exception("Login Error!");
        }
        final UserDetails userDetails = loadByUsername(loginDTO.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
