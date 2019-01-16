package com.provys.testws;

import com.provys.provysdb.ProvysDBContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DbService {

    @Inject
    private ProvysDBContext provysDbContext;

    /**
     * @return user used to access Provys database
     */
    public String getUser() {
        return provysDbContext.getUser();
    }

    /**
     * @return Url used to access Provys database
     */
    public String getUrl() {
        return provysDbContext.getUrl();
    }

    /**
     * Executes SELECT 1 FROM dual against PROVYS database and returns result.
     *
     * @return 1 (if something else is returned, it is wrong)
     */
    public int runSelectFromDual() {
        return provysDbContext.createDSL()
                .selectOne()
                .fetchOne()
                .value1();
    }
}
