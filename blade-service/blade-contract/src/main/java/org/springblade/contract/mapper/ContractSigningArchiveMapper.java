package org.springblade.contract.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springblade.contract.entity.ContractSigningArchiveEntity;
import org.springblade.contract.vo.ContractSigningArchiveRequestVO;

import java.util.List;

/**
 * 合同签订表 Mapper 接口
 *
 * @author liyj
 * @date : 2020-09-23 19:27:05
 */
public interface ContractSigningArchiveMapper extends BaseMapper<ContractSigningArchiveEntity> {

	/**
	 * 分页查询
	 * @param page
	 * @param signing
	 * @retur
	 */
	IPage<ContractSigningArchiveEntity> pageList(IPage<ContractSigningArchiveEntity> page, ContractSigningArchiveRequestVO signing);

	/**
	 * 根据签订id查询关联的归档文件目录
	 * 一对多
	 * @param id
	 * @return
	 */
	List<ContractSigningArchiveEntity> selectBySigningArchiveId(@Param("id") Long id);
}
