package agashchuk.SystemSpecificPackage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

@Component
public class ContexConfigBean implements EmbeddedServletContainerCustomizer {

    @Value("${app.port}")
    private Integer port;

    @Value("${app.context}")
    private String context;

    @Override
    public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
        configurableEmbeddedServletContainer.setPort(port);
        configurableEmbeddedServletContainer.setContextPath(context);
    }
}
