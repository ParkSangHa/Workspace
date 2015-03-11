package com.example.parksangha.myfirstapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    public final static String EXTRA_MESSAGE = "com.parksangha.myfirstapp.MESSAGE";

    /**
     * A numeric value that identifies the notification that we'll be sending.
     * This value needs to be unique within this app, but it doesn't need to be
     * unique system-wide.
     */
    public static final int NOTIFICATION_ID = 1;

    // 콜백 메써드드
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void sendMessage(View view){
        // DisplayActivity에 메시지 보내는 방법
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

        // 유저가 클릭식 파이어될 인텐트 생성
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, intent, 0);

        //NotificationCompat Builder to set up 알람
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        //알람창에 보여질 작은 아이콘 세팅
        builder.setSmallIcon(R.drawable.ic_stat_notification);

        //인텐트 세팅
        builder.setContentIntent(pendingIntent2);

        //사용자가 아이콘 클릭시 알림 삭제 (일부러 옆으로 지우는 것 말고)
        builder.setAutoCancel(true);

        // 사용자가 알림 내리면 왼쪽에 보이는 아이콘 세팅
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

        // 알림창 상세 정보 세팅
        builder.setContentTitle(message);
        builder.setContentText("323-234-234로 전화주세요");
        builder.setSubText("자료를 준비해주세요");

        // notification을 notificationManager에 보내준다
        //
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
