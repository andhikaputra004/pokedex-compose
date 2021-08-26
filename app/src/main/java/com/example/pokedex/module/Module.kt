package com.example.pokedex.module

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.pokedex.BuildConfig
import com.example.pokedex.data.remote.PokedexRepository
import com.example.pokedex.data.remote.PokedexService
import com.example.pokedex.page.main.MainViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Moshi.Builder().build()
    }

    single {
        OkHttpClient.Builder().apply {
            addInterceptor(ChuckerInterceptor.Builder(androidContext()).build())
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            connectTimeout(6000, TimeUnit.MILLISECONDS)
            readTimeout(0, TimeUnit.MILLISECONDS)
        }.build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .client(get())
            .build()
        retrofit
    }

    single { get<Retrofit>().create(PokedexService::class.java) }
    single { PokedexRepository(get()) }
}


val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}