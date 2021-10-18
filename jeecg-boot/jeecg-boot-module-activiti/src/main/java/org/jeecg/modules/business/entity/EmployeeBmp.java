package org.jeecg.modules.business.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: employee_bmp
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@Data
@TableName("employee_bmp")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="employee_bmp对象", description="employee_bmp")
public class EmployeeBmp implements Serializable {
    private static final long serialVersionUID = 1L;

	/**员工编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "员工编号")
    private java.lang.String id;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
    private java.lang.String name;
	/**性别*/
	@Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private java.lang.String gender;
	/**出生日期*/
	@Excel(name = "出生日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private java.util.Date birthday;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
    private java.lang.String idcard;
	/**婚姻状况*/
	@Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
    private java.lang.String wedlock;
	/**民族*/
	@Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private java.lang.Integer nationid;
	/**籍贯*/
	@Excel(name = "籍贯", width = 15)
    @ApiModelProperty(value = "籍贯")
    private java.lang.String nativeplace;
	/**政治面貌*/
	@Excel(name = "政治面貌", width = 15)
    @ApiModelProperty(value = "政治面貌")
    private java.lang.Integer politicid;
	/**邮箱*/
	@Excel(name = "邮箱", width = 15)
    @ApiModelProperty(value = "邮箱")
    private java.lang.String email;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
    private java.lang.String phone;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
    private java.lang.String address;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
    private java.lang.Integer departmentid;
	/**职称ID*/
	@Excel(name = "职称ID", width = 15)
    @ApiModelProperty(value = "职称ID")
    private java.lang.Integer joblevelid;
	/**职位ID*/
	@Excel(name = "职位ID", width = 15)
    @ApiModelProperty(value = "职位ID")
    private java.lang.Integer posid;
	/**聘用形式*/
	@Excel(name = "聘用形式", width = 15)
    @ApiModelProperty(value = "聘用形式")
    private java.lang.String engageform;
	/**最高学历*/
	@Excel(name = "最高学历", width = 15)
    @ApiModelProperty(value = "最高学历")
    private java.lang.String tiptopdegree;
	/**所属专业*/
	@Excel(name = "所属专业", width = 15)
    @ApiModelProperty(value = "所属专业")
    private java.lang.String specialty;
	/**毕业院校*/
	@Excel(name = "毕业院校", width = 15)
    @ApiModelProperty(value = "毕业院校")
    private java.lang.String school;
	/**入职日期*/
	@Excel(name = "入职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "入职日期")
    private java.util.Date begindate;
	/**在职状态*/
	@Excel(name = "在职状态", width = 15)
    @ApiModelProperty(value = "在职状态")
    private java.lang.String workstate;
	/**工号*/
	@Excel(name = "工号", width = 15)
    @ApiModelProperty(value = "工号")
    private java.lang.String workid;
	/**合同期限*/
	@Excel(name = "合同期限", width = 15)
    @ApiModelProperty(value = "合同期限")
    private java.lang.Double contractterm;
	/**转正日期*/
	@Excel(name = "转正日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "转正日期")
    private java.util.Date conversiontime;
	/**离职日期*/
	@Excel(name = "离职日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "离职日期")
    private java.util.Date notworkdate;
	/**合同起始日期*/
	@Excel(name = "合同起始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同起始日期")
    private java.util.Date begincontract;
	/**合同终止日期*/
	@Excel(name = "合同终止日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同终止日期")
    private java.util.Date endcontract;
	/**工龄*/
	@Excel(name = "工龄", width = 15)
    @ApiModelProperty(value = "工龄")
    private java.lang.Integer workage;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**流程状态*/
	@Excel(name = "流程状态", width = 15)
    @ApiModelProperty(value = "流程状态")
    private java.lang.String bpmStatus;
}
