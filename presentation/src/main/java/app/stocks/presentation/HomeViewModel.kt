package app.stocks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.stocks.data.dto.localDto.CompanyOverviewEntity
import app.stocks.domain.AppRepository
import app.stocks.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val repository: AppRepository,
): AndroidViewModel(application) {

    private val _response = MutableStateFlow<Resource<List<CompanyOverviewEntity>>?>(null)
    val response = _response.asStateFlow()

    fun getCompanyOverview() {
        viewModelScope.launch {
            repository.getCompanyOverview("HOFVW").collectLatest {
                _response.value = it
            }
            println("Company Overview: ${_response.value?.message}")
        }
    }

}