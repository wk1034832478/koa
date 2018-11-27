package hyywk.top.koa.util;

import org.hibernate.result.Output;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class KoaFileUtil {
    public static void copyToDisk(InputStream inputStream , String path, String filename ) throws IOException {
        byte[] bs = FileCopyUtils.copyToByteArray( inputStream ); // 转化为字节流
        File file = new File( path );
        if ( !file.exists() ) { // 创建工作目录
            file.mkdirs();
        }
        File file1 = new File( path,filename );
        FileCopyUtils.copy(bs, file1);
    }

    public static String getSuffixByFilename( String filename ) {
        return filename.substring( filename.indexOf( "." )); // 文件名后缀
    }

    public static void outputFile(String filepath , OutputStream outputStream) throws IOException {
        // 定位文件是否存在
        File file = new File( filepath );
        if ( !file.exists() ) {
            // 不存在，读取默认图片
            throw new IOException("图片不存在");
        }
        byte[] bs = FileCopyUtils.copyToByteArray( file );
        FileCopyUtils.copy( bs , outputStream);
    }
}
