FROM openjdk:11
EXPOSE 8084
ADD "target/usermanagement.jar" "usermanagement.jar"
ENTRYPOINT [ "java", "-jar", "/usermanagement.jar" ]