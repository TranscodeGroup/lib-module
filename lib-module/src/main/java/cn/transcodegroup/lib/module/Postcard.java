package cn.transcodegroup.lib.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;

public class Postcard {
    private String path;
    private Intent intent = null;
    private Bundle options = null;

    public Postcard(String path) {
        this.path = path;
    }

    public Postcard withOptionsCompat(ActivityOptionsCompat optionsCompat) {
        options = optionsCompat.toBundle();
        return this;
    }

    public Postcard withIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    public Postcard applyConfig(Config config) {
        if (intent == null) {
            intent = new Intent();
        }
        config.config(intent);
        return this;
    }

    public String getPath() {
        return path;
    }

    @Nullable
    public Intent getIntent() {
        return intent;
    }

    @Nullable
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
