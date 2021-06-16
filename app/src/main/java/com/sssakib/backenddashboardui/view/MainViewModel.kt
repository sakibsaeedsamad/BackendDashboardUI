package com.sssakib.backenddashboardui.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sssakib.backenddashboardui.model.Dashboard
import com.sssakib.backenddashboardui.data.Repository
import com.sssakib.backenddashboardui.data.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _dashboardItems = MutableLiveData<Result<List<Dashboard.Item>>>()
    val dashboardItems: LiveData<Result<List<Dashboard.Item>>> = _dashboardItems

    fun loadData(showRandom: Boolean) {
        _dashboardItems.postValue(Result.Loading)
        viewModelScope.launch {
            delay(1000) // delay added to slow-down API request
            _dashboardItems.postValue(Repository.getDashboardData(showRandom))
        }
    }
}

