package com.example.produce.controller;

import com.example.produce.entity.ProduceFunction;
import com.example.produce.service.ProduceFunctionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */

@RestController
@RequestMapping(value = "/function", produces = "application/json;charset=UTF-8",method = RequestMethod.POST)
public class ProduceFunctionController {

    @Autowired
    private ProduceFunctionService produceFunctionService;

    @RequestMapping(value = "/addFunction",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestBody ProduceFunction produceFunction)throws Exception{

        try {
            return produceFunctionService.addFunction(produceFunction);
        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/modifyFunction",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String modify(@RequestBody ProduceFunction produceFunction)throws Exception{
        try {
            return produceFunctionService.modifyFunction(produceFunction);
        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/detailsFunction",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody ProduceFunction details(int functionId)throws Exception{
        try {
            return produceFunctionService.detailsFunction(functionId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/functionList",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody List<ProduceFunction> functionList()throws Exception{
        try {
            return produceFunctionService.functionList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/deleteFunction",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String deleteFunction(int functionId)throws Exception{
        try {
            return produceFunctionService.deleteFunction(functionId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/pageFunctionInfo",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public PageInfo pageProduceInfo(@RequestParam int pageNum, @RequestParam int pageSize)throws Exception{
        try {
            return produceFunctionService.findPage(pageNum,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
