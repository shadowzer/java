package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {
    String message = "Welcome to Spring MVC!";

    @RequestMapping(value = "hello")
    public ModelAndView showMessage(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        System.out.println("in controller");

        ModelAndView mv = new ModelAndView("helloworld");
        mv.addObject("message", message);
        mv.addObject("name", name);
        return mv;
    }

    @RequestMapping(value = "encode", method = RequestMethod.POST)
    public ModelAndView encode(@RequestParam(value = "text") String text) throws IOException {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        XZCompressorOutputStream xzOut = new XZCompressorOutputStream(bout);
        xzOut.write(text.getBytes());
        xzOut.close();

        String result = Base64.encodeBase64String(bout.toByteArray());

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("result", result);
        return mv;
    }

    @RequestMapping(value = "decode", method = RequestMethod.POST)
    public ModelAndView decode(@RequestParam(value = "text") String text) {
        String res;
        try
        {
            byte[] ans = Base64.decodeBase64(text);

            ByteArrayInputStream bIn = new ByteArrayInputStream(ans);
            XZCompressorInputStream xzIn = new XZCompressorInputStream(bIn);
            byte[] resultString = new byte[ans.length];
            xzIn.read(resultString);
            xzIn.close();

            res = new String(resultString);
        }
        catch (IOException e)
        {
            res = "exception";
        }

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("result", res);
        return mv;
    }
}