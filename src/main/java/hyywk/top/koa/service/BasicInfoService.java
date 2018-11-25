package hyywk.top.koa.service;

import hyywk.top.koa.entity.Account;
import hyywk.top.koa.entity.BasicInfo;
import hyywk.top.koa.jpa.BasicInfoJpa;
import jdk.internal.util.xml.impl.Input;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import javax.transaction.Transactional;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class BasicInfoService {
    private Logger logger = LoggerFactory.getLogger( BasicInfoService.class );
    @Value("${account.photo.location}")
    private String photoPath;
    @Value("${account.default.photo.location}")
    private String photoDefaultPath;
    @Autowired
    private BasicInfoJpa basicInfoJpa;
    @Transactional
    public void savePhoto(String filename, InputStream inputStream, Long id ) throws IOException, Exception  {

        File file = new File( this.getUserPhotoDir(this.photoPath,id) );
        if ( !file.exists() ) {
            this.logger.info("创建图片目录");
            file.mkdirs();
        }

        String suffix = filename.substring( filename.indexOf( "." )); // 文件名后缀
        String newFilename = UUID.randomUUID()  + suffix;
        String path = this.getUserPhotoDir(this.photoPath,id) + "/" + newFilename;
        BufferedInputStream bufferedInputStream = new BufferedInputStream( inputStream );
        byte[] bs = new byte[ bufferedInputStream.available() ];
        bufferedInputStream.read(bs);

        FileOutputStream fileOutputStream = new FileOutputStream( path );
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream( fileOutputStream );
        bufferedOutputStream.write(bs);
        bufferedOutputStream.flush();

        bufferedInputStream.close();
        bufferedOutputStream.close();
        fileOutputStream.close();

        // 保存头像信息
        if ( this.basicInfoJpa.updatePhoto( newFilename, id) == 0 ) {
            throw new Exception( "保存至数据库失败" );
        }
    }

    public void outputPhoto(String photopath, Long id, OutputStream outputStream) throws IOException{
        // 定位文件是否存在
        File file = new File( this.getUserPhotoFullDir(this.photoPath,id,photopath));
        if ( file.exists() ) {
            this.logger.error("用户头像保存正常");
        } else {
            // 不存在，读取默认图片
            this.logger.error("用户头像不存在");
            file = new File(this.photoDefaultPath);
        }

        byte[] bs = FileCopyUtils.copyToByteArray( file );
        outputStream.write( bs );
    }
    public BasicInfo findById(Long id) {
        return basicInfoJpa.findById(id).get();
    }
    public BasicInfo findByAccount(Long id) {
        Account account = new Account();
        account.setId(id);

        return basicInfoJpa.findByAccount( account );
    }

    public BasicInfo updateBasicInfo(BasicInfo basicInfo) {
        // 判断当前有无id，如果没有，则数据库当中存在该账户的一个basicInfo, 则取出该id
        BasicInfo basicInfo1 = this.basicInfoJpa.findByAccount( basicInfo.getAccount() );
        if ( basicInfo1 != null ) {
            basicInfo.setId( basicInfo1.getId() );
        }
        BasicInfo basicInfo2 = this.basicInfoJpa.save( basicInfo);
        return basicInfo2;
    }

    public String getUserPhotoDir( String path, Long id ) {
        return  path + "-" + id;
    }
    public String getUserPhotoFullDir( String path, Long id, String filename ) {
        return  this.getUserPhotoDir(path, id) + "/" + filename;
    }
}
