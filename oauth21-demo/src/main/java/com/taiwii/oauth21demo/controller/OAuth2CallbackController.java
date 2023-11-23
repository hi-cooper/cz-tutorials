package com.taiwii.oauth21demo.controller;

import okhttp3.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth2callback")
public class OAuth2CallbackController {

    @GetMapping("/getTokenByCode")
    public String getTokenByCode(@RequestParam String code) throws Exception {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("code", code)
                .add("client_id", "clientCode")
                .add("client_secret", "secretCode")
                .add("grant_type", "authorization_code")
                .add("redirect_uri", "http://localhost:8080/oauth2callback/getTokenByCode")
                .build();

        Request request = new Request.Builder()
                .url("http://localhost:8080/oauth2/token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String respBody = response.body().string();
            return respBody;
        }
    }
}
