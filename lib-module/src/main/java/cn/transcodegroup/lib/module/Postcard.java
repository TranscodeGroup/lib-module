package cn.transcodegroup.lib.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

public class Postcard {
    private String path;
    private Intent intent;
    private Bundle options = null;

    public Postcard(String path) {
        this.path = path;
        this.intent = new Intent();
    }

    public Postcard withOptionsCompat(ActivityOptionsCompat optionsCompat) {
        options = optionsCompat.toBundle();
        return this;
    }

    public Postcard applyConfig(Config config) {
        config.config(intent);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Intent getIntent() {
        return intent;
    }

    public Bundle getOptions() {
        return options;
    }

    public void navigation() {
        Router.getInstance().navigation(null, this, -1);
    }

    public void navigation(Context context) {
        Router.getInstance().navigation(context, this, -1);
    }

    public void navigation(Activity activity, int requestCode) {
        Router.getInstance().navigation(activity, this, requestCode);
    }

    public interface Config {
        void config(Intent intent);
    }
}
