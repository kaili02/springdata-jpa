package com.pgh.spring_data_jpa0414.controller;


import com.pgh.spring_data_jpa0414.entity.CommonShowFieldBean;
import com.pgh.spring_data_jpa0414.repository.CommonShowFieldBeanRepository;
import com.pgh.spring_data_jpa0414.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @author pgh
 * @data 2021/4/21 17:50
 *
 * 自定义列表字段控制器
 */
@RestController
@RequestMapping("/api/ueba/manager/commonshowfield")
public class CommonShowFieldController {
    @Autowired
    private CommonShowFieldBeanRepository commonShowFieldBeanRepository;


    /**
     *
     * @param params
     * @return
     */
    @ApiOperation(value = "存入自定义的列表字段信息", httpMethod = "POST")
    @RequestMapping(path = "/addfield",method = RequestMethod.POST)

    public Result addfield(@RequestBody Map<String, Object> params){
         Result res = new Result();
         //List<CommonShowFieldBean> byType = commonShowFieldBeanRepository.findByType(type);
//         if (null==byType|| byType.size()==0){
//
////           SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////
////             commonShowFieldBean.setCreateTime(df.format(new Date()));
////
////             commonShowFieldBean.setUpdateTime(df.format(new Date()));
//             commonShowFieldBean.setCreateTime(new Date());
//
//             commonShowFieldBean.setUpdateTime(new Date());
//             commonShowFieldBeanRepository.save(commonShowFieldBean);
//         }else{
//
//
//         }
        CommonShowFieldBean commonShowFieldBean=new CommonShowFieldBean();
//
//        String[] fieldsarray ={"0123","sb","12f"};
//        StringBuffer ff = new StringBuffer();
//            for(int i = 0;i<commonShowFieldBean.getFieldarray().length;i++){
//
//        ff.append(commonShowFieldBean.getFieldarray()[i]);
//
//        }
//            String ff1 = ff.toString();
       // commonShowFieldBean.setCreateTime(new Date());
       // commonShowFieldBean.setUpdateTime(new Date());
        commonShowFieldBean.setType(params.get("type").toString());
        commonShowFieldBean.setFieldsList(params.get("fieldsList").toString());
      //  commonShowFieldBean.setId(Integer.parseInt(params.get("id").toString()));
        commonShowFieldBeanRepository.save(commonShowFieldBean);
       // dao.update(commonShowFieldBean);
        return res;
    }

    /**
     *
     * @param param
     * @return
     *
     * 通过tyoe查找信息
     */
    @ApiOperation(value = "通过type查找信息", httpMethod = "POST")
    @RequestMapping(path = "/getfield",method = RequestMethod.POST)
    public Result getfield(@RequestBody Map<String,Object>param){
        Result res = new Result();
        System.out.println(param.get("type"));
        res.setResult(commonShowFieldBeanRepository.findByType(param.get("type").toString()));
       // System.out.println(commonShowFieldBeanRepository.findByType(pa));
        return res;
    }
}
