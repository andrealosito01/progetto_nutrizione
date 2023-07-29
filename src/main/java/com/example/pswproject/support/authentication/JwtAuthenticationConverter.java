package com.example.pswproject.support.authentication;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private String CLIENT_NAME;

    public JwtAuthenticationConverter(String client){
        CLIENT_NAME = client;
    }
    @Override
    @SuppressWarnings("unchecked")
    public AbstractAuthenticationToken convert(final Jwt source) {

        Set<GrantedAuthority> authorities = new TreeSet<>();

        Map<String, Object> resourceAccess = source.getClaim("resource_access");
        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(CLIENT_NAME);
        if(resource != null){
            Collection<String> resourceRoles = (Collection<String>) resource.get("roles");
            authorities = resourceRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }
        return new JwtAuthenticationToken(source, authorities);
    }


}