import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.publish.maven.MavenPublication
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.multiplatform.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    `maven-publish`
}

group = "uz.yalla"
version = "1.0.5"

kotlin {
    targets.withType(KotlinNativeTarget::class.java).configureEach {
        compilations.getByName("main") {
            cinterops {
                val coremedia by creating {
                    defFile("src/nativeInterop/cinterop/coremedia.def")
                    compilerOpts("-framework", "CoreMedia")
                }
                val corevideo by creating {
                    defFile("src/nativeInterop/cinterop/corevideo.def")
                    compilerOpts("-framework", "CoreVideo")
                }
            }
        }
    }

    targets.withType(KotlinMultiplatformAndroidLibraryTarget::class.java).configureEach {
        namespace = "uz.yalla.media"
        compileSdk = 36
        minSdk = 26

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        androidResources {
            enable = true
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "YallaMedia"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material)
            implementation(libs.paging.common)
            implementation(libs.paging.compose.common)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.exifinterface)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.accompanist.permissions)
            implementation(libs.camera.camera2)
            implementation(libs.camera.lifecycle)
            implementation(libs.camera.view)
            implementation(libs.kotlinx.coroutines.guava)
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/RoyalTaxi/yalla-media")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

afterEvaluate {
    publishing {
        publications.withType<MavenPublication>().configureEach {
            val baseName = rootProject.name
            if (artifactId.startsWith(baseName)) {
                artifactId = artifactId.replace(baseName, "media")
            }
        }
    }
}
