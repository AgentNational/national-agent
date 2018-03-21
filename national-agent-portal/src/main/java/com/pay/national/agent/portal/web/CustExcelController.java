package com.pay.national.agent.portal.web;

import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pay.national.agent.model.enums.ParentBusinessCode;
import com.pay.national.agent.portal.service.common.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pay.national.agent.common.bean.CreditCardUserModel;
import com.pay.national.agent.common.utils.ExcelUtil;
import com.pay.national.agent.common.utils.LogUtil;
import com.pay.national.agent.model.enums.BusinessCode;

import static com.pay.national.agent.model.enums.ParentBusinessCode.CREDIT_CARD;

/**
 * 合伙方客户数据导入
 * @author shuyan.qi
 * Date:2017年9月27日下午11:40:27
 */
@Controller
@RequestMapping("/custExcel")
public class CustExcelController {

    @Autowired
    private BusinessService businessService;

	@RequestMapping(value="/toUploadCreditCard.action")
	public ModelAndView toUpload(ModelAndView model){
		model.setViewName("custExcel/creditCardExcel");
		return model;
	}
	
	/**
     * 导入excel表
     * @version 1.0
     * @since 1.0
     */
    @RequestMapping(value = "/upload.action", method = RequestMethod.POST)
    public ModelAndView  uploadExcel(@RequestParam(value = "file",required=false) MultipartFile file,HttpServletRequest reqeust,ModelAndView model) {
    	String view = "";
        try{
        	String type = reqeust.getParameter("type");
            ParentBusinessCode parentBusinessCode = ParentBusinessCode.valueOf(type);

            switch (parentBusinessCode) {
			case CREDIT_CARD:
				List<CreditCardUserModel> list = ExcelUtil.importExcel(CreditCardUserModel.class,file.getInputStream(),1);
				String creditCardType = reqeust.getParameter("creditCardType");
				view = "custExcel/creditCardExcel";
                businessService.importUser(list,creditCardType);
				break;
            default:
                break;
			}
        	model.addObject("uploadResult", true);
        }catch(Exception e){
        	LogUtil.error(e.getMessage());
        	model.addObject("uploadResult", false);
        }
        model.setViewName(view);
        return model;
    }
    
    /**
     * 下载excel模板
     * @param type
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/download.action",method=RequestMethod.GET)
    public ModelAndView downloadExcel(@RequestParam("type")String type,HttpServletResponse response,ModelAndView model) {
    	String view = "";
        try{
            ParentBusinessCode parentBusinessCode = ParentBusinessCode.valueOf(type);
            BufferedInputStream input = null;
            switch (parentBusinessCode) {
			case CREDIT_CARD:
				input = new BufferedInputStream(ExcelUtil.excelModelbyClass(CreditCardUserModel.class, null, null));
				view = "custExcel/creditCardExcel";
            default:
                break;
            }
            
            response.setContentType("application/xls");
            response.addHeader("Content-Disposition", "attachment;filename="+new String(("custList").getBytes("UTF-8"),"iso-8859-1")+".xls");
            OutputStream os = response.getOutputStream();
            byte buffBytes[] = new byte[1024];
            int read = 0;
            while ((read = input.read(buffBytes)) != -1) {
                os.write(buffBytes, 0, read);
            }
            os.flush();
            os.close();
            input.close();
        }catch(Exception e){
        	LogUtil.error("downloadExcel catch Exception ",e);
        }
        model.setViewName(view);
        return model;
    }
}
