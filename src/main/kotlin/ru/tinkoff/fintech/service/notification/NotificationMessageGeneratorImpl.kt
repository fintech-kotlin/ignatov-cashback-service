package ru.tinkoff.fintech.service.notification

import ru.tinkoff.fintech.model.NotificationMessageInfo
import java.util.*

class NotificationMessageGeneratorImpl(
    private val cardNumberMasker: CardNumberMasker
) : NotificationMessageGenerator {

    override fun generateMessage(notificationMessageInfo: NotificationMessageInfo): String {
        return """Уважаемый, %s!
Спешим Вам сообщить, что на карту %s
начислен cashback в размере %.2f
за категорию %s.
Спасибо за покупку %s""".format(
            Locale.US,
            notificationMessageInfo.name,
            cardNumberMasker.mask(notificationMessageInfo.cardNumber),
            notificationMessageInfo.cashback,
            notificationMessageInfo.category,
            notificationMessageInfo.transactionDate.toString())
    }
}