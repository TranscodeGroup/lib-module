package cn.transcodegroup.libcommonproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.transcodegroup.lib.module.Router;
import cn.transcodegroup.lib.module.Postcard;
import cn.transcodegroup.lib.module.provider.LanguageProvider;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        LanguageProvider languageProvider = Router.getInstance().getProvider(LanguageProvider.class);
        super.attachBaseContext(languageProvider.wrapBaseContextForActivity(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRouteToMainClick(View view) {
        Router.getInstance()
                .build("/activity/main")
                .applyConfig(new Postcard.Config() {
                    @Override
                    public void config(Intent intent) {

                    }
                })
                .navigation(this);
    }

    public void onRouteToNoFoundClick(View view) {
        Router.getInstance()
                .build("/activity/no_found")
                .navigation(this);
    }
}
