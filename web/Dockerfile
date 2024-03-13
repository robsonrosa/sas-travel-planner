# builder image
FROM node:18-alpine as builder
WORKDIR /app
COPY src .src
COPY *.js .
COPY *.json .
RUN npm install
COPY . .
ARG REACT_APP_SERVER_URL
ENV REACT_APP_SERVER_URL=${REACT_APP_SERVER_URL}
RUN npm run build

# runner image
FROM nginx:1.19.0
COPY --from=builder /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
