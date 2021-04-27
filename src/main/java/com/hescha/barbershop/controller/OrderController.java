package com.hescha.barbershop.controller;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.hescha.barbershop.entity.MService;
import com.hescha.barbershop.entity.Order;
import com.hescha.barbershop.entity.User;
import com.hescha.barbershop.service.MServiceService;
import com.hescha.barbershop.service.OrderService;
import com.hescha.barbershop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService service;

    @Autowired
    MServiceService serviceMService;

    @Autowired
    UserServiceImpl serviceUser;

    @GetMapping
    String get(Model model) {
        List<Order> list = service.repository.findAll();
        model.addAttribute("list", list);
        return "order-list";
    }


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createOrUpdate(Order entity, @Param("serId") Long serId,
								 Principal princopal) {
        entity.setMservice(serviceMService.read(serId));
        if (entity.getUser() == null)
            entity.setUser(serviceUser.findByUsername(princopal.getName()));
        service.create(entity);
        return "redirect:/order";
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/order";
    }

    @RequestMapping(path = "/bookNow", method = RequestMethod.GET)
    public String bookNow(Model model, Principal principal) {
        model.addAttribute("list", serviceMService.repository.findAll());
        model.addAttribute("entity",
				serviceUser.findByUsername(principal.getName()));
        return "orderPage";
    }

    @RequestMapping(path = "/bookNow", method = RequestMethod.POST)
    public String bookNowPOST(Model model, @Param("serId") Long serId,
							  @Param("dateTo") Date dateTo,
							  @Param("timeTo") Time timeTo,
							  Principal principal) throws Exception {
        User user = serviceUser.findByUsername(principal.getName());
        Order order = new Order();
        Date dFrom = new Date(System.currentTimeMillis());
        order.setDateFrom(dFrom);
        order.setDateTo(dateTo);
        order.setTimeTo(timeTo);
        order.setUser(user);
        service.create(order);


        Order order2 = service.repository.findByUserAndDateFromAndDateTo(user
				, dFrom, dateTo);
        MService serv = serviceMService.read(serId);
        serv.getOrderss().add(order2);
        serviceMService.update(serv);

        Order order3 = service.read(order2.getId());
        order3.setMservice(serv);
        service.update(order3);
        return "redirect:/user/history";
    }

    @RequestMapping(path = "/paiding/{id}", method = RequestMethod.GET)
    public String paiding(Model model, @PathVariable("id") Long id) {
        Order order = service.read(id);
        order.setPaid(true);
        service.update(order);
        return "redirect:/order";
    }

    @RequestMapping(path = "/ending/{id}", method = RequestMethod.GET)
    public String bookNow(Model model, @PathVariable("id") Long id) {
        Order order = service.read(id);
        order.setEnded(true);
        service.update(order);
        return "redirect:/order";
    }

    @RequestMapping(path = "/cancelReservation/{id}")
    public String cancel(Model model, @PathVariable("id") Long id) throws Exception {
        service.delete(id);
        return "redirect:/user/history";
    }

}
