package repository.mybatis;

import java.util.List;

import model.mybatis.SyndromeElement;
import repository.CrudMapper;
import cn.smart.dds.DataSource;
import common.annotation.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SyndromeElementMapper extends CrudMapper<SyndromeElement, Long> {

	/**
	 * 
	 * @return
	 *
	 * @author wangyuhao
	 * @date 2015年4月27日 下午10:30:13
	 */
	@DataSource(DataSource.SLAVE)
	List<SyndromeElement> findAll();
	
	/**
	 * 查找2个证素之间的关系
	 * @param zs
	 * @return
	 */
	@DataSource(DataSource.SLAVE)
	List<SyndromeElement> findRelateByZs(SyndromeElement zs);

}