package com.mx.CrudUsuarios1.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.CrudUsuarios1.dao.UsuariosDao;
import com.mx.CrudUsuarios1.dominio.Usuarios;

@Service
public class Implementacion implements Metodos {

	@Autowired
	UsuariosDao dao;

	@Override
	public Usuarios guardar(Usuarios usuario) {
		return dao.save(usuario);
	}

	public Usuarios editar(Usuarios usuario) {
	    String login = usuario.getLogin();
	    Usuarios usuarioExistente = dao.findById(login)
	                                  .orElseThrow(() -> new RuntimeException("No se encontró el usuario a editar"));

	    usuarioExistente.setLogin(usuario.getLogin());
	    usuarioExistente.setPassword(usuario.getPassword());
	    usuarioExistente.setNombre(usuario.getNombre());
	    usuarioExistente.setCliente(usuario.getCliente());
	    usuarioExistente.setEmail(usuario.getEmail());
	    usuarioExistente.setFechaAlta(usuario.getFechaAlta());
	    usuarioExistente.setFechaBaja(usuario.getFechaBaja());
	    usuarioExistente.setStatus(usuario.getStatus());
	    usuarioExistente.setIntentos(usuario.getIntentos());
	    usuarioExistente.setFechaRevocado(usuario.getFechaRevocado());
	    usuarioExistente.setFechaVigencia(usuario.getFechaVigencia());
	    usuarioExistente.setNoAcceso(usuario.getNoAcceso());
	    usuarioExistente.setApellidoPaterno(usuario.getApellidoPaterno());
	    usuarioExistente.setApellidoMaterno(usuario.getApellidoMaterno());
	    usuarioExistente.setArea(usuario.getArea());
	    usuarioExistente.setFechaModificacion(usuario.getFechaModificacion());
	   
	    return dao.save(usuarioExistente);
	}
	@Override
	public void eliminar(Usuarios usuario) {
		dao.delete(usuario);

	}

	@Override
	public Usuarios buscarPorLogin(String login) {
	    if (login == null || login.isEmpty()) {
	        throw new IllegalArgumentException("El login no puede ser nulo o vacío.");
	    }
	    return dao.findById(login)
	              .orElseThrow(() -> new RuntimeException("No se encontró el usuario"));
	}


	@Override
	public List<Usuarios> listar() {
		return dao.findAll();
	}
	
	 public Usuarios buscarPorNombre(String nombre) {
	        return dao.findByNombre(nombre);
	    }
	 
	 public List<Usuarios> obtenerUsuariosPorEstado(char estado) {
		    List<Usuarios> todosLosUsuarios = listar();
		    return todosLosUsuarios.stream()
		            .filter(usuario -> Character.toString(usuario.getStatus()).equals(Character.toString(estado)))
		            .collect(Collectors.toList());
		}
	 
	 public List<Usuarios> obtenerUsuariosPorFiltro(String nombre, Date fechaAlta, Date fechaModificacion) {
		    List<Usuarios> todosLosUsuarios = listar();
		    return todosLosUsuarios.stream()
		            .filter(usuario -> {
		                boolean matchesNombre = nombre == null || nombre.isEmpty() || usuario.getNombre().contains(nombre);

		                LocalDate fechaAltaUsuario = usuario.getFechaAlta().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		                LocalDate fechaModificacionUsuario = usuario.getFechaModificacion().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		                LocalDate fechaAltaFiltro = fechaAlta == null ? null : fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		                LocalDate fechaModificacionFiltro = fechaModificacion == null ? null : fechaModificacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		                boolean matchesFechaAlta = fechaAltaFiltro == null || fechaAltaUsuario.isEqual(fechaAltaFiltro);
		                boolean matchesFechaModificacion = fechaModificacionFiltro == null || fechaModificacionUsuario.isEqual(fechaModificacionFiltro);

		                return matchesNombre && matchesFechaAlta && matchesFechaModificacion;
		            })
		            .collect(Collectors.toList());
		}



	 


}
