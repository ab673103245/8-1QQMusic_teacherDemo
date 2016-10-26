package org.sang.a8_1qqmusic.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import cn.sharesdk.framework.PlatformDb;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by 王松 on 2016/10/24.
 */

public class LoginUtil {

    public static void saveData(PlatformDb db, Context context) {
        SharedPreferences sp = context.getSharedPreferences(MusicUtil.USERINFO_SP, MODE_PRIVATE);
        if (db != null) {
            String userGender = db.getUserGender();
            String userIcon = db.getUserIcon();
            String userId = db.getUserId();
            String userName = db.getUserName();
            Log.d("google.sang", "saveData: userGender:" + userGender);
            Log.d("google.sang", "saveData: userIcon:" + userIcon);
            Log.d("google.sang", "saveData: userId:" + userId);
            Log.d("google.sang", "saveData: userName:" + userName);
            sp.edit().putString("username", userName).putString("userface", userIcon).commit();
        } else {
            sp.edit().putString("username", "").putString("userface", "").commit();
        }
    }
}
