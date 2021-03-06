package com.maximeattoumani.darties_mobile.rest;

import com.maximeattoumani.darties_mobile.model.Enseigne;
import com.maximeattoumani.darties_mobile.model.FaitsVentes;
import com.maximeattoumani.darties_mobile.model.FamProd;
import com.maximeattoumani.darties_mobile.model.Geographie;
import com.maximeattoumani.darties_mobile.model.Gestion;
import com.maximeattoumani.darties_mobile.model.ProduitAccueil;
import com.maximeattoumani.darties_mobile.model.Profil;
import com.maximeattoumani.darties_mobile.model.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
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
    void listFaitsVentesAsync(@Path("token")String token,
                             Callback<List<FaitsVentes>> callback);
    @GET("/apiCall.php/setFaitsVentes/{token}&{id_magasin}&{id_prod}&{id_temps}&{vObj}&{vReel}&{cObj}&{cReel}&{mObj}&{mReel}")
    void setFaitsVentes(@Path("token")String token,
                        @Path("id_magasin")String id_magasin,
                        @Path("id_prod")String id_prod,
                        @Path("id_temps")String id_temps,
                        @Path("vObj")String vObj,
                        @Path("vReel")String vReel,
                        @Path("cObj")String cObj,
                        @Path("cReel")String cReel,
                        @Path("mObj")String mObj,
                        @Path("mReel")String mReel,
                        Callback<List<FaitsVentes>> callback);

    @FormUrlEncoded
    @POST("/apiCall.php/deleteMessage")
    void deleteMessage(@Field("id") int token,Callback<String> callback);

    @GET("/apiCall.php/getNotif/{id}")
    void getNotification(@Path("id")int token,Callback<String> callback);

    @GET("/apiCall.php/gestion_utilisateur/{id}")
    void gestion_utilisateur(@Path("id")String id, Callback<List<Gestion>> callback);
}
