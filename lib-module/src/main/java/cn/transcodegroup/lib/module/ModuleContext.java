package cn.transcodegroup.lib.module;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;

import androidx.annotation.Nullable;

/** 模块级的{@link android.content.Context}, 用于替代{@link Application} */
public abstract class ModuleContext extends ContextWrapper {
    @Nullable
    public static ModuleContext createModule(@Nullable Class<? extends ModuleContext> moduleClass, Application application) {
        if (moduleClass != null) {
            try {
                return moduleClass.getConstructor(Application.class).newInstance(application);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ModuleContext(Application application) {
        super(application);
    }

    /** @deprecated 使用{@link #onAttachBaseContext(Context)}替代 */
    @Override
    @Deprecated
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        throw new RuntimeException("不要调用/重写该方法, 请重写onAttachBaseContext");
    }

    public void onAttachBaseContext(Context base) {
    }

    public void onCreate() {
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }
}
