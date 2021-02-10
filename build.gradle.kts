/*
 *    Copyright 2018 - 2019 Duncan "duncte123" Sterken
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

plugins {
    idea
    `java-library`
    `maven-publish`
}

group = "me.duncte123"
version = "1.3.2_${getBuildNum()}"
val archivesBaseName = "loadingbar"

repositories {
    mavenCentral()
    jcenter() // Legacy :(
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
    implementation(group = "com.fasterxml.jackson.core", name = "jackson-databind", version = "2.10.1")
    implementation(group = "com.google.code.findbugs", name = "jsr305", version = "3.0.2")

}

fun getBuildNum(): String {
    return System.getenv("GITHUB_RUN_NUMBER") ?: "dev"
}

val compileJava: JavaCompile by tasks
val javadoc: Javadoc by tasks
val jar: Jar by tasks
val build: Task by tasks
val publish: Task by tasks
val clean: Task by tasks
val test: Task by tasks
val check: Task by tasks

val sourcesJar = task<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allJava)
}

val javadocJar = task<Jar>("javadocJar") {
    dependsOn(javadoc)
    archiveClassifier.set("javadoc")
}

publishing {
    repositories {
        maven {
            name = "jfrog"
            url = uri("https://duncte123.jfrog.io/artifactory/maven/")
            credentials {
                username = System.getenv("JFROG_USERNAME")
                password = System.getenv("JFROG_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("jfrog") {
            from(components["java"])

            artifactId = archivesBaseName
            groupId = project.group as String
            version = project.version as String

            artifact(javadocJar)
            artifact(sourcesJar)
        }
    }
}

build.apply {
    dependsOn(jar)
    dependsOn(javadocJar)
    dependsOn(sourcesJar)

    jar.mustRunAfter(clean)
    javadocJar.mustRunAfter(jar)
    sourcesJar.mustRunAfter(javadocJar)
}

publish.apply {
    dependsOn(build)

    onlyIf {
        System.getenv("JFROG_USERNAME") != null && System.getenv("JFROG_TOKEN") != null
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "6.8"
    distributionType = Wrapper.DistributionType.ALL
}
