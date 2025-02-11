
## Projeto REST API com Java 21 e Spring Boot 3, utilizando a estrutura da Clean Architecture

### 1 - Baixar o gradle, colocar nas variáveis de ambiente em PATH, o bin e executei o seguinte comando no cmd para testar a versão
``` 
gradle -v
```

### 2 - Dependências do spring boot via gradle + kotlin:

```
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

```

### 3 - Estrutura do projeto (backend) da pasta 'java/com.lorenzozagallo.ca24':
```
adapters/: Inclui os adaptadores que facilitam a comunicação entre a aplicação e o mundo externo (único diretório que "conhece" o Spring).
    in/: Abriga os adaptadores de entrada, tais como controladores REST, que lidam com as requisições dos usuários.
    out/: Contém os adaptadores de saída, responsáveis da interação com bancos de dados e APIs externas, por exemplo.

application/: Hospeda os casos de uso da aplicação, encapsulando a lógica de negócios essencial.

domain/: Representa o coração da aplicação, englobando entidades, exceções e interfaces (portas) que articulam as regras de negócio fundamentais.
    exception/: Define as exceções personalizadas pertinentes ao domínio.
    model/: Modela as entidades do domínio, refletindo os conceitos centrais da aplicação.
    ports/: Estabelece as interfaces que delineiam os contratos para os adaptadores e serviços externos.

Application.java: A classe principal que orquestra a configuração e o execução da aplicação.
```