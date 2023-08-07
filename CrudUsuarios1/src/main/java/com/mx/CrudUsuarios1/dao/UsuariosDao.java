package com.mx.CrudUsuarios1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.CrudUsuarios1.dominio.Usuarios;



@Repository
public interface UsuariosDao extends JpaRepository<Usuarios, String>{
	
	Usuarios findByNombre(String login);
	

}
