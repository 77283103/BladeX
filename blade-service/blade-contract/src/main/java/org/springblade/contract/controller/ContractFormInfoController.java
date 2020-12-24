package org.springblade.contract.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import feign.form.ContentType;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springblade.abutment.entity.*;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.abutment.vo.CompanyInfoVo;
import org.springblade.abutment.vo.SingleSignVo;
import org.springblade.abutment.vo.UploadFileVo;
import org.springblade.contract.constant.ContractFormInfoTemplateContract;
import org.springblade.contract.entity.ContractAccordingEntity;
import org.springblade.contract.entity.ContractBondEntity;
import org.springblade.contract.entity.ContractBondPlanEntity;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.excel.ContractFormInfoImporter;
import org.springblade.contract.excel.ContractFormInfoImporterEx;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.service.*;
import org.springblade.contract.util.AsposeWordToPdfUtils;
import org.springblade.contract.util.TemplateExportUntil;
import org.springblade.contract.util.TemplateSaveUntil;
import org.springblade.contract.vo.ContractAccordingRequestVO;
import org.springblade.contract.vo.ContractFormInfoRequestVO;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.boot.ctrl.BladeController;
import org.springblade.core.excel.util.ExcelUtil;
import org.springblade.core.log.exception.ServiceException;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.mp.support.Query;
import org.springblade.core.secure.annotation.PreAuth;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.*;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.DictBiz;
import org.springblade.system.entity.TemplateFieldEntity;
import org.springblade.system.feign.IDictBizClient;
import org.springblade.system.user.cache.UserCache;
import org.springblade.system.vo.TemplateRequestVO;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.*;


/**
 * 控制器
 *
 * @author : 史智伟
 * @date : 2020-09-23 18:04:37
 */
@RestController
@AllArgsConstructor
@RequestMapping("/contractFormInfo")
@Api(value = "", tags = "")
public class ContractFormInfoController extends BladeController {

	private IContractFormInfoService contractFormInfoService;
	private IContractPerformanceService performanceService;
	private IContractAccordingService accordingService;
	private IContractBondService contractBondService;
	private IContractPerformanceColPayService contractPerformanceColPayService;
	private IContractBondPlanService contractBondPlanService;
	private ContractFormInfoMapper formInfoMapper;
	private IDictBizClient bizClient;
	private IAbutmentClient abutmentClient;
	private IFileClient fileClient;
	private static final Integer CHANGE_CONTRACT_ID = -1;
	private static final String CHANGE_REVIEW_STATUS = "10";
	private static final String APPROVE_REVIEW_STATUS = "10";
	private static final String CONTRACT_REVIEW_STATUS = "20";
	private static final String FILE_EXPORT_CATEGORY = "1";
	private static final String CONTRACT_AUDIT_QUALITY = "30";
	private static final String CONTRACT_EXPORT_STATUS = "40";
	private static final String CONTRACT_SEAL_USING_INFO_STATUS = "50";
	private static final String CONTRACT_SIGNING_STATUS = "60";
	private static final String CONTRACT_ARCHIVE_STATUS = "110";
	private static final String CONTRACT_ASSESSMENT_STATUS = "100";
	private static final String ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS = "130";

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:detail')")
	public R<ContractFormInfoResponseVO> detail(@RequestParam Long id) {
		ContractFormInfoResponseVO detail = contractFormInfoService.getById(id);
		return R.data(detail);
	}
	/**
	 * 合同变更历史详情
	 */
	@GetMapping("/version")
	@ApiOperationSupport(order = 1)
	@ApiOperation(value = "详情", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:version')")
	public R<ContractFormInfoResponseVO> version(@RequestParam Long id) {
		ContractFormInfoResponseVO version = contractFormInfoService.getByChangeHistoryId(id);
		return R.data(version);
	}
	/**
	 * 分页
	 */
	@GetMapping("/list")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:list')")
	public R<IPage<ContractFormInfoResponseVO>> list(ContractFormInfoRequestVO contractFormInfo, Query query) {
		IPage<ContractFormInfoResponseVO> pages = contractFormInfoService.pageList(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}


	/**
	 * 用印分页
	 */
	@GetMapping("/listSealInfo")
	@ApiOperationSupport(order = 3)
	@ApiOperation(value = "分页", notes = "传入ContractFormInfoRequestVO")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:listSealInfo')")
	public R<IPage<ContractFormInfoResponseVO>> listSealInfo(ContractFormInfoRequestVO contractFormInfoRequestVO, Query query) {
		IPage<ContractFormInfoEntity> pages = contractFormInfoService.pageListSealInfo(Condition.getPage(query), contractFormInfoRequestVO);
		return R.data(ContractFormInfoWrapper.build().entityPVPage(pages));
	}


	/**
	 * 多方起草新增
	 */
	@PostMapping("/multiAdd")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiAdd')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> multiAdd(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		contractFormInfo.setContractSoure("20");
		//String sealName = StringUtils.join(contractFormInfo.getSealNameList(), ",");
		//contractFormInfo.setSealName(sealName);
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}


	/**
	 * 独立起草新增
	 */
	@PostMapping("/add")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:add')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> save(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		EkpEntity ekpEntity = new EkpEntity();
		contractFormInfo.setContractSoure("10");
		//String sealName = StringUtils.join(contractFormInfo.getSealNameList(), ",");
		//contractFormInfo.setSealName(sealName);
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlan.setId(null);
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//开始接口处理
		if("30".equals(entity.getContractStatus())){
			// 查查公司有没有申请电子章
			CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
			companyInfoEntity.setQueryType("1");
			// 企业信用代码  问题：从哪取？？？？不知道从哪取所以放了个空串,但这个是必填项
			companyInfoEntity.setOrganCode("91360823092907952B");//这个统一用相对方里的企业信用代码
			// 如果是null的话,说明根本没注册,如果注册了那available是1的话表示有章,0是没章
			CompanyInfoVo companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData(); //这块通了
			// 有章才操作
			if (companyInfoVo.getAvailable().equals("1")) {
				// 上传合同文件 开始  问题:不知道怎么生成pdf,所以就直接从entity里拿了,没有的话是不是需要生成一下?
				// 接口是支持批量上传的
				UploadFileEntity uploadFileEntity = new UploadFileEntity();
				//查询合同正文
				List<FileVO> fileVO=fileClient.getByIds(entity.getTextFile()).getData();
				String newFileDoc="";
				String newFilePdf="";
				//doc转为pdf
				if(CollectionUtil.isNotEmpty(fileVO)){
					newFileDoc=fileVO.get(0).getLink();
					int index = fileVO.get(0).getName().lastIndexOf(".");
					newFilePdf="D:/ftl/"+fileVO.get(0).getName().substring(0,index)+".pdf";
					AsposeWordToPdfUtils.doc2pdf(newFileDoc,newFilePdf);
				}
				File filePDF = new File(newFilePdf);
				R<FileVO> filePDFVO = null;
				try {
					MultipartFile multipartFile = new MockMultipartFile("file", filePDF.getName(),
					ContentType.MULTIPART.toString(), new FileInputStream(filePDF));
					filePDFVO=fileClient.save(multipartFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				/* 上传文件 */
				assert filePDFVO != null;
				entity.setTextFilePdf(filePDFVO.getData().getId()+",");
				// 入参是个file文件,怎么获取这个file文件? 这样获取对不对?
				List<File> files = new ArrayList<File>();
				files.add(filePDF);
				uploadFileEntity.setFile(files);
				// 默认是不合并,该上传几个文件就几个文件
				uploadFileEntity.setIsMerge("0");
				// 调用上传方法 另外需要注意 接口里自动进行了获取token的动作, 现在的获取地址和账号密码都是测试的,正式使用需要修改这些内容,修改位置在blade-abutment的resources.application里
				List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
				// 上传合同文件 结束

				// 盖章方法  开始 问题:这里直接盖章吗?????????? 如果不在这里的话, 把它挪到对应地方
				// 这里用的是单个盖章, 批量盖章需要短信验证
				List<SingleSignVo> singleSignVoList = new ArrayList<SingleSignVo>();
				for(UploadFileVo uploadFileVo:uploadFileVoList){
					SingleSignEntity singleSignEntity = new SingleSignEntity();
					// 企业信用代码  问题：从哪取？？？？不知道从哪取所以放了个空串,但这个是必填项
					singleSignEntity.setIdno("91360823092907952B");
					// 盖章类型 盖什么章??Single：单页签章,Multi：多页签章,Edges：签骑缝章,Key：关键字签章,一次只能盖一种章!
					singleSignEntity.setSignType("Key");
					// 文档信息
					FileInfoEntity fileInfoEntity = new FileInfoEntity();
					// 给哪个文档盖章(上传后返回的文档id)
					fileInfoEntity.setId(uploadFileVo.getId());
					// 显不显示E签宝logo,false是不显示
					fileInfoEntity.setShowImage(false);
					// 盖完章之后文件叫什么名字 可以不填
					//fileInfoEntity.setFileName(null);
					// 如果是加密文档的话,需要输入密码, 没有的话不填
					//fileInfoEntity.setOwnerPassword(null);
					singleSignEntity.setFileBean(fileInfoEntity);
					// 签章位置信息,除骑缝签章外必填
					SignPosEntity signPosEntity = new SignPosEntity();
					// 给哪页盖章 用逗号间隔页码 如 1,2,3 当前是关键字签章,所以不用填
					//signPosEntity.setPosPage(null);
					// 盖章坐标x 不填就是0 关键字的话坐标中心点是关键字
					//signPosEntity.setPosX(0f);
					// 盖章坐标y 不填就是0 关键字的话坐标中心点是关键字
					//signPosEntity.setPosY(0f);
					// 只有关键字签章的时候采用,输入关键字
					signPosEntity.setKey("盖章");
					// 章大小 可以不填,不填最大显示159,小于159按实际大小走,大于159只显示159大小
					//signPosEntity.setWidth(0f);
					// 是否二维码签署 骑缝和多页不生效
					//signPosEntity.setQrcodeSign(false);
					// 是不是作废,如果需要签作废章就选true
					//signPosEntity.setCacellingSign(false);
					// 显示签署时间,印章大于92才能显示
					//signPosEntity.setAddSignTime(false);
					singleSignEntity.setSignPos(signPosEntity);
					// 如果签完一种章需要签另一种的话,就可以天
					AutoSignEntity autoSignEntity = new AutoSignEntity();
					// 企业信用代码 这个代码是为了定位章  如果要盖别单位的章就得填别单位的,不然就和上面填一样
					autoSignEntity.setIdno(singleSignEntity.getIdno());
					// 盖章类型 盖什么章??Single：单页签章,Multi：多页签章,Edges：签骑缝章,Key：关键字签章,一次只能盖一种章!
					autoSignEntity.setIdno("Edges");
					// 和上面那个一样,设定盖章位置
					//SignPosEntity autoSignPosEntity = new SignPosEntity();
					//autoSignEntity.setSignPos(autoSignPosEntity);
					singleSignEntity.setAutoSign(autoSignEntity);

					// 返回的内容里有个filePath,是个ID,这个id可以用来下载或者在线查看盖完章的文件
					singleSignVoList.add(abutmentClient.singleSignPost(singleSignEntity).getData()); //接口通了
				}
				// 盖章方法  结束

				// 获取文件 下载或者在线查看
				List<String> urls = new ArrayList<String>();
				for(SingleSignVo singleSignVo :singleSignVoList){
					ReadSignedEntity readSignedEntity = new ReadSignedEntity();
					readSignedEntity.setId(singleSignVo.getFilePath());
					// pdf的ID
					if (ekpEntity.getFd_file_id() == null || ekpEntity.getFd_file_id().equals("")) {
						ekpEntity.setFd_file_id(singleSignVo.getFilePath());
					} else {
						ekpEntity.setFd_file_id(ekpEntity.getFd_file_id() + "," + singleSignVo.getFilePath());
					}
					// 1在线预览  2下载
					readSignedEntity.setType("1");
					// 返回的是个完整路径,直接通过response.getWriter().write()返回到前台就可以
					urls.add(abutmentClient.readSigned(readSignedEntity).getData()); //接口通了
				}
			}
			contractFormInfoService.updateById(entity);
		}
		//等于30合同为提交状态 这里去对接oa接口
		/*if("30".equals(contractFormInfo.getContractStatus())){
			// 查查公司有没有申请电子章
			*//*CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
			companyInfoEntity.setQueryType("1");
			// 企业信用代码  问题：从哪取？？？？不知道从哪取所以放了个空串,但这个是必填项
			companyInfoEntity.setOrganCode("");
			// 如果是null的话,说明根本没注册,如果注册了那available是1的话表示有章,0是没章
			CompanyInfoVo companyInfoVo = abutmentClient.queryCompanyInfo(companyInfoEntity).getData();
			// 有章才操作
			if (companyInfoVo.getAvailable().equals("1")) {
				// 上传合同文件 开始  问题:不知道怎么生成pdf,所以就直接从entity里拿了,没有的话是不是需要生成一下?
				// 接口是支持批量上传的
				UploadFileEntity uploadFileEntity = new UploadFileEntity();
				// 入参是个file文件,怎么获取这个file文件? 这样获取对不对?
				List<File> files = new ArrayList<File>();
				fileClient.getByIds(entity.getTextFilePdf()).getData().forEach(fileVO -> {
					files.add(new File(fileVO.getLink()));
				});
				uploadFileEntity.setFile(files);
				// 默认是不合并,该上传几个文件就几个文件
				uploadFileEntity.setIsMerge("0");
				// 调用上传方法 另外需要注意 接口里自动进行了获取token的动作, 现在的获取地址和账号密码都是测试的,正式使用需要修改这些内容,修改位置在blade-abutment的resources.application里
				List<UploadFileVo> uploadFileVoList = abutmentClient.uploadFiles(uploadFileEntity).getData();
				// 上传合同文件 结束

				// 盖章方法  开始 问题:这里直接盖章吗?????????? 如果不在这里的话, 把它挪到对应地方
				// 这里用的是单个盖章, 批量盖章需要短信验证
				List<SingleSignVo> singleSignVoList = new ArrayList<SingleSignVo>();
				uploadFileVoList.forEach(uploadFileVo -> {
					SingleSignEntity singleSignEntity = new SingleSignEntity();
					// 企业信用代码  问题：从哪取？？？？不知道从哪取所以放了个空串,但这个是必填项
					singleSignEntity.setIdno("");
					// 盖章类型 盖什么章??Single：单页签章,Multi：多页签章,Edges：签骑缝章,Key：关键字签章,一次只能盖一种章!
					singleSignEntity.setIdno("Key");
					// 文档信息
					FileInfoEntity fileInfoEntity = new FileInfoEntity();
					// 给哪个文档盖章(上传后返回的文档id)
					fileInfoEntity.setId(uploadFileVo.getId());
					// 显不显示E签宝logo,false是不显示
					fileInfoEntity.setShowImage(false);
					// 盖完章之后文件叫什么名字 可以不填
					//fileInfoEntity.setFileName(null);
					// 如果是加密文档的话,需要输入密码, 没有的话不填
					//fileInfoEntity.setOwnerPassword(null);
					singleSignEntity.setFileBean(fileInfoEntity);
					// 签章位置信息,除骑缝签章外必填
					SignPosEntity signPosEntity = new SignPosEntity();
					// 给哪页盖章 用逗号间隔页码 如 1,2,3 当前是关键字签章,所以不用填
					//signPosEntity.setPosPage(null);
					// 盖章坐标x 不填就是0 关键字的话坐标中心点是关键字
					//signPosEntity.setPosX(0f);
					// 盖章坐标y 不填就是0 关键字的话坐标中心点是关键字
					//signPosEntity.setPosY(0f);
					// 只有关键字签章的时候采用,输入关键字
					signPosEntity.setKey("盖章");
					// 章大小 可以不填,不填最大显示159,小于159按实际大小走,大于159只显示159大小
					//signPosEntity.setWidth(0f);
					// 是否二维码签署 骑缝和多页不生效
					//signPosEntity.setQrcodeSign(false);
					// 是不是作废,如果需要签作废章就选true
					//signPosEntity.setCacellingSign(false);
					// 显示签署时间,印章大于92才能显示
					//signPosEntity.setAddSignTime(false);
					singleSignEntity.setSignPos(signPosEntity);
					// 如果签完一种章需要签另一种的话,就可以天
					AutoSignEntity autoSignEntity = new AutoSignEntity();
					// 企业信用代码 这个代码是为了定位章  如果要盖别单位的章就得填别单位的,不然就和上面填一样
					autoSignEntity.setIdno(singleSignEntity.getIdno());
					// 盖章类型 盖什么章??Single：单页签章,Multi：多页签章,Edges：签骑缝章,Key：关键字签章,一次只能盖一种章!
					autoSignEntity.setIdno("Edges");
					// 和上面那个一样,设定盖章位置
					//SignPosEntity autoSignPosEntity = new SignPosEntity();
					//autoSignEntity.setSignPos(autoSignPosEntity);
					singleSignEntity.setAutoSign(autoSignEntity);

					// 返回的内容里有个filePath,是个ID,这个id可以用来下载或者在线查看盖完章的文件
					singleSignVoList.add(abutmentClient.singleSign(singleSignEntity).getData());
				});
				// 盖章方法  结束

				// 获取文件 下载或者在线查看
				List<String> urls = new ArrayList<String>();
				singleSignVoList.forEach(singleSignVo -> {
					ReadSignedEntity readSignedEntity = new ReadSignedEntity();
					readSignedEntity.setId(singleSignVo.getFilePath());
					// pdf的ID
					if (ekpEntity.getFd_file_id() == null || ekpEntity.getFd_file_id().equals("")) {
						ekpEntity.setFd_file_id(singleSignVo.getFilePath());
					} else {
						ekpEntity.setFd_file_id(ekpEntity.getFd_file_id() + "," + singleSignVo.getFilePath());
					}
					// 1在线预览  2下载
					readSignedEntity.setType("1");
					// 返回的是个完整路径,直接通过response.getWriter().write()返回到前台就可以
					urls.add(abutmentClient.readSigned(readSignedEntity).getData());
				});
			}*//*

			// 推送EKP 开始
			//发起流程的员工编号   问题:到底用哪个？ id还是code？？
			ekpEntity.setEmplno(UserCache.getUser(AuthUtil.getUserId()).getCode());
			//单据主题   问题:是不是用合同名称？
			ekpEntity.setDocSubject(contractFormInfo.getContractName());
			//依据文档id(必填,ekp跳转到合同平台传递的依据文档id)   问题:看字面意思应该就是一个类似工单号?应该取哪个值?
			ekpEntity.setFd_parent_id(contractFormInfo.getId() + "");
			//对方名称
			ekpEntity.setFd_name(contractFormInfo.getCounterpartName());
			//合同份数
			ekpEntity.setFd_totle(contractFormInfo.getShare());
			//合同期限
			switch (contractFormInfo.getContractPeriod()) {
				case "xysn":
					ekpEntity.setFd_cont_scop("sx");
					break;
				case "dysn":
					ekpEntity.setFd_cont_scop("dx");
					break;
				case "wzzqx":
					ekpEntity.setFd_cont_scop("wx");
					break;
			}
			//付款期限  根据字典表拆分出来的,不确定是否对,需要确认!
			switch (contractFormInfo.getColPayTerm()) {
				case "f1":
					ekpEntity.setFd_paydate("1");
					break;
				case "f2":
					ekpEntity.setFd_paydate("2");
					break;
				case "f3":
					ekpEntity.setFd_paydate("3");
					break;
				case "f4":
					ekpEntity.setFd_paydate("4");
					break;
				case "f5":
					ekpEntity.setFd_paydate("6");
					ekpEntity.setFd_tiaokuan("4");
					break;
				case "f6":
					ekpEntity.setFd_paydate("6");
					ekpEntity.setFd_tiaokuan("5");
					break;
				case "f7":
					ekpEntity.setFd_paydate("6");
					ekpEntity.setFd_tiaokuan("6");
					break;
				case "priceless_money":
					ekpEntity.setFd_paydate("6");
					break;
				case "s1":
					ekpEntity.setFd_paydate("5");
					ekpEntity.setFd_shouktk("KDFH");
					break;
				case "s2":
					ekpEntity.setFd_paydate("5");
					ekpEntity.setFd_shouktk("SX");
					break;
				case "s3":
					ekpEntity.setFd_paydate("5");
					ekpEntity.setFd_shouktk("SHHK");
					break;

			}
			// 合同开始时间
			ekpEntity.setFd_starttime(DateUtil.format(contractFormInfo.getStartingTime(), "yyyy-MM-dd"));
			// 合同结束时间
			ekpEntity.setFd_lasttime(DateUtil.format(contractFormInfo.getEndTime(), "yyyy-MM-dd"));
			// 合同金额
			ekpEntity.setFd_dollar(contractFormInfo.getContractAmount().toString());
			// 合同标的 交易的商品或服务标题 这个值不知道去哪取
			ekpEntity.setFd_biaodi("");
			// 付款条款 (1:一次性付款 2:分期付款 3:预付货款 4:账扣 5:票折 6:补货) 一次性付款和分期付款怎么区分? 怎么取值?
			//ekpEntity.setFd_tiaokuan("");
			if (ekpEntity.getFd_tiaokuan().equals("1")) {
				// 付款天数
				ekpEntity.setFd_days(contractFormInfo.getDays() + "");
			}
			if (ekpEntity.getFd_tiaokuan().equals("2")) {
				// 进度1  第一个是进度% 第二个是在多少天内付款 第三个是付款比例%  这些值从哪取???????
				ekpEntity.setFd_percent1("");ekpEntity.setFd_billday1("");ekpEntity.setFd_percent2("");
				// 进度2  第一个是进度% 第二个是在多少天内付款 第三个是付款比例%  这些值从哪取???????
				ekpEntity.setFd_percent3("");ekpEntity.setFd_billday2("");ekpEntity.setFd_percent4("");
				// 进度3  第一个是进度% 第二个是在多少天内付款 第三个是付款比例%  这些值从哪取???????
				ekpEntity.setFd_percent5("");ekpEntity.setFd_billday3("");ekpEntity.setFd_percent6("");
				// 进度4  第一个是在多少天内付款 第二个是付款比例%  这些值从哪取???????
				ekpEntity.setFd_percent7("");ekpEntity.setFd_billday4("");
				// 进度5  第一个是在多少天内付款 第二个是付款比例%  这些值从哪取???????
				ekpEntity.setFd_percent8("");ekpEntity.setFd_billday5("");
			}
			if (ekpEntity.getFd_tiaokuan().equals("3")) {
				// 预付货款 发票天数 这些值从哪取???????
				ekpEntity.setFd_billday6("");
				// 预付货款 预付款 这些值从哪取???????
				ekpEntity.setFd_pay("");
			}
			// 违约责任 怎么取????
			ekpEntity.setFd_duty("");
			abutmentClient.sendEkpForm(ekpEntity);
			// 推送EKP 结束
		}*/
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}


	/**
	 * 范本起草新增
	 */
	@PostMapping("/templateSave")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:templateSave')")
	@Transactional(rollbackFor = Exception.class)
	public R<String> templateSave(@Valid @RequestBody TemplateRequestVO template) {
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		//处理合同的二级联动保存
		for (TemplateFieldEntity templateField : templateFieldList) {
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				JSONObject json = JSON.parseObject(jsonObj.get("template").toString());
				j.put("contractBigCategory", jsonObj.get("first"));
				j.put("contractSmallCategory", jsonObj.get("second"));
				if(null!=json){
					j.put("contractTemplateId", json.get("id"));
				}
			} else if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				j.put("colPayType", jsonObj.get("first"));
				j.put("colPayTerm", jsonObj.get("second"));
				j.put("days", jsonObj.get("days"));
			} else if ("id".equals(templateField.getComponentType())) {
				j.put("id", templateField.getFieldValue());
			} else if ("upload".equals(templateField.getComponentType())) {
				//j.put("id", templateField.getFieldValue());
			} else {
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}
		TemplateSaveUntil templateSaveUntil =new TemplateSaveUntil();
		//把json串转换成一个对象
		ContractFormInfoEntity contractFormInfoEntity = JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
		//保存合同和关联表
		templateSaveUntil.templateSave(contractFormInfoEntity,template,j);
		/*if (Func.isEmpty(contractFormInfoEntity.getId())) {
			contractFormInfoEntity.setContractSoure("30");
			contractFormInfoEntity.setContractStatus("10");
			contractFormInfoService.save(contractFormInfoEntity);
		} else {
			contractFormInfoService.updateById(contractFormInfoEntity);
		}*/
		String json = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		contractFormInfoEntity.setJson(json);
		//页面用这个字段来判断是否提交
		if("30".equals(template.getBean())){
			//导出pdf文件
			TemplateExportUntil templateExportUntil=new TemplateExportUntil();
			contractFormInfoEntity.setTextFilePdf(templateExportUntil.templateSave(contractFormInfoEntity,template,json,j).getId()+",");
			contractFormInfoEntity.setContractStatus("30");
		}
		contractFormInfoService.updateById(contractFormInfoEntity);
		return R.data(json);
	}

	/**
	 * 合同预览
	 */
	@PostMapping("/contractBrowse")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "合同预览", notes = "template")
	@Transactional(rollbackFor = Exception.class)
	public R<String> contractBrowse(@Valid @RequestBody TemplateRequestVO template){
		List<TemplateFieldEntity> templateFieldList = JSON.parseArray(template.getJson(), TemplateFieldEntity.class);
		JSONObject j = new JSONObject();
		//处理合同的二级联动保存
		for (TemplateFieldEntity templateField : templateFieldList) {
			if (ContractFormInfoTemplateContract.CONTRACT_BIG_CATEGORY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				JSONObject json = JSON.parseObject(jsonObj.get("template").toString());
				j.put("contractBigCategory", jsonObj.get("first"));
				j.put("contractSmallCategory", jsonObj.get("second"));
				if(null!=json){
					j.put("contractTemplateId", json.get("id"));
				}
			} else if (ContractFormInfoTemplateContract.CONTRACT_COL_PAY.equals(templateField.getRelationCode())) {
				JSONObject jsonObj = JSON.parseObject(templateField.getSecondSelectData());
				j.put("colPayType", jsonObj.get("first"));
				j.put("colPayTerm", jsonObj.get("second"));
				j.put("days", jsonObj.get("days"));
			} else if ("id".equals(templateField.getComponentType())) {
				j.put("id", templateField.getFieldValue());
			} else if ("upload".equals(templateField.getComponentType())) {
			} else {
				j.put(templateField.getFieldName(), templateField.getFieldValue());
			}
		}
		//把json串转换成一个对象
		ContractFormInfoEntity contractFormInfoEntity = JSONObject.toJavaObject(j, ContractFormInfoEntity.class);
		//String json = contractFormInfoService.templateDraft(contractFormInfoEntity, template.getJson());
		//导出pdf文件
		TemplateExportUntil templateExportUntil=new TemplateExportUntil();
		FileVO files=templateExportUntil.templateSave(contractFormInfoEntity,template,template.getJson(),j);
		return R.data(files.getLink());
	}


	/**
	 * 批量导入
	 */
	@PostMapping("/importBatchDraft")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "导入合同", notes = "传入excel")
	@Transactional(rollbackFor = Exception.class)
	public R importUser(MultipartFile file, String json, String contractTemplateId,String contractBigCategory,String contractSmallCategory) {
		//读取Excal 两个sheet数据
		List<ContractFormInfoImporter> read = ExcelUtil.read(file, 0, 5, ContractFormInfoImporter.class);
		List<ContractFormInfoImporterEx> read2 = ExcelUtil.read(file, 1, 1, ContractFormInfoImporterEx.class);
		read.forEach(readEx -> {
			if(("≤3年").equals(readEx.getContractPeriod())){
				readEx.setContractPeriod("小于等于3年");
			}else if((">3年").equals(readEx.getContractPeriod())){
				readEx.setContractPeriod("大于3年");
			}
			if(("票期<10天").equals(readEx.getColPayTerm())){
				readEx.setColPayTerm("票期小于10天");
			}else if(("10天≤票期<45天").equals(readEx.getColPayTerm())){
				readEx.setColPayTerm("票期大于等于10天小于45天");
			}else if(("45天≤票期").equals(readEx.getColPayTerm())){
				readEx.setColPayTerm("票期大于等于45天");
			}
			//contract_form合同形式
			R<List<DictBiz>> contract_form = bizClient.getList("contract_form");
			List<DictBiz> dataBiz = contract_form.getData();
			dataBiz.forEach(contractForm -> {
				if (readEx.getContractForm().equals(contractForm.getDictValue())) {
					readEx.setContractForm(contractForm.getDictKey());
				}
			});
			//收付款-收付款条件
			R<List<DictBiz>> col_pay_term = bizClient.getList("col_pay_term");
			List<DictBiz> dataBiz1 = col_pay_term.getData();
			dataBiz1.forEach(colPayTerm -> {
				if (readEx.getColPayType().equals(colPayTerm.getDictValue())) {
					readEx.setColPayType(colPayTerm.getId().toString());
				} else if (readEx.getColPayTerm().equals(colPayTerm.getDictValue())) {
					readEx.setColPayTerm(colPayTerm.getId().toString());
				}
			});
			//contract_period  合同期限
			R<List<DictBiz>> contract_period = bizClient.getList("contract_period");
			List<DictBiz> dataBiz2 = contract_period.getData();
			dataBiz2.forEach(contractPeriod -> {
				if (readEx.getContractPeriod().equals(contractPeriod.getDictValue())) {
					readEx.setContractPeriod(contractPeriod.getDictKey());
				}
			});
		});
		contractFormInfoService.importContractFormInfo(read,file,json,contractTemplateId,contractBigCategory,contractSmallCategory);
		return R.success("操作成功");
	}

	/**
	 * 批量送审
	 */
	@PostMapping("/submitBatch")
	@ApiOperationSupport(order = 12)
	@ApiOperation(value = "批量送审", notes = "传入依据和合同ids")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> submitBatch(@Valid @RequestBody ContractAccordingRequestVO according) {
		ContractAccordingEntity entity = new ContractAccordingEntity();
		ContractFormInfoEntity infoEntity=new ContractFormInfoEntity();
		//保存依据信息，把依据信息替换到json串中
		for (String id : according.getContractIds()) {
			BeanUtil.copy(according, entity);
			entity.setContractId(Long.parseLong(id));
			accordingService.save(entity);
			JSONObject jsonObj = JSON.parseObject(JSON.toJSONString(entity));
			infoEntity = contractFormInfoService.getById(id);
			String json=infoEntity.getJson();
			com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
			try {
				objects = JSONArray.parseArray(json);
				for (int i = 0; i < objects.size(); i++) {
					JSONObject temp = objects.getJSONObject(i);
					String relationCode = temp.getString("relationCode");
					if("ContractAccording".equals(relationCode)){
						temp.put("tableData",jsonObj);
						objects.set(i, temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json = objects.toJSONString();
			infoEntity.setJson(json);
			infoEntity.setContractStatus("30");
			contractFormInfoService.saveOrUpdate(infoEntity);
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(infoEntity));
	}


	/**
	 * 复用
	 */
	@PostMapping("/multiplex")
	@ApiOperationSupport(order = 13)
	@ApiOperation(value = "复用", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiplex')")
	public R<ContractFormInfoEntity> multiplex(@RequestParam Long id) {
		ContractFormInfoEntity entity = contractFormInfoService.getById(id);
		ContractFormInfoEntity contractFormInfo = new ContractFormInfoEntity();
		BeanUtil.copy(entity, contractFormInfo);
		String json=contractFormInfo.getJson();
		if (!Func.isEmpty(json)) {
			com.alibaba.fastjson.JSONArray objects = new com.alibaba.fastjson.JSONArray();
			try {
				objects = JSONArray.parseArray(json);
				for (int i = 0; i < objects.size(); i++) {
					JSONObject temp = objects.getJSONObject(i);
					String componentType = temp.getString("componentType");
					if("id".equals(componentType)){
						temp.put("fieldValue","");
						objects.set(i, temp);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			json = objects.toJSONString();
			contractFormInfo.setJson(json);
		}
		return R.data(contractFormInfo);
	}


	/**
	 * 修改
	 * 判断变更合同id是否为空 否则修改元年合同状态 为已消毁
	 */
	@PostMapping("/update")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:update')")
	public R update(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
		if (Func.isEmpty(contractFormInfo.getId())) {
			throw new ServiceException("id不能为空");
		}
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isNotEmpty(entity.getChangeContractId())) {
			formInfoMapper.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS,entity.getChangeContractId());
		}
		return R.status(contractFormInfoService.updateById(entity));
	}

	/**
	 * 导出后修改合同状态为待用印 并统计下载次数 修改下载状态
	 * 30>40
	 */
	@PostMapping("/updateExport")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateExport')")
	public R updateExport(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		Integer fileExportCount = infoEntity.getFileExportCount();
		fileExportCount += 1;
		contractFormInfoService.textExportCount(id, fileExportCount, FILE_EXPORT_CATEGORY);
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		return R.status(contractFormInfoService.updateExportStatus(CONTRACT_EXPORT_STATUS, id));
	}

	/**
	 * 复用导出合同文本
	 */
	@PostMapping("/repeatExport")
	@ApiOperationSupport(order = 6)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:repeatExport')")
	public R<ContractFormInfoResponseVO> repeatExport(@RequestParam Long id) {
		ContractFormInfoEntity infoEntity = contractFormInfoService.getById(id);
		Integer fileExportCount = infoEntity.getFileExportCount();
		fileExportCount += 1;
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		contractFormInfoService.textExportCount(id, fileExportCount, FILE_EXPORT_CATEGORY);
		infoEntity.setFileExportCount(fileExportCount);
		ContractFormInfoResponseVO formInfoResponseVO=ContractFormInfoWrapper.build().entityPV(infoEntity);
		return R.data(formInfoResponseVO);
	}



	/**
	 * 审核后修改状态为待导出
	 * 20>30
	 */
	@PostMapping("/updateAuditStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateAuditStatus')")
	public R auditStatus(@RequestParam Long id) {
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_AUDIT_QUALITY;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
	}

	/**
	 * 用印后修改状态为待签定
	 * 40>50
	 */
	@PostMapping("/updateSealStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateSealStatus')")
	public R sealStatus(@RequestParam Long id) {
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_SEAL_USING_INFO_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
	}

	/**
	 * 签订后修改状态待归档
	 * 50>60
	 */
	@PostMapping("/updateSigningStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateSigningStatus')")
	public R signingStatus(@RequestParam Long id) {
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_SIGNING_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
	}

	/**
	 * 归档后修改状态待评估
	 * 60>110
	 */
	@PostMapping("/updateArchiveStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateArchiveStatus')")
	public R archiveStatus(@RequestParam Long id) {
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_ARCHIVE_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
	}

	/**
	 * 评估后修改状态为待分析
	 * 100
	 */
	@PostMapping("/updateAssessmentStatus")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "修改", notes = "传入id")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:updateAssessmentStatus')")
	public R assessmentStatus(@RequestParam Long id) {
		if (Func.isEmpty(id)) {
			throw new ServiceException("id不能为空");
		}
		String contractStatus = CONTRACT_ASSESSMENT_STATUS;
		return R.status(contractFormInfoService.updateExportStatus(contractStatus, id));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:remove')")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.status(contractFormInfoService.deleteLogic(Func.toLongList(ids)));
	}

	/**
	 * 合同大类金额
	 */
	@PostMapping("/getAmountList")
	@ApiOperation(value = "合同大类金额", notes = "")
	public ArrayList<Map<String, String>> getAmountList() {
		List<ContractFormInfoEntity> list = contractFormInfoService.getAmountList();
		ArrayList<Map<String, String>> listMap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("name", list.get(i).getDictValue());
			map.put("value", String.valueOf(list.get(i).getContractAmount()));
			listMap.add(map);
		}
		return listMap;
	}

	/**
	 * 合同大类数量
	 */
	@PostMapping("/getNumList")
	@ApiOperation(value = "合同大类数量", notes = "传入ids")
	public ArrayList<Map<String, String>> getNumList() {
		List<ContractFormInfoEntity> list = contractFormInfoService.getNumList();
		ArrayList<Map<String, String>> listMap = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> map = new HashMap<>();
			map.put("name", list.get(i).getDictValue());
			map.put("value", String.valueOf(list.get(i).getCount()));
			listMap.add(map);
		}
		return listMap;
	}
	/**
	 * 合同统计分析分页
	 */
	@GetMapping("/listStatistics")
	@ApiOperationSupport(order = 2)
	@ApiOperation(value = "分页", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:listStatistics')")
	public R<IPage<ContractFormInfoResponseVO>> listStatistics(ContractFormInfoRequestVO contractFormInfo, Query query) {
		IPage<ContractFormInfoResponseVO> pages = contractFormInfoService.pageListStatistics(Condition.getPage(query), contractFormInfo);
		return R.data(pages);
	}
	/**
	 * 导出excel
	 * @param formInfoEntityList
	 * @param response
	 */
	@PostMapping("/exportTargetDataResultStatistics")
	@ApiOperationSupport(order = 7)
	@ApiOperation(value = "导出", notes = "")
	public void exportTargetDataResult(@RequestBody List<ContractFormInfoResponseVO> formInfoEntityList, HttpServletResponse response) {
		if(CollectionUtil.isNotEmpty(formInfoEntityList)){
			/* 导出文件名称 */
			String  fileName = "合同统计分析信息导出";
			WriteSheet sheet1 = new WriteSheet();
			/* 导出的sheet的名称 */
			sheet1.setSheetName("合同统计分析信息导出");
			sheet1.setSheetNo(0);
			/* 需要存入的数据 */
			List<List<Object>> data = new ArrayList<>();
			/* formInfoEntityList 表示要写入的数据 因为是前台显示列表 由前台进行传值，后期可以根据自己的需求进行改变 */
			for(ContractFormInfoResponseVO contractFormInfoEntity:formInfoEntityList){
				/* 属性 cloumns 表示一行，cloumns包含的数据是一行的数据
				  要将一行的每个值 作为list的一个属性存进到list里 ，数据要和展示的excel表头一致*/
				List<Object> cloumns = new ArrayList<Object>();
				/*合同类别*/
				cloumns.add(contractFormInfoEntity.getContractBigCategory());
				/*合同金额*/
				cloumns.add(contractFormInfoEntity.getContractAmount());
				/*签订数量*/
				cloumns.add(contractFormInfoEntity.getSigningCount());
				/*占比金额比例*/
				cloumns.add(contractFormInfoEntity.getAmountRatio());
				/*收支类型*/
				cloumns.add(contractFormInfoEntity.getColPayType());
				/*合同状态*/
				cloumns.add(bizClient.getValue("contract_status",contractFormInfoEntity.getContractStatus()).getData());
				/*签订单位*/
				cloumns.add(contractFormInfoEntity.getSigningEntity().getManageUnit());
				data.add(cloumns);
			}
			/* 表头名称，excel的表头 一个list对象为一行里的一个表头名称 */
			List<List<String>> headList = new ArrayList<List<String>>();
			/* 此处表头为一行要显示的所有表头，要和数据的顺序对应上  需要转换为list */
			List<String> head = Arrays.asList("合同类别","合同金额","签订数量","占比金额比例","收支类型","合同状态","签订单位");
			/* 为了生成一个独立的list对象，所进行的初始化 */
			List<String>  head2 =null;
			for( String head1:head){
				head2 = new ArrayList<>();
				/* 将表头的数据赋值进入list对象 */
				head2.add(head1);
				/* 将数据赋值进入最终要输出的表头 */
				headList.add(head2);
			}
			try {
				response.setContentType("application/vnd.ms-excel");
				response.setCharacterEncoding(Charsets.UTF_8.name());
				fileName = URLEncoder.encode(fileName, Charsets.UTF_8.name());
				response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
				EasyExcel.write(response.getOutputStream()).head(headList).sheet().doWrite(data);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 独立起草变更新增
	 */
	@PostMapping("/addChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:addChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> saveChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
//		contractFormInfo.setContractSoure("10");
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else if(Long.valueOf(CHANGE_CONTRACT_ID).equals(contractFormInfo.getChangeContractId())){
			entity.setId(null);
			entity.setChangeContractId(contractFormInfo.getId());
			//清空合同的文本导出次数记录
			entity.setFileExportCount(0);
			entity.setFileExportCategory(0);
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}else {
					contractBondPlan.setId(null);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
			contractFormInfoService.saveAccording(contractFormInfo);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				if (Func.isNotEmpty(performance.getId())){
					performance.setId(null);
				}
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				if (Func.isNotEmpty(performanceColPay.getId())){
					performanceColPay.setId(null);
				}
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//判断满足已变更新合同的条件 修改原合同状态
		if (CHANGE_REVIEW_STATUS.equals(contractFormInfo.getChangeCategory())&& APPROVE_REVIEW_STATUS.equals(contractFormInfo.getSubmitStatus())
				&& CONTRACT_REVIEW_STATUS.equals(contractFormInfo.getContractStatus())) {
			formInfoMapper.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS,contractFormInfo.getChangeContractId());
		}
		return R.data(ContractFormInfoWrapper.build().entityPV(entity));
	}


	/**
	 * 多方起草变更新增
	 */
	@PostMapping("/multiAddChange")
	@ApiOperationSupport(order = 5)
	@ApiOperation(value = "新增", notes = "传入contractFormInfo")
	@PreAuth("hasPermission('contractFormInfo:contractFormInfo:multiAddChange')")
	@Transactional(rollbackFor = Exception.class)
	public R<ContractFormInfoEntity> multiAddChange(@Valid @RequestBody ContractFormInfoRequestVO contractFormInfo) {
//		contractFormInfo.setContractSoure("20");
		//String sealName = StringUtils.join(contractFormInfo.getSealNameList(), ",");
		//contractFormInfo.setSealName(sealName);
		ContractFormInfoEntity entity = new ContractFormInfoEntity();
		BeanUtil.copy(contractFormInfo, entity);
		if (Func.isEmpty(contractFormInfo.getId())) {
			contractFormInfoService.save(entity);
		}else if(Long.valueOf(CHANGE_CONTRACT_ID).equals(contractFormInfo.getChangeContractId())) {
			entity.setId(null);
			entity.setChangeContractId(contractFormInfo.getId());
			//清空合同的文本导出次数记录
			entity.setFileExportCount(0);
			entity.setFileExportCategory(0);
			contractFormInfoService.save(entity);
		}else {
			contractFormInfoService.updateById(entity);
		}
		contractFormInfo.setId(entity.getId());
		/*保存相对方信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getCounterpart())) {
			contractFormInfoService.saveCounterpart(contractFormInfo);
		}
		/*保存保证金信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getContractBond())) {
			List<Long> list = new ArrayList<>();
			ContractBondPlanEntity contractBondPlan = new ContractBondPlanEntity();
			//删除保证金库脏数据
			contractBondService.deleteByContractId(contractFormInfo.getId());
			//删除保证金履约计划脏数据
			contractBondPlanService.deleteByContractId(contractFormInfo.getId());
			for (ContractBondEntity contractBondEntity : contractFormInfo.getContractBond()) {
				BeanUtil.copy(contractBondEntity, contractBondPlan);
				//判断是否为保证金库里面的保证金、或变更合同原合同的保证金
				if (Func.isEmpty(contractBondEntity.getId())) {
					contractBondService.save(contractBondEntity);
				}else {
					contractBondPlan.setId(null);
				}
				//保存保证金履约计划
				contractBondPlan.setContractId(contractFormInfo.getId());
				contractBondPlanService.save(contractBondPlan);
				list.add(contractBondEntity.getId());
			}
			//保存保证ID与合同ID进行关联
			contractBondService.saveBond(list, contractFormInfo.getId());
		}
		/*保存依据信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getAccording())) {
			ContractAccordingEntity contractAccording = contractFormInfo.getAccording().get(0);
			contractAccording.setContractId(contractFormInfo.getId());
			accordingService.updateById(contractAccording);
		}
		/*保存履约信息*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceList())) {
			//删除履约信息脏数据
			performanceService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceList().forEach(performance -> {
				if (Func.isNotEmpty(performance.getId())){
					performance.setId(null);
				}
				performance.setContractId(contractFormInfo.getId());
				performanceService.save(performance);
			});
		}
		/*保存履约计划收付款*/
		if (CollectionUtil.isNotEmpty(contractFormInfo.getPerformanceColPayList())) {
			//删除收付款脏数据
			contractPerformanceColPayService.deleteByContractId(contractFormInfo.getId());
			contractFormInfo.getPerformanceColPayList().forEach(performanceColPay -> {
				if (Func.isNotEmpty(performanceColPay.getId())){
					performanceColPay.setId(null);
				}
				performanceColPay.setContractId(contractFormInfo.getId());
				contractPerformanceColPayService.save(performanceColPay);
			});
		}
		//判断满足已变更新合同的条件 修改原合同状态
		if (CHANGE_REVIEW_STATUS.equals(contractFormInfo.getChangeCategory())&& APPROVE_REVIEW_STATUS.equals(contractFormInfo.getSubmitStatus())
				&& CONTRACT_REVIEW_STATUS.equals(contractFormInfo.getContractStatus())) {
			formInfoMapper.updateExportStatus(ORIGINAL_CONTRACT_CHANGE_ABANDONED_STATUS,contractFormInfo.getChangeContractId());
		}
		return R.data(contractFormInfo);
	}
}
