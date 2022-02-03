package com.example.myapplication;

import android.app.PendingIntent;
import android.appwidget.*;
import android.content.*;
import android.net.Uri;
import android.widget.*;

public class MainActivity extends AppWidgetProvider {
    public void onUpdate(Context c, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0; i < appWidgetIds.length; i++) {
            int id = appWidgetIds[i];
            String url = "ESCOM";
            Intent in = new Intent(Intent.ACTION_VIEW);
            in.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            in.setData(Uri.parse(url));
            PendingIntent pi = PendingIntent.getActivity(c, 0, in, 0);
            RemoteViews rv = new RemoteViews(c.getPackageName(), R.layout.activity_main);
            rv.setOnClickPendingIntent(R.id.xbn, pi);
            appWidgetManager.updateAppWidget(id, rv);
            Toast.makeText(c, "Widget agregado", Toast.LENGTH_SHORT).show();
        }
    }
}
