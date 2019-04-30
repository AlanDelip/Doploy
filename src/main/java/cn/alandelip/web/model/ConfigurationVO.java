package cn.alandelip.web.model;

import lombok.Data;

/**
 * This VO contains configurations from users' selections
 * Help generate the script template
 *
 * @author Alan.Zhufeng Xu, 4/17/2019.
 */
@Data
public class ConfigurationVO {
	String type;

	// database configurations
	String dbuser;
	String dbname;
	String dbpassword;
	String dbport;
	String dbhost;
	// mysql root password
	String dbrootpass;

	// framework configurations
	String entry;
	String port;
	// python dependencies(since python's dependencies are not defined in a exterior file, we have to install them in prior)
	String dependencies;

	// maven project configurations
	String artifactId;
	String version;

}
