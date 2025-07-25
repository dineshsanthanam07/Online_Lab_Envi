plugins {
    application
    alias(libs.plugins.spring.boot.gradle.plugin)
    alias(libs.plugins.openapi.generator.plugin)
}

apply(plugin = "io.spring.dependency-management")

group = "com.proctor.service"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
        nativeImageCapable = true
    }
}

dependencies {
    implementation(libs.springDocOpenApiWebfluxUi)
    implementation(libs.springDocOpenApiWebfluxApi)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}

openApiGenerate {
    generatorName.set("spring")
    outputDir.set(projectDir.path)
    inputSpecRootDirectory.set("${projectDir.path}/src/main/resources/openapi")
    packageName.set("com.proctor.service")
    apiPackage.set("com.proctor.service.controller")
    modelPackage.set("com.proctor.service.dto")
    configOptions.set(
        mutableMapOf<String, String>(
            "openApiNullable" to "false",
            "useSpringBoot3" to "true",
            "interfaceOnly" to "true",
            "requestMappingMode" to "api_interface",
            "useJakartaEe" to "true",
            "skipDefaultInterface" to "true",
            "documentationProvider" to "springdoc",
            "reactive" to "true",
            "useBeanValidation" to "true"
        )
    )
    apiFilesConstrainedTo.set(mutableListOf(""))
    modelFilesConstrainedTo.set(mutableListOf(""))
}

tasks.compileJava {
    dependsOn(tasks.openApiGenerate)
}

tasks.processResources {
    dependsOn(tasks.openApiGenerate)
}

tasks.processTestResources {
    dependsOn(tasks.openApiGenerate)
}