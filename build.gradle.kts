plugins {
    application
    alias(libs.plugins.spring.boot.gradle.plugin)
    alias(libs.plugins.openapi.generator.plugin)
    alias(libs.plugins.lombok.gradle.plugin)
}

apply(plugin = "io.spring.dependency-management")

group = "com.proctor.service"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDir("${layout.buildDirectory.asFile.get().path}/generated/src/main/java")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

dependencies {
    implementation(libs.springDocOpenApiWebfluxUi)
    implementation(libs.springDocOpenApiWebfluxApi)
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.data:spring-data-r2dbc")
    implementation ("io.asyncer:r2dbc-mysql")
    implementation("org.projectlombok:lombok")
}

openApiGenerate {
    generatorName.set("spring")
    outputDir.set("${layout.buildDirectory.asFile.get().path}/generated")
    inputSpec.set("${projectDir.toURI()}/src/main/resources/openapi/openapi-spec.yaml")
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
            "useBeanValidation" to "true",
            "hateoas" to "true"
        )
    )
    apiFilesConstrainedTo.set(mutableListOf(""))
    modelFilesConstrainedTo.set(mutableListOf(""))
    typeMappings.set(
        mutableMapOf<String, String>(
            "problemDetail" to "ProblemDetail",
            "offsetDateTime" to "OffsetDateTime",
        )
    )
    importMappings.set(
        mutableMapOf<String, String>(
            "ProblemDetail" to "org.springframework.http.ProblemDetail",
            "OffsetDateTime" to "java.time.OffsetDateTime",
        )
    )
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