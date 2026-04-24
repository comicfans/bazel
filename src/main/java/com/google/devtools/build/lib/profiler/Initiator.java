package com.google.devtools.build.lib.profiler;

public class Initiator{
    final long threadId;
    final long startTimeNanos;
    public Initiator(long threadId,long startTimeNanos){
        this.threadId = threadId;
        this.startTimeNanos=startTimeNanos;
    }

    public long getStartTimeNanos(){
        return startTimeNanos;
    }

    public long getThreadId(){
        return threadId;
    }


    @Override
    public String toString(){
        return threadId + "_" + startTimeNanos;
    }
}
