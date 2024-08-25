package com.tusaryan.basic_project_structure.prod_ready_features.prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/** Using Spring Security :
 * To get the current admin/user who is trying to update/create this(post) particular entity.
 * from auditor aware interface we get the username and attach it to CreatedBy or LastModifiedBy field.
 * */
//pass inside generics <type of id> then option+return to implement getCurrentAuditor method
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        /** Steps we will be doing later
         * get security context
         * get authentication
         * get the principle (user who logged in with the credentials)
         * get the username
         * Then return that username
         * After creating the auditor aware Impl then we create the bean of this so that it is ready to use.
         * To create bean use AppConfig/any config
         * */

        //return the name of this current auditor, for now we are just passing it ourselves
        //for now we are hard coding this name just for learning purpose, this is never recommended.
        return Optional.of("Aryan Kumar");
    }
}
