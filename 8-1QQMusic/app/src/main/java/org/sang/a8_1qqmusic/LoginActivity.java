package org.sang.a8_1qqmusic;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import org.sang.a8_1qqmusic.util.LoginUtil;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void login(View view) {
        QQ qq = new QQ(this);
        //判断用户是否已经获取授权
        if (qq.isAuthValid()) {
            PlatformDb db = qq.getDb();
            LoginUtil.saveData(db, LoginActivity.this);
//            startActivity(new Intent(this, MainActivity.class));
//            LoginActivity.this.finish();
        } else {
            //引导用户登录
//            qq.authorize();
            qq.showUser(null);
        }
        qq.setPlatformActionListener(new PlatformActionListener() {
            //登录成功时回调
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                PlatformDb db = platform.getDb();
                LoginUtil.saveData(db, LoginActivity.this);
//                startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                LoginActivity.this.finish();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
