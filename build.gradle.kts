import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "net.ins.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    val activity_version = "1.9.3"
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.slf4j:slf4j-api:2.0.16")
    testImplementation("ch.qos.logback:logback-classic:1.5.16")
//    implementation("org.apache.logging.log4j:log4j-core:2.24.3")
//    implementation("androidx.activity:activity-ktx:$activity_version")
//    implementation("androidx.activity:activity-compose:1.3.1")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "notation"
            packageVersion = "1.0.0"
        }
    }
}
