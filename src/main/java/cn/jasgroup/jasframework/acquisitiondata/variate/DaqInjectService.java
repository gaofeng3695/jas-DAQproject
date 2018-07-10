package cn.jasgroup.jasframework.acquisitiondata.variate;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.base.data.BaseData;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
public class DaqInjectService {
	
	public void injectCurrentUnitId(BaseData baseData){
		baseData.setValue("current_unit_id", ThreadLocalHolder.getCurrentUser().getUnitId());
	}
	
}
