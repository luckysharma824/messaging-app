package com.funonfire.repository;

import com.funonfire.entity.Message;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CassandraRepository<Message, String> {
    @AllowFiltering
    Slice<Message> findByChannelId(String channelId, Pageable pageable);
}
