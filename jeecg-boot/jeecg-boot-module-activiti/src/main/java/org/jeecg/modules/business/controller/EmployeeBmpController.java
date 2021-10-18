package org.jeecg.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.business.entity.EmployeeBmp;
import org.jeecg.modules.business.service.IEmployeeBmpService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: employee_bmp
 * @Author: jeecg-boot
 * @Date:   2021-10-18
 * @Version: V1.0
 */
@Api(tags="employee_bmp")
@RestController
@RequestMapping("/business/employeeBmp")
@Slf4j
public class EmployeeBmpController extends JeecgController<EmployeeBmp, IEmployeeBmpService> {
	@Autowired
	private IEmployeeBmpService employeeBmpService;
	
	/**
	 * 分页列表查询
	 *
	 * @param employeeBmp
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "employee_bmp-分页列表查询")
	@ApiOperation(value="employee_bmp-分页列表查询", notes="employee_bmp-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(EmployeeBmp employeeBmp,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<EmployeeBmp> queryWrapper = QueryGenerator.initQueryWrapper(employeeBmp, req.getParameterMap());
		Page<EmployeeBmp> page = new Page<EmployeeBmp>(pageNo, pageSize);
		IPage<EmployeeBmp> pageList = employeeBmpService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param employeeBmp
	 * @return
	 */
	@AutoLog(value = "employee_bmp-添加")
	@ApiOperation(value="employee_bmp-添加", notes="employee_bmp-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody EmployeeBmp employeeBmp) {
        employeeBmp.setBpmStatus("1");
		employeeBmpService.save(employeeBmp);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param employeeBmp
	 * @return
	 */
	@AutoLog(value = "employee_bmp-编辑")
	@ApiOperation(value="employee_bmp-编辑", notes="employee_bmp-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody EmployeeBmp employeeBmp) {
		employeeBmpService.updateById(employeeBmp);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "employee_bmp-通过id删除")
	@ApiOperation(value="employee_bmp-通过id删除", notes="employee_bmp-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		employeeBmpService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "employee_bmp-批量删除")
	@ApiOperation(value="employee_bmp-批量删除", notes="employee_bmp-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.employeeBmpService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "employee_bmp-通过id查询")
	@ApiOperation(value="employee_bmp-通过id查询", notes="employee_bmp-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		EmployeeBmp employeeBmp = employeeBmpService.getById(id);
		if(employeeBmp==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(employeeBmp);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param employeeBmp
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EmployeeBmp employeeBmp) {
        return super.exportXls(request, employeeBmp, EmployeeBmp.class, "employee_bmp");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, EmployeeBmp.class);
    }

}
