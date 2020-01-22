package com.monitoring.energy;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class EnergyMonitorSwaggerConfiguration {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(api())
                .paths(PathSelectors.any())
                .build();
               /* .globalOperationParameters(parameters);*/
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Energy Monitoring coding API Doc.")
                .description("More about the API.")
                .version("1.0")
                .build();
    }

    private Predicate<RequestHandler> api() {
        return Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot"));
    }
}
