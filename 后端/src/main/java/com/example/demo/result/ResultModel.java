package com.example.demo.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultModel {
    private Integer errorCode;
    private String msg;
    private Object data;

    public ResultModel(Integer errorCode,String msg,Object data){
        this.errorCode=errorCode;
        this.msg=msg;
        this.data=data;
    }
}
