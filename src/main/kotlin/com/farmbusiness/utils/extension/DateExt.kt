package com.farmbusiness.utils.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author @jeancsanchez
 * @created 24/12/2023
 * Jesus loves you.
 */

private const val BR_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss"

fun Date.toBRFormat(): String =
    SimpleDateFormat(BR_DATE_PATTERN).format(this)

fun Date.set(
    year: Int? = null,
    month: Int? = null,
    day: Int? = null,
    hour: Int? = null,
    minute: Int? = null,
    second: Int? = null
): Date {
    val today = Date()

    return Calendar.getInstance().run {
        set(Calendar.YEAR, year ?: today.year())
        set(Calendar.MONTH, month?.minus(1) ?: today.month())
        set(Calendar.DAY_OF_MONTH, day ?: today.day())
        set(Calendar.HOUR_OF_DAY, hour ?: today.hour())
        set(Calendar.MINUTE, minute ?: today.minute())
        set(Calendar.SECOND, second ?: today.second())
        this.time
    }
}

fun Date.add(field: Int, value: Int): Date {
    val curr = this
    return Calendar.getInstance().run {
        time = curr
        add(field, value)
        time
    }
}

fun Date.isSameDay(other: Date, includeTime: Boolean = false): Boolean {
    val curr = Calendar.getInstance().also { it.time = this }
    val comparable = Calendar.getInstance().also { it.time = other }

    return if (
        curr.day() == comparable.day()
        && curr.month() == comparable.month()
        && curr.year() == comparable.year()
    ) {
        if (includeTime) {
            curr.hour() == comparable.hour() && curr.minute() == comparable.minute()
        } else {
            true
        }
    } else {
        false
    }
}

fun Date.day(): Int {
    return Calendar.getInstance()
        .also { it.time = this }
        .day()
}

fun Date.month(): Int {
    return Calendar.getInstance()
        .also { it.time = this }
        .month()
}

fun Date.year(): Int {
    return Calendar.getInstance()
        .also { it.time = this }
        .year()
}

fun Date.hour(): Int {
    return Calendar.getInstance()
        .also { it.time = this }
        .hour()
}

fun Date.minute(): Int {
    return Calendar.getInstance()
        .also { it.time = this }
        .minute()
}

fun Date.second(): Int {
    return Calendar.getInstance()
        .also { it.time = this }
        .second()
}

fun Calendar.day(): Int {
    return get(Calendar.DAY_OF_MONTH)
}

fun Calendar.month(): Int {
    return get(Calendar.MONTH)
}

fun Calendar.year(): Int {
    return get(Calendar.YEAR)
}

fun Calendar.hour(): Int {
    return get(Calendar.HOUR_OF_DAY)
}

fun Calendar.minute(): Int {
    return get(Calendar.MINUTE)
}

fun Calendar.second(): Int {
    return get(Calendar.SECOND)
}