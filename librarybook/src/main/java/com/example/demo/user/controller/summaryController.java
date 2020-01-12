package com.example.demo.user.controller;

import com.example.demo.dao.BookDaO;
import com.example.demo.model.Book;
import com.example.demo.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.ArrayList;

@Controller
public class summaryController {
    @Autowired
    BookDaO bookDAO;

    @RequestMapping("/summary")
    public String summary(@RequestParam("id")int id, Model m){
        Book book=bookDAO.getOne(id);
        String path=book.getFilePath2();
        File file=new File(path.replaceAll("\\\\","/"));
        try {
            BufferedReader reader=new BufferedReader(new FileReader(file));
            StringBuilder builder=new StringBuilder();
            int chr;
            while((chr=reader.read())!=-1){
                builder.append((char)chr);
            }
            String str=builder.toString();
            String[] array=str.split("/");
            ArrayList<String> array2=new ArrayList<>();
            for(String s:array){
                array2.add(s.split(":")[1]);
                System.out.print(s.split(":")[1]);
            }
            BookInfo info=new BookInfo(array2);
            m.addAttribute("info",info);
            return "summaryBook";

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
