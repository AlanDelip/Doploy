package cn.alandelip;

import cn.alandelip.logic.GeneratorLogic;
import cn.alandelip.logic.impl.GeneratorLogicImpl;
import cn.alandelip.web.model.ConfigurationVO;
import cn.alandelip.web.model.TemplateVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

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
	public void testFlaskPostgre() {
		ConfigurationVO configuration = new ConfigurationVO();
		configuration.setType("FLASK_POSTGRESQL");
		configuration.setEntry("entry");
		configuration.setPort("8000");
		configuration.setDbname("testdb");
		configuration.setDbpassword("12345");
		configuration.setDbuser("root");
		configuration.setDbport("123");
//		configuration.setDependencies("numpy pandas");
		List<TemplateVO> templates = generatorLogic.generateTemplate(configuration);
		System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(templates));
	}

	@Test
	public void testExpressMongo() {
		ConfigurationVO configuration = new ConfigurationVO();
		configuration.setType("EXPRESS_MONGODB");
		configuration.setEntry("entry");
		configuration.setPort("8000");
		configuration.setDbname("testdb");
		configuration.setDbport("123");
		List<TemplateVO> templates = generatorLogic.generateTemplate(configuration);
		System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(templates));
	}

	@Test
	public void testSpringMySQL() {
		ConfigurationVO configuration = new ConfigurationVO();
		configuration.setType("SPRINGBOOT_MYSQL");
		configuration.setArtifactId("springboot");
		configuration.setVersion("LATEST");
		configuration.setPort("8000");
		configuration.setDbname("testdb");
		configuration.setDbport("3306");
//		configuration.setDbuser("doploy");
//		configuration.setDbpassword("doploy");
		configuration.setDbrootpass("root");
		List<TemplateVO> templates = generatorLogic.generateTemplate(configuration);
		System.out.println(new GsonBuilder().disableHtmlEscaping().create().toJson(templates));
	}
}
