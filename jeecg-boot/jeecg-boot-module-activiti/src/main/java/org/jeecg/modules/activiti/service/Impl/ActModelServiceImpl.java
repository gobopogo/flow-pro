package org.jeecg.modules.activiti.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.repository.Model;
import org.activiti.explorer.util.XmlUtil;
import org.apache.commons.lang3.StringUtils;
import org.jeecg.modules.activiti.service.IActModelService;
import org.jeecg.modules.activiti.vo.ProcessDeploymentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * activiti 模型服务
 *
 * @author dongjb
 * @date 2021/11/15
 */
@Service
public class ActModelServiceImpl implements IActModelService {
    private final RepositoryService repositoryService;

    @Autowired
    public ActModelServiceImpl(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public String createModel(InputStreamReader reader, ProcessDeploymentVo deployment) {
        String processName = null;
        String processKey = null;
        String processDescription = null;

        if(deployment != null ) {
            if (StringUtils.isNotEmpty(deployment.getProcessName())) {
                processName = deployment.getProcessName();
            }
            if (StringUtils.isNotEmpty(deployment.getProcessKey())) {
                processKey = deployment.getProcessKey();
            }
            if (StringUtils.isNotEmpty(deployment.getProcessDescription())) {
                processDescription = deployment.getProcessDescription();
            }
        }

        try {
            XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
            XMLStreamReader xtr = xif.createXMLStreamReader(reader);
            BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

            if (bpmnModel.getMainProcess() == null || bpmnModel.getMainProcess().getId() == null) {
                System.out.println("err1");
            } else {
                if (bpmnModel.getLocationMap().isEmpty()) {
                    System.out.println("err2");
                } else {
                    if (StringUtils.isEmpty(processName)) {
                        if (StringUtils.isNotEmpty(bpmnModel.getMainProcess().getName())) {
                            processName = bpmnModel.getMainProcess().getName();
                        } else {
                            processName = bpmnModel.getMainProcess().getId();
                        }
                    }
                    Model modelData;
                    modelData = repositoryService.newModel();
                    ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
                    modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
                    modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
                    modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.isNotEmpty(processDescription) ? processDescription:processName);
                    modelData.setMetaInfo(modelObjectNode.toString());
                    modelData.setName(processName);
                    modelData.setKey(StringUtils.isNotEmpty(processKey) ? processKey:processName);

                    repositoryService.saveModel(modelData);

                    BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
                    ObjectNode editorNode = jsonConverter.convertToJson(bpmnModel);

                    repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes(StandardCharsets.UTF_8));
                    return modelData.getId();
                }
            }
        } catch (Exception e) {
            System.out.println("err4");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("err5");
                }
            }
        }
        return null;
    }
}
