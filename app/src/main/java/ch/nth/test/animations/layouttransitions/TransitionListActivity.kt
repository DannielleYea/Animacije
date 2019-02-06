package ch.nth.test.animations.layouttransitions

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.transition.Scene
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import ch.nth.test.animations.R
import kotlinx.android.synthetic.main.activity_transition_list.*


class TransitionListActivity : AppCompatActivity(), TransitionListAdapter.OnItemSelectedListener {

    companion object {
        const val ANIMATIONS_ENABLED_EXTRA = "animations_enabled_extra"
    }

    private lateinit var view: View

    private var animationsEnabled = false
    private lateinit var mSceneRoot: ViewGroup
    private lateinit var scene: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        animationsEnabled = intent.getBooleanExtra(ANIMATIONS_ENABLED_EXTRA, false)

        mSceneRoot = findViewById(R.id.layout_holder)

        scene = Scene.getSceneForLayout(mSceneRoot, R.layout.list_item_square, this)

        list.adapter = TransitionListAdapter(this)
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun itemSelected(itemView: View) {
        if (::view.isInitialized) {
            content.removeView(view)
        }

        view = itemView

        if (!animationsEnabled) {
            (view.parent as ViewGroup).removeView(view)
            nonAnimationTransition(itemView)

            return
        }

        val location = IntArray(2)
        view.getLocationOnScreen(location)

        val holderPosition = IntArray(2)
        layout_holder.getLocationInWindow(holderPosition)

        view.x = location[0].toFloat()
        view.y = location[1].toFloat()

        val scaledWidth = (layout_holder.width - (layout_holder.paddingStart + layout_holder.paddingEnd.toFloat())) / itemView.width.toFloat()
        val scaledHeight = (layout_holder.height - (layout_holder.paddingTop + layout_holder.paddingBottom.toFloat())) / itemView.height.toFloat()

        (view.parent as ViewGroup).removeView(view)
        content.addView(view)

        val animationX = PropertyValuesHolder.ofFloat(View.X, holderPosition[0].toFloat() + ((layout_holder.width - view.width) / 2))
        val animationY = PropertyValuesHolder.ofFloat(View.Y, holderPosition[1].toFloat() + ((layout_holder.height - view.height) / 4))
        val animationScaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, scaledWidth)
        val animationScaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, scaledHeight)

        ObjectAnimator.ofPropertyValuesHolder(view, animationX, animationScaleX, animationY, animationScaleY).apply {
            duration = 350
        }.start()

    }

    private fun nonAnimationTransition(itemView: View) {
        val scaledWidth = (layout_holder.width - (layout_holder.paddingStart + layout_holder.paddingEnd.toFloat())) / itemView.width.toFloat()
        val scaledHeight = (layout_holder.height - (layout_holder.paddingTop + layout_holder.paddingBottom.toFloat())) / itemView.height.toFloat()

        itemView.scaleX = scaledWidth
        itemView.scaleY = scaledHeight

        (layout_holder as ViewGroup).removeAllViews()
        (layout_holder as ViewGroup).addView(itemView)

        val params = itemView.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.CENTER

        itemView.layoutParams = params

        layout_holder.invalidate()
    }
}
