package com.tarura.RoadInventory.Controllers;

import com.tarura.RoadInventory.Entities.ProgramProjects;
import com.tarura.RoadInventory.Services.ProgramProjectService.ProgramProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tz.go.tarura.sharedUtils.ListResponse;
import tz.go.tarura.sharedUtils.Response;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/")
public class ProgramProjectsController {

    @Autowired
    public ProgramProjectServiceImpl service;

    @RequestMapping(value = "ProgramProjects", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<ProgramProjects> save(@Valid @RequestBody ProgramProjects ProgramProjectsDto){
        return service.save(ProgramProjectsDto);
    }

    @RequestMapping(value = "ProgramProjects/id/{id}", method = RequestMethod.GET)
    public Response<ProgramProjects> get(@Valid @PathVariable String id){
        return service.get(id);
    }


    @RequestMapping(value = "ProgramProjects", method = RequestMethod.GET)
    public Response<Page> getAll(@RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return service.getAll(page, size);
    }

    @RequestMapping(value = "ProgramProjects/all", method = RequestMethod.GET)
    public ListResponse<ProgramProjects> getAll(){
        return service.getAll();
    }

    @RequestMapping(value = "ProgramProjects/id/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<ProgramProjects> update(@Valid @RequestBody ProgramProjects ProgramProjects, @PathVariable String id) {
        return service.update(ProgramProjects, id);
    }

    @RequestMapping(value = "ProgramProjects/id/{id}", method = RequestMethod.DELETE)
    public ListResponse<ProgramProjects> delete(@Valid @PathVariable String id) {
        return service.delete(id);
    }

}
