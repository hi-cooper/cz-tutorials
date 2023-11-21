package com.taiwii.oauth20demo.controller;

import okhttp3.*;
import okhttp3.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth2callback")
public class OAuth2CallbackController {

    @GetMapping("/getTokenByCode")
    public String getTokenByCode(@RequestParam String code) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("code", code)
                .add("client_id", "clientId")
                .add("client_secret", "clientSecret")
                .add("grant_type", "authorization_code")
                .add("redirect_uri", "http://localhost:8080/oauth2callback/getTokenByCode")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/oauth/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
