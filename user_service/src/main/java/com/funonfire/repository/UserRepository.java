package com.funonfire.repository;

import com.funonfire.entity.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {
    @AllowFiltering
    User findByMsisdn(String msisdn);
}
