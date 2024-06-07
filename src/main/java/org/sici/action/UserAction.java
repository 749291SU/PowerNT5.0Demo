package org.sici.action;

import lombok.extern.slf4j.Slf4j;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @projectName: PowerNT5.0Demo
 * @package: org.sici.controller
 * @className: UserController
 * @author: 749291
 * @description: TODO
 * @date: 6/7/2024 9:03 AM
 * @version: 1.0
 */

@Slf4j
public class UserAction extends DispatchAction {
    public ActionForward getAllUser(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request,  HttpServletResponse response) {
        System.out.println(request.getContextPath());
        log.info("getAllUser");
        try {
            String sql_commond = "select * from user";
            pnbclient.command.SQLCommandService sqlCommandService = new pnbclient.command.SQLCommandService();
            List users = sqlCommandService.getList(sql_commond);

            HttpSession session = request.getSession();
            session.setAttribute("users", users);
            log.info("set users : {}", users);
        } catch (Exception e) {
            log.info("error");
        }
        return mapping.findForward("getAllUser");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) {
        log.info("save");
        try {
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            Double salary = Double.valueOf(request.getParameter("salary"));


            String sql_commond = "insert into user(username, name, age, gender, phone, salary)\n"
                                    + "values('"+username+"', '"+name+"', "+age+", '" + gender + "', " + phone + ", " + salary + ")";
            pnbclient.command.SQLCommandService sqlCommandService = new pnbclient.command.SQLCommandService();
            sqlCommandService.execute(sql_commond);
        } catch (Exception e) {

        }
        return mapping.findForward("save");
    }

    public ActionForward updateUser(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String phone = request.getParameter("phone");
            String gender = request.getParameter("gender");
            Double salary = Double.valueOf(request.getParameter("salary"));


            String sql_commond = "update user set username = '"+username+"', name = '"+name+ "', gender = '" + gender + "', age = "+age + ", phone = '" + phone + "', salary = " + salary + " where id = " + id;
            pnbclient.command.SQLCommandService sqlCommandService = new pnbclient.command.SQLCommandService();
            sqlCommandService.execute(sql_commond);

            getAllUser(mapping, form, request, response);
        } catch (Exception e) {

        }
        return mapping.findForward("updateUser");
    }

    public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            String sql_commond = "delete from user where id = " + id;
            pnbclient.command.SQLCommandService sqlCommandService = new pnbclient.command.SQLCommandService();
            sqlCommandService.execute(sql_commond);
            getAllUser(mapping, form, request, response);
        } catch (Exception e) {

        }
        return mapping.findForward("deleteUser");
    }
}
