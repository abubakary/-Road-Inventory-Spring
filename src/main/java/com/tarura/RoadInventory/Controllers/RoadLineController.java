package com.tarura.RoadInventory.Controllers;

import com.tarura.RoadInventory.Dtos.RoadLineDto;
import com.tarura.RoadInventory.Entities.RoadLine;
import com.tarura.RoadInventory.Services.RoadLineService.RoadLineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tz.go.tarura.sharedUtils.ListResponse;
import tz.go.tarura.sharedUtils.Response;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/")
public class RoadLineController {

    @Autowired
    public RoadLineServiceImpl service;

    @RequestMapping(value = "roadLine", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<RoadLine> saveRoad(@Valid @RequestBody RoadLineDto roadLineDto){
        return service.save(roadLineDto);
    }

    @RequestMapping(value = "roadLine/id/{id}", method = RequestMethod.GET)
    public Response<RoadLine> getRoad(@Valid @PathVariable String id){
        return service.get(id);
    }


    @RequestMapping(value = "roadLine", method = RequestMethod.GET)
    public Response<Page> getAllRoads(@RequestParam(required = false) String title, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return service.getAll(page, size);
    }

    @RequestMapping(value = "roadLine/id/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response<RoadLine> updateRoad(@Valid @RequestBody RoadLine roadLine, @PathVariable String id) {
        return service.update(roadLine, id);
    }

    @RequestMapping(value = "roadLine/id/{id}", method = RequestMethod.DELETE)
    public ListResponse<RoadLine> deleteStatus(@Valid @PathVariable String id) {
        return service.delete(id);
    }

}
