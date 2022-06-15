package sg.edu.np.mad.exercise2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MsgViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView name;
        TextView description;

public MsgViewHolder( View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        name = itemView.findViewById(R.id.name);
        description = itemView.findViewById(R.id.description);

        }
}