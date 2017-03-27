package com.greatconan.conan.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

//作者：文远
//链接：https://www.zhihu.com/question/31427895/answer/80558995
//来源：知乎
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

public class Start4 implements PageProcessor {
// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
   private Site site = Site.me().setRetryTimes(3).setSleepTime(0);


   // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
   public void process(Page page) {
// 部分二：定义如何抽取页面信息，并保存下来
      //这段代码的分为两部分:
      // page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all()用于获取所有满足"(https:/ /github\.com/\w+/\w+)"这个正则表达式的链接，
      // page.addTargetRequests()则将这些链接加入到待抓取的队列中去。
      page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
      page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+)").all());

      page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
      //这段代码使用了XPath，它的意思是“查找所有class属性为'entry-title public'的h1元素，并找到他的strong子节点的a子节点，并提取a节点的文本信息”。 对应的Html是这样子的：
      page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
      if (page.getResultItems().get("name")==null){
//skip this page
         page.setSkip(true);
      }
// 部分三：从页面发现后续的url地址来抓取
      page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
   }

   public Site getSite() {
return site;
   }

public static void main(String[] args) {
      Spider.create(new Start4())
//从"https://github.com/code4craft"开始抓
            .addUrl("https://github.com/code4craft")
//开启5个线程抓取
            .thread(5)
//启动爬虫
            .run();
   }
}