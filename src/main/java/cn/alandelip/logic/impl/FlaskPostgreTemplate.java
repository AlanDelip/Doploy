package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import com.google.gson.GsonBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import software.amazon.awssdk.core.exception.SdkClientException;

import java.io.IOException;
import java.util.*;

/**
 * @author Alan.Zhufeng Xu, 4/18/2019.
 */
public class FlaskPostgreTemplate implements TemplateLogic {
	@Override
	public List<TemplateVO> generateTemplate(ConfigurationVO configuration, Configuration cfg) {
		List<TemplateVO> templates = new ArrayList<>();
		long signedTimestamp = new Date().getTime();
		try {
			Template dfTmpl = cfg.getTemplate("flask-postgresql-dockerfile-tmpl.ftlh");
			Map<String, String> dfRoot = new HashMap<>();
			dfRoot.put("workdir", configuration.getWorkdir());
			dfRoot.put("port", configuration.getPort());
			dfRoot.put("entry", configuration.getEntry());
			String key = signedTimestamp + "/Dockerfile";

			String envUrl = S3Upload.upload(dfRoot, dfTmpl, key);
			templates.add(new TemplateVO(envUrl, "Dockerfile"));

		} catch (IOException | SdkClientException e) {
			e.printStackTrace();
		}

		try {
			Template envTmpl = cfg.getTemplate("flask-postgresql-env-tmpl.ftlh");
			Map<String, String> envRoot = new HashMap<>();
			envRoot.put("dbname", configuration.getDbname());
			envRoot.put("dbuser", configuration.getDbuser());
			envRoot.put("dbpassword", configuration.getDbpassword());
			String key = signedTimestamp + "/env";

			String envUrl = S3Upload.upload(envRoot, envTmpl, key);
			templates.add(new TemplateVO(envUrl, "env"));

		} catch (IOException | SdkClientException e) {
			e.printStackTrace();
		}

		try {
			Template composeTmpl = cfg.getTemplate("flask-postgresql-compose-tmpl.ftlh");
			Map<String, String> composeRoot = new HashMap<>();
			composeRoot.put("workdir", configuration.getWorkdir());
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
