package com.application.asseco.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.application.asseco.config.security.SecurityConstants.AUTHORIZATION_HEADER;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket swaggerApiAll() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("all")
        .select()
        .paths(Predicates.and(PathSelectors.any(), excludeApi()))
        .build()
        .apiInfo(new ApiInfoBuilder()
            .version("1.0.0")
            .title("Asseco Application APIs")
            .description("Swagger documentation for Asseco Application module's API")
            .build())
          .securitySchemes(Lists.newArrayList(apiKey()))
          .securityContexts(Lists.newArrayList(securityContext()));
  }

  private Predicate<String> excludeApi() {
    return Predicates.not(PathSelectors.regex("/error"));
  }

  private ApiKey apiKey() {
    return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
            .securityReferences(securityReferences())
            .build();
  }

  private List<SecurityReference> securityReferences() {
    SecurityReference securityReference = SecurityReference.builder()
            .reference("JWT")
            .scopes(
                    new AuthorizationScope[]{
                            new AuthorizationScopeBuilder().scope("global").build()
                    })
            .build();
    return Lists.newArrayList(securityReference);
  }
}
