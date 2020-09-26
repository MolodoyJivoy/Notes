package zakaz.zakaz.notes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;

import java.util.List;

import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Model.Tag;
import zakaz.zakaz.notes.R;

public class NoteRecyclerListAdapter extends RecyclerView.Adapter<NoteRecyclerListAdapter.ViewHolderMain> {

    private Context context;
    private List<Note> modelList;

    private OnNoteListener m_onNoteListener;

    public NoteRecyclerListAdapter(Context context, List<Note> modelList, OnNoteListener onNoteListener) {
        this.context = context;
        this.modelList = modelList;
        this.m_onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public NoteRecyclerListAdapter.ViewHolderMain onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_note, parent, false);
        return new NoteRecyclerListAdapter.ViewHolderMain(view, m_onNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteRecyclerListAdapter.ViewHolderMain holder, int position) {
        holder.ZagolovokNote.setText(modelList.get(position).getZagolovok());
        holder.TextNote.setText(modelList.get(position).getToday());

        RecyclerAdapterTagsMainMenu recyclerAdapterTagsMainMenu = new RecyclerAdapterTagsMainMenu(context, modelList.get(position).getTag());
        holder.recyclerViewTags.setAdapter(recyclerAdapterTagsMainMenu);
        holder.recyclerViewTags.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));


        StringBuffer tags = new StringBuffer();
        for (String tag : modelList.get(position).getTag()){
            tags.append("#");
            tags.append(tag);
            tags.append(" ");

        }
        holder.TagNote.setText(tags);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolderMain extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView ZagolovokNote;
        TextView TextNote;
        TextView TagNote;
        RecyclerView recyclerViewTags;
        OnNoteListener onNoteListener;

        public ViewHolderMain(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            ZagolovokNote = itemView.findViewById(R.id.ZagolovokNote);
            TextNote = itemView.findViewById(R.id.textNote);
            TagNote = itemView.findViewById(R.id.tagNote);
            recyclerViewTags = itemView.findViewById(R.id.ItemsCircleMainManu);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition(), modelList);
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position, List<Note> modelList);
    }
}
