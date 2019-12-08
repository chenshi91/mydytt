package com.dytt.module.movie.feign;

import com.dytt.module.movie.fallback.MovieApiFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-module-movie",fallback = MovieApiFeignFallback.class)
public interface MovieApiFeign {

}
