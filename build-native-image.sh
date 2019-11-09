#!/bin/bash

./gradlew --no-daemon clean shadowJar

native-image --no-server \
    -jar build/libs/ratpack-graalvm-demo-all.jar \
    -H:Name=ratpack-graalvm-demo \
    -H:ReflectionConfigurationFiles=reflections.json \
    -H:DynamicProxyConfigurationFiles=proxies.json \
    --no-fallback \
    --enable-url-protocols=http \
    --report-unsupported-elements-at-runtime \
    --allow-incomplete-classpath \
    --initialize-at-run-time=io.netty.handler.codec.http.HttpObjectEncoder,io.netty.handler.ssl.ReferenceCountedOpenSslEngine,io.netty.handler.ssl.ReferenceCountedOpenSslClientContext,io.netty.handler.ssl.ReferenceCountedOpenSslServerContext,io.netty.handler.ssl.JdkNpnApplicationProtocolNegotiator,io.netty.handler.ssl.JettyNpnSslEngine,io.netty.handler.ssl.ConscryptAlpnSslEngine,io.netty.util.internal.logging.Log4JLogger,io.netty.internal.tcnative.CertificateVerifier,io.netty.internal.tcnative.SSL \
    --initialize-at-build-time \
    -Dratpack.epoll.disable=true


