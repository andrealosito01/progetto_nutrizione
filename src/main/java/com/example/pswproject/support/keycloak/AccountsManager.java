package com.example.pswproject.support.keycloak;

import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class AccountsManager {

    @Autowired
    RealmResource realmResource;

    @Autowired
    ClientRepresentation app1Client;

    public void registraSuKeycloak(String username, String email, String password, String role){
        // creo una rappresentazione di quello che sarà l'utente su keycloak
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(username);
        user.setEmail(email);
        // provo ad inserire l'utente su keycloak
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(user);
        System.out.println("Response: " + response.getStatus() + " " + response.getStatusInfo());
        if(response.getStatus() == 201){
            // l'inserimento è andato a buon fine
            System.out.println(response.getLocation());
            String userId = CreatedResponseUtil.getCreatedId(response);
            System.out.println("User created with userId: " + userId);
            // setto la password dell'utente
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(password);
            UserResource userResource = usersResource.get(userId);
            System.out.println(userResource);
            userResource.resetPassword(passwordCred);
            // setto il ruolo dell'utente
            RoleRepresentation userClientRole = realmResource.clients().get(app1Client.getId()).roles().get(role).toRepresentation();
            userResource.roles().clientLevel(app1Client.getId()).add(Arrays.asList(userClientRole));
        }else{
            throw new RuntimeException();
        }
    }

    public void modificaPassword(String username, String password){
        UsersResource usersResource = realmResource.users();
        UserRepresentation userRepresentation = usersResource.searchByUsername(username,true).get(0);
        if(userRepresentation == null)
            throw new RuntimeException();

        UserResource userResource = usersResource.get(userRepresentation.getId());
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        System.out.println(password);
        passwordCred.setValue(password);
        userResource.resetPassword(passwordCred);

    }

    public boolean rimuoviDaKeycloak(String username){
        UsersResource usersResource = realmResource.users();
        List<UserRepresentation> res = usersResource.searchByUsername(username,true);
        if(res.size() != 1)
            return false;
        UserResource utenteDaEliminare = usersResource.get(res.get(0).getId());
        utenteDaEliminare.remove();
        return true;
    }
}
