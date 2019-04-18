package cn.alandelip.logic;

import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import freemarker.template.Configuration;

import java.util.List;

/**
 * @author Alan.Zhufeng Xu, 4/18/2019.
 */
public interface TemplateLogic {
	List<TemplateVO> generateTemplate(ConfigurationVO configuration, Configuration cfg);
}
