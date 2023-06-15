package com.example.app22;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        super.onUpdate(context,appWidgetManager,appWidgetIds);;
        for (int appWidgetid : appWidgetIds){
            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
            Intent i = new Intent(context,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_IMMUTABLE);
            views.setOnClickPendingIntent(R.id.First,pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetid,views);
        }
    }
}
