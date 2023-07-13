package com.test.microservice.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig
{
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKeys ()
    {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    public SecurityScheme oauth ()
    {
        return new OAuth("JWT", Arrays.asList(new AuthorizationScope("global", "accessEverything")), null);
    }

    private List<SecurityContext> securityContexts ()
    {
        return Arrays.asList(
            SecurityContext.builder().securityReferences(securityReferences()).build());
    }
    
    private List<SecurityReference> securityReferences ()
    {
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        return Arrays.asList(
            new SecurityReference("JWT", new AuthorizationScope[] { scope }));
    }

    @Bean
    public Docket api ()
    {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(
            getInfo()).securityContexts(securityContexts()).securitySchemes(
                Arrays.asList(apiKeys())).select().apis(
                    RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }    

    @GetMapping("/")
    public ApiInfo getInfo ()
    {
        System.out.println("Getting Customers");
        return new ApiInfo("titile","description","version","termsOfServiceUrl",new Contact("name", "1", "123"),"license","licenseUrl",Collections.emptyList());
    }
}
