package ch.nth.test.animations.sceneAnimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Scene
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import ch.nth.test.animations.R
import kotlinx.android.synthetic.main.activity_scene_animation.*

class SceneAnimationActivity : AppCompatActivity() {

    private lateinit var mMenu: Menu

    private lateinit var mSceneRoot: ViewGroup

    private lateinit var mLoginScene: Scene
    private lateinit var mRegisterScene: Scene

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_animation)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mSceneRoot = sceneRoot as ViewGroup

        mLoginScene = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_login, this)
        mRegisterScene = Scene.getSceneForLayout(mSceneRoot, R.layout.scene_register, this)

        TransitionManager.go(mLoginScene)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.login_register_menu, menu)
        mMenu = menu!!

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.login) {
            mMenu.findItem(R.id.register).isVisible = true
            mMenu.findItem(R.id.login).isVisible = false

            TransitionManager.go(mLoginScene)

            return true
        } else if (item?.itemId == R.id.register) {
            mMenu.findItem(R.id.register).isVisible = false
            mMenu.findItem(R.id.login).isVisible = true

            TransitionManager.go(mRegisterScene)
            return true
        } else if (android.R.id.home == item?.itemId) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }
}
