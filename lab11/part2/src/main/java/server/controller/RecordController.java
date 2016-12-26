package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import server.entity.Record;
import server.service.RecordService;

import java.util.Date;

@RestController
@RequestMapping("/")
public class RecordController {

    @Autowired
    RecordService recordService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAllRecords(Model model) {

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("records", recordService.getAll());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView saveRecord(@RequestParam(value = "username") String username,@RequestParam(value = "message") String message ) {

        Record record = new Record(username, (new Date(System.currentTimeMillis())).toString() , message);
        recordService.save(record);
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("records", recordService.getAll());

        return mv;
    }
}
