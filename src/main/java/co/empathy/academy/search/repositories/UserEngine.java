package co.empathy.academy.search.repositories;

import co.empathy.academy.search.models.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface UserEngine extends ElasticsearchRepository<User, String>{

}
