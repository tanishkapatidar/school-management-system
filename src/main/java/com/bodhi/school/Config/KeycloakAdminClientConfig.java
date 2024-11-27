package com.bodhi.school.Config;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(KeycloakSpringBootProperties.class)
@KeycloakConfiguration
public class KeycloakAdminClientConfig {

    private final KeycloakSpringBootProperties keycloakSpringBootProperties;


    public KeycloakAdminClientConfig(KeycloakSpringBootProperties keycloakProperties) {
        this.keycloakSpringBootProperties = keycloakProperties;
    }
    @Bean()
    public Keycloak getKeycloakAdminClient() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakSpringBootProperties.getAuthServerUrl())
                .realm(keycloakSpringBootProperties.getRealm())
                .clientId(keycloakSpringBootProperties.getResource())
                .grantType(OAuth2Constants.PASSWORD)
                .username((String) keycloakSpringBootProperties.getCredentials().getOrDefault("username","admin"))
                .password((String) keycloakSpringBootProperties.getCredentials().getOrDefault("password","admin"))
                .build();
    }
}



