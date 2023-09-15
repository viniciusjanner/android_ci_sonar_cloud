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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        multiDexEnabled = true
    } // NOSONAR

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
} // NOSONAR

dependencies {
    // ----------------------------------------------------------------------------------------------
    // Develop
    // ----------------------------------------------------------------------------------------------
    // Androidx
    implementation(Dependencies.Develop.Androidx.appCompat)
    implementation(Dependencies.Develop.Androidx.constraintLayout)
    implementation(Dependencies.Develop.Androidx.core)
    implementation(Dependencies.Develop.Androidx.coerSplashScreen)
    implementation(Dependencies.Develop.Androidx.dataStorePrefs)
    implementation(Dependencies.Develop.Androidx.lifecycleRunTime)
    implementation(Dependencies.Develop.Androidx.lifecycleLiveData)
    implementation(Dependencies.Develop.Androidx.lifecycleViewModel)
    implementation(Dependencies.Develop.Androidx.multidex)
    // Google
    implementation(Dependencies.Develop.Google.material)
    // JetBrains
    implementation(Dependencies.Develop.JetBrains.coroutinesAndroid)
    implementation(Dependencies.Develop.JetBrains.coroutinesCore)

    // ----------------------------------------------------------------------------------------------
    // Test > Instr
    // ----------------------------------------------------------------------------------------------
    // Androidx
    androidTestImplementation(Dependencies.Test.Instr.Androidx.testEspresso)
    androidTestImplementation(Dependencies.Test.Instr.Androidx.testJunit)

    // ----------------------------------------------------------------------------------------------
    // Test > Unit
    // ----------------------------------------------------------------------------------------------
    // Androidx
    testImplementation(Dependencies.Test.Unit.Androidx.archCore)
    testImplementation(Dependencies.Test.Unit.Androidx.testCore)
    testImplementation(Dependencies.Test.Unit.Androidx.testExtJunit)
    testImplementation(Dependencies.Test.Unit.Androidx.testExtTruth)
    testImplementation(Dependencies.Test.Unit.Androidx.testRules)
    testImplementation(Dependencies.Test.Unit.Androidx.testRunner)
    // JetBrains
    testImplementation(Dependencies.Test.Unit.JetBrains.kotlinxCoroutines)
    // Junit
    testImplementation(Dependencies.Test.Unit.Junit.junit)
    // Others
    testImplementation(Dependencies.Test.Unit.Others.mockito)
    testImplementation(Dependencies.Test.Unit.Others.mockk)
    testImplementation(Dependencies.Test.Unit.Others.nhaarmanMockito)
}
