/*
 * The MIT License
 *
 * Copyright 2015 jdlee.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.jbake.app.template;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.jbake.app.Crawler;
import org.jbake.app.Renderer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author jdlee
 */
public class GroovyTemplateEngineRenderingTest extends AbstractTemplateEngineRenderingTest{

    public GroovyTemplateEngineRenderingTest() {
        super("groovyTemplates", "gsp");
        outputStrings.put("categories", Arrays.asList("/blog/2012/first-post.html"));
        outputStrings.put("categories_index", Arrays.asList("<a href=\"/categories/Technology.html\"/>"));
    }
    
  @Test
  @Override
  public void renderCategories() throws Exception {
      Crawler crawler = new Crawler(db, sourceFolder, config);
      crawler.crawl(new File(sourceFolder.getPath() + File.separator + "content"));
      Renderer renderer = new Renderer(db, destinationFolder, templateFolder, config);
      renderer.renderCategories(db.getCategories(), "categories");

      // verify
      File outputFile = new File(destinationFolder + File.separator + "categories" + File.separator + "Technology.html");
      Assert.assertTrue(outputFile.exists());
      String output = FileUtils.readFileToString(outputFile);
      for (String string : outputStrings.get("categories")) {
          assertThat(output).contains(string);
      }
      
      // verify index.html file
      File indexFile = new File(destinationFolder + File.separator + "categories" + File.separator + "index.html");
      Assert.assertTrue(indexFile.exists());
      String indexData = FileUtils.readFileToString(indexFile);
      for (String string : outputStrings.get("categories_index")) {
          assertThat(indexData).contains(string);
      }
  }

}
