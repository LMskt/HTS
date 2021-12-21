package com.example.demo.result;

public class ResultFailure extends ResultModel{
    public ResultFailure(String msg){
        super(1,msg,null);
    }
    public ResultFailure(String msg,Object data){
        super(1,msg,data);
    }
    public ResultFailure(Integer flag,String msg){
        super(flag,msg,null);
    }
}
