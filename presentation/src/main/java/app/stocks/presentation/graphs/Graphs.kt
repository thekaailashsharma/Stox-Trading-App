package app.stocks.presentation.graphs

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import app.stocks.utils.round
import app.stocks.utils.trimrs
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.column.columnChart
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollSpec
import com.patrykandpatrick.vico.compose.component.shape.shader.BrushShader
import com.patrykandpatrick.vico.core.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.chart.line.LineChart.LineSpec
import com.patrykandpatrick.vico.core.component.shape.LineComponent
import com.patrykandpatrick.vico.core.dimensions.MutableDimensions
import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer

@Composable
fun Graphs(
    xAxisLabels: List<String>,
    lineModelProducer: ChartEntryModelProducer,
    barModelProducer: ChartEntryModelProducer,
    graphColor: Color = MaterialTheme.colorScheme.primary,
    showGraphAnimation: Boolean,
    title: String = "Expenses",
    changeGraphAnimation: (Boolean) -> Unit,
) {
    val context = LocalContext.current
    val lineChart = rememberSaveable { mutableStateOf(true) }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                modifier = Modifier.padding(
                    start = 12.dp,
                    end = 12.dp,
                    top = 8.dp,
                    bottom = 4.dp
                ),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.weight(1f))
            IconButton(onClick = {
                lineChart.value = !lineChart.value
                changeGraphAnimation(true)
            }, content = {
                Icon(
                    imageVector = if (lineChart.value) {
                        Icons.Default.BarChart
                    } else {
                        Icons.Default.Timeline
                    },
                    contentDescription = "Toggle Chart",
                    modifier = Modifier
                        .size(22.dp)
                        .align(Alignment.CenterVertically)
                )
            })
        }
        Spacer(modifier = Modifier.height(12.dp))
        if (lineChart.value) {
            LineChart(
                xAxisLabels,
                lineModelProducer,
                graphColor,
                showGraphAnimation,
                changeGraphAnimation
            )
        } else {
            BarChart(
                xAxisLabels,
                barModelProducer,
                graphColor,
                showGraphAnimation,
                changeGraphAnimation
            )
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun LineChart(
    labels: List<String>,
    modelProducer: ChartEntryModelProducer,
    graphColor: Color,
    showGraphAnimation: Boolean,
    changeGraphAnimation: (Boolean) -> Unit
) {
    val spacing = labels.size / 5
    Chart(
        chart = lineChart(
            lines = listOf(
                LineSpec(
                    lineColor = graphColor.toArgb(),
                    lineBackgroundShader = BrushShader(
                        brush = Brush.verticalGradient(
                            listOf(
                                graphColor.copy(alpha = 0.2f),
                                Color.Transparent
                            )
                        ),
                    )
                )
            ),
            axisValuesOverrider = com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider.fixed(
                maxY = modelProducer.getModel()?.maxY?.toDouble()?.round()?.toFloat(),
            ),
        ),
        chartModelProducer = modelProducer,
        bottomAxis = rememberBottomAxis(
            valueFormatter = rememberLabelFormatter(labels),
            itemPlacer = AxisItemPlacer.Horizontal.default(
                spacing = if (spacing <= 1) 1 else spacing,
                offset = if (spacing == 0) 0 else (labels.size - 1) % spacing,
                shiftExtremeTicks = false,
                addExtremeLabelPadding = true
            ),
            guideline = null
        ),
        startAxis = rememberStartAxis(
            valueFormatter = { value, _ ->
                value.toInt().trimrs
            },
            itemPlacer = AxisItemPlacer.Vertical.default(
                maxItemCount = 6
            ),
            guideline = null
        ),
        chartScrollSpec = rememberChartScrollSpec(
            isScrollEnabled = false
        ),
        horizontalLayout = HorizontalLayout.fullWidth(),
        marker = rememberMarker(labels),
        runInitialAnimation = showGraphAnimation,
        modifier = Modifier
            .aspectRatio(1.5f),
    )
    changeGraphAnimation(false)
}

@Composable
fun BarChart(
    labels: List<String>,
    modelProducer: ChartEntryModelProducer,
    graphColor: Color,
    showGraphAnimation: Boolean,
    changeGraphAnimation: (Boolean) -> Unit
) {
    val spacing = labels.size / 5
    Chart(
        chart = columnChart(
            columns = listOf(
                LineComponent(
                    color = graphColor.toArgb(),
                    thicknessDp = 100f,
                    margins = MutableDimensions(horizontalDp = 0.5f, verticalDp = 0f),
                )
            ),
            axisValuesOverrider = com.patrykandpatrick.vico.core.chart.values.AxisValuesOverrider.fixed(
                maxY = modelProducer.getModel()?.maxY?.toDouble()?.round()?.toFloat(),
            ),
            spacing = 0.5.dp
        ),
        chartModelProducer = modelProducer,
        bottomAxis = rememberBottomAxis(
            valueFormatter = rememberLabelFormatter(labels),
            itemPlacer = AxisItemPlacer.Horizontal.default(
                spacing = if (spacing <= 1) 1 else spacing,
                offset = if (spacing == 0) 0 else (labels.size - 1) % spacing,
                shiftExtremeTicks = false,
                addExtremeLabelPadding = true
            ),
            guideline = null
        ),
        startAxis = rememberStartAxis(
            valueFormatter = { value, _ ->
                value.toInt().trimrs
            },
            itemPlacer = AxisItemPlacer.Vertical.default(
                maxItemCount = 6
            ),
            guideline = null
        ),
        chartScrollSpec = rememberChartScrollSpec(
            isScrollEnabled = false
        ),
        horizontalLayout = HorizontalLayout.fullWidth(),
        marker = rememberMarker(labels),
        runInitialAnimation = showGraphAnimation,
        modifier = Modifier
            .aspectRatio(1.5f),
    )
    changeGraphAnimation(false)
}

@Composable
fun rememberLabelFormatter(labels: List<String>) = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _ ->
    labels[value.toInt()]
}