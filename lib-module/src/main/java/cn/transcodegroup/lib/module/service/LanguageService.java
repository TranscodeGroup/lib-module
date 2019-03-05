package cn.transcodegroup.lib.module.service;

import android.content.Context;

import cn.transcodegroup.lib.module.Router;

public interface LanguageService extends Router.Service {
    Context wrapBaseContextForActivity(Context base);
}
