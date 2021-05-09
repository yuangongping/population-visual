package cetc.bigdata.gx.datagroup.population.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MapInVo {
    @Length(max = 30, message = "长度不能超过30")
    private String nodeName;

}
