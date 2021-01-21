package com.tarura.RoadInventory.Services.GenericService;

import tz.go.tarura.sharedUtils.ListResponse;
import tz.go.tarura.sharedUtils.Response;

public interface CrudService<T, U, S, V> {
    public Response<T> save(S saveDto);
    public Response<T> get(String id);
    public Response<V> getAll(int page, int size);
    public ListResponse<T> getAll();
    public Response<T> update(U updateDto, String id);
    public ListResponse<T> delete(String id);
}
