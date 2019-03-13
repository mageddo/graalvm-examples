FROM oracle/graalvm-ce:1.0.0-rc13 as graalvm
COPY . /app/
WORKDIR /app
RUN ./build-native-image.sh

FROM frolvlad/alpine-glibc
EXPOSE 8080
COPY --from=graalvm /app/micronaut .
ENTRYPOINT ["./micronaut"]
