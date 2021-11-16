package org.jeecg.modules.activiti.service;

import org.jeecg.modules.activiti.vo.ProcessDeploymentVo;

import java.io.InputStreamReader;

/**
 * activiti 模型服务
 *
 * @author dongjb
 * @date 2021/11/15
 */
public interface IActModelService {
    /**
     * 创建模型
     *
     * @param reader 输入文件读取
     */
    String createModel(InputStreamReader reader, ProcessDeploymentVo deployment);
}
