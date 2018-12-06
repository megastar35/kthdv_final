package com.fsoft.fa.interviewprocessmanagement;

import com.fsoft.fa.interviewprocessmanagement.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class InterviewProcessManagementApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(InterviewProcessManagementApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(InterviewProcessManagementApplication.class, args);
    }
}
