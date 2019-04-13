FROM tomcat:9
RUN groupadd -r jenkins \
  && useradd -r -g jenkins jenkins
RUN groupadd -r docker \
  && useradd -r -g docker osgdev
RUN chown -R jenkins:jenkins /usr/local/tomcat
RUN chmod -R 777 /usr/local/tomcat
USER jenkins
WORKDIR /usr/local/tomcat
EXPOSE 8080
CMD ["/usr/local/tomcat/bin/catalina.sh","run"]
