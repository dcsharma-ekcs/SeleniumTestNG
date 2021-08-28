package com.copperchips.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AccessToken_GET {

    static String accessToken;

    @Test
    public void test_01(){

       Response response =  RestAssured.get("https://reqres.in/api/user/2");
       System.out.println(response.statusCode());
       System.out.println(response.asString());
       System.out.println(response.body().asString());
       System.out.println(response.getTime());
        }

    @Test
    public void test_access_token_get(){
        String responseString ;

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("client_id","7598e1f5-8bdf-499b-8af0-84e2980e5606");
        map.put("client_secret","4z~4KIXN0Tt-BeA4R~444cV-YfQKehU6md");
        map.put("grant_type","client_credentials");
        map.put("scope","https://vestanpb2c.onmicrosoft.com/7598e1f5-8bdf-499b-8af0-84e2980e5606/.default");

        accessToken = given().
            header("Content-Type","application/x-www-form-urlencoded").
            params(map).
    when().
            post("https://login.microsoftonline.com/vestanpb2c.onmicrosoft.com/oauth2/v2.0/token").
    then().
            statusCode(200).
            extract().
            path("access_token").toString();

            System.out.println("access token: "+accessToken);
    }

}
