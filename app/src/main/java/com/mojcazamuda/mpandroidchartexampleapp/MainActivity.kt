package com.mojcazamuda.mpandroidchartexampleapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*

class MainActivity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var input3: EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        lineChart = findViewById(R.id.lineChart)
        barChart = findViewById(R.id.barChart)
        pieChart = findViewById(R.id.pieChart)
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        input3 = findViewById(R.id.input3)

        lineChart.description.text = "Line chart showing values over time."
        lineChart.legend.isEnabled=false
        lineChart.description.textSize=13f


        barChart.description.text = "Bar chart comparing three values."
        barChart.legend.isEnabled = false
        barChart.description.textSize=13f




        pieChart.setDrawEntryLabels(false)
        pieChart.legend.isEnabled = true
        pieChart.description.text = "Pie chart distribution of three values."
        pieChart.description.textSize=13f


        /*lineChart.viewTreeObserver.addOnGlobalLayoutListener {
            lineChart.description.setPosition(lineChart.width.toFloat(), lineChart.height.toFloat())
        }

        barChart.viewTreeObserver.addOnGlobalLayoutListener {
            barChart.description.setPosition(barChart.width.toFloat(), barChart.height.toFloat())
        }*/
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                updateCharts(
                    input1.text.toString().toFloatOrNull() ?: 0f,
                    input2.text.toString().toFloatOrNull() ?: 0f,
                    input3.text.toString().toFloatOrNull() ?: 0f
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        input1.addTextChangedListener(textWatcher)
        input2.addTextChangedListener(textWatcher)
        input3.addTextChangedListener(textWatcher)

        updateCharts(0f, 0f, 0f)
    }

    private fun updateCharts(value1: Float, value2: Float, value3: Float) {
        val lineEntries = listOf(
            Entry(0f, value1),
            Entry(1f, value2),
            Entry(2f, value3)
        )
        val lineDataSet = LineDataSet(lineEntries, "Line Chart")
        lineDataSet.color = Color.BLUE
        lineDataSet.valueTextColor = Color.BLACK
        lineDataSet.valueTextSize = 10f
        lineChart.data = LineData(lineDataSet)
        lineChart.invalidate()

        val barEntries = listOf(
            BarEntry(0f, value1),
            BarEntry(1f, value2),
            BarEntry(2f, value3)
        )
        val barDataSet = BarDataSet(barEntries, "Bar Chart")
        barDataSet.colors = listOf(Color.BLUE, Color.GREEN, Color.RED)
        barDataSet.valueTextColor = Color.BLACK
        barDataSet.valueTextSize =10f
        barChart.data = BarData(barDataSet)
        barChart.invalidate()

        val pieEntries = listOf(
            PieEntry(value1, "Value 1"),
            PieEntry(value2, "Value 2"),
            PieEntry(value3, "Value 3")
        )
        val pieDataSet = PieDataSet(pieEntries, "")
        pieDataSet.colors = listOf(Color.MAGENTA, Color.CYAN, Color.YELLOW)
        pieChart.legend.form = Legend.LegendForm.CIRCLE
        pieDataSet.valueTextColor = Color.BLACK
        pieDataSet.valueTextSize =10f
        pieChart.data = PieData(pieDataSet)
        pieChart.invalidate()
    }
}
