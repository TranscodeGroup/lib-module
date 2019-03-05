package cn.transcodegroup.lib.module.provider;

import android.content.Context;

import cn.transcodegroup.lib.module.Router;

public interface LanguageProvider extends Router.Provider {
    Context wrapBaseContextForActivity(Context base);
}
