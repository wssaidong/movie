package com.x.movie.domain.repository;

import com.x.movie.domain.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:
 * @Author caisd1
 * @Date 2019-9-24
 * @Version V1.0
 **/
public interface MovieRepository extends MongoRepository<Movie, String> {
}
