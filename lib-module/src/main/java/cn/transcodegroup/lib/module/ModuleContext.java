package cn.transcodegroup.lib.module;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;

/** 模块级的{@link android.content.Context}, 用于替代{@link Application} */
public abstract class ModuleContext extends ContextWrapper {

    public ModuleContext(Application application) {
        super(application);
    }

    @Override
    @Deprecated
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        throw new RuntimeException("不要调用/重写该方法, 请重写onAttachBaseContext");
    }

    public abstract void onAttachBaseContext(Context base);

    public void onCreate() {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
