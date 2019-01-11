package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.PipeDao;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.entity.SteelPipe;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeService;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.utils.BeanUtilEx;
import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao.CutWeldDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao.entity.CutWeld;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.importexcel.utils.DataCheckUtil;
import cn.jasgroup.jasframework.importexcel.utils.ExcelParseUtil;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
@Transactional
public class CutWeldService {

	private static final String String = null;

	@Autowired
	private CutWeldDao cutWeldDao;

	@Autowired
	private CommonDataJdbcService commonDataJdbcService;

	@Autowired
	private PipeDao pipeDao;

	@Autowired
	private DaqPrivilegeService daqPrivilegeService;

	@Autowired
	private PipeService pipeService;

	/**
	 * <p>
	 * 功能描述：钢管切管之后保存段钢管信息。
	 * </p>
	 * <p>
	 * 葛建。
	 * </p>
	 * 
	 * @param cutWeld
	 * @since JDK1.8。
	 *        <p>
	 *        创建日期:2018年7月10日 上午9:00:49。
	 *        </p>
	 *        <p>
	 *        更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。
	 *        </p>
	 */
	public void savePipeAfterCut(CutWeld cutWeld) {
		// 获取原钢管对象
		SteelPipe pipe = (SteelPipe) commonDataJdbcService.get(SteelPipe.class, cutWeld.getPipeOid());
		pipe.setTendersOid(cutWeld.getTendersOid());
		pipe.setPipelineOid(cutWeld.getPipelineOid());
		pipe.setIsCut(1);
		pipe.setIsUse(1);
		pipe.setConstructUnit(cutWeld.getConstructUnit());
		// 将原钢管对象设置成已切管已使用
		commonDataJdbcService.update(pipe);
		// 保存段钢管信息
		saveSegmentPipe(cutWeld, pipe);

	}

	/**
	 * <p>
	 * 功能描述：修改钢管切管之后保存段钢管信息。
	 * </p>
	 * <p>
	 * 葛建。
	 * </p>
	 * 
	 * @param cutWeld
	 * @since JDK1.8。
	 *        <p>
	 *        创建日期:2018年7月16日 下午3:06:03。
	 *        </p>
	 *        <p>
	 *        更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。
	 *        </p>
	 */
	public void updatePipeAfterCut(CutWeld cutWeld) {
		// 获取原钢管对象
		SteelPipe pipe = (SteelPipe) commonDataJdbcService.get(SteelPipe.class, cutWeld.getPipeOid());
		pipe.setTendersOid(cutWeld.getTendersOid());
		pipe.setPipelineOid(cutWeld.getPipelineOid());
		pipe.setConstructUnit(cutWeld.getConstructUnit());
		// 删除原段钢管信息
		pipeDao.deleteSegmentPipe(pipe.getPipeCode(), pipe.getOid());
		// 保存新的段钢管信息
		saveSegmentPipe(cutWeld, pipe);
	}

	public void deletePipeAfterCut(CutWeld cutWeld) {
		cutWeld = (CutWeld) commonDataJdbcService.get(CutWeld.class, cutWeld.getOid());
		// 获取原钢管对象
		SteelPipe pipe = (SteelPipe) commonDataJdbcService.get(SteelPipe.class, cutWeld.getPipeOid());
		// 删除原段钢管信息
		pipeDao.deleteSegmentPipe(pipe.getPipeCode(), pipe.getOid());
		pipe.setIsCut(0);
		pipe.setIsUse(0);
		pipe.setTendersOid("");
		pipe.setPipelineOid("");
		// 将原钢管对象设置成未切管未使用
		commonDataJdbcService.update(pipe);
	}

	/**
	 * <p>
	 * 功能描述：保存段钢管信息。
	 * </p>
	 * <p>
	 * 葛建。
	 * </p>
	 * 
	 * @param cutWeld
	 * @param pipe
	 * @since JDK1.8。
	 *        <p>
	 *        创建日期:2018年7月16日 下午3:20:16。
	 *        </p>
	 *        <p>
	 *        更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。
	 *        </p>
	 */
	private void saveSegmentPipe(CutWeld cutWeld, SteelPipe pipe) {
		for (int i = 0; i < cutWeld.getSegmentsNum(); i++) {
			// 用于封装被切断的段钢管对象
			SteelPipe cutedPipe = new SteelPipe();
			// 将原钢管数据赋值给段钢管
			try {
				BeanUtilEx.copyProperties(cutedPipe, pipe);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cutedPipe.setOid(UUID.randomUUID().toString());
			cutedPipe.setProjectOid(pipe.getProjectOid());
			cutedPipe.setTendersOid("");
			cutedPipe.setPipelineOid("");
			cutedPipe.setPipeCode(pipe.getPipeCode() + "-" + (i + 1));
			setLengthAndWeight(cutedPipe, cutWeld, i, pipe);
			cutedPipe.setIsCut(0);
			cutedPipe.setIsUse(0);
			cutedPipe.setIsColdBend(0);
			cutedPipe.setIsChild(1);
			// 设置创建和修改信息
			cutedPipe.setCreateDatetime(new Date());
			cutedPipe.setCreateUserId(ThreadLocalHolder.getCurrentUserId());
			cutedPipe.setCreateUserName(ThreadLocalHolder.getCurrentUserName());
			cutedPipe.setModifyDatetime(new Date());
			cutedPipe.setModifyUserId(ThreadLocalHolder.getCurrentUserId());
			cutedPipe.setModifyUserName(ThreadLocalHolder.getCurrentUserName());
			// 保存段钢管信息
			commonDataJdbcService.save(cutedPipe);
		}
	}

	// 设置切管的长度和重量
	private void setLengthAndWeight(SteelPipe cutedPipe, CutWeld cutWeld, int i, SteelPipe pipe) {
		if (i == 0) {
			cutedPipe.setPipeLength(cutWeld.getFirstParagraphLength());
		} else if (i == 1) {
			cutedPipe.setPipeLength(cutWeld.getSecondParagraphLength());
		} else if (i == 2) {
			cutedPipe.setPipeLength(cutWeld.getThirdParagraphLength());
		} else if (i == 3) {
			cutedPipe.setPipeLength(cutWeld.getFourthParagraphLength());
		} else if (i == 4) {
			cutedPipe.setPipeLength(cutWeld.getFifthParagraphLength());
		}
		if (pipe.getPipeWeight() != null && pipe.getPipeWeight() > 0) {
			cutedPipe.setPipeWeight(pipe.getPipeWeight() * (cutedPipe.getPipeLength() / pipe.getPipeLength()));
		}
	}

	/**
	 * <p>
	 * 功能描述：校验old(阈值转换字段填写编码)Excel表格数据：必填，长度精度，域值转换，有效性，唯一性。
	 * </p>
	 * <p>
	 * 葛建。
	 * </p>
	 * 
	 * @param workbook
	 * @return
	 * @since JDK1.8。
	 *        <p>
	 * 		创建日期:2018年10月23日 上午9:58:58。
	 *        </p>
	 *        <p>
	 * 		更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。
	 *        </p>
	 */
	public Map<String, Object> verifyExcelDataByCode(Workbook workbook) {
		// 返回提示信息
		Map<String, Object> msgMap = new HashMap<String, Object>();
		// 查询切管表中的项目和钢管
		List<Map<String, Object>> pipeListInCut = cutWeldDao.queryPipeCodeList();
		// 查询用户所拥有的项目
		List<Map<String, Object>> projectList = daqPrivilegeService.getProject("pipe_network_code_001");
		Sheet sheet = workbook.getSheet("切管信息表");
		if (sheet == null) {
			msgMap.put("getSheet", "没有名为‘切管信息表’的sheet页;<br>");
			return msgMap;
		}
		int lastRowNum = sheet.getLastRowNum();
		Row row3 = sheet.getRow(2);
		boolean row3Blank = DataCheckUtil.isRowBlank(row3, 1, 15);
		if (row3Blank) {
			msgMap.put("getData", "excel表中数据为空;<br>");
			return msgMap;
		}
		// 封装违反唯一性字段
		List<String> uniqueList = new ArrayList<String>();
		StringBuffer nullBuffer = null;
		StringBuffer effectiveBuffer = null;
		StringBuffer uniqueBuffer = null;
		nullBuffer = new StringBuffer();
		effectiveBuffer = new StringBuffer();
		uniqueBuffer = new StringBuffer();
		// 项目下的标段
		List<Map<String, Object>> tendersList = null;
		// 标段下的管线
		List<Map<String, Object>> pipelineList = null;
		// 标段下的监理单位
		List<Map<String, Object>> supervisionUnitList = null;
		// 项目下的钢管
		List<Map<java.lang.String, Object>> pipeList2 = null;
		// 循环遍历行数据
		for (int i = 2; i < lastRowNum; i++) {
			Row row = sheet.getRow(i);
			boolean rowBlank = DataCheckUtil.isRowBlank(row, 1, 15);
			if (rowBlank) {
				break;
			}
			// 遍历列数据
			for (int j = 0; j < 14; j++) {
				String cellValue = "";
				int numericValue = -1;
				double doubleValue = 0;
				if (j < 4 || j > 9) {
					// 获取每个单元格的值
					cellValue = row.getCell(j).getStringCellValue();
					// ---------------------------判断非空----------------------
					if (j < 4 || j == 10 || j == 11) {
						if (StringUtils.isBlank(cellValue)) {
							nullBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据必填;<br>");
						}
					}

				} else {
					if (j == 4) {
						numericValue = (int) row.getCell(j).getNumericCellValue();
						// ---------------------------判断非空----------------------
						if (numericValue <= 0) {
							nullBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据必填;<br>");
						}
					} else if (j == 5) {
						doubleValue = row.getCell(j).getNumericCellValue();
						// ---------------------------判断非空----------------------
						if (doubleValue <= 0) {
							if (j == 4 || j == 5) {
								nullBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据必填;<br>");
							}
						}
					}
				}

				// --------------------------判断长度精度---------------------
				if (j > 3 && j < 10) {
					if (j == 4) {
						if (numericValue >= 6 || numericValue <= 0) {
//							effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据应大于等于1小于等于5;<br>");
							effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
						}
						double first = row.getCell(5).getNumericCellValue();
						double second = row.getCell(6).getNumericCellValue();
						double third = row.getCell(7).getNumericCellValue();
						double fourth = row.getCell(8).getNumericCellValue();
						double fifth = row.getCell(9).getNumericCellValue();
						switch (numericValue) {
						case 1:
							if (second>0 || third>0 || fourth>0 || fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第G-J列数据应为空;<br>");
							}
							break;
						case 2:
							if (third>0 || fourth>0 || fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第H-J列数据应为空;<br>");
							}
							break;
						case 3:
							if (fourth>0 || fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第I-J列数据应为空;<br>");
							}
							break;
						case 4:
							if (fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第J列数据应为空;<br>");
							}
							break;
						default:
							break;
						}
					} else {
						Object cellData = ExcelParseUtil.getCellData(row.getCell(j));
						if (doubleValue < 0 || doubleValue > 999999) {
							effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据应大于0小于999999.999;<br>");
						}
					}
				} else if (j == 13) {
					if (StringUtils.isNotBlank(cellValue) && cellValue.length() > 200) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据长度应小于200个字符;<br>");
					}
				} else {
					if (StringUtils.isNotBlank(cellValue) && cellValue.length() > 50) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据长度应小于50个字符;<br>");
					}
				}

				// --------------------------域值转换---------------------
				// --------------------------判断有效性，即数据库中是否有该数据，当前用户所在单位,在当前用户对应的项目下的标段、钢管，标段下的管线、监理单位，钢管长度--------------------
				Boolean flag = null;
				switch (j) {
				case 10:
					// ----------------------------施工单位有效性
					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "pri_unit", "unit_code");
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
					if (!unitOid.equals(cellValue)) {
//						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据有误,请填写登录用户所在部门编码;<br>");
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
						
					}
					break;
				case 0:
					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_project", "project_code");
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------项目有效性
					flag = false;
					for (int k = 0; k < projectList.size(); k++) {
						if (projectList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}

					}
					if (flag == false) {
//						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据有误，请填写登录用户所拥有的项目;<br>");
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// 查询项目下的标段
					tendersList = daqPrivilegeService.getTendersList(cellValue);
					// 查询项目下的钢管
					pipeList2 = pipeService.getPipeList("1", cellValue);
					break;
				case 1:
//					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_tenders", "tenders_code");
					cellValue = cutWeldDao.getOidBytendersName("code",cellValue, row.getCell(0).getStringCellValue());
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------标段有效性
					flag = false;
					for (int k = 0; k < tendersList.size(); k++) {
						if (tendersList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}

					}
					if (flag == false) {
//						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据有误，请填写对应项目下的标段;<br>");
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// 查询标段下的管线
					pipelineList = daqPrivilegeService.getPipelineList(cellValue);
					// 查询标段下的监理单位
					supervisionUnitList = daqPrivilegeService.getSupervisionUnitByTendersOid(cellValue);

					break;
				case 2:
//					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_pipeline", "pipeline_code");
					cellValue = cutWeldDao.getOidByPipelineName("code",cellValue, row.getCell(0).getStringCellValue());
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------管线有效性
					flag = false;
					for (int k = 0; k < pipelineList.size(); k++) {
						if (pipelineList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}

					}
					if (flag == false) {
//						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据有误，请填写对应标段下的管线;<br>");
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					break;
				case 3:
//					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_material_pipe", "pipe_code");
					cellValue = cutWeldDao.getOidByPipeCode(cellValue, row.getCell(0).getStringCellValue());
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------钢管有效性
					flag = false;
					for (int k = 0; k < pipeList2.size(); k++) {
						if (pipeList2.get(k).get("key").equals(cellValue)) {
							flag = true;
						}

					}
					if (flag == false) {
//						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据有误，请填写对应项目下未被使用的的钢管;<br>");
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// //钢管长度有效性
					if (StringUtils.isBlank(cellValue)) {
						break;
					}
					Object pipeLengthObj = cutWeldDao.getPipeInfo(cellValue).get(0).get("pipe_length");
					numericValue = (int) row.getCell(j+1).getNumericCellValue();
					if (pipeLengthObj != null) {
						// 钢管实际长度
						double pipeLength = Double.parseDouble(pipeLengthObj.toString());
						double first = row.getCell(5).getNumericCellValue();
						double second = row.getCell(6).getNumericCellValue();
						double third = row.getCell(7).getNumericCellValue();
						double fourth = row.getCell(8).getNumericCellValue();
						double fifth = row.getCell(9).getNumericCellValue();
						switch (numericValue) {
						case 1:
							if (first == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F列长度不能为0;<br>");
							}
							break;
						case 2:
							if (first == 0 || second == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-G列长度不能为0;<br>");
							}
							break;
						case 3:
							if (first == 0 || second == 0 || third == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-H列长度不能为0;<br>");
							}
							break;
						case 4:
							if (first == 0 || second == 0 || third == 0 || fourth == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-I列长度不能为0;<br>");
							}
							break;
						default:
							if (first == 0 || second == 0 || third == 0 || fourth == 0 || fifth == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-J列长度不能为0;<br>");
							}
							break;
						}
						if (pipeLength != (first + second + third + fourth + fifth)) {
							effectiveBuffer.append("第" + (1 + i) + "行数据有误，钢管总长度应为" + pipeLength + ";<br>");
						}
					}
					break;
				case 11:
					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "pri_unit", "unit_code");
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------监理单位有效性
					flag = false;
					for (int k = 0; k < supervisionUnitList.size(); k++) {
						if (supervisionUnitList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}
					}
					if (flag == false) {
//						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据有误，请填写对应标段下的监理单位;<br>");
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					;
					break;
				default:
					break;
				}

			}
			// --------------------------业务唯一性---------------------
			// 判断与数据库中数据是否冲突
			// 封装钢管编号列数据
			for (int k = 0; k < uniqueList.size(); k++) {
				// 判断表中数据是否重复
				if (uniqueList.contains(row.getCell(3).getStringCellValue())
						&& uniqueList.get(k++).equals(row.getCell(0).getStringCellValue())) {
					uniqueBuffer.append("第" + (1 + i) + "行，第C列数据与表中第三列其他数据重复;<br>");
				}
			}
			uniqueList.add(row.getCell(3).getStringCellValue());
			uniqueList.add(row.getCell(0).getStringCellValue());
			for (int k = 0; k < pipeListInCut.size(); k++) {
				String pipeCode = (String) pipeListInCut.get(k).get("pipe_code");
				String projectCode = (String) pipeListInCut.get(k).get("project_code");
				if ((row.getCell(3).getStringCellValue().equals(pipeCode))
						&& (row.getCell(0).getStringCellValue().equals(projectCode))) {
					uniqueBuffer.append("第" + (1 + i) + "行，第C列数据与数据库中其他数据重复;<br>");
				}
			}

		}
		msgMap.put("notNull", nullBuffer.toString());
		msgMap.put("invalid", effectiveBuffer.toString());
		msgMap.put("notUnique", uniqueBuffer.toString());
		return msgMap;
	}

	/**
	 * <p>功能描述：校验new(阈值转换字段填写名称)Excel表格数据：必填，长度精度，域值转换，有效性，唯一性。</p>
	  * <p> 葛建。</p>	
	  * @param workbook
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月20日 下午2:02:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> verifyExcelDataByName(Workbook workbook) {
		// 返回提示信息
		Map<String, Object> msgMap = new HashMap<String, Object>();
		// 查询切管表中的项目和钢管
		List<Map<String, Object>> pipeListInCut = cutWeldDao.queryPipeCodeList();
		// 查询用户所拥有的项目
		List<Map<String, Object>> projectList = daqPrivilegeService.getProject("pipe_network_code_001");
		Sheet sheet = workbook.getSheet("切管信息表");
		if (sheet == null) {
			msgMap.put("getSheet", "没有名为‘切管信息表’的sheet页;<br>");
			return msgMap;
		}
		int lastRowNum = sheet.getLastRowNum();
		Row row3 = sheet.getRow(2);
		boolean row3Blank = DataCheckUtil.isRowBlank(row3, 1, 15);
		if (row3Blank) {
			msgMap.put("getData", "excel表中数据为空;<br>");
			return msgMap;
		}
		// 封装违反唯一性字段
		List<String> uniqueList = new ArrayList<String>();
		StringBuffer nullBuffer = null;
		StringBuffer effectiveBuffer = null;
		StringBuffer uniqueBuffer = null;
		nullBuffer = new StringBuffer();
		effectiveBuffer = new StringBuffer();
		uniqueBuffer = new StringBuffer();
		// 项目下的标段
		List<Map<String, Object>> tendersList = null;
		// 标段下的管线
		List<Map<String, Object>> pipelineList = null;
		// 标段下的监理单位
		List<Map<String, Object>> supervisionUnitList = null;
		// 项目下的钢管
		List<Map<java.lang.String, Object>> pipeList2 = null;
		// 循环遍历行数据
		for (int i = 2; i < lastRowNum; i++) {
			Row row = sheet.getRow(i);
			boolean rowBlank = DataCheckUtil.isRowBlank(row, 1, 15);
			if (rowBlank) {
				break;
			}
			// 遍历列数据
			for (int j = 0; j < 14; j++) {
				String cellValue = "";
				int numericValue = -1;
				double doubleValue = 0;
				if (j < 4 || j > 9) {
					if (row.getCell(j) != null) {
						// 获取每个单元格的值
						cellValue = row.getCell(j).getStringCellValue();
						// ---------------------------判断非空----------------------
						if (j < 4 || j == 10 || j == 11) {
							if (StringUtils.isBlank(cellValue)) {
								nullBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据必填;<br>");
							}
						}
					}
				} else {
					if (j == 4) {
						numericValue = (int) row.getCell(j).getNumericCellValue();
						// ---------------------------判断非空----------------------
						if (numericValue <= 0) {
							nullBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据必填;<br>");
						}
					} else if (j == 5) {
						doubleValue = row.getCell(j).getNumericCellValue();
						// ---------------------------判断非空----------------------
						if (doubleValue <= 0) {
							if (j == 4 || j == 5) {
								nullBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据必填;<br>");
							}
						}
					}
				}

				// --------------------------判断长度精度---------------------
				if (j > 3 && j < 10) {
					if (j == 4) {
						if (numericValue >= 6 || numericValue <= 0) {
							effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
						}
						double first = row.getCell(5).getNumericCellValue();
						double second = row.getCell(6).getNumericCellValue();
						double third = row.getCell(7).getNumericCellValue();
						double fourth = row.getCell(8).getNumericCellValue();
						double fifth = row.getCell(9).getNumericCellValue();
						switch (numericValue) {
						case 1:
							if (second>0 || third>0 || fourth>0 || fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第G-J列数据应为空;<br>");
							}
							break;
						case 2:
							if (third>0 || fourth>0 || fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第H-J列数据应为空;<br>");
							}
							break;
						case 3:
							if (fourth>0 || fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第I-J列数据应为空;<br>");
							}
							break;
						case 4:
							if (fifth>0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第J列数据应为空;<br>");
							}
							break;
						default:
							break;
						}
					} else {
						Object cellData = ExcelParseUtil.getCellData(row.getCell(j));
						if (doubleValue < 0 || doubleValue > 999999) {
							effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据应大于0小于999999.999;<br>");
						}
					}
				} else if (j == 13) {
					if (StringUtils.isNotBlank(cellValue) && cellValue.length() > 200) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据长度应小于200个字符;<br>");
					}
				} else {
					if (StringUtils.isNotBlank(cellValue) && cellValue.length() > 50) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据长度应小于50个字符;<br>");
					}
				}

				// --------------------------域值转换---------------------
				// --------------------------判断有效性，即数据库中是否有该数据，当前用户所在单位,在当前用户对应的项目下的标段、钢管，标段下的管线、监理单位，钢管长度--------------------
				Boolean flag = null;
				switch (j) {
				case 0:
					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_project", "project_name");
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------项目有效性
					flag = false;
					for (int k = 0; k < projectList.size(); k++) {
						if (projectList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}
					}
					if (flag == false) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// 查询项目下的标段
					tendersList = daqPrivilegeService.getTendersList(cellValue);
					// 查询项目下的钢管
					pipeList2 = pipeService.getPipeList("1", cellValue);
					break;
				case 1:
//					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_tenders", "tenders_name");
					cellValue = cutWeldDao.getOidBytendersName("name",cellValue, row.getCell(0).getStringCellValue());
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------标段有效性
					flag = false;
					for (int k = 0; k < tendersList.size(); k++) {
						if (tendersList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}
					}
					if (flag == false) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// 查询标段下的管线
					pipelineList = daqPrivilegeService.getPipelineList(cellValue);
					// 查询标段下的监理单位
					supervisionUnitList = daqPrivilegeService.getSupervisionUnitByTendersOid(cellValue);
					
					break;
				case 2:
//					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_pipeline", "pipeline_name");
					cellValue = cutWeldDao.getOidByPipelineName("name",cellValue, row.getCell(0).getStringCellValue());
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------管线有效性
					flag = false;
					for (int k = 0; k < pipelineList.size(); k++) {
						if (pipelineList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}
						
					}
					if (flag == false) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					break;
				case 3:
//					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "daq_material_pipe", "pipe_code");
					cellValue = cutWeldDao.getOidByPipeCode(cellValue, row.getCell(0).getStringCellValue());
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------钢管有效性
					flag = false;
					for (int k = 0; k < pipeList2.size(); k++) {
						if (pipeList2.get(k).get("key").equals(cellValue)) {
							flag = true;
						}
						
					}
					if (flag == false) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// //钢管长度有效性
					if (StringUtils.isBlank(cellValue)) {
						break;
					}
					Object pipeLengthObj = cutWeldDao.getPipeInfo(cellValue).get(0).get("pipe_length");
					numericValue = (int) row.getCell(j+1).getNumericCellValue();
					if (pipeLengthObj != null) {
						// 钢管实际长度
						double pipeLength = Double.parseDouble(pipeLengthObj.toString());
						double first = row.getCell(5).getNumericCellValue();
						double second = row.getCell(6).getNumericCellValue();
						double third = row.getCell(7).getNumericCellValue();
						double fourth = row.getCell(8).getNumericCellValue();
						double fifth = row.getCell(9).getNumericCellValue();
						switch (numericValue) {
						case 1:
							if (first == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F列长度不能为0;<br>");
							}
							break;
						case 2:
							if (first == 0 || second == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-G列长度不能为0;<br>");
							}
							break;
						case 3:
							if (first == 0 || second == 0 || third == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-H列长度不能为0;<br>");
							}
							break;
						case 4:
							if (first == 0 || second == 0 || third == 0 || fourth == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-I列长度不能为0;<br>");
							}
							break;
						default:
							if (first == 0 || second == 0 || third == 0 || fourth == 0 || fifth == 0) {
								effectiveBuffer.append("第" + (1 + i) + "行，第F-J列长度不能为0;<br>");
							}
							break;
						}
						if (pipeLength != (first + second + third + fourth + fifth)) {
							effectiveBuffer.append("第" + (1 + i) + "行数据有误，钢管总长度应为" + pipeLength + ";<br>");
						}
					}
					break;
				case 10:
					// ----------------------------施工单位有效性
					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "pri_unit", "unit_name");
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
					if (!unitOid.equals(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
						
					}
					break;
				case 11:
					cellValue = cutWeldDao.getOidByUniqueField(cellValue, "pri_unit", "unit_name");
					if (StringUtils.isBlank(cellValue)) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					// ----------------------------监理单位有效性
					flag = false;
					for (int k = 0; k < supervisionUnitList.size(); k++) {
						if (supervisionUnitList.get(k).get("key").equals(cellValue)) {
							flag = true;
						}
					}
					if (flag == false) {
						effectiveBuffer.append("第" + (1 + i) + "行，第" + ((char)(j + 65)) + "列数据无效;<br>");
					}
					;
					break;
				default:
					break;
				}

			}
			// --------------------------业务唯一性---------------------
			// 判断与数据库中数据是否冲突
			// 封装钢管编号列数据
			for (int k = 0; k < uniqueList.size(); k++) {
				// 判断表中数据是否重复
				if (uniqueList.contains(row.getCell(3).getStringCellValue())
						&& uniqueList.get(k++).equals(row.getCell(0).getStringCellValue())) {
					uniqueBuffer.append("第" + (1 + i) + "行，第C列数据与表中第三列其他数据重复;<br>");
				}
			}
			uniqueList.add(row.getCell(3).getStringCellValue());
			uniqueList.add(row.getCell(0).getStringCellValue());
			for (int k = 0; k < pipeListInCut.size(); k++) {
				String pipeCode = (String) pipeListInCut.get(k).get("pipe_code");
				String projectCode = (String) pipeListInCut.get(k).get("project_code");
				if ((row.getCell(3).getStringCellValue().equals(pipeCode))
						&& (row.getCell(0).getStringCellValue().equals(projectCode))) {
					uniqueBuffer.append("第" + (1 + i) + "行，第C列数据与数据库中其他数据重复;<br>");
				}
			}

		}
		msgMap.put("notNull", nullBuffer.toString());
		msgMap.put("invalid", effectiveBuffer.toString());
		msgMap.put("notUnique", uniqueBuffer.toString());
		return msgMap;
	}
	
	/**
	 * <p>功能描述：excel表格数据入库。</p>
	  * <p> 葛建。</p>	
	  * @param workbook
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月20日 下午2:02:03。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void insertDataToDBByCode(Workbook workbook) {
		Sheet sheet = workbook.getSheet("切管信息表");
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 2; i < lastRowNum; i++) {
			CutWeld cutWeld = new CutWeld();
			Row row = sheet.getRow(i);
			boolean rowBlank = DataCheckUtil.isRowBlank(row, 1, 15);
			if (rowBlank) {
				break;
			}
			String projectOid = cutWeldDao.getOidByUniqueField(row.getCell(0).getStringCellValue(), "daq_project",
					"project_code");
			cutWeld.setProjectOid(projectOid);
			String tendersOid = cutWeldDao.getOidBytendersName("code",row.getCell(1).getStringCellValue(), row.getCell(0).getStringCellValue());
			cutWeld.setTendersOid(tendersOid);
			String pipelineOid = cutWeldDao.getOidByPipelineName("code",row.getCell(2).getStringCellValue(), row.getCell(0).getStringCellValue());
			cutWeld.setPipelineOid(pipelineOid);
			String pipeOid = cutWeldDao.getOidByPipeCode(row.getCell(3).getStringCellValue(), row.getCell(0).getStringCellValue());
			cutWeld.setPipeOid(pipeOid);
			List<Map<String, Object>> pipeInfo = cutWeldDao.getPipeInfo(pipeOid);
			double pipeDiameter = 0;
			if (pipeInfo.get(0).get("pipe_diameter") != null) {
				pipeDiameter = Double.parseDouble(pipeInfo.get(0).get("pipe_diameter").toString());
			}
			cutWeld.setPipeDiameter(pipeDiameter);
			double wallThickness = 0;
			if (pipeInfo.get(0).get("wall_thickness") != null) {
				wallThickness = Double.parseDouble(pipeInfo.get(0).get("wall_thickness").toString());
			}
			cutWeld.setWallThickness(wallThickness);
			int segmentsNum = (int) row.getCell(4).getNumericCellValue();
			cutWeld.setSegmentsNum(segmentsNum);
			double firstParagraphLength = row.getCell(5).getNumericCellValue();
			cutWeld.setFirstParagraphLength(firstParagraphLength);
			if (row.getCell(6).getNumericCellValue() > 0) {
				double secondParagraphLength = row.getCell(6).getNumericCellValue();
				cutWeld.setSecondParagraphLength(secondParagraphLength);
			}
			if (row.getCell(7).getNumericCellValue() > 0) {
				double thirdParagraphLength = row.getCell(7).getNumericCellValue();
				cutWeld.setThirdParagraphLength(thirdParagraphLength);
			}
			if (row.getCell(8).getNumericCellValue() > 0) {
				double fourthParagraphLength = row.getCell(8).getNumericCellValue();
				cutWeld.setFourthParagraphLength(fourthParagraphLength);
			}
			if (row.getCell(9).getNumericCellValue() > 0) {
				double fifthParagraphLength = row.getCell(9).getNumericCellValue();
				cutWeld.setFifthParagraphLength(fifthParagraphLength);
			}
			String constructUnit = ThreadLocalHolder.getCurrentUser().getUnitId();
			cutWeld.setConstructUnit(constructUnit);
			String supervisionUnit = cutWeldDao.getOidByUniqueField(row.getCell(11).getStringCellValue(), "pri_unit",
					"unit_code");
			cutWeld.setSupervisionUnit(supervisionUnit);
			if (row.getCell(12) != null) {
				String supervisionEngineer = row.getCell(12).getStringCellValue();
				cutWeld.setSupervisionEngineer(supervisionEngineer);
			}
			if (row.getCell(13) != null) {
				String remarks = row.getCell(13).getStringCellValue();
				cutWeld.setRemarks(remarks);
			}
			try {
				commonDataJdbcService.save(cutWeld);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * <p>功能描述：new(阈值转换字段填写名称)excel表格数据入库。</p>
	  * <p> 葛建。</p>	
	  * @param workbook
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月20日 下午3:05:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void insertDataToDBByName(Workbook workbook) {
		Sheet sheet = workbook.getSheet("切管信息表");
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 2; i < lastRowNum; i++) {
			CutWeld cutWeld = new CutWeld();
			Row row = sheet.getRow(i);
			boolean rowBlank = DataCheckUtil.isRowBlank(row, 1, 15);
			if (rowBlank) {
				break;
			}
			String projectOid = cutWeldDao.getOidByUniqueField(row.getCell(0).getStringCellValue(), "daq_project",
					"project_name");
			cutWeld.setProjectOid(projectOid);
			String tendersOid = cutWeldDao.getOidBytendersName("name",row.getCell(1).getStringCellValue(), row.getCell(0).getStringCellValue());
			cutWeld.setTendersOid(tendersOid);
			String pipelineOid = cutWeldDao.getOidByPipelineName("name",row.getCell(2).getStringCellValue(), row.getCell(0).getStringCellValue());
			cutWeld.setPipelineOid(pipelineOid);
			String pipeOid = cutWeldDao.getOidByPipeCode(row.getCell(3).getStringCellValue(), row.getCell(0).getStringCellValue());
			cutWeld.setPipeOid(pipeOid);
			List<Map<String, Object>> pipeInfo = cutWeldDao.getPipeInfo(pipeOid);
			double pipeDiameter = 0;
			if (pipeInfo.get(0).get("pipe_diameter") != null) {
				pipeDiameter = Double.parseDouble(pipeInfo.get(0).get("pipe_diameter").toString());
			}
			cutWeld.setPipeDiameter(pipeDiameter);
			double wallThickness = 0;
			if (pipeInfo.get(0).get("wall_thickness") != null) {
				wallThickness = Double.parseDouble(pipeInfo.get(0).get("wall_thickness").toString());
			}
			cutWeld.setWallThickness(wallThickness);
			int segmentsNum = (int) row.getCell(4).getNumericCellValue();
			cutWeld.setSegmentsNum(segmentsNum);
			double firstParagraphLength = row.getCell(5).getNumericCellValue();
			cutWeld.setFirstParagraphLength(firstParagraphLength);
			if (row.getCell(6).getNumericCellValue() > 0) {
				double secondParagraphLength = row.getCell(6).getNumericCellValue();
				cutWeld.setSecondParagraphLength(secondParagraphLength);
			}
			if (row.getCell(7).getNumericCellValue() > 0) {
				double thirdParagraphLength = row.getCell(7).getNumericCellValue();
				cutWeld.setThirdParagraphLength(thirdParagraphLength);
			}
			if (row.getCell(8).getNumericCellValue() > 0) {
				double fourthParagraphLength = row.getCell(8).getNumericCellValue();
				cutWeld.setFourthParagraphLength(fourthParagraphLength);
			}
			if (row.getCell(9).getNumericCellValue() > 0) {
				double fifthParagraphLength = row.getCell(9).getNumericCellValue();
				cutWeld.setFifthParagraphLength(fifthParagraphLength);
			}
			String constructUnit = ThreadLocalHolder.getCurrentUser().getUnitId();
			cutWeld.setConstructUnit(constructUnit);
			String supervisionUnit = cutWeldDao.getOidByUniqueField(row.getCell(11).getStringCellValue(), "pri_unit",
					"unit_name");
			cutWeld.setSupervisionUnit(supervisionUnit);
			if (row.getCell(12) != null) {
				String supervisionEngineer = row.getCell(12).getStringCellValue();
				cutWeld.setSupervisionEngineer(supervisionEngineer);
			}
			if (row.getCell(13) != null) {
				String remarks = row.getCell(13).getStringCellValue();
				cutWeld.setRemarks(remarks);
			}
			try {
				commonDataJdbcService.save(cutWeld);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
