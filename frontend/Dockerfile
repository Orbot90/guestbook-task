FROM alpine
WORKDIR /app
COPY ./package*.json ./
RUN apk add --update nodejs nodejs-npm
RUN npm install
COPY . /app
RUN npm run build
EXPOSE 3000
CMD npm start