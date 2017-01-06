package com.maximeattoumani.darties_mobile.rest;

import com.maximeattoumani.darties_mobile.model.Enseigne;
import com.maximeattoumani.darties_mobile.model.FaitsVentes;
import com.maximeattoumani.darties_mobile.model.FamProd;
import com.maximeattoumani.darties_mobile.model.Geographie;
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.Profil;
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

    @GET("/apiCall.php/getProfil/{token}")
    void listProfilAsync(@Path("token")String token,
                      Callback<List<Profil>> callback);

    @GET("/apiCall.php/getGeo/{token}")
    void listGeoAsync(@Path("token")String token,
                         Callback<List<Geographie>> callback);

    @GET("/apiCall.php/getEnseigne/{token}")
    void listEnseigneAsync(@Path("token")String token,
                      Callback<List<Enseigne>> callback);

    /*@GET("/apiCall.php/getTemps/{token}")
    void listPeriodeAsync(@Path("token")String token,
                      Callback<List<Periode>> callback);
*/
    @GET("/apiCall.php/getDimFamilleProduit/{token}")
    void listFamProduitAsync(@Path("token")String token,
                             Callback<List<FamProd>> callback);

    @GET("/apiCall.php/getFaitsVentes/{token}")
    void listFaisVentesAsync(@Path("token")String token,
                             Callback<List<FaitsVentes>> callback);
}
