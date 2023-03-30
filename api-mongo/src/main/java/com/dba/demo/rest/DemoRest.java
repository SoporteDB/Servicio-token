package com.dba.demo.rest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dba.demo.model.AuthenticationReq;
import com.dba.demo.model.Promocion;
import com.dba.demo.model.TokenInfo;
import com.dba.demo.service.JwtUtilService;
import com.dba.demo.service.PromocionService;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/eficaciaChatBot")
public class DemoRest {
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  PromocionService promocionService;

  @Autowired
  UserDetailsService usuarioDetailsService;

  @Autowired
  private JwtUtilService jwtUtilService;
  
  public static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60 * (long) 8; // 8 Horas


  private static final Logger logger = LoggerFactory.getLogger(DemoRest.class);
  
  
  
  
  @PostMapping("/prueba")
  public Promocion agregarDatos(@RequestBody Promocion nuevosdatos) {
      return promocionService.add(nuevosdatos);
  }
  
  
  
  @PostMapping("/promocion")
	public ResponseEntity<Promocion>guardar(@RequestBody Promocion promocion){
		HttpStatus status=null;
		JSONObject prueba = new JSONObject();
		try {
			promocionService.add(promocion);
			status=HttpStatus.CREATED;
		}catch (Exception e) {
			status=HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return ResponseEntity.status(status).body(promocion);
	}



  @GetMapping("/publico")
  public ResponseEntity<?> getMensajePublico() {
    var auth =  SecurityContextHolder.getContext().getAuthentication();
    logger.info("Datos del Usuario: {}", auth.getPrincipal());
    logger.info("Datos de los Permisos {}", auth.getAuthorities());
    logger.info("Esta autenticado {}", auth.isAuthenticated());

    Map<String, String> mensaje = new HashMap<>();
    mensaje.put("contenido", "Hola Mundo");
    return ResponseEntity.ok(mensaje);
  }
  
  @GetMapping("/promocion")
	public List<Promocion> findAll(){
	    
		return promocionService.findAll();
	}

  @PostMapping("/publico/authenticate")
  public ResponseEntity<String> authenticate(@RequestBody AuthenticationReq authenticationReq) {
    logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());

    try {
    	authenticationManager.authenticate(
    	        new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
    	            authenticationReq.getClave()));

    	    final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
    	        authenticationReq.getUsuario());

    	    final String jwt = jwtUtilService.generateToken(userDetails);

    	    TokenInfo tokenInfo = new TokenInfo(jwt);
    	    JSONObject prueba = new JSONObject();
    	    //prueba.put("Prueba");
    	    prueba.put("Token", jwt);
    	    prueba.put("expira_en",JWT_TOKEN_VALIDITY);
    	    return ResponseEntity.ok(prueba.toString());
    	
    }catch(Exception e) {
    	JSONObject prueba = new JSONObject();
    	prueba.put("Error",e.getMessage());
    	prueba.put("Status", FORBIDDEN.value());
        return new ResponseEntity<>(prueba.toString(), HttpStatus.FORBIDDEN);

    }
    }
    	

}
