package com.mx.CrudUsuarios1.service;

import java.util.List;

import com.mx.CrudUsuarios1.dominio.Usuarios;



public interface Metodos {

	public Usuarios guardar(Usuarios usuario);

	public Usuarios editar(Usuarios usuario);

	public void eliminar(Usuarios usuario);

	public Usuarios buscarPorLogin(String login);

	public List<Usuarios> listar();

}