package controller;



import model.Customer;
import service.CustomerServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "customerServiceServlet",urlPatterns = "/customerList")
public class customerServiceServlet extends HttpServlet {
    CustomerServiceImpl customerServiceObj = new CustomerServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                try {
                    addNewCustomer(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    updateCustomer(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteCustomer(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                try {
                    showEditFrom(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    showDeleteForm(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                try {
                    displayList(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void displayList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Customer> customers = customerServiceObj.findAll();
        request.setAttribute("customerList",customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("displayList.jsp");
        dispatcher.forward(request,response);
    }
    // create function
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNew.jsp");
        dispatcher.forward(request,response);
    }
    private void addNewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        boolean isMale;
        String gender = request.getParameter("gender");
        if("male".equals(gender))
            isMale = true;
        else
            isMale = false;
        Customer newCustomer = new Customer(name,address,age,isMale);
        customerServiceObj.add(newCustomer);
        displayList(request,response);
    }
    //edit function
    private void showEditFrom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer editedCustomer = new Customer();
        for (Customer customerObj:customerServiceObj.findAll() ) {
            if(customerObj.getId()==id)
                editedCustomer = customerObj;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("customer",editedCustomer);
        dispatcher.forward(request,response);
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        boolean isMale;
        if(gender.equals("male"))
            isMale = true;
        else  isMale = false;
        Customer editedCustomer = new Customer(id,name,address,age,isMale);
        for (Customer customerObj:customerServiceObj.findAll() ) {
            if(customerObj.getId()==id)
                customerObj = editedCustomer;
        }
        customerServiceObj.update(id,editedCustomer);
        displayList(request,response);
    }

    //delete function
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer deletedCustomer = new Customer();
        for (Customer customerObj:customerServiceObj.findAll() ) {
            if(customerObj.getId()==id)
                deletedCustomer = customerObj;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
        request.setAttribute("customer",deletedCustomer);
        dispatcher.forward(request,response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerServiceObj.delete(id);
        displayList(request,response);
    }
}
