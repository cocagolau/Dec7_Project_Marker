package me.dec7.marker.repository;

import me.dec7.marker.entity.Log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository  extends JpaRepository<Log, Long>{
	
}
