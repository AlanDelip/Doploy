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
	String dbuser;
	String dbname;
	String dbpassword;
	String type;
	String workdir;
	String entry;
	String port;
}
