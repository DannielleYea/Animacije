package ch.nth.test.animations.activitytransitions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import ch.nth.test.animations.Data;
import ch.nth.test.animations.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.LinkedList;

/**
 * @Author Danijel Turić
 * 2019
 * Animations
 */
public class NonAnimatedListViewAdapter extends RecyclerView.Adapter<NonAnimatedListViewAdapter.ViewHolder> {

    private boolean editMode = false;
    private boolean fromSaveMode = false;
    private Activity callingActivity;

    private LinkedList<Data> mData = setupDummyData();

    public NonAnimatedListViewAdapter(Activity activity) {
        this.callingActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        if (!editMode) {
            viewHolder.icon.setRotation(45);
        } else {
            viewHolder.icon.setRotation(0);
        }

        viewHolder.itemText.setText(mData.get(i).description);

        RequestOptions options = new RequestOptions();
        options.centerCrop();

        Glide.with(viewHolder.itemView.getContext()).load(mData.get(i).path).apply(options).into(viewHolder.itemImage);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        viewHolder.itemImage.setColorFilter(filter);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private ImageView itemImage;
        private TextView itemText;

        ViewHolder(View view) {
            super(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(v, mData.get(getAdapterPosition()));
                }
            });

            icon = view.findViewById(R.id.list_item_delete);
            itemImage = view.findViewById(R.id.item_image);
            itemText = view.findViewById(R.id.item_text);
        }
    }

    public void enableEditMode(boolean enable) {
        editMode = enable;
        fromSaveMode = true;

        notifyDataSetChanged();
    }
    private void startActivity(View itemView, Data data) {

        Context context = itemView.getContext();

        Intent intent = new Intent(context, AnimatedDetailsActivity.class);

        intent.putExtra(AnimatedDetailsActivity.EXTRAS_DATA, data);

        context.startActivity(intent);
    }

    private LinkedList<Data> setupDummyData() {

        LinkedList<Data> data = new LinkedList<>();

        data.add(new Data("https://images.unsplash.com/photo-1449130015084-2d48a345ae62?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Mustang"));
        data.add(new Data("https://images.unsplash.com/photo-1514316454349-750a7fd3da3a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Mecedes"));
        data.add(new Data("https://images.unsplash.com/photo-1525609004556-c46c7d6cf023?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Lamborgini"));
        data.add(new Data("https://images.unsplash.com/photo-1503376780353-7e6692767b70?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Porsche"));
        data.add(new Data("https://images.unsplash.com/photo-1522932467653-e48f79727abf?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "BMW"));
        data.add(new Data("https://images.unsplash.com/photo-1506719040632-7d586470c936?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Nissan"));
        data.add(new Data("https://images.unsplash.com/photo-1514867644123-6385d58d3cd4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Ferrari"));
        data.add(new Data("https://images.unsplash.com/photo-1454264856604-ca40bd3a0a7a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Kogh"));
        data.add(new Data("https://images.unsplash.com/photo-1507722661946-ec4abdfe6a1e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "Old stuff"));
        data.add(new Data("https://images.unsplash.com/photo-1470849731624-be3736e40973?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "With leaves"));

        return data;
    }
}
