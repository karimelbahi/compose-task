package com.feature.home.data.repo.base

import com.example.common.Resource
import com.example.network.ErrorEntity
import com.example.network.GeneralErrorHandler
import com.example.network.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


open class BaseRepo {
    @Inject
    lateinit var errorHandler: GeneralErrorHandler

    @Inject
    lateinit var networkUtils: NetworkUtils

    protected fun loadFromApi(isLoader: Boolean = false,api: suspend () -> Any) =
        if (networkUtils.isNetworkAvailable()) {
            flow {
                if (isLoader)
                    emit(Resource.loading())
                val result = api.invoke()
                emit(Resource.success(result))
            }.catch { e ->
                e.printStackTrace()
                emit(Resource.error(errorHandler.getErrorMessage(errorHandler.getError(e)), e))
            }.flowOn(Dispatchers.IO)
        } else {
            flow { emit(Resource.error<Nothing>(errorHandler.getErrorMessage(ErrorEntity.Network))) }
                .flowOn(Dispatchers.IO)
        }
}