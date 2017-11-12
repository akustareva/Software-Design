package ru.akustareva.sd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.akustareva.sd.dao.ToDoDao;
import ru.akustareva.sd.model.Business;
import ru.akustareva.sd.model.ToDoList;

@Controller
public class ToDoListController {
    @Autowired
    private ToDoDao toDoDao;

    @RequestMapping(value = "/add-list", method = RequestMethod.POST)
    public String addToDoList(@ModelAttribute("product") ToDoList toDoList) {
        toDoDao.addToDoList(toDoList);
        return "redirect:/get-lists";
    }

    @RequestMapping(value = "/get-lists", method = RequestMethod.GET)
    public String getToDoLists(ModelMap map) {
        map.addAttribute("lists", toDoDao.getAllToDoLists());
        map.addAttribute("list", new ToDoList());
        return "index";
    }

    @RequestMapping(value = "/remove-list", method = RequestMethod.GET)
    public String deleteToDoList(@ModelAttribute("list") ToDoList toDoList) {
        toDoDao.deleteToDoListById(toDoList.getId());
        return "redirect:/get-lists";
    }

    @RequestMapping(value = "/edit-list", method = RequestMethod.GET)
    public String editToDoList(@ModelAttribute("list") ToDoList toDoList, ModelMap map) {
        map.addAttribute("list", toDoDao.getListById(toDoList.getId()));
        map.addAttribute("businesses", toDoDao.getAllListBusiness(toDoList));
        map.addAttribute("business", new Business());
        return "list";
    }

    @RequestMapping(value = "/add-business", method = RequestMethod.POST)
    public String addBusinessToList(@ModelAttribute("business") Business business) {
        toDoDao.addBusinessToList(business);
        return "redirect:/edit-list?id=" + business.getListId();
    }

    @RequestMapping(value = "/set-done", method = RequestMethod.POST)
    public String setBusinessAsDone(@ModelAttribute("business") Business business, @RequestParam("sid") String selected) {
        int id = Integer.parseInt(selected);
        Business selectedBusiness = toDoDao.getBusinessById(id);
        toDoDao.setBusinessDoneStatus((selectedBusiness.getIsDone() + 1) % 2, id);
        return "redirect:/edit-list?id=" + business.getListId();
    }
}
