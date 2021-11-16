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
import bpmnModeler from "workflow-bpmn-modeler";
import { httpAction, getAction } from '@/api/manage'

export default {
  components: {
    bpmnModeler,
  },
  data() {
    return {
      submitLoading: false,
      url: {
        createanddeployment:'/activiti/models/createanddeployment',
      },
      deployment: {},
      xml: "", // Query the xml
      users: [
        { name: "The Beatles", id: "1" },
        { name: "The Rolling Stones", id: "2" },
        { name: "Pink Floyed", id: "3" },
      ],
      groups: [
        { name: "Folk Music", id: "4" },
        { name: "Rock Music", id: "5" },
        { name: "Classical Music", id: "6" },
      ],
      categorys: [
        { name: "Music", id: "7" },
        { name: "Articles", id: "8" },
      ],
    };
  },
  mounted() {
    this.getModelDetail()
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
      console.log(data);  // { process: {...}, xml: '...', svg: '...' }

      this.submitLoading = true;
      this.deployment.category = data.process.category
      this.deployment.processKey = data.process.id
      this.deployment.processName = data.process.name
      this.deployment.processDescription = data.process.name + data.process.id
      this.deployment.xml = data.xml
      this.deployment.svg = data.svg

      httpAction(this.url.createanddeployment,this.deployment,'post').then(res => {
        if (res.success) {
          this.$message.success("操作成功");
          this.modalCancelVisible = false;
        }else {
          this.$message.error(res.message);
        }
      }).finally(()=>this.submitLoading = false);

    },
  },
};
</script>
