package com.example.pswproject.configurations;

import com.example.pswproject.support.authentication.*;
import com.example.pswproject.support.keycloak.AccountsManager;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.admin.client.token.TokenService;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.ws.rs.core.MultivaluedMap;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value(value = "${keycloak.resource}")
    private String CLIENT_NAME;
    @Value(value = "${keycloak.clientSecret}")
    private String CLIENT_SECRET;
    @Value(value = "${keycloak.serverUrl}")
    private String SERVER_URL;
    @Value(value = "${keycloak.realm}")
    private String REALM;
    @Value(value = "${keycloak.username}")
    private String ADMIN_USERNAME;
    @Value(value = "${keycloak.password}")
    private String ADMIN_PASSWORD;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/check/simple").permitAll()
                .antMatchers("/utente").permitAll()
                .anyRequest().authenticated().and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(new JwtAuthenticationConverter(CLIENT_NAME));
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("OPTIONS");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", configuration);
        return new CorsFilter(source);
    }

    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(CLIENT_NAME)
                .clientSecret(CLIENT_SECRET)
                .username(ADMIN_USERNAME)
                .password(ADMIN_PASSWORD)
                .build();
    }

    @Bean
    public RealmResource realmResource(){
        return keycloak().realm(REALM);
    }

    @Bean
    public AccountsManager accountsManager(){
        return new AccountsManager();
    }

    @Bean
    public ClientRepresentation clientRepresentation(){
        return realmResource().clients().findByClientId(CLIENT_NAME).get(0);
    }

}
