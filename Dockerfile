FROM vad1mo/groovy-docker

MAINTAINER 8gears AG <hello@8gears.com>

COPY src/service.groovy /app/service.groovy

EXPOSE 5050

CMD ["groovy","/app/service.groovy"]