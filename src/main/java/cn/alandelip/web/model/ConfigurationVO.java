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
	// mysql root password
	String dbrootpass;

	// framework configurations
	String entry;
	String port;

	// maven project configurations
	String artifactId;
	String version;

}
