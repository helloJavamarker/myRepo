package com.example.demo.com.test.dao.es;

import com.example.demo.com.test.entity.es.TestBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsDAO  extends CrudRepository<TestBean, Long> {
//    Iterable<TestBean> findAll() ;
//
//    void saveAll(List<TestBean> list) ;

//    void save(TestBean bean) ;


    List<TestBean> findByName(String name);

    List<TestBean> findByNameOrDesc(String name, String desc);
}
