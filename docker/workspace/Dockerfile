FROM alpine:3.13.5

RUN apk add --no-cache ca-certificates openssl curl && \
    curl -L https://github.com/golang-migrate/migrate/releases/download/v4.14.1/migrate.linux-amd64.tar.gz | tar xvz -C /usr/local/bin && \
    mv /usr/local/bin/migrate.linux-amd64 /usr/local/bin/migrate && \
    sh -c "$(curl --location https://taskfile.dev/install.sh)" -- -d -b /usr/local/bin && \
    rm -rf /tmp/*

WORKDIR /workspace