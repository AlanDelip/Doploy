package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.Map;

public class SpringMongoTemplate extends TemplateLogic {
    private static final String TEMPLATE_BASE = "spring-mongodb";

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
        return null;
    }

    @Override
    protected Map<String, String> loadDCConfig(ConfigurationVO configuration) {
        return null;
    }
}
