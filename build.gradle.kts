plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "1.42"
    id("org.jetbrains.kotlin.jvm") version "1.5.10"
}

taboolib {
    install("common")
    install("common-5")
    install("module-ai")
    install("module-chat")
    install("module-configuration")
    install("module-database")
    install("module-metrics")
//    install("module-effect")
    install("module-kether")
    install("module-lang")
    install("module-navigation")
    install("module-navigation")
    install("module-nms")
    install("module-nms-util")
    install("module-ui")
    install("expansion-command-helper")
    install("platform-bukkit")
    classifier = null
    version = "6.0.9-64"

    description {
        contributors {
            name("纸杯")
        }
        dependencies {
            name("GermPlugin").optional(true)
            name("Chemdah").optional(true)
            name("Planners").optional(true)
            name("OriginAttribute").optional(true)
            name("CMI").optional(true)
            name("PlaceholderAPI").optional(true)
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11900:11900-minimize:mapped")
    compileOnly("ink.ptms.core:v11900:11900-minimize:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    repositories {
        maven {
            url = uri("https://repo.tabooproject.org/repository/releases")
            credentials {
                username = project.findProperty("taboolibUsername").toString()
                password = project.findProperty("taboolibPassword").toString()
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            groupId = project.group.toString()
        }
    }
}