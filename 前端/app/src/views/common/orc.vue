<template>
<div>
      <div style="height: 150px">
        <el-upload
            class="upload-demo"
            ref="upload"
            action=""
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :file-list="fileList"
            :on-change="wocao"
            :auto-upload="false">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
      </div>
  <div >
    <el-input type="textarea"
              :rows="6"
              resize="none"
              v-model="text"
              placeholder="请输入内容" >
    </el-input>
  </div>
</div>
</template>

<script>
import {carIdentify, transformBase64} from "@/api/api";

export default {
  name: "orc",
  data(){
    return{
      fileList:[],
      text:''
    }
  },
  methods:{
    wocao(file,fileList) {
      var that=this
      transformBase64(file.raw).then(
          data => {
            carIdentify(data).then(res => {
              // this.textlist=res
              // console.log(this.textlist)
              // that.changeto()
              for(let i=0;i<res.length;i++){
                // console.log(res[i].words)
                this.text+=res[i].words
              }
              console.log(this.text)
            })
          }
      )
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.text=''
    },
    handlePreview(file) {
      console.log(file);
    },
    changeto(){
      for(let i=0;i<this.textlist;i++){
        console.log(this.textlist[i].Getter)
      }
    }
  }
}
</script>

<style scoped>
</style>
