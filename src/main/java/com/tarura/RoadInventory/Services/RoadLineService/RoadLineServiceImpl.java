package com.tarura.RoadInventory.Services.RoadLineService;

import com.tarura.RoadInventory.Dtos.RoadLineDto;
import com.tarura.RoadInventory.Dtos.RoadPointsDto;
import com.tarura.RoadInventory.Entities.RoadLine;
import com.tarura.RoadInventory.Entities.RoadPoints;
import com.tarura.RoadInventory.Repositories.RoadLineRepository;
import com.tarura.RoadInventory.Services.GenericService.CrudService;
import com.tarura.RoadInventory.Services.RoadPointsService.RoadPointsServiceImpl;
import org.locationtech.jts.geom.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tz.go.tarura.sharedUtils.ListResponse;
import tz.go.tarura.sharedUtils.Response;
import tz.go.tarura.sharedUtils.ResponseCode;
import tz.go.tarura.sharedUtils.TeRMISHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RoadLineServiceImpl implements CrudService<RoadLine, RoadLine, RoadLineDto, Page> {

    @Autowired
    private RoadLineRepository repository;

    @Autowired
    private RoadPointsServiceImpl roadPointsService;


    Response<RoadLine> response = new Response<>();
    ListResponse<RoadLine> listResponse = new ListResponse<>();

    @Override
    public Response<RoadLine> save(RoadLineDto saveDto ) {
        try {

            System.out.println(saveDto.toString());

            if(repository.existsById(saveDto.getRoadCode())){

                response.setCode(ResponseCode.DUPLICATE);
                response.setDescription("This record exists!");
                response.setStatus(false);
                response.setData(null);

            } else{
                RoadLine road = new RoadLine();
                road.setRoadCode(saveDto.getRoadCode());

                road = repository.save(road);

                GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
                Coordinate[] coordinates = {};
                CoordinateSequence[] points = {};
                ArrayList<Coordinate> lsCoordinates = new ArrayList<>(Arrays.asList(coordinates));
                ArrayList<CoordinateSequence> lsPoint = new ArrayList<>(Arrays.asList(points));

                System.out.println("################"+ saveDto.getPointData().size() +"##########");
                    for (RoadPointsDto roadPointDto: saveDto.getPointData()) {

                        if(roadPointDto.getAccuracy() <= 5){

                            lsCoordinates.add(new Coordinate( roadPointDto.getLongitude(), roadPointDto.getLatitude() ));

                            RoadPoints roadPoints = new RoadPoints();
                            TeRMISHelper.copyNonNullProperties(roadPointDto, roadPoints);
                            roadPoints.setRoadLine(road);
                            roadPointsService.save(roadPoints);
                        }

                    }

                LineString lineString = geometryFactory.createLineString(lsCoordinates.toArray(coordinates));
                Geometry geom = roadPointsService.wktToGeometry(lineString.toText());
                if (!geom.getGeometryType().equals("LineString")) {
                    throw new RuntimeException("Geometry must be a LineString. Got a " + geom.getGeometryType());
                }
                road.setGeometry(geom);

                repository.save(road);

                response.setCode(ResponseCode.SUCCESS);
                response.setDescription("saved Successfully");
                response.setStatus(true);
                response.setData(null);

            }
        } catch (Exception e) {

            response.setDescription("failed to save" +  e.toString());
            response.setCode(ResponseCode.FAILURE);
            response.setStatus(false);
            response.setData(null);

            e.printStackTrace();

        }
        return response;
    }

    @Override
    public Response<RoadLine> get(String id) {
        try {

            RoadLine roadLine = repository.findById(id).orElse(null);

            if(!repository.existsById(id)){

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setDescription("No record found");
                response.setData(null);
                response.setStatus(false);

            }else{

                response.setCode(ResponseCode.SUCCESS);
                response.setDescription("");
                response.setData(roadLine);
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
            Page<RoadLine> roadLines = repository.findAll(paging);

            long totalElements = roadLines.toList().size();

            if(totalElements > 0){
                response.setCode(ResponseCode.SUCCESS);
                response.setStatus(true);
                response.setData(roadLines);
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
    public ListResponse<RoadLine> getAll() {
        try {

            List<RoadLine> roadLines = repository.findAll();

            long totalElements = roadLines.size();

            if(totalElements > 0){

                listResponse.setCode(ResponseCode.SUCCESS);
                listResponse.setData(roadLines);
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
    public Response<RoadLine> update(RoadLine roadLineUpdateDto, String id) {
        try {

            RoadLine roadLine = repository.findById(id).orElse(null);
            TeRMISHelper.copyNonNullProperties(roadLineUpdateDto, roadLine);

            if (roadLine == null) {

                response.setCode(ResponseCode.NO_RECORD_FOUND);
                response.setData(null);
                response.setStatus(false);
                response.setDescription("road with id: " + id + " could not be found!");

            } else {

                RoadLine roadLineUpdated = repository.save(roadLine);

                response.setCode(ResponseCode.SUCCESS);
                response.setData(roadLineUpdated);
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
    public ListResponse<RoadLine> delete(String id) {

        try {
            RoadLine roadLine = repository.findById(id).orElse(null);

            if (roadLine == null) {

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
