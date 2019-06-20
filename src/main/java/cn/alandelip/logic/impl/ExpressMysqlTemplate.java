package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExpressMysqlTemplate extends TemplateLogic {
    private static final String TEMPLATE_BASE = "express-mysql";

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
        composeRoot.put("dbuser", configuration.getDbuser());
        composeRoot.put("dbpass", configuration.getDbpassword());
        composeRoot.put("dbrootpass", configuration.getDbrootpass());
        composeRoot.put("dbport", configuration.getDbport());
        composeRoot.put("dbhost", configuration.getDbhost());
        composeRoot.put("port", configuration.getPort());
        composeRoot.put("dbinitentry",configuration.getDbinitentry());
        return composeRoot;
    }
}
