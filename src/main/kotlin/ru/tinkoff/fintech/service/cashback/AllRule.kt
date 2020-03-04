package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo

class AllRule : CashbackRule() {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.mccCode == 5734 && transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_ALL &&
            isPal(transactionInfo.transactionSum)
        ) {
            return transactionInfo.transactionSum *
                    NOK(
                        transactionInfo.firstName.length,
                        transactionInfo.lastName.length
                    ) / 100_000.0
        }
        return 0.0
    }

    private fun isPal(sum: Double): Boolean {
        val sumStr = (sum * 100).toInt().toString()
        val halfLength = sumStr.length / 2
        val firstHalf = sumStr.substring(0, halfLength)
        val secondHalf = sumStr.reversed().substring(0, halfLength)

        var diff = 0
        for (i in 0 until halfLength) if (firstHalf[i] != secondHalf[i]) diff++
        return diff <= 1
    }

    private fun NOK(a: Int, b: Int): Int =
        a / NOD(a, b) * b

    private tailrec fun NOD(a: Int, b: Int): Int =
        if (b == 0) a else NOD(b, a % b)
}