package cn.transcodegroup.libcommonproject;

import java.util.List;

import cn.transcodegroup.lib.module.ModuleContext;
import cn.transcodegroup.lib.module.MultiModuleApplication;
import cn.transcodegroup.lib.module.Router;

public class App extends MultiModuleApplication {
    public static final String TAG = "LibModuleProject";

    @Override
    protected void onCreateModule(List<ModuleContext> modules) {
        Router.getInstance().init(this);
        modules.add(new SampleModuleContext(this));
    }
}
