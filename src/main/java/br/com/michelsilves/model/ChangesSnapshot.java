package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ChangesSnapshot {
    @JsonIgnore
    @Id
    @GeneratedValue(generator = "github_issue_changes_generator")
    @SequenceGenerator(
            name = "github_issue_changes_generator",
            sequenceName = "github_issue_changes_sequence",
            initialValue = 1
    )
    private Long id;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "title_changes_from_id")
    private TitleChangeFrom title;

    @Builder
    public ChangesSnapshot(Long id, TitleChangeFrom title) {
        this.id = id;
        this.title = title;
    }
}
