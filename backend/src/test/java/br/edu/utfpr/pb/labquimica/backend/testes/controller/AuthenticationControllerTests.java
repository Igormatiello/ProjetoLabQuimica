package br.edu.utfpr.pb.labquimica.backend.testes.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;

import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import br.edu.utfpr.pb.labquimica.backend.security.model.AuthenticationRequest;

public class AuthenticationControllerTests {

	public void deveRetornarAutenticacaoEStatusOk() {

		UserDetails userDetails = new Usuario();

		String password = userDetails.getPassword();
		String username = userDetails.getUsername();

		AuthenticationRequest authenticationRequest = new AuthenticationRequest();

		authenticationRequest.setUsername(username);
		authenticationRequest.setPassword(password);

	}

	public void deveCarregarTokenERetornarOk() {

		HttpServletRequest pedido;

		// String s= pedido.getHeader();

	}

}
