package com.droidplusplus.maximesstask.utils

import android.app.Activity
import android.widget.Toast


/**
 * Toast Extension Functions
 * */
fun Activity.toast(
    message: String,
    length: Int = Toast.LENGTH_LONG
) {
    //if (BuildConfig.DEBUG || validOnLiveApp)
    Toast.makeText(this, message, length).show()
}