package com.norsche.models.data;

import com.norsche.models.Anime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface AnimeDao extends CrudRepository<Anime, Integer> {
}
