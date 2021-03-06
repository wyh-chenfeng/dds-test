package service.syndrome_element;

import java.util.List;

import model.mybatis.SyndromeElement;
import service.CrudService;

public interface SyndromeElementService extends CrudService<SyndromeElement, Long> {

	/**
	 * 
	 * @param syndromeElementInput
	 *
	 * @author wangyuhao
	 * @date 2015年4月27日 下午10:26:19
	 */
	void create(SyndromeElementInput syndromeElementInput);

	/**
	 * 
	 * @return
	 *
	 * @author wangyuhao
	 * @date 2015年4月27日 下午10:29:39
	 */
	List<SyndromeElement> findAll();
	
	/**
	 * 查找2个证素之间的关系
	 * @param zs
	 * @return
	 */
	List<SyndromeElement> findRelateByZs(SyndromeElement zs);

}
