package com.agoodwater.system.network;

import rx.Scheduler;

public interface PostExecutionThread {
    Scheduler getScheduler();
}
