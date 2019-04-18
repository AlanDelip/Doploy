package cn.alandelip;

import cn.alandelip.logic.GeneratorLogic;
import cn.alandelip.logic.impl.GeneratorLogicImpl;
import cn.alandelip.web.model.ConfigurationVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Alan.Zhufeng Xu, 4/17/2019.
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@SpringBootTest
public class TemplateTest {
	@InjectMocks
	private GeneratorLogic generatorLogic = new GeneratorLogicImpl();

	@Before
	public void setupMockMvc() {
		initMocks(this);
	}

	@Test
	public void test() {
		ConfigurationVO configuration = new ConfigurationVO();
		configuration.setEntry("entry");
		configuration.setWorkdir("workdir");
		configuration.setPort("8000");
		configuration.setType("FLASK_POSTGRESQL");
		configuration.setDbname("sample");
		configuration.setDbpassword("12345");
		configuration.setDbuser("root");
		generatorLogic.generateTemplate(configuration);
	}
}
