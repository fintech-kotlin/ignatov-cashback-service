package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo

abstract class CashbackRule() {
    abstract fun calculateCashback(transactionInfo : TransactionInfo) : Double
}