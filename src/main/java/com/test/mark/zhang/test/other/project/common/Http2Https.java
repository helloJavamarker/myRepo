package com.test.mark.zhang.test.other.project.common;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by mark
 * @Classname Http2Https
 * @Description TODO
 * @Date 2022/1/6 5:02 下午
 */
@Configuration
public class Http2Https {

    //https://www.jianshu.com/p/ae4b8a2debbd

//    @Value("${http.port:8088}")
//    private int httpPort;
//    @Value("${server.port}")
//    private int httpsPort;
//
//    @Bean
//    public TomcatServletWebServerFactory servletWebServerFactory() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addMethod("/ws/cmccWebService/*");
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
//        return tomcat;
//    }
//
//    private Connector createHttpConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setSecure(false);
//        connector.setPort(httpPort);
//        connector.setRedirectPort(httpsPort);
//        return null;
//    }
}
