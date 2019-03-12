#### Running on Intellij / Eclipse

* Setup Required JVM Args

		-javaagent:/home/typer/.m2/repository/org/aspectj/aspectjweaver/1.9.2/aspectjweaver-1.9.2.jar

* Activate annotation processing
* Start `Application` class

#### Running on terminal

Setup database

	docker-compose up --force-recreate

Running appplication

	./gradlew run


### Testing api

```bash
$ curl -s localhost:8080/fruits | jq '.'
[
  {
    "name": "GRAPE"
  },
  {
    "name": "STRAWBERRY"
  },
  {
    "name": "APPLE"
  },
  {
    "name": "ORANGE"
  },
  {
    "name": "BLUEBERRY"
  }
]
```
