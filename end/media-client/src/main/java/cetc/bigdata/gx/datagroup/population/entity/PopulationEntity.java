package cetc.bigdata.gx.datagroup.population.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "pupolation", keepGlobalPrefix = true)
public class PopulationEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String createdTime;
    private String updateTime;
    private String node;
    private String region;
    private Integer hushu;
    private Integer hushu_jiating;
    private Integer hushu_jiti;
    private Integer contactPhone;
    private Integer renkoushu;
    private Integer renkoushu_nan;
    private Integer renkoushu_nv;
}
