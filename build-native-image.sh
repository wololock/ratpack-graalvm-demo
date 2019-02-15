#!/bin/bash

./gradlew clean shadowJar

native-image --no-server \
    -jar build/libs/ratpack-graalvm-demo-all.jar \
    -H:Name=ratpack-graalvm-demo \
    -H:EnableURLProtocols=http \
    -H:+AllowVMInspection \
    -H:+ReportUnsupportedElementsAtRuntime \
    -H:ReflectionConfigurationFiles=reflections.json \
    -H:DynamicProxyConfigurationFiles=proxies.json \
    --allow-incomplete-classpath \
    --delay-class-initialization-to-runtime=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.ssl.ReferenceCountedOpenSslEngine,io.netty.handler.ssl.ReferenceCountedOpenSslClientContext,io.netty.handler.ssl.ReferenceCountedOpenSslServerContext,io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator,io.netty.handler.ssl.JettyNpnSslEngine,io.netty.handler.ssl.ConscryptAlpnSslEngine,io.netty.util.internal.logging.Log4JLogger \
    -Dratpack.epoll.disable=true


