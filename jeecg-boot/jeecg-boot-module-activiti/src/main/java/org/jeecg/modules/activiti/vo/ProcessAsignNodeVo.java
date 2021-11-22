package org.jeecg.modules.activiti.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessDeploymentVo {
    /**
     * 流程类别
     */
    private String category;

    /**
     * 流程定义key
     */
    private String processKey;

    /**
     * 流程定义名字
     */
    private String processName;

    /**
     * 流程描述
     */
    private String processDescription;

    /**
     * 权限
     */
    private String initiator;

    /**
     * 流程定义内容
     */
    private String xml;

    /**
     * 流程定义svg
     */
    private String svg;

    /**
     * 节点指派人列表
     */
    private Object[]  asignNodeList;

}

      asignNode: {
              nodeId: '',
              spry: {
              //选中的用户
              userIds: '',
              roleIds: '',
              departmentIds: '',
              departmentManageIds: '',
              formVariables: '',
              chooseSponsor: false,
              chooseDepHeader: false
              },
              },