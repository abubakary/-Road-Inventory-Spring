package com.tarura.RoadInventory.Controllers;

import com.tarura.RoadInventory.Entities.ProgramProjects;
import com.tarura.RoadInventory.Entities.RegionDistricts;
import com.tarura.RoadInventory.Services.RegionDistricts.RegionDistrictsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tz.go.tarura.sharedUtils.ListResponse;
import tz.go.tarura.sharedUtils.Response;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/")
public class RegionDistrictsController {

    @Autowired
    public RegionDistrictsServiceImpl service;

    @RequestMapping(value = "RegionDistricts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<RegionDistricts> save(@Valid @RequestBody RegionDistricts RegionDistrictsDto){
        return service.save(RegionDistrictsDto);
    }

    @RequestMapping(value = "RegionDistricts/id/{id}", method = RequestMethod.GET)
    public Response<RegionDistricts> get(@Valid @PathVariable String id){
        return service.get(id);
    }

    @RequestMapping(value = "RegionDistricts/all", method = RequestMethod.GET)
    public ListResponse<RegionDistricts> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "RegionDistricts", method = RequestMethod.GET)
    public Response<Page> getAll(@RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return service.getAll(page, size);
    }

    @RequestMapping(value = "RegionDistricts/id/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<RegionDistricts> update(@Valid @RequestBody RegionDistricts RegionDistricts, @PathVariable String id) {
        return service.update(RegionDistricts, id);
    }

    @RequestMapping(value = "RegionDistricts/id/{id}", method = RequestMethod.DELETE)
    public ListResponse<RegionDistricts> delete(@Valid @PathVariable String id) {
        return service.delete(id);
    }

}
