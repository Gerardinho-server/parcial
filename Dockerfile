FROM openjdk:17
COPY "./target/sigcom-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8107
ENTRYPOINT [ "java" "-jar" "app.jar" ]