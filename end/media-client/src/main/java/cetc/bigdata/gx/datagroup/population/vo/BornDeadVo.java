package cetc.bigdata.gx.datagroup.population.vo;

import lombok.Data;

/**
 * description:
 * Created by YGP on 2021-5-9
 */
@Data
public class BornDeadVo {
    private String name;
    private Integer born;
    private Integer  bornM;
    private Integer  bornW;
    private Integer  dead;
    private Integer  deadM;
    private Integer  deadW;
}
