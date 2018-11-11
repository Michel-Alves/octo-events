package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@NoArgsConstructor @Getter
public class IssueSnapshot {

    @JsonIgnore
    @Id
    @GeneratedValue(generator = "github_issue_snapshot_generator")
    @SequenceGenerator(
            name = "github_issue_snapshot_generator",
            sequenceName = "github_issue_snapshot_sequence",
            initialValue = 1
    )
    private Long githubIssueSnapshotId;

    private Long id;
    private String url;
    private String rerpositoryUrl;
    private String labelsUrl;
    private String commentsUrl;
    private String eventsUrl;
    private String htmlUrl;
    private String nodeId;
    private Long number;
    private String title;

    @OneToOne (cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "user_snapshot_id")
    private GithubUserSnapshot user;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "issue_event_labels_id")
    private List<LabelSnapshot> labels;

    private String state;
    private boolean locked;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE  })
    @JoinColumn(name = "assigne_snapshot_id")
    private GithubUserSnapshot assignee;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "milestone_snapshot_id")
    private MilestoneSnapshot milestone;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "assignees_id")
    private List<GithubUserSnapshot> assignees;

    private Integer comments;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime closedAt;
    private String authorAssociation;
    private String body;

    @Builder
    public IssueSnapshot(String url,
                         String rerpositoryUrl,
                         String labelsUrl,
                         String commentsUrl,
                         String eventsUrl,
                         String htmlUrl,
                         Long id,
                         String nodeId,
                         Long number,
                         String title,
                         GithubUserSnapshot user,
                         List<LabelSnapshot> labels,
                         String state,
                         boolean locked,
                         GithubUserSnapshot assignee,
                         List<GithubUserSnapshot> assignees,
                         MilestoneSnapshot milestone,
                         Integer comments,
                         OffsetDateTime createdAt,
                         OffsetDateTime updatedAt,
                         OffsetDateTime closedAt,
                         String authorAssociation,
                         String body) {
        this.githubIssueSnapshotId = githubIssueSnapshotId;
        this.url = url;
        this.rerpositoryUrl = rerpositoryUrl;
        this.labelsUrl = labelsUrl;
        this.commentsUrl = commentsUrl;
        this.eventsUrl = eventsUrl;
        this.htmlUrl = htmlUrl;
        this.id = id;
        this.nodeId = nodeId;
        this.number = number;
        this.title = title;
        this.user = user;
        this.labels = labels;
        this.state = state;
        this.locked = locked;
        this.assignee = assignee;
        this.assignees = assignees;
        this.milestone = milestone;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.authorAssociation = authorAssociation;
        this.body = body;
    }
}