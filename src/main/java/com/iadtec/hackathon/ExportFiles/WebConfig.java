package com.iadtec.hackathon.ExportFiles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON).favorPathExtension(true);
    }


    @Bean
    public ViewResolver contentNegotiatingViewResolver (ContentNegotiationManager manager){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        List<ViewResolver> resolvers = new ArrayList<>();

        resolvers.add(pdfViewResolver());
        resolvers.add(excelViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;
    }

    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }


    @Bean
    public ViewResolver pdfViewResolver() {
        return new PdfViewResolver();
    }
}
