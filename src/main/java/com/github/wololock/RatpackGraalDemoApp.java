package com.github.wololock;

import com.oracle.svm.core.annotate.Alias;
import com.oracle.svm.core.annotate.RecomputeFieldValue;
import com.oracle.svm.core.annotate.TargetClass;
import ratpack.server.RatpackServer;

import java.util.HashMap;
import java.util.Map;

import static ratpack.jackson.Jackson.json;

final class RatpackGraalDemoApp {

    public static void main(String[] args) throws Exception {
        final Map<String, String> message = new HashMap<String, String>() {{
            put("message", "Hello, World!");
        }};

        RatpackServer.start(server ->
                server.serverConfig(config -> config.sysProps().development(false).threads(1))
                        .handlers(chain -> chain.get(ctx -> ctx.render(json(message))))
        );
    }

    @TargetClass(className = "io.netty.util.internal.PlatformDependent0")
    static final class Target_io_netty_util_internal_PlatformDependent0 {
        @Alias
        @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.FieldOffset, declClassName = "java.nio.Buffer", name = "address")
        private static long ADDRESS_FIELD_OFFSET;
    }

    @TargetClass(className = "io.netty.util.internal.CleanerJava6")
    static final class Target_io_netty_util_internal_CleanerJava6 {
        @Alias
        @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.FieldOffset, declClassName = "java.nio.DirectByteBuffer", name = "cleaner")
        private static long CLEANER_FIELD_OFFSET;
    }

    @TargetClass(className = "io.netty.util.internal.shaded.org.jctools.util.UnsafeRefArrayAccess")
    static final class Target_io_netty_util_internal_shaded_org_jctools_util_UnsafeRefArrayAccess {
        @Alias
        @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexShift, declClass = Object[].class)
        public static int REF_ELEMENT_SHIFT;
    }

    @TargetClass(className = "com.github.benmanes.caffeine.cache.UnsafeRefArrayAccess")
    static final class Target_com_github_benmanes_caffeine_cache_UnsafeRefArrayAccess {
        @Alias
        @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexShift, declClass = Object[].class)
        public static int REF_ELEMENT_SHIFT;
    }
}
