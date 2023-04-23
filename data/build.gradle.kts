plugins {
    id(Plugins.AGP.library)
    id(Plugins.Kotlin.kapt)
    id(Plugins.Kotlin.android)

}

android {
    namespace = "com.example.notes_architecture"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    //Modules
    implementation(project(":domain"))

    //Testing
    testImplementation(Deps.UI.junit)
    androidTestImplementation(Deps.UI.extJunit)

    //Room
    implementation(Deps.Room.room)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.roomRuntime)

    //Coroutines
    implementation(Deps.Coroutines.android)

    //Inject
    implementation(Deps.JavaX.inject)
}