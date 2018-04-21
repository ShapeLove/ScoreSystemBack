package com.shape.domain;

import lombok.Data;

/**
 * Created by Shape on 2018/4/5.
 */
@Data
public class ScoreVo {
    private Integer id;
    private Integer userId;
    private Integer chinese;
    private Integer math;
    private Integer englisth;
    private Integer computer;
    private Integer technology;
    private Integer pythsic;
    private String name;
}
