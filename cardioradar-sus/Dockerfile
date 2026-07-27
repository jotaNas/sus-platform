# Estágio 1: Compilação usando Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /build

# Copia arquivos para cache de dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código e empacota a aplicação
COPY src ./src
RUN mvn package -DskipTests -Dquarkus.package.type=fast-jar

# Estágio 2: Execução com JRE do Java 21 leve
FROM eclipse-temurin:21-jre-alpine
WORKDIR /deployments

# Permissões de segurança para execução no container
RUN chown 1001 /deployments \
    && chmod "g+rwX" /deployments \
    && chown 1001:root /deployments

# Copia os artefatos gerados pelo Quarkus 
COPY --from=build --chown=1001:root /build/target/quarkus-app/lib/ /deployments/lib/
COPY --from=build --chown=1001:root /build/target/quarkus-app/*.jar /deployments/
COPY --from=build --chown=1001:root /build/target/quarkus-app/app/ /deployments/app/
COPY --from=build --chown=1001:root /build/target/quarkus-app/quarkus/ /deployments/quarkus/

USER 1001

EXPOSE 8080
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"

CMD ["java", "-jar", "/deployments/quarkus-run.jar"]
