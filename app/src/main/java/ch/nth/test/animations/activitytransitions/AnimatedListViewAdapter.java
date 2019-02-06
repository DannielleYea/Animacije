package ch.nth.test.animations.activitytransitions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
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
public class AnimatedListViewAdapter extends RecyclerView.Adapter<AnimatedListViewAdapter.ViewHolder> {

    private boolean editMode = false;
    private boolean fromSaveMode = false;
    private Activity callingActivity;

    private LinkedList<Data> mData = setupDummyData();

    public AnimatedListViewAdapter(Activity activity) {
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
            if (!fromSaveMode)
                startEnterAnimation(viewHolder.itemView);
            rotateRight(viewHolder.icon);
        } else {
            rotateLeft(viewHolder.icon);
        }

        viewHolder.itemText.setText(mData.get(i).description);

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.drawable.circle_21);

        Glide.with(viewHolder.itemView.getContext()).load(mData.get(i).path).apply(options).into(viewHolder.itemImage);

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        viewHolder.itemImage.setColorFilter(filter);
    }

    public void enableEditMode(boolean enable) {
        editMode = enable;
        fromSaveMode = true;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private void startEnterAnimation(View view) {
        view.setTranslationX(-1000f);
        view.animate()
                .translationX(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(1000)
                .start();
    }

    private void rotateRight(final View view) {
        final Handler rotationHandler = new Handler();

        rotationHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setRotation(view.getRotation() + 5);

                if (view.getRotation() % 90 == 45) {
                    rotationHandler.removeCallbacks(this);
                } else
                    rotationHandler.post(this);
            }
        }, 120);
    }

    private void rotateLeft(final View view) {
        final Handler rotationHandler = new Handler();

        rotationHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setRotation(view.getRotation() - 5);

                if (view.getRotation() % 90 == 0) {
                    rotationHandler.removeCallbacks(this);
                } else
                    rotationHandler.post(this);
            }
        }, 120);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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

    private void startActivity(View itemView, Data data) {

        Context context = itemView.getContext();

        Intent intent = new Intent(context, AnimatedDetailsActivity.class);

        intent.putExtra(AnimatedDetailsActivity.EXTRAS_DATA, data);

        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                callingActivity,
                new Pair<>(itemView, AnimatedDetailsActivity.HOLDER),
//                new Pair<>(itemView.findViewById(R.id.item_text), AnimatedDetailsActivity.TEXT_VIEW),
                new Pair<>(itemView.findViewById(R.id.item_image), AnimatedDetailsActivity.IMAGE_VIEW));

        ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
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
