//noinspection GradleCompatible
plugins{
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.gabrielbmoro.programmingchallenge"
        minSdkVersion(22)
        targetSdkVersion(29)
        versionCode = 13
        versionName = "1.0.7"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.71")

    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0-rc01")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.room:room-runtime:2.2.5")
    kapt("androidx.room:room-compiler:2.2.5")
    implementation("androidx.room:room-ktx:2.2.5")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0-alpha01")

    implementation("com.google.android.material:material:1.1.0")

    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")

    implementation("com.github.bumptech.glide:glide:4.11.0")

    implementation("org.koin:koin-androidx-scope:2.1.5")
    implementation("org.koin:koin-androidx-viewmodel:2.1.5")
    implementation("org.koin:koin-androidx-ext:2.1.5")

    testImplementation("junit:junit:4.12")
    testImplementation("com.google.truth:truth:0.42")
    testImplementation("androidx.test:core:1.2.0")
    testImplementation("org.mockito:mockito-core:2.27.0")
    testImplementation("org.koin:koin-test:2.1.5")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    androidTestImplementation("androidx.test:rules:1.2.0")
    androidTestImplementation("com.google.truth:truth:0.42")
}