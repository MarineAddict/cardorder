package com.sh.wxapp.req.user;

import com.sh.wxapp.req.PageableRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * UserListReq
 *
 * @author xuqie
 * @version 1.0.0
 **/
@ApiModel("用户列表请求体")
@Data
public class UserListRequest extends PageableRequest {
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("用户名称")
    private String name;
}
