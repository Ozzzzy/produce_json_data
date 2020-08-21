package com.example.produce.controller;

import com.example.produce.entity.ProduceInformation;
import com.example.produce.service.ProduceInformationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hou
 * @date 2020/8/21
 */

@RestController
public class ProduceInformationController {

    @Autowired
    private ProduceInformationService produceInformationService;

    @RequestMapping(value = "/addProduce",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String add(String jsonString)throws Exception{

        try {
            JsonReader jsonReader = new JsonReader();
            ProduceInformation produceInformation = jsonReader.getProduceList(jsonString).get(0);
            produceInformationService.addProduce(produceInformation);
            return "add successful !";

        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/modifyProduce",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String modify(String jsonString)throws Exception{
        try {
            JsonReader jsonReader = new JsonReader();
            ProduceInformation produceInformation = jsonReader.getProduceList(jsonString).get(0);
            produceInformationService.modifyProduce(produceInformation);
            return "modify successful !";

        }catch (Exception e){
            e.printStackTrace();
            return "wrong";
        }
    }

    @RequestMapping(value = "/detailsProduce",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody ProduceInformation details(int produceId)throws Exception{
        try {
            return produceInformationService.detailsProduce(produceId);

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/produceList",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody List<ProduceInformation> produceList()throws Exception{
        try {
            return produceInformationService.produceList();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/deleteProduce",produces = "application/json; charset=UTF-8", method = RequestMethod.POST)
    public @ResponseBody String deleteProduce(int produceId)throws Exception{
        try {
            produceInformationService.deleteProduce(produceId);
            return "delete successful!";

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


}
