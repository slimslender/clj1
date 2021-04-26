FROM clojure:lein@sha256:928a90f9605eb4cc260b2704ae2617703a35ab38fc5d4ac057e05c8116f54c72 AS builder

ARG MVN_ARTIFACTORYMAVENREPOSITORY_USER
ARG MVN_ARTIFACTORYMAVENREPOSITORY_PWD

RUN mkdir /build

WORKDIR /build

COPY project.clj /build
COPY src /build/src

RUN lein metajar

FROM clojure:openjdk-11-lein@sha256:928a90f9605eb4cc260b2704ae2617703a35ab38fc5d4ac057e05c8116f54c72

MAINTAINER Jim Clark <jim@atomist.com>

RUN mkdir -p /usr/src/app \
    && mkdir -p /usr/src/app/bin \
    && mkdir -p /usr/src/app/lib

WORKDIR /usr/src/app

COPY --from=builder /build/target/lib /usr/src/app/lib
COPY --from=builder /build/target/metajar/server.jar /usr/src/app/

CMD ["-Djava.net.preferIPv4Stack=true", "-Xmx2048m", "-jar", "/usr/src/app/server.jar", "-Dclojure.core.async.pool-size=20", "-Datomist.kafka-client.pool-size=20"]

EXPOSE 3000
