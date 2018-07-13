package com.agoodwater.system.network;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase {
    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();


    protected UseCase() {

        this.threadExecutor = JobExecutor.getInstance();
        this.postExecutionThread = UIThread.getInstance();
    }

    public void execute(Subscriber UseCaseSubscriber) {
        this.subscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(threadExecutor))
//                .retryWhen(new RetryWhenProcess(5)) //检测联网错误的
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(UseCaseSubscriber);
    }

    public void unSubscribe() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected abstract Observable buildUseCaseObservable();


}
