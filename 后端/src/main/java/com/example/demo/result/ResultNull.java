package com.example.demo.result;

public class ResultNull extends ResultModel {
    public ResultNull(String msg){
        super(2,msg,null);
    }
    public ResultNull (String msg,Object data){
        super(2,msg,data);
    }
}
