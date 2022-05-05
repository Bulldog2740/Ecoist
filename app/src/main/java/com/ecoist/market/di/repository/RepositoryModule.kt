package com.ecoist.market.di.repository

import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.data.roomdb.PhotoRepositoryEco
import com.ecoist.market.data.roomdb.ProductRepositoryEco
import com.ecoist.market.domain.repository.TelegramRepository
import org.koin.dsl.module

fun repositoryModule() = module {
    factory { CategoryRepository(get()) }
    factory { TelegramRepository(get()) }
    factory { CategoryRepository(get()) }
   factory { PhotoRepositoryEco(get()) }
   factory { ProductRepositoryEco(get()) }
}