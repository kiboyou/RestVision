FROM ubuntu:latest
LABEL authors="enigma"

ENTRYPOINT ["top", "-b"]