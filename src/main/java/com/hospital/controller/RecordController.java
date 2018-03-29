package com.hospital.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.entity.Record;
import com.hospital.entity.User;
import com.hospital.service.RecordService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by N1muRa on 2018/3/29.
 */
@Controller
public class RecordController {

    @Autowired
    private RecordService recordService;

    private final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "admin/record/{page}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getRecords(@PathVariable String page){
        int _page = Integer.parseInt(page);
        List recordList = recordService.getAllRecord();
        int pageCount = ((recordList.size() - 1) / 10) + 1;
        int fromIndex = (_page - 1) * 10;
        List<Record> records = null;
        if (recordList.size() > fromIndex){
            int toIndex = _page * 10;
            if (recordList.size() > toIndex) {
                records = recordList.subList(fromIndex, toIndex);
            } else {
                records = recordList.subList(fromIndex, recordList.size());
            }
        }
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = objectMapper.writeValueAsString(records);
        } catch (JsonProcessingException e) {
            log.error(e);
            e.printStackTrace();
        }
        return "{\"data\":"+json+",\"pages\":"+pageCount+"}";
    }

    @RequestMapping(value = "admin/record", method = RequestMethod.POST)
    @ResponseBody
    public String saveRecord(@RequestBody Record record) {
        recordService.saveRecord(record);
        return "{\"result\":true}";
    }

    @RequestMapping(value = "admin/record",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteRecord(@RequestBody Record record) {
        Integer id = record.getId();
        recordService.deleteRecord(id);
        return "{\"result\":true}";
    }
}
