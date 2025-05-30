plugins {
	kotlin("jvm") version "2.1.20" apply false
	kotlin("plugin.spring") version "2.1.20" apply false
	kotlin("plugin.jpa") version "2.1.20" apply false
	kotlin("kapt") version "2.1.20" apply false
	id("org.springframework.boot") version "3.5.0" apply false
	id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
	group = "dev.dornol.ticket"
	version = "1.0"

	repositories {
		mavenCentral()
	}

}