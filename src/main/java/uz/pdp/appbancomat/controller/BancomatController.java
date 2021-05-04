package uz.pdp.appbancomat.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appbancomat.payload.ApiResponse;
import uz.pdp.appbancomat.payload.BancomatDto;
import uz.pdp.appbancomat.service.BancomatService;

@RestController
@RequestMapping("/api/bancomat")
public class BancomatController {

    final BancomatService bancomatService;

    public BancomatController(BancomatService bancomatService) {
        this.bancomatService = bancomatService;
    }

    @PostMapping
    public HttpEntity<?> addBancomat(@RequestBody BancomatDto bancomatDto) {
        ApiResponse apiResponse = bancomatService.add(bancomatDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? 201 : 409).body(apiResponse);
    }
}
