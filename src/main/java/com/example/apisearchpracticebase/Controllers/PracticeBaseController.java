package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.PracticeBase;
import com.example.apisearchpracticebase.Repositories.PracticeBaseRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("practiceBase")
public class PracticeBaseController {
    @Autowired
    PracticeBaseRepos practiceBaseRepos;

    @RequestMapping("/all")
    public Iterable<PracticeBase> getAll(){
        return practiceBaseRepos.findAll();
    }

    @RequestMapping("getAtName/{name}")
    public Iterable<PracticeBase> getAtName(@PathVariable("name") String value){
        if(value.equals("zero")){
            return practiceBaseRepos.findAll();
        }else{
            return practiceBaseRepos.getPracticeBaseByNameBaseContains(value);
        }
    }

    @RequestMapping("getAtFilter/{filter}")
    public Iterable<PracticeBase> getAtFilter(@PathVariable("filter") String filter){
        switch(filter) {
            case "Набор открыт":
                return practiceBaseRepos.getPracticeBaseByStatusDialingEquals("Active");
            case "Набор закрыт":
                return practiceBaseRepos.getPracticeBaseByStatusDialingEquals("Inactive");
            case "А-Я":
                return practiceBaseRepos.findAll(Sort.by(Sort.Direction.ASC, "nameBase"));
            case "Я-А":
                return practiceBaseRepos.findAll(Sort.by(Sort.Direction.DESC, "nameBase"));
            case "С номером телефона":
                return practiceBaseRepos.findByManagerContactPhoneNumberIsNot("");
            case "С электронной почтой":
                return practiceBaseRepos.findByManagerContactEmailIsNot("");
            case "С Вконтакте":
                return practiceBaseRepos.findByManagerContactVkPageDataIsNot("");
            case "С WhatsApp":
                return practiceBaseRepos.findByManagerContactWhatsAppDataIsNot("");
            case "С Telegram":
                return practiceBaseRepos.findByManagerContactTelegramDataIsNot("");
            default:
                return practiceBaseRepos.findAll();
        }
    }
}
