package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo
import kotlin.math.min
import kotlin.math.roundToInt
import kotlin.math.roundToLong

internal const val LOYALTY_PROGRAM_BLACK = "BLACK"
internal const val LOYALTY_PROGRAM_ALL = "ALL"
internal const val LOYALTY_PROGRAM_BEER = "BEER"
internal const val MAX_CASH_BACK = 3000.0
internal const val MCC_SOFTWARE = 5734
internal const val MCC_BEER = 5921

class CashbackCalculatorImpl : CashbackCalculator {
    private val cashbackRules = mutableListOf(
        BlackRule(),
        DevilRule(),
        AllRule(),
        BeerRule()
    )

    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        var cashback = 0.0

        for (cashbackRule in cashbackRules)
            cashback += cashbackRule.calculateCashback(transactionInfo)

        //фиксим ошибку представления дробных чисел
        cashback = (cashback * 100.0).roundToInt() / 100.0

        return min(MAX_CASH_BACK - transactionInfo.cashbackTotalValue, cashback)
    }


}