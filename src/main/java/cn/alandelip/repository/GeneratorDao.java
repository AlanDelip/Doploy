package cn.alandelip.repository;

import cn.alandelip.entity.GeneratorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Alan on 2017/3/14
 */
public interface GeneratorDao extends JpaRepository<GeneratorData, Long> {
}
