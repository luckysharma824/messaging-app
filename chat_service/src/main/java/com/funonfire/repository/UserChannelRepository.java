package com.funonfire.repository;

import com.funonfire.entity.UserChannel;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChannelRepository extends CassandraRepository<UserChannel, String> {
    @AllowFiltering
    UserChannel findByUserIdAndChannelId(String userId, String chatId);

}
