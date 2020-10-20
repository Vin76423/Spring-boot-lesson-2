package com.example.calcresourse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double num1;
    private double num2;
    private String operationType;
    private double result;
    private long userToken;

    public Operation(double num1, double num2, String operationType) {
        this.num1 = num1;
        this.num2 = num2;
        this.operationType = operationType;
    }

    public Operation(String operationType) {
        this.operationType = operationType;
    }
}
