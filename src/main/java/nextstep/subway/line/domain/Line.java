package nextstep.subway.line.domain;

import lombok.Builder;
import nextstep.subway.BaseEntity;
import nextstep.subway.common.Money;
import nextstep.subway.station.domain.Station;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
public class Line extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "surcharge")
    private Money surcharge;

    @Embedded
    private Sections sections;

    public Line() {
    }

    public Line(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line(String name, String color, Station upStation, Station downStation, int distance) {
        this(name, color, upStation, downStation, distance, 0);
    }

    @Builder
    public Line(String name, String color, Station upStation, Station downStation, int distance, int surcharge) {
        this.name = name;
        this.color = color;
        Section section = new Section(this, upStation, downStation, distance);
        this.sections = new Sections(Arrays.asList(section));
        this.surcharge = Money.valueOf(surcharge);
    }

    public void update(Line line) {
        this.name = line.getName();
        this.color = line.getColor();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<Section> getSections() {
        return sections.getSections();
    }

    public List<Station> getOrderedStations() {
        return sections.getOrderedStations();
    }

    public Money getSurcharge() {
        return surcharge;
    }

    public void add(final Section section) {
        sections.add(section);
    }

    public void remove(final Station station) {
        sections.remove(station);
    }
}
