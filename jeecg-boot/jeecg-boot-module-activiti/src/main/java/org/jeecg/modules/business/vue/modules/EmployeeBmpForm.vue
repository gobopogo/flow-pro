<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="员工姓名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="name">
              <a-input v-model="model.name" placeholder="请输入员工姓名"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="性别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="gender">
              <a-input v-model="model.gender" placeholder="请输入性别"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="出生日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="birthday">
              <j-date placeholder="请选择出生日期" v-model="model.birthday"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="身份证号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="idcard">
              <a-input v-model="model.idcard" placeholder="请输入身份证号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="婚姻状况" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="wedlock">
              <a-input v-model="model.wedlock" placeholder="请输入婚姻状况"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="民族" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nationid">
              <a-input-number v-model="model.nationid" placeholder="请输入民族" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="籍贯" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="nativeplace">
              <a-input v-model="model.nativeplace" placeholder="请输入籍贯"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="政治面貌" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="politicid">
              <a-input-number v-model="model.politicid" placeholder="请输入政治面貌" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="邮箱" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="email">
              <a-input v-model="model.email" placeholder="请输入邮箱"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="电话号码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="phone">
              <a-input v-model="model.phone" placeholder="请输入电话号码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="联系地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="address">
              <a-input v-model="model.address" placeholder="请输入联系地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="departmentid">
              <a-input-number v-model="model.departmentid" placeholder="请输入所属部门" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="职称ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="joblevelid">
              <a-input-number v-model="model.joblevelid" placeholder="请输入职称ID" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="职位ID" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="posid">
              <a-input-number v-model="model.posid" placeholder="请输入职位ID" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="聘用形式" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="engageform">
              <a-input v-model="model.engageform" placeholder="请输入聘用形式"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最高学历" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="tiptopdegree">
              <a-input v-model="model.tiptopdegree" placeholder="请输入最高学历"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="所属专业" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specialty">
              <a-input v-model="model.specialty" placeholder="请输入所属专业"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="毕业院校" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="school">
              <a-input v-model="model.school" placeholder="请输入毕业院校"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="入职日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="begindate">
              <j-date placeholder="请选择入职日期" v-model="model.begindate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="在职状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workstate">
              <a-input v-model="model.workstate" placeholder="请输入在职状态"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="工号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="workid">
              <a-input v-model="model.workid" placeholder="请输入工号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="合同期限" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contractterm">
              <a-input-number v-model="model.contractterm" placeholder="请输入合同期限" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="转正日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="conversiontime">
              <j-date placeholder="请选择转正日期" v-model="model.conversiontime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="离职日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="notworkdate">
              <j-date placeholder="请选择离职日期" v-model="model.notworkdate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="合同起始日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="begincontract">
              <j-date placeholder="请选择合同起始日期" v-model="model.begincontract"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="合同终止日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="endcontract">
              <j-date placeholder="请选择合同终止日期" v-model="model.endcontract"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="工龄" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="days">
              <a-input-number v-model="model.days" placeholder="请输入工龄" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="流程状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bpmStatus">
              <a-input v-model="model.bpmStatus" placeholder="请输入流程状态"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="busineeid" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="bpmId">
              <a-input v-model="model.bpmId" placeholder="请输入busineeid"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col v-if="showFlowSubmitButton" :span="24" style="text-align: center">
            <a-button @click="submitForm">提 交</a-button>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'EmployeeBmpForm',
    components: {
    },
    props: {
      //流程表单data
      formData: {
        type: Object,
        default: ()=>{},
        required: false
      },
      //表单模式：true流程表单 false普通表单
      formBpm: {
        type: Boolean,
        default: false,
        required: false
      },
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/business/employeeBmp/add",
          edit: "/business/employeeBmp/edit",
          queryById: "/business/employeeBmp/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return false
          }
          return true
        }
        return this.disabled
      },
      showFlowSubmitButton(){
        if(this.formBpm===true){
          if(this.formData.disabled===false){
            return true
          }
        }
        return false
      }
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
      //如果是流程中表单，则需要加载流程表单data
      this.showFlowData();
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      //渲染流程表单数据
      showFlowData(){
        if(this.formBpm === true){
          let params = {id:this.formData.dataId};
          getAction(this.url.queryById,params).then((res)=>{
            if(res.success){
              this.edit (res.result);
            }
          });
        }
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>