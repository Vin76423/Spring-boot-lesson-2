package com.example.calcresourse.repository;

import com.example.calcresourse.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface OperationH2repository extends JpaRepository<Operation, Long> {
    Optional<List<Operation>> findAllByUserToken(long token);
    Optional<List<Operation>> findAllByUserTokenAndOperationType(long token, String operationType);
}
