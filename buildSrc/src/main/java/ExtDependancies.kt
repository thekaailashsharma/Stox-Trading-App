import org.gradle.kotlin.dsl.DependencyHandlerScope

class DepsBuilder {
    internal val list = ArrayList<String>()
    operator fun String.unaryPlus() = list.add(this)
}

fun DependencyHandlerScope.implementation(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("implementation", dep)
        }
    }

fun DependencyHandlerScope.kapt(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("kapt", dep)
        }
    }

fun DependencyHandlerScope.api(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("api", dep)
        }
    }


fun DependencyHandlerScope.testImplementation(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("testImplementation", dep)
        }
    }


fun DependencyHandlerScope.androidTestImplementation(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("androidTestImplementation", dep)
        }
    }


fun DependencyHandlerScope.classpath(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("classpath", dep)
        }
    }

fun DependencyHandlerScope.implementationPlatform(func: DepsBuilder.() -> Unit) =
    DepsBuilder().apply {
        func()
        list.forEach { dep ->
            dependencies.add("implementation(platform", dep)
        }
    }


fun DependencyHandlerScope.implementCore() {
    implementation {
        +Dependencies.Core.`core-ktx`
        +Dependencies.Core.`lifecycle-runtime-ktx`
        +Dependencies.Core.`activity-compose`
        platform(Dependencies.Core.`compose-bom`)
    }

}

fun DependencyHandlerScope.implementMaterial() {
    implementation {
        +Dependencies.Material.`material3-icons-extended`
    }
}

fun DependencyHandlerScope.implementNavigation() {
    implementation {
        +Dependencies.Navigation.`navigation-compose`
    }
}

fun DependencyHandlerScope.implementLottie() {
    implementation {
        +Dependencies.Lottie.`lottie-compose`
    }
}

fun DependencyHandlerScope.implementKtorClient() {
    implementation {
        +Dependencies.KtorClient.`ktor-client-core`
        +Dependencies.KtorClient.`ktor-client`
        +Dependencies.KtorClient.`ktor-client-android`
        +Dependencies.KtorClient.`ktor-client-cio`
        +Dependencies.KtorClient.`ktor-client-serialization`
        +Dependencies.KtorClient.`ktor-client-logging`
        +Dependencies.KtorClient.`ktor-client-jvm`
        +Dependencies.KtorClient.`ktor-client-content-negotiation`
        +Dependencies.KtorClient.`ktor-client-serialization-gson`
        +Dependencies.KtorClient.`ktor-serialization-kotlinx-json`
        +Dependencies.KtorClient.`ktor-client-logging-jvm`
        +Dependencies.KtorClient.`ktor-client-gson`
    }
}

fun DependencyHandlerScope.implementGson() {
    implementation {
        +Dependencies.Gson.`gson`
    }
}

fun DependencyHandlerScope.implementLogback() {
    implementation {
        +Dependencies.KtorClient.`logback-classic`
    }
}

fun DependencyHandlerScope.implementDataStore() {
    implementation {
        +Dependencies.DataStore.`datastore-preferences`
    }
}

fun DependencyHandlerScope.implementDaggerHilt() {
    implementation {
        +Dependencies.DaggerHilt.`hilt-android`
        +Dependencies.DaggerHilt.`hilt-navigation-compose`
    }
    kapt {
        +Dependencies.DaggerHilt.`hilt-compiler`
    }
}

fun DependencyHandlerScope.implementRoom() {
    implementation {
        +Dependencies.Room.`room-runtime`
        +Dependencies.Room.`room-ktx`
    }
    kapt {
        +Dependencies.Room.`room-compiler`
    }
}

fun DependencyHandlerScope.implementCoil() {
    implementation {
        +Dependencies.Coil.`coil-compose`
    }
}

fun DependencyHandlerScope.implementCompose() {
    implementation {
        +Dependencies.Compose.`compose-ui`
        +Dependencies.Compose.`compose-ui-graphics`
        +Dependencies.Compose.`compose-ui-tooling-preview`
    }
}

fun DependencyHandlerScope.implementCollapsingToolbar() {
    implementation {
        +Dependencies.CollapsingToolbar.`collapsing-toolbar`
    }
}
