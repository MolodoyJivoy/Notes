package zakaz.zakaz.notes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Model.StatusNote;
import zakaz.zakaz.notes.R;

public class NoteRecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Note> modelList;

    private OnNoteListener m_onNoteListener;

    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    public NoteRecyclerListAdapter(Context context, List<Note> modelList, OnNoteListener onNoteListener) {
        this.context = context;
        this.modelList = modelList;
        this.m_onNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case TYPE_ONE:
                View view = layoutInflater.inflate(R.layout.item_note, parent, false);
                return new NoteRecyclerListAdapter.ViewHolderMain(view, m_onNoteListener);
            case TYPE_TWO:
                View view2 = layoutInflater.inflate(R.layout.item_note_easy, parent, false);
                return new NoteRecyclerListAdapter.ViewHolderMainEasy(view2, m_onNoteListener);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_ONE:
                ViewHolderMain viewHolder0 = (ViewHolderMain) holder;
                initViewholderModern((ViewHolderMain) holder, position);
                break;

            case TYPE_TWO:
                ViewHolderMainEasy viewHolder2 = (ViewHolderMainEasy) holder;
                initViewholderEasy((ViewHolderMainEasy) holder, position);
                break;
        }

    }

    private void initViewholderEasy(ViewHolderMainEasy holder, int position) {
        holder.ZagolovokNoteEasy.setText(modelList.get(position).getZagolovok());
        holder.TextNoteEasy.setText(modelList.get(position).getToday());
        holder.DateNoteEasy.setText(modelList.get(position).getDate());
        holder.bookMarkEasy.setImageResource(R.drawable.ic_bookmark_note_easy);

    }

    private void initViewholderModern(ViewHolderMain holder, int position) {
        holder.ZagolovokNote.setText(modelList.get(position).getZagolovok());
        holder.TextNote.setText(modelList.get(position).getToday());

        RecyclerAdapterTagsMainMenu recyclerAdapterTagsMainMenu = new RecyclerAdapterTagsMainMenu(context, modelList.get(position).getTag());
        holder.recyclerViewTags.setAdapter(recyclerAdapterTagsMainMenu);
        holder.recyclerViewTags.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        holder.bookMarkModern.setImageResource(R.drawable.ic_bookmark_note);

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

        ImageView bookMarkModern;

        public ViewHolderMain(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            ZagolovokNote = itemView.findViewById(R.id.ZagolovokNote);
            TextNote = itemView.findViewById(R.id.TextEasyNotes);
            TagNote = itemView.findViewById(R.id.tagNote);
            recyclerViewTags = itemView.findViewById(R.id.ItemsCircleMainManu);
            bookMarkModern = itemView.findViewById(R.id.bookMarkModern);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition(), modelList);
        }
    }

    public class ViewHolderMainEasy extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView ZagolovokNoteEasy;
        TextView DateNoteEasy;
        TextView TextNoteEasy;
        OnNoteListener onNoteListener;

        ImageView bookMarkEasy;

        public ViewHolderMainEasy(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            ZagolovokNoteEasy = itemView.findViewById(R.id.ZagolovokNoteEasy);
            DateNoteEasy = itemView.findViewById(R.id.DateNoteEasy);
            TextNoteEasy = itemView.findViewById(R.id.TextEasyNotes);
            bookMarkEasy = itemView.findViewById(R.id.bookMarkEasy);
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

    @Override
    public int getItemViewType(int position) {
        if (modelList.get(position).getNoteStatus() == StatusNote.MODERN) {
            return TYPE_ONE;
        } else if (modelList.get(position).getNoteStatus() == StatusNote.EASY) {
            return TYPE_TWO;
        } else {
            return -1;
        }
        //return position % 2 * 2;
    }
}
