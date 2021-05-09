package cetc.bigdata.gx.datagroup.population.controller;

import cetc.bigdata.gx.datagroup.population.service.PopulationService;
import cetc.bigdata.gx.datagroup.population.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cetc.bigdata.gx.datagroup.population.util.R;
import javax.validation.Valid;


@RestController
@RequestMapping("population")
public class PopulationController {
    @Autowired
    PopulationService populationService;

    @GetMapping("/map")
    public R getRenKou(@Valid MapInVo mapInVo) {
        return R.ok().data( populationService.getMapData(mapInVo));
    }

    @GetMapping("/sex")
    public R getSex(@Valid SexInVo sexInVo) {
        return R.ok().data( populationService.getSex(sexInVo));
    }

    @GetMapping("/age")
    public R getAge(@Valid AgeInVo ageInVo) {
        return R.ok().data( populationService.getAge(ageInVo));
    }


    @GetMapping("/born-dead")
    public R getBornDead(@Valid AgeInVo ageInVo) {
        return R.ok().data( populationService.getBornDead(ageInVo));
    }

    @GetMapping("/marry")
    public R getMarry(@Valid AgeInVo ageInVo) {
        return R.ok().data( populationService.getMarry(ageInVo));
    }
}
