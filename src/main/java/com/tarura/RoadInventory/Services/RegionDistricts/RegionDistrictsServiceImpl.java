package com.tarura.RoadInventory.Services.RegionDistricts;

import com.tarura.RoadInventory.Entities.RegionDistricts;
import com.tarura.RoadInventory.Repositories.ProgramProjectRepository;
import com.tarura.RoadInventory.Repositories.RegionDistrictRepository;
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
public class RegionDistrictsServiceImpl implements CrudService<RegionDistricts, RegionDistricts, RegionDistricts, Page> {

    @Autowired
    private RegionDistrictRepository repository;
    

    Response<RegionDistricts> response = new Response<>();
    ListResponse<RegionDistricts> listResponse = new ListResponse<>();

    @Override
    public Response<RegionDistricts> save(RegionDistricts saveDto ) {
        try {

            RegionDistricts RegionDistricts = repository.save(saveDto);

            response.setCode(ResponseCode.SUCCESS);
            response.setDescription("saved Successfully");
            response.setStatus(true);
            response.setData(RegionDistricts);

        } catch (Exception e) {

            response.setDescription("failed to save");
            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setData(null);

        }
        return response;
    }

    @Override
    public Response<RegionDistricts> get(String id) {
        try {

            RegionDistricts RegionDistricts = repository.findById(id).orElse(null);

            if(!repository.existsById(id)){

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setDescription("No record found");
                response.setData(null);
                response.setStatus(false);

            }else{

                response.setCode(ResponseCode.SUCCESS);
                response.setDescription("");
                response.setData(RegionDistricts);
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
            Page<RegionDistricts> RegionDistrictss = repository.findAll(paging);

            long totalElements = RegionDistrictss.toList().size();

            if(totalElements > 0){
                response.setCode(ResponseCode.SUCCESS);
                response.setStatus(true);
                response.setData(RegionDistrictss);
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
    public ListResponse<RegionDistricts> getAll() {
        try {

            List<RegionDistricts> RegionDistrictss = repository.findAll();

            long totalElements = RegionDistrictss.size();

            if(totalElements > 0){

                listResponse.setCode(ResponseCode.SUCCESS);
                listResponse.setData(RegionDistrictss);
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
    public Response<RegionDistricts> update(RegionDistricts RegionDistrictsUpdateDto, String id) {
        try {

            RegionDistricts RegionDistricts = repository.findById(id).orElse(null);
            TeRMISHelper.copyNonNullProperties(RegionDistrictsUpdateDto, RegionDistricts);

            if (RegionDistricts == null) {

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setData(null);
                response.setStatus(false);
                response.setDescription("road with id: " + id + " could not be found!");

            } else {

                RegionDistricts RegionDistrictsUpdated = repository.save(RegionDistricts);

                response.setCode(ResponseCode.SUCCESS);
                response.setData(RegionDistrictsUpdated);
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
    public ListResponse<RegionDistricts> delete(String id) {

        try {
            RegionDistricts RegionDistricts = repository.findById(id).orElse(null);

            if (RegionDistricts == null) {

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
