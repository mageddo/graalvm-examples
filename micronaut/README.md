#### Running

#### Generating Binary

Compiling the ginary

	docker-compose up --force-recreate --build native-image
	
Copying the binary from the container

	docker-compose run -T --rm --entrypoint cat native-image ./micronaut > micronaut

#### Reference
* http://www.greggbolinger.com/using-springs-jdbctemplate-with-micronaut/
* https://guides.micronaut.io/creating-your-first-micronaut-app/guide/index.html
