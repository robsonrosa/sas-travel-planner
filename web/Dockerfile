FROM nginx:1.19.0
COPY build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
ARG REACT_APP_SERVER_URL
ENV REACT_APP_SERVER_URL=${REACT_APP_SERVER_URL}
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
