package br.edu.utfpr.pb.labquimica.backend.controller;


import br.edu.utfpr.pb.labquimica.backend.AppConstant;
import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.security.TokenUtils;
import br.edu.utfpr.pb.labquimica.backend.security.model.AuthenticationRequest;
import br.edu.utfpr.pb.labquimica.backend.security.model.AuthenticationResponse;
import br.edu.utfpr.pb.labquimica.backend.service.AnexoFormularioService;
import br.edu.utfpr.pb.labquimica.backend.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    
	
	
    private AuthenticationManager authenticationManager;
    
    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        
   }
    
    
    
    private TokenUtils tokenUtils;
    public AuthenticationController(TokenUtils tokenUtils) {
        this.tokenUtils =tokenUtils;
        
   }
   

    private UsuarioServiceImpl usuarioService;
    
    public AuthenticationController(UsuarioServiceImpl usuarioService) {
        this.usuarioService =usuarioService;
        
   }
    
    

    @PostMapping(value = "")
    public ResponseEntity<?> authenticationRequest(
            @RequestBody AuthenticationRequest a)
            throws AuthenticationException {

        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(a.getUsername(), a.getPassword())
        ); // {"username" : "admin", "password" : "123"}

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = this.usuarioService.loadUserByUsername(a.getUsername());
        String token = this.tokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(token)); // {"token" : "AIUuadsa82asdn"}
    }

    @GetMapping(value = "refresh")
    public ResponseEntity<?> authenticationRequest(
            HttpServletRequest request) {
        String token = request.getHeader(AppConstant.TOKEN_HEADER);
        String username = this.tokenUtils.getUsernameFromToken(token);

        Usuario u = (Usuario) this.usuarioService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, u.getLastPasswordReset())) {
            String refreshedToken = this.tokenUtils.refreshToken(token);

            return ResponseEntity.ok(new AuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
