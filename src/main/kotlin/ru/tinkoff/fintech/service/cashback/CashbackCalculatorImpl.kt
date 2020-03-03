package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo
import kotlin.math.min

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
        var cashback = transactionInfo.cashbackTotalValue

        for (cashbackRule in cashbackRules)
            cashback += cashbackRule.calculateCashback(transactionInfo)

        return min(MAX_CASH_BACK, cashback)
    }


}