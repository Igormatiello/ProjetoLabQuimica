package br.edu.utfpr.pb.labquimica.backend.security.config;

import br.edu.utfpr.pb.labquimica.backend.security.filter.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService)
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(super.authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//        ROLES(SOLICITANTE, ATENDENTE, ADMIN)
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/swagger-resources/**").permitAll()
				.antMatchers("/swagger-ui.html").permitAll().antMatchers("/webjars/**").permitAll()
				.antMatchers("/v1/api-docs").permitAll().antMatchers("/v2/api-docs").permitAll()
				.antMatchers("/session/**").authenticated().antMatchers("/auth/**").permitAll().antMatchers("/cidade")
				.permitAll().antMatchers("/home/**").permitAll().antMatchers("/cidade/busca").permitAll()
				.antMatchers("/cidade/**").hasAnyRole("ADMIN").antMatchers("/instituicao/").permitAll()
				.antMatchers("/instituicao/**").hasAnyRole("ADMIN").antMatchers("/pessoa/doc-exists/**").permitAll()
				.antMatchers("/pessoa/**").authenticated().antMatchers("/amostra-formulario/**").hasAnyRole("ADMIN")
				.antMatchers("/anexo-formulario/**").hasAnyRole("ADMIN").antMatchers(HttpMethod.GET, "/equipamento")
				.authenticated().antMatchers("/equipamento/**").hasAnyRole("ADMIN")
				.antMatchers("/formulario/busca-por-pessoa/**").permitAll()
				.antMatchers(HttpMethod.GET, "/formulario/{id}").authenticated()
				.antMatchers(HttpMethod.POST, "/formulario").authenticated().antMatchers("/formulario/**")
				.hasAnyRole("ADMIN").antMatchers("/instituicao/**").hasAnyRole("ADMIN")
				.antMatchers("/lancamento-financeiro/**").hasAnyRole("ADMIN").antMatchers("/papel/**")
				.hasAnyRole("ADMIN").antMatchers("/participacao-programa-ensino/pessoa-instituicao/**").authenticated()
				.antMatchers("/participacao-programa-ensino/**").hasAnyRole("ADMIN")
				.antMatchers("/pessoa-intituicao/**").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.GET, "/servico/equipamento").authenticated().antMatchers("/servico/**")
				.hasAnyRole("ADMIN").antMatchers("/solicitacao-cadastro/new").permitAll()
				.antMatchers("/solicitacao-cadastro/**").hasAnyRole("ADMIN").antMatchers("/usuario/**")
				.hasAnyRole("ADMIN").antMatchers(HttpMethod.GET, "/creditoProfessor/buscaCreditosPorProfessor/**")
				.authenticated().antMatchers("/creditoProfessor/**").hasAnyRole("ADMIN").anyRequest().permitAll();

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
	}
}
