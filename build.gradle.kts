import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id ("org.flywaydb.flyway") version "6.5.0"
	id("org.seasar.doma.codegen") version "1.2.1"
	id("org.seasar.doma.compile") version "1.1.0"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
	kotlin("plugin.jpa") version "1.4.32"
	kotlin("kapt") version "1.5.0"
}

group = "com.moriokameda"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	val domaVersion: String by project
	val domaSpringVersion: String by project
//	springの設定
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
//	kotlinの設定
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	flywayの設定
	implementation("org.flywaydb:flyway-core")
//	domaの設定
	kapt("org.seasar.doma:doma-processor:$domaVersion")
	implementation("org.seasar.doma:doma-kotlin:$domaVersion")
	implementation("org.seasar.doma:doma-slf4j:$domaVersion")
	implementation("org.seasar.doma.boot:doma-spring-boot-starter:$domaSpringVersion")

//	apacheの設定
	implementation("commons-io:commons-io:2.8.0")
	implementation("org.apache.tika:tika-parsers:1.24.1")
	implementation("org.apache.commons:commons-dbcp2:2.8.0")

	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
//	mysqlの設定
	runtimeOnly("mysql:mysql-connector-java")

	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
