package com.dietsodasoftware.carfinder.mvc.controller;

import com.dietsodasoftware.carfinder.model.VehicleOffering;
import com.dietsodasoftware.carfinder.mvc.exception.HttpNotFoundError;
import com.dietsodasoftware.carfinder.mvc.view.ListResults;
import com.dietsodasoftware.carfinder.service.VehicleOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: wendel.schultz
 * Date: 7/14/14
 */
@Controller
@RequestMapping("/offerings")
public class VehicleOfferingController {

    @Autowired
    private VehicleOfferingService vehicleService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public VehicleOffering createOffering(@RequestBody VehicleOffering offering){
        final VehicleOffering newOffering = vehicleService.createOffering(offering.getTitle(), offering.getDescription(), offering.getPrice());

        return newOffering;

    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public VehicleOffering loadOffering(@PathVariable("id") Long id){

        final VehicleOffering offering = vehicleService.find(id);

        if(offering == null){
            throw new HttpNotFoundError("Unknown ID: " + id);
        }

        return offering;
    }


    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ListResults<VehicleOffering> list(){

        List<VehicleOffering> offerings = vehicleService.findAll();

        return new ListResults<VehicleOffering>(offerings);

    }
}
