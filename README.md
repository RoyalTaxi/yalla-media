# Yalla Media

A Kotlin Multiplatform media library for camera, gallery, and image picking on Android and iOS.

## Installation

Add the GitHub Packages repository and dependency to your project:

```kotlin
// settings.gradle.kts
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/RoyalTaxi/yalla-media")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

// build.gradle.kts
dependencies {
    implementation("uz.yalla:media:1.0.3")
}
```

## Usage

### Camera

```kotlin
YallaCamera(
    modifier = Modifier.fillMaxSize(),
    captureIcon = { onClick -> /* ... */ },
    convertIcon = { onClick -> /* ... */ },
    onCapture = { bytes -> /* ... */ }
)
```

### Gallery

```kotlin
YallaGallery(
    modifier = Modifier.fillMaxSize(),
    onImageSelected = { bytes -> /* ... */ }
)
```

### Image Picker

```kotlin
val picker = rememberImagePickerLauncher(
    selectionMode = SelectionMode.Single,
    scope = rememberCoroutineScope(),
    onResult = { images -> /* ... */ }
)

picker.launch()
```

### Compression

```kotlin
val compressed = compressImage(imageBytes = bytes)
```

## License

Copyright 2026 Yalla. All rights reserved.
