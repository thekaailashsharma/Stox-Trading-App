package app.stocks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.stocks.data.dto.remoteDto.intraday.IntradayResponse
import app.stocks.data.dto.remoteDto.intraday.X20240626130000
import app.stocks.presentation.graphs.Graphs
import app.stocks.presentation.graphs.LineChart
import app.stocks.presentation.graphs.StockChart
import app.stocks.utils.Resource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.getCompanyOverview()
    }

    val response = viewModel.response.collectAsState()
    val response1 = viewModel.response1.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
//        response.value?.let { resource ->
//            when (resource) {
//                is Resource.Success -> {
//                    resource.data?.let { companyOverview ->
//                        Text(
//                            text = companyOverview.toString() ?: "Hello, World!",
//                            color = Color.White
//                        )
//                    }
//                }
//                is Resource.Error -> {
//                    resource.message?.let { message ->
//                        Text(
//                            text = message,
//                            color = Color.White
//                        )
//                    }
//                }
//                is Resource.Loading -> {
//                   LinearProgressIndicator()
//                }
//            }
//        }
        response1.value?.let { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { companyOverview ->
                        println("IntraDay Info is $companyOverview")
                        StockChart(
                            infos = companyOverview,
                            graphColor = Color.Green,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .align(Alignment.CenterHorizontally)
                        )
//                        val charts  = convertIntraDayResponseToChartData(companyOverview)
//                        LineChart(
//                            labels = charts.first,
//                            modelProducer = charts.second,
//                            graphColor = Color.Green,
//                            showGraphAnimation =  true
//                        ) {
//
//                        }
                    }
                }
                is Resource.Error -> {
                    resource.message?.let { message ->
                        Text(
                            text = message,
                            color = Color.White
                        )
                    }
                }
                is Resource.Loading -> {
                    LinearProgressIndicator()
                }
            }
        }
    }
}




