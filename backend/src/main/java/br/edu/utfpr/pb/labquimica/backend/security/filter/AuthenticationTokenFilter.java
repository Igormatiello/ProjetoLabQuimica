package br.edu.utfpr.pb.labquimica.backend.security.filter;

import br.edu.utfpr.pb.labquimica.backend.AppConstant;
import br.edu.utfpr.pb.labquimica.backend.security.TokenUtils;
import br.edu.utfpr.pb.labquimica.backend.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UsuarioServiceImpl userDetailsService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		tokenUtils = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())
				.getBean(TokenUtils.class);
		userDetailsService = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext())
				.getBean(UsuarioServiceImpl.class);

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE, PATCH");
		resp.setHeader("Access-Control-Allow-Headers",
				"Origin, X-Requested-With, " + "Authorization, Content-Type, Accept, X-CSRF-TOKEN, Cache-Control, DNT, "
						+ "X-CustomHeader, Keep-Alive, User-Agent, If-Modified-Since, Content-Range, " + "Range, "
						+ AppConstant.TOKEN_HEADER);
		resp.setHeader("Access-Control-Max-Age", "3600");

		if ("OPTIONS".equalsIgnoreCase(((HttpServletRequest) httpRequest).getMethod())) {
			resp.setStatus(HttpServletResponse.SC_OK);
		}

		String authToken = httpRequest.getHeader(AppConstant.TOKEN_HEADER);
		String username = this.tokenUtils.getUsernameFromToken(authToken);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.tokenUtils.validateToken(authToken, userDetails)) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}

}
