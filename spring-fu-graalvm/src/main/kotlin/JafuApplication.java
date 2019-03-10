import org.springframework.fu.jafu.JafuApplication;

import static org.springframework.fu.jafu.Jafu.webApplication;
import static org.springframework.fu.jafu.web.WebFluxServerDsl.server;


public class DemoApplication {

	public static JafuApplication app = webApplication(a ->
		a.enable(server(s -> s.router(r ->
			r.GET("/", request -> ok().syncBody("Hello world!"))
		)))
	);

	public static void main (String[] args) {
		app.run(args);
	}
}
