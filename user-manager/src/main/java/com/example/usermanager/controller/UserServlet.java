package com.example.usermanager.controller;

import com.example.usermanager.model.Country;
import com.example.usermanager.model.User;
import dao.CountryDao;
import dao.ICountryDao;
import dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    //    private static final long serialVersionUID = 1L;
    private UserDao userDAO;
    private ICountryDao iCountryDao;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDao();
        iCountryDao = new CountryDao();

        if (this.getServletContext().getAttribute("countryList") == null) {
            this.getServletContext().setAttribute("countryList", iCountryDao.selectAllCountry());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
                case "sales":
                    showSalesPage(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showSalesPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user/sales.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Country> listCountry = iCountryDao.selectAllCountry();
        request.setAttribute("listCountry", listCountry);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/edit.jsp");
        List<Country> countryList = iCountryDao.selectAllCountry();
        request.setAttribute("listCountry", countryList);
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int idCountry = Integer.parseInt(request.getParameter("idCountry"));
        User newUser = new User(name, email, idCountry);

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(newUser);

        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", getErrorFromContraint(constraintViolations));
            request.setAttribute("user", newUser);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user/create.jsp");
            requestDispatcher.forward(request, response);
        } else {

            request.setAttribute("success", "Insert success xxx");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/user/create.jsp");
            requestDispatcher.forward(request, response);
        }


//        userDAO.insertUser(newUser);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/create.jsp");
//        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int idCountry = Integer.parseInt(request.getParameter("idCountry"));
        User newUser = new User(id, name, email, idCountry);
        userDAO.updateUser(newUser);

        List<Country> countryList = iCountryDao.selectAllCountry();
        request.setAttribute("listCountry", countryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);

        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user/list.jsp");
        dispatcher.forward(request, response);
    }

    private HashMap<String, List<String>> getErrorFromContraint(Set<ConstraintViolation<User>> constraintViolations) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (ConstraintViolation<User> c : constraintViolations) {
            if (hashMap.keySet().contains(c.getPropertyPath().toString())) {
                hashMap.get(c.getPropertyPath().toString()).add(c.getMessage());
            } else {
                List<String> listMessages = new ArrayList<>();
                listMessages.add(c.getMessage());
                hashMap.put(c.getPropertyPath().toString(), listMessages);
            }
        }
        return hashMap;
    }
}
