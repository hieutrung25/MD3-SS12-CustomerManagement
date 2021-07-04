package service;
import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerServiceImpl implements CustomerService<Customer> {
    private String jdbcURL = "jdbc:mysql://localhost:3306/customers?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customertbl (name, address,age,isMale) VALUES " +
            " (?, ?, ?, ?);";

    private static final String SELECT_CUSTOMER_BY_ID = "select id,name,address,age,isMale from customertbl where id =?";
    private static final String SELECT_ALL_CUSTOMER = "select * from customertbl";
    private static final String DELETE_CUSTOMER_SQL = "delete from customertbl where id = ?;";
    private static final String UPDATE_CUSTOMER_SQL = "update customertbl set name = ?,address= ?, age =?,isMale =? where id = ?;";

    public CustomerServiceImpl() {
    }

    protected Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        return connection;
    }

    private static HashMap<Integer, Customer> customers = new HashMap<>();
    static{
//        public customer(int id, String name, String address, int age, boolean isMale)
        customers.put(1,new Customer(1,"Xuan","Ha Noi", 26,false));
        customers.put(2,new Customer(2,"Ha","Nghe An", 25,false));
        customers.put(3,new Customer(3,"Thu","Quang Nam", 25,false));
        customers.put(4,new Customer(4,"Dong","Da Nang", 21,true));
        customers.put(5,new Customer(5,"Tay","TP HCM", 20,true));
        customers.put(6,new Customer(6,"Nam","Can Tho", 33,true));
        customers.put(7,new Customer(7,"Bac","Tien Giang", 17,true));
    }

    @Override
    public List<Customer> findAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            int age = resultSet.getInt("age");
            boolean isMale = resultSet.getBoolean("isMale");
            Customer customer = new Customer(id,name,address,age,isMale);
            customerList.add(customer);
        }
      return customerList;
    }

    @Override
    public void add(Customer object) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);
        preparedStatement.setString(1,object.getName());
        preparedStatement.setString(2,object.getAddress());
        preparedStatement.setInt(3,object.getAge());
        preparedStatement.setBoolean(4,object.isMale());
        int addedRow = preparedStatement.executeUpdate();
        System.out.println("The number of added row: "+ addedRow);
        connection.close();
    }

    @Override
    public void update(int updateId, Customer object) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);
        preparedStatement.setString(1,object.getName());
        preparedStatement.setString(2,object.getAddress());
        preparedStatement.setInt(3,object.getAge());
        preparedStatement.setBoolean(4,object.isMale());
        preparedStatement.setInt(5, updateId);
        int updatedRow =preparedStatement.executeUpdate();
        System.out.println("The number of updated row: "+ updatedRow);
        connection.close();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL);
        preparedStatement.setInt(1,id);
        int deletedRow =preparedStatement.executeUpdate();
        System.out.println("The number of deleted row: "+ deletedRow);
        connection.close();
    }
}
