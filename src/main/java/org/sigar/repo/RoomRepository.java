package org.sigar.repo;

import org.sigar.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository  extends JpaRepository<Room,Long> {
}
