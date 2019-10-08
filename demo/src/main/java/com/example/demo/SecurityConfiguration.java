package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.demo.business.IAuthTokenService;
import com.example.demo.persistence.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("pepe").password(passwordEncoder.encode("clave")).roles("USER").and()
		// .withUser("admin").password(passwordEncoder.encode("123")).roles("ADMIN");
		auth.userDetailsService(userDetailService);

	}

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@Bean
	public static PasswordEncoder passwordEncoder() {
		// return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private IAuthTokenService authTokenService;
	@Autowired 
	private UsuarioRepository usuariosDAO;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAfter(new CustomTokenAuthenticationFilter(authTokenService, usuariosDAO),
				UsernamePasswordAuthenticationFilter.class);
		http.httpBasic();
		http.authorizeRequests().antMatchers("/api/v1/**").authenticated();
		http.formLogin();// .loginPage("/login.html").successForwardUrl("/index.html");
		http.logout().deleteCookies("JSESSIONID", "rmiw3");
		http.rememberMe().tokenRepository(rmRepository()).rememberMeParameter("rmp").rememberMeCookieName("rmiw3");
		http.csrf().disable();
	}

	@Autowired
	private DataSource ds;

	private PersistentTokenRepository rmRepository() {
		JdbcTokenRepositoryImpl r = new JdbcTokenRepositoryImpl();
		r.setDataSource(ds);
		return r;
	}
	/*
	 * CREATE TABLE `persistent_logins` ( `username` varchar(100) NOT NULL, `series`
	 * varchar(64) NOT NULL, `token` varchar(64) NOT NULL, `last_used` timestamp NOT
	 * NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY
	 * (`series`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8
	 */

}
