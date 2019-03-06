package cn.transcodegroup.lib.module.service;

import cn.transcodegroup.lib.module.Router;

public interface MapService extends Router.Service {
    int MAP_TYPE_BAIDU = 1;
    int MAP_TYPE_GOOGLE = 2;

    int getMapType();
}
