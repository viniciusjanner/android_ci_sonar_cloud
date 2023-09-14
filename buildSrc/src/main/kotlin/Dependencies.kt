object Dependencies {

    object Develop {

        object Androidx {
            const val appCompat          = "androidx.appcompat:appcompat:1.6.1"

            const val constraintLayout   = "androidx.constraintlayout:constraintlayout:2.1.4"

            const val core               = "androidx.core:core-ktx:1.10.1"
            const val coerSplashScreen   = "androidx.core:core-splashscreen:1.0.0-alpha01"

            const val dataStorePrefs     = "androidx.datastore:datastore-preferences:1.0.0"

            const val lifecycleRunTime   = "androidx.lifecycle:lifecycle-runtime-ktx:2.6.1"
            const val lifecycleLiveData  = "androidx.lifecycle:lifecycle-livedata-ktx:2.6.1"
            const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"

            const val multidex           = "androidx.multidex:multidex:2.0.1"
        }

        object Google {
            const val material = "com.google.android.material:material:1.9.0"
        }

        object JetBrains {
            const val coroutinesAndroid  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
            const val coroutinesCore     = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3"
        }
    }

    object Test {

        object Instr {

            object Androidx {
                const val testEspresso = "androidx.test.espresso:espresso-core:3.5.1"
                const val testJunit    = "androidx.test.ext:junit:1.1.5"
            }
        }

        object Unit {
            object Androidx {
                const val archCore     = "androidx.arch.core:core-testing:2.2.0" // testes com LiveData

                const val testCore     = "androidx.test:core:1.5.0"
                const val testExtJunit = "androidx.test.ext:junit:1.1.5" // testes com Coroutines
                const val testExtTruth = "androidx.test.ext:truth:1.5.0"
                const val testRules    = "androidx.test:rules:1.5.0"
                const val testRunner   = "androidx.test:runner:1.5.2"
            }

            object JetBrains {
                const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3"
            }

            object Junit {
                const val junit = "junit:junit:4.13.2"
            }

            object Others {
                const val mockito         = "org.mockito:mockito-core:4.5.1"
                const val mockk           = "io.mockk:mockk:1.13.7"
                const val nhaarmanMockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
            }
        }
    }
}
