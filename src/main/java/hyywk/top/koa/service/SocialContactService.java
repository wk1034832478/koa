package hyywk.top.koa.service;

import hyywk.top.koa.entity.PersonDynamic;
import hyywk.top.koa.jpa.PersonDynamicJpa;
import hyywk.top.koa.util.KoaFileUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.UUID;

@Service
public class SocialContactService {
    @Value( "${social.contact.images.location}" )
    private String picturesPath;

    // 返回图片url
    public String savePicture( InputStream inputStream, String filename ) throws IOException  {
        //FileCopyUtils.
        String suffix = KoaFileUtil.getSuffixByFilename( filename );
        String newFilename = UUID.randomUUID()  + suffix;
        KoaFileUtil.copyToDisk( inputStream, this.picturesPath, newFilename);
        return newFilename;
    }

    public void readPicture(String filename, OutputStream outputStream) throws IOException  {
        KoaFileUtil.outputFile( picturesPath + "/" + filename, outputStream);
        outputStream.close();
    }


}
