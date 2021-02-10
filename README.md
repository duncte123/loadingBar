[version]: https://api.bintray.com/packages/duncte123/maven/loadingbar/images/download.svg
[download]: https://duncte123.jfrog.io/ui/packages/gav:%2F%2Fme.duncte123:loadingbar

# loadingBar

Util package to make loading bars

## Adding to your project
The current latest version is: [ ![version][] ][download]

#### With gradle

```GRADLE
repositories {
    maven {
        url 'https://duncte123.jfrog.io/artifactory/maven'
    }
}

dependencies {
    implementation group: 'me.duncte123', name: 'loadingbar', version: '[VERSION]'
}
```

#### With maven

```XML
<repository>
    <id>jfrog-duncte123</id>
    <name>jfrog-duncte123</name>
    <url>https://duncte123.jfrog.io/artifactory/maven</url>
</repository>

<dependency>
  <groupId>me.duncte123</groupId>
  <artifactId>loadingbar</artifactId>
  <version>[VERSION]</version>
  <type>pom</type>
</dependency>
```

Make sure to replace `[VERSION]` with the version listed above.

## Examples

To generate a loading bar you can use the `LoadingBar#generateImage` method.
The `generateImage` method returns a byte array that you can write to a file like this:
```java
try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample.png")) {
    outputStream.write(LoadingBar.generateImage(69.69));
}
```

The following code should generate this output:

![The result](https://raw.githubusercontent.com/duncte123/loadingBar/master/loadingBarExample.png)

You can customize the colors using the `LoadingBarConfig` class like this:

```java
LoadingBarConfig config = LoadingBarConfig.defaultConfig()
                            .setFillColor(Color.RED)
                            .setBorderColor(Color.PINK)
                            .setPrecision(3);

try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample-color.png")) {
    outputStream.write(LoadingBar.generateImage(69.69, config));
}
```

Which will result in the following output:

![The result](https://raw.githubusercontent.com/duncte123/loadingBar/master/loadingBarExample-color.png)
