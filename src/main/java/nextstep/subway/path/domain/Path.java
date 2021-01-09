package nextstep.subway.path.domain;

import java.util.List;

public class Path {

    private final PathSections pathSections;

    private final Distance distance;

    public Path(final PathSections pathSections, final int distance) {
        this(pathSections, Distance.valueOf(distance));
    }

    public Path(final PathSections pathSections, final Distance distance) {
        this.pathSections = pathSections;
        this.distance = distance;
    }

    public List<PathStation> getPathStations() {
        return pathSections.getPathStations();
    }

    public int getDistance() {
        return distance.getValue();
    }
}
