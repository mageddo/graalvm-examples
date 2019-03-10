import jdk.nashorn.internal.runtime.PropertyDescriptor.GET
import org.omg.CORBA.ServerRequest
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.kofu.application
import org.springframework.boot.kofu.ref
import org.springframework.boot.kofu.web.jackson
import org.springframework.boot.kofu.web.mustache
import org.springframework.boot.kofu.web.server
import org.springframework.context.support.beans
import java.net.URI

val app = application {
	import(beans)
	listener<ApplicationReadyEvent> {
		ref<UserRepository>()
	}
//	properties<SampleProperties>("sample")
	server {
		port = if (profiles.contains("test")) 8181 else 8080
		mustache()
		codecs {
			string()
			jackson {
				indentOutput = true
			}
		}
		routes(import = ::appRoutes)
		import(::routes)
	}
}

val beans = beans {
	bean<UserRepository>()
	bean<UserHandler>()
}

fun routes(userHandler: UserHandler) = router {
	router.GET("/", userHandler::listView)
	router.GET("/api/user", userHandler::listApi)
	GET("/conf", userHandler::conf)
}

fun main() = {

	app.run()
}

class UserHandler(private val userRepository: UserRepository) {
	fun findAll(request: ServerRequest) = ok().body(userRepository.findAll())
//	fun findById(request: ServerRequest) = userRepository
//		.findById(request.pathVariable("id"))
//		.flatMap { ok().syncBody(it) }
//		.switchIfEmpty(notFound().build())
//
//	fun new(request: ServerRequest) = request
//		.bodyToMono<UserRequest>()
//		.map { User(login = it.login, age = it.age) }
//		.flatMap { userRepository.save(it) }
//		.flatMap { created(URI.create("/api/user/${it.id}")).syncBody(it) }
//
//	fun update(request: ServerRequest) = request
//		.bodyToMono<UserRequest>()
//		.zipWith(userRepository.findById(request.pathVariable("id")))
//		.map { User(it.t2.id, it.t1.login, it.t1.age) }
//		.flatMap { userRepository.save(it) }
//		.flatMap { ok().syncBody(it) }
//		.switchIfEmpty(notFound().build())
//
//
//	fun delete(request: ServerRequest) = userRepository
//		.findById(request.pathVariable("id"))
//		.flatMap { userRepository.delete(it).then(noContent().build()) }
//		.switchIfEmpty(notFound().build())

}


class UserRepository() {
	fun count(): Long = 5
	fun findAll(): List<User> = listOf()
	fun findOne(id: String): User? = null
	fun deleteAll() = {}
	 fun save(user: User): User? = null
}

class User() {

}

