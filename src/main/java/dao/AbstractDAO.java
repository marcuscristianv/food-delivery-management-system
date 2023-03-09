package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.*;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import connection.ConnectionFactory;

/**
 * Clasa care se ocupa cu nivelul de acces la baza de date, separand astfel business logic de restul
 * @param <T> obiectul pe care se abstractizeaza
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> crtClass) {
        this.type = crtClass;
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    private String createSimpleSelectQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM warehouse.");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    private String createInsertQuery() {
        StringBuilder query = new StringBuilder();
        StringBuilder fields = new StringBuilder();
        StringBuilder values = new StringBuilder();

        query.append("INSERT INTO warehouse." + type.getSimpleName());
        fields.append(" (");
        values.append("VALUES(");

        for(int i = 0; i < type.getDeclaredFields().length; i++) {
            fields.append(type.getDeclaredFields()[i].getName());
            values.append("?");

            if(i+1 == type.getDeclaredFields().length) {
                fields.append(") ");
                values.append(")");
            } else {
                fields.append(",");
                values.append(",");
            }
        }

        query.append("" + fields + values);
        return query.toString();
    }

    private String createUpdateQuery(String setCol, String whereCol) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        sb.append(setCol + " = ?");
        sb.append(" WHERE ");
        sb.append(whereCol + " = ?");

        return sb.toString();
    }

    private String createDeleteQuery(String whereCol) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM warehouse.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(whereCol + " = ?");

        return sb.toString();
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSimpleSelectQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findById " + e.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            return null;
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            for(int i = 0; i < type.getDeclaredFields().length; i++) {
                Field field = type.getDeclaredFields()[i];
                field.setAccessible(true);
                statement.setObject(i+1, field.get(t));
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    public void update(String setCol, String setValue, String whereCol, String whereValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(setCol, whereCol);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, setValue);
            statement.setObject(2, whereValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    public void delete(String whereCol, String condValue) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(whereCol);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            statement.setObject(1, condValue);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
