package org.jbake.app;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by frank on 28.03.16.
 */
public class FileUtilTest {

    @Test
    public void testGetRunningLocation() throws Exception {

        File path = FileUtil.getRunningLocation();
        assertEquals(new File("build/").getAbsolutePath(), path.getPath());
    }
}