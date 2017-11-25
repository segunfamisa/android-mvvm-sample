package com.segunfamisa.sample.mvvm.utils;

import java.util.concurrent.Executor;

import io.reactivex.Scheduler;

/**
 * Interface defining the schedulers possible to provide
 */
public interface SchedulerProvider {

    Scheduler io();

    Scheduler computation();

    Scheduler mainThread();

    Scheduler fromExecutor(Executor executor);

}
