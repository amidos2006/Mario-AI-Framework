import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
}

group = "cz.cuni.gamedev.nail123"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.apache.commons", "commons-csv", "1.8")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.register<JavaExec>("singleRun") {
    group = "run"

    classpath = sourceSets.main.get().runtimeClasspath
    main = "RunGameKt"
}

tasks.register<JavaExec>("generateCSV") {
    group = "run"

    classpath = sourceSets.main.get().runtimeClasspath
    main = "GatherMetricsKt"
}

