package cetc.bigdata.gx.datagroup.population.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * description:
 * Created by YGP on 2021-5-8
 */
@Data
public class AgeInVo {
    @Length(max = 30, message = "长度不能超过30")
    private String nodeName;
}
