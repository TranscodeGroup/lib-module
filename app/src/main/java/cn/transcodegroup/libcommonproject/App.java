package cn.transcodegroup.libcommonproject;

import android.content.Context;
import android.util.Log;

import java.util.List;

import cn.transcodegroup.lib.common.ModuleContext;
import cn.transcodegroup.lib.common.SimpleMultiModuleApplication;

public class App extends SimpleMultiModuleApplication {
    private static final String TAG = "App";

    @Override
    protected void onCreateModule(List<ModuleContext> modules) {
        modules.add(new ModuleContext(this) {
            @Override
            public void onAttachBaseContext(Context base) {
                Log.d(TAG, "ModuleContext.onAttachBaseContext() called with: base = [" + base + "]");
            }
        });
    }
}
