package app.stocks.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.stocks.data.dto.localDto.CompanyOverviewEntity
import app.stocks.data.dto.localDto.IntraDayEntity
import app.stocks.data.dto.localDto.TopPerformersWithRelations
import app.stocks.data.dto.remoteDto.intraday.IntraDayInfo
import app.stocks.data.dto.remoteDto.intraday.IntradayResponse
import app.stocks.data.dto.remoteDto.intraday.TimeSeries60min
import app.stocks.data.dto.remoteDto.intraday.X20240626130000
import app.stocks.domain.AppRepository
import app.stocks.utils.Resource
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryOf
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

    private val _response = MutableStateFlow<Resource<TopPerformersWithRelations>?>(null)
    val response = _response.asStateFlow()

    private val _response1 = MutableStateFlow<Resource<List<IntraDayInfo>>?>(null)
    val response1 = _response1.asStateFlow()

    fun getCompanyOverview() {
        viewModelScope.launch {
//            repository.getTopPerformers().collectLatest {
//                _response.value = it
//            }
            _response1.value = repository.getIntraDayInfo("HOFVW")
            println("Company Overview: ${_response.value?.message}")
        }
    }

}




