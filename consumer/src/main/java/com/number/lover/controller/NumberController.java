package com.number.lover.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.number.lover.consumer.service.TelPhoneNumberDao;

@RestController
@RequestMapping("/numbers")
public class NumberController {

    private TelPhoneNumberDao telPhoneNumberDao;

    public NumberController(TelPhoneNumberDao telPhoneNumberDao) {
        this.telPhoneNumberDao = telPhoneNumberDao;
    }

    @GetMapping("/allPhoneNumbers/{index}")
    public List<Map<String, Object>> getAllBooks(@PathVariable String index) {
        return telPhoneNumberDao.getAllNumbers(index);
    }
}
