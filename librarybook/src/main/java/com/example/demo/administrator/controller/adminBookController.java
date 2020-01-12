package com.example.demo.administrator.controller;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.BookDaO;
import com.example.demo.dao.UserInfoDao;
import com.example.demo.dao.VisitInfoDao;
import com.example.demo.menu.dao.BoardMessageDao;
import com.example.demo.menu.model.BoardMessage;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class adminBookController {
    @Autowired
    BookDaO bookDAO;
    @Autowired
    VisitInfoDao dao;
    @Autowired
    UserInfoDao dao2;
    @Autowired
    ArticleDao dao3;
    @Autowired
    BoardMessageDao dao4;
    @RequestMapping("/list") //图书一览页面
    public String list(Model m){
        Sort sort=new Sort(Sort.Direction.ASC, "id");
        Pageable pageable=new PageRequest(0,1000000,sort);
        Page<Book> page=bookDAO.findAll(pageable);
        m.addAttribute("page",page);

        List<Book> list1=bookDAO.findByCategory("Literature");
        List<Book> list2=bookDAO.findByCategory("History");
        List<Book> list3=bookDAO.findByCategory("Art");
        List<Book> list4=bookDAO.findByCategory("Education");
        List<Book> list5=bookDAO.findByCategory("Military");
        List<Book> list6=bookDAO.findByCategory("Science");

        for(int i=1;i<=3;i++){
            m.addAttribute("literature"+i,getMax(list1));
            m.addAttribute("history"+i,getMax(list2));
            m.addAttribute("art"+i,getMax(list3));
            m.addAttribute("education"+i,getMax(list4));
            m.addAttribute("military"+i,getMax(list5));
            m.addAttribute("science"+i,getMax(list6));
        }
        return "listBook";
    }

    private Book getMax(List<Book> list){
        int max=0;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getStamp()>list.get(max).getStamp()) max=i;
        }
        Book book=list.get(max);
        list.remove(max);
        return book;
    }
    @RequestMapping(value="/delete")   //删除的时候连带书的上传一起删
    public String delete(@RequestParam("id")int id)throws Exception{
        Book book=bookDAO.getOne(id);
        try {
            String filePath1=book.getFilePath1();
            String filePath2=book.getFilePath2();
            String filePath3=book.getFilePath3();
            filePath1=filePath1.replaceAll("\\\\","/");
            filePath2=filePath2.replaceAll("\\\\","/");
            filePath3=filePath3.replaceAll("\\\\","/");
            File f1=new File(filePath1);
            File f2=new File(filePath2);
            File f3=new File(filePath3);
            f1.delete();
            f2.delete();
            f3.delete();
        }catch (Exception e){
            e.printStackTrace();
        }
        bookDAO.delete(book);
        return "redirect:list";
    }
    @RequestMapping(value="/edit")
    public String edit(@RequestParam("id")int id,Model m)throws Exception{
        Book book=bookDAO.getOne(id);
        System.out.print(book.getCategory());
        m.addAttribute("c",book);
        return "editBook";
    }
    @RequestMapping(value = "/add")
    public String add(Book book, @RequestParam("file")MultipartFile file1, @RequestParam("summary")MultipartFile file2, @RequestParam("image")MultipartFile file3)throws Exception{
        addLibrary(book,file1,1);
        addLibrary(book,file2,2);
        addLibrary(book,file3,3);
        bookDAO.save(book);
        return "redirect:/admin/upload";
    }
    @RequestMapping("/update")
    public String update(Book book,@RequestParam("id")int id)throws Exception{
        System.out.println(book);
        Book book2=bookDAO.getOne(id);
        book.setFilePath1(book2.getFilePath1());
        book.setFilePath2(book2.getFilePath2());
        book.setFilePath3(book2.getFilePath3());
        bookDAO.save(book);
        return "redirect:list";
    }

    @RequestMapping("/admin/upload")  //上传图书页面
    public String turnToUpload(){
        return "upload";
    }

    @RequestMapping("/admin/analysis")  //图表页面
    public String turnToAnalysis(Model m){
        List<Book> list=bookDAO.findAll();
        for(int i=1;i<=6;i++){
            m.addAttribute("book"+i,getMax(list));
        }
        m.addAttribute("users",dao2.getAll().size());//用户数量
        m.addAttribute("visits",dao.getByCurrentDay().size());//访问数量
        m.addAttribute("uploads",bookDAO.findAll().size());//上传图书数量
        m.addAttribute("articles",dao3.getAll().size());//用户上传文章数量
        Map<String, String> map=new HashMap<>();
        int num=dao4.findAll().size();
        int i=notRepeatedRandom(null,num);
        int j=notRepeatedRandom(new int[]{i},num);
        int k=notRepeatedRandom(new int[]{i,j},num);
        int l=notRepeatedRandom(new int[]{i,j,k},num);
        int n=notRepeatedRandom(new int[]{i,j,k,l},num);
        int z=notRepeatedRandom(new int[]{i,j,k,l,n},num);
        map.put("1",dao4.getOne(i+1).getMessage());
        map.put("2",dao4.getOne(j+1).getMessage());
        map.put("3",dao4.getOne(k+1).getMessage());
        map.put("4",dao4.getOne(l+1).getMessage());
        map.put("5",dao4.getOne(n+1).getMessage());
        map.put("6",dao4.getOne(z+1).getMessage());
        m.addAttribute("advice",map);
        System.out.println(map);
        return "analysis";
    }

    private int notRepeatedRandom(int[] s,int size){
        if(s==null) return new Random().nextInt(size);
        Random random=new Random();
        while(true){
            int num=random.nextInt(size);
            boolean flag=true;
            for(int k:s){
                if(k==num) flag=false;
            }
            if(flag) return num;
        }
    }

    @RequestMapping("admin/bookNum") //ajax返回各种书的数量
    @ResponseBody
    public Map<String,Integer> bookNum(){
        Map<String,Integer> map=new HashMap<>();
        map.put("histor",bookDAO.findByCategory("History").size());
        map.put("literature",bookDAO.findByCategory("Literature").size());
        map.put("art",bookDAO.findByCategory("Art").size());
        map.put("education",bookDAO.findByCategory("Education").size());
        map.put("military",bookDAO.findByCategory("Military").size());
        map.put("science",bookDAO.findByCategory("Science").size());
        return  map;
    }

    @RequestMapping("/admin/visitNum")
    @ResponseBody
    public Map<String,Integer> visitNum(){
        Map<String,Integer> map=new HashMap<>();
        map.put("Jan",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"01").size());
        map.put("Feb",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"02").size());
        map.put("Mar",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"03").size());
        map.put("Apr",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"04").size());
        map.put("May",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"05").size());
        map.put("Jun",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"06").size());
        map.put("Jul",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"07").size());
        map.put("Aug",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"08").size());
        map.put("Sep",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"09").size());
        map.put("Oct",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"10").size());
        map.put("Nov",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"11").size());
        map.put("Dec",dao.getByYearAndMonth(String.valueOf(new Date().getYear()+1900),"12").size());
        return map;
    }

    @RequestMapping("/admin/yearlyVisit")
    @ResponseBody
    public Map<String,Integer> yearlyVisit(){
        Map<String,Integer> map=new HashMap<>();
        map.put("2014",dao.getByYear("2014").size());
        map.put("2015",dao.getByYear("2015").size());
        map.put("2016",dao.getByYear("2016").size());
        map.put("2017",dao.getByYear("2017").size());
        map.put("2018",dao.getByYear("2018").size());
        map.put("2019",dao.getByYear("2019").size());
        map.put("2020",dao.getByYear("2020").size());
        return map;
    }

    @RequestMapping("/admin/yearlyRegist")
    @ResponseBody
    public Map<String,Integer> yearlyRegist(){
        Map<String,Integer> map=new HashMap<>();
        map.put("2014",dao2.getByRegistYear("2014").size());
        map.put("2015",dao2.getByRegistYear("2015").size());
        map.put("2016",dao2.getByRegistYear("2016").size());
        map.put("2017",dao2.getByRegistYear("2017").size());
        map.put("2018",dao2.getByRegistYear("2018").size());
        map.put("2019",dao2.getByRegistYear("2019").size());
        map.put("2020",dao2.getByRegistYear("2020").size());
        return map;
    }




    private void addLibrary(Book book,MultipartFile file,int id){  //保存书的文字材料上传
        String fileName=file.getOriginalFilename();
        fileName=System.currentTimeMillis()+fileName.split("\\\\")[fileName.split("\\\\").length-1]; //格式为时间戳加源文件名
        File filepath=new File("D:"+ File.separator+"librarybook"+ File.separator+"upload");
        if (!filepath.exists()) filepath.mkdirs();
        String fileDir="D:"+ File.separator+"librarybook"+ File.separator+"upload"+ File.separator+fileName;   //存在D:library upload下
        File f=new File(fileDir);
        f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(id==1){
            book.setFilePath1(fileDir);
        }else if(id==2){
            book.setFilePath2(fileDir);
        }else{
            book.setFilePath3(fileDir);
        }

    }
}
