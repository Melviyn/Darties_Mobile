package com.maximeattoumani.darties_mobile;

import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.Profil;
import com.maximeattoumani.darties_mobile.model.User;
import com.maximeattoumani.darties_mobile.rest.ApiClient;
import com.maximeattoumani.darties_mobile.rest.ApiInterface;

import org.junit.Test;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    private ApiInterface apiService;

    @Test
    public void api_isCorrect() {

        apiService = ApiClient.getClient();
        apiService.listApiAsync("javier.audibert@darties.com", "4AfgG97", new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                User user = users.get(0);
                assertEquals("51ff65b83616450eeb197760ebe2ec42",user.getApi_key());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    @Test
    public void token_isCorrect(){
        apiService = ApiClient.getClient();
        apiService.listProfilAsync("51ff65b83616450eeb197760ebe2ec42", new Callback<List<Profil>>() {
            @Override
            public void success(List<Profil> profils, Response response) {
                Profil profil = profils.get(0);
                assertEquals("Administrateur",profil.getLib_profil());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Test
    public void accueil_isCorrect(){
        apiService = ApiClient.getClient();
        apiService.listAccueilAsync("51ff65b83616450eeb197760ebe2ec42", "2015_1_2015", "101", "0", new Callback<List<ProduitAccueil>>() {
            @Override
            public void success(List<ProduitAccueil> produitAccueils, Response response) {
                assertEquals(200,response.getStatus());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}