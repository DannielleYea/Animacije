package ch.nth.test.animations.layouttransitions

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ch.nth.test.animations.Data
import ch.nth.test.animations.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.list_item_square.view.*
import java.util.*

/**
 * @Author Danijel Turić
 *   2019
 *   Animations
 */
class TransitionListAdapter(listener: OnItemSelectedListener) :
    RecyclerView.Adapter<TransitionListAdapter.ViewHolder>() {

    private var mListener = listener
    private val mData = setupDummyData()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_square, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.label.text = mData[position].description

        val options = RequestOptions()
        options.centerCrop()

        Glide.with(holder.itemView.context).load(mData[position].path).apply(options).into(holder.image)

        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val filter = ColorMatrixColorFilter(colorMatrix)
        holder.image.colorFilter = filter

        holder.itemView.setOnClickListener {
            val location = IntArray(2)
            it.getLocationOnScreen(location)

            val view = holder.itemView
            view.item_image.colorFilter = null
            Glide.with(holder.itemView.context).load(mData[position].path).into(holder.image)
            view.item_text.text = ""
            view.setOnClickListener(null)

            mListener.itemSelected(view)
        }
    }

    interface OnItemSelectedListener {
        fun itemSelected(itemView: View)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal val image: ImageView = view.findViewById(R.id.item_image)
        internal val label: TextView = view.findViewById(R.id.item_text)
    }

    private fun setupDummyData(): LinkedList<Data> {

        val data = LinkedList<Data>()

        data.add(
            Data(
                "https://images.unsplash.com/photo-1449130015084-2d48a345ae62?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Mustang"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1514316454349-750a7fd3da3a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Mecedes"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1525609004556-c46c7d6cf023?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Lamborgini"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1503376780353-7e6692767b70?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Porsche"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1522932467653-e48f79727abf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "BMW"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1506719040632-7d586470c936?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Nissan"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1514867644123-6385d58d3cd4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Ferrari"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1454264856604-ca40bd3a0a7a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Kogh"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1507722661946-ec4abdfe6a1e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "Old stuff"
            )
        )
        data.add(
            Data(
                "https://images.unsplash.com/photo-1470849731624-be3736e40973?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60",
                "With leaves"
            )
        )


        return data
    }

}