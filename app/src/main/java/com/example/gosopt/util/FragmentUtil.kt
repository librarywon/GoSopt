package com.example.gosopt.util

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.snackBar(
    message: CharSequence,
    duration: Int = Snackbar.LENGTH_SHORT,
    view: View = requireView()
) {
    Snackbar.make(view, message, duration).show()
}