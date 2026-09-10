plugins {
    kotlin("jvm") version "2.0.21"
}

group = "com.mateo"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 6.1.3 (la versión de la documentación que estamos aplicando).
    // El BOM fija las versiones de todos los módulos de la plataforma JUnit.
    testImplementation(platform("org.junit:junit-bom:6.1.3"))

    // junit-jupiter trae junit-jupiter-api + junit-jupiter-engine + junit-jupiter-params
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Necesario para que Gradle pueda descubrir y ejecutar las pruebas de la plataforma JUnit.
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        showExceptions = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
