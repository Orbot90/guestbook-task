FROM alpine
RUN apk add --update openjdk8
WORKDIR /app
COPY build/libs/guestbook-backend-1.0-SNAPSHOT.jar /app/backend.jar
EXPOSE 8080
CMD java -jar -Dspring.profiles.active=prod backend.jar