package newsreader.downloader;

import java.util.ArrayList;
import java.util.List;

public class ParallelDownloader extends Downloader implements Runnable{

    private List<String> urls = new ArrayList<>();
    @Override
    public int process(List<String> urls) {
        this.urls = urls;
        Thread t1 = new Thread(this);
        t1.start();
        return urls.size();
    }

    @Override
    public void run() {
        for (String url : urls) {
            saveUrl2File(url);
        }
    }
}
