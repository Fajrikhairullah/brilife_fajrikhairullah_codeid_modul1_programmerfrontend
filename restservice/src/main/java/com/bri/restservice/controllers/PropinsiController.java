package com.bri.restservice.controllers;

import com.bri.restservice.entities.Propinsi;
import com.bri.restservice.models.PageableList;
import com.bri.restservice.models.PropinsiModel;
import com.bri.restservice.models.ResponseMessage;
import com.bri.restservice.services.PropinsiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.lang.reflect.Type;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/propinsi")
@RestController
@Validated
@Api(value = "Propinsi", description = "Controller for Propinsi", tags = {"propinsi"})
public class PropinsiController {
    
    @Autowired
    private PropinsiService propinsiService;
    
    @PostMapping
    public ResponseMessage<PropinsiModel> add(@RequestBody @Valid PropinsiModel model) {
        Propinsi entity = propinsiService.save(new Propinsi(model.getIdPropinsi() ,model.getNamaPropinsi()));

        ModelMapper modelMaper = new ModelMapper();
        PropinsiModel data = modelMaper.map(entity, PropinsiModel.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<PropinsiModel> edit(@PathVariable Integer id, @RequestBody @Valid PropinsiModel model) {

        ModelMapper modelMaper = new ModelMapper();
        model.setIdPropinsi(id);
        Propinsi entity = propinsiService.findById(id);
        modelMaper.map(model, entity);

        entity = propinsiService.save(entity);

        PropinsiModel data = modelMaper.map(entity, PropinsiModel.class);
        return ResponseMessage.success(data);

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<PropinsiModel> removeById(@PathVariable Integer id
    ) {
        Propinsi entity = propinsiService.removeById(id);

        ModelMapper modelMaper = new ModelMapper();
        PropinsiModel model = modelMaper.map(entity, PropinsiModel.class);

        return ResponseMessage.success(model);

    }

    @GetMapping("/{id}")
    public ResponseMessage<PropinsiModel> findById(@PathVariable Integer id) {
        Propinsi entity = propinsiService.findById(id);

        ModelMapper modelMaper = new ModelMapper();
        PropinsiModel model = modelMaper.map(entity, PropinsiModel.class);
        return ResponseMessage.success(model);

    }

    @ApiOperation(value = "Find All Propinsi", tags = {"propinsi"})
    @ApiResponses({
        @ApiResponse(code = 200, message = "This is response", response = ResponseMessage.class)
    })
    @GetMapping()
    public ResponseMessage findAll(
            @RequestParam(required = false) @ApiParam(required = true) Integer idPropinsi,
            @RequestParam(required = false) @ApiParam(required = true) String namaPropinsi,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        Propinsi entity = new Propinsi(idPropinsi, namaPropinsi);
        Sort.Direction direction = Sort.Direction
                //                .valueOf(sort.toUpperCase());
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Propinsi> pagePropinsi = propinsiService.findAll(entity, page, size, direction);
        List<Propinsi> propinsi = pagePropinsi.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<PropinsiModel>>() {
        }.getType();
        List<PropinsiModel> propinsiModels = modelMapper.map(propinsi, type);

        PageableList<PropinsiModel> data = new PageableList(propinsiModels, pagePropinsi.getNumber(), pagePropinsi.getSize(),
                pagePropinsi.getTotalElements());
        return ResponseMessage.success(data);
    }

    
}
