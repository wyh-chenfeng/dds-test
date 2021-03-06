package service.syndrome_element;

import java.util.List;

import javax.annotation.Resource;

import model.mybatis.SyndromeElement;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.mybatis.SyndromeElementMapper;
import service.CrudServiceImpl;

@Service
@Transactional(readOnly = true)
public class SyndromeElementServiceImpl extends
		CrudServiceImpl<SyndromeElement, Long, SyndromeElementMapper> implements
		SyndromeElementService {

	@Resource
	@Override
	public void setRepository(SyndromeElementMapper syndromeElementMapper) {

	    super.setRepository(syndromeElementMapper);
	}

	@Override
	@Transactional
	public void create(SyndromeElementInput syndromeElementInput) {
		SyndromeElement syndromeElement = new SyndromeElement();
		BeanUtils.copyProperties(syndromeElementInput, syndromeElement);
		repository.insertSelective(syndromeElement);
	}

	@Override
	public List<SyndromeElement> findAll() {
		return repository.findAll();
	}

	@Override
	public List<SyndromeElement> findRelateByZs(SyndromeElement zs) {
		return repository.findRelateByZs(zs);
	}

}
