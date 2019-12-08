package com.dytt.module.movie.fallback;

import com.dytt.module.movie.feign.MovieApiFeign;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MovieApiFeignFallback  implements FallbackFactory<MovieApiFeign> {
    @Override
    public MovieApiFeign create(Throwable throwable) {
        return null;
    }
}
