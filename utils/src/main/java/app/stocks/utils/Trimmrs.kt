package app.stocks.utils

val Int.rs
    get() = "₹$this"

val Double.rs
    get() = format(this)

val Int.trimrs: String
    get() {
        val isNeg = this < 0
        val abs = kotlin.math.abs(this)
        val numberedString = when {
            abs < 1000 -> {
                abs.rs
            }

            abs < 100000 -> {
                (abs / 1000.0).rs + "K"
            }

            abs < 10000000 -> {
                (abs / 100000.0).rs + " lac"
            }

            else -> {
                (abs / 10000000.0).rs + " crore"
            }
        }
        return if (isNeg) {
            "-$numberedString"
        } else {
            numberedString
        }
    }

val Double.trimrs: String
    get() {
        val isNeg = this < 0
        val abs = kotlin.math.abs(this)
        val numberedString = when {
            abs < 1000 -> {
                abs.rs
            }

            abs < 100000 -> {
                (abs / 1000.0).rs + "K"
            }

            abs < 10000000 -> {
                (abs / 100000.0).rs + " lac"
            }

            else -> {
                (abs / 10000000.0).rs + " crore"
            }
        }
        return if (isNeg) {
            "-$numberedString"
        } else {
            numberedString
        }
    }
