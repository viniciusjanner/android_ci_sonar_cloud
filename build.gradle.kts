plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("org.sonarqube") version "4.3.1.3277"
}

sonar {
    properties {
        property("sonar.projectKey", "viniciusjanner_android_github_actions_sonar_cloud")
        property("sonar.organization", "viniciusjanner")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}
