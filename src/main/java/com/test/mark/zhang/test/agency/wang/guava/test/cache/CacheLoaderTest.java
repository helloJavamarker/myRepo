package com.test.mark.zhang.test.agency.wang.guava.test.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.test.mark.zhang.test.agency.wang.guava.cache.Employee;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/18
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class CacheLoaderTest {

    private boolean isTrue = false;

    @Test
    public void testBasic() throws ExecutionException, InterruptedException {
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder().maximumSize(10)
                .expireAfterAccess(30, TimeUnit.MILLISECONDS)
                .build(createCacheLoader());

        Employee employee = cache.get("Alex");
        assertThat(employee, notNullValue());
        assertLoadFromDBThenReset();
        employee = cache.get("Alex");
        assertThat(employee, notNullValue());
        assertLoadFromCache();

        TimeUnit.MILLISECONDS.sleep(31);
        employee = cache.get("Alex");

        assertThat(employee, notNullValue());
        assertLoadFromDBThenReset();
    }

    @Test
    public void testEvictionBySize() {
        CacheLoader<String, Employee> cacheLoader = createCacheLoader();
        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .maximumSize(3).build(cacheLoader);

        cache.getUnchecked("Alex");
        assertLoadFromDBThenReset();
        cache.getUnchecked("Jack");
        assertLoadFromDBThenReset();

        cache.getUnchecked("Gavin");
        assertLoadFromDBThenReset();

        assertThat(cache.size(), equalTo(3L));
        cache.getUnchecked("Susan");
        assertThat(cache.getIfPresent("Alex"), nullValue());

        //getIfPresent方式获取数据时,如果cache没有数据,不会load
        assertThat(cache.getIfPresent("Susan"), notNullValue());
    }

    @Test
    public void testEvictionByWeight() {
        Weigher<String, Employee> weigher = (key, employee) ->
                employee.getName().length() + employee.getEmpID().length() + employee.getDept().length();

        LoadingCache<String, Employee> cache = CacheBuilder.newBuilder()
                .maximumWeight(45)
                .concurrencyLevel(1)
                .weigher(weigher)
                .build(createCacheLoader());

        cache.getUnchecked("Gavin");
        assertLoadFromDBThenReset();

        cache.getUnchecked("Kevin");
        assertLoadFromDBThenReset();

        cache.getUnchecked("Allen");
        assertLoadFromDBThenReset();
        assertThat(cache.size(), equalTo(3L));
        assertThat(cache.getIfPresent("Gavin"), notNullValue());

        cache.getUnchecked("Jason");

        assertThat(cache.getIfPresent("Kevin"), nullValue());
        assertThat(cache.size(), equalTo(3L));
    }

    private CacheLoader<String, Employee> createCacheLoader() {
        return new CacheLoader<String, Employee>() {
            //load定义数据来源  第一次查数据从数据库,第二次直接拿缓存---有没有调用findEmployeeByName
            @Override
            public Employee load(String key) throws Exception {
                return findEmployeeByName(key);
            }
        };
    }


    private void assertLoadFromDBThenReset() {
        assertThat(true, equalTo(isTrue));
        this.isTrue = false;
    }

    private void assertLoadFromCache() {
        assertThat(false, equalTo(isTrue));
    }


    private Employee findEmployeeByName(final String name) {
        System.out.println("The employee " + name + " is load from DB.");
        isTrue = true;
        return new Employee(name, name, name);
    }
}