package com.app.pizzaorderingsystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/pos/customer_home").setViewName("customer_home");
        registry.addViewController("/pos/admin_home").setViewName("admin_home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path pizzaUploadDir = Paths.get("./pizza-images");
        String pizzaUploadPath = pizzaUploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/pizza-images/**").addResourceLocations("file:/" + pizzaUploadPath + "/");
    }
}
