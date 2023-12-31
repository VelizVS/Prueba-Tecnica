package com.mx.CrudUsuarios1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mx.CrudUsuarios1.serviceSecurity.UserService;





@Configuration
@EnableWebSecurity
public class Configuracion  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserService userDetailsService;
	 @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		{
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable()
        .authorizeRequests()
        // Configurar permisos de acceso aquí
        .anyRequest().permitAll(); // Permitir todas las solicitudes
		
		
		//http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		
	}

}
