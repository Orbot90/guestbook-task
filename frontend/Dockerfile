FROM node:latest

RUN npm install webpack@4.44.2 -g

WORKDIR /tmp
RUN npm i -D webpack-cli -g

COPY ./package*.json ./
RUN npm config set registry http://registry.npmjs.org/ && npm install

WORKDIR /app

COPY . /app
RUN cp -a /tmp/node_modules /usr/src/app/
RUN npm install --save-dev babel-loader@8.1.0
RUN npm install --save-dev style-loader css-loader
RUN webpack

EXPOSE 8081
CMD npm start