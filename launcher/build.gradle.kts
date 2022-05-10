/*
 *  Copyright (c) 2020, 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

plugins {
    `java-library`
    id("application")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

val edcVersion: String by project
val edcGroup: String by project

dependencies {
    implementation("${edcGroup}:core:${edcVersion}")
    implementation("${edcGroup}:observability-api:${edcVersion}")
    implementation("${edcGroup}:filesystem-configuration:${edcVersion}")
    implementation("${edcGroup}:http:${edcVersion}")

    // API key authentication (also used for CORS support)
    implementation("${edcGroup}:auth-tokenbased:${edcVersion}")

    // Federated catalog
    implementation("${edcGroup}:catalog-cache:${edcVersion}")
    implementation("${edcGroup}:catalog-node-directory-memory:${edcVersion}")
    implementation("${edcGroup}:catalog-cache-store-memory:${edcVersion}")
}

application {
    mainClass.set("org.eclipse.dataspaceconnector.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    exclude("**/pom.properties", "**/pom.xml")
    mergeServiceFiles()
    archiveFileName.set("registration-service.jar")
}
