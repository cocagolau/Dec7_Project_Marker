package me.dec7.marker.repository;

import me.dec7.marker.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByName(String name);
}
