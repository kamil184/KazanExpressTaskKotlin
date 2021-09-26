package com.kamil184.kazanexpresstaskkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils


internal class PlaceholderView : View {
    constructor(context: Context?) : super(context) {
        setBackgroundResource(R.drawable.placeholder_background)
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.placeholder))
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        setBackgroundResource(R.drawable.placeholder_background)
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.placeholder))
    }
}