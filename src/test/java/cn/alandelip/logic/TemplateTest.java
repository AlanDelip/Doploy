package cn.alandelip.logic;

import cn.alandelip.logic.impl.ExpressMongoTemplate;
import cn.alandelip.logic.impl.ExpressPostgreTemplate;
import cn.alandelip.logic.impl.FlaskPostgreTemplate;
import cn.alandelip.logic.impl.SpringMysqlTemplate;
import cn.alandelip.web.model.ConfigurationVO;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author Alan.Zhufeng Xu, 4/17/2019.
 */
public class TemplateTest {
    private final String dfPath = "/dockerfile-tmpl.ftlh", dcPath = "/compose-tmpl.ftlh";
    private Configuration cfg;
    private ConfigurationVO configuration;

    @Before
    public void loadConfig() {
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

        configuration = new ConfigurationVO();
        configuration.setType("TYPE");
        configuration.setDbuser("root");
        configuration.setDbname("testdb");
        configuration.setDbpassword("testpass");
        configuration.setDbport("6000");
        configuration.setDbhost("host");
        configuration.setDbinitentry("dbentry");
        configuration.setDbrootpass("rootpass");

        configuration.setEntry("entry");
        configuration.setPort("8000");
        configuration.setDependencies("numpy pandas");

        configuration.setArtifactId("artifact");
        configuration.setVersion("1.0");
    }

    @Test
    public void testFlaskPostgre() {
        TemplateLogic template = new FlaskPostgreTemplate();

        generateTmpl(
                template.loadTemplate(cfg, dfPath),
                template.loadTemplate(cfg, dcPath),
                template.loadDFConfig(configuration),
                template.loadDCConfig(configuration));
    }

    @Test
    public void testExpressPostgre() {
        TemplateLogic template = new ExpressPostgreTemplate();

        generateTmpl(
                template.loadTemplate(cfg, dfPath),
                template.loadTemplate(cfg, dcPath),
                template.loadDFConfig(configuration),
                template.loadDCConfig(configuration));

    }

    @Test
    public void testExpressMongo() {
        TemplateLogic template = new ExpressMongoTemplate();

        generateTmpl(
                template.loadTemplate(cfg, dfPath),
                template.loadTemplate(cfg, dcPath),
                template.loadDFConfig(configuration),
                template.loadDCConfig(configuration));
    }

    @Test
    public void testSpringMySQL() {
        TemplateLogic template = new SpringMysqlTemplate();

        generateTmpl(
                template.loadTemplate(cfg, dfPath),
                template.loadTemplate(cfg, dcPath),
                template.loadDFConfig(configuration),
                template.loadDCConfig(configuration));
    }

    private void generateTmpl(Template dfTmpl, Template dcTmpl, Map<String, String> dfRoot, Map<String, String> dcRoot) {
        Writer out = new OutputStreamWriter(System.out);
        try {
            System.out.println(dfTmpl.getName());
            dfTmpl.process(dfRoot, out);
            System.out.println("\n");

            System.out.println(dcTmpl.getName());
            dcTmpl.process(dcRoot, out);
            System.out.println("\n");

        } catch (TemplateException | IOException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
