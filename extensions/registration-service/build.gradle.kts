plugins {
    `java-library`
    id("io.swagger.core.v3.swagger-gradle-plugin")
}

val rsApi : String by project
val edcVersion: String by project
val edcGroup: String by project
val awaitility: String by project
val jupiterVersion: String by project
val assertj: String by project

dependencies{
    implementation("${edcGroup}:core:${edcVersion}")

    testImplementation("org.assertj:assertj-core:${assertj}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${jupiterVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${jupiterVersion}")
}

