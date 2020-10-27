package my.all.notes.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import my.all.notes.R;

public class RecyclerAdapterTagsMainMenu extends RecyclerView.Adapter<RecyclerAdapterTagsMainMenu.ViewHolderMain> {

    private Context context;
    private String[] modelList;

    private int[] colors = {R.color.Color1, R.color.Color2, R.color.Color3, R.color.Color4,R.color.Color5,
            R.color.Color6, R.color.Color7 ,R.color.Color8, R.color.Color9, R.color.Color10};

    public RecyclerAdapterTagsMainMenu(Context context, String[] modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_tag_main_menu, parent, false);
        return new RecyclerAdapterTagsMainMenu.ViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMain holder, int position) {
        int a = 0;
        int b = 9;
        int random = a + (int) (Math.random() * b);
        holder.chip.setText("#" + modelList[position]);
        holder.chip.setCloseIconVisible(false);
        holder.chip.setChipBackgroundColorResource(colors[random]);
        holder.chip.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return modelList.length;
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder{

        Chip chip;
        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip_item_main_menu);
        }
    }

}
