FROM nginx:1.15

ENV APP_NAME esc-server

ADD $APP_NAME/target/classes/portal.conf /etc/nginx/conf.d/portal.conf

VOLUME /home/jenkins/home/workspace/esc-server/

ENV PORT 8080

COPY $APP_NAME/target/classes/static /usr/share/nginx/html/
