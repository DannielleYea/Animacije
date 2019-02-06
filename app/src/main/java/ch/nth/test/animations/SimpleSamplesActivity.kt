package ch.nth.test.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_simple_samples.*


class SimpleSamplesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_samples)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val animation = resources.getDrawable(R.drawable.hamburger_animated_vector) as AnimatedVectorDrawable
        supportActionBar?.setHomeAsUpIndicator(animation)
        animation.start()

        slide.setOnClickListener {
            ObjectAnimator.ofFloat(it, View.TRANSLATION_X, Math.abs(slide.x - 200f)).apply {
                duration = 1000
                start()
            }

            val animation: AnimatedVectorDrawable
            if (animatedHamburger.isEnabled) {
                animation = resources.getDrawable(R.drawable.hamburger_animated_vector) as AnimatedVectorDrawable
                animatedHamburger.isEnabled = false
            } else {
                animation = resources.getDrawable(R.drawable.arow_animated_vector) as AnimatedVectorDrawable
                animatedHamburger.isEnabled = true
            }

            animatedHamburger.setImageDrawable(animation)
            animation.start()
        }

        var currentlySelectedButton: Button? = null

        firstChoice.setOnClickListener {

            currentlySelectedButton?.animate()?.apply {
                scaleX(1f)
                scaleY(1f)
            }?.withStartAction {
                enterUnselectedState(currentlySelectedButton!!)
            }?.start()

            it.animate().apply {
                scaleX(1.25f)
                scaleY(1.25f)
            }.withEndAction {
                val animatorSet = AnimatorSet()

                animatorSet
                        .play(ObjectAnimator.ofArgb(it, "backgroundColor", Color.parseColor("#45C1F1")))
                        .after(ObjectAnimator.ofArgb(it, "textColor", Color.parseColor("#FFFFFF")))

                animatorSet.start()
                currentlySelectedButton = it as Button
            }?.start()
        }

        secondChoice.setOnClickListener {

            currentlySelectedButton?.animate()?.apply {
                scaleX(1f)
                scaleY(1f)
            }?.withStartAction {
                enterUnselectedState(currentlySelectedButton!!)
            }?.start()

            it.animate().apply {
                scaleX(1.25f)
                scaleY(1.25f)
            }.withEndAction {
                val animatorSet = AnimatorSet()

                animatorSet
                        .play(ObjectAnimator.ofArgb(it, "backgroundColor", Color.parseColor("#FB34E3")))
                        .after(ObjectAnimator.ofArgb(it, "textColor", Color.parseColor("#FFFFFF")))

                animatorSet.start()
                currentlySelectedButton = it as Button
            }?.start()
        }

        slideMultiple.setOnClickListener {
            val animatorSet = AnimatorSet()

            val animationScaleDownX = ObjectAnimator.ofFloat(it, View.SCALE_X, .5f)
            val animationScaleDownY = ObjectAnimator.ofFloat(it, View.SCALE_Y, .5f)
            val animationMoveX = ObjectAnimator.ofFloat(it, View.TRANSLATION_X, Math.abs(it.x - 200f))
            val animationScaleUpX = ObjectAnimator.ofFloat(it, View.SCALE_X, 1f)
            val animationScaleUpY = ObjectAnimator.ofFloat(it, View.SCALE_Y, 1f)

            val animatorSetScaleDown = AnimatorSet()
            animatorSetScaleDown.play(animationScaleDownX).with(animationScaleDownY)

            val animatorSetScaleUp = AnimatorSet()
            animatorSetScaleUp.play(animationScaleUpX).with(animationScaleUpY)

            val color = if (Math.abs(it.x - 200) == 0f) {
                Color.parseColor("#FE2E64")
            } else {
                Color.parseColor("#59af34")
            }

            animatorSet.play(animationMoveX).before(animatorSetScaleUp).after(animatorSetScaleDown).with(
                    ObjectAnimator.ofArgb(it, "backgroundColor", color)
            )

            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    if (slideMultiple.x == 200f) {
                        slideMultiple.text = "ON"
                    } else {
                        slideMultiple.text = "OFF"
                    }
                    slideMultiple.isClickable = true
                }

                override fun onAnimationCancel(animation: Animator?) {}

                override fun onAnimationStart(animation: Animator?) {
                    slideMultiple.text = ""
                    slideMultiple.isClickable = false
                }

            })

            animatorSet.duration = 150
            animatorSet.start()
        }

        loadingButton.setOnClickListener {

            animatedDrawable.setBackgroundResource(R.drawable.circle)
            val animation = animatedDrawable.background as AnimationDrawable

            if (loadingIndicator.visibility == View.VISIBLE) {
                loadingIndicator.visibility = View.INVISIBLE
                animation.stop()
                animatedDrawable.visibility = View.INVISIBLE
            } else {
                loadingIndicator.visibility = View.VISIBLE
                animatedDrawable.visibility = View.VISIBLE

                animation.isOneShot = false
                animation.start()
            }

        }

        ring.setOnClickListener {

            ValueAnimator.ofFloat(0f, 100f).apply {
                addUpdateListener { value -> ring.setProgress(value.animatedValue as Float / 100) }
                duration = 2500
            }.start()

        }

        checkboxVisibility.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ObjectAnimator.ofFloat(buttonVisibility, View.ALPHA, 0f, 1f).setDuration(500).start()
                buttonVisibility.isClickable = true
            } else {
                ObjectAnimator.ofFloat(buttonVisibility, View.ALPHA, 1f, 0f).setDuration(300).start()
                buttonVisibility.isClickable = false
            }
        }

        rotateButton.setOnClickListener {
            it.animate().apply {
                rotation(Math.abs(it.rotation - 360f))
                duration = 500
                start()
                it.isClickable = false

                setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        if (it.rotation == 0f) {
                            (it as Button).text = "Like \uD83D\uDC4D"
                        } else {
                            (it as Button).text = "Unlike ❌"
                        }

                        it.isClickable = true
                    }

                    override fun onAnimationCancel(animation: Animator?) {}

                    override fun onAnimationStart(animation: Animator?) {
                    }
                })
            }
        }


        var like = false
        rotateYButton.setOnClickListener {
            it.animate().apply {

                if (like) {
                    rotationY(-180f)
                } else {
                    rotationY(180f)
                }

                like = !like

                duration = 500
                it.isClickable = false

                setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        if (!like) {
                            (it as Button).text = "Like \uD83D\uDC4D"
                            it.rotationY = 0f
                        } else {
                            (it as Button).rotationY = 0f
                            it.text = "Unlike ❌"
                        }

                        it.isClickable = true
                    }

                    override fun onAnimationCancel(animation: Animator?) {}

                    override fun onAnimationStart(animation: Animator?) {
                        (it as Button).text = ""
                    }
                })
            }.start()
        }
    }

    private fun enterUnselectedState(currentlySelectedButton: Button) {
        val animatorSet = AnimatorSet()
        animatorSet
                .play(ObjectAnimator.ofArgb(currentlySelectedButton, "textColor", Color.parseColor("#000000")))
                .after(ObjectAnimator.ofArgb(currentlySelectedButton, "backgroundColor", Color.parseColor("#ffffff")))

        animatorSet.start()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
