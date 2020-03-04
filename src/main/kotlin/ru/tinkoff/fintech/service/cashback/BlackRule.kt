package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo

class BlackRule : CashbackRule() {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double =
        if (transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_BLACK)
            transactionInfo.transactionSum * 0.01
        else
            0.0
}