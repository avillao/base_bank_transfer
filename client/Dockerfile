FROM maven:3.9.1-eclipse-temurin-17 as maven

WORKDIR /usr/src/app
COPY . .
RUN mvn package

FROM maven:3.9.1-eclipse-temurin-17
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/bankClient.jar /opt/app/

ENTRYPOINT ["java","-jar","bankClient.jar"]
