plugins {
    id("java")
}

group = "org.ppp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.apache.poi:poi:4.1.2")
    implementation("org.apache.poi:poi-ooxml:4.1.2")
    implementation("com.google.code.gson:gson:2.8.9")


    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

}

tasks.test {
    useJUnitPlatform()
}