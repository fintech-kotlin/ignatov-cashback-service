package ru.tinkoff.fintech.service.notification

import kotlin.math.min

class CardNumberMaskerImpl: CardNumberMasker {

    override fun mask(cardNumber: String, maskChar: Char, start: Int, end: Int): String {
        require(start <= end) {"Start index cannot be greater than end index"}

        val startPos = min(start, cardNumber.length)
        val endPos = min(end, cardNumber.length)

        return cardNumber.replaceRange(
            startPos,endPos, maskChar.toString().repeat(endPos - startPos)
        )
    }
}