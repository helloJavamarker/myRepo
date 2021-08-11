package com.test.mark.zhang.test.other.cache.em;

import com.test.mark.zhang.test.other.cache.Cache;
import com.test.mark.zhang.test.other.cache.Shard;
import com.test.mark.zhang.test.other.cache.local.CaffeineCache;
import com.test.mark.zhang.test.other.cache.local.EHCache;
import com.test.mark.zhang.test.other.cache.local.map.LRUMapCache;
import com.test.mark.zhang.test.other.cache.local.map.MultiLRUMapCache;
import com.test.mark.zhang.test.other.cache.local.map.MultiMapAutoCleanCache;
import com.test.mark.zhang.test.other.cache.local.map.MultiMapCache;
import com.test.mark.zhang.test.other.cache.local.map.SingleMapAutoCleanCache;
import com.test.mark.zhang.test.other.cache.local.map.SingleMapCache;
import com.test.mark.zhang.test.other.cache.local.map.shard.LRUMapShardCache;
import com.test.mark.zhang.test.other.cache.local.map.shard.MultiLRUMapShardCache;
import com.test.mark.zhang.test.other.cache.local.map.shard.MultiMapAutoCleanShardCache;
import com.test.mark.zhang.test.other.cache.local.map.shard.MultiMapShardCache;
import com.test.mark.zhang.test.other.cache.local.map.shard.SingleMapAutoCleanShardCache;
import com.test.mark.zhang.test.other.cache.local.map.shard.SingleMapShardCache;
import com.test.mark.zhang.test.other.cache.local.shard.CaffeineShardCache;
import com.test.mark.zhang.test.other.cache.local.shard.EHShardCache;
import com.test.mark.zhang.test.other.cache.redis.SingleRedisCache;
import com.test.mark.zhang.test.other.cache.redis.shard.SingleRedisShardCache;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.Asserts;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Cache的实现类描述
 *
 * @author fuli
 * @version 1.0.0
 * @date 2018年9月6日
 */
public enum CacheClass {
    /*** Local ***/
    CaffeineCache(CaffeineCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null) {
            switch (args.length) {
                case 1:
                    return new CaffeineCache(Integer.parseInt(args[0].toString()));
                case 3: {
                    TimeUnit timeUnit = TimeUnit.valueOf(args[2].toString());
                    Asserts.check(timeUnit != null, "[Cache] CaffeineCache No support TimeUnit '" + args[2] + "'");
                    return new CaffeineCache(Integer.parseInt(args[0].toString()), Long.parseLong(args[1].toString()), timeUnit);
                }
                case 4: {
                    TimeUnit timeUnit = TimeUnit.valueOf(args[2].toString());
                    Asserts.check(timeUnit != null, "[Cache] CaffeineCache No support TimeUnit '" + args[2] + "'");
                    return new CaffeineCache(Integer.parseInt(args[0].toString()), Long.parseLong(args[1].toString()), timeUnit, args[3].toString());
                }
                default:
                    //
            }
        }
        return new CaffeineCache();
    }),
    EHCache(com.test.mark.zhang.test.other.cache.local.EHCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize EHCache need one parameter at least!");
        if (args.length == 1) {
            return new EHCache(args[0].toString());
        }
        return new EHCache(args[0].toString(), args[1].toString());
    }),
    LRUMapCache(com.test.mark.zhang.test.other.cache.local.map.LRUMapCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null && args.length > 0) {
            return new LRUMapCache(Integer.parseInt(args[0].toString()));
        }
        return new LRUMapCache();
    }),
    MultiLRUMapCache(com.test.mark.zhang.test.other.cache.local.map.MultiLRUMapCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null && args.length > 0) {
            return new MultiLRUMapCache(Integer.parseInt(args[0].toString()));
        }
        return new MultiLRUMapCache();
    }),
    MultiMapAutoCleanCache(com.test.mark.zhang.test.other.cache.local.map.MultiMapAutoCleanCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null && args.length > 0) {
            return new MultiMapAutoCleanCache(Integer.parseInt(args[0].toString()));
        }
        return new MultiMapAutoCleanCache();
    }),
    MultiMapCache(com.test.mark.zhang.test.other.cache.local.map.MultiMapCache.class, CacheType.LOCAL, (args, beanGetter) -> new MultiMapCache()),
    SingleMapAutoCleanCache(com.test.mark.zhang.test.other.cache.local.map.SingleMapAutoCleanCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null && args.length > 0) {
            return new SingleMapAutoCleanCache(Integer.parseInt(args[0].toString()));
        }
        return new SingleMapAutoCleanCache();
    }),
    SingleMapCache(com.test.mark.zhang.test.other.cache.local.map.SingleMapCache.class, CacheType.LOCAL, (args, beanGetter) -> new SingleMapCache()),
    // shard cache
    CaffeineShardCache(com.test.mark.zhang.test.other.cache.local.shard.CaffeineShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null) {
            switch (args.length) {
                case 1: {
                    if (StringUtils.isNumeric(args[0].toString())) {
                        return new CaffeineShardCache(Integer.parseInt(args[0].toString()));
                    } else {
                        return new CaffeineShardCache(args[0].toString());
                    }
                }
                case 3: {
                    TimeUnit timeUnit = TimeUnit.valueOf(args[2].toString());
                    Asserts.check(timeUnit != null, "[Cache] CaffeineShardCache No support TimeUnit '" + args[2] + "'");
                    return new CaffeineShardCache(Integer.parseInt(args[0].toString()), Long.parseLong(args[1].toString()), timeUnit);
                }
                case 4: {
                    TimeUnit timeUnit = TimeUnit.valueOf(args[2].toString());
                    Asserts.check(timeUnit != null, "[Cache] CaffeineShardCache No support TimeUnit '" + args[2] + "'");
                    return new CaffeineShardCache(Integer.parseInt(args[0].toString()), Long.parseLong(args[1].toString()), timeUnit, args[3].toString());
                }
            }
        }
        return new CaffeineShardCache();
    }),
    EHShardCache(com.test.mark.zhang.test.other.cache.local.shard.EHShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        if (args != null && args.length > 0) {
            return new EHShardCache(args[0].toString());
        }
        return new EHShardCache();
    }),
    LRUMapShardCache(com.test.mark.zhang.test.other.cache.local.map.shard.LRUMapShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize LRUMapShardCache need one parameter at least!");
        if (args.length == 1) {
            return new LRUMapShardCache(args[0].toString());
        }
        return new LRUMapShardCache(args[0].toString(), Integer.parseInt(args[1].toString()));
    }),
    MultiLRUMapShardCache(com.test.mark.zhang.test.other.cache.local.map.shard.MultiLRUMapShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize MultiLRUMapShardCache need one parameter at least!");
        if (args.length == 1) {
            return new MultiLRUMapShardCache(args[0].toString());
        }
        return new MultiLRUMapShardCache(args[0].toString(), Integer.parseInt(args[1].toString()));
    }),
    MultiMapAutoCleanShardCache(com.test.mark.zhang.test.other.cache.local.map.shard.MultiMapAutoCleanShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize MultiMapAutoCleanShardCache need one parameter at least!");
        if (args.length == 1) {
            return new MultiMapAutoCleanShardCache(args[0].toString());
        }
        return new MultiMapAutoCleanShardCache(args[0].toString(), Integer.parseInt(args[1].toString()));
    }),
    MultiMapShardCache(com.test.mark.zhang.test.other.cache.local.map.shard.MultiMapShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize MultiMapShardCache need one parameter at least!");
        return new MultiMapShardCache(args[0].toString());
    }),
    SingleMapAutoCleanShardCache(com.test.mark.zhang.test.other.cache.local.map.shard.SingleMapAutoCleanShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize MultiMapAutoCleanShardCache need one parameter at least!");
        if (args.length == 1) {
            return new SingleMapAutoCleanShardCache(args[0].toString());
        }
        return new SingleMapAutoCleanShardCache(args[0].toString(), Integer.parseInt(args[1].toString()));
    }),
    SingleMapShardCache(com.test.mark.zhang.test.other.cache.local.map.shard.SingleMapShardCache.class, CacheType.LOCAL, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "Initailize MultiMapShardCache need one parameter at least!");
        return new SingleMapShardCache(args[0].toString());
    }),
    /*** Remote ***/
    SingleRedisCache(com.test.mark.zhang.test.other.cache.redis.SingleRedisCache.class, CacheType.REMOTE, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "the RemoteCache need allocate an instance of an implementation");
        @SuppressWarnings("unchecked")
        RedisTemplate<Object, Object> redisTemplate = (RedisTemplate<Object, Object>) beanGetter.apply(args[0].toString());
        return new SingleRedisCache(redisTemplate);
    }),
    // shard cache
    SingleRedisShardCache(com.test.mark.zhang.test.other.cache.redis.shard.SingleRedisShardCache.class, CacheType.REMOTE, (args, beanGetter) -> {
        Asserts.check(args != null && args.length > 0, "the RemoteCache need allocate an instance of an implementation");
        @SuppressWarnings("unchecked")
        RedisTemplate<Object, Object> redisTemplate = (RedisTemplate<Object, Object>) beanGetter.apply(args[0].toString());
        if (args.length == 1) {
            return new SingleRedisShardCache(redisTemplate);
        }
        return new SingleRedisShardCache(redisTemplate, (Shard) beanGetter.apply(args[1].toString()));
    });
    public final Class<?> clazz;
    public final CacheType type;
    private final BiFunction<Object[], Function<String, Object>, Cache<?, ?>> beanCreater;

    private CacheClass(Class<?> clazz, CacheType type, BiFunction<Object[], Function<String, Object>, Cache<?, ?>> beanCreater) {
        this.clazz = clazz;
        this.type = type;
        this.beanCreater = beanCreater;
    }

    /**
     * @param args 仅一个参数且为空字符串时,则认为args为null
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends Cache<?, ?>> T instantiates(Object[] args, Function<String, Object> beanGetter) {
        return (T) beanCreater.apply(args, beanGetter);
    }


}
