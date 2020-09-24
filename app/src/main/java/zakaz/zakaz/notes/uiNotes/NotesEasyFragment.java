package zakaz.zakaz.notes.uiNotes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import zakaz.zakaz.notes.R;


public class NotesEasyFragment extends Fragment {

    TextView Date;

    public NotesEasyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes_easy, container, false);

        init(view);
        getTime();
        // Inflate the layout for this fragment
        return view;
    }

    private void getTime() {
        DateFormat df = new SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        Date.setText(date);
    }

    private void init(View view) {
        Date = view.findViewById(R.id.DateEasyNotes);
    }
}