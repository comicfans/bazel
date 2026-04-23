package com.google.devtools.build.lib.profiler;
import com.google.devtools.build.lib.concurrent.ThreadSafety.ThreadCompatible;
import com.google.gson.stream.JsonWriter;
import java.util.concurrent.TimeUnit;
import java.io.IOException;

@ThreadCompatible
class FlowData implements TraceData {
    final long startTimeNanos;
    final boolean isStart;
    final long threadId;
    final String id;

    public FlowData(long startTimeNanos, boolean isStart,long threadId, String id){
        this.startTimeNanos = startTimeNanos;
        this.isStart = isStart;
        this.threadId = threadId;
        this.id = id;
    }

  @Override
  public String toString() {
    return "Thread " + threadId+ (isStart?" send":" receive")+" flow with id:"+id;
  }

  @Override
  public void writeTraceData(JsonWriter jsonWriter, long profileStartTimeNanos) throws IOException {
    jsonWriter.setIndent("  ");
    jsonWriter.beginObject();
    jsonWriter.setIndent("");
    jsonWriter.name("cat").value("flow");
    jsonWriter.name("name").value(id);
    jsonWriter.name("ph").value(isStart? "s":"t");
    jsonWriter
        .name("ts")
        .value(TimeUnit.NANOSECONDS.toMicros(startTimeNanos - profileStartTimeNanos));
    jsonWriter.name("pid").value(1);
    jsonWriter.name("id").value(id.hashCode());

    jsonWriter
        .name("tid")
        .value(threadId);
    jsonWriter.endObject();
  }


}
