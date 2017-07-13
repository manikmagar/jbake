package org.jbake.app;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * Created by frank on 28.03.16.
 */
public class FileUtilTest {

    @Test
    public void testGetRunningLocation() throws Exception {

        File path = FileUtil.getRunningLocation();
        assertThat(new File("build/").getAbsolutePath()).isEqualTo(path.getPath());
    }
    
    @Test
    public void testFileIgnore(){
    	URL sourceUrl = this.getClass().getResource("/fixture");
    	File contentFolder = new File(sourceUrl.getFile(), "content");
    	
    	//Without filter, make sure ignorable file is selected
    	File files1[] = contentFolder.listFiles();
    	assertThat(files1).contains(new File(contentFolder, ".ignorablefile.html"));
    	
    	//When using filter, ignorable file should not be selected
    	File files2[] = contentFolder.listFiles(FileUtil.getFileFilter());
    	assertThat(files2).doesNotContain(new File(contentFolder, ".ignorablefile.html"));
    }
    
    @Test
    public void testFolderIgnore(){
    	URL sourceUrl = this.getClass().getResource("/fixture");
    	File contentFolder = new File(sourceUrl.getFile() + "/content/blog");
    	
    	//Without filter, make sure ignorable folder is selected
    	File files1[] = contentFolder.listFiles();
    	assertThat(files1).contains(new File(contentFolder, "ignorablefolder"));
    	
    	//When using filter, ignorable folder should not be selected
    	File files2[] = contentFolder.listFiles(FileUtil.getFileFilter());
    	assertThat(files2).doesNotContain(new File(contentFolder, "ignorablefolder"));
    }
}