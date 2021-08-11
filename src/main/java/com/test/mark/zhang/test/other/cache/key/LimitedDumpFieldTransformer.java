package com.test.mark.zhang.test.other.cache.key;

//import com.lz.components.common.beanutil.BeanUtils;

/**
 * 将Object所有对象属性dump成String,再做hash计算,然后求模
 *
 * @author bailey
 * @version 1.0
 * @date 2017-06-13 13:58
 */
public class LimitedDumpFieldTransformer extends LimitedTransformer {

    public LimitedDumpFieldTransformer() {
        super();
    }

    public LimitedDumpFieldTransformer(int limit) {
        super(limit);
    }

    @Override
    protected int assess(Object original) {
        //String dumpInfo = BeanUtils.dump(original);
        String dumpInfo = "BeanUtils.dump(original)";
        if (dumpInfo == null) {
            return 0;
        }
        int h = 0;
        return (h = dumpInfo.hashCode()) ^ (h >>> 16);
    }
}
