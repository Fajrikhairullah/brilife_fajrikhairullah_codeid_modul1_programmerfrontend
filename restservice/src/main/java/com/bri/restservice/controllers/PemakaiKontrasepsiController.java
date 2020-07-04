package com.bri.restservice.controllers;

import com.bri.restservice.entities.Kontrasepsi;
import com.bri.restservice.entities.PemakaiKontrasepsi;
import com.bri.restservice.entities.Propinsi;
import com.bri.restservice.models.PageableList;
import com.bri.restservice.models.PemakaiKontrasepsiModel;
import com.bri.restservice.models.PemakaiKontrasepsiSummary;
import com.bri.restservice.models.ResponseMessage;
import com.bri.restservice.services.PemakaiKontrasepsiService;
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

@RequestMapping("/pemakaikontrasepsi")
@RestController
@Validated
public class PemakaiKontrasepsiController {
    
    @Autowired
    private PemakaiKontrasepsiService pemakaiKontrasepsiService;
    
    @PostMapping
    public ResponseMessage<PemakaiKontrasepsiModel> add(@RequestBody @Valid PemakaiKontrasepsiModel model) {
        PemakaiKontrasepsi entity = pemakaiKontrasepsiService.save(new PemakaiKontrasepsi(model.getIdList(), model.getPropinsi(), model.getKontrasepsi(), model.getJumlahPemakai()));

        ModelMapper modelMaper = new ModelMapper();
        PemakaiKontrasepsiModel data = modelMaper.map(entity, PemakaiKontrasepsiModel.class);

        return ResponseMessage.success(data);
    }

    @PutMapping("/{id}")
    public ResponseMessage<PemakaiKontrasepsiModel> edit(@PathVariable Integer id, @RequestBody @Valid PemakaiKontrasepsiModel model) {

        ModelMapper modelMaper = new ModelMapper();
        model.setIdList(id);
        PemakaiKontrasepsi entity = pemakaiKontrasepsiService.findById(id);
        modelMaper.map(model, entity);

        entity = pemakaiKontrasepsiService.save(entity);

        PemakaiKontrasepsiModel data = modelMaper.map(entity, PemakaiKontrasepsiModel.class);
        return ResponseMessage.success(data);

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<PemakaiKontrasepsiModel> removeById(@PathVariable Integer id
    ) {
        PemakaiKontrasepsi entity = pemakaiKontrasepsiService.removeById(id);

        ModelMapper modelMaper = new ModelMapper();
        PemakaiKontrasepsiModel model = modelMaper.map(entity, PemakaiKontrasepsiModel.class);

        return ResponseMessage.success(model);

    }

    @GetMapping("/{id}")
    public ResponseMessage<PemakaiKontrasepsiModel> findById(@PathVariable Integer id) {
        PemakaiKontrasepsi entity = pemakaiKontrasepsiService.findById(id);

        ModelMapper modelMaper = new ModelMapper();
        PemakaiKontrasepsiModel model = modelMaper.map(entity, PemakaiKontrasepsiModel.class);
        return ResponseMessage.success(model);

    }

    @GetMapping()
    public ResponseMessage findAll(
            @RequestParam(required = false) Propinsi propinsi,
            @RequestParam(required = false) Kontrasepsi kontrasepsi,
            @RequestParam(required = false) Integer jumlahPemakai,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        if (size > 100) {
            size = 100;
        }

        PemakaiKontrasepsi entity = new PemakaiKontrasepsi();
        Sort.Direction direction = Sort.Direction
                //                .valueOf(sort.toUpperCase());
                .fromOptionalString(sort.toUpperCase())
                .orElse(Sort.Direction.ASC);
        Page<PemakaiKontrasepsi> pageStocks = pemakaiKontrasepsiService.findAll(entity, page, size, direction);
        List<PemakaiKontrasepsi> stocks = pageStocks.toList();

        ModelMapper modelMapper = new ModelMapper();
        Type type = new TypeToken<List<PemakaiKontrasepsiModel>>() {
        }.getType();
        List<PemakaiKontrasepsiModel> pemakaiKontrasepsiModels = modelMapper.map(stocks, type);

        PageableList<PemakaiKontrasepsiModel> data = new PageableList(pemakaiKontrasepsiModels, pageStocks.getNumber(), pageStocks.getSize(),
                pageStocks.getTotalElements());
        return ResponseMessage.success(data);
    }

    @GetMapping(path = "/summary")
    public ResponseMessage<List<PemakaiKontrasepsiSummary>> kontrasepsiSummary() {
        List<PemakaiKontrasepsiSummary> pemakaiKontrasepsiSummaries = pemakaiKontrasepsiService.pemakaiKontrasepsiSummary();
        return ResponseMessage.success(pemakaiKontrasepsiSummaries);
    }

    
    
}
