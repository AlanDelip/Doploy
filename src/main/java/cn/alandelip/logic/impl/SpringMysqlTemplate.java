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
 * @author Alan.Zhufeng Xu, 4/20/2019.
 */
public class SpringMysqlTemplate extends TemplateLogic {
    private static final String TEMPLATE_BASE = "springboot-mysql";


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
        composeRoot.put("dbname", configuration.getDbname());
        composeRoot.put("dbuser", configuration.getDbuser());
        composeRoot.put("dbpass", configuration.getDbpassword());
        composeRoot.put("dbrootpass", configuration.getDbrootpass());
        composeRoot.put("dbport", configuration.getDbport());
        composeRoot.put("dbhost", configuration.getDbhost());
        composeRoot.put("port", configuration.getPort());
        return composeRoot;
    }
}
