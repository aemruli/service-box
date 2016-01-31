package org.springframework.cloud.sample.servicebox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Agim Emruli
 */
@RestController
public class ConfigController {

    @Autowired
    private ConfigBean configBean;

    @RequestMapping("/config/dump")
    public String getConfigBean() {
        return configBean.toString();
    }
}
