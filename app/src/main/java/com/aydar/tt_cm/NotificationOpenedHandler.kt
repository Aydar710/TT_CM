package com.aydar.tt_cm

import android.content.Context
import android.content.Intent
import com.aydar.tt_cm.featurepersons.presentation.PersonsActivity
import com.onesignal.OSNotificationOpenResult
import com.onesignal.OneSignal


class NotificationOpenedHandler(private val context: Context) :
    OneSignal.NotificationOpenedHandler {

    override fun notificationOpened(result: OSNotificationOpenResult?) {
        val data = result?.notification?.payload?.additionalData
        val action = data?.optString("action")

        action?.let {
            if (it == ACTION_SEARCH_FOR_PARTNER) {
                val intent = Intent(
                    context,
                    PersonsActivity::class.java
                )
                intent.flags =
                    Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }
}