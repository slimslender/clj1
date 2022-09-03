FROM clojure:lein@sha256:3cddeaad6b03391f44134b2ac9f32115a0ef1f91a0bd3544a6da799bc6166115 AS builder

ARG MVN_ARTIFACTORYMAVENREPOSITORY_USER
ARG MVN_ARTIFACTORYMAVENREPOSITORY_PWD

RUN mkdir /build

WORKDIR /build

COPY project.clj /build
COPY src /build/src

RUN lein metajar

FROM clojure:openjdk-11-lein@sha256:2050775923b9d63f448228d28557baf144dbcca86d7f1123c0ea6f498c68f69c

MAINTAINER Jim Clark <jim@atomist.com>

RUN mkdir -p /usr/src/app \
    && mkdir -p /usr/src/app/bin \
    && mkdir -p /usr/src/app/lib

WORKDIR /usr/src/app

COPY --from=builder /build/target/lib /usr/src/app/lib
COPY --from=builder /build/target/metajar/server.jar /usr/src/app/

CMD ["-Djava.net.preferIPv4Stack=true", "-Xmx2048m", "-jar", "/usr/src/app/server.jar", "-Dclojure.core.async.pool-size=20", "-Datomist.kafka-client.pool-size=20"]

EXPOSE 3000
