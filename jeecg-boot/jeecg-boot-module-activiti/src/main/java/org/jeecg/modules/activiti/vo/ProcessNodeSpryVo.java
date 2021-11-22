package org.jeecg.modules.activiti.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProcessAsignNodeVo {
    /**
     * 流程节点标识
     */
    private String nodeId;

    /**
     * 流程定义key
     */
    private spry spry;

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