package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import org.springblade.contract.entity.ContractCounterpartEntity;
import org.springblade.contract.excel.ContractCounterpartExcel;
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

    /**
     * 导入相对方信息
     *
     * @param data
     * @param isCovered
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importCounterpart(List<ContractCounterpartExcel> data, Boolean isCovered) {
        //将时间转化保存到数据库
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //遍历sheet页每条数据
        data.forEach(counterpartExcel -> {
            ContractCounterpartEntity contractCounterpartEntity = new ContractCounterpartEntity();
            //相对方类型
            if (!"-".equals(counterpartExcel.getCounterpartCategory()) && !"".equals(counterpartExcel.getCounterpartCategory())) {
                contractCounterpartEntity.setCounterpartCategory(counterpartExcel.getCounterpartCategory()); }
            //相对方名称
            if (!"-".equals(counterpartExcel.getName()) && !"".equals(counterpartExcel.getName())) {
                contractCounterpartEntity.setName(counterpartExcel.getName()); }
//            //相对方性质
//            if (!"-".equals(counterpartExcel.getNatureCategory()) && !"".equals(counterpartExcel.getNatureCategory())) {
//                contractCounterpartEntity.setNatureCategory(counterpartExcel.getNatureCategory()); }
            //注册地址
            if (!"-".equals(counterpartExcel.getRegisteredAddress()) && !"".equals(counterpartExcel.getRegisteredAddress())) {
                contractCounterpartEntity.setRegisteredAddress(counterpartExcel.getRegisteredAddress()); }
            //法定代表人
            if (!"-".equals(counterpartExcel.getLegalRepresentative()) && !"".equals(counterpartExcel.getLegalRepresentative())) {
                contractCounterpartEntity.setLegalRepresentative(counterpartExcel.getLegalRepresentative()); }
            //身份证号
            if (!"-".equals(counterpartExcel.getIdNumber()) && !"".equals(counterpartExcel.getIdNumber())) {
                contractCounterpartEntity.setIdNumber(counterpartExcel.getIdNumber()); }
            //护照号
            if (!"-".equals(counterpartExcel.getPassportId()) && !"".equals(counterpartExcel.getPassportId())) {
                contractCounterpartEntity.setPassportId(counterpartExcel.getPassportId()); }
            //成立日期
            if (!"-".equals(counterpartExcel.getEstablishDate()) && !"".equals(counterpartExcel.getEstablishDate())) {
                try {
                    contractCounterpartEntity.setEstablishDate(
                            format.parse(counterpartExcel.getEstablishDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            //注册资本
            if (!"-".equals(counterpartExcel.getRegisteredCapital()) && !"".equals(counterpartExcel.getRegisteredCapital())) {
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
            if (!"-".equals(counterpartExcel.getCurrencyCategory()) && !"".equals(counterpartExcel.getCurrencyCategory())) {
                contractCounterpartEntity.setCurrencyCategory(
                		dictBizClient.getKey("bz",counterpartExcel.getCurrencyCategory()).getData()); }
            //社会统一信用代码
            if (!"-".equals(counterpartExcel.getUnifiedSocialCreditCode()) && !"".equals(counterpartExcel.getUnifiedSocialCreditCode())) {
                contractCounterpartEntity.setUnifiedSocialCreditCode(counterpartExcel.getUnifiedSocialCreditCode()); }
            //组织机构代码
            if (!"-".equals(counterpartExcel.getOrganizationCode()) && !"".equals(counterpartExcel.getOrganizationCode())) {
                contractCounterpartEntity.setOrganizationCode(counterpartExcel.getOrganizationCode()); }
            //电子印章序列号
            if (!"-".equals(counterpartExcel.getElectronicSealSerialId()) && !"".equals(counterpartExcel.getElectronicSealSerialId())) {
                contractCounterpartEntity.setElectronicSealSerialId(counterpartExcel.getElectronicSealSerialId());
            }
            //相关联系人
            if (!"-".equals(counterpartExcel.getCounterpartCategory()) && !"".equals(counterpartExcel.getCounterpartCategory())) {
                contractCounterpartEntity.setContactPersonPhone(counterpartExcel.getContactPersonName()); }
            //联系人电话
            if (!"-".equals(counterpartExcel.getContactPersonPhone()) && !"".equals(counterpartExcel.getContactPersonPhone())) {
                contractCounterpartEntity.setContactPersonPhone(counterpartExcel.getContactPersonPhone()); }
            //联系人邮箱
            if (!"-".equals(counterpartExcel.getContactPersonMail()) && !"".equals(counterpartExcel.getContactPersonMail())) {
                contractCounterpartEntity.setContactPersonMail(counterpartExcel.getContactPersonMail()); }
            //开户银行
            if (!"-".equals(counterpartExcel.getDepositBank()) && !"".equals(counterpartExcel.getDepositBank())) {
                contractCounterpartEntity.setDepositBank(counterpartExcel.getDepositBank()); }
            //开户地址
            if (!"-".equals(counterpartExcel.getAccountOpeningAddress()) && !"".equals(counterpartExcel.getAccountOpeningAddress())) {
                contractCounterpartEntity.setAccountOpeningAddress(counterpartExcel.getAccountOpeningAddress()); }
            //银行账号
            if (!"-".equals(counterpartExcel.getBankAccount()) && !"".equals(counterpartExcel.getBankAccount())) {
                contractCounterpartEntity.setBankAccount(counterpartExcel.getBankAccount()); }
            //付款方式
            if (!"-".equals(counterpartExcel.getPaymentMethod()) && !"".equals(counterpartExcel.getPaymentMethod())) {
                contractCounterpartEntity.setPaymentMethod(
                        dictBizClient.getValue("payment_method",counterpartExcel.getPaymentMethod()).getData());
            }
            //相关附件
            if (!"-".equals(counterpartExcel.getAttachedFiles()) && !"".equals(counterpartExcel.getAttachedFiles())) {
                contractCounterpartEntity.setAttachedFiles(counterpartExcel.getAttachedFiles());
            }
            //存续状态
            if (!"-".equals(counterpartExcel.getExistenceStatus()) && !"".equals(counterpartExcel.getExistenceStatus())) {
                contractCounterpartEntity.setExistenceStatus(
                        dictBizClient.getKey("existence_status",counterpartExcel.getExistenceStatus()).getData());
            }
            if (contractCounterpartMapper.selectByName(contractCounterpartEntity.getName()).size()<=0){
                this.save(contractCounterpartEntity);
            }else {
                contractCounterpartMapper.updateByName(contractCounterpartEntity);
            }
        });
    }
}
