package cn.alandelip.logic.impl;

import cn.alandelip.entity.GeneratorData;
import cn.alandelip.logic.GeneratorLogic;
import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Alan on 2017/3/14
 */
@Service
@Log4j2
public class GeneratorLogicImpl implements GeneratorLogic {
	private Configuration cfg;
	private TemplateLogic templateLogic;

	@Autowired
	public GeneratorLogicImpl() {
		try {
			cfg = new Configuration(Configuration.VERSION_2_3_27);
			cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<TemplateVO> generateTemplate(ConfigurationVO configuration) {
		switch (GeneratorData.Type.valueOf(configuration.getType().toUpperCase())) {
			case FLASK_POSTGRESQL:
				templateLogic = new FlaskPostgreTemplate();
				break;
			case EXPRESS_MONGO:
				templateLogic = new ExpressMongoTemplate();
				break;
			case SPRING_MYSQL:
				break;
		}

		return templateLogic.generateTemplate(configuration, cfg);
	}
}
