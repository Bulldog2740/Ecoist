package com.ecoist.market.di.repository

import androidx.room.Room
import com.ecoist.market.data.roomdb.DataBase
import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.domain.repository.PhotoRepository
import com.ecoist.market.domain.repository.ProductRepository
import com.ecoist.market.domain.repository.TelegramRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

const val DB_NAME="eco_db"

fun dataBaseModule() = module {

    single {
        Room.databaseBuilder(
            androidContext().applicationContext, DataBase::class.java, DB_NAME
        ).build()
    }

    single { get<DataBase>().getProductDao() }
    single { get<DataBase>().getPhotoDao() }
    single { get<DataBase>().getCategoryDao() }

    factory { CategoryRepository(get(),get()) }
    factory { TelegramRepository(get()) }
    factory { PhotoRepository(get(),get()) }
    factory { ProductRepository(get(),get()) }
}
