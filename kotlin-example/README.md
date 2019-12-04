### Objects Expressions Issues
Kotlin object expressions broke the native-image compilation
process producing an error like below 

```
Caused by: java.lang.NoClassDefFoundError: com/mageddo/Stuff$DefaultImpls
```

The snippet code which generates the error

```kotlin
interface Stuff {
  fun doStuff(msg: String)

  companion object {
    @JvmField
    val DEFAULT_STUFF: Stuff = object : Stuff {
      override fun doStuff(msg: String) {
      }
    }
  }
}
```



#### Reference
* [GraalVM created issue about the error][1]
* [Kotlin created issue indicating the error as a kotlin bug][2]

[1]: https://github.com/oracle/graal/issues/1521
[2]: https://youtrack.jetbrains.com/issue/KT-33097