package cn.alandelip.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Alan on 2017/3/14
 */
@Data
@Entity
@Table(name = "generator")

public class GeneratorData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String detail;

    @NotNull
    private Type type;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    public enum Type {
        SPRINGBOOT_MYSQL, SPRINGBOOT_POSTGRESQL, SPRINGBOOT_MONGODB,
        EXPRESS_MYSQL, EXPRESS_POSTGRESQL, EXPRESS_MONGODB,
        FLASK_MYSQL, FLASK_POSTGRESQL, FLASK_MONGODB
    }

}
