package cn.alandelip.logic;

import cn.alandelip.logic.impl.S3Upload;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Alan.Zhufeng Xu, 4/18/2019.
 */
public abstract class TemplateLogic {
    public List<TemplateVO> generateTemplate(ConfigurationVO configuration, Configuration cfg) {
        List<TemplateVO> templates = new ArrayList<>();
        long signedTimestamp = new Date().getTime();
        try {
            Template dfTmpl = loadTemplate(cfg, "/dockerfile-tmpl.ftlh");
            String key = signedTimestamp + "/Dockerfile";
            String envUrl = S3Upload.upload(loadDFConfig(configuration), dfTmpl, key);
            templates.add(new TemplateVO(envUrl, "Dockerfile"));

        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        try {
            Template composeTmpl = loadTemplate(cfg, "/compose-tmpl.ftlh");
            String key = signedTimestamp + "/docker-compose.yml";
            String envUrl = S3Upload.upload(loadDCConfig(configuration), composeTmpl, key);
            templates.add(new TemplateVO(envUrl, "docker-compose"));

        } catch (SdkClientException e) {
            e.printStackTrace();
        }

        return templates;
    }

    protected abstract Template loadTemplate(Configuration cfg, String name);

    protected abstract Map<String, String> loadDFConfig(ConfigurationVO configuration);

    protected abstract Map<String, String> loadDCConfig(ConfigurationVO configuration);

}
