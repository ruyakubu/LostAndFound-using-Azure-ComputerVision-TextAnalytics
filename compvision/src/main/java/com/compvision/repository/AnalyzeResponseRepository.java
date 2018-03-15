package com.compvision.repository;

import com.compvision.domain.AnalyzeResponse;

import com.microsoft.azure.spring.data.documentdb.repository.DocumentDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeResponseRepository extends DocumentDbRepository<AnalyzeResponse, String>{

}
