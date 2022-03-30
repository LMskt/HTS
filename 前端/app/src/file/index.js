

export function readimg(filename){
    // const giao=require.context('D:/hsttest',true,/\.png$|\.jpg$/)
    // return giao('./'+filename)
}

//防抖处理
export function Debonce(fn){
    let timer;
    return function (){
        clearTimeout(timer);
        timer=setTimeout(fn,2000)
    }

}


//节流处理
export function Throttle(fn){
    let timer;
    return function (){
        if (timer)
            return
        timer=setTimeout(function (){
            fn();
            timer=null;
        },2000)
    }
}

