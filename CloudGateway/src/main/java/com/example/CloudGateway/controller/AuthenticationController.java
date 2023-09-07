package com.example.CloudGateway.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CloudGateway.model.AuthenticationResponse;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    
    public ResponseEntity<AuthenticationResponse> login(
        @AuthenticationPrincipal OidcUser oidcUser,
        Model model,
        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client
    ){
        AuthenticationResponse authenticationResponse 
        = AuthenticationResponse.builder()
        .userId(oidcUser.getEmail())
        .accessToken(client.getAccessToken().getTokenValue())
        .refreshToken(client.getRefreshToken().getTokenValue())
        .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
        .authorityList(oidcUser.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toList()))
        .build();

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

}
