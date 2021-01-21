package com.tarura.RoadInventory.Services.ProgramProjectService;

import com.tarura.RoadInventory.Entities.ProgramProjects;
import com.tarura.RoadInventory.Repositories.ProgramProjectRepository;
import com.tarura.RoadInventory.Services.GenericService.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tz.go.tarura.sharedUtils.ListResponse;
import tz.go.tarura.sharedUtils.Response;
import tz.go.tarura.sharedUtils.ResponseCode;
import tz.go.tarura.sharedUtils.TeRMISHelper;

import java.util.List;

@Service
public class ProgramProjectServiceImpl implements CrudService<ProgramProjects, ProgramProjects, ProgramProjects, Page> {

    @Autowired
    private ProgramProjectRepository repository;
    

    Response<ProgramProjects> response = new Response<>();
    ListResponse<ProgramProjects> listResponse = new ListResponse<>();

    @Override
    public Response<ProgramProjects> save(ProgramProjects saveDto ) {
        try {

            ProgramProjects ProgramProjects = repository.save(saveDto);

            response.setCode(ResponseCode.SUCCESS);
            response.setDescription("saved Successfully");
            response.setStatus(true);
            response.setData(ProgramProjects);

        } catch (Exception e) {

            response.setDescription("failed to save");
            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setData(null);

        }
        return response;
    }

    @Override
    public Response<ProgramProjects> get(String id) {
        try {

            ProgramProjects ProgramProjects = repository.findById(id).orElse(null);

            if(!repository.existsById(id)){

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setDescription("No record found");
                response.setData(null);
                response.setStatus(false);

            }else{

                response.setCode(ResponseCode.SUCCESS);
                response.setDescription("");
                response.setData(ProgramProjects);
                response.setStatus(true);

            }
        } catch (Exception e) {

            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setData(null);

        }
        return response;
    }

    @Override
    public Response<Page> getAll(int page, int size) {
        Response<Page> response = new Response<>();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<ProgramProjects> ProgramProjectss = repository.findAll(paging);

            long totalElements = ProgramProjectss.toList().size();

            if(totalElements > 0){
                response.setCode(ResponseCode.SUCCESS);
                response.setStatus(true);
                response.setData(ProgramProjectss);
            }else {

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setStatus(false);
                response.setData(null);

            }


        } catch (Exception e) {

            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setData(null);

        }
        return response;
    }

    @Override
    public ListResponse<ProgramProjects> getAll() {
        try {

            List<ProgramProjects> ProgramProjectss = repository.findAll();

            long totalElements = ProgramProjectss.size();

            if(totalElements > 0){

                listResponse.setCode(ResponseCode.SUCCESS);
                listResponse.setData(ProgramProjectss);
                listResponse.setStatus(true);
            }else {

                listResponse.setCode(ResponseCode.NO_RECORD_FOUND);
                listResponse.setStatus(false);
                listResponse.setData(null);

            }

            listResponse.setTotalElements(totalElements);

        } catch (Exception e) {

            listResponse.setCode(ResponseCode.FAILURE);
            listResponse.setStatus(false);
            listResponse.setData(null);
            listResponse.setTotalElements((long) 0);

        }
        return listResponse;
    }



    @Override
    public Response<ProgramProjects> update(ProgramProjects ProgramProjectsUpdateDto, String id) {
        try {

            ProgramProjects ProgramProjects = repository.findById(id).orElse(null);
            TeRMISHelper.copyNonNullProperties(ProgramProjectsUpdateDto, ProgramProjects);

            if (ProgramProjects == null) {

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setData(null);
                response.setStatus(false);
                response.setDescription("road with id: " + id + " could not be found!");

            } else {

                ProgramProjects ProgramProjectsUpdated = repository.save(ProgramProjects);

                response.setCode(ResponseCode.SUCCESS);
                response.setData(ProgramProjectsUpdated);
                response.setStatus(true);
                response.setDescription("updated successfully!");

            }

        } catch (Exception e) {

            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setDescription("Internal Error!" + e.toString());
            response.setData(null);
        }
        return response;




    }

    @Override
    public ListResponse<ProgramProjects> delete(String id) {

        try {
            ProgramProjects ProgramProjects = repository.findById(id).orElse(null);

            if (ProgramProjects == null) {

                listResponse.setCode(ResponseCode.NO_RECORD_FOUND);
                listResponse.setData(null);
                listResponse.setStatus(false);
                listResponse.setTotalElements((long) 0);

            } else {

                repository.deleteById(id);
                long totalElements = repository.findAll().size();

                listResponse.setCode(ResponseCode.SUCCESS);
                listResponse.setData(repository.findAll());
                listResponse.setTotalElements(totalElements);
                listResponse.setStatus(true);
            }
        } catch (Exception e) {

            listResponse.setCode(ResponseCode.FAILURE);
            listResponse.setStatus(false);
            listResponse.setData(null);
            listResponse.setTotalElements((long) 0);
        }

        return listResponse;
    }

}
