package app.stocks.utils

import android.icu.text.NumberFormat
import android.os.Build
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.log10

fun Double.round(): Int {
    if (this < 0) {
        throw IllegalArgumentException("Input must be a non-negative integer.")
    }
    val exponent = log10(this).toInt()
    val coefficient = this / pow(10, exponent)
    return if (coefficient % 1 <= 0.25) {
        ((coefficient.toInt() + 0.25) * pow(10, exponent)).toInt()
    } else if (coefficient % 1 <= 0.5) {
        ((coefficient.toInt() + 0.5) * pow(10, exponent)).toInt()
    } else if (coefficient % 1 <= 0.75) {
        ((coefficient.toInt() + 0.75) * pow(10, exponent)).toInt()
    } else {
        ceil(coefficient).toInt() * pow(10, exponent)
    }
}

fun pow(b: Int, k: Int): Int {
    var b = b
    var k = k
    when (b) {
        0 -> return if (k == 0) 1 else 0
        1 -> return 1
        -1 -> return if (k and 1 == 0) 1 else -1
        2 -> return if (k < Integer.SIZE) 1 shl k else 0
        -2 -> return if (k < Integer.SIZE) {
            if (k and 1 == 0) 1 shl k else -(1 shl k)
        } else {
            0
        }

        else -> {}
    }
    var accum = 1
    while (true) {
        when (k) {
            0 -> return accum
            1 -> return b * accum
            else -> {
                accum *= if (k and 1 == 0) 1 else b
                b *= b
            }
        }
        k = k shr 1
    }
}

fun checkNonNegative(role: String, x: Int): Int {
    require(x >= 0) { "$role ($x) must be >= 0" }
    return x
}

fun format(double: Double): String {
    val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        NumberFormat.getCurrencyInstance(Locale("en", "in"))
            .apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 0
            }
    } else {
        java.text.NumberFormat.getCurrencyInstance(Locale("en", "in"))
            .apply {
                maximumFractionDigits = 2
                minimumFractionDigits = 0
            }
    }
    return formatter.format(double)
}