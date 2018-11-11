package br.com.michelsilves.repository;

import br.com.michelsilves.model.IssuesEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface IssuesEventRepository
        extends Repository<IssuesEvent, Long>, JpaRepository<IssuesEvent, Long> {
    List<IssuesEvent> findAllByIssue_Id(Long issueId);
}
