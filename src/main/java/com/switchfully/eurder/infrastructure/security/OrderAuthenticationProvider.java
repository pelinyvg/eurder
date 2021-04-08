package com.switchfully.eurder.infrastructure.security;

import com.switchfully.eurder.infrastructure.security.external.ExternalAuthentication;
import com.switchfully.eurder.infrastructure.security.external.FakeAuthenticationService;
import com.switchfully.eurder.infrastructure.security.feature.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAuthenticationProvider implements AuthenticationProvider {

    private final FakeAuthenticationService fakeAuthenticationService;

    @Autowired
    public OrderAuthenticationProvider(FakeAuthenticationService fakeAuthenticationService) {
        this.fakeAuthenticationService = fakeAuthenticationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        ExternalAuthentication user = fakeAuthenticationService.getUser(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
        if(user != null){
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    rolesToGrantedAuthorities(Feature.getFeaturesForRoles(user.getRoles())));
        }
        throw new BadCredentialsException("The provided credentials were invalid.");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Collection<? extends GrantedAuthority> rolesToGrantedAuthorities(List<Feature> features) {
        return features.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
