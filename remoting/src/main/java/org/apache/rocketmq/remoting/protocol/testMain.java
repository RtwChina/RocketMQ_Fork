package org.apache.rocketmq.remoting.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rtw
 * @since 2019/4/3
 */
public class testMain {
    public static void main(String[] args) {
        Map map = new HashMap();
    }



    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
