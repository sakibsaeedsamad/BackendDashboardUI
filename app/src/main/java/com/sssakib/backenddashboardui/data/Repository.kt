package com.sssakib.backenddashboardui.data


import com.sssakib.backenddashboardui.model.Dashboard
import com.sssakib.backenddashboardui.network.NetworkClient
import java.lang.Exception
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

    private val apiService = NetworkClient.service

    suspend fun getDashboardData(isRandomRequired: Boolean = false): Result<List<Dashboard.Item>> {
        return withContext(Dispatchers.IO) {
            try {
                if(isRandomRequired) {
                    Result.Success(apiService.getRandomDashboard().data)
                } else {
                    Result.Success(apiService.getDashboard().data)
                }
            } catch (exception: Exception) {
                Result.Failure(exception)
            }
        }
    }
}
