package br.pucrs.ages.mase.plan_service.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import br.pucrs.ages.mase.plan_service.entity.PlanTemplate;

@Repository
public interface PlanTemplateRepository extends ReactiveMongoRepository<PlanTemplate, String> {

}
