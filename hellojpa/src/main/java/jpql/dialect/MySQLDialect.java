package jpql.dialect;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;

import static org.hibernate.type.StandardBasicTypes.STRING;

public class MySQLDialect extends MySQL8Dialect {

    public MySQLDialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", STRING));
    }
}
