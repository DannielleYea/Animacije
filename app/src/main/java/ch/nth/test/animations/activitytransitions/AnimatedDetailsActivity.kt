package ch.nth.test.animations.activitytransitions

import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ch.nth.test.animations.Data
import ch.nth.test.animations.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_animated_details.*

class AnimatedDetailsActivity : AppCompatActivity() {

    private lateinit var mData: Data

    companion object {

        const val EXTRAS_DATA = "intent_extras_data"
        const val IMAGE_VIEW = "image_view"
        const val HOLDER = "holder"
        const val TEXT_VIEW = "text_view"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_details)

        supportActionBar?.setHomeButtonEnabled(true)

        mData = intent?.extras?.get(EXTRAS_DATA) as Data

        ViewCompat.setTransitionName(detailsImage, IMAGE_VIEW)
        ViewCompat.setTransitionName(detailsHolder, HOLDER)

        setupData()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return false
    }

    private fun setupData() {

        val options = RequestOptions()
        options.centerCrop()

        Glide.with(this).load(mData.path).apply(options).into(detailsImage)

        detailsDescription.text = mData.description
    }

}
