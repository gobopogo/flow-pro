<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button  :disabled="disabled"  @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button  :disabled="disabled"  type="primary" icon="download" @click="handleExportXls('employee_bmp')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button  :disabled="disabled"  type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <a-dropdown  :disabled="disabled"  v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="startProcess(record)"><a-icon type="login"/>发起流程</a-menu-item>
          <a-menu-item key="2" @click="startProcess(record)"><a-icon type="check"/>审批通过</a-menu-item>
          <a-menu-item key="3" @click="startProcess(record)"><a-icon type="rollback"/>审批驳回</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 流程操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a  :disabled="disabled"   @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item  :disabled="disabled"   v-if="record.bpmStatus === '1'">
                <a  :disabled="disabled"   @click="startProcess(record)">发起流程</a>
              </a-menu-item>
              <a-menu-item  :disabled="disabled"  >
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a  :disabled="disabled" >删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <employee-bmp-modal ref="modalForm" @ok="modalFormOk"></employee-bmp-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import EmployeeBmpModal from './modules/EmployeeBmpModal'
  import { postAction } from '@/api/manage'

  export default {
    name: 'EmployeeBmpList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      EmployeeBmpModal
    },
    data () {
      return {
        description: 'employee_bmp管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'员工姓名',
            align:"center",
            dataIndex: 'name'
          },
          {
            title:'性别',
            align:"center",
            dataIndex: 'gender'
          },
          {
            title:'出生日期',
            align:"center",
            dataIndex: 'birthday',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'身份证号',
            align:"center",
            dataIndex: 'idcard'
          },
          {
            title:'婚姻状况',
            align:"center",
            dataIndex: 'wedlock'
          },
          {
            title:'民族',
            align:"center",
            dataIndex: 'nationid'
          },
          {
            title:'籍贯',
            align:"center",
            dataIndex: 'nativeplace'
          },
          {
            title:'政治面貌',
            align:"center",
            dataIndex: 'politicid'
          },
          {
            title:'邮箱',
            align:"center",
            dataIndex: 'email'
          },
          {
            title:'电话号码',
            align:"center",
            dataIndex: 'phone'
          },
          {
            title:'联系地址',
            align:"center",
            dataIndex: 'address'
          },
          {
            title:'所属部门',
            align:"center",
            dataIndex: 'departmentid'
          },
          {
            title:'职称ID',
            align:"center",
            dataIndex: 'joblevelid'
          },
          {
            title:'职位ID',
            align:"center",
            dataIndex: 'posid'
          },
          {
            title:'聘用形式',
            align:"center",
            dataIndex: 'engageform'
          },
          {
            title:'最高学历',
            align:"center",
            dataIndex: 'tiptopdegree'
          },
          {
            title:'所属专业',
            align:"center",
            dataIndex: 'specialty'
          },
          {
            title:'毕业院校',
            align:"center",
            dataIndex: 'school'
          },
          {
            title:'入职日期',
            align:"center",
            dataIndex: 'begindate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'在职状态',
            align:"center",
            dataIndex: 'workstate'
          },
          {
            title:'工号',
            align:"center",
            dataIndex: 'workid'
          },
          {
            title:'合同期限',
            align:"center",
            dataIndex: 'contractterm'
          },
          {
            title:'转正日期',
            align:"center",
            dataIndex: 'conversiontime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'离职日期',
            align:"center",
            dataIndex: 'notworkdate',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'合同起始日期',
            align:"center",
            dataIndex: 'begincontract',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'合同终止日期',
            align:"center",
            dataIndex: 'endcontract',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'工龄',
            align:"center",
            dataIndex: 'workage'
          },
          {
            title:'流程状态',
            align:"center",
            dataIndex: 'bpmStatus'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/business/employeeBmp/list",
          delete: "/business/employeeBmp/delete",
          deleteBatch: "/business/employeeBmp/deleteBatch",
          exportXlsUrl: "/business/employeeBmp/exportXls",
          importExcelUrl: "business/employeeBmp/importExcel",
          startProcess: '/act/process/extActProcess/startMutilProcess'
        },
        //代码生成后需手动设置流程编码
        flowCode: 'dev_employee_bmp_001',
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      startProcess(record){
        this.$confirm({
          title:'提示',
          content:'确认提交流程吗?',
          onOk:()=>{
            let params = {
              flowCode: this.flowCode,
              id: record.id,
              formUrl: 'business/modules/EmployeeBmpForm',
              formUrlMobile: ''
            }
            postAction(this.url.startProcess, params).then(res=>{
              if(res.success){
                this.$message.success(res.message);
                this.loadData();
                this.onClearSelected();
              }else{
                this.$message.warning(res.message);
              }
            }).catch((e)=>{
              this.$message.warning('不识别的请求!');
            })
          }
        })
      },
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'name',text:'员工姓名'})
        fieldList.push({type:'string',value:'gender',text:'性别'})
        fieldList.push({type:'date',value:'birthday',text:'出生日期'})
        fieldList.push({type:'string',value:'idcard',text:'身份证号'})
        fieldList.push({type:'string',value:'wedlock',text:'婚姻状况'})
        fieldList.push({type:'int',value:'nationid',text:'民族'})
        fieldList.push({type:'string',value:'nativeplace',text:'籍贯'})
        fieldList.push({type:'int',value:'politicid',text:'政治面貌'})
        fieldList.push({type:'string',value:'email',text:'邮箱'})
        fieldList.push({type:'string',value:'phone',text:'电话号码'})
        fieldList.push({type:'string',value:'address',text:'联系地址'})
        fieldList.push({type:'int',value:'departmentid',text:'所属部门'})
        fieldList.push({type:'int',value:'joblevelid',text:'职称ID'})
        fieldList.push({type:'int',value:'posid',text:'职位ID'})
        fieldList.push({type:'string',value:'engageform',text:'聘用形式'})
        fieldList.push({type:'string',value:'tiptopdegree',text:'最高学历'})
        fieldList.push({type:'string',value:'specialty',text:'所属专业'})
        fieldList.push({type:'string',value:'school',text:'毕业院校'})
        fieldList.push({type:'date',value:'begindate',text:'入职日期'})
        fieldList.push({type:'string',value:'workstate',text:'在职状态'})
        fieldList.push({type:'string',value:'workid',text:'工号'})
        fieldList.push({type:'number',value:'contractterm',text:'合同期限'})
        fieldList.push({type:'date',value:'conversiontime',text:'转正日期'})
        fieldList.push({type:'date',value:'notworkdate',text:'离职日期'})
        fieldList.push({type:'date',value:'begincontract',text:'合同起始日期'})
        fieldList.push({type:'date',value:'endcontract',text:'合同终止日期'})
        fieldList.push({type:'int',value:'workage',text:'工龄'})
        fieldList.push({type:'string',value:'bpmStatus',text:'流程状态'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>