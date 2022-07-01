package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class TodoController {
    @Autowired
    TodoRepo repo;
    @RequestMapping("/")
    public ModelAndView home()
    {
        ModelAndView mv = new ModelAndView("index");
        List<Todo> data = repo.findAll();
        mv.addObject("datas", data);
        return mv;
    }

    @RequestMapping(value = "/", method= RequestMethod.POST)
    public String submit(@RequestParam(required = false) Map<String, String> allParams)
    {
        String title = allParams.get("title");
        String description = allParams.get("description");
        System.out.println(title+" "+description);
        Todo to = new Todo();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String cdate = dtf.format(now);
        to.setTitle(title);
        to.setDescription(description);
        to.setCdate(cdate);
        to.setCstatus(false);
        int id;
        if(repo.findMaxId() == null)
        {
            id = 1;
        }
        else
        {
            id = repo.findMaxId() + 1;
        }
        to.setId(id);
        repo.save(to);
        return "redirect:/";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public String deleteById(@PathVariable("id") int id)
    {
        Optional<Todo> opto = repo.findById(id);
        if(opto.isPresent())
        {
            repo.deleteById(id);
        }
        else
        {
            System.out.println("Not present");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/togglestatus/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String toggleStatusById(@PathVariable("id") int id) {
        System.out.println("id : " + id);

        Todo doto = repo.getById(id);
        if (doto.isCstatus()){
            doto.setCstatus(false);
        }
        else{
            doto.setCstatus(true);
        }
        repo.save(doto);
        return "done";
    }


}
