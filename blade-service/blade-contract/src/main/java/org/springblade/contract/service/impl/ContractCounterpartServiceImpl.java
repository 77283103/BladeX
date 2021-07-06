package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.excel.ContractCounterpartExcel;
import org.springblade.contract.excel.importbatchdraft.ContractCounterpartImportBatchDraftExcel;
import org.springblade.contract.mapper.ContractCounterpartMapper;
import org.springblade.contract.service.IContractCounterpartService;
import org.springblade.contract.vo.ContractCounterpartRequestVO;
import org.springblade.contract.vo.ContractCounterpartResponseVO;
import org.springblade.contract.wrapper.ContractCounterpartWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.Func;
import org.springblade.resource.feign.IFileClient;
import org.springblade.resource.vo.FileVO;
import org.springblade.system.feign.IDictBizClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 相对方管理 服务实现类
 *
 * @author XHB
 * @date : 2020-09-23 19:35:06
 */
@Service
@AllArgsConstructor
public class ContractCounterpartServiceImpl extends BaseServiceImpl<ContractCounterpartMapper, ContractCounterpartEntity> implements IContractCounterpartService {

    private IFileClient fileClient;
    private IDictBizClient dictBizClient;
    private ContractCounterpartMapper contractCounterpartMapper;

    @Override
    public IPage<ContractCounterpartResponseVO> pageList(IPage<ContractCounterpartEntity> page, ContractCounterpartRequestVO counterpart) {
        page = baseMapper.pageList(page, counterpart);
        IPage<ContractCounterpartResponseVO> pages = ContractCounterpartWrapper.build().entityPVPage(page);
        return pages;
    }

	@Override
	public List<ContractCounterpartEntity> getByUnifiedSocialCreditCode(String unifiedSocialCreditCode, String name) {
		return contractCounterpartMapper.selectByUnifiedSocialCreditCode(unifiedSocialCreditCode, name);
	}

	/**
     * 重写向对方vo方法返回附件 包装返回视图层
     *
     * @param id
     * @return
     */
    @Override
    public ContractCounterpartResponseVO getById(Long id) {
        ContractCounterpartEntity counterpartEntity = baseMapper.selectById(id);
        ContractCounterpartResponseVO counterpartResponseVO = ContractCounterpartWrapper.build().entityPV(counterpartEntity);
        //查询依据附件
        //@Func.isNoneBlank判断是否全为非空字符串
        if (Func.isNoneBlank(counterpartEntity.getAttachedFiles())) {
            R<List<FileVO>> result = fileClient.getByIds(counterpartEntity.getAttachedFiles());
            if (result.isSuccess()) {
                counterpartResponseVO.setCounterpartFilesVOList(result.getData());
            }
        }
        return counterpartResponseVO;
    }


    public List<ContractCounterpartEntity> findListByCodes(List<String> codes){
    	return baseMapper.findListByCodes(codes);
	}

	@Override
	public List<ContractCounterpartEntity> saveByBatchDraftExcel(List<ContractCounterpartImportBatchDraftExcel> contractCounterpartImportBatchDraftExcels, Long contractInfoId) {
		List<String>counterpartCodes = new ArrayList<>();
		contractCounterpartImportBatchDraftExcels.forEach(contractCounterpartImportBatchDraftExcel -> {
			counterpartCodes.add(contractCounterpartImportBatchDraftExcel.getUnifiedSocialCreditCode());
		});
		List<ContractCounterpartEntity>counterpartEntityList = new ArrayList<>();
		if(Func.isNotEmpty(counterpartCodes)){
			counterpartEntityList = baseMapper.findListByCodes(counterpartCodes);
			this.saveSettingListByContractInfoId(contractInfoId,counterpartEntityList);
		}
		return counterpartEntityList;
	}


	public void saveSettingListByContractInfoId(Long contractInfoId,List<ContractCounterpartEntity>counterpartEntityList){
    	if(Func.isEmpty(counterpartEntityList)){
    		return;
		}
		baseMapper.deleteByContractId(contractInfoId);
		baseMapper.saveListByContractId(contractInfoId, counterpartEntityList);
	}

	/**
     * 导入相对方信息
     *
     * @param data
     * @param isCovered
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importCounterpart(List<ContractCounterpartExcel> data, Boolean isCovered) {
    	List<ContractCounterpartEntity> list=new ArrayList<>();
        //将时间转化保存到数据库
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //遍历sheet页每条数据
        data.forEach(counterpartExcel -> {
            ContractCounterpartEntity contractCounterpartEntity = new ContractCounterpartEntity();
            //相对方类型
            if (!"-".equals(counterpartExcel.getCounterpartCategory()) && !"".equals(counterpartExcel.getCounterpartCategory()) && null!=counterpartExcel.getCounterpartCategory()) {
                contractCounterpartEntity.setCounterpartCategory(counterpartExcel.getCounterpartCategory()); }
            //相对方名称
            if (!"-".equals(counterpartExcel.getName()) && !"".equals(counterpartExcel.getName())) {
                contractCounterpartEntity.setName(counterpartExcel.getName()); }
//            //相对方性质
//            if (!"-".equals(counterpartExcel.getNatureCategory()) && !"".equals(counterpartExcel.getNatureCategory())) {
//                contractCounterpartEntity.setNatureCategory(counterpartExcel.getNatureCategory()); }
            //注册地址
            if (!"-".equals(counterpartExcel.getRegisteredAddress()) && !"".equals(counterpartExcel.getRegisteredAddress()) && null!=counterpartExcel.getRegisteredAddress()) {
                contractCounterpartEntity.setRegisteredAddress(counterpartExcel.getRegisteredAddress()); }
            //法定代表人
            if (!"-".equals(counterpartExcel.getLegalRepresentative()) && !"".equals(counterpartExcel.getLegalRepresentative()) && null!=counterpartExcel.getLegalRepresentative()) {
                contractCounterpartEntity.setLegalRepresentative(counterpartExcel.getLegalRepresentative()); }
            //身份证号
            if (!"-".equals(counterpartExcel.getIdNumber()) && !"".equals(counterpartExcel.getIdNumber()) && null!=counterpartExcel.getIdNumber()) {
                contractCounterpartEntity.setIdNumber(counterpartExcel.getIdNumber()); }
            //护照号
            if (!"-".equals(counterpartExcel.getPassportId()) && !"".equals(counterpartExcel.getPassportId()) && null!=counterpartExcel.getPassportId()) {
                contractCounterpartEntity.setPassportId(counterpartExcel.getPassportId()); }
            //成立日期
            if (!"-".equals(counterpartExcel.getEstablishDate()) && !"".equals(counterpartExcel.getEstablishDate()) && null!=counterpartExcel.getEstablishDate()) {
                try {
                    contractCounterpartEntity.setEstablishDate(
                            format.parse(counterpartExcel.getEstablishDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //注册资本
            if (!"-".equals(counterpartExcel.getRegisteredCapital()) && !"".equals(counterpartExcel.getRegisteredCapital()) && null!=counterpartExcel.getRegisteredCapital()) {
//				BigDecimal registeredCapital=null;
//				if (counterpartExcel.getRegisteredCapital().contains("人民币")) {
//					registeredCapital = BigDecimal.valueOf(
//							Long.valueOf(
//									counterpartExcel.getRegisteredCapital().
//											substring(0, counterpartExcel.getRegisteredCapital().indexOf("人民币"))), 6);
//				}
                contractCounterpartEntity.setRegisteredCapital(counterpartExcel.getRegisteredCapital());
            }
            //币种
            if (!"-".equals(counterpartExcel.getCurrencyCategory()) && !"".equals(counterpartExcel.getCurrencyCategory()) && null!=counterpartExcel.getCurrencyCategory()) {
                contractCounterpartEntity.setCurrencyCategory(
                		dictBizClient.getKey("bz",counterpartExcel.getCurrencyCategory()).getData()); }
            //社会统一信用代码
            if (!"-".equals(counterpartExcel.getUnifiedSocialCreditCode()) && !"".equals(counterpartExcel.getUnifiedSocialCreditCode()) && null!=counterpartExcel.getUnifiedSocialCreditCode()) {
                contractCounterpartEntity.setUnifiedSocialCreditCode(counterpartExcel.getUnifiedSocialCreditCode()); }
            //组织机构代码
            if (!"-".equals(counterpartExcel.getOrganizationCode()) && !"".equals(counterpartExcel.getOrganizationCode()) && null!=counterpartExcel.getOrganizationCode()) {
                contractCounterpartEntity.setOrganizationCode(counterpartExcel.getOrganizationCode()); }
            //电子印章序列号
            if (!"-".equals(counterpartExcel.getElectronicSealSerialId()) && !"".equals(counterpartExcel.getElectronicSealSerialId()) && null!=counterpartExcel.getElectronicSealSerialId()) {
                contractCounterpartEntity.setElectronicSealSerialId(counterpartExcel.getElectronicSealSerialId());
            }
            //相关联系人
            if (!"-".equals(counterpartExcel.getCounterpartCategory()) && !"".equals(counterpartExcel.getCounterpartCategory()) && null!=counterpartExcel.getCounterpartCategory()) {
                contractCounterpartEntity.setContactPersonPhone(counterpartExcel.getContactPersonName()); }
            //联系人电话
            if (!"-".equals(counterpartExcel.getContactPersonPhone()) && !"".equals(counterpartExcel.getContactPersonPhone()) && null!=counterpartExcel.getContactPersonPhone()) {
                contractCounterpartEntity.setContactPersonPhone(counterpartExcel.getContactPersonPhone()); }
            //联系人邮箱
            if (!"-".equals(counterpartExcel.getContactPersonMail()) && !"".equals(counterpartExcel.getContactPersonMail()) && null!=counterpartExcel.getContactPersonMail()) {
                contractCounterpartEntity.setContactPersonMail(counterpartExcel.getContactPersonMail()); }
            //开户银行
            if (!"-".equals(counterpartExcel.getDepositBank()) && !"".equals(counterpartExcel.getDepositBank()) && null!=counterpartExcel.getDepositBank()) {
                contractCounterpartEntity.setDepositBank(counterpartExcel.getDepositBank()); }
            //开户地址
            if (!"-".equals(counterpartExcel.getAccountOpeningAddress()) && !"".equals(counterpartExcel.getAccountOpeningAddress()) && null!=counterpartExcel.getAccountOpeningAddress()) {
                contractCounterpartEntity.setAccountOpeningAddress(counterpartExcel.getAccountOpeningAddress()); }
            //银行账号
            if (!"-".equals(counterpartExcel.getBankAccount()) && !"".equals(counterpartExcel.getBankAccount()) && null!=counterpartExcel.getBankAccount()) {
                contractCounterpartEntity.setBankAccount(counterpartExcel.getBankAccount()); }
            //付款方式
            if (!"-".equals(counterpartExcel.getPaymentMethod()) && !"".equals(counterpartExcel.getPaymentMethod()) && null!=counterpartExcel.getPaymentMethod()) {
                contractCounterpartEntity.setPaymentMethod(
                        dictBizClient.getValue("payment_method",counterpartExcel.getPaymentMethod()).getData());
            }
            //相关附件
            if (!"-".equals(counterpartExcel.getAttachedFiles()) && !"".equals(counterpartExcel.getAttachedFiles()) && null!=counterpartExcel.getAttachedFiles()) {
                contractCounterpartEntity.setAttachedFiles(counterpartExcel.getAttachedFiles());
            }
            //存续状态
            if (!"-".equals(counterpartExcel.getExistenceStatus()) && !"".equals(counterpartExcel.getExistenceStatus()) && null!=counterpartExcel.getExistenceStatus()) {
                contractCounterpartEntity.setExistenceStatus(
                        dictBizClient.getKey("existence_status",counterpartExcel.getExistenceStatus()).getData());
            }
			//单位类型
			if (!"-".equals(counterpartExcel.getClassification()) && !"".equals(counterpartExcel.getClassification()) && null!=counterpartExcel.getClassification()) {
				contractCounterpartEntity.setClassification(counterpartExcel.getClassification());
			}
			//黑名单标识
			if (!"-".equals(counterpartExcel.getBlacklistLogo()) && !"".equals(counterpartExcel.getBlacklistLogo()) && null!=counterpartExcel.getBlacklistLogo()) {
				contractCounterpartEntity.setBlacklistLogo(counterpartExcel.getBlacklistLogo());
			}
			//是否注销
			if (!"-".equals(counterpartExcel.getCancellation()) && !"".equals(counterpartExcel.getCancellation()) && null!=counterpartExcel.getCancellation()) {
				contractCounterpartEntity.setCancellation(counterpartExcel.getCancellation());
			}
			//备注
			if (!"-".equals(counterpartExcel.getRemarks()) && !"".equals(counterpartExcel.getRemarks()) && null!=counterpartExcel.getRemarks()) {
				contractCounterpartEntity.setRemarks(counterpartExcel.getRemarks());
			}
			//更名每月检视
			if (!"-".equals(counterpartExcel.getRenameReview()) && !"".equals(counterpartExcel.getRenameReview()) && null!=counterpartExcel.getRenameReview()) {
				contractCounterpartEntity.setRenameReview(counterpartExcel.getRenameReview());
			}
			//半角名称
			if (!"-".equals(counterpartExcel.getHalfWidthName()) && !"".equals(counterpartExcel.getHalfWidthName()) && null!=counterpartExcel.getHalfWidthName()) {
				contractCounterpartEntity.setHalfWidthName(counterpartExcel.getHalfWidthName());
			}

            if (contractCounterpartMapper.selectByName(contractCounterpartEntity.getUnifiedSocialCreditCode()).size()<=0){
                this.save(contractCounterpartEntity);
            }else {
                contractCounterpartMapper.updateByName(contractCounterpartEntity);
            }
        });
    }
}
