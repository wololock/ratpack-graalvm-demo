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

    @TargetClass(className = "com.github.benmanes.caffeine.cache.UnsafeRefArrayAccess")
    static final class Target_com_github_benmanes_caffeine_cache_UnsafeRefArrayAccess {
        @Alias
        @RecomputeFieldValue(kind = RecomputeFieldValue.Kind.ArrayIndexShift, declClass = Object[].class)
        public static int REF_ELEMENT_SHIFT;
    }
}
