package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor
public class IssuesEvent {

    @Id
    @GeneratedValue(generator = "issues_event_generator")
    @SequenceGenerator(
            name = "issues_event_generator",
            sequenceName = "issues_event_sequence",
            initialValue = 1
    )
    private Long id;

    @Column(nullable = false)
    private String action;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "issue_snapshot_id", nullable = false)
    private IssueSnapshot issue;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "changes_id")
    private ChangesSnapshot changes;

    private String changesTitleFrom;

    private String changesBodyFrom;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "assigneeUserId")
    private GithubUserSnapshot assignee;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "labelId")
    private LabelSnapshot label;

    @Builder
    public IssuesEvent(
            String action,
            IssueSnapshot issue,
            ChangesSnapshot changes,
            String changesTitleFrom,
            String changesBodyFrom,
            GithubUserSnapshot assignee,
            LabelSnapshot label)
    {
        this.action = action;
        this.issue = issue;
        this.changes = changes;
        this.changesTitleFrom = changesTitleFrom;
        this.changesBodyFrom = changesBodyFrom;
        this.assignee = assignee;
        this.label = label;
    }

    @JsonIgnore
    public Long getIssueId() {
        return issue.getId();
    }
}

