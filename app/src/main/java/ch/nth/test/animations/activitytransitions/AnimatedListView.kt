package ch.nth.test.animations.activitytransitions

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import ch.nth.test.animations.R
import kotlinx.android.synthetic.main.activity_animated_list_view.*


class AnimatedListView : AppCompatActivity() {

    private lateinit var mMenu: Menu
    private val mAdapter = AnimatedListViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animated_list_view)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)

        menuInflater.inflate(R.menu.edit, menu)
        mMenu = menu!!

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.edit) {

            mAdapter.enableEditMode(true)

            mMenu.findItem(R.id.edit).isVisible = false
            mMenu.findItem(R.id.save).isVisible = true

            return true
        } else if (item?.itemId == R.id.save) {
            mAdapter.enableEditMode(false)

            mMenu.findItem(R.id.edit).isVisible = true
            mMenu.findItem(R.id.save).isVisible = false

            return true
        } else if(item?.itemId == android.R.id.home){
            finish()

            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
