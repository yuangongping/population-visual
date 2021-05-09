package cetc.bigdata.gx.datagroup.population.service;

import cetc.bigdata.gx.datagroup.population.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import cetc.bigdata.gx.datagroup.population.entity.PopulationEntity;

import java.util.List;


public interface PopulationService extends IService<PopulationEntity> {
    List<MapOutVo> getMapData(MapInVo addVo);
    List<MapOutVo> getSex(SexInVo sexInVo);
    List<AgeOutVo> getAge(AgeInVo ageInVo);
    List<BornDeadVo> getBornDead(AgeInVo ageInVo);
    List<MarryVo> getMarry(AgeInVo ageInVo);
}
