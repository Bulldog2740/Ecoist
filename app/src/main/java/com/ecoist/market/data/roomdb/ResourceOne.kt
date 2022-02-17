package com.ecoist.market.data.roomdb

data class ResourceOne<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): ResourceOne<T> {
            return ResourceOne(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: Throwable, data: T?): ResourceOne<T> {
            return ResourceOne(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): ResourceOne<T> {
            return ResourceOne(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}