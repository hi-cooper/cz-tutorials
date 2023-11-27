# OAuth 2.0示例

> - Authorization Code 授权码模式
> - Client Credentials 客户端模式
> - Implicit 简化模式
> - Password 密码模式
> - Refresh Token

# 1 Authorization Code 授权码模式

## 1.1 获取授权码Code

```shell
GET http://localhost:8080/oauth/authorize?response_type=code&client_id=clientId&scope=testScope&redirect_uri=http://localhost:8080/oauth2callback/getTokenByCode
```

## 1.2 通过授权码Code获取token

```shell
POST http://localhost:8080/oauth/token?grant_type=authorization_code&client_id=clientId&client_secret=clientSecret&code=JWKfJ8&redirect_uri=http://127.0.0.1:8080
```

# 2 Implicit 简化模式

```shell
GET http://localhost:8080/oauth/authorize?response_type=code&client_id=clientId&scope=testScope&redirect_uri=http://localhost:8080/oauth2callback/getTokenByCode
```

# 3 Client Credentials 客户端模式

```shell
POST http://localhost:8080/oauth/token?grant_type=client_credentials&client_id=clientId&client_secret=clientSecret
```

# 4 Password 密码模式

```shell
POST http://localhost:8080/oauth/token?grant_type=password&client_id=clientId&client_secret=clientSecret&username=user&password=password
```

# 5 Refresh Token

```shell
POST http://localhost:8080/oauth/token?grant_type=refresh_token&client_id=clientId&client_secret=clientSecret&refresh_token=wMIUJiieJ7ecZJXWQn003P1M1Zw
```


