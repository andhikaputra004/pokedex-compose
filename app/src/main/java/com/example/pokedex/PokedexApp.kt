package com.example.pokedex

import android.app.Application
import com.example.pokedex.module.networkModule
import com.example.pokedex.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokedexApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PokedexApp)
            modules(
                networkModule, viewModelModule
            )
        }
    }
}