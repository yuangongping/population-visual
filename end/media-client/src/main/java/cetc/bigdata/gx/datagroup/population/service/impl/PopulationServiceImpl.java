package cetc.bigdata.gx.datagroup.population.service.impl;

import cetc.bigdata.gx.datagroup.population.vo.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cetc.bigdata.gx.datagroup.population.service.PopulationService;
import cetc.bigdata.gx.datagroup.population.dao.PopulationDao;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cetc.bigdata.gx.datagroup.population.entity.PopulationEntity;
import java.util.*;



@Service
public class PopulationServiceImpl extends ServiceImpl<PopulationDao,
        PopulationEntity> implements PopulationService {

    @Autowired
    private PopulationDao populationDao;

    @Override
    public List<MapOutVo> getMapData(MapInVo mapInVo) {
        QueryWrapper<MapOutVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node", mapInVo.getNodeName());
        return populationDao.getMapData(queryWrapper);
    }

    @Override
    public List<MapOutVo> getSex(SexInVo sexInVo) {
        QueryWrapper<MapOutVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node", sexInVo.getNodeName());
        return  sexInVo.getSex().equals("M")? populationDao.getSexM(queryWrapper):populationDao.getSexW(queryWrapper);
    }

    @Override
    public List<AgeOutVo> getAge(AgeInVo ageInVo){
        QueryWrapper<MapOutVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node", ageInVo.getNodeName());
        return  populationDao.getAge(queryWrapper);
    }

    @Override
    public List<BornDeadVo> getBornDead(AgeInVo ageInVo){
        QueryWrapper<MapOutVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node", ageInVo.getNodeName());
        return  populationDao.getBornDead(queryWrapper);
    }

    @Override
    public List<MarryVo> getMarry(AgeInVo ageInVo){
        QueryWrapper<MapOutVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node", ageInVo.getNodeName());
        return  populationDao.getMarry(queryWrapper);
    }

}
