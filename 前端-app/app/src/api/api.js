import store from "@/store"
import axios from "axios";
import el from "element-ui/src/locale/lang/el";
import router from "@/router";

let localbaseURL='http://192.168.43.167:8281/';
// let localbaseURL='http://192.168.43.242:8281/';
axios.defaults.baseURL=localbaseURL;
//语音识别的access_token  创建时间2021/7/15
let voice_access_token="24.eaedcdc635ff7988152b8276270b0e78.2592000.1628920778.282335-24551434"

export var host=localbaseURL;
const Axios=axios.create({
    baseURL:localbaseURL,
    timeout:20000,
    // headers:{'jwt':store.getters.get_jwt()}
    // withCredentials:true   //开启后，服务器才能拿到你的cookie
})

Axios.interceptors.request.use(config=>{
    //如果storage里面存在token，将其放在请求头里
    if(store.getters.get_jwt!=''){
        config.headers.common['jwt']=store.getters.get_jwt;
    }else {
        //不存在token跳转登录页
        //登录页的axios不要用封装的Axios来写
        router.push("/homepage")
    }
    return config;
},error => {
    console.log(error)
   return Promise.reject(error)
})


Axios.interceptors.response.use(response=>{
    if(response.data.errorCode==1){
        alert("请求失败，请重试")
    }
    return response;
    },error => {
    return Promise.reject(error)
})


export function httpGet({
    url,
    params={}
}){
    return new Promise((resolve, reject) => {
       Axios({
           url:url,
           method:"get",
           params:params
       }).then(res=>{
           resolve(res.data)
       }).catch(err=>{
           reject(err)
           }
       )
    })
}

export function httpPost({
    url,
    params={}
}){
    return new Promise((resolve, reject) => {
        Axios({
            url:url,
            method:"post",
            params:params
        }).then(res=>{
            resolve(res.data)
        }).catch(err=>{
            reject(err)
        })
    })
}

export function transformBase64(file){
    return new Promise(((resolve, reject) => {
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            resolve(e.target.result)
        }
    }))
}

export function carIdentify(ocrImg){
    return new Promise(((resolve, reject) => {
        var params = new FormData();
        params.append('image',ocrImg);
        params.append("access_token", '24.92ad2d5ee6bb11d2b6071ce00b4f14a1.2592000.1628354839.282335-24518491');
        axios.post("https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token",params,
            {headers: {'content-type': 'application/x-www-form-urlencoded'} }
        ).then((res)=>{
            resolve(res.data.words_result)
        })
    }))
}










