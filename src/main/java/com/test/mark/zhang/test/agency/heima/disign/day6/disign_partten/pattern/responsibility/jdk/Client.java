package com.test.mark.zhang.test.agency.heima.disign.day6.disign_partten.pattern.responsibility.jdk;

/**
 * @version v1.0
 * @ClassName: Client
 * @Description: TODO(一句话描述该类的功能)
 * @Author: 黑马程序员
 */
public class Client {
    public static void main(String[] args) {
        Request  req = null;
        Response res = null ;

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new FirstFilter()).addFilter(new SecondFilter());
        filterChain.doFilter(req,res);
    }
}
