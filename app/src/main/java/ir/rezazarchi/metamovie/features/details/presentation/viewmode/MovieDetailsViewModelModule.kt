package ir.rezazarchi.metamovie.features.details.presentation.viewmode

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val movieDetailsViewModelModule = module {
    viewModelOf(::MovieDetailsViewModel)
}