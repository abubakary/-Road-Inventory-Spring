package com.tarura.RoadInventory.Services.RoadPointsService;

import com.tarura.RoadInventory.Entities.RoadPoints;
import com.tarura.RoadInventory.Repositories.RoadPointsRepository;
import com.tarura.RoadInventory.Services.GenericService.CrudService;
import org.locationtech.jts.geom.*;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
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
public class RoadPointsServiceImpl implements CrudService<RoadPoints, RoadPoints, RoadPoints, Page> {

    @Autowired
    private RoadPointsRepository repository;
    

    Response<RoadPoints> response = new Response<>();
    ListResponse<RoadPoints> listResponse = new ListResponse<>();

    @Override
    public Response<RoadPoints> save(RoadPoints saveDto ) {
        try {

            GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
            Point point = geometryFactory.createPoint( new Coordinate( saveDto.getLongitude(), saveDto.getLatitude() ) );

            Geometry geom = wktToGeometry(point.toText());

            if (!geom.getGeometryType().equals("Point")) {
                throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
            }

            saveDto.setGeometry(geom);
            RoadPoints RoadPoints = repository.save(saveDto);

            response.setCode(ResponseCode.SUCCESS);
            response.setDescription("saved Successfully");
            response.setStatus(true);
            response.setData(RoadPoints);

        } catch (Exception e) {

            response.setDescription("failed to save");
            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setData(null);

        }
        return response;
    }

    @Override
    public Response<RoadPoints> get(String id) {
        try {

            RoadPoints RoadPoints = repository.findById(id).orElse(null);

            if(!repository.existsById(id)){

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setDescription("No record found");
                response.setData(null);
                response.setStatus(false);

            }else{

                response.setCode(ResponseCode.SUCCESS);
                response.setDescription("");
                response.setData(RoadPoints);
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
            Page<RoadPoints> RoadPointss = repository.findAll(paging);

            long totalElements = RoadPointss.toList().size();

            if(totalElements > 0){
                response.setCode(ResponseCode.SUCCESS);
                response.setStatus(true);
                response.setData(RoadPointss);
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
    public ListResponse<RoadPoints> getAll() {
        try {

            List<RoadPoints> RoadPointss = repository.findAll();

            long totalElements = RoadPointss.size();

            if(totalElements > 0){

                listResponse.setCode(ResponseCode.SUCCESS);
                listResponse.setData(RoadPointss);
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
    public Response<RoadPoints> update(RoadPoints RoadPointsUpdateDto, String id) {
        try {

            RoadPoints RoadPoints = repository.findById(id).orElse(null);
            TeRMISHelper.copyNonNullProperties(RoadPointsUpdateDto, RoadPoints);

            if (RoadPoints == null) {

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setData(null);
                response.setStatus(false);
                response.setDescription("road with id: " + id + " could not be found!");

            } else {

                RoadPoints RoadPointsUpdated = repository.save(RoadPoints);

                response.setCode(ResponseCode.SUCCESS);
                response.setData(RoadPointsUpdated);
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
    public ListResponse<RoadPoints> delete(String id) {

        try {
            RoadPoints RoadPoints = repository.findById(id).orElse(null);

            if (RoadPoints == null) {

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
    public Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }

    /**
     * Utility method to assemble all arguments save the first into a String
     */
    private static String assemble(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        return builder.toString();
    }

}
