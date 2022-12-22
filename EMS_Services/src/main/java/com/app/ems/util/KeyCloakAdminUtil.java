package com.app.ems.util;

import java.io.IOException;
import java.util.Arrays;
import javax.ws.rs.core.Response;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RealmRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Configuration;

import com.app.ems.entity.KeyCloakUserEmplReg;

@Configuration
public class KeyCloakAdminUtil {

	Keycloak keycloak = KeycloakBuilder.builder().serverUrl("http://localhost:8080/").realm("master")
			.clientId("admin-cli").username("keycloak").password("Keycloak123$").build();

	public void createRealm() {
		RealmRepresentation rr = new RealmRepresentation();
		rr.setId("ems_service");
		rr.setRealm("ems_service");
		rr.setEnabled(true);
		keycloak.realms().create(rr);

	}

	public void createClient(String clientId, String realmName) throws IOException {
		RealmResource createdRealmResource = keycloak.realms().realm(realmName);
		ClientRepresentation clientRepresentation = new ClientRepresentation();
		clientRepresentation.setClientId(clientId);
		clientRepresentation.setProtocol("openid-connect");
		clientRepresentation.setSecret(clientId);
		createdRealmResource.clients().create(clientRepresentation);

	}

	public void createRoles() {
		RoleRepresentation clientRoleRepresentation = new RoleRepresentation();
		clientRoleRepresentation.setName("employee");
		clientRoleRepresentation.setClientRole(true);
		keycloak.realm("ems_service").clients().findByClientId("ems_service-client")
				.forEach(clientRepresentation -> keycloak.realm("ems_service").clients()
						.get(clientRepresentation.getId()).roles().create(clientRoleRepresentation));

	}

	public void assignRoleToUser(KeyCloakUserEmplReg keyCloakUserEmplReg) {

		// Define user
		UserRepresentation user = new UserRepresentation();
		user.setEnabled(true);
		user.setUsername(keyCloakUserEmplReg.getUserName());
		user.setEmail(keyCloakUserEmplReg.getEmail());

		// Get realm
		RealmResource realmResource = keycloak.realm("ems_service");
		UsersResource usersRessource = realmResource.users();

		// Create user (requires manage-users role)
		Response response = usersRessource.create(user);
		String userId = CreatedResponseUtil.getCreatedId(response);
		// Define password credential
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(keyCloakUserEmplReg.getPassword());

		UserResource userResource = usersRessource.get(userId);
		userResource.resetPassword(passwordCred);

		RoleRepresentation testerRealmRole = realmResource.roles().get(keyCloakUserEmplReg.getUserRole())
				.toRepresentation();

		userResource.roles().realmLevel().add(Arrays.asList(testerRealmRole));

	}

//	public static void main(String[] args) throws IOException {
//		KeyCloakAdminConfig config = new KeyCloakAdminConfig();
//		// config.createRealm();
//		// config.createClient("ems_service-client", "ems_service");
//		// config.createRoles();
//		//config.setUserPass();
//		config.createUser();
//	}
}
