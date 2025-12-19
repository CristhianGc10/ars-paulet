# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep Room entities
-keep class com.ars.paulet.data.local.entity.** { *; }

# Keep Coil
-keep class coil3.** { *; }

# Keep Lottie
-keep class com.airbnb.lottie.** { *; }

# Keep ExoPlayer
-keep class androidx.media3.** { *; }

# Kotlin Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Keep generic type information for Kotlin
-keepattributes Signature
-keepattributes *Annotation*

# For debugging - remove in production
-keepattributes SourceFile,LineNumberTable
