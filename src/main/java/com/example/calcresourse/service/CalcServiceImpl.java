package com.example.calcresourse.service;

import com.example.calcresourse.entity.Operation;
import org.springframework.stereotype.Service;

@Service
public class CalcServiceImpl implements CalcService {
    public Operation calc(Operation operation) {
        switch (operation.getOperationType()) {
            case "SUM" -> {
                operation.setResult(operation.getNum1() + operation.getNum2());
                break;
            }
            case "SUB" -> {
                operation.setResult(operation.getNum1() - operation.getNum2());
                break;
            }
            case "MUL" -> {
                operation.setResult(operation.getNum1() * operation.getNum2());
            }
            case "DIV" -> {
                if (operation.getNum2() == 0) {
                    throw new ArithmeticException("Cen not division by zero!");
                }
                operation.setResult(operation.getNum1() / operation.getNum2());
                break;
            }
            default -> throw new UnsupportedOperationException();
        }
        return operation;
    }
}
