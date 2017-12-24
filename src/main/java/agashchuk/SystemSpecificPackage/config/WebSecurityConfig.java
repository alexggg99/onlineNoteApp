package agashchuk.SystemSpecificPackage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("static/css/**", "/webjars/**", "static/js/**", "static/img/**").permitAll()
                    .antMatchers("/note/**").hasRole("USER")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers(HttpMethod.POST, "/rest/login/**").permitAll()
                    .and()
                .exceptionHandling()
                .accessDeniedPage("/errors/405_access_denied")
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .and()
                .rememberMe();

        //remember me configuration
//        http.rememberMe().
//                key("rem-me-key").
//                rememberMeParameter("remember-me-param").
//                rememberMeCookieName("my-remember-me").
//                tokenValiditySeconds(86400);

//        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setParameterName("_csrf");
        repository.setHeaderName("X-CSRF-TOKEN");
        return repository;
    }
}