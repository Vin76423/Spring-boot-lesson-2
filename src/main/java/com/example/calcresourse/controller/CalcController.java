package com.example.calcresourse.controller;

import com.example.calcresourse.entity.Operation;
import com.example.calcresourse.repository.OperationH2repository;
import com.example.calcresourse.repository.UserH2repository;
import com.example.calcresourse.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/calc")
public class CalcController {

    @Autowired
    private OperationH2repository operationH2Repository;
    @Autowired
    private UserH2repository userH2repository;
    @Autowired
    private CalcService calcService;



    @PostMapping(path = "/count")
    public ResponseEntity<Operation> count(@RequestBody Operation operation, @RequestParam("token") String token) {
        ResponseEntity<Operation> responseEntity;
        if (userH2repository.existsUserByToken(Long.parseLong(token))) {
            Operation result = calcService.calc(operation);
            result.setUserToken(Long.parseLong(token));
            responseEntity = ResponseEntity.ok(operationH2Repository.save(result));
        }else {
            responseEntity = ResponseEntity.ok(new Operation());
        }
        return responseEntity;
    }

    @GetMapping(path = "/report")
    public ResponseEntity<List<Operation>> showReport(@RequestParam("token") String token) {
        if (userH2repository.existsUserByToken(Long.parseLong(token))) {
            return ResponseEntity.of(operationH2Repository.findAllByUserToken(Long.parseLong(token)));
        }else{
//            return ResponseEntity.ok(new ArrayList<>());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/sorted-report")
    public ResponseEntity<List<Operation>> showSortedReport(@RequestParam("token") String token, @RequestParam("operationType") String operationType) {
        if (userH2repository.existsUserByToken(Long.parseLong(token))) {
            return ResponseEntity.of(operationH2Repository.findAllByUserTokenAndOperationType(Long.parseLong(token), operationType));
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
