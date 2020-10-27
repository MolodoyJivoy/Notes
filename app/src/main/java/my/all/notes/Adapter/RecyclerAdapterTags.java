package my.all.notes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import my.all.notes.Model.Tag;
import my.all.notes.R;

public class RecyclerAdapterTags extends RecyclerView.Adapter<RecyclerAdapterTags.ViewHolderMain> {

    private Context context;
    private List<Tag> modelList;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomView;

    public RecyclerAdapterTags(Context context, List<Tag> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_tag, parent, false);
        return new RecyclerAdapterTags.ViewHolderMain(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMain holder, final int position) {
        holder.chip.setCheckable(false);
        holder.chip.setTextAppearance(R.style.chipTextAppearance);
        holder.chip.setChipBackgroundColorResource(R.color.TRANSPARENT);
        holder.chip.setChipStrokeWidth((float) 0.3);
        holder.chip.setChipStrokeColorResource(R.color.BackChip);
        holder.chip.setElevation(6);
        ShapeAppearanceModel shapeAppearanceModel = holder.chip.getShapeAppearanceModel();
        ShapeAppearanceModel builder = shapeAppearanceModel.toBuilder().setAllCorners(CornerFamily.ROUNDED, 18).build();
        holder.chip.setShapeAppearanceModel(builder);
        if (modelList.get(position).getId() == -1){
            holder.chip.setChipIconVisible(true);
            holder.chip.setChipIconTintResource(R.color.ColorChip);
            holder.chip.setChipIconResource(R.drawable.ic_baseline_add_24);
            holder.chip.setCloseIconVisible(false);
        }
        else {
            holder.chip.setCloseIconVisible(true);
            holder.chip.setChipIconVisible(false);
            holder.chip.setCloseIconTintResource(R.color.ColorChip);
            holder.chip.setCloseIconSize(54);
        }
        holder.chip.setText(modelList.get(position).getName());
        holder.chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelList.get(position).getId() == -1){
                    startBottomMenu(position);
                }else {
                    editTagMenu(position);
                }
            }
        });

        holder.chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modelList.get(position).getId() != -1){
                    modelList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder implements View.OnClickListener{

        Chip chip;
        OnNoteListener onNoteListener;

        public ViewHolderMain(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chip_item);
            initBottomMenu(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition(), modelList);
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position, List<Tag> modelList);
    }

    void initBottomMenu(View view){
        bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetsDialogTheme);
        bottomView = LayoutInflater.from(context).inflate(R.layout.bottom_sheets_about, (LinearLayout)view.findViewById(R.id.bottomContainer));
        bottomSheetDialog.setContentView(bottomView);
    }

    void startBottomMenu(final int pos){
        Button sendButton = bottomView.findViewById(R.id.sendButton);
        final TextInputEditText textTag = bottomView.findViewById(R.id.TagName);
        textTag.setText("");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelList.add(0, new Tag(textTag.getText().toString(), pos));
                notifyDataSetChanged();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public List<Tag> getModelList(){
        if (modelList.size() != 0){
            return modelList;
        }else {
            return null;
        }
    }

    void editTagMenu(final int pos){
        Button sendButton = bottomView.findViewById(R.id.sendButton);
        final TextInputEditText textTag = bottomView.findViewById(R.id.TagName);
        textTag.setText(modelList.get(pos).getName());
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelList.get(pos).setName(textTag.getText().toString());
                notifyDataSetChanged();
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public void setItems(List<Tag> modelList) {
        this.modelList = modelList;
    }
}
