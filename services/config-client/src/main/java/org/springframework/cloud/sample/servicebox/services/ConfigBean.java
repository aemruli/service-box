package org.springframework.cloud.sample.servicebox.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author Agim Emruli
 */
@Component
@RefreshScope
public class ConfigBean {

    @Value("${info.description}")
    private String description;

    @Value("${info.url}")
    private String url;

    @Value("${foo}")
    private String foo;

    @Value("${bar:}")
    private String bar;

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfigBean{");
        sb.append("description='").append(description).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", foo='").append(foo).append('\'');
        sb.append(", bar='").append(bar).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
