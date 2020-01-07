package com.guineap_pig_329.guinea_pig;

import com.guineap_pig_329.guinea_pig.dao.wrapper.Cen;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication

public class GuineaPigApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuineaPigApplication.class, args);
        Util.CEN = (Cen) Util.getCenInstance(Util.PATH, Cen.class);
    }


    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @Bean
    public TomcatServletWebServerFactory tomcatEmbedded(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {

            ((AbstractHttp11Protocol<?>)connector.getProtocolHandler()).setMaxSwallowSize(-1);

        });

        return tomcat;
    }
}
