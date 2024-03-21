// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.AndroidApplication.id) version Plugins.AndroidApplication.version apply false
    id(Plugins.KotlinAndroid.id) version Plugins.KotlinAndroid.version apply false
    id(Plugins.KotlinJvm.id) version Plugins.KotlinJvm.version apply false
}