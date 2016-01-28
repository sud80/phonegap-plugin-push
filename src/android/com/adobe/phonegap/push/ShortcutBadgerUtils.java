package com.adobe.phonegap.push;

import android.util.Log;
import android.content.Context;
import android.content.SharedPreferences;

import me.leolin.shortcutbadger.ShortcutBadger;

public class ShortcutBadgerUtils {

    public static final String LOG_TAG = "PushPluginShortcutBadgerUtils";
    protected static final String CONTEXT_KEY = "pushpluginbadgecount";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return ctx.getSharedPreferences(CONTEXT_KEY, Context.MODE_PRIVATE);
    }

    private static void saveBadge (Context ctx, int count) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(CONTEXT_KEY, count);
        editor.apply();
    }

    public static boolean setBadgeCount(Context ctx, int count)
    {
        Log.d(LOG_TAG, "setBadgeIcon: " + count);
        boolean status = ShortcutBadger.applyCount(ctx, count);
        if(status) {
            saveBadge(ctx, count);
        } else {
            Log.e(LOG_TAG, "Failed setBadgeIcon: " + count);
        }
        return status;
    }

    public static int getBadgeCount(Context ctx)
    {
        SharedPreferences settings = getSharedPreferences(ctx);
        int count = settings.getInt(CONTEXT_KEY, 0);
        Log.d(LOG_TAG, "getBadgeIcon: " + count);
        return count;
    }
}
