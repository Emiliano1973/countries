FROM customjava17:1.0


MAINTAINER Emiliano Echinofora "emiliano.echinofora@gmail.com"

EXPOSE 8080

WORKDIR /usr/local/bin/

COPY target/counties-0.0.1-SNAPSHOT.jar webapp.jar

CMD ["java", "-jar","webapp.jar"]