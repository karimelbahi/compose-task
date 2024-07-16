package com.example.task.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun String?.convertNullToEmpty(): String {
    return this?.replace("null","") ?: ""
}


fun Context.openYoutubeUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        // If the user doesn't have the YouTube app installed, open the URL in a web browser instead
        intent.setPackage(null)
        startActivity(Intent.createChooser(intent, "Open with"))
    }
}

fun String.splitStringToList(splitSign :String): List<String> {
    return this.split(splitSign).map { it.trim() }
}