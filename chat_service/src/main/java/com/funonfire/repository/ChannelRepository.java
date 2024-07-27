package com.funonfire.repository;

import com.funonfire.entity.Channel;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends CassandraRepository<Channel, String> {
    Channel findByName(String name);

    @AllowFiltering
    @Query(value = "select c from UserChannel uc, Channel c where uc.channelId = c.id and uc.userId =?1;")
    Slice<Channel> findByUserId(String userId, Pageable pageable);

}
