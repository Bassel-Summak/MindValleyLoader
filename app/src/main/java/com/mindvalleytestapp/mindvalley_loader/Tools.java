package com.mindvalleytestapp.mindvalley_loader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Tools {


    public void CopyFromStream(InputStream inputStream, OutputStream outputStream)
    {
        final int buffer_size=1024;
        try
        {
            byte[] bytes=new byte[buffer_size];
            for(;;)
            {
                int count=inputStream.read(bytes, 0, buffer_size);
                if(count==-1)
                    break;
                outputStream.write(bytes, 0, count);
            }
        }
        catch(Exception ex){}
    }

    public Bitmap decodeAndReadTheFile(File f){

        try {

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            FileInputStream First_stream=new FileInputStream(f);
            BitmapFactory.decodeStream(First_stream,null,options);
            First_stream.close();

            final int REQUIRED_SIZE=350;

            int width_tmp=options.outWidth, height_tmp=options.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2 < REQUIRED_SIZE || height_tmp/2 < REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize=scale;
            FileInputStream Second_stream=new FileInputStream(f);
            Bitmap bitmap=BitmapFactory.decodeStream(Second_stream, null, options2);
            Second_stream.close();
            return bitmap;

        } catch (FileNotFoundException e) {
        }

        catch (IOException e) {
        }
        return null;
    }

}
