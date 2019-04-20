package cn.alandelip.logic.impl;

import cn.alandelip.logic.TemplateLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;

import java.util.List;

/**
 * @author Alan.Zhufeng Xu, 4/20/2019.
 */
public class SpringMysqlTemplate implements TemplateLogic {
	private static final String TEMPLATE_BASE = "springboot-mysql";

	@Override
	public List<TemplateVO> generateTemplate(ConfigurationVO configuration, Configuration cfg) {
		return null;
	}
}
