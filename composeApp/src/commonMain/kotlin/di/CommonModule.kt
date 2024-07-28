package di

import data.repo.CategoryMealsMealsRepoImpl
import data.repo.HomeRepoImpl
import domain.repo.CategoryMealsRepo
import domain.repo.HomeRepo
import domain.usecases.CategoryMealsMealsUseCaseImpl
import domain.usecases.HomeUseCaseImpl
import org.koin.core.module.Module
import org.koin.dsl.module
import presentation.category.CategoryMealsUseCase
import presentation.category.CategoryMealsViewModel
import presentation.home.HomeUseCase
import presentation.home.HomeViewModel


fun commonModule() = networkModule() + module {

    factory<CategoryMealsUseCase> { CategoryMealsMealsUseCaseImpl(get()) }
    single<CategoryMealsRepo> { CategoryMealsMealsRepoImpl(httpClient = get()) }

    factory<HomeUseCase> { HomeUseCaseImpl(get()) }
    single<HomeRepo> { HomeRepoImpl(httpClient = get()) }

    single { HomeViewModel(get()) }
    single { CategoryMealsViewModel(get()) }

}

//expect fun platformModule(): Module
