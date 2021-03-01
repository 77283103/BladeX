package org.springblade.contract.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractFormInfoEntity;
import org.springblade.contract.entity.ContractTemplateEntity;
import org.springblade.contract.mapper.ContractFormInfoMapper;
import org.springblade.contract.mapper.ContractTemplateMapper;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractTemplateService;
import org.springblade.contract.vo.ContractFormInfoResponseVO;
import org.springblade.contract.vo.ContractTemplateResponseVO;
import org.springblade.core.mp.support.Condition;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.system.entity.TemplateEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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

    private IContractFormInfoService formInfoService;
	private IContractFormInfoService contractFormInfoMapper;
	private IContractTemplateService templateService;
	private ContractTemplateMapper templateMapper;
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
		ContractFormInfoEntity contractFormInfo=contractFormInfoMapper.getById(id);
		if(Func.isEmpty(contractFormInfo.getId())){
			return R.fail("合同信息不存在");
		}
		if("30".equals(contractFormInfo.getSubmitStatus())){
			if("1".equals(contractFormInfo.getContractForm())){
				contractFormInfo.setContractStatus("60");
			}else if("3".equals(contractFormInfo.getContractForm())){
				contractFormInfo.setContractStatus("50");
			}else{
				contractFormInfo.setContractStatus("30");
			}
		}
		contractFormInfo.setSubmitStatus(status);
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
