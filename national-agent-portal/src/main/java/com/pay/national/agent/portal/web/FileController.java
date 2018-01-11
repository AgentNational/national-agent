package com.pay.national.agent.portal.web;

import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.pay.national.agent.common.bean.EmployeeDTO;
import com.pay.national.agent.common.bean.ImportModel;
import com.pay.national.agent.common.utils.ExcelUtil;

@Controller
@RequestMapping("/file")
public class FileController {

    
    private static Logger logger = LoggerFactory.getLogger(ImportController.class);
    /**
     * 
     */
    @RequestMapping(value = "/toUploadExcel.action")
    public  String  toUploadExcel() {
        return "file/uploadExcel";
    }
    /**
     * 导入excel表
     * @version 1.0
     * @since 1.0
     */
    @RequestMapping(value = "/importEmployee.action", method = RequestMethod.POST)
    public @ResponseBody String  uploadExcel(@RequestParam(value = "file",required=false) MultipartFile file, HttpServletRequest request) {
        try{
            List<EmployeeDTO> employeeDTOList  = ExcelUtil.importExcel(EmployeeDTO.class, file.getInputStream(),1);
            //可做持久化操作，现只打印观察
            for(EmployeeDTO employeeDTO : employeeDTOList){
                logger.info("name=" + employeeDTO.getName() + ",telephone=" + employeeDTO.getTelephone()+",sex=" + employeeDTO.getSex());
            }
        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
    /**
     * 导出excel模版
     * @version 1.0
     * @since 1.0
     */
    @RequestMapping(value = "/downloadEmployeeModel.action", method = RequestMethod.GET)
    public @ResponseBody String downloadEmployeeModel(HttpServletResponse response) {
        try{
            response.setContentType("application/xls");
            response.addHeader("Content-Disposition", "attachment;filename="+new String(("eeelist").getBytes("UTF-8"),"iso-8859-1")+".xls");
            Map<Integer,String[]> paramMap = new HashMap<Integer,String[]>();
            //excel第三行为下拉选择框
            paramMap.put(2, new String[]{"man","women"});
            BufferedInputStream input = new BufferedInputStream(ExcelUtil.excelModelbyClass(EmployeeDTO.class, paramMap, null));
            byte buffBytes[] = new byte[1024];
            OutputStream os = response.getOutputStream();
            int read = 0;
            while ((read = input.read(buffBytes)) != -1) {
                os.write(buffBytes, 0, read);
            }
            os.flush();
            os.close();
            input.close();
            return "下载成功";
        }catch(Exception e){
            logger.error("downloadEmployeeModel() catch Exception ",e);
            return "下载失败";
        }
    }
    
    /**
     * 导出excel模版
     * @version 1.0
     * @since 1.0
     */
    @RequestMapping(value = "/downloadEmployee.action", method = RequestMethod.GET)
    public @ResponseBody String downloadEmployee(HttpServletResponse response) {
        try{
            response.setContentType("application/xls");
            response.addHeader("Content-Disposition", "attachment;filename="+new String(("fdsf").getBytes("UTF-8"),"iso-8859-1")+".xls");
            List<ImportModel> employeeDTOList = new ArrayList<>();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setName("aaaa");
            employeeDTO.setSex("man");
            employeeDTO.setTelephone("256461");
            employeeDTOList.add(employeeDTO);
            EmployeeDTO employeeDTO1 = new EmployeeDTO();
            employeeDTO1.setName("bbbb");
            employeeDTO1.setSex("woman");
            employeeDTO1.setTelephone("1235256461");
            employeeDTOList.add(employeeDTO1);
            BufferedInputStream input = new BufferedInputStream(ExcelUtil.exportExcel(employeeDTOList, EmployeeDTO.class));
            byte buffBytes[] = new byte[1024];
            OutputStream os = response.getOutputStream();
            int read = 0;
            while ((read = input.read(buffBytes)) != -1) {
                os.write(buffBytes, 0, read);
            }
            os.flush();
            os.close();
            input.close();
            return "下载成功";
        }catch(Exception e){
            logger.error("downloadEmployeeModel() catch Exception ",e);
            return "下载失败";
        }
    }

}
