package zakaz.zakaz.notes.Adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;

import java.util.List;

import zakaz.zakaz.notes.Model.Tag;
import zakaz.zakaz.notes.R;

public class RecyclerAdapterTagsMainMenu extends RecyclerView.Adapter<RecyclerAdapterTagsMainMenu.ViewHolderMain> {

    private Context context;
    private String[] modelList;

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
        holder.chip.setText("#" + modelList[position]);
        holder.chip.setCloseIconVisible(false);
        holder.chip.setChipBackgroundColorResource(R.color.Color1);
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
