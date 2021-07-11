/**
 * 
 */
package com.java.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Madushan Lahiru
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";

	private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("JWT Authentiation & Autherization REST API")
                .description("REST API to demonstrate Authentication and Autheriazation using JSON Web Token.")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

	  @Bean
	  public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .apiInfo(metaData())
	        .securityContexts(Arrays.asList(securityContext()))
	        .securitySchemes(Arrays.asList(apiKey()))
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.java.controller"))
	        .paths(PathSelectors.any())
	        .build();
	  }

	  private ApiKey apiKey() {
	    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	  }

	  private SecurityContext securityContext() {
	    return SecurityContext.builder()
	        .securityReferences(defaultAuth())
	        .build();
	  }

	  private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	  }
	      
}
