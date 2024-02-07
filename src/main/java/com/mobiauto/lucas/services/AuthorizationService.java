
package com.mobiauto.lucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mobiauto.lucas.domain.Usuarios.UsuariosRepository;

/**
 * AuthorizationService
 */
@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UsuariosRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        return repository.findByEmail(username);
    }

}