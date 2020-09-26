package zakaz.zakaz.notes.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.R;

public class NoteListAdapter extends BaseAdapter {

    private Context context;
    private List<Note> note;

    public NoteListAdapter(Context context, List<Note> note) {
        this.context = context;
        this.note = note;
    }

    @Override
    public int getCount() {
        return note.size();
    }

    @Override
    public Object getItem(int position) {
        return note.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1 = View.inflate(context, R.layout.item_note, null);
        TextView ZagolovokNote = view1.findViewById(R.id.ZagolovokNote);
        TextView TextNote = view1.findViewById(R.id.textNote);
        TextView TagNote = view1.findViewById(R.id.tagNote);

        ZagolovokNote.setText(note.get(position).getZagolovok());
        TextNote.setText(note.get(position).getToday());
        StringBuffer tags = new StringBuffer();
        for (String tag : note.get(position).getTag()){
            tags.append("#");
            tags.append(tag);
            tags.append(" ");

        }
        TagNote.setText(tags);
        return view1;
    }
}
