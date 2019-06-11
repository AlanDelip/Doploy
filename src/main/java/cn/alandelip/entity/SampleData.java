package cn.alandelip.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

/**
 * @author Alan on 2017/3/14
 */
@Data
public class SampleData {
    @NotNull
    private ObjectId _id;

    @Id
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String detail;

}
