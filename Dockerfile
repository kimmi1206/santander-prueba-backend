FROM eclipse-temurin:17
RUN mkdir /opt/app
COPY build/libs/products-0.0.1-SNAPSHOT.jar /opt/app/api.jar
EXPOSE 8080:8080
CMD ["java", "-jar", "/opt/app/api.jar"]