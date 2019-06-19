package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlaskMongoTemplate extends TemplateLogic {
    private static final String TEMPLATE_BASE = "flask-mongodb";

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
        dfRoot.put("dependencies", configuration.getDependencies());
        return dfRoot;
    }

    @Override
    protected Map<String, String> loadDCConfig(ConfigurationVO configuration) {
        Map<String, String> composeRoot = new HashMap<>();
        composeRoot.put("dbname", configuration.getDbname());
        composeRoot.put("dbport", configuration.getDbport());
        composeRoot.put("port", configuration.getPort());
        composeRoot.put("dbhost", configuration.getDbhost());
        return composeRoot;
    }
}
