package com.arthurzera.website.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

import com.arthurzera.website.auth.FacebookConnectionSignUp;
import com.arthurzera.website.auth.FacebookSignInAdapter;
import com.arthurzera.website.exception.AccessDeniedHandlerImp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	
	@Autowired
	private UsersConnectionRepository usersConnectionRepositrory;

	@Autowired
	private FacebookConnectionSignUp facebookConnectionSignUp;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;
 
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/index", "/users/register", "/error/**", "/users/login", "/users/auth/**",
						"/connect/**", "/re-sent-confirmation-email")
				.permitAll().antMatchers("/admin/**").hasAuthority("ROLE_ADMIN").anyRequest().authenticated().and()
				.formLogin().loginPage("/users/login").loginProcessingUrl("/users/login").defaultSuccessUrl("/")
				.usernameParameter("username").passwordParameter("password").permitAll().and().logout().and()
				.rememberMe().rememberMeCookieName("remember-me").tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(60 * 60 * 24 * 7).and().exceptionHandling()
				.accessDeniedHandler(new AccessDeniedHandlerImp("/error/acess-denied"));
		http.csrf().disable();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		return tokenRepository;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/js/**", "/img/**", "/css/**");
	}

	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(passwordEncoder).and().userDetailsService(userDetailsService);
	}
	
	@Bean
	public ProviderSignInController providerSignInController() {
		((InMemoryUsersConnectionRepository) usersConnectionRepositrory).setConnectionSignUp(facebookConnectionSignUp);
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepositrory, new FacebookSignInAdapter());
	}
}