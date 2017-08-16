FROM vad1mo/groovy-docker

MAINTAINER 8gears AG <hello@8gears.com>

COPY src/service.groovy /app/service.groovy
RUN grape install io.ratpack ratpack-groovy 1.4.4 && grape install org.slf4j slf4j-simple 1.7.22

EXPOSE 5050

CMD ["groovy","/app/service.groovy"]
