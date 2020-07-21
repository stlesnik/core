package controller;

import model.Atm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.AtmService;

@Controller
public class atmController {
    private AtmService atmService;

    @Autowired()
    @Qualifier(value = "atmService")
    public void setAtmService(AtmService atmService){
        this.atmService = atmService;
    }


    @RequestMapping(value = "/atm", method = RequestMethod.GET)
    public String listAtm(Model model){
        model.addAttribute("atm", new Atm());
        model.addAttribute("listAtm", this.atmService.listAtm());

        return "atm";
    }

    @RequestMapping(value = "/atm/add", method = RequestMethod.POST)
    public String addAtm(@ModelAttribute("atm") Atm atm){
        if(atm.getID() == 0){
            this.atmService.addAtm(atm);
        }else{
            this.atmService.updateAtm(atm);
        }
        return "redirect:/atm";
    }

    @RequestMapping("/remove/{id}")
    public String removeAtm(@PathVariable("id") int id){
        this.atmService.removeAtm(id);

        return "redirect:/atm";
    }

    @RequestMapping("/edit/{id}")
    public String editAtm(@PathVariable("id") int id, Model model){
        model.addAttribute("atm", this.atmService.getAtmById(id));
        model.addAttribute("listAtm", this.atmService.listAtm());

        return "atm";
    }

    @RequestMapping("atmdata/{id}")
    public String atmData(@PathVariable("id") int id, Model model){
        model.addAttribute("atm", this.atmService.getAtmById(id));

        return "atmdata";
    }
}
