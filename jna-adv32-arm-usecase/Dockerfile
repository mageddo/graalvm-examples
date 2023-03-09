FROM debian:9-slim AS BULDER
# GLIB 2.24
ENV GRAALVM_URL='https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-22.3.1/graalvm-ce-java17-linux-amd64-22.3.1.tar.gz'
ADD ${GRAALVM_URL} /graalvm/graalvm.tgz
RUN tar --strip 1 -zxvf /graalvm/graalvm.tgz -C /graalvm &&\
    /graalvm/bin/gu install native-image &&\
    apt-get update -y &&\
    apt-get install --force-yes -y build-essential libz-dev zlib1g-dev
ENV JAVA_HOME=/graalvm
COPY ./ /app
WORKDIR /app
RUN ./gradlew build nativeCompile

FROM debian:9-slim
COPY --from=BULDER /app/build/native/nativeCompile/graalvm-jna /bin/graalvm-jna
ENTRYPOINT /bin/graalvm-jna
