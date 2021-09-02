plugins {
    kotlin("jvm") version "1.5.30"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}

group = "io.github.monun"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("commons-cli:commons-cli:1.3.1")
    implementation("com.google.code.gson:gson:2.8.8")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "16"
        }
    }

    shadowJar {
        archiveClassifier.set("")
        archiveVersion.set("")

        manifest {
            attributes("Main-Class" to "io.github.monun.paperapi.MainKt")
        }
    }
}