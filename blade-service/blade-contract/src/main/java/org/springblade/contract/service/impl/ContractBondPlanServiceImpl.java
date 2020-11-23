package org.springblade.contract.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springblade.contract.entity.*;
import org.springblade.contract.mapper.*;
import org.springblade.contract.service.IContractBondPlanService;
import org.springblade.contract.service.IContractBondService;
import org.springblade.contract.service.IContractFormInfoService;
import org.springblade.contract.service.IContractSigningService;
import org.springblade.contract.vo.*;
import org.springblade.contract.wrapper.ContractBondPlanWrapper;
import org.springblade.contract.wrapper.ContractBondWrapper;
import org.springblade.contract.wrapper.ContractFormInfoWrapper;
import org.springblade.core.mp.base.BaseServiceImpl;
import org.springblade.core.tool.utils.Func;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 保证金 服务实现类
 *
 * @author szw
 * @date : 2020-11-04 18:28:11
 */
@Service
@AllArgsConstructor
public class ContractBondPlanServiceImpl extends BaseServiceImpl<ContractBondPlanMapper, ContractBondPlanEntity> implements IContractBondPlanService {
    private ContractFormInfoMapper formInfoMapper;
    private IContractFormInfoService formInfoService;
    private ContractCounterpartMapper counterpartMapper;
    private IContractSigningService signingService;
    private ContractSigningMapper signingMapper;

    @SneakyThrows
    @Override
    public IPage<ContractBondPlanResponseVO> pageList(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond) {
        page = baseMapper.pageList(page, contractBond);
        IPage<ContractBondPlanResponseVO> pages = ContractBondPlanWrapper.build().entityPVPage(page);
        List<ContractBondPlanResponseVO> records = pages.getRecords();
        List<ContractBondResponseVO> recordList = new ArrayList<>();
        for (ContractBondPlanResponseVO v : records) {
            ContractFormInfoEntity formInfoEntity = formInfoMapper.selectById(v.getContractId());
            if (Func.isNotEmpty(formInfoEntity)) {
                ContractFormInfoResponseVO formInfoResponseVO = ContractFormInfoWrapper.build().entityPV(formInfoEntity);
                v.setContractFormInfoEntity(formInfoResponseVO);
                List<ContractCounterpartEntity> counterpartEntity = counterpartMapper.selectByIds(formInfoEntity.getId());
                v.setCounterpartEntityList(counterpartEntity);
                ContractSigningEntity signingEntity = signingMapper.selectSigningById(formInfoEntity.getId());
                v.setSigningEntity(signingEntity);
                String endTime=plusDay(15, new SimpleDateFormat("yy-MM-dd").format(v.getPlanPayTime()));
                v.setPlanPayTimeEnd(endTime);
            }
        }
        return pages;
    }

    @Override
    public IPage<ContractBondPlanEntity> pageListSerious(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond) {
        page = baseMapper.pageListSerious(page, contractBond);
        return page;
    }

    @Override
    public IPage<ContractBondPlanEntity> pageListLong(IPage<ContractBondPlanEntity> page, ContractBondPlanRequestVO contractBond) {
        page = baseMapper.pageListLong(page, contractBond);
        return page;
    }

    @Override
    public void saveBond(List<Long> ids, Long id) {
        baseMapper.deleteBond(id);
        baseMapper.saveBond(ids, id);
    }

    @Override
    public ContractBondPlanResponseVO getById(Long id) {
        ContractBondPlanEntity bondPlanEntity=baseMapper.selectById(id);
        ContractBondPlanResponseVO bondPlanResponseVO=ContractBondPlanWrapper.build().entityPV(bondPlanEntity);
        return bondPlanResponseVO;
    }

    /**
     * 指定日期加上天数后的日期
     *
     * @param num     为增加的天数
     * @param newDate 创建时间
     * @return
     * @throws ParseException
     */
    public static String plusDay(int num, String newDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date currDate = format.parse(newDate);
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);
        currDate = ca.getTime();
        String endDate = format.format(currDate);
        return endDate;
    }



}
