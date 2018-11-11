package br.com.michelsilves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Data
public class TitleChangeFrom {
    @Id
    @JsonIgnore
    @GeneratedValue(generator = "title_change_from_generator")
    @SequenceGenerator(
            name = "title_change_from_generator",
            sequenceName = "title_change_from_sequence",
            initialValue = 1
    )
    private Long id;
    @JsonProperty("from")
    private String changeOldValue;
}
