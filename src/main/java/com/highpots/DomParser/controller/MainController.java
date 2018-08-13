package com.highpots.DomParser.controller;

import com.highpots.DomParser.entity.WordCount;
import com.highpots.DomParser.model.PagingModel;
import com.highpots.DomParser.model.WordCountMetaModel;
import com.highpots.DomParser.service.WordService;
import com.highpots.DomParser.util.ModelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private WordService wordService;

    @RequestMapping(method = RequestMethod.GET, path = "/words", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<WordCountMetaModel> loadAllWords() {
        return ModelHelper.convert(wordService.loadAll());
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/parse", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<WordCountMetaModel> parseAndLoad(@RequestParam("url") String url) throws Exception {
        return ModelHelper.convert(wordService.parseAndSave(url));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/load", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingModel<WordCountMetaModel> load(
            @RequestParam("current") int current,
            @RequestParam("rowCount") int rowCount,
            @RequestParam("searchPhrase") String searchPhrase
    ) {

        PagingModel<WordCount> wordCountPagingModel = wordService.load(current, rowCount, searchPhrase);

        return new PagingModel<WordCountMetaModel>(wordCountPagingModel.getPageNumber(), wordCountPagingModel.getCount(), wordCountPagingModel.getTotal(), ModelHelper.convert(wordCountPagingModel.getResult()));
    }
}
