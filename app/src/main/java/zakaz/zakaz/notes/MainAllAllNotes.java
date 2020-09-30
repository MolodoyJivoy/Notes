package zakaz.zakaz.notes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import io.paperdb.Paper;
import zakaz.zakaz.notes.Adapter.NoteRecyclerListAdapter;
import zakaz.zakaz.notes.Data.Data;
import zakaz.zakaz.notes.Model.Note;
import zakaz.zakaz.notes.Presenter.INoteAllPresenter;
import zakaz.zakaz.notes.Presenter.NoteAllAllPresenter;
import zakaz.zakaz.notes.View.IMainAllNotes;

public class MainAllAllNotes extends AppCompatActivity implements IMainAllNotes, NoteRecyclerListAdapter.OnNoteListener {

    Button newNote;
    INoteAllPresenter iNoteAllPresenter;
    RecyclerView listAllView;

    Toolbar toolbar;

    NoteRecyclerListAdapter noteRecyclerListAdapter;

    private static final int NOTIFY_ID = 101;

    private static String CHANNEL_ID = "Cat channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //clear();
        listAllView = findViewById(R.id.AllNotes);
        newNote = findViewById(R.id.newNote);
        toolbar = findViewById(R.id.toolbarMain);
        toolbar.setTitle("Все заметки");
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);

//        notificathion();
        //showNotification(MainAllAllNotes.this, "1", "2", null);
        issueNotification();

        iNoteAllPresenter = new NoteAllAllPresenter(this, new Data());
        iNoteAllPresenter.onAllNotes(getApplicationContext(), 0);

        newNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAllAllNotes.this, Notes.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    private void notificathion() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(MainAllAllNotes.this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_settings_24)
                        .setContentTitle("Напоминание")
                        .setContentText("Пора покормить кота")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(MainAllAllNotes.this);
        notificationManager.notify(NOTIFY_ID, builder.build());
    }

    public void showNotification(Context context, String title, String body, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = 1;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_MIN;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);

//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
//                0,
//                PendingIntent.FLAG_UPDATE_CURRENT
//        );
//        mBuilder.setContentIntent(resultPendingIntent);
//        mBuilder.setAutoCancel(false);
//        mBuilder.setOngoing(true);
        notificationManager.notify(notificationId, mBuilder.build());
    }

    void issueNotification()
    {

        // make the channel. The method has been discussed before.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            makeNotificationChannel("CHANNEL_1", "Example channel", NotificationManager.IMPORTANCE_HIGH);
        }
        // the check ensures that the channel will only be made
        // if the device is running Android 8+

        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this, "CHANNEL_1");
        // the second parameter is the channel id.
        // it should be the same as passed to the makeNotificationChannel() method

        notification
                .setSmallIcon(R.drawable.ic_present) // can use any other icon
                .setContentTitle("Случайный подарок")
                .setContentText("Купить цветы")
                .setNumber(3); // this shows a number in the notification dots

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.notify(1, notification.build());
        // it is better to not use 0 as notification id, so used 1.
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void makeNotificationChannel(String id, String name, int importance)
    {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setShowBadge(false); // set false to disable badges, Oreo exclusive

        NotificationManager notificationManager =
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        assert notificationManager != null;
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public void AllNotes(List<Note> note, int mode) {
        //noteListAdapter = new NoteListAdapter(this, note);
        // mode - режитм получения данных

        if (mode == 0){
            noteRecyclerListAdapter = new NoteRecyclerListAdapter(this, note, this);
            listAllView.setAdapter(noteRecyclerListAdapter);
            listAllView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }else if (mode == 1){
            noteRecyclerListAdapter.setItems(note);
            noteRecyclerListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_main_setting:
                Intent intent = new Intent(MainAllAllNotes.this, Settings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == 1){
                iNoteAllPresenter.onAllNotes(getApplicationContext(), 0);
                Toast.makeText(MainAllAllNotes.this, "Заметка добавлена", Toast.LENGTH_SHORT).show();
            }else if (requestCode ==2 ){
                Toast.makeText(MainAllAllNotes.this, "Заметка обнавлена", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    void clear(){
        Paper.init(getApplicationContext());
        Paper.book("Notes").destroy();
    }

    @Override
    public void onNoteClick(int position, List<Note> modelList) {
        Intent intent = new Intent(MainAllAllNotes.this, Notes.class);
        startActivityForResult(intent, 2);
        Toast.makeText(this, modelList.get(position).getToday(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoteLongClick(int position, List<Note> modelList) {
        iNoteAllPresenter.onDelete(modelList.get(position).getUniqueID(), MainAllAllNotes.this);
        iNoteAllPresenter.onAllNotes(MainAllAllNotes.this, 1);
    }
}