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
public class ExpressMongoTemplate implements TemplateLogic {
	private static final String TEMPLATE_BASE = "express-mongodb";

	@Override
	public List<TemplateVO> generateTemplate(ConfigurationVO configuration, Configuration cfg) {
		List<TemplateVO> templates = new ArrayList<>();
		long signedTimestamp = new Date().getTime();
		try {
			Template dfTmpl = cfg.getTemplate(TEMPLATE_BASE + "/dockerfile-tmpl.ftlh");
			Map<String, String> dfRoot = new HashMap<>();
			dfRoot.put("entry", configuration.getEntry());
			String key = signedTimestamp + "/Dockerfile";

			String envUrl = S3Upload.upload(dfRoot, dfTmpl, key);
			templates.add(new TemplateVO(envUrl, "Dockerfile"));

		} catch (IOException | SdkClientException e) {
			e.printStackTrace();
		}

		try {
			Template composeTmpl = cfg.getTemplate(TEMPLATE_BASE + "/compose-tmpl.ftlh");
			Map<String, String> composeRoot = new HashMap<>();
			composeRoot.put("dbname", configuration.getDbname());
			composeRoot.put("dbport", configuration.getDbport());
			composeRoot.put("port", configuration.getPort());
			String key = signedTimestamp + "/docker-compose.yml";

			String envUrl = S3Upload.upload(composeRoot, composeTmpl, key);
			templates.add(new TemplateVO(envUrl, "docker-compose"));

		} catch (IOException | SdkClientException e) {
			e.printStackTrace();
		}

		return templates;
	}

}
