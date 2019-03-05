package cn.transcodegroup.libcommonproject;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import cn.transcodegroup.lib.module.Router;
import cn.transcodegroup.lib.module.ModuleContext;
import cn.transcodegroup.lib.module.provider.LanguageProvider;

class SampleModuleContext extends ModuleContext {
    public SampleModuleContext(Application application) {
        super(application);
    }

    @Override
    public void onAttachBaseContext(Context base) {
        Log.d(App.TAG, "ModuleContext.onAttachBaseContext() called with: base = [" + base + "]");
        Router.getInstance().registerPath("/activity/main", MainActivity.class);
        Router.getInstance().registerProvider(LanguageProvider.class, new LanguageProvider() {
            @Override
            public Context wrapBaseContextForActivity(Context base) {
                Log.d(App.TAG, "wrapBaseContextForActivity: " + base);
                return base;
            }
        });
    }
}
