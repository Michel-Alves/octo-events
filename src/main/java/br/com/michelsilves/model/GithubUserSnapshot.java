package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Getter @NoArgsConstructor
public class GithubUserSnapshot {
    @JsonIgnore
    @Id
    @GeneratedValue(generator = "github_user_snapshot_generator")
    @SequenceGenerator(
            name = "github_user_snapshot_generator",
            sequenceName = "github_user_snapshot_sequence",
            initialValue = 1
    )
    private Long githubUserSnapshotId;

    private Long id;
    private String login;
    private String nodeId;
    private String avatarUrl;
    private String gravatarId;
    private String url;
    private String htmlUrl;
    private String followersUrl;
    private String followingUrl;
    private String gistsUrl;
    private String starredUrl;
    private String subscriptionsUrl;
    private String organizationsUrl;
    private String reposUrl;
    private String eventUrl;
    private String receivedEventsUrl;
    private String type;
    private Boolean siteAdmin;

    @Builder
    public GithubUserSnapshot(Long id,
                              String login,
                              String nodeId,
                              String avatarUrl,
                              String gravatarId,
                              String url,
                              String htmlUrl,
                              String followersUrl,
                              String followingUrl,
                              String gistsUrl,
                              String starredUrl,
                              String subscriptionsUrl,
                              String organizationsUrl,
                              String reposUrl,
                              String eventUrl,
                              String receivedEventsUrl,
                              String type,
                              Boolean siteAdmin) {
        this.id = id;
        this.login = login;
        this.nodeId = nodeId;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventUrl = eventUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
    }
}
