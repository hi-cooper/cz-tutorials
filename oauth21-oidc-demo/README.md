# OpenID Connect示例

> - OpenID Connect：简称OIDC
> - OIDC基于OAuth2.0实现。区别：OIDC在返回access_token时，会同时返回id_token
> - 这里仅介绍基于Authorization Code的OIDC实现

# 1 Authorization Code 授权码模式

> 流程与OAuth2.0完全相同，仅在“通过授权码code获取token”的响应中，会同时返回id_token

## 1.1 获取授权码Code

### 1.1.1 Request

```shell
GET http://localhost:8080/oauth2/authorize?response_type=code&client_id=clientId&scope=openid+profile+phone&redirect_uri=http://www.baidu.com
```

### 1.1.2 Response

```shell
https://www.baidu.com/?code=umvUQi2uaFxrrpliqlGY9LEEo9fIOI80veILdF2jIFcXPg3ZgK0FjKLit7te8oT_NiCbFVFTKb0GKmBuhItqQ1BEqKENjvKeSZpHa7_f_9dryWA8118lN6u-kxHeHfCp
```

## 1.2 通过授权码Code获取token

### 1.2.1 Request

```shell
POST http://localhost:8080/oauth2/token

x-www-form-urlencoded

grant_type:authorization_code
client_id:clientId
client_secret:clientSecret
redirect_uri:http://www.baidu.com
code:umvUQi2uaFxrrpliqlGY9LEEo9fIOI80veILdF2jIFcXPg3ZgK0FjKLit7te8oT_NiCbFVFTKb0GKmBuhItqQ1BEqKENjvKeSZpHa7_f_9dryWA8118lN6u-kxHeHfCp
```

### 1.2.2 Response

```json
{    "access_token":"eyJraWQiOiIyMjE4OTI5ZS1jMzJiLTQ5NTEtOGU0ZS01MWEyY2I5ZjYyMzkiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiY2xpZW50SWQiLCJuYmYiOjE3MDA3MzIwOTUsInNjb3BlIjpbInBob25lIiwib3BlbmlkIiwicHJvZmlsZSJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE3MDA3MzIzOTUsImlhdCI6MTcwMDczMjA5NX0.GuQma9Si9aJgaUhv1mJFSlo54qdtP2D-pTnv5DHspdVDE9z5KdpKSvBb1jrCncnjPDpONOvCftLZHedvDEVfIYGYOOTuwT1zCzy8HYsno1InOpCyXtK42IPddMKfQTjcx7JvrgAajmzszqcJWp45i_V909XaeHO1RuW2RxRV4G34oY_LoKwnv4GQOO2-IZV-KzkIT7EQiFQHG2Wwt-QN7fxN8QOwTMyCeYAYRRTqa6ELtSkYWjTQgVo3e9fhCkltyBC7M4x73VXDN0VJZ-HBOaUX5sB-L2ETXdpqdmrUms4XkIRktA-gQXgbPKlqgSEvXoKh10zEBbeXRuFFP8egKA",
    "refresh_token":"eIPnchZJz9JJz_Xo2v7F7uH1WKcDJMx8skUDicVFvwAeDxry0XPQvAnR2dZZ-wNTWroGcUtSIY6AEW2mQlBdLiDOJuiPF1deBf_2nYFxJVHT99hKT10Tp0ri4pvSAcvw",
    "scope":"phone openid profile",
    "id_token":"eyJraWQiOiIyMjE4OTI5ZS1jMzJiLTQ5NTEtOGU0ZS01MWEyY2I5ZjYyMzkiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiY2xpZW50SWQiLCJhenAiOiJjbGllbnRJZCIsImF1dGhfdGltZSI6MTcwMDczMjAzMywiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwiZXhwIjoxNzAwNzMzODk1LCJpYXQiOjE3MDA3MzIwOTUsInNpZCI6IkMydnpoVGlvVXQ1ak9heVhDMlJDdlc0RnNrNUxuMkMxd3Q3UEtkQ2xGMVUifQ.jAY45CEBXG_cK8asT-DjrJtD_onv8BadLbZp-6-oFA_qnni00v0mls2qE6AfOYGJFk25c9OFTY_1mS8VkyqSrCGra3Jc_X8p16wSVngcA4LXT-rd5JWy2YVrZr23Bgkt9HOTKcu3QYoKecKYi0VTcpW38TwDjkqg1xKmes8ac6ImUFJDINE4HH4zhDpkjkj7qd55arSOG8Qeq0I5fxGP09VVNodc8jl_5yDmpgt3m3FKTIO6t9NTln5vJ2v0aKttcmNNI-ICKK7i73uhTs-alI3aY9M5SJMJ1b_DE-xpQjReuEw-ThoEYDzqmmsrJTVLc9BQG60BgHJvr-x6fO6upw",
    "token_type":"Bearer",
    "expires_in":299
}
```

# 2 userinfo Endpoint

## 2.1 Request

```shell
POST http://localhost:8080/userinfo

header:
Authorization:Bearer eyJraWQiOiIyMjE4OTI5ZS1jMzJiLTQ5NTEtOGU0ZS01MWEyY2I5ZjYyMzkiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiYXVkIjoiY2xpZW50SWQiLCJuYmYiOjE3MDA3MzIwOTUsInNjb3BlIjpbInBob25lIiwib3BlbmlkIiwicHJvZmlsZSJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE3MDA3MzIzOTUsImlhdCI6MTcwMDczMjA5NX0.GuQma9Si9aJgaUhv1mJFSlo54qdtP2D-pTnv5DHspdVDE9z5KdpKSvBb1jrCncnjPDpONOvCftLZHedvDEVfIYGYOOTuwT1zCzy8HYsno1InOpCyXtK42IPddMKfQTjcx7JvrgAajmzszqcJWp45i_V909XaeHO1RuW2RxRV4G34oY_LoKwnv4GQOO2-IZV-KzkIT7EQiFQHG2Wwt-QN7fxN8QOwTMyCeYAYRRTqa6ELtSkYWjTQgVo3e9fhCkltyBC7M4x73VXDN0VJZ-HBOaUX5sB-L2ETXdpqdmrUms4XkIRktA-gQXgbPKlqgSEvXoKh10zEBbeXRuFFP8egKA
```

## 2.2 Response

```json
{
    "sub": "user"
}
```

# 3 Configuration Endpoint

```shell
http://localhost:8080/.well-known/openid-configuration
```

