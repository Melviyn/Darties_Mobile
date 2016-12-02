package com.maximeattoumani.darties_mobile.rest;

import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Maxime ATTOUMANI on 15/11/2016.
 */
public interface ApiInterface {

    //A modifier selon le host associer a votre pc
public static final String ENDPOINT = "http://darties1equipea.ddns.net/Darties_Equipe1/private_html";

    @GET("/apiCall.php/login/{mail}&{password}")
    void listApiAsync(@Path("mail")String mail,
                      @Path("password")String password,
                      Callback<List<User>> callback);

    @GET("/apiCall.php/getUser/{token}")
    void listUserAsync(@Path("token")String token,
                       Callback<List<User>> callback);

    @GET("/apiCall.php/tabAccueil/{token}&{temps}&{geo}&{enseigne}")
    void listAccueilAsync(@Path("token")String token,
                           @Path("temps")String temps,
                          @Path("geo") String geo,
                          @Path("enseigne")String enseigne,
                          Callback<List<ProduitAccueil>> classback);

}
