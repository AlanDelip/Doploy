package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;

import java.util.List;

public class FlaskMysqlTemplate implements TemplateLogic {
    private static final String TEMPLATE_BASE = "flask-mysql";

    @Override
    public List<TemplateVO> generateTemplate(ConfigurationVO configuration, Configuration cfg) {
        return null;
    }
}
