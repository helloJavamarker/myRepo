package com.test.mark.zhang.test.other.delayQueue.priority;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        if (u1.getNumber().charAt(0) == u2.getNumber().charAt(0)) {
            // 如果两人的号都是A开头或者都是V开头,比较号的大小:
            return u1.getNumber().compareTo(u2.getNumber());
        }
        if (u1.getNumber().charAt(0) == 'V') {
            // u1的号码是V开头,优先级高:
            return -1;
        } else {
            return 1;
        }
    }
}
