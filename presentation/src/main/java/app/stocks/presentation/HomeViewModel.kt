package app.stocks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.stocks.data.remote.RemoteStocksRepository
import app.stocks.data.remote.dto.overview.CompanyOverviewResponse
import app.stocks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val repository: RemoteStocksRepository,
): AndroidViewModel(application) {

    private val _response = MutableStateFlow<Resource<CompanyOverviewResponse>?>(null)
    val response = _response.asStateFlow()

    fun getCompanyOverview() {
        viewModelScope.launch {
            _response.value = repository.getCompanyInfo("HOFVW")
            println("Company Overview: ${_response.value?.message}")
        }
    }

}