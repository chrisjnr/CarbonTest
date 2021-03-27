package com.loveworldapps.data.di

import com.loveworldapps.data.UserRepositoryImpl
import org.koin.dsl.module

/**
 * Created by manuelchris-ogar on 27/03/2021.
 */
val allRepositoryImplViewModules = module{
    single { UserRepositoryImpl(get(),get(), get()) }

}