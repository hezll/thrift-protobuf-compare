#!/bin/sh

# Small documents, should probably use fairly small heap to capture effects
# of memory allocation

/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home/bin/java -Xmx256m -server \
-cp build:$(find lib -name "*.jar" | tr '\n' ':') serializers.BenchmarkRunner
