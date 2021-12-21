package com.example.demo.result;

public class ResultSucess extends ResultModel{
    public ResultSucess(String msg){
        super(0,msg,null);
    }
    public ResultSucess(String msg,Object data){
        super(0,msg,data);
    }
}
