package com.example.demo.user.controller;

import com.example.demo.dao.BookDaO;
import com.example.demo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class userBookController {
    @Autowired
    BookDaO bookDAO;

    @RequestMapping("/userlist")
    public String userlist(Model m, @RequestParam(name = "start", defaultValue = "0") int start, @RequestParam(name = "size", defaultValue = "6") int size,@RequestParam("username")String username) {
        start = start < 0 ? 0 : start;
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page<Book> page = bookDAO.findAll(pageable);
        Iterator<Book> iterator=page.iterator();
        int num=0;
        while (iterator.hasNext()){
            num++;
            iterator.next();
        }
        m.addAttribute("page", page);
        m.addAttribute("num",num);
        m.addAttribute("username",username);
        Iterator<Book> iterator1=page.iterator();
        if(num>=1)m.addAttribute("book1",iterator1.next());
        if(num>=2)m.addAttribute("book2",iterator1.next());
        if(num>=3)m.addAttribute("book3",iterator1.next());
        if(num>=4)m.addAttribute("book4",iterator1.next());
        if(num>=5)m.addAttribute("book5",iterator1.next());
        if(num>=6)m.addAttribute("book6",iterator1.next());
        return "listBook2";
    }

    @RequestMapping("/download")
    public void download(@RequestParam("id") int id, HttpServletResponse res) {
        Book book = bookDAO.getOne(id);
        File file = new File(book.getFilePath1().replaceAll("\\\\", "/"));
        try {
            res.setContentType("application/force-download");// 设置强制下载不打开
            res.addHeader("Content-Disposition", "attachment;fileName=" + file.getName());// 设置文件名
            BufferedInputStream istream = new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream ostream = res.getOutputStream();
            byte[] box = new byte[1024];
            int i = istream.read(box);
            while (i != -1) {
                ostream.write(box, 0, i);
                i=istream.read(box);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/search")
    @ResponseBody
    public Map<String, String> search(@RequestParam(name = "index",defaultValue = "")String index,@RequestParam(name = "name",defaultValue = "")String name, @RequestParam(name = "category",defaultValue = "") String category){
        Book book;
        if(!index.equals("")){ //索引优先，若无索引，则按照书名分类查找，若无分类则按照书名查找
            book=bookDAO.findByIndexing(index);
        }
        else{
            if(category.equals("")) book=bookDAO.findByName(name);
            else book=bookDAO.findByNameAndCategory(name,category);

        }
        Map<String,String> map=new HashMap<>();
        if(book==null) map.put("status","no");
        else{
            map.put("status","yes");
            map.put("name",book.getName());
            map.put("category",book.getCategory());
            map.put("id",String.valueOf(book.getId()));
            map.put("index",book.getIndexing());
        }
        return map;
    }
}




