package cn.edu.whu.waytojavaeurekaclient.controller;

import cn.edu.whu.waytojavaeurekaclient.service.qqonline.QqOnlineWebService;
import cn.edu.whu.waytojavaeurekaclient.service.qqonline.QqOnlineWebServiceSoap;
import cn.edu.whu.waytojavaeurekaclient.service.randomfonts.ArrayOfString;
import cn.edu.whu.waytojavaeurekaclient.service.randomfonts.RandomFontsWebService;
import cn.edu.whu.waytojavaeurekaclient.service.randomfonts.RandomFontsWebServiceSoap;
import cn.edu.whu.waytojavaeurekaclient.service.validatecode.ValidateCodeWebService;
import cn.edu.whu.waytojavaeurekaclient.service.validatecode.ValidateCodeWebServiceSoap;
import cn.edu.whu.waytojavaeurekaclient.service.validateemail.ValidateEmailWebService;
import cn.edu.whu.waytojavaeurekaclient.service.validateemail.ValidateEmailWebServiceSoap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.util.List;


@RestController
public class EmailLoginValidate {
    @RequestMapping("/logincheck")

    public void index(){
        String emailaddress = "476417477@qq.com";
        //首先检查邮箱是否有效
        checkEmailValidate(emailaddress);
        //其次检查QQ是否登录
        checkQQonline(emailaddress.split("@")[0]);
        //生成随机验证码
        generateValidate();
    }

    public void checkEmailValidate(String email){
        ValidateEmailWebService validateEmailWebService = new ValidateEmailWebService();
        ValidateEmailWebServiceSoap validateEmailWebServiceSoap =
                validateEmailWebService.getValidateEmailWebServiceSoap();
        Short status = validateEmailWebServiceSoap.validateEmailAddress(email);
        System.out.println("Is Email validate?: " + (status == 1 ?"yes":"no"));
    }

    public void checkQQonline(String qqnum){
        QqOnlineWebService qqOnlineWebService = new QqOnlineWebService();
        QqOnlineWebServiceSoap qqOnlineWebServiceSoap =
                qqOnlineWebService.getQqOnlineWebServiceSoap();
        String status = qqOnlineWebServiceSoap.qqCheckOnline(qqnum);
        System.out.println("Is QQ login?: " + (status.equals("Y")?"yes":"no"));
    }

    public void generateValidate(){
        RandomFontsWebService randomFontsWebService = new RandomFontsWebService();
        RandomFontsWebServiceSoap randomFontsWebServiceSoap =
                randomFontsWebService.getRandomFontsWebServiceSoap();
        ArrayOfString result = randomFontsWebServiceSoap.getCharFonts(5);
        List<String> fonts = result.getString();
        String out = "";
        for(String font: fonts){
            out += (font);
        }

        ValidateCodeWebService validateCodeWebService = new ValidateCodeWebService();
        ValidateCodeWebServiceSoap validateCodeWebServiceSoap =
                validateCodeWebService.getValidateCodeWebServiceSoap();
        byte[] image = validateCodeWebServiceSoap.enValidateByte(out);
        byte2image(image, "E:\\validatecode\\" + out + ".jpg");
    }

    //byte数组到图片
    public void byte2image(byte[] data,String path){
        if(data.length<3||path.equals("")) return;
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }

}
