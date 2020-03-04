package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo

class BlackRule : CashbackRule() {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_BLACK)
            return transactionInfo.transactionSum * 0.01
        return 0.0
    }
}