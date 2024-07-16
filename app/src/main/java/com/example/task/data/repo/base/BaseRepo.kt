package com.example.task.data.repo.base


import com.example.task.data.api.utils.ErrorEntity
import com.example.task.data.api.utils.GeneralErrorHandler
import com.example.task.presentation.utils.NetworkUtils
import com.example.task.presentation.utils.Resource
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