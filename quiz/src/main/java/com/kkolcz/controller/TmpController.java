package com.kkolcz.controller;

import com.kkolcz.dao.TmpDao;
import com.kkolcz.model.Tmp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/tmp")
public class TmpController {

    @Autowired
    TmpDao tmpDao;

    @RequestMapping(method = RequestMethod.GET)
    public String tmpPage(ModelMap model){
        List<Tmp> tmpList = tmpDao.findAll();
        model.addAttribute("tmpList",tmpList);
        return "tmpList";
    }


}
