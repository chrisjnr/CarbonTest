package com.loveworldapps.carbontest.modules

import com.loveworldapps.carbontest.ui.UserViewModel
import org.koin.dsl.module


/**
 * Created by manuelchris-ogar on 27/03/2021.
 */
val  ViewModelModule = module {
    single { UserViewModel(get()) }
}