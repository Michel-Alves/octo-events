package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Getter @NoArgsConstructor
public class MilestoneSnapshot {
    @JsonIgnore
    @Id
    @GeneratedValue(generator = "milestone_snapshot_generator")
    @SequenceGenerator(
            name = "milestone_snapshot_generator",
            sequenceName = "milestone_snapshot_sequence",
            initialValue = 1
    )
    private Long milestoneSnapshotId;

    private Long id;
    private String url;
    private String htmlUrl;
    private String labelsUrl;
    private String nodeId;
    private Long number;
    private String title;
    private String description;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "milestone_creator_user_snapshot_id")
    private GithubUserSnapshot creator;

    private Integer openIssues;
    private Integer closedIssues;
    private String state;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private OffsetDateTime dueOn;
    private OffsetDateTime closedAt;

    @Builder
    public MilestoneSnapshot(
            Long milestoneSnapshotId,
            Long id,
            String url,
            String htmlUrl,
            String labelsUrl,
            String nodeId,
            Long number,
            String title,
            String description,
            GithubUserSnapshot creator,
            Integer openIssues,
            Integer closedIssues,
            String state,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            OffsetDateTime dueOn,
            OffsetDateTime closedAt)
    {
        this.milestoneSnapshotId = milestoneSnapshotId;
        this.id = id;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.labelsUrl = labelsUrl;
        this.nodeId = nodeId;
        this.number = number;
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.openIssues = openIssues;
        this.closedIssues = closedIssues;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueOn = dueOn;
        this.closedAt = closedAt;
    }
}
