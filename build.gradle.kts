plugins {
    id("java")
}

group = "org.hibernate.bugs"
version = "1.0-SNAPSHOT"

val hibernateVersion = "6.1.6.Final"
val jupiterVersion = "5.9.2"

dependencies {
    implementation("org.hibernate.orm", "hibernate-core", hibernateVersion)
    implementation("org.hibernate.orm", "hibernate-testing", hibernateVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-api", jupiterVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", jupiterVersion)
    testRuntimeOnly("com.h2database", "h2", "2.1.212")
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}