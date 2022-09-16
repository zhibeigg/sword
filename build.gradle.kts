plugins {
    `java-library`
    `maven-publish`
    id("io.izzel.taboolib") version "1.42"
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
}

taboolib {
    install("common")
    install("common-5")
    install("common-5-shaded")
    install("module-chat")
    install("module-chat-shaded")
    install("module-ai")
    install("module-kether")
    install("module-effect")
    install("module-nms")
    install("module-nms-util")
    install("module-configuration")
    install("platform-bukkit")
    install("expansion-command-helper")
    install("expansion-player-database")
    classifier = null
    version = "6.0.9-92"

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
            name("AuthMe").optional(true)
            name("Kingdoms").optional(true)
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