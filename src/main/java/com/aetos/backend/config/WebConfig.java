package com.aetos.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String base = System.getenv("UPLOADS_DIR");
        if (base == null || base.isBlank()) base = System.getenv("RESOURCES_DIR");
        if (base == null || base.isBlank()) base = Paths.get(System.getProperty("user.home"), "aetos-uploads").toAbsolutePath().toString();
        String normalized = base.endsWith("/") ? base : base + "/";
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + normalized);
        registry.addResourceHandler("/api/uploads/**")
                .addResourceLocations("file:" + normalized);
        System.out.println("✅ Directorio de uploads configurado en: " + base);
    }
}
