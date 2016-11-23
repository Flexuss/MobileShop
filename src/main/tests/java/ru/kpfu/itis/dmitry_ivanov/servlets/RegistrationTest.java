package ru.kpfu.itis.dmitry_ivanov.servlets;

import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mockito;
import ru.kpfu.itis.dmitry_ivanov.database.Database;
import ru.kpfu.itis.dmitry_ivanov.servlets.Registration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Dmitry on 23.11.2016.
 */
public class RegistrationTest extends Mockito {

    Database db=new Database();

    @Test
    public void createUser() throws ServletException, IOException {
        HttpServletRequest request=mock(HttpServletRequest.class);
        HttpServletResponse response=mock(HttpServletResponse.class);
        when(request.getParameter("login")).thenReturn("test");
        when(request.getParameter("password")).thenReturn("test");
        when(request.getParameter("e-mail")).thenReturn("test@mail.ru");
        when(request.getParameter("confirmPassword")).thenReturn("test");

        assertTrue(db.checkuser("test"));

        new Registration().doPost(request,response);

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("e-mail");
        verify(request, atLeast(1)).getParameter("confirmPassword");

        assertFalse(db.checkuser("test"));
        db.removeUser("test");
    }
}