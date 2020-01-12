package com.example.demo.menu.controller;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.BookDaO;
import com.example.demo.dao.UserInfoDao;
import com.example.demo.dao.VisitInfoDao;
import com.example.demo.index.model.Token;
import com.example.demo.menu.dao.BoardMessageDao;
import com.example.demo.menu.dao.StampDao;
import com.example.demo.menu.dao.UserMessageDao;
import com.example.demo.menu.model.BoardMessage;
import com.example.demo.menu.model.Stamp;
import com.example.demo.menu.model.UserMessage;
import com.example.demo.model.Article;
import com.example.demo.model.Book;
import com.example.demo.model.OtherInfo;
import com.example.demo.model.UserInfo;
import com.example.demo.user.dao.UserSessionDaO;
import org.apache.commons.io.IOUtils;
import org.jodconverter.DocumentConverter;
import org.jodconverter.office.OfficeException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
public class menuController {
    @Autowired
    UserSessionDaO daO;
    @Autowired
    UserMessageDao dao;
    @Autowired
    BoardMessageDao dao2;
    @Autowired
    StampDao dao3;
    @Autowired
    BookDaO dao4;
    @Autowired
    private DocumentConverter converter;
    @Autowired
    private VisitInfoDao dao5;
    @Autowired
    private ArticleDao dao6;
    @Autowired
    private  UserInfoDao dao7;

    @RequestMapping("/menu")
    public String menu(@RequestParam(value = "username",defaultValue = "")String username,Model m,HttpServletRequest req){
        if(username==null){
            username=((Token)req.getSession().getAttribute("token")).getUsername();
        }
        m.addAttribute("username",username);
    return "menu";
    }
    @RequestMapping("/log")
    public String logout(@RequestParam(name="username",defaultValue = "") String username){
        daO.delete(daO.getByUsername(username));
        return "index";
    }

    @RequestMapping("/library1")
    public String library1(){
        return "library1";
    }
    @RequestMapping("/library2")
    public String library2(){
        return "library2";
    }

    @RequestMapping("/contact")
    public String contact(){
       return "contact";
    }

    @RequestMapping("/leaveMessage")
    public String receiveMessage(UserMessage message){
        System.out.print(message);
        dao.save(message);
        return "contact";
    }

    @RequestMapping("/messageBoard")
    public String messageBoard(){
        return "redirect:/listBoardMessage";
    }

    @RequestMapping("/listBoardMessage")
    public String listBoardMessage(Model m, @RequestParam(name="start",defaultValue ="1")int start){
        start=start<2?0:start-1;
        Sort sort=new Sort(Sort.Direction.ASC, "id");
        Pageable pageable=new PageRequest(start,5,sort);
        Page<BoardMessage> page=dao2.findAll(pageable);
        Iterator<BoardMessage> iterator=page.iterator();
        m.addAttribute("page",page);
        System.out.print(page);
        if(iterator.hasNext())
        m.addAttribute("page1",iterator.next());
        else m.addAttribute("page1",new BoardMessage("xxx","内容尚空，等你来哟"));
        if(iterator.hasNext())
        m.addAttribute("page2",iterator.next());
        else m.addAttribute("page2",new BoardMessage("xxx","内容尚空，等你来哟"));
        if(iterator.hasNext())
        m.addAttribute("page3",iterator.next());
        else m.addAttribute("page3",new BoardMessage("xxx","内容尚空，等你来哟"));
        if(iterator.hasNext())
        m.addAttribute("page4",iterator.next());
        else m.addAttribute("page4",new BoardMessage("xxx","内容尚空，等你来哟"));
        if(iterator.hasNext())
        m.addAttribute("page5",iterator.next());
        else m.addAttribute("page5",new BoardMessage("xxx","内容尚空，等你来哟"));
        return "messageboard";
    }

    @RequestMapping("/saveMessage")
    @ResponseBody
    public String saveMessage(@RequestParam("message")String message, HttpServletRequest req){
        dao2.save(new BoardMessage(((Token) req.getSession().getAttribute("token")).getUsername(),message));
        return "success";
    }

    @RequestMapping("/stamp")
    public String stamp(Model m,HttpServletRequest req){
        String username=((Token)req.getSession().getAttribute("token")).getUsername();
        List<Stamp> list=dao3.getAllByUsername(username);
        int num=0;
        for (Stamp stamp : list) {
            num++;
        }
        m.addAttribute("num",num);
        m.addAttribute("username",username);
        for(int i=1;i<=6;i++){
            Iterator<Stamp> iterator=list.iterator();
            if(num>=i){
                m.addAttribute("book"+i,dao4.findByIndexing((iterator.next().getIndexing())));
            }
        }
        return "stamp";

    }

    @RequestMapping("/stampBook/add")
    @ResponseBody
    public String stampBook(@RequestParam("user")String user,@RequestParam("index") String index){
        Iterator<Stamp> list=dao3.findAll().iterator();
        int length=0;
        while (list.hasNext()){
            length++;
            list.next();
        }
        if(length>=6) return "no";//只允许收藏6本
        Stamp stamp=dao3.getByUsernameAndIndexing(user,index);
        System.out.println(user);
        System.out.println(index);
       if(stamp==null) {
           dao3.save(new Stamp(user,index));
           Book book=dao4.findByIndexing(index);
           book.setStamp(book.getStamp()+1);
           dao4.save(book);
           return "yes";
       }
       else{
           return "no";
       }
    }

    @RequestMapping("/stampBook/cancel")
    public  String cancel(@RequestParam("index")String indexing){
        dao3.delete(dao3.getByIndexing(indexing));
        return "redirect:/stamp";
    }
    @RequestMapping("/stampBook/read")
    public  String read(@RequestParam("index")String indexing, HttpServletResponse res){
        String url=dao4.findByIndexing(indexing).getFilePath1();
        File file=new File(url);
        try{
            File pdfAddr=new File("D:/librarybook/pdf");
            if(!pdfAddr.exists()) pdfAddr.mkdirs();
            converter.convert(file).to(new File("D:/librarybook/pdf"+File.separator+"book"+indexing+".pdf")).execute();
            ServletOutputStream outputStream=res.getOutputStream();
            FileInputStream inputStream=new FileInputStream(new File("D:/librarybook/pdf"+File.separator+"book"+indexing+".pdf"));
            int i = IOUtils.copy(inputStream, outputStream);
            System.out.println(i);
            inputStream.close();
            outputStream.close();
        } catch (OfficeException | IOException e) {
            e.printStackTrace();
        }
        return "This is pdf for book"+indexing;
    }

    @RequestMapping("/article")
    public String article(Model m,HttpServletRequest request){
        Article article=dao6.randomGet();
        String userName=((Token)request.getSession().getAttribute("token")).getUsername();
        m.addAttribute("head",article.getHead());
        m.addAttribute("content",article.getContent());
        m.addAttribute("author",article.getAuthor());
        m.addAttribute("time",article.getTime());
        m.addAttribute("username",userName);
        return "article";
    }

    @RequestMapping("/personalInfo")
    public String personalInfo(HttpServletRequest request,Model m){
        String userName=((Token)request.getSession().getAttribute("token")).getUsername();
        UserInfo info=dao7.get(userName);
        m.addAttribute("userInfo",info);
        return "userinfo";
    }

    @RequestMapping("/userInfo/notebook")
    public String notebook(HttpServletRequest request,Model m){
        String userName=((Token)request.getSession().getAttribute("token")).getUsername();
        UserInfo info=dao7.get(userName);
        m.addAttribute("otherInfo",info.getOtherInfo());
        return "personalInfoEdit";
    }

    @RequestMapping("/userInfo/update")
    public String update(HttpServletRequest request,@RequestParam("favBook")String favBook,@RequestParam("favWriter")String favWriter,@RequestParam("kind")String kind,@RequestParam("reason")String reason){
        OtherInfo info=new OtherInfo(favBook,favWriter,kind,reason);
        HashMap<String,Object> map=new HashMap<>();
        System.out.println(info);
        map.put("otherInfo",info);
        dao7.update(((Token)request.getSession().getAttribute("token")).getUsername(),map);
        return "redirect:/userInfo/notebook";
    }




}
