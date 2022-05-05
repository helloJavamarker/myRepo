package com.test.mark.zhang.test.other.project.v5.event.subscribe;

public class Client {


    /**
     * 有一个天气预报系统，负责通知明天的天气情况。有几个角色订阅了该系统，他们根据明天的天气情况安排明天的事务。这几个角色分别为农夫、建筑工人、程序员。
     * 下雨天
     * 农夫喜出望外：又可以好好歇一天了，让庄稼庄稼好好成长吧；
     * 建筑工人欣喜万分：完美，睡到天昏地暗；
     * 程序员：继续上班，关我毛事；
     * 日晴万里
     * 农夫感慨：即将迎接充实的一天，收获满满；
     * 建筑工人牢骚：这么大太阳，苦逼的一天；
     * 程序员：继续上班，关我毛事；
     * <p>
     * 一个发布者,多个订阅者,订阅者可以做不同的动作
     *
     * @param args
     */
    public static void main(String[] args) {

        Farmer farmer = new Farmer();
        Worker worker = new Worker();
        Programmer programmer = new Programmer();

        WeatherServer weatherServer = new WeatherServer();

        weatherServer.addSubscriber(farmer);
        weatherServer.addSubscriber(worker);
        weatherServer.addSubscriber(programmer);

        weatherServer.publishInfo("rain");
    }
}

