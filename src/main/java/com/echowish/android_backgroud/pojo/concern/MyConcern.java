package com.echowish.android_backgroud.pojo.concern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyConcern {
    public Integer hostId;
    public Integer friId;
    public String name;
    public String headImage;
}
