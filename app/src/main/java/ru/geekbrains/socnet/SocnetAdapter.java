package ru.geekbrains.socnet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {

    private SocialDataSource  dataSource;
    private OnItemClickListener itemClickListener;

    public SocnetAdapter(SocialDataSource  dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        if (itemClickListener != null){
            vh.setOnClickListener(itemClickListener);
        }
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SocnetAdapter.ViewHolder holder, int position) {
        Soc soc = dataSource.getSoc(position);
        holder.setData(soc.getDescription(), soc.getPicture(), soc.isLike());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    // Интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView image;
        private CheckBox like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Получаем позицию адаптера
                    int adapterPosition = getAdapterPosition();
                    // Проверяем ее на корректность
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    listener.onItemClick(v, adapterPosition);
                }
            });
        }

        public void setData(String description, int picture, boolean like){
            this.like.setChecked(like);
            image.setImageResource(picture);
            this.description.setText(description);
        }
    }
}
