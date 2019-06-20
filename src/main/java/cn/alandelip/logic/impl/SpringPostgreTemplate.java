package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SpringPostgreTemplate extends TemplateLogic {
    private static final String TEMPLATE_BASE = "springboot-postgresql";

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
        dfRoot.put("artifactid", configuration.getArtifactId());
        dfRoot.put("version", configuration.getVersion());
        return dfRoot;
    }

    @Override
    protected Map<String, String> loadDCConfig(ConfigurationVO configuration) {
        Map<String, String> composeRoot = new HashMap<>();
        composeRoot.put("port", configuration.getPort());
        composeRoot.put("dbname", configuration.getDbname());
        composeRoot.put("dbhost", configuration.getDbhost());
        composeRoot.put("dbuser", configuration.getDbuser());
        composeRoot.put("dbpass", configuration.getDbpassword());
        composeRoot.put("dbport", configuration.getDbport());
        return composeRoot;
    }
}
