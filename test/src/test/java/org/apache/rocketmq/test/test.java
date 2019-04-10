package org.apache.rocketmq.test;

/**
 * @author rtw
 * @since 2019/3/4
 */
public class test {
    public static void main(String[] args) {

        String str = new String("abc");
        String str2 = str.intern();
        System.out.println(str2);

    }
}
