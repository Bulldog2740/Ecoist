package com.ecoist.market.di.repository

import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.domain.repository.PhotoRepository
import com.ecoist.market.domain.repository.ProductRepository
import com.ecoist.market.domain.repository.TelegramRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    factory { CategoryRepository(get()) }
    factory { TelegramRepository(get()) }
    factory { CategoryRepository(get()) }
   factory { PhotoRepository(get()) }
   factory { ProductRepository(get()) }
}