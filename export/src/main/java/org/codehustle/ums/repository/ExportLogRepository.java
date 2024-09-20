package org.codehustle.ums.repository;

import org.codehustle.ums.entity.ExportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportLogRepository extends JpaRepository<ExportLog,Integer> {
}
