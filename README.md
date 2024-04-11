# api-template
快速搭建 springboot 框架

通过filter进行username password 验证。 通过添加 其他filter进行别的验证，
### 验证url
``/login POST username password form-data``

### 响应格式
``{ code:string, msg:string, value:any }``

### 请求 header 携带token格式

``Authorization: bearer {jwttoken}``

### 目的->
邮箱登录, 第三方登录