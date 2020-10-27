package my.all.notes.Util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import my.all.notes.R;

public class Tutorial{

    public Activity activity;

    public Tutorial(Activity activity) {
        this.activity = activity;
    }

    public void tutorial(Context context, String text_head, int text) {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(context).inflate(R.layout.tutorial_how_use_app, (ConstraintLayout) activity.findViewById(R.id.container_tutorial));
        AlertDialog alertDialog = alertDialogBuilder.setView(view).create();
        TextView head = view.findViewById(R.id.tutorial_head);
        TextView text_main = view.findViewById(R.id.tutorial_text);

        head.setText(text_head);
        text_main.setText(text);

        Button button_OK = view.findViewById(R.id.tutorial_OK);
        button_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
