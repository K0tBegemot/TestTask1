package com.kotbegemot.testtask1.api.openapi;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ObjectSchema;
import org.springdoc.core.converters.AdditionalModelsConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class to configure OpenAPI documentation
 * Change ModelAndView object representation in Swagger documentation to simple object to hide it real structure.
 * Necessary because the object is used for redirection
 */
@Configuration
public class OpenAPIConfiguration
{
    @Value("${api.common.version}")
    String apiVersion;
    @Value("${api.common.title}")
    String apiTitle;
    @Value("${api.common.description}")
    String apiDescription;
    @Value("${api.common.externalDocDesc}")
    String apiExternalDocDesc;
    @Value("${api.common.externalDocUrl}")
    String apiExternalDocUrl;
    @Value("${api.common.contact.name}")
    String apiContactName;
    @Value("${api.common.contact.url}")
    String apiContactUrl;
    @Value("${api.common.contact.email}")
    String apiContactEmail;
    @Bean
    public OpenAPI getOpenApiDocumentation() {
        AdditionalModelsConverter.replaceWithSchema(ModelAndView.class, new ObjectSchema());
        return new OpenAPI()
                .info(new Info().title(apiTitle)
                        .description(apiDescription)
                        .version(apiVersion)
                        .contact(new Contact()
                                .name(apiContactName)
                                .url(apiContactUrl)
                                .email(apiContactEmail)))
                .externalDocs(new ExternalDocumentation()
                        .description(apiExternalDocDesc)
                        .url(apiExternalDocUrl));
    }
}