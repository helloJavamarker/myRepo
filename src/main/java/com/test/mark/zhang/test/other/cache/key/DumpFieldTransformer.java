package com.test.mark.zhang.test.other.cache.key;

//import com.lz.components.common.beanutil.BeanUtils;

public class DumpFieldTransformer implements CacheKeyTransformer {
    @Override
    public Integer make(Object original) {
//        String dumpInfo = BeanUtils.dump(original);
//        return dumpInfo == null ? 0 : Math.abs(dumpInfo.hashCode());
        return null;
    }

}
