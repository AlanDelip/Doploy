package cn.alandelip.repository;

import cn.alandelip.entity.SampleData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Alan on 2017/3/14
 */
public interface SampleDao extends MongoRepository<SampleData, Long> {
    SampleData findByName(String name);

    SampleData findByNameAndDetail(String name, String detail);
}
