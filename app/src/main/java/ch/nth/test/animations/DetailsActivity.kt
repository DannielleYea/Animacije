package ch.nth.test.animations

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateOvershootInterpolator
import kotlinx.android.synthetic.main.activity_details.*


class DetailsActivity : AppCompatActivity() {

    companion object {

        const val button = "button"
        const val textView = "textView"
    }

    var iconRotated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ViewCompat.setTransitionName(secoundActivityButton, button)
        ViewCompat.setTransitionName(secoundActivityTextView, textView)

        secoundActivityButton.setOnClickListener {
            onBackPressed()
        }

        icon.setOnClickListener {
            icon.rotation = if (iconRotated) 45f else 0f

            iconRotated = !iconRotated

            val changeBounds = ChangeBounds().apply {
                addTarget(icon)
                interpolator = AnticipateOvershootInterpolator()
                duration = 1000
            }

            TransitionManager.beginDelayedTransition(findViewById(android.R.id.content), changeBounds)

            val iconLayoutParams = icon.layoutParams
            iconLayoutParams.width = (iconLayoutParams.width * if (iconRotated) 2f else 0.5f).toInt()
            iconLayoutParams.height = (iconLayoutParams.height * if (iconRotated) 2f else 0.5f).toInt()

            icon.layoutParams = iconLayoutParams
        }

//        loadingIndicator.setOnClickListener {
//            loadingIndicator.stopAnimation()
//        }
    }
}
