package cn.com.zhshzh.core.swagger;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Predicates.not;
import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;
import static springfox.documentation.builders.RequestHandlerSelectors.withMethodAnnotation;

/**
 * Swagger配置文件
 * 该类依赖了google的guava组件和springfox.documentation组件
 *
 * @author WBT
 * @since 2019/10/16
 **/
@Configuration
@EnableSwagger2
public class MySwagger2Config {

    @Value("${swagger.name}")
    private String name;
    @Value("${swagger.url}")
    private String url;
    @Value("${swagger.email}")
    private String email;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.version}")
    private String version;

    /**
     * 组织Docket对象，翻译过来就是摘要的意思，它是生成API文档的核心对象，里面配置一些必要的信息
     *
     * @return 摘要对象
     */
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        //指定规范，这里是SWAGGER_2
        return new Docket(DocumentationType.SWAGGER_2)
                //设定Api文档头信息，这个信息会展示在文档UI的头部位置
                .apiInfo(getApiInfo())
                .select()
                //添加过滤条件，谓词过滤predicate，这里是自定义注解进行过滤
                .apis(not(withMethodAnnotation(SwaggerCustomIgnore.class)))
                //配置匹配规则(也可以在类上用@ComponentScan扫描包进行匹配)
                .paths(this.allowPaths())
                .build()
                .globalOperationParameters(globalOperation());
    }

    /**
     * 添加token认证
     * @return
     */
    private List<Parameter> globalOperation() {
        ParameterBuilder tokenParameter = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        tokenParameter.name("Authorization").description("Token").modelRef(new ModelRef("String")).parameterType("header").required(true).build();
        parameters.add(tokenParameter.build());
        return parameters;
    }

    /**
     * 自定义API文档基本信息实体
     *
     * @return UI界面显示的内容
     */
    private ApiInfo getApiInfo() {
        //构建联系实体，在UI界面会显示
        Contact contact = new Contact(this.name, this.url, this.email);
        return new ApiInfoBuilder()
                .contact(contact)
                //文档标题
                .title(this.title)
                //文档描述
                .description(this.description)
                //文档版本
                .version(this.version)
                .build();
    }

    /**
     * path匹配规则，配置要生成swagger API的路径
     *
     * @return 生成swagger API的路径
     */
    private Predicate<String> allowPaths() {
        return or(
                regex("/userInfo.*"),
                regex("/menuInfos.*"),
                regex("/params.*")
        );
    }
}
