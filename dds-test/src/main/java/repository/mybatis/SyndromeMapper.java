package repository.mybatis;

import java.util.List;

import model.mybatis.Syndrome;

import org.apache.ibatis.annotations.Param;

import repository.CrudMapper;
import cn.smart.dds.DataSource;
import common.annotation.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SyndromeMapper extends CrudMapper<Syndrome, Long> {

	/**
	 * 
	 * @return
	 *
	 * @author wangyuhao
	 * @date 2015年4月25日 下午9:27:40
	 */
//	@DataSource(DataSource.MASTER)
	@DataSource(DataSource.SLAVE)
	List<Syndrome> findAll();
	
	/**
     * 通过症状名字查找
     * @param zzName
     * @return
     */
	@DataSource(DataSource.SLAVE)
    List<Syndrome> findAllByZz(String zzName);

    /**
     * 
     * @param id
     * @return
     *
     * @author wangyuhao
     */
	@DataSource(DataSource.SLAVE)
    Syndrome findNextSyndromeById(@Param("id") Long id);

    /**
     * 
     * @return
     *
     * @author wangyuhao
     */
	@DataSource(DataSource.SLAVE)
    Syndrome findFirstSyndrome();

}