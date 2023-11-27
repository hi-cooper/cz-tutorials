# OAuth 2.1示例

> - Authorization Code 授权码模式
> - Authorization Code + PKCE 授权码PKCE模式
> - Client Credentials 客户端模式
> - Device Code 设备码模式
> - Refresh Token

# 1 Authorization Code 授权码模式

## 1.1 封装获取token

```shell
GET http://localhost:8080/oauth2/authorize?response_type=code&client_id=clientCode&scope=testScope&redirect_uri=http://localhost:8080/oauth2callback/getTokenByCode
```

## 1.2 正常获取token

### 1.2.1 获取授权码Code

```shell
GET http://localhost:8080/oauth2/authorize?response_type=code&client_id=clientCode2&scope=testScope&redirect_uri=http://www.baidu.com
```

### 1.2.2 通过授权码Code获取token

```shell
POST http://localhost:8080/oauth2/token

x-www-form-urlencoded

grant_type:authorization_code
client_id:clientCode2
client_secret:secretCode2
redirect_uri:http://www.baidu.com
code:EiYTZZyRf2EAiCo_GMqBX_XWID7a0CcA238KrM3GeZNYNB7fX7dvwnm__idyt0CwLTpJRwUmGWL1jQWHfdG7BtyDQdTUcI10y_wa76IGJDP3FIW1KMwWEqLLUTz9LN-B
```

# 2 Authorization Code + PKCE 授权码PKCE模式

```properties
code_verifier:ZjE0NWI0MmUtNDM1MC00ZDFhLWIzN2ItNTJiOGIwMTUyMGIz
code_challenge:obgHsGjT2Rle_g9D1LJYzS5IyAHbr3lDiO6ORKJth4k
```

### 1.2.1 获取授权码Code

```shell
GET http://localhost:8080/oauth2/authorize?response_type=code&client_id=clientCodePKCE&scope=testScope&redirect_uri=http://www.baidu.com&code_challenge_method=S256&code_challenge=obgHsGjT2Rle_g9D1LJYzS5IyAHbr3lDiO6ORKJth4k
```

### 1.2.2 通过授权码Code获取token

```shell
POST http://localhost:8080/oauth2/token

x-www-form-urlencoded

grant_type:authorization_code
client_id:clientCodePKCE
redirect_uri:http://www.baidu.com
code_verifier:ZjE0NWI0MmUtNDM1MC00ZDFhLWIzN2ItNTJiOGIwMTUyMGIz
code:l5Uv52PpwarVdr-wdQBj5-yk7wCOYpKYvIuKr0lksVYaxM_jq6yvflawPJU3lJYDvTaWLs1XCgaPXnc0OJ9L1XVKhLFncTLGO4LNpv96aYMUHa2NJ-x0cuSEQaoK2SXq
```

# 3 Client Credentials 客户端模式

```shell
POST http://localhost:8080/oauth2/token

x-www-form-urlencoded

grant_type:client_credentials
client_id:clientCredentials
client_secret:secretCredentials
```

# 4 Device Code 设备码模式

## 4.1 【设备端】获取Device Code

```shell
POST http://localhost:8080/oauth2/device_authorization

x-www-form-urlencoded

client_id:clientDevice
scope:testScope
```

## 4.2 登录并授权【APP端】

```shell
GET http://localhost:8080/oauth2/device_verification?user_code=GJTG-HVVS

或（需要额外配置）

http://localhost:8080/activate?user_code=KFWJ-DDQS
```

## 4.3 获取token【设备端】

```shell
POST http://localhost:8080/oauth2/token

x-www-form-urlencoded

grant_type:urn:ietf:params:oauth:grant-type:device_code
client_id:clientDevice
device_code:Yv6TeF00ew-nPog0kcFI4GAA2NdMagMO_FKHI87zI05deLfj0LH2Aj4lDxEdWsNsrcfqYqHoK70AzZoVJj5EXMWAmosKTRVY4c80nx63MlOjxPUe8uDWEjE-9d2gMaLJ
```

# 5 Refresh Token

```shell
POST http://localhost:8080/oauth2/token

x-www-form-urlencoded

grant_type:refresh_token
client_id:clientCode
client_secret:secretCode
refresh_token:FqZqqjjlpQlpSR5WasldooHF3dKSytJ7gVobHQLVrr6yz7vASviM-VtYnpmQFz33WaBXWn75Te2dwWasMCrtISv_yuZWSTtdzJUjPBbDjWIGB20EhwWOwzKBThPql3HZ
```


