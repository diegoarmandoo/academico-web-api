package br.com.academico.persistence;

import javax.inject.Singleton;
import javax.sql.DataSource;
import org.glassfish.hk2.api.Factory;
import org.jvnet.hk2.annotations.Service;
import org.postgresql.ds.PGSimpleDataSource;

@Service
public class PostgresDataSourceProvider implements Factory<DataSource> {

    private final String[] hostsDB = new String[] {"localhost"};
	private final int[] portsDB = new int[] {5432};
	private final String nameDB = "academico-db";
	private final String userDB = "postgres";
	private final String pswDB = "postgres";

    @Override
    public DataSource provide() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setServerNames(this.hostsDB);
        dataSource.setPortNumbers(this.portsDB);
        dataSource.setDatabaseName(this.nameDB);
        dataSource.setUser(this.userDB);
        dataSource.setPassword(this.pswDB);
        return dataSource;
    }

    @Override
    public void dispose(DataSource arg0) {
        // TODO Auto-generated method stub
        
    }

}
    
