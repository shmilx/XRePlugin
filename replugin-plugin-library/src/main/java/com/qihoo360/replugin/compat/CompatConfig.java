package com.qihoo360.replugin.compat;

/**
 * 兼容层配置
 *
 * @author shmiyx
 * @since 2022/01/05
 */
public final class CompatConfig {

    private static volatile CompatConfig sInstance;

    public static final boolean DEPENDENCY_ANDROIDX;
    public static final boolean DEPENDENCY_SUPPORT;

    static {
        DEPENDENCY_ANDROIDX = findClassByClassName("androidx.fragment.app.FragmentActivity");
        DEPENDENCY_SUPPORT = findClassByClassName("android.support.v4.app.FragmentActivity");
    }

    private CompatConfig() {
    }

    public static CompatConfig getInstance() {
        if (sInstance == null) {
            synchronized (CompatConfig.class) {
                if (sInstance == null) {
                    sInstance = new CompatConfig();
                }
            }
        }
        return sInstance;
    }

    private static boolean findClassByClassName(String className) {
        boolean hasDependency;
        try {
            Class.forName(className);
            hasDependency = true;
        } catch (ClassNotFoundException e) {
            hasDependency = false;
        }
        return hasDependency;
    }
}
