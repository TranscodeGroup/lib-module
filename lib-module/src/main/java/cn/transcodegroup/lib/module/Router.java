package cn.transcodegroup.lib.module;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

/**
 * <h2>Manual Router/手动路由/Module Router/模块路由</h2>
 * @see <a href="https://github.com/alibaba/ARouter/blob/master/README_CN.md">ARouter</a>
 */
public class Router {
    private static final String TAG = "MRouter";

    public static Router getInstance() {
        return RouterLoader.INSTANCE;
    }

    /** path -> class */
    private Map<String, Class<? extends Activity>> activitys = new HashMap<>();
    private Map<String, Class<? extends Fragment>> fragments = new HashMap<>();
    private Map<String, Class<? extends android.app.Fragment>> appFragments = new HashMap<>();
    /** interface -> instance */
    private Map<Class<? extends Service>, Service> services = new HashMap<>();
    private Context appContext;
    private OnNavigationLostListener onNavigationLostListener;

    public void init(Application application) {
        appContext = application;
    }

    public Postcard build(String path) {
        return new Postcard(path);
    }

    public void navigation(@Nullable Context ctx, Postcard postcard, int requestCode) {
        Context context = ctx != null ? ctx : appContext;
        boolean isActivity = context instanceof Activity;

        Intent intent = postcard.getIntent();
        Class<? extends Activity> activityClass = getActivity(postcard.getPath());
        if (activityClass == null) {
            if (onNavigationLostListener != null) {
                onNavigationLostListener.onNavigationLost(postcard.getPath());
            } else {
                Toast.makeText(appContext, "no found: " + postcard.getPath(), Toast.LENGTH_SHORT).show();
            }
            return;
        }
        intent.setClass(context, activityClass);
        if (requestCode >= 0) {
            if (!isActivity) {
                throw new IllegalArgumentException("startActivityForResult的context要是Activity");
            }
            ActivityCompat.startActivityForResult((Activity) context, intent, requestCode, postcard.getOptions());
        } else {
            if (!isActivity) {
                Log.d(TAG, "context不是activity, 已需要添加FLAG_ACTIVITY_NEW_TASK, 防止崩溃");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            ActivityCompat.startActivity(context, intent, postcard.getOptions());
        }

    }

    public void registerPath(String path, Class<?> cls) {
        Map map;
        if (Activity.class.isAssignableFrom(cls)) {
            map = activitys;
        } else if (Fragment.class.isAssignableFrom(cls)) {
            map = fragments;
        } else if (android.app.Fragment.class.isAssignableFrom(cls)) {
            map = appFragments;
        } else {
            throw new IllegalArgumentException("不支持的cls类型: " + cls);
        }
        if (map.get(path) != null) {
            throw new IllegalStateException("path已经被注册: " + path + "->" + map.get(path));
        }
        map.put(path, cls);
    }

    public <T extends Service, P extends T> void registerService(Class<T> serviceInterface, P service) {
        if (services.get(serviceInterface) != null) {
            throw new IllegalStateException("service已经被注册: " + serviceInterface + "->" + services.get(serviceInterface));
        }
        services.put(serviceInterface, service);
    }

    public Class<? extends Activity> getActivity(String path) {
        return activitys.get(path);
    }

    public Class<? extends Fragment> getFragment(String path) {
        return fragments.get(path);
    }

    public Class<? extends android.app.Fragment> getAppFragment(String path) {
        return appFragments.get(path);
    }

    public <T extends Service> T getService(Class<T> serviceInterface) {
        return (T) services.get(serviceInterface);
    }

    public void setOnNavigationLostListener(OnNavigationLostListener onNavigationLostListener) {
        this.onNavigationLostListener = onNavigationLostListener;
    }

    public interface OnNavigationLostListener {
        void onNavigationLost(String path);
    }

    /** 目前没有需要实现的方法 */
    public interface Service {

    }

    private static class RouterLoader {
        static final Router INSTANCE = new Router();
    }
}
