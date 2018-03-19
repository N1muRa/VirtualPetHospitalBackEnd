package com.hospital.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.entity.Medicine;
import com.hospital.service.MedicineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/19.
 */

@Controller
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    private final Log log = LogFactory.getLog(getClass());

//    获得指定页码的药品信息
    @RequestMapping(value = "admin/medicine/{page}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMedicine(@PathVariable String page) {
        int _page = Integer.parseInt(page);
        List medicines = medicineService.getAllMedicine();
        List<Medicine> subMedicines = null;
        int pageCount = ((medicines.size() - 1) / 10) + 1;
        int fromIndex = (_page - 1) * 10;
        if (medicines.size() >= fromIndex) {
            int toIndex = _page * 10;
            if (medicines.size() >= toIndex){
                subMedicines = medicines.subList(fromIndex, toIndex);
            } else {
                subMedicines = medicines.subList(fromIndex, medicines.size());
            }
        }
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = objectMapper.writeValueAsString(subMedicines);
        } catch (JsonProcessingException e) {
            log.error(e);
            e.printStackTrace();
        }
        return "{\"data\":"+json+",\"pages\":"+pageCount+"}";
    }

//    增加药品
    @RequestMapping(value = "admin/medicine", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveMedicine(@RequestBody Medicine medicine) {
        try {
            medicineService.saveMedicine(medicine);
        } catch (Exception e) {
            return "{\"result\":false}";
        }
        return "{\"result\":true}";
    }

//    更新药品
    @RequestMapping(value = "admin/medicine", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateMedicine(@RequestBody Medicine medicine) {
        try {
            medicineService.updateMedicine(medicine);
        } catch (Exception e) {
            return "{\"result\":false}";
        }
        return "{\"result\":true}";
    }

//    删除药品
    @RequestMapping(value = "admin/medicine", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteMedicine(@RequestBody Medicine medicine) {
        int id = medicine.getId();
        medicineService.deleteMedicine(id);
        return "{\"result\":true}";
    }
}
