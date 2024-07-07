package app.stocks.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        response.value?.let { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { companyOverview ->
                        Text(
                            text = companyOverview.symbol ?: "Hello, World!",
                            color = Color.White
                        )
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