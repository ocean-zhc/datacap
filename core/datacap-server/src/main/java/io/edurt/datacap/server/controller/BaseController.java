package io.edurt.datacap.server.controller;

import io.edurt.datacap.common.response.CommonResponse;
import io.edurt.datacap.service.body.FilterBody;
import io.edurt.datacap.service.service.BaseService;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public abstract class BaseController<T>
        implements Serializable
{
    private final PagingAndSortingRepository repository;
    private final BaseService<T> service;

    protected BaseController(PagingAndSortingRepository repository, BaseService<T> service)
    {
        this.repository = repository;
        this.service = service;
    }

    /**
     * Get data based on pagination
     */
    @PostMapping(value = "list")
    public CommonResponse list(@RequestBody FilterBody filter)
    {
        return service.getAll(repository, filter);
    }

    /**
     * Save changes
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public CommonResponse saveAndUpdate(@RequestBody T configure)
    {
        return service.saveOrUpdate(repository, configure);
    }

    @DeleteMapping
    public CommonResponse delete(@RequestParam(value = "id") Long id)
    {
        return service.deleteById(repository, id);
    }
}
