# Bank Project
Plataforma: Android<br>
Versão: 1.0<br>
minSDK: 19

Aplicativo desenvolvido utilizando boas práticas do Android Jetpack<br>

| Auth  | Statement |
| ------------- | ------------- |
| <img src="https://github.com/LucasFerreira159/BankProject/blob/master/screenshots/device-2020-05-19-181234_framed.png" width="300"> | <img src="https://github.com/LucasFerreira159/BankProject/blob/master/screenshots/device-2020-05-19-181202_framed.png" width="300"> |


### Validação de login ###
Para logar no aplicativo você precisará escolher o método E-Mail ou CPF
* Se escolher CPF, terá que digitar um CPF válido

#### Comprimento da senha ####
* O comprimento mínimo da senha (10 caracteres) deve ser inserido.

#### Força da senha: ####
A senha deve atender a pelo menos três das quatro regras de complexidade a seguir: <br>

* Pelo menos um caractere maiúsculo (AZ)
* Pelo menos um caractere minúsculo (az)
* Pelo menos um dígito (0-9)
* Pelo menos um caractere especial

Para validar a força da senha, eu optei em utilizar O zxcvbn que é uma recomendação do padrão OWASP que pode ser encontrado no documento Mobile App Security Checklist 1.1.4 no capítulo : v4 - Authentication and Session Management


### Recursos do Android Jetpack utilizados ###
* View Model
* Live Data
* Navigation
* Android KTX

### Recursos de terceiros utilizados ###

* [OkHttp Logging Interceptor] (https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

* [Retrofit] (https://square.github.io/retrofit/)

* [GSON] (https://github.com/google/gson)

* [RxJava] (https://github.com/ReactiveX/RxAndroid)

* [zxcvbn] (https://github.com/nulab/zxcvbn4j)
