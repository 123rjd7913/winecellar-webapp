package com.cellar.wine.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.cellar.wine.utils.Regex.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wine extends BaseEntity {

    @Builder
    public Wine(Long id, String label, Producer producer, String vintage, String varietal) {
        super(id);
        this.producer = producer;
        this.label = label;
        this.vintage = vintage;
        this.varietal = varietal;
    }

    @ManyToOne
    @JoinColumn(name = "producer_id", referencedColumnName = "id")
    private Producer producer;

    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE)
    @Column(name = "label")
    private String label;

    @Size(min = 4, max = 4, message = "Needs to be in YYYY format")
    @Pattern(regexp = NUMERIC_PATTERN, message = NUMERIC_MESSAGE)
    @Column(name = "vintage")
    private String vintage;

    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE)
    @Column(name = "varietal")
    private String varietal;

}
