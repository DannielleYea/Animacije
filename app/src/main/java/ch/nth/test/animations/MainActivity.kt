package ch.nth.test.animations

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import ch.nth.test.animations.activitytransitions.AnimatedListView
import ch.nth.test.animations.activitytransitions.NonAnimatedListView
import ch.nth.test.animations.layouttransitions.TransitionListActivity
import ch.nth.test.animations.sceneAnimations.SceneAnimationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)

        button.setOnClickListener {
            //startActivity()
            startActivity(Intent(this, AnimatedListView::class.java))
        }

        buttonNon.setOnClickListener {
            startActivity(Intent(this, NonAnimatedListView::class.java))
            //startActivity(Intent(this, DetailsActivity::class.java))
        }

        simpleSample.setOnClickListener {
            startActivity(Intent(this, SimpleSamplesActivity::class.java))
            //startActivity(Intent(this, DetailsActivity::class.java))
        }

        hello.setOnClickListener {
            startActivity(Intent(this, AnimatedListView::class.java))
        }

        transitionAnim.setOnClickListener {
            val intent = Intent(this, TransitionListActivity::class.java)

            intent.putExtra(TransitionListActivity.ANIMATIONS_ENABLED_EXTRA, true)
            startActivity(intent)
        }

        transitionNonAnim.setOnClickListener {
            val intent = Intent(this, TransitionListActivity::class.java)

            intent.putExtra(TransitionListActivity.ANIMATIONS_ENABLED_EXTRA, false)
            startActivity(intent)
        }

        scenesAnim.setOnClickListener {
            startActivity(Intent(this, SceneAnimationActivity::class.java))
        }
    }

    private fun startActivity() {

        val intent = Intent(this, DetailsActivity::class.java)

        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(findViewById(R.id.hello), DetailsActivity.textView),
                Pair(findViewById(R.id.button), DetailsActivity.button)
        )
        ActivityCompat.startActivity(this, intent, activityOptions.toBundle())

    }
}
