plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = Config.namespace
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.namespace
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    lint {
        abortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // androidx
    implementation(Dependencies.Androidx.appCompat)

    implementation(Dependencies.Androidx.constraintLayout)

    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.coerSplashScreen)

    implementation(Dependencies.Androidx.dataStorePrefs)

    implementation(Dependencies.Androidx.lifecycleRunTime)
    implementation(Dependencies.Androidx.lifecycleLiveData)
    implementation(Dependencies.Androidx.lifecycleViewModel)

    implementation(Dependencies.Androidx.multidex)

    implementation(Dependencies.JetBrains.coroutinesAndroid)
    implementation(Dependencies.JetBrains.coroutinesCore)

    // google
    implementation(Dependencies.Google.material)

    // androidTest
    androidTestImplementation(Dependencies.Androidx.testEspresso)
    androidTestImplementation(Dependencies.Androidx.testJunit)

    // test
    testImplementation(Dependencies.Junit.junit)
}
