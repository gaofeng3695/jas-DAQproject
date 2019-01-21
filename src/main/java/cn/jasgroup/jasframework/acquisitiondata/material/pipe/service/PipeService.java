package cn.jasgroup.jasframework.acquisitiondata.material.pipe.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.deepoove.poi.data.PictureRenderData;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.PipeDao;
import cn.jasgroup.jasframework.acquisitiondata.story.StoryData;
import cn.jasgroup.jasframework.acquisitiondata.utils.ScannerUtils;
import cn.jasgroup.jasframework.base.service.RedisService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import cn.jasgroup.jasframework.utils.ReadConfigUtil;


@Service
@Transactional
public class PipeService {

	@Autowired
	private PipeDao pipeDao;
	
	@Autowired
	private RedisService redisService;
	

	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param type
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:22:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeList(String type,String projectOid) {
		return pipeDao.getPipeList(type,projectOid);
	}
	/**
	  * <p>功能描述：根据项目获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:18:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPipeList(String projectOid,String type) {
		return pipeDao.getMaterialPipeList(projectOid,type);
	}
	/***
	  * <p>功能描述：根据项目获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午9:32:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPipeList(List<String> projectOids) {
		return pipeDao.getMaterialPipeList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:19:09。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHotBendsList(String projectOid,String type) {
		return pipeDao.getMaterialHotBendsList(projectOid,type);
	}
	/***
	  * <p>功能描述：根据项目获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:09:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHotBendsList(List<String> projectOids) {
		return pipeDao.getMaterialHotBendsList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取所有的三通列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:13:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTeeList(String projectOid) {
		return pipeDao.getMaterialTeeList(projectOid);
	}
	/**
	  * <p>功能描述：根据项目获取所有的三通列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:32。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTeeList(List<String> projectOids) {
		return pipeDao.getMaterialTeeList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取绝缘接头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:13:32。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialJnsulatedJointList(String projectOid) {
		return pipeDao.getMaterialJnsulatedJointList(projectOid);
	}
	/**
	 	* <p>功能描述：根据项目获取绝缘接头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialJnsulatedJointList(List<String> projectOids) {
		return pipeDao.getMaterialJnsulatedJointList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取所有的大小头列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:15。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialReducerList(String projectOid) {
		return pipeDao.getMaterialReducerList(projectOid);
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialReducerList(List<String> projectOids) {
		return pipeDao.getMaterialReducerList(projectOids);
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialClosureList(String projectOid) {
		return pipeDao.getMaterialClosureList(projectOid);
	}
	/**
	  * <p>功能描述：根据项目获取所有的大小头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialClosureList(List<String> projectOids) {
		return pipeDao.getMaterialClosureList(projectOids);
	}
	
	/**
	 * <p>功能描述：获取阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:28:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getValveList(String projectOid) {
		return pipeDao.getValveList(projectOid);
	}
	
	/**
	 * <p>功能描述：根据项目获取所有的阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:29:12。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getValveList(List<String> projectOids) {
		return pipeDao.getValveList(projectOids);
	}
	
	/**
	  * <p>功能描述：根据项目获取冷弯管下拉选列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:07:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getPipeColdBendingList(List<String> projectOids){
		return pipeDao.getPipeColdBendingList(projectOids);
	}
	
	/***
	  * <p>功能描述：直管二维生成。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param oids
	  * @return
	  * @throws Exception
	  * @since JDK1.8。
	  * <p>创建日期:2018年11月23日 上午11:27:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String produceScanner(HttpServletRequest request,String functionCode,List<String> oids) throws Exception{
		String root = request.getServletContext().getRealPath(File.separator);
		String scannerFileOid = UUID.randomUUID().toString();
		String tempPath = ReadConfigUtil.getPlatformConfig("scanner.tempPath");
		tempPath += File.separator+System.currentTimeMillis();
		String tableName = this.pipeDao.getTableNameByFunctionCode(functionCode);
		String titleName=null;
		String segmentFileName=null;
		List<PipeScannerBo> scannerList = null;
		if(tableName.equals("daq_material_pipe")){
			titleName = "直管";
			segmentFileName = "pipeSegment.docx";
			scannerList = this.pipeDao.queryPipeScannerInfo(oids);
		}else if(tableName.equals("daq_material_hot_bends")){
			titleName = "热煨弯管";
			segmentFileName = "hotPipeSegment.docx";
			scannerList = this.pipeDao.queryHotPipeScannerInfo(oids);
		}
		if(scannerList!=null && scannerList.size()>0){
			StoryData data = new StoryData();
			data.setTitleName(titleName);
			List<PipeScannerBo> segments = new ArrayList<PipeScannerBo>();
			List<PipeScannerBo> segments2 = new ArrayList<PipeScannerBo>();
			for(int i=0; i<scannerList.size(); i++){
				PipeScannerBo bo = scannerList.get(i);
				String picturePath = ScannerUtils.createScanner(tempPath, bo.getScannerContext());
				bo.setPicture(new PictureRenderData(130, 130, picturePath));
				if(i%2==0){
					segments.add(bo);
				}else{
					segments2.add(bo);
				}
			}
			if(segments.size()>0){
				DocxRenderData segment = new DocxRenderData(new File(root+segmentFileName), segments );
				data.setSegment(segment);
			}
			if(segments2.size()>0){
				DocxRenderData segment2 = new DocxRenderData(new File(root+segmentFileName), segments2 );
				data.setSegment2(segment2);
			}

			XWPFTemplate template = XWPFTemplate.compile(new File(root+"/story.docx")).render(data);
			String scannerFilePath = tempPath+File.separator+titleName+"二维码.docx";
			FileOutputStream out = new FileOutputStream(scannerFilePath);
			template.write(out);
			out.flush();
			out.close();
			template.close();
			redisService.putValue(scannerFileOid, scannerFilePath);
			redisService.expirse(scannerFileOid, 30l, TimeUnit.MINUTES);
			return scannerFileOid;
		}
		return null;
	}
}
