package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.io.IOException;
import java.util.*;

/**
 * @author Alan.Zhufeng Xu, 4/19/2019.
 */
public class ExpressMongoTemplate extends TemplateLogic {
    private static final String TEMPLATE_BASE = "express-mongodb";

    @Override
    protected Template loadTemplate(Configuration cfg, String name) {

        try {
            return cfg.getTemplate(TEMPLATE_BASE + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Map<String, String> loadDFConfig(ConfigurationVO configuration) {
        Map<String, String> dfRoot = new HashMap<>();
        dfRoot.put("entry", configuration.getEntry());
        return dfRoot;
    }

    @Override
    protected Map<String, String> loadDCConfig(ConfigurationVO configuration) {
        Map<String, String> composeRoot = new HashMap<>();
        composeRoot.put("dbname", configuration.getDbname());
        composeRoot.put("dbport", configuration.getDbport());
        composeRoot.put("port", configuration.getPort());
        return composeRoot;
    }

}
