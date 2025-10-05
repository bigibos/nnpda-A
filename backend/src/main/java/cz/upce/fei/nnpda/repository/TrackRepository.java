package cz.upce.fei.nnpda.repository;

import cz.upce.fei.nnpda.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Long> {
}
