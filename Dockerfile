FROM mysql:5.7
ENV MYSQL_DATABASE=mystok
ENV MYSQL_USER=root
ENV MYSQL_ROOT_PASSWORD=password
EXPOSE 3306
CMD ["mysqld","--character-set-server=utf8"]
