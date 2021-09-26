flyway:migrate -Dflyway.url=jdbc:h2:file:./h2/crudy;DB_CLOSE_ON_EXIT=FALSE -Dflyway.user=sa -Dflyway.password=


pour le projet Springboot admin si sur machine de dev

@EnableAdminServer

server.port=8181