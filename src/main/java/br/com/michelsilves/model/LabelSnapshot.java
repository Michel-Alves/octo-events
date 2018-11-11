package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Getter @NoArgsConstructor
public class LabelSnapshot {
    @JsonIgnore
    @Id
    @GeneratedValue(generator = "github_label_snapshot_generator")
    @SequenceGenerator(
            name = "github_label_snapshot_generator",
            sequenceName = "github_label_snapshot_sequence",
            initialValue = 1
    )
    private Long labelSnapshotId;

    private Long id;
    private String nodeId;
    private String url;
    private String name;
    private String color;
    @JsonProperty("default")
    private Boolean _default;

    @Builder
    public LabelSnapshot(
            Long labelSnapshotId,
            Long id,
            String nodeId,
            String url,
            String name,
            String color,
            Boolean _default)
    {
        this.labelSnapshotId = labelSnapshotId;
        this.id = id;
        this.nodeId = nodeId;
        this.url = url;
        this.name = name;
        this.color = color;
        this._default = _default;
    }
}
