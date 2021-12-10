<template>
  <div>
    <bpmn-modeler
      ref="refNode"
      :xml="xml"
      :users="users"
      :groups="groups"
      :categorys="categorys"
      :is-view="false"
      @save="save"
    />
  </div>
</template>

<script>
import bpmnModeler from './package/index'
import { httpAction, getAction } from '@/api/manage'

export default {
  components: {
    bpmnModeler,
  },
  data() {
    return {
      url: {
        createanddeployment:'/activiti/models/createanddeployment',
      },
      deployment: {},
      xml: "", // Query the xml
      users: [
        { name: '张三', id: 'zhangsan' },
        { name: '李四', id: 'lisi' },
        { name: '王五', id: 'wangwu' }
      ],
      groups: [
        { name: 'web组', id: 'web' },
        { name: 'java组', id: 'java' },
        { name: 'python组', id: 'python' }
      ],
      categorys: [
        { name: 'OA', id: 'oa' },
        { name: '财务', id: 'finance' }
      ]
    };
  },
  mounted() {
    // this.getModelDetail()
  },
  methods: {
    getModelDetail() {
      fetch('https://cdn.jsdelivr.net/gh/dong-jianbin/flow-pro/ant-design-vue-jeecg/src/assets/Leave.bpmn20.xml')
        .then(response => {
          return response.text()
        }).then(xml => {
          this.xml = xml
      })
    },
    save(data) {
      let asignNodeList = this.$store.state.flowable.asignNodeList
      this.deployment.asignNodeList = asignNodeList

      let baseInfo = this.$store.state.flowable.baseInfo
      this.deployment.category = baseInfo.flowGroup
      this.deployment.processKey = baseInfo.flowName
      this.deployment.processName = baseInfo.flowName
      this.deployment.processDescription = baseInfo.flowRemark
      this.deployment.initiator = baseInfo.initiator

      this.deployment.xml = data.xml
      this.deployment.svg = data.svg

      let formConf = this.$store.state.flowable.formConf
      console.log("getformConf",formConf)
      this.deployment.businessTable = formConf.formRef

      httpAction(this.url.createanddeployment,this.deployment,'post').then(res => {
        if (res.success) {
          this.$message.success("操作成功");
          this.modalCancelVisible = false;
        }else {
          this.$message.error(res.message);
        }
      }).finally(console.log("模型创建完成"));

    },
    // 给父级页面提供得获取本页数据得方法
    getData() {
      this.$refs.refNode.save()
      return new Promise((resolve, reject) => {
        // this.$refs['elForm'].validate(valid => {
        //   if (!valid) {
        //     reject({ target: this.tabName})
        //     return
        //   }
        //   this.formData.flowImg = this.activeIcon
        //   resolve({ formData: this.formData, target: this.tabName})  // TODO 提交表单
        // })
        resolve({ formData: this.deployment, target: "processDesign"})  // TODO 提交表单
      })
    },
  },
};
</script>
