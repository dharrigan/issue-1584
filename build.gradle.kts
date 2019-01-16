import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    val kotlinVersion = "1.3.11"
    val nebulaKotlinVersion = "1.3.11"
    val nebulaProjectVersion = "5.2.1"
    val springBootVersion = "2.1.1.RELEASE"
    val springDependencyManagementVersion = "1.0.6.RELEASE"

    idea
    java
    jacoco
    kotlin("jvm") version kotlinVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    id("nebula.integtest") version nebulaProjectVersion
    id("nebula.kotlin") version nebulaKotlinVersion
    id("nebula.project") version nebulaProjectVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.springframework.boot") version springBootVersion
}

val nexusUrl: String by project

val assertJVersion = "3.11.1"
val azureServiceBusVersion = "2.1.0"
val azureServiceBusJavaVersion = "1.2.10"
val elVersion = "3.0.1-b10"
val guavaVersion = "27.0.1-jre"
val jacksonKotlinVersion = "2.9.8"
val jacksonVersion = "2.9.8"
val jsonPathVersion = "2.4.0"
val kotlinLoggingVersion = "1.6.22"
val kotlinMockitoVersion = "2.1.0"
val kotlinVersion = "1.3.11"
val mockitoVersion = "2.23.16"
val springCloudStreamVersion = "Fishtown.RELEASE"
val springCloudVersion = "Finchley.SR2"
val validationApiVersion = "2.0.1.Final"
val jaxbRuntimeVersion = "2.3.1"

group = "com.dharrigan"
version = System.getenv("PROJECT_VERSION") ?: "0.0.0-SNAPSHOT"
description = "Circular Stream"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenLocal()
    maven("$nexusUrl/maven-public")
}

configurations {
    all { resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS) }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        mavenBom("org.springframework.cloud:spring-cloud-stream-dependencies:$springCloudStreamVersion")
    }
}

dependencies {
    implementation(kotlin("reflect", kotlinVersion))

    // Depends on Spring Boot 2.1 coming out with better Kotlin Support
    // https://github.com/spring-projects/spring-boot/issues/5537
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-integration")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon")
    implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonKotlinVersion")

    implementation("com.google.guava:guava:$guavaVersion")
    implementation("com.microsoft.azure:azure-servicebus-spring-boot-starter:$azureServiceBusVersion")
    implementation("com.microsoft.azure:azure-servicebus:$azureServiceBusJavaVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("javax.validation:validation-api:$validationApiVersion")
    implementation("org.glassfish:javax.el:$elVersion")
    implementation("com.jayway.jsonpath:json-path:$jsonPathVersion")

    // Required for Java 10 (since Azure Service Bus 'azure-servicebus-spring-boot-starter' depends upon it!)
    api("org.glassfish.jaxb:jaxb-runtime:$jaxbRuntimeVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:$kotlinMockitoVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    integTestImplementation("org.springframework.cloud:spring-cloud-stream-test-support")
}

idea {
    module.isDownloadJavadoc = true
    project {
        vcs = "Git"
        languageLevel = IdeaLanguageLevel(java.sourceCompatibility)
    }
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(arrayOf("-Xlint:all", "-parameters"))
        options.setIncremental(true)
    }
    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            javaParameters = true
            jvmTarget = java.sourceCompatibility.name
        }
    }
    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = java.sourceCompatibility.name
            javaParameters = true
        }
    }
    jacocoTestReport {
        reports.html.isEnabled = true
    }
    test {
        failFast = true
        useJUnitPlatform()
        testLogging {
            showExceptions = true
            showStackTraces = true
            exceptionFormat = TestExceptionFormat.FULL
        }
    }
    bootJar {
        rootSpec.exclude("logback.xml", "**/config/application-*.yml")
    }
}
