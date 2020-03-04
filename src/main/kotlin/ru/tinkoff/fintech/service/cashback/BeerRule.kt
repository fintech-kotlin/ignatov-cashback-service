package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo
import java.time.LocalDate

class BeerRule : CashbackRule() {
    companion object {
        private const val SPECIAL_FIRST_NAME = "олег"
        private const val SPECIAL_LAST_NAME = "олегов"
    }
    override fun calculateCashback(transactionInfo: TransactionInfo): Double =
        if (transactionInfo.loyaltyProgramName != LOYALTY_PROGRAM_BEER ||
                transactionInfo.mccCode != MCC_BEER)  0.0
        else
            transactionInfo.transactionSum * calcPercent(transactionInfo)

    private fun calcPercent(transactionInfo: TransactionInfo) : Double {
        return when {
            transactionInfo.firstName.toLowerCase() == SPECIAL_FIRST_NAME &&
                    transactionInfo.lastName.toLowerCase() == SPECIAL_LAST_NAME ->  0.1

            transactionInfo.firstName.toLowerCase() == SPECIAL_FIRST_NAME ->  0.07

            transactionInfo.firstName.toLowerCase()[0] == getFirstCharOfMonth(LocalDate.now().monthValue) -> 0.05

            transactionInfo.firstName.toLowerCase()[0] ==
                    getFirstCharOfMonth(LocalDate.now().minusMonths(1).monthValue) ||
                    transactionInfo.firstName.toLowerCase()[0] ==
                    getFirstCharOfMonth(LocalDate.now().plusMonths(1).monthValue) -> 0.03

            else -> 0.02
        }

    }

    private fun getFirstCharOfMonth(month : Int) : Char =
        when (month) {
            1 -> 'я'
            2 -> 'ф'
            3, 5 -> 'м'
            4, 8 -> 'а'
            6, 7 -> 'и'
            9 -> 'с'
            10 -> 'о'
            11 -> 'н'
            else -> 'д'
        }
}