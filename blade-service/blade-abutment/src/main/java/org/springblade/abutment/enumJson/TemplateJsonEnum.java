package org.springblade.abutment.enumJson;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springblade.contract.entity.CglCategorySalesContracts1Entity;
import org.springblade.contract.entity.CglLowCostHardware1Entity;
import org.springblade.contract.entity.CglRawMaterials1Entity;
import org.springblade.contract.vo.CglCategorySalesContracts1ResponseVO;
import org.springblade.contract.vo.CglLowCostHardware1ResponseVO;
import org.springblade.contract.vo.CglRawMaterials1ResponseVO;
import org.springblade.system.entity.TemplateFieldJsonEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author xhbbo
 */
@Getter
@AllArgsConstructor
@Slf4j
public enum TemplateJsonEnum {
	// 物流服务合同（二段仓储+配送）
	WLFW_23("WLFW_23") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 作业外包协议
	WBXY_27("WBXY_27") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 新陈列协议书
	CLXY_42("CLXY_42") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 店招合同
	DZHT_35("DZHT_35") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 媒体类：视频广告改编合同
	GBHT_14("GBHT_14") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 加工承揽合同（代工合同）
	CLHT_19("CLHT_19") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 音频制作合同
	ZZHT_18("ZZHT_18") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 修图合同
	XTHT_17("XTHT_17") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 视频制作合同
	ZZHT_16("ZZHT_16") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 视频广告拍摄制作合同
	ZZHT_15("ZZHT_15") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 活动执行合同
	HDZX_05("HDZX_05") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 物流服务合同（二段配送）
	FWHT_24("FWHT_24") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 媒体类：平面广告拍摄制作合同
	ZZHT_12("ZZHT_12") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 生产类：设备维修保养合同
	BYHT_21("BYHT_21") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 物流服务合同（一段+调拨运输）
	FWHT_25("FWHT_25") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 采购类_打样合同书
	DYHT_03("DYHT_03") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 保密协议（二方）
	BMXY_01("BMXY_01") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 保密协议（三方）
	BMXY_02("BMXY_02") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 房屋租赁合同模板
	FWZL_36("FWZL_36") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 消防设施维修保养服务合同
	XFWB_60("XFWB_60") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 广告制作安装合同模板
	GGZZ_04("GGZZ_04") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 买卖合同（国内设备购买）
	MMHT_06("MMHT_06") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	// 2021年统一e商城平台入驻服务协议（统一经销商）
	FWXY_50("FWXY_50") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//统一e商城平台入驻服务协议（统一经销商）目前使用模板
	FWXY_29("FWXY_29") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//物流服务合同（冷冻）
	WLFW_59("WLFW_59") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//劳务派遣合同
	LWHT_52("LWHT_52") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//市调合同（定性+定量）
	SDHT_13("SDHT_13") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//班车服务合同
	FWHT_51("FWHT_51") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//设备投放使用协议
	SBTF_40("SBTF_40") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//生产项目外包服务合同
	FWHT_22("FWHT_22") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//配送服务合同
	FWHT_38("FWHT_38") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//下脚品买卖合同
	MMHT_26("MMHT_26") {
		@Override
		public String setScheduler() {
			return null;
		}

		@Override
		public Object setScheduler(String type) {
			return null;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			return null;
		}
	},
	//原物料-买卖合同
	MMHT_10("MMHT_10") {
		@Override
		public String setScheduler() {
			return "cglRawMaterials1List";
		}

		@Override
		public Object setScheduler(String type) {
			List<ConcurrentHashMap<Object, Object>> objects = new ArrayList<>();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(type, TemplateFieldJsonEntity.class);
			templateFieldList.forEach(teL -> {
				List<CglRawMaterials1ResponseVO> CglRawMaterials1;
				if ("cglRawMaterials1List".equals(teL.getFieldName())) {
					CglRawMaterials1 = JSON.parseArray(teL.getTableData(), CglRawMaterials1ResponseVO.class);
					CglRawMaterials1.forEach(cg -> {
						try {
							ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
							for (Field field : cg.getClass().getDeclaredFields()) {
								field.setAccessible(true);
								String fieldName = field.getName();
								String value;
								if (field.get(cg) == null) {
									continue;
								} else {
									value = field.get(cg).toString();
								}
								map.put(fieldName, value);
							}
							map.remove("cglNumber");
							map.remove("createUserName");
							map.remove("isDeleted");
							map.remove("id");
							map.remove("createDeptName");
							map.remove("updateUserName");
							map.remove("updateUser");
							map.remove("updateTime");
							map.remove("createDept");
							map.remove("createTime");
							map.remove("createUser");
							map.remove("status");
							map.remove("contractId");
							map.remove("serialVersionUID");
							objects.add(map);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					});
				}
			});
			return objects;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			if (tag) {
				List<ConcurrentHashMap<Object, Object>> objects = new ArrayList<>();
				ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
				for (Field field : CglRawMaterials1Entity.class.getDeclaredFields()) {
					if (field.isAnnotationPresent(ApiModelProperty.class)) {
						/**
						 * 获取字段名
						 */
						ApiModelProperty declaredAnnotation = field.getDeclaredAnnotation(ApiModelProperty.class);
						String column = declaredAnnotation.value();
						map.put(field.getName(), column);
					}
				}
				map.remove("cglNumber");
				map.remove("createUserName");
				map.remove("isDeleted");
				map.remove("id");
				map.remove("createDeptName");
				map.remove("updateUserName");
				map.remove("updateUser");
				map.remove("updateTime");
				map.remove("createDept");
				map.remove("createTime");
				map.remove("createUser");
				map.remove("status");
				map.remove("contractId");
				map.remove("serialVersionUID");
				objects.add(map);
				return objects;
			}
			return null;
		}
	},
	//买卖合同（五金低耗类）
	MMHT_08("MMHT_08") {
		@Override
		public String setScheduler() {
			return "cglLowCostHardware1List";
		}

		@Override
		public Object setScheduler(String type) {
			List<ConcurrentHashMap<Object, Object>> objects = new ArrayList<>();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(type, TemplateFieldJsonEntity.class);
			templateFieldList.forEach(teL -> {
				List<CglLowCostHardware1ResponseVO> cglLowCostHardware1List;
				if ("cglLowCostHardware1List".equals(teL.getFieldName())) {
					cglLowCostHardware1List = JSON.parseArray(teL.getTableData(), CglLowCostHardware1ResponseVO.class);
					cglLowCostHardware1List.forEach(cg -> {
						try {
							ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
							for (Field field : cg.getClass().getDeclaredFields()) {
								field.setAccessible(true);
								if (!containsValue().containsValue(field.getName())) {
									String fieldName = field.getName();
									String value;
									if (field.get(cg) == null) {
										continue;
									} else {
										value = field.get(cg).toString();
									}
									map.put(fieldName, value);
								}
							}
							objects.add(map);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					});
				}
			});
			return objects;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			if (tag) {
				List<ConcurrentHashMap<Object, Object>> objects = new ArrayList<>();
				ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
				for (Field field : CglLowCostHardware1Entity.class.getDeclaredFields()) {
					if (!containsValue().containsValue(field.getName())) {
						if (field.isAnnotationPresent(ApiModelProperty.class)) {
							/**
							 * 获取字段名
							 */
							ApiModelProperty declaredAnnotation = field.getDeclaredAnnotation(ApiModelProperty.class);
							String column = declaredAnnotation.value();
							map.put(field.getName(), column);
						}
					}
				}
				objects.add(map);
				return objects;
			}
			return null;
		}
	},
	//买卖合同（行销品）
	MMHT_07("MMHT_07") {
		@Override
		public String setScheduler() {
			return "CglCategorySalesContracts1";
		}

		@Override
		public Object setScheduler(String type) {
			List<ConcurrentHashMap<Object, Object>> objects = new ArrayList<>();
			List<TemplateFieldJsonEntity> templateFieldList = JSON.parseArray(type, TemplateFieldJsonEntity.class);
			templateFieldList.forEach(teL -> {
				List<CglCategorySalesContracts1ResponseVO> CglCategorySalesContracts1;
				if ("cglLowCostHardware1List".equals(teL.getFieldName())) {
					CglCategorySalesContracts1 = JSON.parseArray(teL.getTableData(), CglCategorySalesContracts1ResponseVO.class);
					CglCategorySalesContracts1.forEach(cg -> {
						try {
							ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
							for (Field field : cg.getClass().getDeclaredFields()) {
								field.setAccessible(true);
								if (!containsValue().containsValue(field.getName())) {
									String fieldName = field.getName();
									String value;
									if (field.get(cg) == null) {
										continue;
									} else {
										value = field.get(cg).toString();
									}
									map.put(fieldName, value);
								}
							}
							objects.add(map);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					});
				}
			});
			return objects;
		}

		@Override
		public List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag) {
			if (tag) {
				List<ConcurrentHashMap<Object, Object>> objects = new ArrayList<>();
				ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
				for (Field field : CglCategorySalesContracts1Entity.class.getDeclaredFields()) {
					if (!containsValue().containsValue(field.getName())) {
						if (field.isAnnotationPresent(ApiModelProperty.class)) {
							/**
							 * 获取字段名
							 */
							ApiModelProperty declaredAnnotation = field.getDeclaredAnnotation(ApiModelProperty.class);
							String column = declaredAnnotation.value();
							map.put(field.getName(), column);
						}
					}
				}
				objects.add(map);
				return objects;
			}
			return null;
		}
	};

	public abstract String setScheduler();

	public abstract Object setScheduler(String type);

	public abstract List<ConcurrentHashMap<Object, Object>> setScheduler(String type, Boolean tag);

	@Getter
	public String type;

	/**
	 * 通过轮询来获得相应的方法
	 */
	public static TemplateJsonEnum fromValue(String type) {
		return Stream.of(TemplateJsonEnum.values()).filter(fileType ->
			StringUtils.equals(fileType.getType(), type)
		).findFirst().get();
	}

	public Map<String, String> containsValue() {
		Map<String, String> map = new HashMap();
		map.put("cglNumber", "cglNumber");
		map.put("createUserName", "createUserName");
		map.put("isDeleted", "isDeleted");
		map.put("id", "id");
		map.put("createDeptName", "createDeptName");
		map.put("updateUserName", "updateUserName");
		map.put("updateUser", "updateUser");
		map.put("updateTime", "updateTime");
		map.put("createDept", "createDept");
		map.put("createTime", "createTime");
		map.put("createUser", "createUser");
		map.put("status", "status");
		map.put("contractId", "contractId");
		map.put("serialVersionUID", "serialVersionUID");
		return map;
	}
}
