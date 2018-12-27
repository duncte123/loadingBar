[version]: https://api.bintray.com/packages/duncte123/maven/loadingbar/images/download.svg
[download]: https://bintray.com/duncte123/maven/loadingbar/_latestVersion

# loadingBar [![Build Status](https://travis-ci.org/duncte123/loadingBar.svg?branch=master)](https://travis-ci.org/duncte123/loadingBar)

Util package to make loading bars

## Adding to your project
The current latest version is: [ ![version][] ][download]

#### With gradle

```GRADLE
repositories {
    jcenter()
}

dependencies {
    implementation group: 'me.duncte123', name: 'loadingbar', version: '[VERSION]'
}
```

#### With maven

```XML
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
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
                            .setBorderColor(Color.PINK);

try (FileOutputStream outputStream = new FileOutputStream("loadingBarExample-color.png")) {
    outputStream.write(LoadingBar.generateImage(69.69, config));
}
```

Witch will result in the following output:

![The result](https://raw.githubusercontent.com/duncte123/loadingBar/master/loadingBarExample-color.png)