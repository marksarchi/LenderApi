//package com.sarchi.lenderapi.config;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import org.springframework.stereotype.Component;
//
//@Component
//@OpenAPIDefinition(
//        info = @Info(title = "lendtech-repayment-api",
//                version = "1.0.0")
//)public class SwaggerConfiguration {
//
////    public static final String AUTHORIZATION_HEADER = "Authorization";
////
////    private ApiKey apiKey() {
////        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
////    }
////
////    private ApiInfo apiInfo() {
////        return new ApiInfo(
////            "Zone service",
////            "Backend service for creation and management of Territories, Sale districts and routes",
////            "1",
////            "Terms of Service",
////            new Contact("Kyosk", "https://github.com/Kyosk-Digital/zone-service", "info@kyosk.com"),
////            "No License",
////            "API License URL",
////            Collections.emptyList()
////        );
////    }
////
////    @Bean
////    public Docket api() {
////        String groupName = "Swagger";
////        return new Docket(DocumentationType.SWAGGER_2)
////            .apiInfo(apiInfo())
////            .groupName(groupName)
////            .securityContexts(List.of(securityContext()))
////            .securitySchemes(List.of(apiKey()))
////            .select()
////            .apis(RequestHandlerSelectors.basePackage("app.kyosk.zone"))
////            .paths(PathSelectors.any())
////            .build();
////    }
////
////    private SecurityContext securityContext() {
////        return SecurityContext.builder().securityReferences(defaultAuth()).build();
////    }
////
////    private List<SecurityReference> defaultAuth() {
////        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////        AuthorizationScope[] authorizationScopesArray = new AuthorizationScope[] { authorizationScope };
////
////        return List.of(new SecurityReference("JWT", authorizationScopesArray));
////    }
//}
