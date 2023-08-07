package com.mx.CrudUsuarios1.serviceSecurity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mx.CrudUsuarios1.dao.UsuariosDao;
import com.mx.CrudUsuarios1.dominio.Usuarios;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UsuariosDao dao;
	

	/*@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuarios user = dao.findByNombre(username);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		UserDetails userDet = new User(user.getLogin(), user.getPassword(),roles);
		return userDet;
	}
*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Usuarios user = dao.findByNombre(username);
	    if (user == null) {
	        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
	    }

	    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

	    return new User(user.getLogin(), user.getPassword(), roles);
	}
	

    
}