package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.util.Map;

public class ExpressMysqlTemplate extends TemplateLogic {
    @Override
    protected Template loadTemplate(Configuration cfg, String name) {
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
