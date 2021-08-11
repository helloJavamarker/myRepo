package com.test.mark.zhang.test.other.cache.local;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * 可自动清理过期对象的本地缓存
 *
 * @author bailey.fu
 * @version 2.1
 * @date Dec 14, 2010
 * @update 2017-06-20 15:01
 * @description 自定义缓存, 由ActionTimer实现计时
 */
public abstract class AutoCleanAbleMapCache extends MapCache {
    private static final int DEFAULT_CLEAR_INTERVAL = 1;

    /**
     * 清理间隔(单位：分钟；最小为1分钟,默认1分钟)
     */
    private int clearInterval;
    //private ActionTimer actionTimer;
    // 检查间隔1分钟
    private int checkInterval = 1;
    private boolean emittingHasStoped = false;
    private Observable<Long> obva;
    private Disposable emitterDis;

    public AutoCleanAbleMapCache() {
        this.clearInterval = DEFAULT_CLEAR_INTERVAL;
        startEmit();
    }

    public AutoCleanAbleMapCache(Integer clearInterval) {
        this.clearInterval = clearInterval < 1 ? DEFAULT_CLEAR_INTERVAL : clearInterval;
        startEmit();
    }

    private void init() {
        //this.actionTimer = new ActionTimer(true);
        this.obva = Observable.create((ObservableOnSubscribe<Long>) e -> {
            Disposable d = Observable.interval(checkInterval, TimeUnit.MINUTES).subscribe((t) -> {
                emit(e, t);
            });
            setEmitterDis(d);
        });
        this.obva.subscribeOn(Schedulers.newThread()).observeOn(Schedulers.io()).subscribe(this::clear, (e) -> {
            //LOGGER.error("AutoCleanAbleCache emit error", e);
            stopEmit();
        }, () -> {
            stopEmit();
        });
    }

    private void setEmitterDis(Disposable dis) {
        emitterDis = dis;
    }

    private void startEmit() {
        emittingHasStoped = false;
        init();
    }

    private void stopEmit() {
        emittingHasStoped = true;
        //actionTimer = null;
        obva = null;
        if (!emitterDis.isDisposed()) {
            emitterDis.dispose();
        }
    }

    private void clear(long t) {
        //if (actionTimer.onTime(clearInterval * 60 * 1000)) {
            clearExpiring();
        //}
    }

    private void emit(ObservableEmitter<Long> emitter, long t) {
        if (emittingHasStoped) {
            emitter.onComplete();
        }
        emitter.onNext(t);
    }

    abstract protected void clearExpiring();

    protected class Entity {
        Object element;
        long expiring;
        long createTime;

        public Entity(Object element) {
            this.element = element;
            expiring = 0L;
            this.createTime = System.currentTimeMillis();
        }

        public Entity(Object element, long expiring) {
            this.element = element;
            this.expiring = expiring;
            this.createTime = System.currentTimeMillis();
        }

        public boolean unAble() {
            return expiring > 0 && (createTime + expiring) <= System.currentTimeMillis();
        }

        public Object getElement() {
            if (unAble()) {
                return null;
            }
            return element;
        }
    }
}
