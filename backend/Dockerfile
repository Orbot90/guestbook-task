FROM alpine
RUN apk add --update openjdk8
WORKDIR /app
COPY build/libs/guestbook-backend.jar /app/backend.jar
EXPOSE 8080
CMD java -jar backend.jar