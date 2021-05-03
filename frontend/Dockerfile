FROM alpine
WORKDIR /app
COPY ./package*.json ./
RUN apk add --update nodejs nodejs-npm
RUN npm install
COPY . /app
RUN npm run build
EXPOSE 8081
CMD npm start