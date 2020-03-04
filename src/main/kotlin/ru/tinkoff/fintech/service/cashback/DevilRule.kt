package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo

class DevilRule  : CashbackRule() {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.transactionSum.rem(666) == 0.0) return 6.66
        return 0.0
    }
}