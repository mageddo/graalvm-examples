Evidence how JVM can recover directories resources and 
gaal native binaries can't yet


JVM

```
./gradlew run
[file:.../build/resources/main/folder/subfolder]
[file:.../build/resources/main/folder/subfolder/resource-001.txt]
```


Graal Binary

```
./gradlew clean build nativeImage && ./build/graal/resources-resolution
[]
[resource:folder/subfolder/resource-001.txt]
```
