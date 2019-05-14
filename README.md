# KOALA RANGE #

API de integração do App e Website.

### Ferramentas e Tecnologias Necessárias ###

* Java 8
* Maven 3.*
* Mysql Server

### Como rodar a API? ###

* Rodar o arquivo script-base.sql para criar a base no Mysql
* A partir da raíz do projeto, executar o comando mvn clean install para baixar as dependências do projeto
* Executar o comando mvn spring-boot:run para subir a aplicação
* Acessar o projeto na porta 8080

### Endpoints ###

##### Posts #####
* create - post - /posts/create?desc={desc}&create-date={create-date}&lat={lat}&long={long}&perfil={perfil}&categorias={categorias}
* update - put - /posts/update?post-id={post-id}&desc={desc}&create-date={create-date}&lat={lat}&long={long}&perfil={perfil}&categorias={categorias}
* delete - delete - /posts/delete?id={id}
* find-all - get - /posts/find-all
* find-one - get - /posts/find-one?id={id}
* find-by-filter - get - /posts/find-by-filter?categorias={categorias}&range={range}&duration={duration}&orderby={orderby}

##### Perfil #####
* create - post - /perfil/create?nickname={nickname}&email={email}&avatar={avatar}
* update - put - /perfil/update?post-id={post-id}&desc={desc}&create-date={create-date}&lat={lat}&long={long}&perfil={perfil}&categorias={categorias}
* find-one - get - /perfil/find-one?id={id}

##### Comentários #####
* create - post - /comments/create?desc={desc}&create-date={create-date}&perfil={perfil}&post-id={post-id}
* update - put - /comments/update?id={id}&desc={desc}
* delete - delete - /comments/delete?id={id}
* find-one - get - /comments/find-one?id={id}
* find-by-post - get - /comments/find-by-post?post-id={post-id}

##### Curtidas #####
* create - post - /likes/create?desc={desc}&create-date={create-date}&perfil={perfil}&post-id={post-id}
* delete - delete - /likes/delete?id={id}
* count-post-likes - get - /likes/count-post-likes?post-id={post-id}
