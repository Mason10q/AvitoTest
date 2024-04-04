import buildsrc.Libs
import buildsrc.Versions

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "ru.avito.avitotest"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "ru.avito.avitotest"
        minSdk = Versions.versionCode
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = Versions.compatibility
        targetCompatibility = Versions.compatibility
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.Google.material)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.fragments)
    implementation(Libs.AndroidX.navigationUiKtx)
    implementation(Libs.AndroidX.navigationFragmentKtx)

    implementation(Libs.DI.dagger)
    kapt(Libs.DI.daggerCompiler)

    implementation(Libs.Network.gson)

    implementation(Libs.Network.ohttp)
    implementation(platform(Libs.Network.okhttpBom))
    implementation(Libs.Network.okhttpLogInter)

    implementation(Libs.Network.retrofit)
    implementation(Libs.Network.retrofitGson)
    implementation(Libs.Network.retrofitRxJava3)

    implementation(Libs.RxJava.rxJava)
    implementation(Libs.RxJava.rxJavaAndroid)
}