package cn.alandelip.web.ctrl;

import cn.alandelip.logic.GeneratorLogic;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller, handling CURD(create, update, read, delete) operations.
 *
 * @author Alan on 2017/3/14
 */
@RestController
@Log4j2
public class GeneratorCtrl {

	private GeneratorLogic generatorLogic;

	@Autowired
	public GeneratorCtrl(GeneratorLogic generatorLogic) {
		this.generatorLogic = generatorLogic;
	}

	public List<TemplateVO> generateTemplate(ConfigurationVO configuration) {
		return generatorLogic.generateTemplate(configuration);
	}
}
