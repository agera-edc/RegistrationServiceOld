plugins {
    java
    `java-library`
    `maven-publish`
}

val swagger: String by project
val rsApi : String by project


publishing {
    repositories {
        mavenLocal()
    }
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven {
            url = uri("https://maven.iais.fraunhofer.de/artifactory/eis-ids-public/")
        }
    }

    pluginManager.withPlugin("io.swagger.core.v3.swagger-gradle-plugin") {

        dependencies {
            // this is used to scan the classpath and generate an openapi yaml file
            implementation("io.swagger.core.v3:swagger-jaxrs2-jakarta:${swagger}")
            implementation("jakarta.ws.rs:jakarta.ws.rs-api:${rsApi}")
        }
        // this is used to scan the classpath and generate an openapi yaml file
        tasks.withType<io.swagger.v3.plugins.gradle.tasks.ResolveTask> {
            outputFileName = project.name
            outputFormat = io.swagger.v3.plugins.gradle.tasks.ResolveTask.Format.YAML
            prettyPrint = true
            classpath = java.sourceSets["main"].runtimeClasspath
            buildClasspath = classpath
            resourcePackages = setOf("org.eclipse.dataspaceconnector")
            outputDir = file("${rootProject.projectDir.path}/resources/openapi/yaml")
        }
        configurations {
            all {
                exclude(group = "com.fasterxml.jackson.jaxrs", module = "jackson-jaxrs-json-provider")
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
    }
}
buildscript {
    dependencies {
        classpath("io.swagger.core.v3:swagger-gradle-plugin:2.1.13")
    }
}
