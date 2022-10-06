package com.davimaia.appextensions

/*
This function is a tool to deal with any element in your activity when you activate or deactivate the keyboard.
Like hide the navbar when the keyboard is active and show when you deactivate it
 */

fun Activity.keyboardListener(isOpen: (isOpen: Boolean) -> Unit) {
    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets ->
        val isKeyboardVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
        isOpen(isKeyboardVisible)
        insets
    }
}

/*
These three deal with view mode.
 */

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}


