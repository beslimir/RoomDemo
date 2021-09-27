package com.example.roomdemo

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, text: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, text, duration).apply { show() }
}