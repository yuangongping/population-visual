package cetc.bigdata.gx.datagroup.population.dao;

import cetc.bigdata.gx.datagroup.population.vo.AgeOutVo;
import cetc.bigdata.gx.datagroup.population.vo.BornDeadVo;
import cetc.bigdata.gx.datagroup.population.vo.MapOutVo;
import cetc.bigdata.gx.datagroup.population.vo.MarryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import cetc.bigdata.gx.datagroup.population.entity.PopulationEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface PopulationDao extends BaseMapper<PopulationEntity> {
    List<MapOutVo> getMapData(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
    List<MapOutVo> getSexM(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
    List<MapOutVo> getSexW(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
    List<AgeOutVo> getAge(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
    List<BornDeadVo> getBornDead(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);
    List<MarryVo> getMarry(@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}
