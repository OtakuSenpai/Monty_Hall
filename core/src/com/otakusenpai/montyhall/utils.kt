package com.otakusenpai.montyhall

import java.util.*

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) +  start

enum class State(state: Int) {
    Start(0),
    Confirm(1),
    End(2)
}