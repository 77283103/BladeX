package org.springblade.contract.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import feign.form.ContentType;
import lombok.AllArgsConstructor;
import org.springblade.abutment.feign.IAbutmentClient;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractSigningEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.mapper.ContractTemplateMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSigningService;
import org.springblade.contract.service.IContractTemplateService;
import org.springblade.contract.util.AsposeWordToPdfUtils;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.BeanUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.entity.TemplateEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 合同Feign实现类
 *
 * @author Chill
 */
@ApiIgnore
@RestController
@AllArgsConstructor
public class ContractClient implements IContractClient{

	private IFileClient fileClient;
	private IAbutmentClient abutmentClient;
    private IContractFormInfoService formInfoService;
	private ContractFormInfoMapper contractFormInfoMapper;
	private IContractTemplateService templateService;
	private ContractTemplateMapper templateMapper;
	private IContractSigningService contractSigningService;
	//private static final String ftlPath="D:/ftl/";//模板路径
	private static final String ftlPath="/ftl/";
    @Override
    @GetMapping(CONTRACT)
    public R<ContractFormInfoResponseVO> getById(Long id) {
        return R.data(formInfoService.getById(id));
    }

	@Override
	@GetMapping(CHOOSE)
	public R<List<ContractFormInfoEntity>> getChooseList() {
		return R.data(formInfoService.getChooseList());
	}

	@Override
	@PostMapping(TEMPLATE_UPDATE)
	public R<ContractTemplateResponseVO> templateUpdate(TemplateEntity entity) {
		ContractTemplateEntity templateFieldEntity=new ContractTemplateEntity();
		QueryWrapper<ContractTemplateEntity> queryWrapper = Condition.getQueryWrapper(templateFieldEntity)
			.eq("template_code",entity.getTemplateCode())
			.eq("is_deleted",0);
			/*.eq("template_status","10")
			.or().eq("template_status","40");*/
		List<ContractTemplateEntity> list = templateService.list(queryWrapper);
		for (ContractTemplateEntity v : list) {
			if (Func.isEmpty(templateMapper.latestById(v.getId()))) {
				v.setJson(entity.getJson());
				templateService.updateById(v);
			}
		}
		return null;
	}

	@Override
	@GetMapping(CONTRACT_SAVE)
	public R saveContractFormInfo(Long id, String status) {
		ContractFormInfoEntity contractFormInfo = contractFormInfoMapper.selectById(id);
		if(Func.isEmpty(contractFormInfo)){
			return R.fail("合同信息不存在");
		}
		if("30".equals(status)){
			if("1".equals(contractFormInfo.getContractForm())){
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String date = df.format(new Date());
				ContractSigningEntity entity = new ContractSigningEntity();
				entity.setContractId(contractFormInfo.getId());
				/*File filePDF=null;
				FileOutputStream fos = null;
				try {
					filePDF = new File(ftlPath + contractFormInfo.getContractListName() +date+ ".pdf");
					fos = new FileOutputStream(filePDF);
					R<String> token=abutmentClient.token();
					String url="http://sa.pec.com.cn:9080/common/file/downloadSinged?id="+contractFormInfo.getTextFilePdf()+"&token="+token.getData();
					fos.write(AsposeWordToPdfUtils.getUrlFileData(url));
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				File filePDFNew=new File(ftlPath + contractFormInfo.getContractListName() +date+ ".pdf");
				R<FileVO> filePDFVO = null;
				try {
					MultipartFile multipartFile = new MockMultipartFile("file", filePDFNew.getName(),
						ContentType.MULTIPART.toString(), new FileInputStream(filePDFNew));
					filePDFVO = fileClient.save(multipartFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				assert filePDFVO != null;
				entity.setTextFiles(filePDFVO.getData().getId() + ",");*/
				entity.setSubmissionType(" ");
				entity.setAddressee(" ");
				contractSigningService.save(entity);
				contractFormInfo.setContractStatus("60");
			}else if("3".equals(contractFormInfo.getContractForm())){
				contractFormInfo.setContractStatus("50");
			}else{
				contractFormInfo.setContractStatus("30");
			}
		}else{
			contractFormInfo.setContractStatus(status);
		}
		formInfoService.saveOrUpdate(contractFormInfo);
		return R.success("审核成功");
	}

	@Override
	@GetMapping(TEMPLATE_GET_ID)
	public R<ContractTemplateEntity> getByTemplateId(Long id) {
		ContractTemplateEntity templateFieldEntity=templateService.getById(id);
		return R.data(templateFieldEntity);
	}


}
