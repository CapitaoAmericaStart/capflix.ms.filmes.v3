package br.com.capflix.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfigurations {

	@Bean
	public Docket portalApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.capflix.controller"))
			.paths(PathSelectors.ant("/**"))
			.build()
			.apiInfo(apiInfo())
			.useDefaultResponseMessages(false)
            .enable(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("API Capflix Cadastro de Filmes")
			.description("CRUD de Filmes com validação")
			.version("1.0")
			.license("Apache License Version 2.0")
			.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
			.contact( new Contact ("Ricardo", null,"ricardo.moreira@capgemini.com"))
			.build();
	}

}
