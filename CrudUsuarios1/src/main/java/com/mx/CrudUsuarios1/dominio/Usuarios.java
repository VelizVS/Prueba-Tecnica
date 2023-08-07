package com.mx.CrudUsuarios1.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Usuarios {

	/*CREATE TABLE USUARIO(
			LOGIN VARCHAR2(20 BYTE) PRIMARY KEY,
			PASSWORD VARCHAR2(30 BYTE),
			NOMBRE VARCHAR2(50 BYTE),
			CLIENTE FLOAT,
			EMAIL VARCHAR2(50 BYTE),
			FECHA_ALTA DATE,  --SYSDATE
			FECHA_BAJA DATE,
			STATUS CHAR(1 BYTE), --A
			INTENTOS FLOAT,
			FECHA_REVOCADO DATE,
			FECHA_VIGENCIA DATE,
			NO_ACCESO INTEGER,
			APELLIDO_PATERNO VARCHAR2(50 BYTE),
			APELLIDO_MATERNO VARCHAR2(50 BYTE),
			AREA NUMBER(4),
			FECHA_MODIFICACION DATE --SYSDATE
			);
	*/

	@Id
	@Column
	String login;
	@Column
	String password;
	@Column
	String nombre;
	@Column
	Double cliente;
	@Column
	String email;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Column(name="FECHA_ALTA",columnDefinition = "TIMESTAMP")
	Date fechaAlta;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Column(name="FECHA_BAJA",columnDefinition = "TIMESTAMP")
	Date fechaBaja;
	@Column
	char status = 'A';
	@Column
	Double intentos;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Column(name="FECHA_REVOCADO",columnDefinition = "TIMESTAMP")
	Date fechaRevocado;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Column(name="FECHA_VIGENCIA",columnDefinition = "TIMESTAMP")
	Date fechaVigencia;
	@Column
	int noAcceso;
	@Column
	String apellidoPaterno;
	@Column
	String apellidoMaterno;
	@Column
	int area;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "America/Mexico_City")
	@Column(name="FECHA_MODIFICACION",columnDefinition = "TIMESTAMP")
	Date fechaModificacion;

	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getCliente() {
		return cliente;
	}
	public void setCliente(Double cliente) {
		this.cliente = cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fecha_alta) {
		this.fechaAlta = fecha_alta;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fecha_baja) {
		this.fechaBaja = fecha_baja;
	}
	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	public Double getIntentos() {
		return intentos;
	}
	public void setIntentos(Double intentos) {
		this.intentos = intentos;
	}
	public Date getFechaRevocado() {
		return fechaRevocado;
	}
	public void setFechaRevocado(Date fecha_revocado) {
		this.fechaRevocado = fecha_revocado;
	}
	public Date getFechaVigencia() {
		return fechaVigencia;
	}
	public void setFechaVigencia(Date fecha_vigencia) {
		this.fechaVigencia = fecha_vigencia;
	}
	public int getNoAcceso() {
		return noAcceso;
	}
	public void setNoAcceso(int no_acceso) {
		this.noAcceso = no_acceso;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	

	






}
