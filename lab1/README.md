## Build and usage

```sh
mvn clean package

# Можно явно указать директорию (по умолчанию .)
java -jar target/lab1-1.0-SNAPSHOT.jar path/to/directory
```

## Build and usage (alternative)

```sh
mvn compile
mvn exec:java "-Dexec.mainClass=ru.spbstu.telematics.java.App" "-Dexec.args=path/to/directory"
```

