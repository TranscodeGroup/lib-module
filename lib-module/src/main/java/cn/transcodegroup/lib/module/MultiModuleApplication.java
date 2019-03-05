package cn.transcodegroup.lib.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiModuleApplication extends Application {
    private final List<ModuleContext> mModules;

    public MultiModuleApplication() {
        mModules = new ArrayList<>();
        onCreateModule(mModules);
    }

    protected abstract void onCreateModule(List<ModuleContext> modules);

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        onAttachBaseContext(base);
        for (ModuleContext module : mModules) {
            module.onAttachBaseContext(base);
        }
    }

    public abstract void onAttachBaseContext(Context base);

    @Override
    public void onCreate() {
        super.onCreate();
        for (ModuleContext module : mModules) {
            module.onCreate();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        for (ModuleContext module : mModules) {
            module.onConfigurationChanged(newConfig);
        }
    }
}
