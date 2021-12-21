

export function readimg(filename){
    const giao=require.context('D:/桌面文件/test',true,/\.png$|\.jpg$/)
    return giao('./'+filename)
}
