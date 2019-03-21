This is an example of how to use sqlite with graalvm native image

### Using on standard JVM 

```
./gradlew -q run
It Works from SQLITE!!!
```


### Building and running from binary file

Unfortunately sqlite driver is not working with graalvm yet,
bellow the steps to reproduce the problem

```
$ ./gradlew clean build nativeImage && ./build/graal/sqlite
Failed to load native library:sqlite-unknown-b8850178-1f5f-41ea-a1dd-1d3349f3a388-libsqlitejdbc.so. osinfo: Linux/x86_64
java.lang.UnsatisfiedLinkError: Unsupported JNI version 0xffffffff, required by /var/tmp/sqlite-unknown-b8850178-1f5f-41ea-a1dd-1d3349f3a388-libsqlitejdbc.so
Exception in thread "main" java.lang.UnsatisfiedLinkError: org.sqlite.core.NativeDB._open_utf8([BI)V [symbol: Java_org_sqlite_core_NativeDB__1open_1utf8 or Java_org_sqlite_core_NativeDB__1open_1utf8___3BI]
	at com.oracle.svm.jni.access.JNINativeLinkage.getOrFindEntryPoint(JNINativeLinkage.java:145)
	at com.oracle.svm.jni.JNIGeneratedMethodSupport.nativeCallAddress(JNIGeneratedMethodSupport.java:54)
	at org.sqlite.core.NativeDB._open_utf8(NativeDB.java)
	at org.sqlite.core.NativeDB._open(NativeDB.java:78)
	at org.sqlite.core.DB.open(DB.java:195)
	at org.sqlite.SQLiteConnection.open(SQLiteConnection.java:243)
	at org.sqlite.SQLiteConnection.<init>(SQLiteConnection.java:61)
	at org.sqlite.jdbc3.JDBC3Connection.<init>(JDBC3Connection.java:28)
	at org.sqlite.jdbc4.JDBC4Connection.<init>(JDBC4Connection.java:21)
	at org.sqlite.JDBC.createConnection(JDBC.java:115)
	at org.sqlite.SQLiteDataSource.getConnection(SQLiteDataSource.java:410)
	at org.sqlite.SQLiteDataSource.getConnection(SQLiteDataSource.java:398)
	at com.mageddo.sqlite.App.main(App.java:15)

```
