package com.cellar.wine.models;

import com.cellar.wine.security.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

import static com.cellar.wine.utils.Regex.ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE;
import static com.cellar.wine.utils.Regex.ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Producer extends BaseEntity {

    @Builder
    public Producer(Long id, String name, String country, String appellation, Set<Wine> wines) {
        super(id);
        this.name = name;
        this.country = country;
        this.appellation = appellation;
        if(wines != null) {
            this.wines = wines;
        }
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producer")
    private Set<Wine> wines = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE)
    @Column(name = "name")
    private String name;

    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE)
    @Column(name = "country")
    private String country;

    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE)
    @Column(name = "appellation")
    private String appellation;

    @Pattern(regexp = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_PATTERN, message = ALPHANUMERIC_SPACES_HYPHEN_PERIOD_MESSAGE)
    @Column(name = "province")
    private String province;

    public Wine getWine(String label) {
        return getWine(label, false);
    }

    public Wine getWine(String label, boolean ignoreNew) {
        label = label.toLowerCase();
        for (Wine wine : wines) {
            if (!ignoreNew || !wine.isNew()) {
                String compName = wine.getLabel();
                compName = compName.toLowerCase();
                if(compName.equals(label)) {
                    return wine;
                }
            }
        }
        return null;
    }
}