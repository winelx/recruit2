package com.recruit.zejuxin.recruit.Code.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;


/**
 * @class name：com.diabin.app.latte
 * @anthor winelx
 * @time 2017/8/21 0021 11:01
 * 配置文件的存储和获取
 */
public class Configurator {
    /**
     * 存储配置
     */
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //字体存储
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<IconFontDescriptor>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    private static final Handler HANDLER = new Handler();

    //创建枚举，初始化枚举类
    private Configurator() {

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
        LATTE_CONFIGS.put(ConfigType.HANDLER.name(), HANDLER);
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 线程安全单例实现
     */
    static Configurator getInstance() {
        return Holder.configurator;
    }

    //实现configurator，用静态的方法。
    private static class Holder {
        private static final Configurator configurator = new Configurator();
    }

    public final void configure() {
        initICOn();

        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigType.ACTIVITY, activity);
        return this;
    }

    @SuppressWarnings("unchecked")

    public final <T> T getConfiguration(Enum<ConfigType> key) {
        checkCOnfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    private void checkCOnfiguration() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady) {
            throw new RuntimeException("error");
        }
    }

    private void initICOn() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> list) {
        INTERCEPTORS.addAll(list);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
}
