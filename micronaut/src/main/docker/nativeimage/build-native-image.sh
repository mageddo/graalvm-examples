pwd
echo $HOME
echo "> Building jar"
./gradlew clean assemble

echo "> Discovering refletion classes"
export JAR_CLASS_PATH=build/libs/micronaut.jar
java -cp ${JAR_CLASS_PATH} io.micronaut.graal.reflect.GraalClassLoadingAnalyzer

echo "> Building binary file"
native-image --no-server \
	--class-path "${JAR_CLASS_PATH}" \
	-H:ReflectionConfigurationFiles=build/reflect.json \
	-H:EnableURLProtocols=http \
	-H:IncludeResources="logback.xml|application.yml" \
	-H:Name=micronaut \
	-H:Class=com.mageddo.micronaut.Application \
	-H:+ReportUnsupportedElementsAtRuntime \
	-H:+AllowVMInspection \
	--allow-incomplete-classpath \
	--rerun-class-initialization-at-runtime='sun.security.jca.JCAUtil$CachedSecureRandomHolder,javax.net.ssl.SSLContext' \
	--delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder,io.netty.handler.ssl.util.ThreadLocalInsecureRandom,com.sun.jndi.dns.DnsClient
