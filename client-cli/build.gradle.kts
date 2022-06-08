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
    api(project(":rest-client"))

    api("info.picocli:picocli:4.6.3")
    annotationProcessor("info.picocli:picocli-codegen:4.6.3")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Aproject=${project.group}/${project.name}")
}

application {
    mainClass.set("org.eclipse.dataspaceconnector.registration.cli.RegistrationServiceCli")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    mergeServiceFiles()
    archiveFileName.set("registration-service-cli.jar")
}
