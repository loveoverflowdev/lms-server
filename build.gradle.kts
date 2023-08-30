
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("plugin.serialization") version "1.9.10"
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.3"
}

group = "com.asura"
version = "0.0.1"

application {
    mainClass.set("com.asura.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

val exposedVersion: String by project

dependencies {
    /// KTOR
    implementation("io.ktor:ktor-server-core-jvm")
    // Server
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    // Serialization
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    // Validation
    implementation("io.ktor:ktor-server-request-validation:$ktor_version")

    /// DateTime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    /// Database
    // ORM
    implementation("org.flywaydb:flyway-core:9.16.0")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
    // MYSQL
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation(dependencyNotation = "mysql:mysql-connector-java:8.0.28")

    /// Password Hashing
    implementation("at.favre.lib:bcrypt:0.10.2")

    /// Authentication
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation(kotlin("stdlib-jdk8"))
}

kotlin {
    jvmToolchain(11)
}
