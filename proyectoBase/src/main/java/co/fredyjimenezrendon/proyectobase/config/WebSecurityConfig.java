package co.fredyjimenezrendon.proyectobase.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		 
	@Autowired
	DataSource dataSource;
    
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	   auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery("select login, password, habilitado from usuario where login = ?")
	  .authoritiesByUsernameQuery("select u.login, r.nombre from rol r inner join usuario_rol ur on ur.id_rol = r.id_rol inner join usuario u on u.id_usuario = ur.id_usuario where u.login = ?")
	  .passwordEncoder(passwordEncoder());
	} 

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").access("hasRole('admin')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
            	.loginProcessingUrl("/auth/login_check").usernameParameter("username").passwordParameter("password")
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/inicio")
                .and()
            .rememberMe()
            	.rememberMeParameter("remember-me")
            	.tokenRepository(persistentTokenRepository())
            	.tokenValiditySeconds(1209600)//15 Dias
                .and()
            .logout()
                .permitAll()
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf();
        
        http.
        	sessionManagement()
        		.maximumSessions(1)
        		.maxSessionsPreventsLogin(true)
        		.expiredUrl("/sesionExpirada");
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
	
}
