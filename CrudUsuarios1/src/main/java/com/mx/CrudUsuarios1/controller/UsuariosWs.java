package com.mx.CrudUsuarios1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.CrudUsuarios1.dominio.Usuarios;
import com.mx.CrudUsuarios1.service.Implementacion;
import com.mx.CrudUsuarios1.service.Metodos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping(path = "/", produces="application/json")
@CrossOrigin("*")
public class UsuariosWs {
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuariosWs.class);
	
	@Autowired
	private Metodos usuarioService;

	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	Implementacion imp;
	
	@PostMapping(path = "login")
	public ResponseEntity<?> login(@RequestBody Usuarios usuario) {
	    // Buscar al usuario en la base de datos por el nombre de usuario
	    Usuarios user = imp.buscarPorLogin(usuario.getLogin());

	    if (user == null) {
	    	 Map<String, String> errorResponse = new HashMap<>();
	         errorResponse.put("message", "Usuario no encontrado.");
	         return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	    }

	    // Comprobar si la contraseña coincide (la contraseña debe estar encriptada en la base de datos)
	    if (passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
	        // Autenticación exitosa, devuelve un mensaje de éxito
	        Map<String, String> successResponse = new HashMap<>();
	        successResponse.put("message", "Autenticación exitosa.");
	        return new ResponseEntity<>(successResponse, HttpStatus.OK);
	    } else {
	    	// Contraseña incorrecta, devuelve un código de estado 401 (Unauthorized)
	        Map<String, String> errorResponse = new HashMap<>();
	        errorResponse.put("message", "Contraseña incorrecta.");
	        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
	    }
	}
	
	@GetMapping("/usuarios/estado/{estado}")
	public ResponseEntity<List<Usuarios>> obtenerUsuariosPorEstado(@PathVariable char estado) {
	    // Convertir el char 'estado' a un objeto String antes de realizar la comparación
	    String estadoString = "" + estado;

	    List<Usuarios> usuariosPorEstado = imp.listar().stream()
	            .filter(usuario -> usuario.getStatus() == estadoString.charAt(0))
	            .collect(Collectors.toList());

	    return ResponseEntity.ok(usuariosPorEstado);
	}





	// http://localhost:9002/listar
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(path = "listar")
	public ResponseEntity<List<Usuarios>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(imp.listar());
	}

	// http://localhost:9001/buscarId
	@PostMapping(path = "buscarLogin")
	public ResponseEntity<String> buscarLogin(@RequestBody Usuarios usuario) {
	    String login = usuario.getLogin(); // Obtiene el valor del campo login
	    Usuarios usuarioEncontrado = imp.buscarPorLogin(login);

	    System.out.println("Usuario--" + usuarioEncontrado.getNombre());

	    return new ResponseEntity<>("Nombre: " + "\n" + usuarioEncontrado.getNombre() + "\n" +
	            "Apellido paterno:" + "\n" + usuarioEncontrado.getApellidoPaterno(), HttpStatus.OK);
	}

	
	@PostMapping(path = "buscarN")
	public ResponseEntity<String> buscarNombre(@RequestBody Usuarios usuario) {
	    Usuarios user = imp.buscarPorNombre(usuario.getNombre());
	    if (user == null) {
	        return new ResponseEntity<>("No se encontró el usuario", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Nombre: " + user.getNombre() + "\n" + "Apellido paterno: " + user.getApellidoPaterno(), HttpStatus.OK);
	    }
	}

	// http://localhost:9002/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<String> guardar(@RequestBody Usuarios usuario) {
		 String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
	        usuario.setPassword(contraseñaEncriptada);
		imp.guardar(usuario);
		return new ResponseEntity<>("Se guardo el usuario: " + usuario.getNombre(), HttpStatus.CREATED);
	}

	// http://localhost:9002/editar
	@PostMapping(path = "editar")
	public ResponseEntity<String> editar(@RequestBody Usuarios usuario) {
		String contraseñaEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(contraseñaEncriptada);
		imp.guardar(usuario);
		return new ResponseEntity<>("Se edito el usuario: " + usuario.getNombre(), HttpStatus.OK);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	 @PostMapping(path = "eliminar")
    public ResponseEntity<Map<String, String>> eliminar(@RequestBody Usuarios usuario) {
        LOGGER.debug("Recibida solicitud para eliminar usuario: {}", usuario.getLogin());

        try {
            // Lógica para eliminar el usuario
            imp.eliminar(usuario);

            LOGGER.info("Usuario eliminado exitosamente: {}", usuario.getLogin());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Se eliminó el usuario");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOGGER.error("Error al eliminar el usuario: {}", usuario.getLogin(), e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error al eliminar el usuario");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


	@GetMapping("/api/usuarios")
    public List<Usuarios> getUsuariosPorFiltro(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaAlta,
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaModificacion
    ) {
        // Casting explícito para acceder al método obtenerUsuariosPorFiltro de Implementacion
        if (usuarioService instanceof Implementacion) {
            Implementacion implementacion = (Implementacion) usuarioService;
            return implementacion.obtenerUsuariosPorFiltro(nombre, fechaAlta, fechaModificacion);
        } else {
            throw new IllegalStateException("El tipo de servicio no es compatible con la implementación requerida.");
        }
    }

}


