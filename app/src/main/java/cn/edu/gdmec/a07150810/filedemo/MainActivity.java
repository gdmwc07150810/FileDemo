package cn.edu.gdmec.a07150810.filedemo;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fPhonedicectory;
    private File fExternalStoragePublicDirectory;
    private File fExternalStorageDirectory;
    private File fDataStorage;
    private File fDownloadCacheDirectory;
    private File fRootDirectory;
    private String name;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.result);
        fPhonedicectory = this.getFilesDir();
        fExternalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDirectory = Environment.getExternalStorageDirectory();
        fDataStorage = Environment.getDataDirectory();
        fDownloadCacheDirectory = Environment.getDownloadCacheDirectory();
        fRootDirectory = Environment.getRootDirectory();
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_REMOVED)){
            Button btn = (Button) findViewById(R.id.externalStoragePublicDirectory);
            btn.setEnabled(false);
        }
    }
    public void phoneDirectory(View v){
        path = fPhonedicectory.getPath();
        try{
            FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
            fos.write("hello".getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
        listFiles(path);
    }
    public void externalStoragePublicDirectory(View v){
        path = String.valueOf(fExternalStoragePublicDirectory.getAbsoluteFile());
        listFiles(path);
    }
    public void externalStorageDirectory(View v){
        path = String.valueOf(fExternalStorageDirectory.getAbsoluteFile());
        listFiles(path);
    }
    public void dataStorage(View v){
        path = String.valueOf(fDataStorage.getAbsoluteFile());
        listFiles(path);
    }
    public void downloadCacheDirectory(View v){
        path = String.valueOf(fDownloadCacheDirectory.getAbsoluteFile());
        listFiles(path);
    }
    public void rootDirectory(View v){
        path = String.valueOf(fRootDirectory.getAbsoluteFile());
        listFiles(path);
    }
    private boolean listFiles(String path){
        name = "路径:"+path+"\n文件清单:\n";
        File file = new File(String.valueOf(path));
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for(File f:file.listFiles()){
                path = String.valueOf(f.getAbsoluteFile());
                name = name + f.getName()+"\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}
