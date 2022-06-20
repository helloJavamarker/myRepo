package com.test.mark.zhang.test.agency.heima.man.interview.day04.tx.other;

public interface ServiceC {

    //    @Transactional(rollbackFor = Exception.class)
    public void transfer(int from, int to, int amount);
}
