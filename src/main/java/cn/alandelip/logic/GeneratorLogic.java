package cn.alandelip.logic;

import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;

import java.util.List;

/**
 * @author Alan on 2017/3/14
 */
public interface GeneratorLogic {
	List<TemplateVO> generateTemplate(ConfigurationVO configuration);
}
