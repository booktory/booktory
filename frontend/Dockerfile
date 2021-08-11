FROM node:lts-alpine as build-stage
WORKDIR /homepage
COPY . .
RUN npm install
RUN npm run build

FROM nginx:stable-alpine as production-stage

RUN rm /etc/nginx/sites-available/default
COPY ./homepage.conf /etc/nginx/conf.d/default.conf
COPY --from=build-stage ./dist /var/www/html

EXPOSE 3000
CMD ["nginx", "-g", "daemon off;"] 