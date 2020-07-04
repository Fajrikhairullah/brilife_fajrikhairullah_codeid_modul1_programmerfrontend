package com.bri.restservice.controllers;

import com.bri.restservice.entities.Kontrasepsi;
import com.bri.restservice.models.KontrasepsiModel;
import com.bri.restservice.models.PageableList;
import com.bri.restservice.models.ResponseMessage;
import com.bri.restservice.services.KontrasepsiService;
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

@RequestMapping("/kontrasepsi")
@RestController
@Validated
@Api(value = "Kontrasepsi", description = "Controller for Kontrasepsi", tags = {"kontrasepsi"})
public class KontrasepsiController {
    
    @Autowired
    private KontrasepsiService kontrasepsiService;
    
    @PostMapping
    public ResponseMessage<KontrasepsiModel> add(@RequestBody @Valid KontrasepsiModel model) {
        Kontrasepsi entity = kontrasepsiService.save(new Kontrasepsi(model.getIdKontrasepsi(), model.getNamaKontrasepsi()));

        ModelMapper modelMaper = new ModelMapper();
        KontrasepsiModel data = modelMaper.map(entity, KontrasepsiModel.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<KontrasepsiModel> edit(@PathVariable Integer id, @RequestBody @Valid KontrasepsiModel model) {

        ModelMapper modelMaper = new ModelMapper();
        model.setIdKontrasepsi(id);
        Kontrasepsi entity = kontrasepsiService.findById(id);
        modelMaper.map(model, entity);

        entity = kontrasepsiService.save(entity);

        KontrasepsiModel data = modelMaper.map(entity, KontrasepsiModel.class);
        return ResponseMessage.success(data);

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<KontrasepsiModel> removeById(@PathVariable Integer id
    ) {
        Kontrasepsi entity = kontrasepsiService.removeById(id);

        ModelMapper modelMaper = new ModelMapper();
        KontrasepsiModel model = modelMaper.map(entity, KontrasepsiModel.class);

        return ResponseMessage.success(model);

    }

    @GetMapping("/{id}")
    public ResponseMessage<KontrasepsiModel> findById(@PathVariable Integer id) {
        Kontrasepsi entity = kontrasepsiService.findById(id);

        ModelMapper modelMaper = new ModelMapper();
        KontrasepsiModel model = modelMaper.map(entity, KontrasepsiModel.class);
        return ResponseMessage.success(model);

    }

    @ApiOperation(value = "Find All Kontrasepsi", tags = {"kontrasepsi"})
    @ApiResponses({
        @ApiResponse(code = 200, message = "This is response", response = ResponseMessage.class)
    })
    @GetMapping()
    public ResponseMessage findAll(
            @RequestParam(required = false) @ApiParam(required = true) Integer idKontrasepsi,
            @RequestParam(required = false) @ApiParam(required = true) String namaKontrasepsi,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        Kontrasepsi entity = new Kontrasepsi(idKontrasepsi, namaKontrasepsi);
        Sort.Direction direction = Sort.Direction
                //                .valueOf(sort.toUpperCase());
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<Kontrasepsi> pageKontrasepsi = kontrasepsiService.findAll(entity, page, size, direction);
        List<Kontrasepsi> kontrasepsi = pageKontrasepsi.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<KontrasepsiModel>>() {
        }.getType();
        List<KontrasepsiModel> kontrasepsiModels = modelMapper.map(kontrasepsi, type);

        PageableList<KontrasepsiModel> data = new PageableList(kontrasepsiModels, pageKontrasepsi.getNumber(), pageKontrasepsi.getSize(),
                pageKontrasepsi.getTotalElements());
        return ResponseMessage.success(data);
    }

}
