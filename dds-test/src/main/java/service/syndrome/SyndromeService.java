package service.syndrome;

import java.util.List;
import java.util.Map;

import cn.smart.dds.DataSource;

import model.mybatis.Syndrome;
import service.CrudService;

public interface SyndromeService extends CrudService<Syndrome, Long> {

	/**
	 * 
	 * @param syndromeCreateInput
	 *
	 * @author wangyuhao
	 * @date 2015年4月25日 下午9:26:01
	 */
	@DataSource(DataSource.MASTER)
    void create(SyndromeCreateInput syndromeCreateInput);

    /**
     * 
     * @return
     *
     * @author wangyuhao
     * @date 2015年4月25日 下午9:25:54
     */
    List<Map<String, Object>> findSyndromeInitData();

    /**
     * 
     * @return
     *
     * @author wangyuhao
     */
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
     * @param syndromeUpdateInput
     * @return
     *
     * @author wangyuhao
     */
    @DataSource(DataSource.SLAVE)
    Syndrome update(SyndromeCreateInput syndromeUpdateInput);

	void test(Syndrome syndrome);
}
