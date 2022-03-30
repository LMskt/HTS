

export function readimg(filename){
    const giao=require.context('C:/Users/12108/Desktop/test',true,/\.png$|\.jpg$|\.jpeg$/)
    return giao('./'+filename)
}
