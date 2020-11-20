<template>
  <div class="main">
    <div>标签管理</div>
    <el-form
      :model="ruleForm"
      :rules="rules"
      ref="ruleForm"
      label-width="100px"
      class="demo-ruleForm"
    >
    <el-form-item label="标签" prop="tag" size="small" >
          <!-- <el-input v-model="item.tagId"></el-input> -->
          <!-- <el-checkbox v-model="item.id" >{{item.tagId}}</el-checkbox> -->
      <label style="display:block;float:left;padding-right:20px;cursor:pointer" title="添加库位标签" @click="openDialog()"><i class="el-icon-circle-plus"></i></label>
      <el-checkbox-group 
        v-model="ruleForm.faStorehouseTagSel">
        <el-checkbox v-for="obj in ruleForm.faStorehouseTagList" :label="obj.id" :key="obj.id" @change="editTagList(obj.id)">{{obj.name}}</el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item >
      <el-button @click="toFaStorehouseManage" size="small" :class="{changBg:ischange == 2}">取消</el-button>
      <el-button  @click="submitForm('ruleForm'),changBg(1)" size="small" :class="{changBg:ischange == 1}">确定</el-button>
    </el-form-item>
    </el-form>

    <el-dialog
      title="新增标签"
      :visible.sync="dialogVisible"
      width="700px">
      
      <el-form
      :model="tagRuleForm"
      :rules="tagRules"
      ref="tagRuleForm"
      label-width="100px"
      class="demo-ruleForm">
        <el-form-item label="名称" prop="name" size="small">
          <el-input v-model="tagRuleForm.name" style="width:300px"></el-input>
        </el-form-item>
        <el-form-item label="标签值" prop="value" size="small">
          <el-input v-model="tagRuleForm.value" style="width:300px"></el-input>
        </el-form-item>
        <!-- <el-form-item label="优先级" prop="priority" size="small">
          <el-input v-model="tagRuleForm.priority" style="width:300px"></el-input>
        </el-form-item> -->
        <el-form-item >
          <el-button @click="dialogVisible = false" size="small" >取消</el-button>
          <el-button  type="primary" @click="submitTagForm('tagRuleForm')" size="small" >确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {btn} from "@/components/minxs/button"
import { findFaStorehouse,updateFaStorehouse } from "@/api/faStorehouse";
import { getWareHouseTagList ,addFaWarehouseTag} from "@/api/faWareHouseTag";
export default {
  mixins:[btn],
  data() {
    const numValidator = (rule, value, callback) => {
      if (!value) {
        return callback();
      } else {
        
        if(!(/^\d+$/.test(value))){
          return callback(new Error("数量必须是数字"));
        }
        if(value>128){
           return callback(new Error("最大值不能超过128"));
        }
        return callback();
      }
    };
    return {
      ruleForm: {
        faStorehouseTag:[],
        faStorehouseTagList:[]
      },
      rules: {
       
      },
      dialogVisible: false,
      tagRuleForm: {
        name: "",
        value: "",
        priority: "",
        businessType:""
      },
      tagRules: {
        name: [
          { required: true, message: "请输入标签名称", trigger: "blur" },
          { max: 16, message: '最大长度为16个字符', trigger: 'blur' }
        ],
        value: [
          { required: true, message: "请输入标签值", trigger: "blur" },
          { max: 16, message: '最大长度为16个字符', trigger: 'blur' }
        ],
        priority: [
          {validator:numValidator,trigger: "blur"}
        ]
        
      },
    };
  },
  mounted() {
    let id = this.$route.query.id;
    this.find(id);
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          updateFaStorehouse(this[formName]).then((res)=>{
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.$router.push("/faStorehouseManage");
           })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    toFaStorehouseManage() {
      this.$router.push("/faStorehouseManage");
    },
    editTagList(tagId){
       let indexVal=-1;
       this.ruleForm.faStorehouseTag.forEach((v,index) =>{
            if(v.id==tagId){
               indexVal=index;
            }
       })
       if(indexVal!=-1){
           this.ruleForm.faStorehouseTag.splice(indexVal,1)
       }else{
          this.ruleForm.faStorehouseTagList.forEach((v)=>{
              if(v.id==tagId){
                 this.ruleForm.faStorehouseTag.push(v);
              }
          })
       }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    find(id) {
      this.ruleForm=null;
      getWareHouseTagList(new Object()).then(res =>{
          findFaStorehouse(id).then(res1 => {
             if (res1.code === "0") {
               res1.data.faStorehouseTagList = res.data;
               res1.data['faStorehouseTagSel']=[];
               if(res1.data.faStorehouseTag){
                 res1.data.faStorehouseTag.forEach((v)=>{
                   res1.data.faStorehouseTagSel.push(v.id);
                 })
               }else{
                 res1.data.faStorehouseTag=new Array();
               }
               this.ruleForm=res1.data;
             }
          });
      });
    },

    submitTagForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          addFaWarehouseTag(this[formName]).then((res)=>{
              this.$message({
                message: "创建成功",
                type: "success"
              });
              this.dialogVisible=false;
              this.find(this.$route.query.id);
          })
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    openDialog(){
      this.dialogVisible=true;
      this.tagRuleForm={name: "",
        value: "",
        priority: "",
        businessType:""}
    }

  }
};
</script>

<style lang="scss" scoped>
@import "~@/styles/systemStyle/addUser.scss";
.tree {
  width: 100%;
  height: 345px;
  border-radius: 4px;
  border: 1px solid rgba(230, 230, 230, 1);
  overflow: hidden;
  &>.el-tree{
      margin: 16px;
  }
}
.main>>>.el-textarea__inner{
    height: 88px;
}
.main>>>.el-button{
 margin-bottom:10px;
}
</style>