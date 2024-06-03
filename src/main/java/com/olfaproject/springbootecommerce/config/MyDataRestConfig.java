package com.olfaproject.springbootecommerce.config;

import com.olfaproject.springbootecommerce.entity.Country;
import com.olfaproject.springbootecommerce.entity.Product;
import com.olfaproject.springbootecommerce.entity.ProductCategory;
import com.olfaproject.springbootecommerce.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private EntityManagerFactory entityManagerFactory;

    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions= {HttpMethod.DELETE, HttpMethod.POST, HttpMethod.PUT};

        disableHttpMethods(Product.class,config, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class,config, theUnsupportedActions);
        disableHttpMethods(Country.class,config, theUnsupportedActions);
        disableHttpMethods(State.class,config, theUnsupportedActions);
        // call an internal helper mathod
        exposeIds(config);

    }

    private void disableHttpMethods(Class theclass,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theclass)
                .withItemExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                .withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions)));
    }

    //call an internal helper method
    private void exposeIds(RepositoryRestConfiguration config){
        // exposz entity ids
        //
        // get a list of all entity classesfrom the entity manager

        Set<EntityType<?>> entites = entityManagerFactory.getMetamodel().getEntities();
        //  create an array of the entity types
        List<Class> entityClasses = new ArrayList<>();
        // get the entity types for the entities
        for( EntityType tempEntityType : entites){
            entityClasses.add(tempEntityType.getJavaType());
        }
        // expose the entity ids for the array of entity/domain types
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }


}

